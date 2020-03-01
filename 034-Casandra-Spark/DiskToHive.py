"""
Author: Jeff Alkire
Date:   Feb 29, 2020
Description:  BigData bootcamp task 34
"""

from pyspark.sql     import SparkSession
from Task34Constants import Task34Constants


# PRINT ROT FUNCTION
def pr(x): print(x)

# SETUP SPARK SESSION
spark = SparkSession.builder \
                    .master( "local[*]" ) \
                    .appName( "task34" ) \
                    .enableHiveSupport() \
                    .getOrCreate()

# CONNECT TO DB
spark.sql( "USE task34" )

# LOAD CSV DATA, STORE IN DB AND PRINT ON SCREEN
csv_df = spark.read.format("csv").option("header", "false").load(Task34Constants.CSV_FILE_URL)
csv_df.write.mode("overwrite").insertInto("csv_data")
csv_df.foreach(pr)

# LOAD JSON DATA, STORE IN DB AND PRINT ON SCREEN
json_df = spark.read.format("json").load(Task34Constants.JSON_FILE_URL)
json_df.write.mode("overwrite").insertInto("json_data")
json_df.foreach(pr)

