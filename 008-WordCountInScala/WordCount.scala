import java.io.File
import java.io.PrintWriter

object WordCount {
    val textFile = scala.io.Source.fromFile( "/home/jeff/BigData/004-WordCount/shakespeare-hamlet.txt")
    val contents = try
                       textFile.mkString
                   finally
                       textFile.close()

    val wordlist = contents.split( "\\W+" )

    def main( args: Array[String] ): Unit = {
        val countMap: scala.collection.mutable.Map[String,Int] = scala.collection.mutable.Map()

        // count words
        for ( word <- wordlist ) {
            val lcWord = word.toLowerCase
            if ( lcWord.length>0 ) {
                val count = countMap.get( lcWord ).getOrElse(0) + 1
                countMap += ( lcWord -> count )
            }
        } // for

        // output
        val fileWriter = new PrintWriter( new File("/home/jeff/output/wordcount_scala.csv") )
        fileWriter.write( "word,count\n" )

        countMap foreach( item => fileWriter.write( item._1 + "," + item._2 + "\n" ) )
        fileWriter.close()

    } // main

} // object
