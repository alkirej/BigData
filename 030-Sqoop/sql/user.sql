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

