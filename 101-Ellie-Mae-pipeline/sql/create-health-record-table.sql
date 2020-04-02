USE sqooptest;

DROP TABLE IF EXISTS health_visit;

CREATE TABLE health_visit
( restaurant_name VARCHAR(80),
  inspection_date VARCHAR(20),
  inspection_score VARCHAR(5),
  restaurant_address VARCHAR(200),
  restaurant_city VARCHAR(80),
  restaurant_state VARCHAR(20),
  restaurant_zip VARCHAR(10),
  inspection_description VARCHAR(200),
  inspection_type VARCHAR(80),
  inspection_violation VARCHAR(200)
);
