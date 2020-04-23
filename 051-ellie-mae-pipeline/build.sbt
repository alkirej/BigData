name := "101-Ellie-Mae-pipeline"
version := "0.1"
scalaVersion := "2.11.8"

libraryDependencies += "org.apache.spark"  %% "spark-core"      % "2.4.4"
libraryDependencies += "org.apache.spark"  %% "spark-sql"       % "2.4.4"
libraryDependencies += "org.apache.spark"  %% "spark-streaming" % "2.4.4"
libraryDependencies += "org.apache.spark"  %% "spark-hive"      % "2.4.4"

unmanagedJars in Compile := (file("/opt/hive/lib/") ** "mysql-*.jar").classpath
