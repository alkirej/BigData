name := "1-Spark"
version := "0.1"
scalaVersion := "2.11.8"

// KAFKA
libraryDependencies += "org.apache.kafka" % "kafka-clients" % "2.4.0"

// LOGGER
libraryDependencies += "org.slf4j" % "slf4j-log4j12" % "1.7.30"

// SPARK
libraryDependencies += "org.apache.spark" %% "spark-core" % "2.4.4"
libraryDependencies += "org.apache.spark" %% "spark-sql"  % "2.4.4"
libraryDependencies += "org.apache.spark" %% "spark-streaming" % "2.4.4"
libraryDependencies += "org.apache.spark" %% "spark-streaming-kafka-0-10" % "2.4.4"
