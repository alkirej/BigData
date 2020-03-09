package net.alkire.task35

object Constants {
    // CREATE SPARK SESSION
    val Master: String        = "local[*]"
    val AppName: String       = "task33"
    
    // CREATE KAFKA STREAM
    val KafkaFormat: String   = "kafka"
    
    val ConfigServers: String   = "kafka.bootstrap.servers"
    val ConfigSubscribe: String = "subscribe"
    
    val ServerList: String    = "cleanos:50001,cleanos:50002"
    val TopicLIst: String     = "GlobalWarming,Tornado"
    

/*
    // WRITE DF
    val WriteOptionPath: String   = "path"
    val WriteOptionCPLoc: String  = "checkpointLocation"
    val WriteOptionOffset: String = "starting.offsets"
    
    val WriteValuePath: String    = "task33-out"
    val WriteValueCPLoc: String   = "checkpoint"
    val WriteValueOffset: String  = "earliest"
    
    val WriteFormat: String       = "csv"
    val WriteQueryName: String    = "task-033"
    
    val TableName: String         = "try2"
    val ColFamName : String       = "name"
    val ColName : String          = "f_and_l_name"
  */
}