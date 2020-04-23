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

