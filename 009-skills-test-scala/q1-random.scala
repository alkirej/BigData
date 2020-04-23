import scala.util.Random

/**
 * This class is for the completion of Big Data Bootcamp task 9 question 1.
 */

object qOneRandom {
    val person = (  "Anna Alkire", 12 )
    
    /**
     * Print the name and id of a user
     */
    def printNameAndId ( name: String, id: Int )
    {
        print (name + " " + id + "\n" )
    }
    
    /**
     * Generate and return <count> random numbers
     *
     * @param   count   the number of random integers to generate
     * @return  a list of random numbers of the specified length
     */
    def randomGen( count: Int, max: Int = 100 ): List[Int] =
    {
        val rv: List[Int] = List.fill(count)(Random.nextInt(max))

        // This doesn't work because the list lenght cannot change
        // val rv: List[Int] = for ( i <- 1 to max )  yield Random.nextInt(max)
        return rv
    }

    /**
     * Main executable entry point.
     * @param  args - command line arguments (ignored)
     */    
    def main( args: Array[String] ): Unit = {
        // call randum number generator method
        val rnList = randomGen( 30 )
        print (rnList)
    
        print ("\n\n" )

        // print name and id        
        val person = ( "Anna Alkire", 101 )
        (printNameAndId _).tupled( person )
    }
}
