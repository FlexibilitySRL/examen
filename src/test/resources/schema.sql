CREATE TABLE products (
        id int NOT NULL AUTO_INCREMENT,
        name varchar(45) DEFAULT NULL,
        value varchar(45) DEFAULT NULL,
        PRIMARY KEY (id)
);


CREATE TABLE clients(
       id int NOT NULL AUTO_INCREMENT,
       name varchar(45) DEFAULT NULL,
       last_name varchar(45) DEFAULT NULL,
       PRIMARY KEY (`id`)
) ;


CREATE TABLE sales (
     id int NOT NULL AUTO_INCREMENT,
     client_id int DEFAULT NULL,
     approved tinyint(1) DEFAULT NULL,
     amount decimal(10,0) NOT NULL,
     PRIMARY KEY (id),
     CONSTRAINT sales_ibfk_1 FOREIGN KEY (client_id) REFERENCES clients (id)
);


CREATE TABLE sale_products (
     sale_id int DEFAULT NULL,
     product_id int DEFAULT NULL,
     id int NOT NULL AUTO_INCREMENT,
     PRIMARY KEY (id),
     CONSTRAINT sale_products_ibfk_1 FOREIGN KEY (sale_id) REFERENCES sales (id),
     CONSTRAINT sale_products_ibfk_2 FOREIGN KEY (product_id) REFERENCES products (id)
);
