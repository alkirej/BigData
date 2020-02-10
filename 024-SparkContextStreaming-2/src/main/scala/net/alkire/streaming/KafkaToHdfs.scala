package net.alkire.streaming
import sys.process._

import java.io.{File, PrintWriter}
import java.util.Arrays
import java.util.Properties

import scala.collection.mutable.StringBuilder
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
        props.put ("bootstrap.servers", Constants.ConsumerBrokerName )
        props.put ("key.deserializer", Constants.DeserializingClass )
        props.put ("value.deserializer", Constants.DeserializingClass )
        props.put ("group.id", Constants.GroupId )
        props
    }
    
    /**
     * Display the results supplied by the consumer
     * @param kc  the consumer who will give us the results
     */
    def readResults( kc: KafkaConsumer[String,String] ): String = {
        val results: ConsumerRecords[String,String] = kc.poll(2000)
        val data: StringBuilder = new StringBuilder()
        
        val iter = results.iterator()
        while (iter.hasNext) {
            val r = iter.next
            data.append( r )
        }
        data.toString()
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
    
    /**
     * Write the given data to an hdfs location
     * @param data      the data to save to the hdfs(ystem)
     * @param location  the path to the disk where the file is to be stored.
     */
    def saveData( data: String, location: String ): Unit = {
        // WRITE DATA TO LOCAL DISK
        val local_writer = new PrintWriter( new File( location ) )
        local_writer.write( data )
        local_writer.close
    
        // COPY DATA ONTO THE HDFS SYSTEM
        ("/opt/hadoop/bin/hdfs dfs -put -f " + location + " " + Constants.HdfsLocation).!
    }
    
    /**
     * Retreive data from kafka server and store in hdfs
     * @param args   command line
     */
    def main(args: Array[String]): Unit = {
        val con: KafkaConsumer[String,String] = consumer
        val data = readResults( con )
        saveData( data, Constants.LocalPath )
    }
}
