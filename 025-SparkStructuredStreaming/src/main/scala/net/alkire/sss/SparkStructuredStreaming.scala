package net.alkire.sss

import java.io.{BufferedWriter, File, FileWriter, PrintWriter}

import org.apache.commons.codec.StringEncoder
import org.apache.spark.sql.streaming.{DataStreamReader, StreamingQuery}
import org.apache.spark.sql.{DataFrame, Dataset, ForeachWriter, Row, SparkSession}
import org.apache.spark.sql.types.{StringType, StructType}

object SparkStructuredStreaming {
    
    
    def main( args: Array[ String ] ): Unit = {
        // BUILD A SPARK SESSION
        val spark: SparkSession = SparkSession.builder
                .master( "local" )
                .appName( "sss" )
                .getOrCreate()
        
        // CREATING A STREAMING DATAFRAME FROM A KAFKA SOURCE
        val df: DataFrame = spark.readStream
                .format( "kafka" )
                .option( "kafka.bootstrap.servers", "jeff-laptop-apt:50001,jeff-laptop-apt:50002" )
                .option( "subscribe", "GlobalWarming,Tornado" )
                .load()
                .selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)")
                .as("msg")
        
        // SET UP THE WRITE STREAM AND START STREAMING
        df.writeStream
          .option( "path", "received-from-kafka" )
          .option( "checkpointLocation", "checkpoint" )
          .option( "starting.offsets", "earliest" )
          .queryName( "two" )
          .format( "csv")
          .start()
          .awaitTermination()
    }
}
