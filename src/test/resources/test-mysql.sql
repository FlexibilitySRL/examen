DROP table IF EXISTS PRODUCT;


CREATE TABLE `product` (
  id INTEGER NOT NULL AUTO_INCREMENT,
  active bool DEFAULT NULL,
  description varchar(255) NOT NULL,
  stock INTEGER NOT NULL,
  CONSTRAINT PRODUCT_PK PRIMARY KEY (`id`)
) ;

INSERT INTO PRODUCT
(active, description, stock)
VALUES(1, 'sopa', 10);


DROP table IF EXISTS CLIENT;


CREATE TABLE `CLIENT` (
  id INTEGER NOT NULL AUTO_INCREMENT,
  active bool DEFAULT NULL,
  EMAIL varchar(255) NOT NULL,
  NAME VARCHAR(255) NOT NULL,
  CONSTRAINT CLIENT_PK PRIMARY KEY (`id`)
) ;

INSERT INTO CLIENT
(active, EMAIL, NAME)
VALUES(1, 'gucompi@gmail.com', 'Gustavo Cliente');

DROP table IF EXISTS SELLER;


CREATE TABLE `SELLER` (
  id INTEGER NOT NULL AUTO_INCREMENT,
  active bool DEFAULT NULL,
  NAME VARCHAR(255) NOT NULL,
  CONSTRAINT SELLER_PK PRIMARY KEY (`id`)
) ;

INSERT INTO SELLER
(active, NAME)
VALUES(1, 'Gustavo Vendedor');


DROP table IF EXISTS purchase;

CREATE TABLE `purchase` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `approved` bit(1) NOT NULL,
  `quantity` int DEFAULT NULL,
  `total_amount` decimal(19,2) DEFAULT NULL,
  `client_id` bigint NOT NULL,
  `product_id` bigint NOT NULL,
  `seller_id` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ;

insert into purchase (approved,quantity,total_amount,client_id,product_id,seller_id) values
(false,2,10,1,1,1)