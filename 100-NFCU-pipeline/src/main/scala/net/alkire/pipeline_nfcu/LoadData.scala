package net.alkire.pipeline_nfcu
import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession

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
    } // main
}
