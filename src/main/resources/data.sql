/* Clients */
insert into client (ID, IDENTIFIER, NAME, SURNAME) VALUES 
(1001, '123456', 'Cliente', 'Primero'),
(1002, '123457', 'Cliente', 'Segundo'),
(1003, '123458', 'Cliente', 'Tercero'),
(1004, '123459', 'Cliente', 'Cuarto'),
(1005, '123460', 'Cliente', 'Quinto');

/* Sellers */
insert into seller (ID, IDENTIFIER, NAME, SURNAME) VALUES 
(1001, '123456', 'Vendedor', 'Primero'),
(1002, '123457', 'Vendedor', 'Segundo'),
(1003, '123458', 'Vendedor', 'Tercero'),
(1004, '123459', 'Vendedor', 'Cuarto'),
(1005, '123460', 'Vendedor', 'Quinto');

/* Products */
insert into product (ID, AMOUNT, CODE, NAME, PRICE) VALUES 
(1001, 40, 'LEN01', 'Lenovo 3300', 100.00),
(1002, 50, 'MAC01', 'Macbook', 115.00),
(1003, 60, 'DELL01', 'Dell 3030', 99.99),
(1004, 45, 'TOSH01', 'Toshiba 2020', 95.50),
(1005, 55, 'TOSH02', 'Toshiba 2021', 99.50);

/* Sales */
insert into sale (ID, AMOUNT, CODE, DATE, DATE_APPROVED, STATUS, VALUE, CLIENT_ID, PRODUCT_ID, SELLER_ID) VALUES 
(1001, 10, 'SALE01', '2020-08-05 00:00:00.000', null, 'PENDIENTE', 1000.00, 1001, 1001, 1001),
(1002, 5, 'SALE02', '2020-08-05 00:00:00.000', null, 'PENDIENTE', 575.00, 1001, 1002, 1001),
(1003, 10, 'SALE03', '2020-08-05 00:00:00.000', '2020-08-15 00:00:00.000', 'APROBADO', 999.90, 1002, 1003, 1003),
(1004, 5, 'SALE04', '2020-08-05 00:00:00.000', null, 'PENDIENTE', 477.50, 1003, 1004, 1004);









