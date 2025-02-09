package alkire.net.task33

import org.apache.spark.internal.Logging
import org.apache.spark.sql.execution.streaming.Sink

import org.apache.spark.sql.sources.{DataSourceRegister, StreamSinkProvider}
import org.apache.spark.sql.streaming.OutputMode
import org.apache.spark.sql.{DataFrame, SQLContext}

class HbaseSink( options: Map[String,String]) extends Sink with Logging
{
    // store hBaseCatalog
    private val hBaseCatalog = options.get("hbasecat").map(_.toString).getOrElse("")
    
    override def addBatch(batchId: Long, data: DataFrame): Unit = synchronized
    {
        val df = data.sparkSession.createDataFrame(data.rdd, data.schema)
        df.write
            //.options(Map(HBaseTableCatalog.tableCatalog -> hBaseCatalog,
            //    HBaseTableCatalog.newTable -> "5"))
            .format("org.apache.spark.sql.execution.datasources.hbase").save()
    }
}

class HBaseSinkProvider extends StreamSinkProvider with DataSourceRegister {
    def createSink( sqlContext: SQLContext,
                    parameters: Map[String, String],
                    partitionColumns: Seq[String],
                    outputMode: OutputMode): Sink =
    {
        new HbaseSink(parameters)
    }
    
    def shortName(): String = "hbase"
}

