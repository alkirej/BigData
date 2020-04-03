package net.alkire.task101

import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions.{col,regexp_replace}

/** Load restaurant violation data from .csv files. */
object LoadDataEm {
    /**
     * Returns a newly build SparkSession object.
     *
     * @return SparkSession object
     */
    def createSparkSession: SparkSession = {
        SparkSession.builder
            .master(Constant.Master)
            .appName(Constant.AppName)
            .config( "javax.jdo.option.ConnectionURL", "jdbc:mysql://localhost/metastore?createDatabaseIfNotExist=true" )
            .config( "javax.jdo.option.ConnectionDriverName", "com.mysql.jdbc.Driver" )
            .config( "javax.jdo.option.ConnectionUserName", "jeff" )
            .config( "javax.jdo.option.ConnectionPassword", "jeff" )
            .config( "spark.sql.warehouse.dir",      "hdfs://localhost:50501/user/hive/warehouse" )
            .config( "hive.metastore.warehouse.dir", "hdfs://localhost:50501/user/hive/warehouse" )
            .config( "datanucleus.fixedDatastore", "true" )
            .config( "datanucleus.autoCreateTables", "true" )
            .config( "hive.metastore.schema.verification", "false" )
            .enableHiveSupport()
            .getOrCreate()
    }

    /**
     * Returns the name of the .csv file for the given city
     *
     * @param  city's id (see Constant class)
     *
     * @return full path to data file
     */
    def buildFileName(city: Int): String = {
        assert(city >= 0)
        assert(city < Constant.FileMainName.length)

        val returnVal: StringBuilder = new StringBuilder()
        returnVal.append(Constant.DataDir)
        returnVal.append("/")
        returnVal.append(Constant.FilePrefix)
        returnVal.append(Constant.FileMainName(city))
        returnVal.append(Constant.FileSuffix)

        returnVal.toString
    }

    /**
     * Load the data file for the given city.
     *
     * @param  city  city whose data will be loaded
     * @param  spark session information to connect to filesystem (implicit)
     *
     * @return contents of datafile
     */
    def loadCsvFileFor(city: Int)(implicit spark: SparkSession): DataFrame = {
        assert(city >= 0)
        assert(city < Constant.FileMainName.length)
        assert(null != spark)

        val fn = buildFileName(city)

        val df = spark.read
            .option("header", "true") //first line in file has headers
            .option("multiline", "true")
            .csv(s"file://$fn")
        df.createOrReplaceTempView(Constant.TempViewName)

        if ("" == Constant.SqlByCity(city)) {
            println
            print(df.printSchema)
            null
        } else {
            spark.sqlContext.sql(Constant.SqlByCity(city)).sort()
        }
    }

    /**
     * Drop (if necessary) and recreate the health_visit table
     */
    def prepareHealthVisitTable( implicit spark: SparkSession ): Unit = {
        spark.sql( s"USE ${Constant.JdbcDb}")
        spark.sql( Constant.SqlDropTable )
        spark.sql( Constant.SqlDropTableCsv )
    }

    /**
     * Add contents of dataframe to the health_visit table in Teradata
     *
     * @param df  records to add to table
     */
    def appendVisitData(df: DataFrame): Unit = {
        assert(Constant.ColumnNames.length == df.columns.length)

        // Write parquet file to hive
        df.write.mode("append")
                .partitionBy("restaurant_zip")
                .format("parquet")
                .saveAsTable( Constant.JdbcDbTbl )

        // Write "csv" file to hive
        df.write.mode("append")
            .format("com.databricks.spark.csv")
            .saveAsTable( Constant.JdbcDbTblCsv )
    }

    /**
     * Truncate any data that is longer than its column is designed to handle.
     *
     * @param df     dataframe with potential data that is too long
     * @param spark  spark session used to execute queries (implicit)
     * @return       new dataframe with shortened data
     */
    def truncateData( df: DataFrame )( implicit spark: SparkSession): DataFrame =
    {
        assert(Constant.ColumnNames.length == df.columns.length)
        df.createOrReplaceTempView(Constant.TempViewName)
        spark.sql( Constant.SqlReadTable )
             .withColumn( Constant.AddressColumn,
                          regexp_replace(col(Constant.AddressColumn), "[\\r\\n]", "  ")
                        )
    }

    /**
     * Main code entry point for LoadData object
     *
     * @param args command line
     */
    def main(args: Array[String]): Unit = {
        // BUILD A SPARK SESSION
        implicit val spark: SparkSession = createSparkSession
        prepareHealthVisitTable

        for (idx <- Constant.FileMainName.indices) {
            print(Constant.FileMainName(idx))
            val df: DataFrame = loadCsvFileFor(idx)
            if (df != null) {
                print(s" - ${df.count()}")
                val shortened_df: DataFrame = truncateData(df)
                appendVisitData(shortened_df)
            }
            println
        }
    } // main
} // class