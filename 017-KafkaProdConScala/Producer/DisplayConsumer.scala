package net.alkire.kafka

import kafka.api.{FetchRequest, FetchRequestBuilder, FetchResponse}
import kafka.common.TopicAndPartition
import kafka.consumer.SimpleConsumer
import kafka.message.MessageSet

object DisplayConsumer {

    def main(args: Array[String]): Unit = {
        //val tp: TopicAndPartition = new TopicAndPartition( Constants.TopicName, Constants.Partition )
        val cons: SimpleConsumer = new SimpleConsumer( Constants.KafkaMachineName, Constants.BrokerPort, 2000, 64000, Constants.ClientId )
        //cons.earliestOrLatestOffset(tp, 1, 0)
        val req: FetchRequest = new FetchRequestBuilder()
                        .clientId( Constants.ClientId)
                        .addFetch( Constants.TopicName, Constants.Partition, 0, 1 )
                        .build()
        val resp: FetchResponse = cons.fetch(req)

        println( resp )

        if ( resp.hasError ) {
            println( "UH NO!!!")

            val code: Int = resp.errorCode( Constants.TopicName, Constants.Partition )
            println("Error fetching data from the Broker: Reason: " + code);
        }

        val data = resp.data
        print (data)
        println
        println
while(true) {
        for ( v <- data.values ) {
            val msgSet: MessageSet = v.messages

            for ( w <- v.messages ) {
                print ("---:")
                print(w)
            }
        }
}

    }
}