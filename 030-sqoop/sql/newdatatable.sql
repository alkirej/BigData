USE movies_db;

-- remove any existing data in tables
DROP TABLE return_data;

-- create table to load data into
--     data is stored in row order in text file
CREATE TABLE return_data
    ( user_id   int,
      movie_id  int,
      rating    int,
      time      int
    );

