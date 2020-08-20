 
 ## 这个也是注释的一种方式
 -- 查看当前的版本
 select version();
-- 查看 INNODB引擎的状态
SHOW ENGINE INNODB STATUS ;
 -- 获取命令的帮助
 help 'select';
 help 'show';
 -- 查看当前数据库现在有那些表
 show tables;
 show tables from sakila like 'actor';
  show tables from INFORMATION_SCHEMA;
 SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA='sakila';
-- show table status from 'sakila';
--  查看数据库视图
select * from INFORMATION_SCHEMA.VIEWS;
 -- 查看当前用户下的数据库
 show databases;
 select database();
 -- sys_config
 SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA;
 -- SHOW TABLE INDEX;
 SELECT * FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_NAME='actor' AND TABLE_SCHEMA='sakila';
 SHOW INDEX from sakila.actor;
 -- 获取表的列信息
 describe actor;
 show create table actor;
 show columns from actor;
 select * from customer limit 10;
 select column_name,data_type,is_nullable,column_default from INFORMATION_SCHEMA.COLUMNS WHERE table_name='actor';
 -- 查看 触发器alter
 SELECT * FROM INFORMATION_SCHEMA.TRIGGERS WHERE TRIGGER_SCHEMA='sakila' and EVENT_OBJECT_TABLE='actor';
 SHOW triggers like '%film%';
 -- table privileges
 show grants;
 select * from INFORMATION_SCHEMA.COLUMN_PRIVILEGES;
 -- USER PRIVILIEGES 
 select * from INFORMATION_SCHEMA.USER_PRIVILEGES;
 -- 查看服务器参数设置情况
 show variables like '%schema%';
 --
 show create view sys.session;
 
 -- 查看支持的编码类型
 select * from INFORMATION_SCHEMA.CHARACTER_SETS;
 SHOW CHARACTER SET;
 -- 查看 collation 
 select * from INFORMATION_SCHEMA.COLLATIONS;
 SHOW COLLATION;
 -- MYSQL tablespace data is stored
SELECT FILE_ID,FILE_NAME,FILE_TYPE,TABLESPACE_NAME,FREE_EXTENTS,TOTAL_EXTENTS,
EXTENT_SIZE,INITIAL_SIZE,MAXIMUM_SIZE,AUTOEXTEND_SIZE,DATA_FREE,STATUS,ENGINE
FROM INFORMATION_SCHEMA.FILES;
-- 查询INFORMATION_SCHEMA
SELECT * FROM INFORMATION_SCHEMA.KEYWORDS;
SELECT * FROM INFORMATION_SCHEMA.PARTITIONS;
SELECT PLUGIN_NAME,PLUGIN_STATUS,PLUGIN_TYPE,
PLUGIN_LIBRARY,PLUGIN_LICENSE 
FROM INFORMATION_SCHEMA.PLUGINS;
--  查看插件
SHOW PLUGINS;

-- engines
show engines ;
 -- 创建数据库
 CREATE DATABASE IF NOT EXISTS chronus  CHARACTER SET 'utf8'
 COLLATE 'utf8_general_ci' ;
 commit;
 SELECT @@character_set_database, @@collation_database;
 show char set like 'utf%';
 -- user
 select  * from zk_cluster_info;
 -- 默认 是 session 级别 [seesion|global]
show status like 'Com_______';
show status like 'Innodb_rows_%'
 
 
--  CREATE
-- [DEFINER = { user | CURRENT_USER }]
-- EVENT
--  [IF NOT EXISTS]
-- event_name
-- ON SCHEDULE schedule
-- [ON COMPLETION [NOT] PRESERVE]
-- [ENABLE | DISABLE | DISABLE ON SLAVE]
-- [COMMENT 'string']
-- DO event_body;
-- schedule:
-- AT timestamp [+ INTERVAL interval] ...
-- | EVERY interval
-- [STARTS timestamp [+ INTERVAL interval] ...]
-- [ENDS timestamp [+ INTERVAL interval] ...]
-- interval:
-- quantity {YEAR | QUARTER | MONTH | DAY | HOUR | MINUTE |
-- WEEK | SECOND | YEAR_MONTH | DAY_HOUR | DAY_MINUTE |
-- DAY_SECOND | HOUR_MINUTE | HOUR_SECOND | MINUTE_SECOND}