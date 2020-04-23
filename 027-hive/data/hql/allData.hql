DROP DATABASE movies_db CASCADE;

CREATE DATABASE movies_db;
USE movies_db;



-- remove any existing data in tables

DROP TABLE data;
DROP TABLE data_orig;



-- create table to load data into
--     data is stored in row order in text file

CREATE TABLE data_orig
    ( user_id   int,
      movie_id  int,
      rating    int,
      time      int
    )
ROW FORMAT DELIMITED
  FIELDS TERMINATED BY '\t'
STORED AS TEXTFILE;



-- load data into table 

LOAD DATA LOCAL INPATH '../u.data' INTO TABLE data_orig;


-- create column order table for permanent storage

CREATE TABLE data
    ( user_id   int,
      movie_id  int,
      rating    int,
      time      int
    )
STORED AS PARQUET;


-- copy data from row order table to column order table

INSERT OVERWRITE TABLE data 
SELECT * FROM data_orig;


-- clean up
DROP TABLE data_orig;


-- remove any old data lying around

DROP TABLE movie;
DROP TABLE movie_orig;



-- create temp table for row order data

CREATE TABLE movie_orig
    ( movie_id         INT,
      title            STRING,
      release_dt       DATE,
      video_release_dt DATE,
      imdb_url         STRING,
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
    )
ROW FORMAT DELIMITED
  FIELDS TERMINATED BY '|'
STORED AS TEXTFILE;


-- insert row order data into temp table
LOAD DATA LOCAL INPATH '../new.item' INTO TABLE movie_orig;



-- create permanent table in column order format

CREATE TABLE movie 
    ( movie_id         INT,
      title            STRING,
      release_dt       DATE,
      video_release_dt DATE,
      imdb_url         STRING,
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
    )
STORED AS PARQUET;



-- move data from temp to permanent storage

INSERT OVERWRITE TABLE movie
SELECT * FROM movie_orig;



-- clean up

DROP TABLE movie_orig;



-- remove any old data lying around

DROP TABLE genre;
DROP TABLE genre_orig;



-- create table to store row order data from file

CREATE TABLE genre_orig
    ( name     STRING,
      genre_id INT
    )
ROW FORMAT DELIMITED
  FIELDS TERMINATED BY '|'
STORED AS TEXTFILE;


-- load row order data into table
LOAD DATA LOCAL INPATH '../u.genre' INTO TABLE genre_orig;



-- create table for column order data (final form)

CREATE TABLE genre
    ( name     STRING,
      genre_id INT
    )
STORED AS PARQUET;


-- copy data from temp table to permanent table

INSERT OVERWRITE TABLE genre
SELECT * FROM genre_orig;


-- clean up

DROP TABLE genre_orig;


-- remove any old data laying around

DROP TABLE occupation;
DROP TABLE occupation_orig;



-- create table for row order data

CREATE TABLE occupation_orig ( name varchar(25) )
ROW FORMAT DELIMITED
  FIELDS TERMINATED BY '|'
STORED AS TEXTFILE;



-- load data from file
LOAD DATA LOCAL INPATH '../u.occupation' INTO TABLE occupation_orig;



-- create table for permanent column order data

CREATE TABLE occupation ( name varchar(25) )
STORED AS PARQUET;


-- move data into permanent column order format

INSERT OVERWRITE TABLE occupation
SELECT * FROM occupation_orig;



-- clean up
DROP TABLE occupation_orig;



-- remove any old data

DROP TABLE usr_orig;
DROP TABLE usr;



-- create temp table to store user data

CREATE TABLE usr_orig
    ( user_id int,
      age     int,
      sex     char(1),
      career  varchar(40),
      zipcode varchar(10)
    )
ROW FORMAT DELIMITED
  FIELDS TERMINATED BY '|'
STORED AS TEXTFILE;


-- load row order data into db

LOAD DATA LOCAL INPATH '../u.user' INTO TABLE usr_orig;



-- create a table to permanently store data (in column order format)

CREATE TABLE usr 
    ( user_id int,
      age     int,
      sex     char(1),
      career  varchar(40),
      zipcode varchar(10)
    )
STORED AS PARQUET;



-- copy data from row order table into column order table

INSERT OVERWRITE TABLE usr
SELECT * FROM usr_orig;



-- clean up

DROP TABLE usr_orig;



