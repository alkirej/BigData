package net.alkire.pipeline_nfcu

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.{DataFrame, SparkSession}

/** Load restaurant violation data from .csv files. */
object LoadData {
    /**
     * Returns a newly build SparkSession object.
     *
     * @return SparkSession object
     */
    def createSparkSession: SparkSession =
    {
        SparkSession.builder
            .master(Constant.Master)
            .appName(Constant.AppName)
            .getOrCreate()
    }

    /**
     * Returns the name of the .csv file for the given city
     *
     * @param  city's id (see Constant class)
     * @return full path to data file
     */
    def buildFileName( city: Int ): String =
    {
        val returnVal: StringBuilder = new StringBuilder()
        returnVal.append( Constant.DataDir )
        returnVal.append( "/" )
        returnVal.append( Constant.FilePrefix )
        returnVal.append( Constant.FileMainName(city) )
        returnVal.append( Constant.FileSuffix )

        returnVal.toString
    }

    /**
     * Load the data file for the given city.
     * @param  city  city whose data will be loaded
     * @param  spark session information to connect to filesystem (implicit)
     * @return contents of datafile
     */
    def loadCsvFileFor( city: Int )( implicit spark: SparkSession ): DataFrame =
    {
        assert( city>=0 )
        assert( city<Constant.FileMainName.length )

        val fn = buildFileName(city)

        val df = spark.read
                      // .format("org.apache.spark.csv" )
                      .option("header", "true") //first line in file has headers
                      .option("multiline", "true")
                      .csv(s"file://${fn}")
        df.createOrReplaceTempView( Constant.TempViewName )

        if ( "" == Constant.SqlByCity(city))
        {
            println
            print( df.printSchema )
            null
        } else {
            spark.sqlContext.sql(Constant.SqlByCity(city)).sort()
        }
    }

    def appendVisitData( df: DataFrame ): Unit =
    {
        assert( Constant.ColumnNames.length == df.columns.length )

        df.write.mode("append" ).jdbc( Constant.JdbcUrl, Constant.JdbcDbTbl, Constant.ConnProps )
    }

    /**
     * Main code entry point for LoadData object
     *
     * @param args command line
     */
    def main(args: Array[String]): Unit =
    {
        // Crank logging down to a dull roar.
        Logger.getLogger("org").setLevel(Level.ERROR)

        // BUILD A SPARK SESSION
        implicit val spark: SparkSession = createSparkSession

        for ( idx <- 0 to Constant.FileMainName.length-1 )
        {
            print( Constant.FileMainName(idx) )
            val df: DataFrame = loadCsvFileFor( idx )
            if ( df != null )
            {
                print( " - " )
                println( df.count() )
                df.sample( 0.01d ).show(5)

                appendVisitData( df )
            }
            println
        }

        Class.forName( Constant.JdbcDriverClass )
        println( " ======================== " )
        println( " === NEW READ ATTEMPT ===" )
        println( " ======================== " )
        val df = spark.sqlContext.read.jdbc( Constant.JdbcUrl, Constant.JdbcDbTbl, Constant.ConnProps )
        df.show()
        println( " ======================== " )
    } // main
}
