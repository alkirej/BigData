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

