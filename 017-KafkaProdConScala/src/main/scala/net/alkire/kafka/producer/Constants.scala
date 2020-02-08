package net.alkire.kafka.producer


object Constants {
  // WARNING: using an ip here causes trouble
  val KafkaMachineName = "jeff-laptop-2"
  val BrokerPort: Int  = 50001
  val ClientId: String = "DisplayConsumer"
  val Partition: Int   = 0
  val Offset: String   = "earliest"

  val KafkaBrokerAddr: String  = KafkaMachineName + ":" + BrokerPort
  val TopicName: String        = "GlobalWarming"
  val CharEncoding: String     = "utf-8"
  val SerializingClass: String = "org.apache.kafka.common.serialization.StringSerializer"
}

