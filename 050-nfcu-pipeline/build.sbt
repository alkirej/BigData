name := "100-NFCU-pipeline"
version := "0.1"
scalaVersion := "2.11.8"

libraryDependencies += "org.apache.spark"  %% "spark-core"      % "2.4.4"
libraryDependencies += "org.apache.spark"  %% "spark-sql"       % "2.4.4"
libraryDependencies += "org.apache.spark"  %% "spark-streaming" % "2.4.4"

// Added terajdbc4.jar to classpath.
// Not available as a maven dependency

unmanagedJars in Compile := (file("/home/jeff/git/BigData/100-NFCU-pipeline/jdbc-driver/") ** "*.jar").classpath