import java.io.File
import java.io.PrintWriter

/**
 * Singleton executable object for task 8 - WordCount
 */
object WordCount {
    // FILE NAME, CONTENTS, ETC.
    val textFile = scala.io.Source.fromFile( "/home/jeff/git/BigData/004-WordCount/shakespeare-hamlet.txt")
    val contents = try
                       textFile.mkString
                   finally
                       textFile.close()

    val wordlist = contents.split( "\\W+" )

    /**
     * Main executable entry point.
     *
     * @param  args - command line arguments (that are ignored)
     */
    def main( args: Array[String] ): Unit = {
        val countMap: scala.collection.mutable.Map[String,Int] = scala.collection.mutable.Map()

        // COUNT WORDS
        for ( word <- wordlist ) {
            // compare without regard to case
            val lcWord = word.toLowerCase
            if ( lcWord.length>0 ) {
                val count = countMap.get( lcWord ).getOrElse(0) + 1
                countMap += ( lcWord -> count )
            } // if
        } // for

        // WRITE RESULTS TO FILE
        val fileWriter = new PrintWriter( new File("/home/jeff/git/BigData/008-WordCountInScala/wordcount_scala.csv") )
        fileWriter.write( "word,count\n" )

        countMap foreach( item => fileWriter.write( item._1 + "," + item._2 + "\n" ) )
        fileWriter.close()

    } // main

} // object
