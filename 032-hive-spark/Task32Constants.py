"""
Author: Jeff Alkire
Date:   02-24-20
Notes:  Store constants for Task 32 project. Read a csv and json file from the disk and store
        it to a hive table.
"""
class Task32Constants:
    CSV_FILE_NAME  = "migrantsRefugees.csv"
    JSON_FILE_NAME = "censusChapelHillNC.json"

    # MACHINE_DEPENDANT = ""
    MACHINE_DEPENDANT = "main/"

    CSV_FILE_URL  = "file:///home/jeff/" + MACHINE_DEPENDANT + "git/BigData/032-Hive-Spark/" + CSV_FILE_NAME
    JSON_FILE_URL = "file:///home/jeff/" + MACHINE_DEPENDANT + "git/BigData/032-Hive-Spark/" + JSON_FILE_NAME