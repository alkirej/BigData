name := "create_db"

version := "0.1"

scalaVersion := "2.11.8"
val HiveVer: String    = "2.3.6"
val SparkVer: String   = "2.4.4"

val HivePackage: String = "org.apache.hive"
val SparkPackage: String = "org.apache.spark"

libraryDependencies += SparkPackage %% "spark-core" % SparkVer
libraryDependencies += SparkPackage %% "spark-sql"  % SparkVer
libraryDependencies += SparkPackage %% "spark-hive" % SparkVer

