import os
import re
import textract
from pyspark import SparkContext
from pyspark.sql import SparkSession, DataFrame
from pyspark.rdd import RDD

def open_spark_session(sc:SparkContext) -> SparkSession:
    return SparkSession(sc)   \
        .builder \
        .appName("ProfileExaminer") \
        .master("local[*]") \
        .getOrCreate()

def read_from_files() -> str:
    text = ""

    # DIRECTORY TO STARTFROM
    START_DIR = '/home/jeff/git/BigData/026-ProfilesPySpark/profiles/'

    # READ EACH OF THE PROFILE DOCUMENTS AND ADD TO TEXT VAR
    for dir, subdirs, files in os.walk(START_DIR):
        print('Found directory: %s' % dir)
        for fname in files:
            full_path = START_DIR + fname
            try:
                text += str(textract.process(full_path))
            except:
                print("Exception on " + fname)
    return text

def unique_lines( text: str ) -> list[str]:
    # SPLIT INTO LINES
    lines = text.replace("\\" + "n", '\n')
    line_list = re.split('\n', lines)

    # USE SET TO REMOVE DUPLICATE LINES
    uniq_lines = {line_list[0]}
    for l in line_list:
        uniq_lines.add(l)
    return uniq_lines

def into_words( lines: list ) -> list[str]:
    word_list: list[str] = []
    for l in lines:
        word_list.append( re.split("\\W+", l ) )
    return word_list

def map_reduce( words: list[str] ) -> list[(str,int)]:
    sc: SparkContext = SparkContext()
    spark: SparkSession = open_spark_session(sc)

    lines: RDD = read_and_clean(sc, INPUT_FILE_NAME)
    results: RDD = lines.map(lambda w: (w, 1)).reduceByKey(lambda x,y: x+y).sortByKey()

    df: DataFrame = spark.createDataFrame(results)
    df.coalesce(1).write.format(CSV_GENERATION_CLASS).save(OUTPUT_DIR_NAM

def main():
    text  = read_from_files()
    lines = unique_lines( text )
    words = into_words( lines )


    print( words )

if __name__ == '__main__':
    main()
