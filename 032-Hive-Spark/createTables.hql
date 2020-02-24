-- CREATE AND USE NEW DB

CREATE DATABASE task32; 
USE task32;


-- CREATE TABLE FOR CSV DATA

CREATE TABLE csv_data
    ( country_id   INT,
      country_name STRING,
      year         INT,
      count_of     STRING,
      count        DOUBLE,
      notes        STRING,
      source       STRING
    )
STORED AS PARQUET;


-- CREATE TABLE FOR JSON DATA

CREATE TABLE json_data
    ( amerind_nh    DOUBLE,
      asian_nh      DOUBLE,
      black_nh      DOUBLE,
      divindx_cy    DOUBLE,
      geo_point_2d  STRUCT<
          lat:      DOUBLE,
          lon:      DOUBLE
      >,
      geo_shape     STRUCT<
          geometry:   STRUCT< 
              coordinates: ARRAY<ARRAY<ARRAY<DOUBLE>>>,
              type:        STRING
          >,
          type:  STRING
      >,
      hisppop_cy    DOUBLE,
      id            INT,
      landarea      DOUBLE,
      name          STRING,
      othrace_nh    DOUBLE,
      pacific_nh    DOUBLE,
      st_abbrev     CHAR(2),
      totpop10      DOUBLE,
      totpop_cy     DOUBLE,
      white_nh      DOUBLE
    )
STORED AS PARQUET;

