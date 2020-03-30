package net.alkire.pipeline_nfcu
import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.{SparkSession, DataFrame}

/** Load restaurant violation data from .csv files. */
object LoadData {
    /**
     * Returns a newly build SparkSession object.
     *
     * @return SparkSession object
     */
    def createSparkSession: SparkSession = {
        SparkSession.builder
            .master(Constant.Master)
            .appName(Constant.AppName)
            .getOrCreate()
    }
    def buildFileName( city: Int ): String = {
        val returnVal: StringBuilder = new StringBuilder()
        returnVal.append( Constant.DataDir )
        returnVal.append( "/" )
        returnVal.append( Constant.FilePrefix )
        returnVal.append( Constant.FileMainName(city) )
        returnVal.append( Constant.FileSuffix )

        returnVal.toString
    }

    def loadCsvFileFor( city: Int )(implicit spark: SparkSession): DataFrame = {
        assert( city>=0)
        assert( city<Constant.FileMainName.length
        )
        val fn = buildFileName(city)

        val df = spark.read
                      .format("org.apache.spark.csv")
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

    /**
     * Main code entry point for LoadData object
     *
     * @param args command line
     */
    def main(args: Array[String]): Unit = {
        // Crank logging down to a dull roar.
        Logger.getLogger("org").setLevel(Level.ERROR)

        // BUILD A SPARK SESSION
        val spark: SparkSession = createSparkSession

        for ( idx <- 0 to Constant.FileMainName.length-1 )
        {
            print( Constant.FileMainName(idx) )
            val df: DataFrame = loadCsvFileFor( idx )( spark )
            if ( df != null ) {
                print( " - " )
                println( df.count() )
                df.sample( 0.01d ).show(5)
            }
            println
        }
    } // main
}
