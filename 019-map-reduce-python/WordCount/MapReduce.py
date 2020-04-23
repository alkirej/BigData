from collections import OrderedDict
import re

#####################
# CONSTANTS
####################
#INPUT_FILE_NAME: str = "/home/jeff/git/BigData/004-WordCount/shakespeare-hamlet.txt"
# INPUT_FILE_NAME: str  = "C:\\Users\\jeff\\Documents\\shakespeare-hamlet.txt"
INPUT_FILE_NAME: str = "shakespeare-hamlet.txt"

# OUTPUT_FILE_NAME: str = "/home/jeff/git/BigData/output/wc_results_python.csv"
# OUTPUT_FILE_NAME: str = "C:\\Users\\jeff\\Documents\\wc_results_python.csv"
OUTPUT_FILE_NAME: str = "results.txt"

CSV_HEADER_LINE: str = "word,count"

# ###########################################################################
# return a list of words from the file
# params:
#       file_name -> name of the file to read from disk (full path suggested)
# return:
#       list -> A list of strings representing the words for the file.
#               The list will be only words and converted to lower case
# #############################################################################
def read_and_clean( file_name: str ) -> list:
    # Open the file in read only mode
    textFile = open(file_name, "r")

    # read the file and close it.
    contents = textFile.read()
    textFile.close()

    # convert
    contents = contents.lower()

    # split the file into words
    split: list = re.findall(r"[\w']+", contents)

    return split

# ##############################################################
# Map a list of words to a list of tuples of the same length with
#           each given word paired with the number 1 (as in there
#           is one copy of this word here)
# params:
#       to_map:  a list of strings (words)
# return:
#       a list of tuples (string, int) there the string is the
#           word supplied by the input list and the int is
#           always 1
#################################################################
def map_to_one(to_map: list) -> list:
    with_map: list = []

    for word in to_map:
        with_map.append( (word, 1) )

    return with_map

# ###################################################################
# Given a list of tuples of the form (word,count), return a sorted
# copy of the list with the same length and same tuples, but where
# identical tuples are together in the list.
#
# params:
#       to_combine      the list of tuples to combine together
# return:
#       list of tuples (word, count)    of the coallated data
# ###################################################################
def sort( to_combine: list ) -> list:
    combined_list: list = []

    for t in sorted( to_combine, key=lambda tpl: tpl[0] ):
        combined_list.append( t )
    return combined_list



# #####################################################################
# Combine a list of tuples of the for (word, count) by finding all
# tuples with the given word and adding their counts together. A new
# list of tuples (word, count) will be returned with one tuple for
# each word found in the input list.
#
# params:
#       to_reduce       the list of tuples to reduce (combine together)
# return
#       list of tuples (word,count)     the newly formed list as described
#                                       in the method description
# ####################################################################
def reduce( to_reduce: list ) -> list:
    reduced_list: list = []
    last_word: str  = to_reduce[0][0]
    word_count: int = 1

    for t in to_reduce:
        if last_word==t[0]:
            word_count = word_count + 1
        else:
            reduced_list.append( (last_word, word_count) )
            word_count=1
            last_word=t[0]

    reduced_list.append((last_word, word_count))

    return reduced_list

# ####################################################################
# write the contents of a list of tuples of the form (word, count) to
# a csv.
#
# param:
#       file_name:      the name of the file to store the csv in
#                       It is recommended to included the full path
#       output_list:    the list of (word,count) tuples to write
# side effect:
#       creates or overwrites the file with the given name
# ####################################################################
def write_to_csv( file_name: str, output_list: list):
    output_file = open( file_name, "w+")

    output_file.write(CSV_HEADER_LINE)
    output_file.write("\n")

    formatString = "{},{}"

    for t in output_list:
        output_file.write( formatString.format(t[0],t[1]) )
        output_file.write( "\n" )

# #######################################################
# Read in the shakespeare file and write out a csv containing
# each word found in the book and the number of times the
# word appears in the book in csv format using the helper
# functions above.
# ######################################################
word_list: list = read_and_clean( INPUT_FILE_NAME )
word_count: list = map_to_one( word_list )

word_count = sort( word_count )
word_count = reduce( word_count )

write_to_csv(OUTPUT_FILE_NAME, word_count)

