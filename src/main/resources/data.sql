INSERT INTO CUSTOMER (id,nombre,estado,creation_date) VALUES(1,'ALPINA','A',NOW());
INSERT INTO CUSTOMER (id,nombre,estado,creation_date) VALUES(2,'COLANTA','A',NOW());
INSERT INTO CUSTOMER (id,nombre,estado,creation_date) VALUES(3,'SAMSUM','A',NOW());

INSERT INTO PRODUCT (id,nombre,estado,creation_date) VALUES(1,'COMPUTADOR','A',NOW());
INSERT INTO PRODUCT (id,nombre,estado,creation_date) VALUES(2,'LAMPARA','A',NOW());
INSERT INTO PRODUCT (id,nombre,estado,creation_date) VALUES(3,'IMPRESORA','A',NOW());


INSERT INTO purchase (id,id_compra,unidad,precio,id_product, estado_transaccion) VALUES(1,1,2,2500,1,'PENDIG');
INSERT INTO purchase (id,id_compra,unidad,precio,id_product, estado_transaccion) VALUES(2,2,6,6500,1,'PENDIG');
