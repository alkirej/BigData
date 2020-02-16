name := "024-SparkContextStreaming-2"

version := "0.1"

scalaVersion := "2.11.8"

// https://mvnrepository.com/

// KAFKA
libraryDependencies += "org.apache.kafka" % "kafka-clients" % "2.4.0"

// LOGGER
libraryDependencies += "org.slf4j" % "slf4j-log4j12" % "1.7.30"

libraryDependencies += "com.fasterxml.jackson.core" % "jackson-core" % "2.6.7"
//libraryDependencies += "com.fasterxml.databind.jackson.core" % "jackson-core" % "2.6.7"

// SPARK
libraryDependencies += "org.apache.spark" %% "spark-core" % "2.4.3"
libraryDependencies += "org.apache.spark" %% "spark-sql"  % "2.4.3"
libraryDependencies += "org.apache.spark" %% "spark-streaming" % "2.4.3"
libraryDependencies += "org.apache.spark" %% "spark-streaming-kafka-0-10" % "2.4.3"

// SPARK STREAMING - KAFKA
libraryDependencies += "org.apache.spark" %% "spark-streaming-kafka" % "1.6.3"

libraryDependencies += "org.apache.hadoop" % "hadoop-common" % "2.8.5"

