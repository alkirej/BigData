name := "035-MongoDb-Spark-Kafka"
version := "0.1"
scalaVersion := "2.11.8"

libraryDependencies += "org.apache.spark"  %% "spark-core"      % "2.4.4"
libraryDependencies += "org.apache.spark"  %% "spark-sql"       % "2.4.4"
libraryDependencies += "org.apache.spark"  %% "spark-streaming" % "2.4.4"

libraryDependencies += "org.apache.spark" %% "spark-streaming-kafka" % "1.6.2"
libraryDependencies += "org.apache.spark" %% "spark-sql-kafka-0-10"  % "2.4.4"

libraryDependencies += "org.mongodb.spark" %% "mongo-spark-connector" % "2.4.1"
libraryDependencies += "org.mongodb" % "mongodb-driver" % "3.8.2"
