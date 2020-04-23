package net.alkire.task31
import java.util.Properties

import org.apache.kafka.common.TopicPartition
import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions.{col, lag}
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Seconds, StreamingContext}


object SparkStreamingTask {
  val CONF = new SparkConf().setAppName( "task31-c-s" )
    .setMaster("local[2]")

  def sqlWindowing(implicit spark: SparkSession): Unit =
  {
    val prop: Properties =new java.util.Properties()
    prop.put("user","jeff")
    val url="jdbc:mysql://localhost/task31"

    val df: DataFrame =spark.read.jdbc(url,"sales",prop)
    df.show()

    val window = Window.partitionBy("year", "month")
      .orderBy("year", "month")

    val result = df.withColumn("lagValue",lag(col("price"),1).over(window))
    result.show()
  }

  def sparkWindowing(): Unit =
  {
    val ssc: StreamingContext = new StreamingContext(CONF, Seconds(3))
    ssc.checkpoint( "~/spark.checkpoint")
    // CREATE A D-STREAM TO KAFKA (aka: K-STREAM
    val topics:  Set[String] = "GlobalWarming,Tornado".split(",").toSet
    val kParams: Map[String,String] = Map[String, String]("bootstrap.servers" -> "jeff-laptop-apt:50001,jeff-laptop-apt:50002",
      "key.deserializer"   -> "org.apache.kafka.common.serialization.StringDeserializer",
      "value.deserializer" -> "org.apache.kafka.common.serialization.StringDeserializer",
      "group.id"           -> "default"
    )

    val strm = KafkaUtils.createDirectStream(
      ssc,
      LocationStrategies.PreferConsistent,
      ConsumerStrategies.Subscribe[String,String](topics,kParams)
    )


    val count1 = strm.count
    val count2 = strm.countByWindow( Seconds(9), Seconds(3) )

    count2.foreachRDD(rdd => rdd.foreachPartition( recs => { recs.foreach( r => print("COUNTS:   full window: " + r + "    ")) } ) )
    count1.foreachRDD(rdd => rdd.foreachPartition( recs => { recs.foreach( r => println("last slice: " + r )) } ) )

    ssc.start
    // strCtx.awaitTermination()
    Thread.sleep(20000)
    ssc.stop(true)

  }

  var _offset: Long = -1;
  def updateOffset( offset: Long ): Long =
  {
    if ( -1 == _offset ) {
      _offset = offset
    }
    _offset
  }
  def getOffset(): Long = updateOffset(0)

  def kStream(): Unit =
  {
    val ssc: StreamingContext = new StreamingContext(CONF, Seconds(3))

    // CREATE A D-STREAM TO KAFKA (aka: K-STREAM
    val topics: Set[String] = "GlobalWarming,Tornado".split(",").toSet
    val kParams: Map[String,String] = Map[String, String]("bootstrap.servers" -> "jeff-laptop-apt:50001,jeff-laptop-apt:50002",
      "key.deserializer"   -> "org.apache.kafka.common.serialization.StringDeserializer",
      "value.deserializer" -> "org.apache.kafka.common.serialization.StringDeserializer",
      "group.id"           -> "default"
    )

    val strm = KafkaUtils.createDirectStream(
      ssc,
      LocationStrategies.PreferConsistent,
      ConsumerStrategies.Subscribe[String,String](topics,kParams)
    )

    strm.foreachRDD(rdd => rdd.foreachPartition( recs => recs.foreach( r => {updateOffset(r.offset); println(r.value + " " + r.offset)} )))

    ssc.start
    println( "wait 15 secs.")
    Thread.sleep(15000)
    // strCtx.awaitTermination()
    ssc.stop(true)
  }

  def dStream(): Unit =
  {
    val ssc: StreamingContext    = new StreamingContext(CONF, Seconds(3))
    val dstream: DStream[String] = ssc.textFileStream( "/home/jeff/git/BigData/031-Omnibus" )

    dstream.foreachRDD(rdd => rdd.foreachPartition( recs => recs.foreach(r => println(r) )))

    ssc.start
    println( "You have 15 seconds to place file into /home/jeff/git/BigData/031-Omnibus to input data into this DStream")
    Thread.sleep(15000)
    // strCtx.awaitTermination()
    ssc.stop(true)
  }

  def offsetManagement(): Unit = {
    val ssc: StreamingContext = new StreamingContext(CONF, Seconds(3))

    // CREATE A D-STREAM TO KAFKA (aka: K-STREAM
    val topics: Set[String] = "GlobalWarming,Tornado".split(",").toSet
    val kParams: Map[String,String] = Map[String, String]("bootstrap.servers" -> "jeff-laptop-apt:50001,jeff-laptop-apt:50002",
      "key.deserializer"   -> "org.apache.kafka.common.serialization.StringDeserializer",
      "value.deserializer" -> "org.apache.kafka.common.serialization.StringDeserializer",
      "group.id"           -> "default"
    )

    val tp: Map[TopicPartition, Long] = Map( new TopicPartition("GlobalWarming", 0) -> getOffset() )
    val strm = KafkaUtils.createDirectStream(
      ssc,
      LocationStrategies.PreferConsistent,
      ConsumerStrategies.Subscribe[String,String](topics,kParams,tp)
    )
    println( "Update offset to " + getOffset() )

    strm.foreachRDD(rdd => rdd.foreachPartition( recs => recs.foreach( r => println(r.value + " " + r.offset) )))

    ssc.start
    println( "Wait 15 secs for more Kafka records.")

    Thread.sleep(15000)
    // strCtx.awaitTermination()
    ssc.stop(true)
  }

  def main(args: Array[String]): Unit = {
    Logger.getLogger("org").setLevel(Level.WARN)
    /*
        implicit val spark: SparkSession = SparkSession.builder()
                .appName("task31-c")
                .master( "local[*]" )
                .getOrCreate()
        sqlWindowing
        spark.stop()

        dStream()
        sparkWindowing()
     */

    kStream()
    offsetManagement()
  }

}
