name := "create_db"
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


/*
resolvers += Resolver.mavenLocal
resolvers += "Cascading repo" at "http://conjars.org/repo"

libraryDependencies += HivePackage % "hive-exec"         % HiveVer excludeAll ExclusionRule(organization = "org.mortbay.jetty")  excludeAll ExclusionRule(organization = "javax.servlet")
libraryDependencies += HivePackage % "hive-common"       % HiveVer excludeAll ExclusionRule(organization = "org.mortbay.jetty")  excludeAll ExclusionRule(organization = "javax.servlet")
libraryDependencies += HivePackage % "hive-shims"        % HiveVer excludeAll ExclusionRule(organization = "org.mortbay.jetty")  excludeAll ExclusionRule(organization = "javax.servlet")
libraryDependencies += "org.apache.hive.shims" % "hive-shims-common" % HiveVer excludeAll ExclusionRule(organization = "org.mortbay.jetty")  excludeAll ExclusionRule(organization = "javax.servlet")
libraryDependencies += HivePackage % "hive-metastore"    % HiveVer excludeAll ExclusionRule(organization = "org.mortbay.jetty")  excludeAll ExclusionRule(organization = "javax.servlet")
*/
/*
libraryDependencies += JacksonCorePackage   % "jackson-databind"      % JacksonVer
libraryDependencies += JacksonCorePackage   % "jackson-core"          % JacksonVer
libraryDependencies += JacksonCorePackage   %  "jackson-annotations"  % JacksonVer
libraryDependencies += JacksonModulePackage %% "jackson-module-scala" % JacksonVer
*/
// https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-core
libraryDependencies += "org.apache.logging.log4j" % "log4j-core" % "2.12.1"


libraryDependencies += SparkPackage %% "spark-core" % SparkVer
libraryDependencies += SparkPackage %% "spark-sql"  % SparkVer
libraryDependencies += SparkPackage %% "spark-hive" % SparkVer

libraryDependencies += HadoopPackage % "hadoop-common" % HadoopVer excludeAll ExclusionRule(organization = "org.mortbay.jetty")  excludeAll ExclusionRule(organization = "javax.servlet")
libraryDependencies += HadoopPackage % "hadoop-hdfs"   % HadoopVer excludeAll ExclusionRule(organization = "org.mortbay.jetty")  excludeAll ExclusionRule(organization = "javax.servlet")
libraryDependencies += HadoopPackage % "hadoop-client" % HadoopVer excludeAll ExclusionRule(organization = "org.mortbay.jetty")  excludeAll ExclusionRule(organization = "javax.servlet")
/*
libraryDependencies += HadoopPackage % "hadoop-annotations" % HadoopVer excludeAll ExclusionRule(organization = "org.mortbay.jetty")  excludeAll ExclusionRule(organization = "javax.servlet")
*/
/*
libraryDependencies += "org.pentaho" % "pentaho-aggdesigner-algorithm" % "5.1.5-jhyde" % Test
*/