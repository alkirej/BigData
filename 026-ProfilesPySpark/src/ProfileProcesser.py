import os
import re
import textract

from pyspark import SparkContext, SQLContext
from pyspark.rdd import RDD
from pyspark.sql import SparkSession, DataFrame, Row, functions as F
from pyspark.sql.types import StringType, StructField, StructType

START_DIR = "/home/jeff/git/BigData/026-ProfilesPySpark/profiles/"
STOP_WORDS_FILENAME: str = "terrier-stop.txt"

FULL_WEIGHTED_OUTPUT_FILENAME:str  = "FullWeighted.out"
JOINED_OUTPUT_FILENAME:str = "Joined.out"

WORD_COLUMN_NAME:  str    = "word"
COUNT_COLUMN_NAME: str    = "count"
WEIGHTED_COLUMN_NAME: str = "weight"
FREQUENCY_COUNT_FN: str   = "freq_count"

# FOUR PROFILES TO GATHER DATA ON
THE_CHOSEN_FOUR:list = [ "JeffreyReiherProfile.doc",\
                         "VicenteGuevaraProfile.doc",\
                         "KislayJhaProfile.docx",\
                         "ChrisPeng-Profile.docx"\
                       ]

TOOL_LIST: list =      [ "kafka",  "spark",   "emr",     "mr",        "mapreduce",
                         "flume",  "aws",     "Hadoop",  "Spark",     "Data Bricks",
                         "Hive",   "Presto",  "Airflow", "EC2",       "Amazon",
                         "Python", "Pyspark", "Redshift" "Snowflake", "Googleads"
                       ]
SC: SparkContext = SparkContext()
SPARK: SparkSession = SparkSession(SC)

total_counts_for_the_four: list = []

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
    return text.lower()

def read_one_profile( fname: str ) -> str:
    # READ EACH OF THE PROFILE DOCUMENTS AND ADD TO TEXT VAR
    full_path = START_DIR + fname
    return str(textract.process(full_path).decode("utf-8") ).lower()

#def into_lines( text:str ) -> list:
def into_unique_lines(text: str) -> RDD:
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

def write_weighted_rdd( weighted_rdd: RDD, file_name: str ):
    sql_ctx: SQLContext = SQLContext(SC)
    df:DataFrame = sql_ctx.createDataFrame(weighted_rdd, [ WORD_COLUMN_NAME,COUNT_COLUMN_NAME,
                                                           WEIGHTED_COLUMN_NAME
                                                         ]).orderBy( F.col(COUNT_COLUMN_NAME).desc() )

    df.coalesce(1).write.format("com.databricks.spark.csv")\
                .options(header="true")\
                .save("file:///home/jeff/git/BigData/026-ProfilesPySpark/results/" + file_name )

def clean_row( r: Row ) -> Row:
    for i in [1,3,5,7]:
        if r[i] == "":
            r[i] = 0
    r[9] = r[1] + r[3] + r[5] + r[7]
    return r

def write_joined_df( df: DataFrame, file_name: str ) -> DataFrame:
    #df=df.fillna(0, subset= [ "count_0",  "count_1",  "count_2",  "count_3"  ] )
    #df=df.fillna(0, subset= [ "weight_0", "weight_1", "weight_2", "weight_3" ] )
    df=df.fillna( 0 )
    df=df.withColumn("total", df[1] + df[3] + df[5] + df[7])

    sum_df: DataFrame = df.groupBy().sum()
    total_words:int = sum_df.collect()[0][4]

    #freq_db_fn = F.udf(lambda cnt: freq_db(cnt, total_words) )
    df=df.withColumn("total-weight", F.round(df[9]/total_words*100,3) )

    df.coalesce(1).write.format("com.databricks.spark.csv")\
                .options(header="true")\
                .save("file:///home/jeff/git/BigData/026-ProfilesPySpark/results/" + file_name )
    return df

def calculate_weights( rdd_in: RDD ) -> RDD:
    rdd_in = rdd_in.collect()
    total: int = compute_sum_of_values( rdd_in )
    total_counts_for_the_four.append( total )

    return SC.parallelize(rdd_in).map(lambda x: (x[0], x[1], 0))
    #F.round(100*x[1]/total)))

def total_weight_of_list( words: list, joined_weights: DataFrame ) -> list:
    # WORD LIST TO DF
    words_df = SPARK.createDataFrame(words, StringType()).toDF( WORD_COLUMN_NAME )
    #joined_weights = joined_weights.fillna(0)

    # INNER JOIN DFs
    words_df = words_df.join( joined_weights, on=WORD_COLUMN_NAME, how="inner")

    # COMPUTE TOTAL COUNTS AND WEIGHTS FOR EACH "PROFILE"
    words_df = words_df.agg(F.sum("count_0"), F.sum("count_1"), F.sum("count_2"), F.sum("count_3")).collect()

    return [ 100*words_df[0][i]/total_counts_for_the_four[i] for i in range(4) ]

def convert_to_words( lines: list ) -> RDD:
    words: RDD = into_words( u_lines )
    return remove_stop_words( words )

if __name__ == '__main__':
    # READ TEXT FROM DOC FILES AND CONVERT TO TEXT
    text: str    = read_all_profiles()

    # CONVERT TEXT INTO A LIST OF UNIQUE LINES
    u_lines: RDD = into_unique_lines( text )

    '''
    with open( "profile-lines.txt", "w" ) as file:
        for i in u_lines.collect():
            file.write(i)
            file.write('\n')
    quit()
    '''

    # CONVERT TEXT INTO A LIST OF WORDS (WITH STOP WORDS REMOVED)
    word_rdd: RDD = convert_to_words( u_lines )

    # MAP AND REDUCE THE DATA
    reduced: RDD = word_rdd.map(lambda x: (x, 1)).reduceByKey(lambda i, j: i + j)

    # CALCULATE THE WEIGHT
    weighted_rdd: RDD = calculate_weights( reduced )

    # WRITE CSV TO DISK
    write_weighted_rdd( weighted_rdd, FULL_WEIGHTED_OUTPUT_FILENAME )

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

    join_df = write_joined_df( join_df, JOINED_OUTPUT_FILENAME )

    for i in total_weight_of_list(TOOL_LIST, join_df ):
        print(i)
