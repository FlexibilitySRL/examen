DROP TABLE IF EXISTS paymenttype;

CREATE TABLE paymenttype (
    id BIGINT AUTO_INCREMENT  PRIMARY KEY,
    name VARCHAR(20) NOT NULL
);

DROP TABLE IF EXISTS transactionstatus;

CREATE TABLE transactionstatus (
    id BIGINT AUTO_INCREMENT  PRIMARY KEY,
    name VARCHAR(20) NOT NULL
);

DROP TABLE IF EXISTS buyer;

CREATE TABLE buyer (
    id BIGINT AUTO_INCREMENT  PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    lastname VARCHAR(50) NOT NULL,
    email VARCHAR(80) NOT NULL,
    document VARCHAR(50) NOT NULL
);

DROP TABLE IF EXISTS seller;

CREATE TABLE seller (
   id BIGINT AUTO_INCREMENT  PRIMARY KEY,
   fullname VARCHAR(50) NOT NULL,
   contactemail VARCHAR(80) NOT NULL
);

DROP TABLE IF EXISTS transaction;

CREATE TABLE transaction (
    id BIGINT AUTO_INCREMENT  PRIMARY KEY,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    amount DOUBLE NOT NULL,
    fk_buyer BIGINT NOT NULL,
    fk_seller BIGINT NOT NULL,
    fk_paymenttype BIGINT NOT NULL,
    fk_transactionstatus BIGINT NOT NULL
);

DROP TABLE IF EXISTS product;

CREATE TABLE product (
    id BIGINT AUTO_INCREMENT  PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(250) NOT NULL,
    stock INT NOT NULL,
    price DOUBLE NOT NULL,
    fk_seller BIGINT NOT NULL
);

DROP TABLE IF EXISTS orderdetail;

CREATE TABLE transactiondetail (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    quantity INT NOT NULL,
    fk_transaction BIGINT NOT NULL,
    fk_product BIGINT NOT NULL
);

ALTER TABLE transaction ADD FOREIGN KEY (fk_buyer) REFERENCES buyer(id);
ALTER TABLE transaction ADD FOREIGN KEY (fk_seller) REFERENCES seller(id);
ALTER TABLE transaction ADD FOREIGN KEY (fk_paymenttype) REFERENCES paymenttype(id);
ALTER TABLE transaction ADD FOREIGN KEY (fk_transactionstatus) REFERENCES transactionstatus(id);
ALTER TABLE product ADD FOREIGN KEY (fk_seller) REFERENCES seller(id);
ALTER TABLE transactiondetail ADD FOREIGN KEY (fk_transaction) REFERENCES transaction(id);
ALTER TABLE transactiondetail ADD FOREIGN KEY (fk_product) REFERENCES product(id);