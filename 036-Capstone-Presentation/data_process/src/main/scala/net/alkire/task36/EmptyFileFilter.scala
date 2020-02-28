package net.alkire.task36

import org.apache.hadoop.fs.{Path, PathFilter}


class EmptyFileFilter  extends PathFilter
{

    override def accept( path : Path ): Boolean = {
        if ( Constants.Hdfs.getFileStatus( path ).isDirectory )
        {
            false
        } else
        {
            Constants.Hdfs.getContentSummary(path).getLength > 0
        }
    }
}
