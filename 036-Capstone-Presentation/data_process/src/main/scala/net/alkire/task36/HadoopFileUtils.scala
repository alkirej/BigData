package net.alkire.task36
import org.apache.hadoop.fs.{FSDataInputStream, Path}

import scala.io.Source
import scala.util.Try

object HadoopFileUtils
{
    def getFilesInDir( dir: Path ): Array[Path] =
    {
        if ( Constants.Hdfs.getFileStatus( dir ).isDirectory )
        {
            val files = Constants.Hdfs.listStatus( dir, new TempFileFilter() )
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

    def readTextFile( file: Path ): Try[String] =
    {
        Try( readTextFileHelper(file) )
    }
    
    def moveFile( fileName: String, dirToMoveFrom: Path, dirToSaveIn: Path ): Unit =
    {
        val hdfsFile: Path = new Path( dirToMoveFrom, fileName )
        Constants.Hdfs.moveToLocalFile( hdfsFile, Constants.TempDirPath )
        
        val localFile: Path = new Path ( Constants.TempDir, fileName )
        Constants.Hdfs.moveFromLocalFile( localFile, dirToSaveIn )
    }
}
