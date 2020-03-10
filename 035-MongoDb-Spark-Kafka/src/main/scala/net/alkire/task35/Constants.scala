package net.alkire.task35

object Constants {
    // CREATE SPARK SESSION
    val Master:  String = "local[*]"
    val AppName: String = "task35"
    
    // CREATE KAFKA STREAM
    val KafkaFormat: String = "kafka"
    
    val ConfigServers:     String = "kafka.bootstrap.servers"
    val ConfigSubscribe:   String = "subscribe"
    val ConfigInputDbUrl:  String = "spark.mongodb.input.uri"
    val ConfigOutputDbUrl: String = "spark.mongodb.output.uri"
    
    val ServerList: String = "cleanos:50001,cleanos:50002"
    val TopicLIst:  String = "GlobalWarming,Tornado"
    
}