---- Server Fine tuning
---- my.ini  [mysqld] section
---- # this adds 35% speed increase
---- innodb_flush_log_at_trx_commit=2
---- # To store big blobs
---- max_allowed_packet=200M
---- transaction-isolation = READ-COMMITTED

-- initialization
DROP USER 'smanager';
CREATE USER 'smanager' IDENTIFIED BY 'smanager';
DROP DATABASE smanager;
CREATE DATABASE smanager DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;
GRANT ALL PRIVILEGES ON smanager.* TO 'smanager'@'%';

--  if you get error:
--  ERROR 1396 (HY000) at line 3: Operation CREATE USER failed for 'smanager'@'%'
--  then run the following commands:
-- DROP USER 'smanager'@'localhost'
-- delete from mysql.user where user='smanager';
-- delete from mysql.db where user='smanager';
-- FLUSH PRIVILEGES;
