"""
Author: Jeff Alkire
Date:   Feb 24, 2020
Description:  BigData bootcamp task 32
Notes:        1-I used python v2 to complete this assignment as hive install set python 2 to the default python
                  overriding my choice of python 3.
              2-Hive is particular regarding its connection to Derbyh.  The network servers must be running to execute
                  hive shell from the terminal, but generally can't be running to connect from pyspark code.
              3-If CLASSPATH is not empty when starting Derby, sometimes you will encounter cannot connect to
                  metastore errors from hive
"""

from pyspark.sql     import SparkSession
from Task32Constants import Task32Constants

"""
CREATE_TABLE_SQL_QUERY =\
    "CREATE TABLE json_data\
        ( amerind_nh    DOUBLE,\
          asian_nh      DOUBLE,\
          black_nh      DOUBLE,\
          divindx_cy    DOUBLE,\
          geo_point_2d  STRUCT<\
              lat:      DOUBLE,\
              lon:      DOUBLE,\
          >,\
          geo_shape STRUCT<\
              geometry: STRUCT<\
                  coordinates: ARRAY<STRUCT< x: DOUBLE, y: DOUBLE>>,\
                  type:        STRING\
              >,\
              type:  STRING\
          >,\
          hisppop_cy    DOUBLE,\
          id            INT,\
          landarea      DOUBLE,\
          name          STRING,\
          othrace_nh    DOUBLE,\
          pacific_nh    DOUBLE,\
          st_abbrev     CHAR(2),\
          totpop10      DOUBLE,\
          totpop_cy     DOUBLE,\
          white_nh      DOUBLE\
        )\
    STORED AS PARQUET\
    "
"""

# PRING ROW FUNCTION
def pr(x): print(x)

# SETUP SPARK SESSION
spark = SparkSession.builder \
                    .master( "local[*]" ) \
                    .appName( "task32" ) \
                    .enableHiveSupport() \
                    .getOrCreate()

"""  DEBUGGING CODE - SEE WHAT DATABASES ARE VISIBLE AND WHAT TABLES ARE IN THE DB.
print( "----- DATABASE LIST -----")
bob = spark.sql( "SHOW DATABASES")
bob.foreach(pr)
print( "-------------------------")

spark.sql( "USE task32" )

print( "------ TABLE  LIST ------")
table_list = spark.sql( "SHOW TABLES")
print( "-------------------------")
"""

# CONNECT TO DB
spark.sql( "USE task32" )

# LOAD CSV DATA, STORE IN DB AND PRINT ON SCREEN
csv_df = spark.read.format("csv").option("header", "false").load(Task32Constants.CSV_FILE_URL)
csv_df.write.mode("overwrite").insertInto("csv_data")
csv_df.foreach(pr)

# LOAD JSON DATA, STORE IN DB AND PRINT ON SCREEN
json_df = spark.read.format("json").load(Task32Constants.JSON_FILE_URL)
json_df.write.mode("overwrite").insertInto("json_data")
json_df.foreach(pr)

