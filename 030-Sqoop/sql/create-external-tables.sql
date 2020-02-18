CREATE EXTERNAL TABLE IF NOT EXISTS movielens_db.usr
    ( user_id int,
      age     int,
      sex     char(1),
      career  varchar(40),
      zipcode varchar(10)
    )
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
LINES  TERMINATED BY '\n'
STORED AS TEXTFILE
LOCATION '/user/maria_dev/usr';





CREATE EXTERNAL TABLE movielens_db.genre
    ( name     STRING,
      genre_id INT
    )
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
LINES  TERMINATED BY '\n'
STORED AS TEXTFILE
LOCATION '/user/maria_dev/genre';





CREATE EXTERNAL TABLE movielens_db.movie
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
FIELDS TERMINATED BY ','
LINES  TERMINATED BY '\n'
STORED AS TEXTFILE
LOCATION '/user/maria_dev/movie/';
  



CREATE EXTERNAL TABLE movielens_db.occupation ( name varchar(25) )
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
LINES  TERMINATED BY '\n'
STORED AS TEXTFILE
LOCATION '/user/maria_dev/occupation/';

