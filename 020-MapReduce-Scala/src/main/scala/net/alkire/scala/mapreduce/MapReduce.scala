package net.alkire.scala.mapreduce

import java.io.File
import java.io.PrintWriter
import scala.io.BufferedSource

import scala.io.Source
import scala.collection.mutable.ListBuffer

object MapReduce {
    /**
     * return a list of words from the file
     * params:
     *       fileName    name of the file to read from disk (full path suggested)
     * return:
     *       list    A list of strings representing the words for the file.
     *               The list will be only words and converted to lower case
     */
    def readAndClean( fileName: String ): Array[String] ={
        // Open the file in read only mode
        val file: BufferedSource = Source.fromFile( Constants.InputFileName )
        val text: String = file.mkString
        file.close
        text.toLowerCase().split("\\W+")
    }

    /**
     * Map a list of words to a list of tuples of the same length with
     *           each given word paired with the number 1 (as in there
     *           is one copy of this word here)
     * params:
     *       toMap:  a list of strings (words)
     * return:
     *       a list of tuples (string, int) there the string is the
     *           word supplied by the input list and the int is
     *           always 1
     */
    def mapToOne( toMap: Array[String] ): List[(String,Int)] = {
        val withMap: ListBuffer[(String,Int)] = ListBuffer()

        for ( word <- toMap ) {
            withMap.append( (word, 1) )
        }
        withMap.toList
    }

    /**
     * Given a list of tuples of the form (word,count), return a sorted
     * copy of the list with the same length and same tuples, but where
     * identical tuples are together in the list.
     *
     * params:
     *       toCombine      the list of tuples to combine together
     * return:
     *       list of tuples (word, count)    of the coallated data
     */
    def sort( toCombine: List[(String,Int)] ): List[(String,Int)] = {
        val combinedList: ListBuffer[(String,Int)] = new ListBuffer[(String,Int)]()
        for ( t <- toCombine.sortWith( (x,y) => x._1<y._1 ) )
            combinedList.append( t )

        combinedList.toList
    }

    /**
    * Combine a list of tuples of the for (word, count) by finding all
    * tuples with the given word and adding their counts together. A new
    * list of tuples (word, count) will be returned with one tuple for
    * each word found in the input list.
    *
    * params:
    *       to_reduce       the list of tuples to reduce (combine together)
    * return
    *       list of tuples (word,count)     the newly formed list as described
    *                                       in the method description
    */
    def reduce( toReduce: List[(String,Int)] ): List[(String,Int)] = {
        val reducedList: ListBuffer[(String,Int)] = new ListBuffer[(String,Int)]
        var lastWord: String = toReduce.head._1
        var wc: Int = 1

        for ( t <- toReduce ) {
            if ( lastWord == t._1 ) {
                wc += 1
            } else {
                reducedList.append( (lastWord, wc) )
                wc = 1
                lastWord = t._1
            } // if ... else
        } // for
        reducedList.append( (lastWord, wc) )
        reducedList.toList
    }

    /**
     * write the contents of a list of tuples of the form (word, count) to
     * a csv.
     *
     * param:
     *       file_name:      the name of the file to store the csv in
     *                       It is recommended to included the full path
     *       output_list:    the list of (word,count) tuples to write
     * side effect:
     *       creates or overwrites the file with the given name
     */
    def writeToCsv( fileName: String, outputList: List[(String,Int)] ): Unit = {
        // output
        val fileWriter = new PrintWriter( new File( fileName ) )
        fileWriter.write( Constants.CsvHeaderLine )
        fileWriter.write( "\n" )

        for ( t <- outputList ) {
            fileWriter.write(t._1)
            fileWriter.write(",")
            fileWriter.write( t._2.toString )
            fileWriter.write("\n")
        }

        fileWriter.close()
    }

    /**
     * Read in the shakespeare file and write out a csv containing
     * each word found in the book and the number of times the
     * word appears in the book in csv format using the helper
     * functions above.
     */
    def main(args: Array[String]): Unit = {
        // get the word list (wl)
        val wl = readAndClean(Constants.InputFileName)
        // get and update the word count list (wcl)
        val wcl = mapToOne(wl)
        val sortedWcl = sort(wcl)
        val reducedWcl = reduce(sortedWcl)
        writeToCsv(Constants.OutputFileName, reducedWcl)
    }
}
