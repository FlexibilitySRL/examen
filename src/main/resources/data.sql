INSERT INTO products VALUES (1, 'Coca-Cola', 'A cold icy Coca-cola', 37.5, 100);
INSERT INTO products VALUES (2, 'Pepsi', 'A cold icy Pepsi', 30.5, 100);
INSERT INTO products VALUES (3, 'Halls', 'Keep a fresh mouth', 15, 1000);
INSERT INTO products VALUES (4, 'Portillo Malbec 2019', 'Generic Argentinian Wine', 149.99, 50);
INSERT INTO products VALUES (5, 'Casillero del Diablo Cabernet Sauvignon', 'Generic Chilean Wine', 299.99, 50);

INSERT INTO sellers(id, first_name, last_name, commission_rate) VALUES (1, 'Peter', 'Parker', 12);
INSERT INTO sellers(id, first_name, last_name, commission_rate) VALUES (2, 'Bruce', 'Wayne', 30);
INSERT INTO sellers(id, first_name, last_name, commission_rate) VALUES (3, 'Tony', 'Stark', 30);
INSERT INTO sellers(id, first_name, last_name, commission_rate) VALUES (4, 'Thor', 'Odinson', 10);
INSERT INTO sellers(id, first_name, last_name, commission_rate) VALUES (5, 'Natasha', 'Romanov', 8.5);

INSERT INTO clients(id, first_name, last_name, address, seller_id) VALUES (1, 'Amador', 'Cuenca', 'Fake Street 123', 1);
