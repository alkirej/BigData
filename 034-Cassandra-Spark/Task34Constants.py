"""
Author: Jeff Alkire
Date:   02-24-20
Notes:  Store constants for Task 32 project. Read a csv and json file from the disk and store
        it to a hive table.
"""
class Task34Constants:
    CSV_FILE_NAME  = "migrantsRefugees.csv"
    JSON_FILE_NAME = "simpleSample.json"

    # MACHINE_DEPENDANT = ""
    MACHINE_DEPENDANT = "main/"

    CSV_FILE_URL  = "file:///home/jeff/" + MACHINE_DEPENDANT + "git/BigData/034-Casandra-Spark/" + CSV_FILE_NAME
    JSON_FILE_URL = "file:///home/jeff/" + MACHINE_DEPENDANT + "git/BigData/034-Casandra-Spark/" + JSON_FILE_NAME

    # CFG_KEY_CASSANDRA_HOST = "spark.cassandra.connection.host"
    # CASSANDRA_HOST = "localhost"
    KEYSPACE       = "task34"
    TABLE_NAME     = "test"