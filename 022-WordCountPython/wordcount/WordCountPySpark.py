from pyspark import SparkContext
from pyspark.sql import SparkSession, DataFrame
from pyspark.rdd import RDD
import re

# CONSTANTS
INPUT_FILE_NAME: str = "shakespeare-hamlet.txt"

CSV_GENERATION_CLASS: str = "com.databricks.spark.csv"
OUTPUT_DIR_NAME: str = "hdfs://localhost:50501/output/022-wc-hamlet"

def read_and_clean(sc: SparkContext, fileName: str) -> RDD:
    return sc.textFile(INPUT_FILE_NAME) \
                .map(lambda line: line.lower()) \
                .flatMap( lambda l: re.split("\\W+",l) ) \
                .filter(lambda w: w != "")

def open_spark_session(sc:SparkContext) -> SparkSession:
    return SparkSession(sc)   \
        .builder \
        .appName("WordCountPySpark") \
        .master("local[1]") \
        .getOrCreate()

def sum( x: int, y: int ):
    return x+y

def main():
    sc: SparkContext = SparkContext()
    spark: SparkSession = open_spark_session(sc)

    lines: RDD = read_and_clean(sc, INPUT_FILE_NAME)
    results: RDD = lines.map(lambda w: (w, 1)).reduceByKey(lambda x,y: x+y).sortByKey()

    df: DataFrame = spark.createDataFrame(results)
    df.coalesce(1).write.format(CSV_GENERATION_CLASS).save(OUTPUT_DIR_NAME)


if __name__ == "__main__":
    main()