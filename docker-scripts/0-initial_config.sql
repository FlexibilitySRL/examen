ALTER USER 'root'@'localhost' IDENTIFIED BY 'password';
CREATE DATABASE flexibility;
CREATE USER 'flexibility'@'%' IDENTIFIED BY 'flexibility';
GRANT ALL PRIVILEGES ON *.* TO 'flexibility'@'%'; FLUSH PRIVILEGES;
SET GLOBAL event_scheduler = ON;
SET GLOBAL log_bin_trust_function_creators = 1;
SET GLOBAL sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));
SET GLOBAL group_concat_max_len=18446744073709551615;
