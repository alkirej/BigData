package net.alkire.streaming

import java.io.{File, FileOutputStream, OutputStream, PrintWriter}

import kafka.serializer.StringDecoder
import kafka.serializer.DefaultDecoder
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.kafka.KafkaUtils

import sys.process._

object KafkaToHadoop {
    val OUTPUT_FILE_NAME: String = "asg24.out"
    val HDFS_DIR: String         = "/data" +
            ""
    /**
     * Append the supplied text to our output file - followed by a newline.
     * @param text the text to append to the file
     */
    def appendToFile( text: String ): Unit = {
        val pw = new PrintWriter( new FileOutputStream( new File( "asg24.out" ), true ) )
        pw.write( text )
        pw.write( "\n" )
        pw.close
    
        val cmd: String = "/opt/hadoop/bin/hdfs dfs -put -f " +
                            OUTPUT_FILE_NAME + " " +
                            HDFS_DIR + "/" + OUTPUT_FILE_NAME
        cmd.!
    }
    
    def main(args: Array[String]): Unit = {
        // CREATE SPARK STREAMING CONTEXT OBJECT
        val conf = new SparkConf().setAppName( "StreamFromKafka" )
                                  .setMaster("local[2]")
        val ssc = new StreamingContext(conf, Seconds(15))
        
        // CREATE A DSTREAM TO KAFKA
        val topics = "GlobalWarming,Tornado".split(",").toSet
        val kParams = Map[String, String]("metadata.broker.list" -> "jeff-laptop-apt:50001,jeff-laptop-apt:50002")
        
        val strm = KafkaUtils.createDirectStream[String, Array[Byte], StringDecoder, DefaultDecoder](
            ssc, kParams, topics
        )
        
        // SUPPLY THE CODE TOE EXECUTE EACH TIME A BATCH IS PROCESSED
        strm.foreachRDD(rdd =>
            rdd.foreachPartition( recs => {
                recs.foreach( r => appendToFile( r._2.map(_.toChar).mkString) )
            }
            )
        )
        
        
        ssc.start()
        ssc.awaitTermination()
    }
}