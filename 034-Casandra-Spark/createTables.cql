-- CREATE AND USE NEW DB (aka: KEYSPACE)

CREATE KEYSPACE task34
WITH replication = { 'class':'SimpleStrategy', 'replication_factor':1 };

USE task34;


-- CREATE TABLE FOR CSV DATA

CREATE TABLE csv_data
    ( country_id   INT,
      country_name TEXT,
      year         INT,
      count_of     TEXT,
      count        DOUBLE,
      notes        TEXT,
      source       TEXT
    );


-- CREATE TABLE FOR JSON DATA

CREATE TABLE json_data
    ( 
      json_id     UUID PRIMARY KEY,
      fruit_name  TEXT,
      size        TEXT,
      color       TEXT
    );

