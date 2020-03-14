USE work;

DROP TABLE IF EXISTS price;
CREATE TABLE price ( house_id INT, house_price FLOAT,  ngbrhood VARCHAR(100) );

INSERT INTO price (house_id, house_price, ngbrhood) VALUES (  1,    4567, 'downtown' );
INSERT INTO price (house_id, house_price, ngbrhood) VALUES (  2,  334567, 'downtown' );
INSERT INTO price (house_id, house_price, ngbrhood) VALUES (  3,  234567, 'paradise valley' );
INSERT INTO price (house_id, house_price, ngbrhood) VALUES (  4,  193000, 'paradise valley' );
INSERT INTO price (house_id, house_price, ngbrhood) VALUES (  5,   93800, 'mesa' );
INSERT INTO price (house_id, house_price, ngbrhood) VALUES (  6,  193800, 'mesa' );
INSERT INTO price (house_id, house_price, ngbrhood) VALUES (  7, 1000000, 'sky harbor' );
INSERT INTO price (house_id, house_price, ngbrhood) VALUES (  8,  300000, 'sky harbor' );
INSERT INTO price (house_id, house_price, ngbrhood) VALUES (  9,   18000, 'downtown' );
INSERT INTO price (house_id, house_price, ngbrhood) VALUES ( 10,  250000, 'paradise valley' );
INSERT INTO price (house_id, house_price, ngbrhood) VALUES ( 11,  251100, 'mesa' );
INSERT INTO price (house_id, house_price, ngbrhood) VALUES ( 12,  251200, 'sky harbor' );
COMMIT;

-- FIRST WAY
WITH avg_pr (hood, av_price) 
AS (SELECT ngbrhood, avg(house_price) FROM price GROUP BY ngbrhood)
SELECT a.hood, p.house_price
FROM avg_pr a JOIN prices p ON a.hood = p.ngbrhood 
WHERE p.house_price > a.av_price;


-- SECOND WAY
WITH avg_pr (id, hood, price, avg_price) 
AS (SELECT house_id, ngbrhood, house_price, AVG(house_price) OVER (partition BY ngbrhood) FROM price) 
SELECT hood, price 
FROM avg_pr 
WHERE price > avg_price;

