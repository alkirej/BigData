"""
Author: Jeff Alkire
Date:   Feb 29, 2020
Description:  BigData bootcamp task 34
"""

import os
from pyspark.sql import SparkSession, SQLContext, DataFrame, Column
from pyspark.sql.types import StructType, StructField, StringType

from Task34Constants import Task34Constants

# PRINT ROT FUNCTION
def pr(x): print(x)

os.environ["PYSPARK_SUBMIT_ARGS"] = "--packages com.datastax.spark:spark-cassandra-connector_2.11:2.4.2 --conf spark.cassandra.connection.host=127.0.0.1 pyspark-shell"

# SETUP SPARK SESSION
spark:SparkSession = SparkSession.builder \
                            .master( "local[*]" ) \
                            .appName( "Cassandra-data-task-34" ) \
                            .getOrCreate()

# LOAD CSV DATA, STORE IN DB AND PRINT ON SCREEN

csv_df = spark.read.format("csv").option("header", "false").load(Task34Constants.CSV_FILE_URL)
csv_df.registerTempTable('csv_temp_table')

named_df = spark.sql( "select uuid() as csv_id, _c0 as country_id,\
                                                _c1 as country_name,\
                                                _c2 as year,\
                                                _c3 as count_of,\
                                                _c4 as count,\
                                                _c5 as notes,\
                                                _c6 as source\
                                        from csv_temp_table"\
                     )

named_df.write\
    .format("org.apache.spark.sql.cassandra")\
    .mode( "append" )\
    .options( table="csv_data", keyspace=Task34Constants.KEYSPACE )\
    .save()

print ( "start\n" )
# LOAD JSON DATA, STORE IN DB AND PRINT ON SCREEN
schema = StructType([
                StructField( "fruit", StringType() ),
                StructField( "size",  StringType() ),
                StructField( "color", StringType() )
         ]
         )
json_df = spark.read.schema(schema).format("json").load(Task34Constants.JSON_FILE_URL)
json_df.registerTempTable('json_temp_table')

js_named_df = spark.sql( "select uuid() as json_id, fruit, size, color\
                                           from json_temp_table"\
                       )
js_named_df.write\
    .format("org.apache.spark.sql.cassandra")\
    .mode( "append" )\
    .options( table="json_data", keyspace=Task34Constants.KEYSPACE )\
    .save()


df: DataFrame = spark.read\
                .format("org.apache.spark.sql.cassandra")\
                .options(table="csv_data", keyspace=Task34Constants.KEYSPACE)\
                .load()
df.show()

df = spark.read\
                .format("org.apache.spark.sql.cassandra")\
                .options(table="json_data", keyspace=Task34Constants.KEYSPACE)\
                .load()
df.show()
