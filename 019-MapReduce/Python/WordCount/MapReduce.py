from collections import OrderedDict
import re

#####################
# CONSTANTS
####################
#FILE_NAME: str = "/home/jeff/BigData/004-WordCount/shakespeare-hamlet.txt"
FILE_NAME: str  = "C:\\Users\\jeff\\Documents\\shakespeare-hamlet.txt"

def first( s: OrderedDict ) -> str:
    '''Return the first element from an ordered collection
       or an arbitrary element from an unordered collection.
       Raise StopIteration if the collection is empty.
    '''
    return next( iter(s) )

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
def mapper(to_map: list) -> list:
    with_map: list = []

    for word in to_map:
        with_map.append( (word, 1) )

    return with_map

# ###################################################################
# Combine a list of tuples of the for (word, count) by finding all
# tuples with the given word and adding their counts together. A new
# list of tuples (word, count) will be returned with one tuple for
# each word found in the input list.
#
# params:
#       to_combine      the list of tuples to combine together
# return:
#       list of tuples (word, count)    of the coallated data
# ###################################################################
def sort_shuff_combine( to_combine: list ) -> list:
    combined_list: list = []

    for t in sorted( to_combine, key=lambda tpl: tpl[0] ):
        combined_list.append( t )
    return combined_list




def reducer( to_reduce: list ) -> list:
    output: OrderedDict = []
    last_word: str  = first(input)[0]
    word_count: int = 1

    print (last_word)

    for t in to_reduce:

        print ("k:" + t[0] + " lw:" + last_word + " c:"+ str(word_count) )

        if last_word==t[0]:
            word_count = word_count + 1
        else:
            output.append( (last_word, word_count) )
            word_count=1
            last_word=t[0]

    output.append((last_word, word_count))

    return output

# Start of main function
word_list: list = read_and_clean( FILE_NAME )
word_dict: list = mapper( word_list )
print (word_dict)

word_dict = sort_shuff_combine( word_dict )
print (word_dict)
#for t in word_dict:
#    print( t[0] + ":" +str(t[1]))
word_dict = reducer( word_dict )
print( word_dict )
for t in word_dict:
    print( t[0] + ":" +str(t[1]))

#print( "the: " + str(word_dict["the"]) )