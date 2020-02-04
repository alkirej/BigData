package net.alkire.kafka

import kafka.consumer.{ConsumerConfig}

object Constants {
    // WARNING: using an ip here causes trouble
    val KafkaMachineName = "hadoop"
    val BrokerPort       = 50001
    val ClientId         = "DisplayConsumer"
    val Partition        = 0

    val KafkaBrokerAddr = KafkaMachineName + ":" + BrokerPort
    //val TopicName       = "GlobalWarming"
    val TopicName       = "Tornados"
    val CharEncoding    = "utf-8"

}
