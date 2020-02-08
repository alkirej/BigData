package net.alkire.kafka.consumer

import java.util.Arrays
import java.util.Properties

import org.apache.kafka.common.TopicPartition
import org.apache.kafka.clients.consumer.{ConsumerRecords, KafkaConsumer}

object ScreenConsumer {
    /**
     * Setup the properties for the kafka connection
     * @return configuration for the kafka connection
     */
    def config: Properties = {
        val props: Properties = new Properties
        props.put ("client.id", Constants.ClientId )
        props.put ("auto.offset.reset", Constants.Offset )
        props.put ("bootstrap.servers", Constants.BrokerName )
        props.put ("key.deserializer", Constants.SerializingClass )
        props.put ("value.deserializer", Constants.SerializingClass )
        props.put ("group.id", Constants.GroupId )
        props
    }

    /**
     * Display the results supplied by the consumer
     * @param kc  the consumer who will give us the results
     */
    def display_results( kc: KafkaConsumer[String,String] ) = {
        var results: ConsumerRecords[String,String] = kc.poll(2000)

        val iter = results.iterator()
        while (iter.hasNext) {
            val r = iter.next
            println( r.value )
        }

    }

    /**
     * Create and setup the consumer
     * @return the consumer ready and waiting to read your data
     */
    def consumer(): KafkaConsumer[String,String] = {
        val consumer = new KafkaConsumer[String, String](config)
        val topic = Constants.TopicName
        val topicPartition = new TopicPartition(topic, 0)

        val topics = Arrays.asList(topicPartition)
        consumer.assign(topics)
        consumer.seek( topicPartition, 0 )
        consumer
    }

    def main(args: Array[String]): Unit = {
        display_results( consumer )
    }
}
