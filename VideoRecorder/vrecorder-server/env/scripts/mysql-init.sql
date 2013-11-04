---- Server Fine tuning
---- my.ini  [mysqld] section
---- # this adds 35% speed increase
---- innodb_flush_log_at_trx_commit=2
---- # To store big blobs
---- max_allowed_packet=200M
---- transaction-isolation = READ-COMMITTED

-- initialization
DROP USER 'vrecorder';
CREATE USER 'vrecorder' IDENTIFIED BY 'vrecorder';
DROP DATABASE vrecorder;
CREATE DATABASE vrecorder DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;
GRANT ALL PRIVILEGES ON vrecorder.* TO 'vrecorder'@'%';

--  if you get error:
--  ERROR 1396 (HY000) at line 3: Operation CREATE USER failed for 'vrecorder'@'%'
--  then run the following commands:
-- DROP USER 'vrecorder'@'localhost'
-- delete from mysql.user where user='vrecorder';
-- delete from mysql.db where user='vrecorder';
-- FLUSH PRIVILEGES;
