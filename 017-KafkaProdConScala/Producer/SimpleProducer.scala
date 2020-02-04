package net.alkire.kafka

import java.util.Properties

import kafka.producer.{KeyedMessage, Producer, ProducerConfig}

object SimpleProducer {
    def conf: ProducerConfig = {
        val props: Properties = new Properties
        props.put("metadata.broker.list", Constants.KafkaBrokerAddr)
        props.put("serializer.class", "kafka.serializer.StringEncoder")
        new ProducerConfig(props)

    }

    def message: KeyedMessage[String,String] = {
        val key: String = "key"
        val value = "I always produce the same message - so boring, but, hey ... I'm boring!"
        new KeyedMessage[String,String]( Constants.TopicName, key, value )
    }

    def main(args: Array[String]): Unit = {
        val producer: Producer[String,String] = new Producer(conf)
        producer.send( message )
        producer.send()
    }

}
