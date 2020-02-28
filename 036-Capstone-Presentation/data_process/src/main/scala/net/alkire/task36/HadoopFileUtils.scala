package net.alkire.task36
import org.apache.hadoop.fs.{FSDataInputStream, FileStatus, FileSystem, Path, PathFilter}
import java.io.File

import org.apache.hadoop.conf.Configuration

import scala.io.Source
import scala.util.Try

object HadoopFileUtils
{
    def getFilesInDir( dir: Path ): Array[Path] =
    {
        if ( Constants.Hdfs.getFileStatus( dir ).isDirectory )
        {
            val files = Constants.Hdfs.listStatus( dir, new EmptyFileFilter )
            files.map( f => f.getPath )
        } else
        {
            Array[Path]()
        }
    }
    
    private def readTextFileHelper( file: Path ): String =
    {
        val fStream: FSDataInputStream = Constants.Hdfs.open(file)
        Source.fromInputStream(fStream, "utf-8").mkString
    }

    def readTextFile( file: Path): Try[String] =
    {
        Try( readTextFileHelper(file) )
    }
}
