**
 * This class / singleton object is to complete
 * the Big Data Bootcamp task 9 question 4
 */
object qFourRemoveDup 
{

    /**
     * Main execution entry point and simple routine to convert
     * the list to a set (thereby removing duplicates).
     */
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

