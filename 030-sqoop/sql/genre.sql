-- remove any old data lying around
-- DROP TABLE genre;

-- create table to store row order data from file
CREATE TABLE genre
    ( name     VARCHAR(40),
      genre_id INT
    );

-- load row order data into table
LOAD DATA LOCAL INFILE '/home/maria_dev/data/u.genre' INTO TABLE genre
FIELDS TERMINATED BY '|'
LINES TERMINATED BY '\n';

