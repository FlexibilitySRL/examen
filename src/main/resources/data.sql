INSERT INTO customer (ID, NAME , ACTIVE) VALUES (101, 'Customer 101', TRUE );
INSERT INTO product (ID, NAME , ACTIVE) VALUES (201, 'Product 201', TRUE);
INSERT INTO purchase (ID, CUSTOMER_ID, approved, CREATION_DATE_TIME ) VALUES (301, 101, FALSE, {ts '2021-04-01 15:45:12.69'});
INSERT INTO product_purchase VALUES( 301, 201)