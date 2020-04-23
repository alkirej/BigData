DROP DATABASE movies_db;

CREATE DATABASE movies_db;
USE movies_db;



-- remove any existing data in tables 
-- DROP TABLE data; 

-- create table to load data into
--     data is stored in row order in text file
CREATE TABLE data
    ( user_id   int,
      movie_id  int,
      rating    int,
      time      int
    );

-- load data into table 
LOAD DATA LOCAL INFILE '/home/maria_dev/data/u.data' INTO TABLE data
FIELDS TERMINATED BY '\t'
LINES TERMINATED BY '\n';





-- remove any old data lying around
-- DROP TABLE movie;

-- create temp table for row order data
CREATE TABLE movie
    ( movie_id         INT,
      title            VARCHAR(80),
      release_dt       DATE,
      video_release_dt DATE,
      imdb_url         VARCHAR(120),
      g_unknown        INT,
      g_action         INT,
      g_adventure      INT,
      g_animation      INT,
      g_children       INT,
      g_comedy         INT,
      g_crime          INT,
      g_documentary    INT,
      g_drama          INT,
      g_fantasy        INT,
      g_filemoir       INT,
      g_horror         INT,
      g_musical        INT,
      g_mystery        INT,
      g_romance        INT,
      g_scifi          INT,
      g_thriller       INT,
      g_war            INT,
      g_western        INT
    );

-- insert row order data into temp table
LOAD DATA LOCAL INFILE '/home/maria_dev/data/new.item' INTO TABLE movie
FIELDS TERMINATED BY '|'
LINES  TERMINATED BY '\n';




-- remove any old data lying around
-- DROP TABLE genre;

-- create table to store row order data from file
CREATE TABLE genre
    ( name     VARCHAR(40),
      genre_id INT
    );

-- load row order data into table
LOAD DATA LOCAL INFILE '/home/maria_dev/data/u.genre' INTO TABLE genre
FIELDS TERMINATED BY '|'
LINES TERMINATED BY '\n';



-- remove any old data laying around
-- DROP TABLE occupation;

-- create table for row order data
CREATE TABLE occupation ( name varchar(25) );

-- load data from file
LOAD DATA LOCAL INFILE '/home/maria_dev/data/u.occupation' INTO TABLE occupation
FIELDS TERMINATED BY '|'
LINES TERMINATED BY '\n';



-- remove any old data
-- DROP TABLE usr;

-- create temp table to store user data
CREATE TABLE usr
    ( user_id int,
      age     int,
      sex     char(1),
      career  varchar(40),
      zipcode varchar(10)
    );

-- load row order data into db
LOAD DATA LOCAL INFILE '/home/maria_dev/data/u.user' INTO TABLE usr
FIELDS TERMINATED BY '|'
LINES TERMINATED BY '\n';



