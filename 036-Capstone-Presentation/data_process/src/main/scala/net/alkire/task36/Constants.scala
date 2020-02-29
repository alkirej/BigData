package net.alkire.task36

import java.io.File

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}

object Constants
{
    val LoggerName: String   = "org"

    // CREATE SPARK SESSION
    val Master: String        = "local[*]"
    val AppName: String       = "capstone"
    
    val DataDirName: String   = "hdfs://localhost:50501/data/game_data"
    val DataDir: Path = new Path( DataDirName )

    val CoreSitePath: String           = "/opt/hadoop/etc/hadoop/core-site.xml"
    val DefaultFileSystemKey: String   = "fs.default.name"
    val DefaultFileSystemValue: String = "hdfs://localhost:50501"
    
    
    // DEFAULT HADOOP CONFIGURATION SETUP
    val HadoopConf: Configuration = new Configuration
    HadoopConf.addResource( new Path( Constants.CoreSitePath ) )
    HadoopConf.set( Constants.DefaultFileSystemKey, Constants.DefaultFileSystemValue )
    
    val Hdfs: FileSystem = FileSystem.get( HadoopConf )
    
    val JsonApiLabel: String   = "api"
    val JsonGamesLabel: String = "api.games"
    
    val DotAll: String       = ".*"
    
    val DatabaseName: String  = "capstone"
    val TablenameGame: String = "game"
}
