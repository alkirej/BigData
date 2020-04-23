package net.alkire.task35

import com.mongodb.spark.MongoSpark
import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.{DataFrame, Row, SparkSession}
import com.mongodb.spark.config._
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.ForeachWriter

import scala.collection.mutable.ListBuffer


object KafkaToMongoDb
{
    def main( args: Array[ String ] ): Unit =
    {
        Logger.getLogger("org").setLevel(Level.WARN)
    
        // BUILD A SPARK SESSION
        val spark: SparkSession = SparkSession.builder
            .master( Constants.Master )
            .appName( Constants.AppName )
            .config( Constants.ConfigOutputDbUrl, "mongodb://127.0.0.1/test.people" )
            .getOrCreate()

        // CREATING A STREAMING DATAFRAME FROM A KAFKA SOURCE
        val df: DataFrame = spark.readStream
            .format(Constants.KafkaFormat)
            .option(Constants.ConfigServers, Constants.ServerList)
            .option(Constants.ConfigSubscribe, Constants.TopicLIst)
            .load()
            .selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)")
            .as("msg")
    
        implicit val sc: SparkContext = spark.sparkContext
        val writeConfig = WriteConfig( Map( "collection"     -> "people",
                                            "writeConcern.w" -> "majority",
                                            "spark.mongodb.output.database" -> "test"
                                          ),
                                       Some(WriteConfig(sc))
                                     )
        
        /** This does not work!!!
        val writer: MongodbForeachWriter[Row] = new MongodbForeachWriter[Row] {
                                                    def write( item: Row ): Unit = {
                                                        val data: List[String] = List(item.get(1).toString)
                                                        val rdd: RDD[String]   = sc.parallelize( data )
                                                        MongoSpark.save( rdd, writeConfig )
                                                        
                                                        println( "---> " + item.get(1).toString + " <---" )
                                                    }
                                                }
         */

        val writer: MongodbForeachWriter[Row] = new MongodbForeachWriter[Row] {
            def write( item: Row ): Unit = {
                ListFromKafka.append( item.get(1).toString )
            }
        }
        val query = df.writeStream.foreach( writer ).start
        query.awaitTermination( 20000 )
    
        MongoSpark.save( ListFromKafka.asRdd, writeConfig )
        
    }
}
