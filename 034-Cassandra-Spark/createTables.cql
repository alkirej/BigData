-- CREATE AND USE NEW DB (aka: KEYSPACE)

-- CREATE KEYSPACE task34
-- WITH replication = { 'class':'SimpleStrategy', 'replication_factor':1 };

USE task34;


-- CREATE TABLE FOR CSV DATA
DROP TABLE IF EXISTS csv_data;
CREATE TABLE IF NOT EXISTS csv_data
    ( csv_id       UUID PRIMARY KEY, 
      country_id   TEXT,
      country_name TEXT,
      year         TEXT,
      count_of     TEXT,
      count        TEXT,
      notes        TEXT,
      source       TEXT
    );


-- CREATE TABLE FOR JSON DATA
DROP TABLE IF EXISTS json_data;
CREATE TABLE IF NOT EXISTS json_data
    ( 
      json_id     UUID PRIMARY KEY,
      fruit       TEXT,
      size        TEXT,
      color       TEXT
    );

