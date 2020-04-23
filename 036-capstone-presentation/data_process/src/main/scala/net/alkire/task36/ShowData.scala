package net.alkire.task36

import org.apache.spark.sql.{DataFrame, SparkSession}

object ShowData
{
    def show( df: DataFrame ): Unit =
    {
        df.rdd.foreach( println )
    }

    def showData: Unit =
    {
        val spark: SparkSession = SparkSession.builder
            .master(Constants.Master)
            .appName(Constants.AppName)
            .enableHiveSupport()
            .config("spark.sql.warehouse.dir","hdfs://localhost:50501/user/hive/warehouse")
            .config("hive.metastore.warehouse.dir","hdfs://localhost:50501/user/hive/warehouse")
            .getOrCreate()
        spark.sql( Constants.SqlUseDb )
        
        val gameCtDataDf: DataFrame = spark.sql( s"SELECT COUNT(*) FROM ${Constants.TblNameGame}")
        show( gameCtDataDf )
        val rssCtDataDf: DataFrame = spark.sql( s"SELECT COUNT(*) FROM ${Constants.TblNameRss}")
        show( rssCtDataDf )
        
    }
}
