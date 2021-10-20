INSERT INTO payments.clientes (id,nombre,apellido,correo,telefono) VALUES
	 ('1129578649','Carlos','Lozano','clozanoarroyo@gmail.com','3016680868'),
	 ('32632074','Belky2','Arroyo2','barroyo@gmail.com','3002526634'),
	 ('32632074666','Belky2','Arroyo2','barroyo@gmail.com','3002526634'),
	 ('32632075','Erika','Lamby','elamby@gmail.com','3007788843'),
	 ('75246310','Andrea','Sanchez','asanchez@gmail.com','3002826534');


INSERT INTO payments.productos (nombre,descripcion,precio,stock) VALUES
	 ('Televisor','Tv 51 pulgadas, 4k',150000.0,20),
	 ('Nevera','No frost, 160 litros',200000.0,15),
	 ('Licuadora nueva gen','5 velocidades, vaso de vidrio',100000.0,28),
	 ('Sanduchera','Sanduchera color negro',120000.0,22);

INSERT INTO payments.vendedores (usuario,nombre,apellido,estado) VALUES
	 ('chernandez','Cindy','Hernandez','A'),
	 ('kpolo','Karen','Polo','A'),
	 ('jvillareal2','Jorge','Villareal','I'),
	 ('mvergara','Marcela','Vergara','A'),
	 ('ksarria','Kevin','Sarria','I');

INSERT INTO payments.transacciones (id_cliente,id_vendedor,fecha,estado) VALUES
	 ('1129578649',2,'2021-10-17 17:46:12.0','A'),
	 ('1129578649',2,'2021-10-18 12:25:24.0','A'),
	 ('32632074',1,'2021-10-18 12:30:02.0','A'),
	 ('32632074',1,'2021-10-18 13:24:01.0','A'),
	 ('32632074',2,'2021-10-18 13:46:32.0','P'),
	 ('1129578649',2,'2021-10-19 17:17:59.0','P');

INSERT INTO payments.items_transaccion (id_transaccion,id_producto,cantidad,total) VALUES
	 (1,2,3,600000.0),
	 (32,3,3,300000.0),
	 (33,1,4,600000.0),
	 (34,1,4,600000.0),
	 (36,2,2,333333.0),
	 (37,1,4,600000.0);