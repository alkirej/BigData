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



