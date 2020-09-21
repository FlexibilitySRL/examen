drop table if exists products;

CREATE TABLE IF NOT EXISTS `products` (
`id` int(10) unsigned NOT NULL,
  `names` varchar(45) DEFAULT NULL,
  `values` varchar(45) DEFAULT NULL,
  `reference_number` varchar(45) DEFAULT NULL
);


INSERT INTO `products` (`id`, `names`, `reference_number`, `values`) VALUES
(1, 'camilo', '12', '123123');
