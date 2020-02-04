package net.alkire.kafka

object Constants {
    val ClientId: String            = "myClient"
    val BrokerName: String          = "hadoop:50001"
    val Offset: String              = "earliest"
    val SerializingClass: String    = "org.apache.kafka.common.serialization.StringDeserializer"
    val GroupId: String             = "sample"
    val TopicName: String           = "Tornados"
}
