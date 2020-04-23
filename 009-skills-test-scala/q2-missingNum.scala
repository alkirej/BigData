/**
 * This class / singleton object is to complete
 * the Big Data Bootcamp task 9 question 2
 */
object qTwoMissingNum {

    /**
     * Look through the given array of integers and find
     * the lowest integer that is not anywhere in the list.
     */
    def findMissing( list: List[Int] ): Int = 
    {
        var lookingFor: Int = 0
        var done = false
        
        while ( !done )
        {
            lookingFor = lookingFor + 1
            if ( !list.contains(lookingFor) )
                done = true
        } 
        
        return lookingFor
    }
    
    /**
     * Main entry point for execution.
     */ 
    def main( args: Array[String] ): Unit = {
        val unsortedList: List[Int] = List( 1, 2, 0, -8, 64, 3, 10, 5, 8, 1, 4, 9, 123, 6, 7 )
        val ans:Int = findMissing( unsortedList )
        print (""+ans+"\n")
    }
    
}
