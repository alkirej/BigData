-- remove any old data laying around
-- DROP TABLE occupation;

-- create table for row order data
CREATE TABLE occupation ( name varchar(25) );

-- load data from file
LOAD DATA LOCAL INFILE '/home/maria_dev/data/u.occupation' INTO TABLE occupation
FIELDS TERMINATED BY '|'
LINES TERMINATED BY '\n';

