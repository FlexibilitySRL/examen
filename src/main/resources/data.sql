INSERT INTO persons (person_type, name, surname, email, create_at) VALUES('CLIENT', 'Cristian', 'Romero', 'cromero@outlook.com', '2020-08-25');
INSERT INTO persons (person_type, name, surname, email, create_at) VALUES('CLIENT', 'Carlos', 'Duty', 'carlosduty@outlook.com', '2018-05-15');
INSERT INTO persons (person_type, name, surname, email, create_at) VALUES('SELLER', 'Pedro', 'Gomez', 'pedro@ventas.com', '2010-01-01');
INSERT INTO persons (person_type, name, surname, email, create_at) VALUES('SELLER', 'La casa de los productos caros', '', 'lacasa@productoscaros.com', '2012-03-30');


INSERT INTO products (name, price, create_at) VALUES('Monitor LCD', 28999, NOW());
INSERT INTO products (name, price, create_at) VALUES('Bicicleta', 45999, NOW());
INSERT INTO products (name, price, create_at) VALUES('Teclado', 580, NOW());
INSERT INTO products (name, price, create_at) VALUES('Camara Go Pro', 60000, NOW());
INSERT INTO products (name, price, create_at) VALUES('Equipo de Musica', 32500, NOW());
INSERT INTO products (name, price, create_at) VALUES('Libro', 350, NOW());
INSERT INTO products (name, price, create_at) VALUES('Kit de herramientas', 9500, NOW());
INSERT INTO products (name, price, create_at) VALUES('Mochila', 1999, NOW());

INSERT INTO purchases (description, client_id, seller_id, status, create_at) VALUES('Orden de compra 1', 1, 3, 'PENDING', NOW());
INSERT INTO purchase_items (quantity, purchase_id, product_id) VALUES(3, 1, 1);
INSERT INTO purchase_items (quantity, purchase_id, product_id) VALUES(1, 1, 2);
INSERT INTO purchase_items (quantity, purchase_id, product_id) VALUES(1, 1, 5);

INSERT INTO purchases (description, client_id, seller_id, status, create_at) VALUES('Orden de compra 2', 1, 4, 'PENDING', NOW());
INSERT INTO purchase_items (quantity, purchase_id, product_id) VALUES(1, 2, 6);
INSERT INTO purchase_items (quantity, purchase_id, product_id) VALUES(1, 2, 8);

INSERT INTO purchases (description, client_id, seller_id, status, create_at) VALUES('Orden de compra 3', 1, 3, 'PENDING', NOW());
INSERT INTO purchase_items (quantity, purchase_id, product_id) VALUES(1, 3, 3);
INSERT INTO purchase_items (quantity, purchase_id, product_id) VALUES(2, 3, 4);

INSERT INTO purchases (description, client_id, seller_id, status, create_at) VALUES('Orden de compra 1', 2, 4, 'PENDING', NOW());
INSERT INTO purchase_items (quantity, purchase_id, product_id) VALUES(2, 4, 7);
