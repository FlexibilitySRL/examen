CREATE TABLE `clientes` (
  `id` varchar(15) COLLATE utf8_spanish_ci NOT NULL,
  `nombre` varchar(25) COLLATE utf8_spanish_ci NOT NULL,
  `apellido` varchar(25) COLLATE utf8_spanish_ci NOT NULL,
  `correo` varchar(60) COLLATE utf8_spanish_ci NOT NULL,
  `telefono` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci


CREATE TABLE `productos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `descripcion` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `precio` double NOT NULL,
  `stock` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci


CREATE TABLE `vendedores` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `usuario` varchar(15) COLLATE utf8_spanish_ci NOT NULL,
  `nombre` varchar(25) COLLATE utf8_spanish_ci NOT NULL,
  `apellido` varchar(25) COLLATE utf8_spanish_ci NOT NULL,
  `estado` varchar(1) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci


CREATE TABLE `transacciones` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_cliente` varchar(15) COLLATE utf8_spanish_ci NOT NULL,
  `id_vendedor` bigint(20) NOT NULL,
  `fecha` datetime NOT NULL,
  `estado` varchar(1) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_transacciones_cliente` (`id_cliente`),
  KEY `FK_transacciones_vendedor` (`id_vendedor`),
  CONSTRAINT `FK_transacciones_cliente` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id`),
  CONSTRAINT `FK_transacciones_vendedor` FOREIGN KEY (`id_vendedor`) REFERENCES `vendedores` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci


CREATE TABLE `items_transaccion` (
  `id_transaccion` bigint(20) NOT NULL,
  `id_producto` bigint(20) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `total` double NOT NULL,
  PRIMARY KEY (`id_transaccion`,`id_producto`),
  KEY `FK_items_transaccion_prod` (`id_producto`),
  CONSTRAINT `FK_items_transaccion` FOREIGN KEY (`id_transaccion`) REFERENCES `transacciones` (`id`),
  CONSTRAINT `FK_items_transaccion_prod` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci