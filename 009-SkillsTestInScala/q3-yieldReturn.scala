
object qThreeYield
{
    def sampleReturnFn(): String = {
        return "THIS IS FUN!!!"
    }
    
    def sampleYieldFn(): IndexedSeq[Int] = {
        val result =
                for ( i <- 5 to 25 )
                    yield i * 4 
                    
        return result
    }
    
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
