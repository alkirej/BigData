package net.alkire.wordcountMulti

// import org.apache.spark.
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}
import org.apache.spark.sql.functions.desc
object Main {
    val WordColName: String  = "word"
    val CountColName: String = "cnt"
    
    val InputFileName1: String = "shakespeare-caesar.txt"
    val InputFileName2: String = "shakespeare-hamlet.txt"
    val InputFileName3: String = "bible-kjv.txt"
    
    val spark: SparkSession = openSparkSession()
    import spark.implicits._
    
    /**
     * @return the spark context to allow us to use spark to manipulate our data
     */
    def openSparkSession( ): SparkSession = {
        SparkSession.builder()
                .appName( "WordCountSparkMulti" )
                .master( "local[*]" )
                .getOrCreate()
    }
    
    
    def readWithSparkContext( spark: SparkSession,
                              fileName: String
                            ): DataFrame = {
        val sc: SparkContext = spark.sparkContext
        
        val rdd: RDD[ String ] = sc.textFile( fileName )
        spark.createDataset( rdd ).toDF()
    }
    
    def readWithSparkSession( spark: SparkSession,
                              fileName: String
                            ): DataFrame= {
        spark.read.textFile( fileName ).toDF()
    }
    
    def cleanData( spark: SparkSession, df: DataFrame ) : DataFrame = {
        df.as[(String)].map( line => line.toLowerCase )
                .flatMap( w => w.split( "\\W+" ) )
                .filter( !_.isEmpty )
                .toDF()
    }
    
    def mapReduce( df: DataFrame ): DataFrame = {
        df.as[(String)].map( w => ( w,1 ) )
                .withColumnRenamed("_1",WordColName )
                .withColumnRenamed( "_2", CountColName )
                .sort(WordColName)
                .groupBy( WordColName )
                .sum(CountColName)
                .withColumnRenamed( "sum("+CountColName+")", CountColName)
    }
    
    def sortByCount( df: DataFrame ): DataFrame = {
        df.sort(desc(CountColName))
        
    }
    
    def main( args: Array[ String ] ): Unit = {
        
        val df1: DataFrame = readWithSparkContext( spark, InputFileName1 )
        val dfc1 = cleanData( spark, df1 )
        val dfm1 = mapReduce( dfc1 )
        val res1 = sortByCount( dfm1 )
    
        val df2: DataFrame = readWithSparkContext( spark, InputFileName2 )
        val dfc2 = cleanData( spark, df2 )
        val dfm2 = mapReduce( dfc1 )
        val res2 = sortByCount( dfm2 )
    
        val df3: DataFrame = readWithSparkContext( spark, InputFileName3 )
        val dfc3 = cleanData( spark, df3 )
        val dfm3 = mapReduce( dfc3 )
        val res3 = sortByCount( dfm3 )
        
        res1.show(10)
        print("\n\n\n\n\n")
        res2.show(10)
        print("\n\n\n\n\n")
        res3.show(10)
    
    }
}
