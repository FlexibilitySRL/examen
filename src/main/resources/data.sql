
insert into CLIENTE (ID, APELLIDO, DIRECCION, DOCUMENTO, FECHA_NACIMIENTO, NOMBRE, TELEFONO) values (1,	'López',	'Los Alamos 123', 	'22145112',	'1985-08-07',	'Raul',	'0343444895');
insert into CLIENTE (ID, APELLIDO, DIRECCION, DOCUMENTO, FECHA_NACIMIENTO, NOMBRE, TELEFONO) values (2,	'López',	'Los Alamos 598', 	'25020155',	'1980-03-07',	'María','0343789456');
insert into CLIENTE (ID, APELLIDO, DIRECCION, DOCUMENTO, FECHA_NACIMIENTO, NOMBRE, TELEFONO) values (3,	'Bianchi',	'Los Eucaliptus 56', 	'39145444',	'1990-08-01',	'Alexa',	'34312395');

insert into PRODUCTO (ID, NOMBRE, PRECIO_UNITARIO) values (1, 'yerba', 15);
insert into PRODUCTO (ID, NOMBRE, PRECIO_UNITARIO) values (2, 'azucar', 26);
insert into PRODUCTO (ID, NOMBRE, PRECIO_UNITARIO) values (3, 'termo', 544);
insert into PRODUCTO (ID, NOMBRE, PRECIO_UNITARIO) values (4, 'mate', 154);

insert into FACTURA (ID, ESTADO, FECHA, CLIENTE) values (1, 'En espera de aprobación', '2020-03-25', 1);

insert into DETALLE_FACTURA (ID, CANTIDAD, FACTURA, PRODUCTO) values (1, 3, 1, 1);
insert into DETALLE_FACTURA (ID, CANTIDAD, FACTURA, PRODUCTO) values (2, 5, 1, 2);
insert into DETALLE_FACTURA (ID, CANTIDAD, FACTURA, PRODUCTO) values (3, 10, 1, 3);
