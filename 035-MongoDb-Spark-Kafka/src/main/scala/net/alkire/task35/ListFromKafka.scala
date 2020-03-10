package net.alkire.task35

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.bson.BsonDocument
import org.bson.conversions.Bson

import scala.collection.mutable.ListBuffer

object ListFromKafka
{
    val _allNames = new ListBuffer[BsonDocument]
    
    def append( name: String ): Unit = {
        val newDoc = BsonDocument.parse( s"""{ "name": "${name}" }""" )
        _allNames.append( newDoc )
        println( _allNames )
    }
    
    def asRdd( implicit sc: SparkContext): RDD[BsonDocument] = {
        sc.parallelize( _allNames )
    }
}
