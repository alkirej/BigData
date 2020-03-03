package net.alkire.task36

import org.apache.hadoop.fs.{Path, PathFilter}


class TempFileFilter  extends PathFilter
{

    override def accept( path : Path ): Boolean = {
        val temp:Boolean = path.getName().contains( ".tmp" )
/*
println()
print( path.getName )
print( " ------------>  temp?")
print( temp )
print( "  len: ")
println( Constants.Hdfs.getContentSummary( path ).getLength )
 */
        !temp && Constants.Hdfs.getContentSummary( path ).getLength > 0
    }
}
