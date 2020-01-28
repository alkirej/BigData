
object qFourRemoveDup 
{

    def main( args: Array[String] ): Unit = {
        val weekdays:List[String] = List( "sun", "mon", "tue",
                                          "sun", "mon", "tue",
                                          "wed", "thu", "fri",
                                          "wed", "thu", "fri",
                                          "sat"
                                         )
        
        val noDups: Set[String] = weekdays.toSet
        
        println (noDups)
                                         
    } // main   
}

