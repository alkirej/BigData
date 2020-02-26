package alkire.net.task33

import org.apache.hadoop.hbase.util.Bytes
import org.apache.log4j.{Level, Logger}
import org.apache.hadoop.hbase.client.Put
import org.apache.spark.sql.{DataFrame, SparkSession, Row}

object WriteToHbase
{
    var _id: Int = 1000
    
    def main( args: Array[ String ] ): Unit = {
        Logger.getLogger("org").setLevel( Level.WARN )
        
        // BUILD A SPARK SESSION
        val spark: SparkSession = SparkSession.builder
            .master( Constants.Master )
            .appName( Constants.AppName )
            .getOrCreate()
        
        // CREATING A STREAMING DATAFRAME FROM A KAFKA SOURCE
        val df: DataFrame = spark.readStream
            .format( Constants.KafkaFormat)
            .option( Constants.ConfigServers,   Constants.ServerList )
            .option( Constants.ConfigSubscribe, Constants.TopicLIst  )
            .load()
            .selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)")
            .as("msg")
    
    
    
        val w = new HbaseForeachWriter[Row] {
                    override val tableName: String = "try2"
        
                    override def toPut(record: Row): Put = {
                        _id += 1
                        val key: String    = _id.toString
                        val value: String = record.get(1).toString
                    
                        val p = new Put( Bytes.toBytes( key ) )
                        p.addColumn( Bytes.toBytes( Constants.ColFamName ),
                                     Bytes.toBytes( Constants.ColName ),
                                     Bytes.toBytes( value )
                        )
                    
                        p
                    }
                
                }

        val qy = df.writeStream.foreach( w ).start()
    
        qy.awaitTermination()
    }
}
