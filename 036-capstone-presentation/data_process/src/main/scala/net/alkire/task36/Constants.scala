package net.alkire.task36

import java.io.File

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}

object Constants
{
    val LoggerName: String   = "org"
    val DbName: String  = "capstone"

    // CREATE SPARK SESSION
    val Master: String        = "local[*]"
    val AppName: String       = "capstone"

    val TempDir: String       = "file:///tmp"
    val TempDirPath: Path     = new Path( TempDir )
    
    // GAME DATA DIRECTORY
    val DataDirName: String   = "hdfs://localhost:50501/data/game_data"
    val DataDir: Path = new Path( DataDirName )
    val DataDirBackupName: String = "hdfs://localhost:50501/data/backup/game_data"
    val DataDirBackupPath: Path   = new Path( DataDirBackupName )
    
    // RSS FEED DATA DIRECTORY
    val RssDataDirName: String   = "hdfs://localhost:50501/data/rss_data"
    val RssDataDir: Path = new Path( RssDataDirName )
    val RssDataDirBackupName: String = "hdfs://localhost:50501/data/backup/rss_data"
    val RssDataDirBackupPath: Path   = new Path( RssDataDirBackupName )


    
    val CoreSitePath: String           = "/opt/hadoop/etc/hadoop/core-site.xml"
    val DefaultFileSystemKey: String   = "fs.defaultFS"
    val DefaultFileSystemValue: String = "hdfs://localhost:50501"
    
    
    // DEFAULT HADOOP CONFIGURATION SETUP
    val HadoopConf: Configuration = new Configuration
    HadoopConf.addResource( new Path( Constants.CoreSitePath ) )
    HadoopConf.set( Constants.DefaultFileSystemKey, Constants.DefaultFileSystemValue )
    
    val Hdfs: FileSystem = FileSystem.get( HadoopConf )
    
    val JsonApiLabel: String   = "api"
    val JsonGamesLabel: String = "api.games"
    
    val RssItemLabel:            String = "item"
    val RssItemTitleLabel:       String = "title"
    val RssItemLinkLabel:        String = "link"
    val RssItemImageLabel:       String = "image"
    val RssItemGuidLabel:        String = "guid"
    val RssItemCategoryLabel:    String = "category"
    val RssItemDescriptionLabel: String = "description"
    val RssItemPubDateLabel:     String = "pubdate"
    val RssItemEnclosureLabel:   String = "enclosure"
    
    
    val DotAll: String       = ".*"
    
    val TblNameGame: String = "game"
    val TblNameRss: String  = "rss"
    val SqlCreateDb: String = s"CREATE DATABASE IF NOT EXISTS ${DbName}"
    val SqlUseDb: String    = s"USE ${DbName}"

    
    val SqlCreateGamesTable: String = s"""
                                         |CREATE TABLE IF NOT EXISTS ${TblNameGame}
                                         |       ( endOfPeriod     STRING,
                                         |         arena           STRING,
                                         |         city            STRING,
                                         |         clock           STRING,
                                         |         country         STRING,
                                         |         currentPeriod   STRING,
                                         |         endTimeUTC      STRING,
                                         |         gameDuration    STRING,
                                         |         gameId          STRING,
                                         |         hTeam           STRUCT<
                                         |             fullName:       STRING,
                                         |             logo:           STRING,
                                         |             nickName:       STRING,
                                         |             score:          STRUCT<
                                         |                 points:         STRING
                                         |             >,
                                         |             shortName:      STRING,
                                         |             teamId:         STRING
                                         |         >,
                                         |         halfTime        STRING,
                                         |         league          STRING,
                                         |         seasonStage     STRING,
                                         |         seasonYear      STRING,
                                         |         startTimeUTC    STRING,
                                         |         statusGame      STRING,
                                         |         statusShortGame STRING,
                                         |         vTeam           STRUCT<
                                         |             fullName:       STRING,
                                         |             logo:           STRING,
                                         |             nickName:       STRING,
                                         |             score:          STRUCT<
                                         |                 points:         STRING
                                         |             >,
                                         |             shortName:      STRING,
                                         |             teamId:         STRING
                                         |         >
                                         |    )
                                         |    STORED AS PARQUET
                                         """.stripMargin
    
    val SqlCreateRssTable: String = s"""
                                         |CREATE TABLE IF NOT EXISTS ${TblNameRss}
                                         |       ( title       STRING,
                                         |         link        STRING,
                                         |         guid        STRING,
                                         |         image       STRING,
                                         |         category    STRING,
                                         |         description STRING,
                                         |         pubDate     STRING,
                                         |         enclosure   STRING
                                         |    )
                                         |    STORED AS PARQUET
                                         """.stripMargin
    
}
