--clients
INSERT INTO client
(name, lastname, document, phone, email, active, modification_date, creation_date)
VALUES('Peter', 'Parker', '3509090', '+595971916309', 'peterparker@mail.com', true, now(), now());
INSERT INTO client
(name, lastname, document, phone, email, active, modification_date, creation_date)
VALUES('Bruce', 'Wayne', '3509091', '+595971916310', 'brucewayne@mail.com', true, now(), now());

--sellers
INSERT INTO seller
(code, document, description, active, modification_date, creation_date)
VALUES('DBUG', 'DBUG-001-PP', 'Daily Bugle LLC', true, now(), now());
INSERT INTO seller
(code, document, description, active, modification_date, creation_date)
VALUES('WEN', 'WEN-001-PP', 'Wayne Enterprises', true, now(), now());

--products
INSERT INTO product 
(sku, sku_vendor, cost, sale_price, description, active, seller_id, stock_quantity, modification_date, creation_date)
VALUES ('own-001', 'dbug-001', null, null, 'product-1-description', true, 1, 10, now(), now());
INSERT INTO product
(sku, sku_vendor, cost, sale_price, description, active, seller_id, stock_quantity, modification_date, creation_date)
VALUES ('own-001', 'wen-001', null, null, 'product-10-description', true, 2, 10, now(), now());
