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


