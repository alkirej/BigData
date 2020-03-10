-- ========
-- Sql - queston 5 of skills test
-- ========
-- Create a table for object you created above
-- Insert into that table (1 record)
-- Read all data from the table
-- Delete the table
-- ========

-- create new table
CREATE TABLE weekday (
  day_id    INT PRIMARY KEY,
  day_name  CHAR(3) NOT NULL
);
COMMIT;

-- insert a row into the new table
INSERT INTO weekday ( day_id, day_name )
    VALUE ( 1, 'sun' );
COMMIT;

-- read the contents of the table
SELECT * FROM weekday;
COMMIT;

-- remove the table from the db
DROP TABLE weekday;
COMMIT;
