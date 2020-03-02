package net.alkire.task36
import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions.explode
import org.apache.hadoop.fs.Path

import scala.xml.{Elem, Node, NodeSeq, XML}
import scala.util.{Failure, Success, Try}

object ProcessData
{
    def showData(): Unit =
    {
        ShowData.showData
        // sys.exit
    }
    
    def readGameFile(f: Path)(implicit spark: SparkSession): Unit =
    {
        import spark.implicits._
    
        val df: DataFrame = spark.read.json(Constants.DataDir + "/" + f.getName)
            .select(col = Constants.JsonGamesLabel)
        val expandedDf: DataFrame = df.select(explode($"games")).select("col.*")
        expandedDf.write.mode("append").insertInto(Constants.TblNameGame)
    }
    
    def buildArticleXml(nodes: NodeSeq): NewsArticle =
    {
        var title       = ""
        var link        = ""
        var guid        = ""
        var category    = ""
        var description = ""
        var pubdate     = ""
        var enclosure   = ""
        var image       = ""
        
        for ( node <- nodes )
        {
            val children: Seq[Node] = node.child
            for ( n <- children )
            {
                val content: String = n.text.trim()
                n.label.toLowerCase match
                {
                    case Constants.RssItemTitleLabel       => title       = content
                    case Constants.RssItemLinkLabel        => link        = content
                    case Constants.RssItemGuidLabel        => guid        = content
                    case Constants.RssItemImageLabel       => image       = content
                    case Constants.RssItemCategoryLabel    => category    = content
                    case Constants.RssItemDescriptionLabel => description = content
                    case Constants.RssItemPubDateLabel     => pubdate     = content
                    case Constants.RssItemEnclosureLabel   => enclosure   = content
                    case _ =>  // ignore
                }
            }
        }
        NewsArticle( title, link, guid, image, category, description, pubdate, enclosure )
    }
    
    def storeInDb( articles: Seq[NewsArticle] )( implicit spark: SparkSession ): Unit =
    {
        import spark.implicits._
        val rssDf: DataFrame = articles.toDF()
        rssDf.write.mode("append").insertInto(Constants.TblNameRss)
    }
    
    def processRssFile( f: Path )( implicit spark: SparkSession ): Unit =
    {
        println( f.getName )
        HadoopFileUtils.readTextFile( f ) match
        {
            case Success( text: String ) => {
                println( "Processing:" )

                // Read file
                val feed: NodeSeq = XML.loadString( text ) \\ Constants.RssItemLabel
                val newData: Seq[NewsArticle] = for ( i <- feed ) yield buildArticleXml( i )
                storeInDb( newData )
            }
            
            case Failure( exc )   => {
                exc.printStackTrace()
            }
        }
    }

    
    def main(args: Array[String]): Unit =
    {
        Logger.getLogger(Constants.LoggerName).setLevel(Level.WARN)
        System.setSecurityManager(null)
showData() // !!! remove
   
        // BUILD A SPARK SESSION
        implicit val spark: SparkSession = SparkSession.builder
                                    .master(Constants.Master)
                                    .appName(Constants.AppName)
                                    .enableHiveSupport()
                                    .config( "spark.sql.warehouse.dir",      "hdfs://localhost:50501/user/hive/warehouse" )
                                    .config( "hive.metastore.warehouse.dir", "hdfs://localhost:50501/user/hive/warehouse" )
                                    .getOrCreate()
        
        
        // CONNECT TO APPROPRIATE DB
        // spark.sql( Constants.SqlCreateDb )
        spark.sql( Constants.SqlUseDb )
    
        // CREATE NECESSARY TABLES
        // spark.sql( Constants.SqlCreateGamesTable )
        // spark.sql( Constants.SqlCreateRssTable )
    
        // FIND AND PROCESS FILES IN THE DATA DIRECTORY AND THEN MOVE TO BACKUP LOCATION
        val files: Array[Path] = HadoopFileUtils.getFilesInDir( Constants.DataDir )
        for ( f <- files )
        {
            readGameFile(f)
            HadoopFileUtils.moveFile( f.getName, Constants.DataDir, Constants.DataDirBackupPath )
        }
    
        val rssFiles: Array[Path] = HadoopFileUtils.getFilesInDir( Constants.RssDataDir )
        for ( f <- rssFiles )
        {
            processRssFile(f)
            HadoopFileUtils.moveFile( f.getName, Constants.RssDataDir, Constants.RssDataDirBackupPath )
        }
        
        spark.close
    }
}
