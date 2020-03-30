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
        val fn = buildFileName(city)

        val df = spark.read
                      .format("org.apache.spark.csv")
                      .option("header", "true") //first line in file has headers
                      .option("multiline", "true")
                      .csv(s"file://${fn}")
        // val df = spark.sql(s"SELECT * FROM csv.`file://${fn}`")
        df.show()

        df
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

        loadCsvFileFor( Constant.FnIdxAnchorage )( spark )
    } // main
}
