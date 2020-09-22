drop table if exists clients;

CREATE TABLE IF NOT EXISTS `clients` (
`id` int(10) unsigned NOT NULL,
  `names` varchar(60) NOT NULL,
  `identification` varchar(45) NOT NULL,
  `cellphone` varchar(45) NOT NULL,
  `country` varchar(45) NOT NULL,
  `enterprise` varchar(45) NOT NULL
);

INSERT INTO `clients` (`id`, `names`, `identification`, `cellphone`, `country`, `enterprise`) VALUES
(1, 'Daniel Correa', '123123123', '9999999', 'colombia', 'presto'),
(2, 'Daniel Correa', '123123123', '9999999', 'colombia', 'presto');


