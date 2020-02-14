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

