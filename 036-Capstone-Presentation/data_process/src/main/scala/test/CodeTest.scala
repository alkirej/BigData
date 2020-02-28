package test

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions.{col,expr}

case class Record( birth_date: String, first_name: String, last_name: String, email: String, gender: String )

object CodeTest
{
    def clean( allData: String ): String =
    {
        val idx: Int   = allData.indexOf( "\n" )
        val idx_2: Int = allData.substring(idx+1).indexOf("\n")
        allData.substring( idx_2+2 )
    }
    
    def splitLine( line: String ): Record =
    {
        println( "line = " + line )
        val splits: Array[String] = line.split("\t")
//        val birthDt: Int          = splits(0).asInstanceOf[Int]
        
        if ( splits.length == 5 )
        {
            Record( splits(0), splits(1), splits(2), splits(3), splits(4) )
        } else
        {
            Record( "", "", "", "", "" )
        }
    }
    
    def main(args: Array[String]): Unit =
    {
        Logger.getLogger("org").setLevel(Level.WARN)
        
        val data: String =
            """
birth_date	first_name	last_name	email	gender
2000	Jo-ann	Grenville	jgrenville0@berkeley.edu	Female
2001	Ivie	Barnewall	ibarnewall1@rambler.ru	Female
1997	Martha	Galbraith	mgalbraith2@nps.gov	Female
2008	Gabriel	Rennolds	grennolds3@adobe.com	Male
2012	Erwin	Haylett	ehaylett4@soup.io	Male
1993	Paulina	Hackwell	phackwell5@census.gov	Female
2012	Mervin	Von Welden	mvonwelden6@goo.gl	Male
1993	Farand	Dowderswell	fdowderswell7@furl.net	Female
2001	Gabriello	Brooking	gbrooking8@earthlink.net	Male
2012	Sindee	Egentan	segentan9@privacy.gov.au	Female
2000	Cathleen	Daughtrey	cdaughtreya@tripadvisor.com	Female
1994	Jo	Morson	jmorsonb@nhs.uk	Female
2007	Bar	Yesipov	byesipovc@behance.net	Male
1993	Melba	Killimister	mkillimisterd@stanford.edu	Female
2004	Alley	Benitti	abenittie@yelp.com	Male
1992	Katti	Bill	kbillf@cnbc.com	Female
1998	Orton	Peacey	opeaceyg@comcast.net	Male
        """
    
        // create a spark session
        val spark: SparkSession = SparkSession.builder()
            .master("local[*]")
            .appName("code_test")
            .getOrCreate()
    
        val sc: SparkContext = spark.sparkContext
        
        // read csv data string below
        val cleanData = clean( data )
        println( "=========")
        println( cleanData  )
        println( "=========")
        
        val dataRdd: RDD[String]  = sc.parallelize( cleanData.split( "\n" ) )
        val splitRdd: RDD[Record] = dataRdd.map( line => splitLine( line ) )
    
        val df: DataFrame = spark.createDataFrame( splitRdd )
                                 .withColumn("memberId",
                                              expr( "concat(first_name,' ', last_name, ' ', birth_date)")
                                 )
        df.show()
    }
    
}
