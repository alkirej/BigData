package net.alkire.streaming

object Constants {
    val ApiKey: String = "---"
    val JoshuaTreeLocation = "34.1347,-116.3131"
    
    val LocalPath: String = "weather-jt-2.json"
    val ApiLocation: String = "https://api.darksky.net/forecast/" +
                    Constants.ApiKey + "/" +
                    Constants.JoshuaTreeLocation
    
    val HdfsLocation: String = "/user/weatherInJt.json"
    
    
    // WARNING: using an ip here causes trouble
    val ConsumerBrokerName: String = "jeff-laptop-apt:50001"
    //val KafkaMachineName = "jeff-laptop"
    
    val KafkaMachineName = "localhost"
    val BrokerPort: Int  = 50001
    val ZookeeperUrl: String = "jeff-laptop-apt:2181"
    
    val KafkaBrokerAddr: String  = KafkaMachineName + ":" + BrokerPort
    val TopicName: String        = "GlobalWarming"
    val CharEncoding: String     = "utf-8"
    val SerializingClass: String = "org.apache.kafka.common.serialization.StringSerializer"
    val DeserializingClass: String = "org.apache.kafka.common.serialization.StringDeserializer"
    
    val ClientId: String            = "myClient"
    val Offset: String              = "earliest"
    val GroupId: String             = "sample"

    val AppName: String             = "spark-streaming-from-kafka"
}


