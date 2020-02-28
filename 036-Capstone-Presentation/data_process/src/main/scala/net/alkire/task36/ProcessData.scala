package net.alkire.task36
import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.hadoop.fs.{FSDataInputStream, Path}
import org.apache.spark.sql.functions.{col,explode}

object ProcessData
{
    def main(args: Array[String]): Unit =
    {
        Logger.getLogger("org").setLevel(Level.WARN)

        // BUILD A SPARK SESSION
        val spark: SparkSession = SparkSession.builder
                                                .master(Constants.Master)
                                                .appName(Constants.AppName)
                                                .getOrCreate()
    
        import spark.implicits._

        // FIND AND PROCESS FILES IN THE DATA DIRECTORY
        val files: Array[Path] = HadoopFileUtils.getFilesInDir( Constants.DataDir )
        for ( f <- files )
        {
            val df: DataFrame = spark.read.json( Constants.DataDir + "/" + f.getName )
                                     .select( col = Constants.JsonGamesLabel )
            val df2: DataFrame = df.select( explode($"games") ).select( "col.*" )
            df2.show()
            df2.printSchema()
        }

        spark.close
    }
}
