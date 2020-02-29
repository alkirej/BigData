package net.alkire.task36.createDb

object Constants
{
    
    val LoggerName: String = "org"
    val Master: String  = "local[*]"
    val AppName: String = "setupDb"
    
    val DbName: String  = "capstone"
    val TableNameGame: String = "game"
    
    val SqlCreateDb: String = "CREATE DATABASE IF NOT EXISTS " + DbName
    val SqlUseDb: String    = "USE " + DbName
    
    val SqlCreateGamesTable: String = s"""
          |CREATE TABLE IF NOT EXISTS ${TableNameGame}
          |       ( endOfPeriod     STRING,
          |         arena           STRING,
          |         city            STRING,
          |         clock           STRING,
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
          |       )""".stripMargin
    // last line: STORED AS PARQUET
    
}
