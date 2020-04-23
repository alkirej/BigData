package net.alkire.task31a

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext, sql}
import org.apache.spark.sql.{DataFrame, SparkSession}

object Task31a extends Serializable {
    def main(args: Array[String]): Unit = {
        Logger.getLogger("org").setLevel(Level.OFF)
        val CONF = new SparkConf().setAppName( "task31-c-s" )
                                  .setMaster("local[2]")
         
        val spark = SparkSession
                        .builder()
                        .appName("Task-31-a-bcast")
                        .master("local[*]")
                        .getOrCreate()
        val sc: SparkContext = spark.sparkContext
        
        val accum = sc.accumulator(0)
        sc.parallelize(Array(1, 2, 3, 4)).foreach(x => accum += x)
        
        println( accum.value )
    }
}
