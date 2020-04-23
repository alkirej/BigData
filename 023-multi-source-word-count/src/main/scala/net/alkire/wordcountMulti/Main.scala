package net.alkire.wordcountMulti

import org.apache.spark
import spark.SparkContext
import spark.rdd.RDD
import spark.sql.{DataFrame, Dataset, SaveMode, SparkSession}
import spark.sql.functions.{col, desc}
import org.apache.log4j.{BasicConfigurator, FileAppender, Logger}

import scala.collection.immutable.HashMap

object Main {
    
    val WordColName: String  = "word"
    val CountColName: String = "count"
    
    val InputFileName1: String    = "shakespeare-caesar.txt"
    val InputFileName2: String    = "shakespeare-hamlet.txt"
    val InputFileName3: String    = "bible-kjv.txt"
    
    val OutputFileName1: String   = "caesarTop10.words"
    val OutputFileName2: String   = "hamletTop10.words"
    val OutputFileName3: String   = "kjvTop10.words"
    val OutputFileNameAll: String = "allThreeBooks.words"
    
    val StopWordsFileName: String = "terrier-stop.txt"
    
    val spark: SparkSession = openSparkSession()
    import spark.implicits._

    val StopWords: DataFrame = spark.read.textFile( StopWordsFileName ).toDF(WordColName)
    
    
    
    /**
     * Create a spark session for translating data
     * @return the spark context to allow us to use spark to manipulate our data
     */
    def openSparkSession( ): SparkSession = {
        SparkSession.builder()
                .appName( "WordCountSparkMulti" )
                .master( "local[*]" )
                .getOrCreate()
    }
    
    /**
     * read a file using the SparkContext
     * @param spark     the spark session to use to create the context
     * @param fileName  file to open
     */
    def readWithSparkContext( spark: SparkSession,
                              fileName: String
                            ): Dataset[String] = {
        val sc: SparkContext = spark.sparkContext
        
        val rdd: RDD[ String ] = sc.textFile( fileName )
        spark.createDataset( rdd )
    }
    
    /**
     * Read a file using the SparkSession
     * @param spark     the spark session to use to read the file
     * @param fileName  file to open
     */
    def readWithSparkSession( spark: SparkSession,
                              fileName: String
                            ): DataFrame= {
        spark.read.textFile( fileName ).toDF()
    }
    
    /**
     * Given a dataset of text lines(String), return a dataset of words
     * (String as well) with the data split into words, set in lowercase
     * and with the empty lines removed.
     *
     * @param spark     the spark session to use manipulate the data
     * @return  Dataset[String]  same dataset split by words and with
     *                           the "stop" words removed
     */
    def cleanData( spark: SparkSession, ds: Dataset[String] ) : Dataset[String] = {
        ds.as[(String)].map( line => line.toLowerCase )
                .flatMap( w => w.split( "\\W+" ) )
                .filter( !_.isEmpty )
                .filter( s => s.charAt(0)<'0' || s.charAt(0)>'9' )
    }
    
    /**
     * Given a dataset of Strings, return a dataset of tuples (xxxx, 1)
     * where 1 is a constant in all tuples and xxxx is the value of the
     * String received in the incoming Dataset
     *
     * @param   df     the dataframe to map, sort and recduce
     * @return  Dataset[String]  same data with words counted
     */
    def mapReduce( df: DataFrame ): DataFrame = {
        df.as[(String)].map( w => ( w,1 ) )
                .withColumnRenamed("_1",WordColName )
                .withColumnRenamed( "_2", CountColName )
                .sort(WordColName)
                .groupBy( WordColName )
                .sum(CountColName)
                .withColumnRenamed( "sum("+CountColName+")", CountColName)
    }
    
    /**
     * Remove the stop words from a list of words
     * @param df DataFrame with a list of words (from a book)
     * @return DataFrame - of words from the book (without the Stop Words)
     */
    def removeStopWords( df: DataFrame ) : DataFrame = {
        df.join(StopWords, Seq(WordColName),"left_anti")
    }
    
    /**
     * Sort a DataFrame in reverse order by the number of appearances of each
     * word in the book
     * @param df the list of words and their counts
     * @return  same data, sorted by word count (highest to lowest)
     */
    def sortByCount( df: DataFrame ): DataFrame = {
        df.sort(desc(CountColName))
    }
    
    /**
     * Write a dataframe to a csv file
     * @param df Dataframe to save
     * @param dirName the directory to store the result file(s) in
     */
    def writeToFile( df: DataFrame, dirName: String ): Unit = {
        df.coalesce(1).write.format("com.databricks.spark.csv").save(dirName)
    }
    
    def join( wc1: DataFrame, wc2: DataFrame, wc3: DataFrame ): DataFrame = {
        val tbl1 = wc1.as[(String,Long)].as("a").withColumnRenamed(CountColName,CountColName+"1")
        val tbl2 = wc2.as[(String,Long)].as("b").withColumnRenamed(CountColName,CountColName+"2")
        val tbl3 = wc3.as[(String,Long)].as("c").withColumnRenamed(CountColName,CountColName+"3")
        
        val joinTable:DataFrame = tbl1.join( tbl2, Seq(WordColName), "left" ).join( tbl3, Seq(WordColName), "left" )
        joinTable.sort(WordColName)
    }

    /**
     *
     * @param args
     */
    def main( args: Array[ String ] ): Unit = {
        val log = Logger.getLogger( "org.apache.spark" )
        val fa: FileAppender = new FileAppender()
        fa.setFile("log.out")
        BasicConfigurator.configure( fa )
        
        // Handle file #1
        val ds1: Dataset[String] = readWithSparkContext( spark, InputFileName1 )
        val dfc1 = cleanData( spark, ds1 )
        val dff1: DataFrame = removeStopWords( dfc1.toDF(WordColName) )
        val dfm1 = mapReduce( dff1 )
        val res1: DataFrame = sortByCount( dfm1 )
        writeToFile( res1, OutputFileName1 )
    
        // Handle file #2
        val ds2: Dataset[String] = readWithSparkContext( spark, InputFileName2 )
        val dfc2 = cleanData( spark, ds2 )
        val dff2: DataFrame = removeStopWords( dfc2.toDF(WordColName) )
        val dfm2 = mapReduce( dff2 )
        val res2 = sortByCount( dfm2 )
        writeToFile( res2, OutputFileName2 )
    
        // Handle file #3
        val ds3: Dataset[String] = readWithSparkContext( spark, InputFileName3 )
        val dfc3 = cleanData( spark, ds3 )
        val dff3: DataFrame = removeStopWords( dfc3.toDF(WordColName) )
        val dfm3 = mapReduce( dff3 )
        val res3 = sortByCount( dfm3 )
        writeToFile( res3, OutputFileName3 )
        
        val resAll = join( res1, res2, res3 )
        writeToFile( resAll, OutputFileNameAll )
    }
}
