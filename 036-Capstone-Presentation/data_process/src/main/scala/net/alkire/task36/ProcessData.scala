package net.alkire.task36
import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}
import org.apache.hadoop.fs.{FSDataInputStream, Path}
import org.apache.spark.sql.catalog.Database
import org.apache.spark.sql.functions.{col, explode}

object ProcessData
{
    def main(args: Array[String]): Unit =
    {
        Logger.getLogger(Constants.LoggerName).setLevel(Level.WARN)
        System.setSecurityManager(null)

        // BUILD A SPARK SESSION
        val spark: SparkSession = SparkSession.builder
                                                .master(Constants.Master)
                                                .appName(Constants.AppName)
                                                .enableHiveSupport()
                                                .config("spark.sql.warehouse.dir","hdfs://localhost:50501/user/hive/warehouse")
                                                .getOrCreate()
        import spark.implicits._

        val df: Dataset[Database] = spark.catalog.listDatabases()
        df.show
        
        // CONNECT TO APPROPRIATE DB
        spark.sql( "CREATE DATABASE test" ).show()
    
    
        // FIND AND PROCESS FILES IN THE DATA DIRECTORY
        val files: Array[Path] = HadoopFileUtils.getFilesInDir( Constants.DataDir )
        for ( f <- files )
        {
            val df: DataFrame = spark.read.json( Constants.DataDir + "/" + f.getName )
                                     .select( col = Constants.JsonGamesLabel )
            val expandedDf: DataFrame = df.select( explode($"games") ).select( "col.*" )
            expandedDf.write.mode("append").insertInto( Constants.TablenameGame )
            
expandedDf.show()
        }
        
        spark.close
    }
}
