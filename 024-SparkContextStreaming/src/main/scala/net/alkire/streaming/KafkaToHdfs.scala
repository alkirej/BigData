package net.alkire.streaming
import sys.process._
import java.io.{File, PrintWriter}
import java.util.Arrays
import java.util.Properties

import kafka.serializer.StringDecoder

import scala.collection.mutable.StringBuilder
import org.apache.kafka.common.TopicPartition
import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecords, KafkaConsumer}
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.kafka.KafkaUtils


object KafkaToHdfs {
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
    def readResults_direct( kc: KafkaConsumer[String,String] ): String = {
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
     * Display the results supplied by the consumer
     * @param kc  the consumer who will give us the results
     */
    def readResults( kc: KafkaConsumer[String,String] ): Seq[String] = {
// Create context with 2 second batch interval
        val sparkConf = new SparkConf().setMaster("local[2]").setAppName( "KafkaToHdfs" )
        val ssc = new StreamingContext(sparkConf, Seconds(2))

// Create direct kafka stream with brokers and topics
        val topicSet: scala.collection.immutable.Set[String]
            = "GlobalWarming2".split(",").toSet
        
        val kafkaParams = Map[String, String](
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG -> Constants.ConsumerBrokerName,
                ConsumerConfig.GROUP_ID_CONFIG -> Constants.GroupId,
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG -> Constants.DeserializingClass,
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG -> Constants.DeserializingClass,
                "zookeeper.connect" -> "localhost:2181"
        )
        val messages
            = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](
                            ssc, kafkaParams, topicSet
            )
        
        messages.map(_._2)
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
