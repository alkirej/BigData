def cleanString( uncleanedWord ):
    for i in range( len(uncleanedWord) )
        print( " " + i + " - " )
        println( uncleanedWord[i] )

textFile = open( "/home/jeff/BigData/WordCount/shakespeare-hamlet.txt", "r" )
contents = textFile.read()
textFile.close()
print(contents)

wordMap = {}
wordList = contents.split();

print( wordList )


cleanString( "hello." )
