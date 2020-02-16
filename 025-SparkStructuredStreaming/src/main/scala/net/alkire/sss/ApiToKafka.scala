package net.alkire.sss
import java.net.{URL, URLConnection}
import java.util.Properties

import scala.language.postfixOps
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

object ApiToKafka {
    /**
     * Connect to the specified url, read the data and return it.
     * Side Effect: data read from network
     *
     * @param  httpUrl  the Url to read message from
     * @return the data from the kafka message
     */
    def readDataFromApi( httpUrl: String ): String = {
        // CONNECT OVER WEB
        val url: URL = new URL( httpUrl )
        val conn: URLConnection = url.openConnection
        conn.addRequestProperty( "User-Agent", "Mozilla/5.0" )
        
        // STREAM DATA FROM SITE
        val inputStream = conn.getInputStream
        val content = scala.io.Source.fromInputStream( inputStream ).mkString
        inputStream.close()

        // RETURN THE CONTENT RETRIEVED
        content
    }
    
    /**
     * Setup a properties object to prepare for a connection to kafka as
     * a producer
     *
     * @return  Properties object needed to open the desired Producer
     */
    val kafkaProps: Properties = {
        val p: Properties = new Properties
        p.put("bootstrap.servers", Constants.KafkaBrokerAddr)
        p.put("value.serializer",  Constants.SerializingClass)
        p.put("key.serializer",    Constants.SerializingClass)
        p.put("char.encoder",      Constants.CharEncoding)
        p
    }
    
    /**
     * Create a new message to be sent to Kafka
     * @param data  data to send in message
     * @return a record primed and ready to ship to kafka
     */
    def message( data: String ): ProducerRecord[String,String] = {
        val key: String = "temp"
        new ProducerRecord[String,String]( Constants.TopicName, key, data )
    }
    
    /**
     * Write a message to the kafka borker
     * @param data data to send
     */
    def writeToKafka( data: String ): Unit = {
        // SETUP FOR MESSAGE SEND
        val producer: KafkaProducer[String,String] = new KafkaProducer[String,String]( kafkaProps )
        val record = message( data )

        // SEND MESSAGE AND CLEAN UP
        producer.send(record )
        producer.flush()
        producer.close()
    }
    
    /**
     * Read the latest weather data from the web api and send it to a kafka
     * server
     * @param args  command line
     */
    def main( args: Array[ String ] ): Unit = {
        val data: String = readDataFromApi( Constants.ApiLocation )
        writeToKafka(data)
    }
    
}
