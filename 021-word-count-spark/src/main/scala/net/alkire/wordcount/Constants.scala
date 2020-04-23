package net.alkire.wordcount

object Constants {
  val InputFileName: String = "/home/jeff/BigData/004-WordCount/shakespeare-hamlet.txt"

  val OutputDirName: String  = "hdfs://localhost:50700/output/WordCountSpark/"

  val CsvHeaderLine: String  = "word,count"

  val CsvGenerationClass: String = "com.databricks.spark.csv"
}
