object qTwoMissingNum {

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
    
    def main( args: Array[String] ): Unit = {
        val unsortedList: List[Int] = List( 1, 2, 0, -8, 64, 3, 10, 5, 8, 1, 4, 9, 123, 6, 7 )
        val ans:Int = findMissing( unsortedList )
        print (""+ans+"\n")
    }
    
}