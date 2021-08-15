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

DROP TABLE IF EXISTS user;

CREATE TABLE user (
    id BIGINT AUTO_INCREMENT  PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    lastname VARCHAR(50) NOT NULL,
    email VARCHAR(80) NOT NULL,
    document VARCHAR(50) NOT NULL
);

DROP TABLE IF EXISTS transaction;

CREATE TABLE transaction (
    id BIGINT AUTO_INCREMENT  PRIMARY KEY,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    amount DOUBLE NOT NULL,
    fk_userbuyer BIGINT NOT NULL,
    fk_userseller BIGINT NOT NULL,
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
    fk_userowner BIGINT NOT NULL
);

DROP TABLE IF EXISTS orderdetail;

CREATE TABLE transactiondetail (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    quantity INT NOT NULL,
    fk_transaction BIGINT NOT NULL,
    fk_product BIGINT NOT NULL
);

ALTER TABLE transaction ADD FOREIGN KEY (fk_userbuyer) REFERENCES user(id);
ALTER TABLE transaction ADD FOREIGN KEY (fk_userseller) REFERENCES user(id);
ALTER TABLE transaction ADD FOREIGN KEY (fk_paymenttype) REFERENCES paymenttype(id);
ALTER TABLE transaction ADD FOREIGN KEY (fk_transactionstatus) REFERENCES transactionstatus(id);
ALTER TABLE product ADD FOREIGN KEY (fk_userowner) REFERENCES user(id);
ALTER TABLE transactiondetail ADD FOREIGN KEY (fk_transaction) REFERENCES transaction(id);
ALTER TABLE transactiondetail ADD FOREIGN KEY (fk_product) REFERENCES product(id);