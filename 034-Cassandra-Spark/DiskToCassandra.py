"""
Author: Jeff Alkire
Date:   Feb 29, 2020
Description:  BigData bootcamp task 34
"""

from pyspark.sql     import SparkSession, SQLContext, DataFrame
from pyspark         import SparkContext, RDD
from Task34Constants import Task34Constants

# PRINT ROT FUNCTION
def pr(x): print(x)

import os
os.environ['PYSPARK_SUBMIT_ARGS'] = '--packages com.datastax.spark:spark-cassandra-connector_2.11:2.4.2 --conf spark.cassandra.connection.host=127.0.0.1 pyspark-shell'

# SETUP SPARK SESSION
spark:SparkSession = SparkSession.builder \
                            .master( "local[*]" ) \
                            .appName( "Cassandra-data-task-34" ) \
                            .getOrCreate()

df: DataFrame = spark.read\
                .format("org.apache.spark.sql.cassandra")\
                .options(table="csv_data", keyspace=Task34Constants.KEYSPACE)\
                .load()
df.show()

# LOAD CSV DATA, STORE IN DB AND PRINT ON SCREEN
csv_df = spark.read.format("csv").option("header", "false").load(Task34Constants.CSV_FILE_URL)
csv_df.write\
    .format("org.apache.spark.sql.cassandra")\
    .mode( "append" )\
    .options( table="csv_data", keyspace=Task34Constants.KEYSPACE )\
    .save()
csv_df.foreach(pr)

# LOAD JSON DATA, STORE IN DB AND PRINT ON SCREEN
#json_df = spark.read.format("json").load(Task34Constants.JSON_FILE_URL)
#json_df.write.mode("overwrite").insertInto("json_data")
#json_df.foreach(pr)



df: DataFrame = spark.read\
                .format("org.apache.spark.sql.cassandra")\
                .options(table="csv_data", keyspace=Task34Constants.KEYSPACE)\
                .load()
df.show()
