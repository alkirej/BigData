/**
 * This class / singleton object is to complete
 * the Big Data Bootcamp task 9 question 3
 * This example shows how yield works in Scala
 * to build a list
 */
object qThreeYield
{
    /**
     * Very simple routine to return a simple string.
     * @returns   a string
     */
    def sampleReturnFn(): String = {
        return "THIS IS FUN!!!"
    }
    
    /**
     * Routine to build a list with the yield statement and return
     * it.
     *
     * @returns  a list of numbers generated with a yield statement
     */
    def sampleYieldFn(): IndexedSeq[Int] = {
        val result =
                for ( i <- 5 to 25 )
                    yield i * 4 
                    
        return result
    }
   
    /**
     * Main execution entry point.
     */ 
    def main( args: Array[String] ): Unit = {
        print( "Return sample: call a function and print the result:\n   " )
        println( sampleReturnFn )
        println
        
        println( "Now, call a function that uses a for loop to build a")
        println( "list using yield and returns the resulting list.")
        println( "Print the returned list: " )
        println( sampleYieldFn)
    }
}
