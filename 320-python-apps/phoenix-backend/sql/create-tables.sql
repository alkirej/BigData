CREATE TABLE IF NOT EXISTS label_wide
(
    artifact_id   VARCHAR PRIMARY KEY 
);
SELECT * FROM label_wide;

CREATE TABLE IF NOT EXISTS label_indexable
(
    label_name    VARCHAR,
    artifact_id   VARCHAR,
    label_value   VARCHAR
    CONSTRAINT label_indexable_pk PRIMARY KEY (label_name, artifact_id)
);
    
CREATE INDEX label_name_idx ON label_indexable (label_name) INCLUDE (label_value, artifact_id);

SELECT * FROM label_indexable;

CREATE TABLE IF NOT EXISTS collection
(
    collection_name VARCHAR PRIMARY KEY,
    artifact_list   VARCHAR,
    parent          VARCHAR
);

SELECT * FROM collection;

CREATE TABLE IF NOT EXISTS artifact
(
    artifact_id   VARCHAR NOT NULL PRIMARY KEY,
    doc_name      VARCHAR,
    doc_type      VARCHAR,
    loan_id       VARCHAR,
    mail_id       VARCHAR,
    doc_url       VARCHAR,
    file_size     BIGINT,
    created       VARCHAR
);

SELECT * FROM artifact;
