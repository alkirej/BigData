import os
import re
import textract

from pyspark import SparkContext, SQLContext
from pyspark.rdd import RDD
from pyspark.sql import DataFrame, Row
from pyspark.sql.functions import col
from pyspark.sql.types import IntegerType

START_DIR = "/home/jeff/git/BigData/026-ProfilesPySpark/profiles/"
STOP_WORDS_FILENAME: str = "terrier-stop.txt"

FULL_WEIGHTED_OUTPUT_FILENAME:str  = "FullWeighted.out"
JOINED_OUTPUT_FILENAME:str = "Joined.out"

WORD_COLUMN_NAME:  str    = "word"
COUNT_COLUMN_NAME: str    = "count"
WEIGHTED_COLUMN_NAME: str = "weight"

# FOUR PROFILES TO GATHER DATA ON
THE_CHOSEN_FOUR:list = [ "JeffreyReiherProfile.doc",\
                         "VicenteGuevaraProfile.doc",\
                         "KislayJhaProfile.docx",\
                         "ChrisPeng-Profile.docx"\
                       ]

SC: SparkContext = SparkContext()


def read_all_profiles() -> str:
    """
    Open each profile in the directory and extract the text
    :return: a string of all the profiles concatenated
    """
    text = ""

    # READ EACH OF THE PROFILE DOCUMENTS AND ADD TO TEXT VAR
    for dir, subdirs, files in os.walk(START_DIR):
        for fname in files:
            full_path = START_DIR + fname
            try:
                text += str(textract.process(full_path).decode("utf-8") ).lower()
            except:
                print("Exception on " + fname)
    return text

def read_one_profile( fname: str ) -> str:
    # READ EACH OF THE PROFILE DOCUMENTS AND ADD TO TEXT VAR
    full_path = START_DIR + fname
    return str(textract.process(full_path).decode("utf-8") )

#def into_lines( text:str ) -> list:
def into_unique_lines(text: list) -> RDD:
    """
    Split a string into a list of strings by separating the supplied string at each carriage return
    :param text: The text to split
    :return: a list of strings - one for each line in the text
    """
    result = []
    split_lines: list = re.split("\n", text )
    for i in split_lines:
        result.append( i.strip() )

    return SC.parallelize(result).distinct()

def into_words( rdd_in: RDD ) -> RDD:
    words: RDD = rdd_in.flatMap( lambda x: x.split(' ') ).filter( lambda x: len(x) > 1 )
    return words.map( lambda x: re.sub( ',()',"", x ))

def remove_stop_words( rdd_in: RDD ) -> RDD:
    file = open(STOP_WORDS_FILENAME, "r")
    sw_list: list = file.read().split("\n")
    return rdd_in.filter( lambda x: x not in sw_list )

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

def write_weighted_rdd( weighted_rdd: RDD, file_name: str ):
    sql_ctx: SQLContext = SQLContext(SC)
    df:DataFrame = sql_ctx.createDataFrame(weighted_rdd, [ WORD_COLUMN_NAME,COUNT_COLUMN_NAME,
                                                           WEIGHTED_COLUMN_NAME
                                                         ]).orderBy( col(COUNT_COLUMN_NAME).desc() )

    df.coalesce(1).write.format("com.databricks.spark.csv")\
                .options(header="true")\
                .save("file:///home/jeff/git/BigData/026-ProfilesPySpark/results/" + file_name )

def clean_row( r: Row ) -> Row:
    for i in [1,3,5,7]:
        if r[i] == "":
            r[i] = 0
    r[9] = r[1] + r[3] + r[5] + r[7]
    return r

def write_joined_df( df: DataFrame, file_name: str ):
    df=df.fillna(0, subset= { df[1]: 0,
                              df[3]: 0,
                              df[5]: 0,
                              df[7]: 0
                            }
                )
    df=df.withColumn("total", df[1]+df[3]+df[5]+df[7])

    df.coalesce(1).write.format("com.databricks.spark.csv")\
                .options(header="true")\
                .save("file:///home/jeff/git/BigData/026-ProfilesPySpark/results/" + file_name )

def calculate_weights( rdd_in: RDD ) -> RDD:
    rdd_in = rdd_in.collect()
    total: int = compute_sum_of_values( rdd_in )
    return SC.parallelize(rdd_in).map(lambda x: (x[0], x[1], freq(x[1],total)) )

def convert_to_words( lines: list ) -> RDD:
    words: RDD = into_words( u_lines )
    return remove_stop_words( words )

if __name__ == '__main__':
    """
    # READ TEXT FROM DOC FILES AND CONVERT TO TEXT
    text: str    = read_all_profiles()

    # CONVERT TEXT INTO A LIST OF UNIQUE LINES
    u_lines: RDD = into_unique_lines( text )
    # u_lines.filter( lambda s: s.contains( "data" ) )
    # print( u_lines.take(10) )

    # CONVERT TEXT INTO A LIST OF WORDS (WITH STOP WORDS REMOVED)
    word_rdd: RDD = convert_to_words( u_lines )

    # MAP AND REDUCE THE DATA
    reduced: RDD = word_rdd.map(lambda x: (x, 1)).reduceByKey(lambda i, j: i + j)

    # CALCULATE THE WEIGHT
    weighted_rdd: RDD = calculate_weights( reduced )

    # WRITE CSV TO DISK
    write_weighted_rdd( weighted_rdd, FULL_WEIGHTED_OUTPUT_FILENAME )
    """
    join_df: DataFrame = None

    filenum:int = 0
    for f in THE_CHOSEN_FOUR:
        # READ TEXT FROM DOC FILES AND CONVERT TO TEXT
        text = read_one_profile( f )

        # CONVERT TEXT INTO A LIST OF LINES
        u_lines = into_unique_lines( text )

        # CONVERT TEXT INTO A LIST OF WORDS (WITH STOP WORDS REMOVED)
        word_rdd = convert_to_words( u_lines )

        # MAP AND REDUCE THE DATA
        reduced = word_rdd.map(lambda x: (x, 1)).reduceByKey(lambda i, j: i + j)

        # CALCULATE THE WEIGHT
        weighted_rdd = calculate_weights( reduced )

        # WRITE CSV TO DISK
        write_weighted_rdd( weighted_rdd, f )

        # sql_ctx: SQLContext = SQLContext(SC)
        sql_ctx: SQLContext = SQLContext(SC)
        # df:DataFrame = sql_ctx.createDataFrame(weighted_rdd, [WORD_COLUMN_NAME,COUNT_COLUMN_NAME,"weight"]).orderBy( col(COUNT_COLUMN_NAME).desc() )
        new_df: DataFrame\
            = sql_ctx.createDataFrame( weighted_rdd,
                                       [ WORD_COLUMN_NAME,
                                         COUNT_COLUMN_NAME + "_" + str(filenum),
                                         WEIGHTED_COLUMN_NAME + "_" + str(filenum)
                                       ]
                                      )

        if join_df == None:
            join_df = new_df
        else:
            join_df = join_df.join( new_df, on=WORD_COLUMN_NAME, how="outer" )

        filenum += 1

    write_joined_df( join_df, JOINED_OUTPUT_FILENAME )
    print( join_df.collect() )