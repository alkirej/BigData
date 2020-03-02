name := "ProcessData"
version := "0.1"
scalaVersion := "2.11.8"

val HiveVer: String    = "2.3.6"
val JacksonVer: String = "2.9.8"
val SparkVer: String   = "2.4.4"
val HadoopVer: String  = "2.8.5"

val HivePackage: String = "org.apache.hive"
val JacksonCorePackage: String = "com.fasterxml.jackson.core"
val JacksonModulePackage: String = "com.fasterxml.jackson.module"
val SparkPackage: String = "org.apache.spark"
val HadoopPackage: String = "org.apache.hadoop"

//libraryDependencies += HivePackage % "hive-common" % HiveVer
//libraryDependencies += HivePackage % "hive-exec"   % HiveVer
//libraryDependencies += HivePackage   % "hive-metastore" % HiveVer

libraryDependencies += JacksonCorePackage   % "jackson-databind"      % JacksonVer
libraryDependencies += JacksonCorePackage   % "jackson-core"          % JacksonVer
libraryDependencies += JacksonCorePackage   %  "jackson-annotations"  % JacksonVer
libraryDependencies += JacksonModulePackage %% "jackson-module-scala" % JacksonVer


libraryDependencies += SparkPackage %% "spark-core" % SparkVer
libraryDependencies += SparkPackage %% "spark-sql"  % SparkVer
libraryDependencies += SparkPackage %% "spark-hive" % SparkVer

libraryDependencies += HadoopPackage % "hadoop-common" % HadoopVer
libraryDependencies += HadoopPackage % "hadoop-hdfs"   % HadoopVer
libraryDependencies += HadoopPackage % "hadoop-client" % HadoopVer

libraryDependencies += "com.databricks" %% "spark-xml" % "0.9.0"
