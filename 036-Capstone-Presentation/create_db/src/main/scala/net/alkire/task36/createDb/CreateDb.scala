package net.alkire.task36.createDb

import org.apache.log4j.{Level, LogManager, Logger}
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

object CreateDb
{
    def setLoggingToError(): Unit =
    {
        val rootLgr: Logger = LogManager.getRootLogger()
        rootLgr.setLevel( Level.ERROR )
        
        val crtLgrs = rootLgr.getLoggerRepository.getCurrentLoggers
        while ( crtLgrs.hasMoreElements )
        {
            crtLgrs.nextElement().asInstanceOf[Logger].setLevel( Level.ERROR )
        }
    }

    def main(args: Array[String]): Unit =
    {
        setLoggingToError()
        System.setSecurityManager(null)

        // BUILD A SPARK SESSION
        val spark: SparkSession = SparkSession.builder
            .master(Constants.Master)
            .appName(Constants.AppName)
            .config( "spark.sql.warehouse.dir","hdfs://localhost:50501/user/hive/warehouse" )
            .config( "hive.metastore.warehouse.dir", "hdfs://localhost:50501/user/hive/warehouse" )
            .enableHiveSupport()
            .getOrCreate()

        import spark.implicits._
        setLoggingToError()

        // CONNECT TO APPROPRIATE DB
        spark.sql( Constants.SqlCreateDb )
        spark.sql( Constants.SqlUseDb )
    
    
        // CREATE NECESSARY TABLES
        spark.sql( Constants.SqlCreateGamesTable ).show()
        spark.sql( "SHOW DATABASES" ).show
        spark.sql( "SHOW TABLES" ).show
    
        spark.stop()
        
    }
    
}
