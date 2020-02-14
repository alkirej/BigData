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
