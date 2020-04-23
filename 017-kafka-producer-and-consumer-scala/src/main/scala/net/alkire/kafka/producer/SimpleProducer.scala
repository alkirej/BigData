package net.alkire.kafka.producer

import java.util.Properties
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

object SimpleProducer {
    val props: Properties = {
        val p: Properties = new Properties
        p.put("bootstrap.servers", Constants.KafkaBrokerAddr)
        p.put("value.serializer", Constants.SerializingClass)
        p.put("key.serializer", Constants.SerializingClass)
        p
    }

  def message: ProducerRecord[String,String] = {
      val key: String = "key"
      val value = "I always produce the same message - so boring, but, hey ... I'm boring!"
      new ProducerRecord[String,String]( Constants.TopicName, key, value )
  }

  def main(args: Array[String]): Unit = {
      val producer: KafkaProducer[String,String] = new KafkaProducer[String,String](props)
      val record = message
      producer.send(record)
      producer.close
  }

}
