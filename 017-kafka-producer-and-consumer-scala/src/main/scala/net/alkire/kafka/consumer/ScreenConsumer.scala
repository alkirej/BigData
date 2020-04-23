package net.alkire.kafka.consumer

import org.apache.kafka.clients.consumer.{ConsumerRecords, KafkaConsumer}
import org.apache.kafka.common.TopicPartition

import java.io.{BufferedWriter, File, FileWriter}
import java.util.{Arrays, Properties}

import sys.process._


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

    def writeToFile( contents: String ) : String = {
        val fileName: String = "kafka-scala.output"
        val file = new File( fileName )
        val writer = new BufferedWriter(new FileWriter(file))
        for (line <- contents) {
            writer.write(line)
        }
        writer.close()
        fileName
    }
    
    def moveFileToHDFS( fileName: String ) : Unit = {
        val cmd: String = "/opt/hadoop/bin/hdfs dfs -put -f " + fileName + " /data/" + fileName
        cmd !
    }
    
    /**
     * Display the results supplied by the consumer
     * @param kc  the consumer who will give us the results
     */
    def save_results( kc: KafkaConsumer[String,String] ) = {
        val results: ConsumerRecords[String,String] = kc.poll(2000)
        var sb: StringBuilder = new StringBuilder()
        
        val iter = results.iterator()
        while (iter.hasNext) {
            val r = iter.next
            sb.append(r)
            sb.append("\n")
        }

        val url: String = writeToFile( sb.toString() )
        moveFileToHDFS( url )
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
        save_results( consumer )
    }
}
