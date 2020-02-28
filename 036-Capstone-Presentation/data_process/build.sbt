name := "ProcessData"
version := "0.1"
scalaVersion := "2.11.8"

libraryDependencies += "com.fasterxml.jackson.core"   %% "jackson-databind"     % "2.9.8"
libraryDependencies += "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.9.8"
libraryDependencies += "com.fasterxml.jackson.core"   %% "jackson-core"         % "2.9.8"
libraryDependencies += "com.fasterxml.jackson.core"   %% "jackson-annotations"  % "2.9.8"

libraryDependencies += "org.apache.spark"  %% "spark-core" % "2.4.4"
libraryDependencies += "org.apache.spark"  %% "spark-sql"  % "2.4.4"

libraryDependencies += "org.apache.hadoop" % "hadoop-common" % "3.2.1"
libraryDependencies += "org.apache.hadoop" % "hadoop-hdfs"   % "3.2.1"
libraryDependencies += "org.apache.hadoop" % "hadoop-client" % "3.2.1"

