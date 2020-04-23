CREATE DATABASE IF NOT EXISTS capstone;
USE capstone;

CREATE TABLE IF NOT EXISTS game
       ( endOfPeriod     STRING,
         arena           STRING,
         city            STRING,
         clock           STRING,
         country         STRING,
         currentPeriod   STRING,
         endTimeUTC      STRING,
         gameDuration    STRING,
         gameId          STRING,
         hTeam           STRUCT<
             fullName:       STRING,
             logo:           STRING,
             nickName:       STRING,
             score:          STRUCT<
                 points:         STRING
             >,
             shortName:      STRING,
             teamId:         STRING
         >,
         halfTime        STRING,
         league          STRING,
         seasonStage     STRING,
         seasonYear      STRING,
         startTimeUTC    STRING,
         statusGame      STRING,
         statusShortGame STRING,
         vTeam           STRUCT<
             fullName:       STRING,
             logo:           STRING,
             nickName:       STRING,
             score:          STRUCT<
                 points:         STRING
             >,
             shortName:      STRING,
             teamId:         STRING
         >
    )
    STORED AS PARQUET;


CREATE TABLE IF NOT EXISTS rss
       ( title       STRING,
         link        STRING,
         guid        STRING,
         image       STRING,
         category    STRING,
         description STRING,
         pubDate     STRING,
         enclosure   STRING
    )
    STORED AS PARQUET;

