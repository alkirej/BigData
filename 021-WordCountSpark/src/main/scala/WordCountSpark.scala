package net.alkire.wordcount

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, SparkSession}


object WordCountSpark {
  /**
   * @return  the spark context to allow us to use spark to manipulate our data
   */
  def openSparkSession(): SparkSession = {
    SparkSession.builder()
      .appName("WordCountSpark")
      .master( "local[*]")
      .getOrCreate()
  }

  /**
   * return a list of words from the file
   * @params:
   *       sc          the SparkContext object used for data manuplation
   *       fileName    name of the file to read from disk (full path suggested)
   * @return:
   *       list    A list of strings representing the words for the file.
   *               The list will be only words and converted to lower case
   */
  def readAndClean( sc: SparkContext, fileName: String ): RDD[String] ={
    sc.textFile( Constants.InputFileName )
      .map( line => line.toLowerCase )
      .flatMap( w => w.split("\\W+") )
      .filter( !_.isEmpty )
  }

  /**
   * Read in a text file and count the number of each word in the document
   *
   * @param args  command line
   */
  def main(args: Array[String]): Unit = {
    val spark: SparkSession = openSparkSession
    val sc: SparkContext    = spark.sparkContext

    val lines: RDD[String] = readAndClean( sc, Constants.InputFileName )

    // map, shuffle, reduce
    val results: RDD[(String,Int)] = lines.map( w => (w,1) ).reduceByKey( _+_ ).sortByKey()

    val df: DataFrame = spark.createDataFrame( results ).coalesce(1)
    df.write.format(Constants.CsvGenerationClass).save(Constants.OutputDirName)
  }

}
