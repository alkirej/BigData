-- remove any old data lying around

DROP TABLE genre;
DROP TABLE genre_orig;



-- create table to store row order data from file

CREATE TABLE genre_orig
    ( name     STRING,
      genre_id INT
    )
ROW FORMAT DELIMITED
  FIELDS TERMINATED BY '|'
STORED AS TEXTFILE;


-- load row order data into table
LOAD DATA LOCAL INPATH '../u.genre' INTO TABLE genre_orig;



-- create table for column order data (final form)

CREATE TABLE genre
    ( name     STRING,
      genre_id INT
    )
STORED AS PARQUET;


-- copy data from temp table to permanent table

INSERT OVERWRITE TABLE genre
SELECT * FROM genre_orig;


-- clean up

DROP TABLE genre_orig;
