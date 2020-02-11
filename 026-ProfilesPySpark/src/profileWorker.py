import os
import re
import textract
from pyspark import SparkContext
from pyspark.ml.feature import StopWordsRemover,Tokenizer
from pyspark.rdd import RDD
from pyspark.sql import SparkSession, DataFrame, SQLContext
from pyspark.sql.functions import col
from pyspark.sql.types import DataType

# DIRECTORY TO STARTFROM
START_DIR = "/home/jeff/git/BigData/026-ProfilesPySpark/profiles/"
STOP_WORDS_FILENAME: str = "terrier.txt"

FULL_WEIGHTED_OUTPUT_FILENAME:str  = "FullWeighted.out"

WORD_COLUMN_NAME:  str = "word"
COUNT_COLUMN_NAME: str = "count"

# FOUR PROFILES TO GATHER DATA ON
THE_CHOSEN_FOUR:list = [ "JeffreyReiherProfile.doc",\
                         "VicenteGuevaraProfile.doc",\
                         "KislayJhaProfile.docx",\
                         "ChrisPeng-Profile.docx"\
                       ]

# CREATE AND MAKE AVAILABLE SPARK CONTEXT
SC: SparkContext = SparkContext()

def read_from_profile() -> str:
    """
    Open each profile in the directory and extract the text
    :return: a string of all the profiles concatenated
    """
    text = ""

    # READ EACH OF THE PROFILE DOCUMENTS AND ADD TO TEXT VAR
    for dir, subdirs, files in os.walk(START_DIR):
        print("Found directory: %s" % dir)
        for fname in files:
            full_path = START_DIR + fname
            try:
                text = str(textract.process(full_path).decode("utf-8") )
            except:
                print("Exception on " + fname)
            break
    return text

def into_lines( text:str ) -> list:
    """
    Split a string into a list of strings by separating the supplied string at each carriage return
    :param text: The text to split
    :return: a list of strings - one for each line in the text
    """
    return re.split("\n", text )

def into_unique_lines( line_list: list ) -> list:
    """
    Given a list of strings, return a list of strings where the duplicates are removed
    :param line_list: a list of strings
    :return: the same list of strings with duplicates removed
    """

    # USE SET TO REMOVE DUPLICATE LINES
    uniq_lines = { line_list[0] }
    for l in line_list:
        uniq_lines.add(l)

    return list(uniq_lines)

def into_words( lines: list ) -> list:
    """
    Given a list of strings, separate each item in the list into list of words by separating at whitespace
    :param lines: a list of strings
    :return: a list with each member being a list of strings (words)
    """
    word_list: list[str] = []
    for l in lines:
        word_list.append( re.split("\\W+", l ) )
    return list( word_list )

def read_and_process_files() -> list:
    """
    Read an entire directory containing only .doc and .docx files. Convert the text in those files into a list
    of tuples containing 3 members as follows:
        A - the word found in at least one of the documents
        B - the number of times the word was found in the documents (after duplicate lines were removed)
        C - the weight (percentage of time each word was used in the document)
    :return:
    """
    text: str   = read_from_profile()
    lines: list = into_lines( text )
    u_lines: list = into_unique_lines( lines )
    return into_words( u_lines )


def compute_sum_of_values( rdd_in: RDD ) ->  int:
    """
    Given an RDD, compute sum of all the counts (the 2nd item in the tuple)
    :param rdd_in: the rdd to process
    :return: the total number of words counted in this rdd
    """
    sum: int = 0
    for (i,j) in rdd_in:
        sum+=j

    return sum

def freq( value: int, total: int ) -> str:
    """
    Compute a frequence (%) and return a displayable string
    :param value: the # of times this word was found
    :param total: the total # of all words
    :return: a pretty, displayable weight (%)
    """
    weight: float = 100 * value/total
    str_val: str = str(weight)
    return str_val[:7]+"%"

def process_one_profile( file_name: str ):
    """
    Read and process all of the profile documents
    :return: an RDD containing a tuple as follows: ("word",count,"weight")
    """

    # READ ONE PROFILE
    full_path = START_DIR + file_name
    text:str = str(textract.process(full_path).decode("utf-8") )

    lines: list = into_lines( text )
    words: list = into_words( lines )
    main_processing( words, file_name )

def process_all_profiles():
    """
    Read and process all of the profile documents
    :return: an RDD containing a tuple as follows: ("word",count,"weight")
    """
    main_processing( read_and_process_files(), FULL_WEIGHTED_OUTPUT_FILENAME )

def remove_stop_words(input:RDD) -> RDD:
    return input

def main_processing( data: list, file_name: str ):
    try:
        # FLATTEN DATA AND REMOVE EMPTY RECORDS
        plain_res: RDD = SC.parallelize( data )
        plain_res = plain_res.flatMap( lambda x: x )
        plain_res = plain_res.filter(  lambda x: x!="" )
        red_rdd:RDD = remove_stop_words(plain_res)

        # MAP AND REDUCE THE DATA
        red_rdd = red_rdd.map( lambda x: (x.strip(),1) ).reduceByKey(lambda i,j:i+j ).collect()

        # COUNT THE TOTAL WORDS WE ARE PROCESSING
        results:list = list( red_rdd )
        total_wc = compute_sum_of_values( results )

        # COMPUTE FREQUENCY
        weighted_rdd:RDD = SC.parallelize( results ).map( lambda x: (x[0],x[1], freq(x[1],total_wc) ) )

        # COLLECT DATA AND WRITE TO A CSV FILE
        sql_ctx: SQLContext = SQLContext(SC)
        #df:DataFrame = sql_ctx.createDataFrame(weighted_rdd, [WORD_COLUMN_NAME,COUNT_COLUMN_NAME,"weight"]).orderBy( col(COUNT_COLUMN_NAME).desc() )
        #df.coalesce(1).write.format("com.databricks.spark.csv")\
        #             .options(header="true")\
        #             .save("file:///home/jeff/git/BigData/026-ProfilesPySpark/results/" + file_name )
    finally:
        SC.stop()

def main():
    process_all_profiles()

    for i in THE_CHOSEN_FOUR:
        process_one_profile( i )

if __name__ == "__main__":
    main()

