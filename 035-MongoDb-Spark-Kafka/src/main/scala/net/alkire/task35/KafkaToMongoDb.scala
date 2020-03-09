package net.alkire.task35

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.{DataFrame, SparkSession, Row}


object KafkaToMongoDb
{
    def main( args: Array[ String ] ): Unit =
    {
        Logger.getLogger("org").setLevel(Level.WARN)
    
        // BUILD A SPARK SESSION
        val spark: SparkSession = SparkSession.builder
            .master(Constants.Master)
            .appName(Constants.AppName)
            .getOrCreate()
    
        // CREATING A STREAMING DATAFRAME FROM A KAFKA SOURCE
        val df: DataFrame = spark.readStream
            .format(Constants.KafkaFormat)
            .option(Constants.ConfigServers, Constants.ServerList)
            .option(Constants.ConfigSubscribe, Constants.TopicLIst)
            .load()
            .selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)")
            .as("msg")
    }
}
