
-- Drop tables
DROP TABLE IF EXISTS purchase_detail;
DROP TABLE IF EXISTS purchase;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS seller;
DROP TABLE IF EXISTS client;


CREATE TABLE client (
	id INTEGER NOT NULL AUTO_INCREMENT,
	name varchar(200) NOT NULL,
	lastname varchar(200) NOT NULL,
	document varchar(100) NOT NULL,
	phone varchar(50) NULL,
	email varchar(50) NULL,
	active bool NOT NULL DEFAULT TRUE,
	modification_date timestamp NOT NULL DEFAULT now(),
	creation_date timestamp NOT NULL DEFAULT now(),
	CONSTRAINT client_pk PRIMARY KEY (id)
);

CREATE TABLE seller (
	id INTEGER NOT NULL AUTO_INCREMENT,
	code varchar(100) NOT NULL,
	document varchar(100) NOT NULL,
	description varchar(300) NOT NULL,
	active bool NOT NULL DEFAULT TRUE,
	modification_date timestamp NOT NULL DEFAULT now(),
	creation_date timestamp NOT NULL DEFAULT now(),
	CONSTRAINT seller_pk PRIMARY KEY (id),
	CONSTRAINT unique_seller_code UNIQUE (code)
);

CREATE TABLE product (
	id INTEGER NOT NULL AUTO_INCREMENT,
	sku varchar(100) NOT NULL,
	sku_vendor varchar(100) NOT NULL,
	cost decimal NULL,
	sale_price decimal NULL,
	description varchar(300) NOT NULL,
	active bool NOT NULL DEFAULT TRUE,
	seller_id int8 NOT NULL,
	stock_quantity int NOT NULL DEFAULT 0,
	modification_date timestamp NOT NULL DEFAULT now(),
	creation_date timestamp NOT NULL DEFAULT now(),
	CONSTRAINT product_pk PRIMARY KEY (id)
);
ALTER TABLE product ADD CONSTRAINT product_seller_fk FOREIGN KEY (seller_id) REFERENCES seller(id);

CREATE TABLE purchase (
	id INTEGER NOT NULL AUTO_INCREMENT,
	receipt varchar(200) NOT NULL,
	total_amount decimal NULL,
	taxes decimal NULL,
	status bool NOT NULL,
	client_id int8 NOT NULL,
	modification_date timestamp NOT NULL DEFAULT now(),
	creation_date timestamp NOT NULL DEFAULT now(),
	CONSTRAINT purchase_pk PRIMARY KEY (id)
);
ALTER TABLE purchase ADD CONSTRAINT purchase_client_fk FOREIGN KEY (client_id) REFERENCES client(id);

CREATE TABLE purchase_detail (
	id INTEGER NOT NULL AUTO_INCREMENT,
	product_id int NOT NULL,
	quantity int NOT NULL DEFAULT 0,
	purchase_id int NOT NULL,
	unit_sale_price decimal NULL,
	total_sale_price decimal NULL,
	modification_date timestamp NOT NULL DEFAULT now(),
	creation_date timestamp NOT NULL DEFAULT now(),
	CONSTRAINT purchase_detail_pk PRIMARY KEY (id)
);

ALTER TABLE purchase_detail ADD CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES product(id);
ALTER TABLE purchase_detail ADD CONSTRAINT fk_purchase_id FOREIGN KEY (purchase_id) REFERENCES purchase(id);
