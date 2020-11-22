use flexibility
CREATE TABLE `client` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `seller` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `transaction` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `status` int DEFAULT NULL,
  `id_client` bigint DEFAULT NULL,
  `id_seller` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `key_client` (`id_client`),
  KEY `key_seller` (`id_seller`),
  CONSTRAINT `fk_transaction_client` FOREIGN KEY (`id_client`) REFERENCES `client` (`id`),
  CONSTRAINT `fk_transaction_seller` FOREIGN KEY (`id_seller`) REFERENCES `seller` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `product` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `transaction_detail` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `quantity` int DEFAULT NULL,
  `id_product` bigint DEFAULT NULL,
  `id_transaction` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `key_product` (`id_product`),
  KEY `key_transaction` (`id_transaction`),
  CONSTRAINT `fk_detail_transaction` FOREIGN KEY (`id_transaction`) REFERENCES `transaction` (`id`),
  CONSTRAINT `fk_detail_product` FOREIGN KEY (`id_product`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
