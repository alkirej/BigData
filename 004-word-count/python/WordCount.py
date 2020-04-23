# given a letter, return true if the letter is valid for alpha-numeric
# text and valse if not
#
# @param  letter  a single character string
# @return True if the string is a letter or number
#           False if the character is something else
def cleanLetter( letter ):
    # keep lower case letters
    if letter[0]>='a' and letter[0]<='z':
        return True
    # keep uppercase letters
    if (letter[0]>='A' and letter[0]<='Z'):
        return True
    # keep numbers
    if (letter[0]>='0' and letter[0]<='9'):
        return True
    # don't keep anything else
    return False

# a function to take a character string and return a cleaned up version
# with all non alpha-numeric characters removed
#
# @param uncleanedWord  any string
# @return  the original word without any characters that are not
#               alphanumeric
def cleanString( uncleanedWord ):
    returnVal=""
    for i in range( len(uncleanedWord) ):
        if cleanLetter( uncleanedWord[i] ):
            returnVal = returnVal +  uncleanedWord[i].lower()
    return returnVal

# Start of main function
# Open the file in read only mode
textFile = open( "/home/jeff/git/BigData/004-WordCount/shakespeare-hamlet.txt", "r" )

# read the file and close it.
contents = textFile.read()
textFile.close()

# split the file into words
wordList = contents.split()

# store the results here
resultMap = {}

# loop through the words from the text file, clean them up
# and store them in a dictionary (map)
for currentWord in wordList:
    cleanWord = cleanString( currentWord )
    if cleanWord in resultMap:
        resultMap[cleanWord] = resultMap[cleanWord]+1
    else:
        resultMap[cleanWord] = 1;

# display the results on the screen
formatString = "{},{}"
outputFile = open( "/home/jeff/git/BigData/004-WordCount/python/wordcount_python.csv", "w+" )

outputFile.write( formatString.format("word","count") )
outputFile.write( "\n" )

for currentWord in resultMap.keys():
    if ( len(currentWord) >0 ):
        outputFile.write( formatString.format(currentWord,resultMap[currentWord]) )
        outputFile.write( "\n" )
        
outputFile.close
