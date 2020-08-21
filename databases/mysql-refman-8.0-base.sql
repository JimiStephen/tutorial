 
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
 
 -- 慢日志查询 --log-slow-queries[=filename]
 -- 命令查看当前MySQL在进行的线程，包括线程的状态、是否锁表等，
 -- 可以实时地查看 SQL 的执行情况，同时对一些锁表操作进行优化。
 show processlist;
 
 -- explain 
 -- type 表示表的连接类型，性能由好到差的连接类型为( system ---> const -----> eq_ref ------> ref
-- -----> ref_or_null----> index_merge ---> index_subquery -----> range -----> index ------>
-- all )

show profile for query 6;
-- 查看执行sql执行的耗时情况；
show profiles;
-- 通过 have_profiling 参数，能够看到当前MySQL是否支持profile：
select @@have_profiling;
-- 默认profiling是关闭的，可以通过set语句在Session级别开启profiling：
-- set profiling=1// profilling 开关
select @@profiling;
-- MySQL5.6提供了对SQL的跟踪trace, 
--  打开trace ， 设置格式为 JSON，并设置trace最大能够使用的内存大小，避免解析过程中因为默认内存过小而不能
-- 够完整展示。
SET optimizer_trace="enabled=on",end_markers_in_json=on;
set optimizer_trace_max_mem_size=1000000;

select * from actor where actor_id < 1000;
select * from information_schema.optimizer_trace;

-- 查看索引使用情况
show status like 'Handler_read%';
show global status like 'Handler_read%';
-- Handler_read_first：索引中第一条被读的次数。如果较高，表示服务器正执行大量全索引扫描（这个值越低
-- 越好）。
-- Handler_read_key：如果索引正在工作，这个值代表一个行被索引值读的次数，如果值越低，表示索引得到的
-- 性能改善不高，因为索引不经常使用（这个值越高越好）。
-- Handler_read_next ：按照键顺序读下一行的请求数。如果你用范围约束或如果执行索引扫描来查询索引列，
-- 该值增加。
-- Handler_read_prev：按照键顺序读前一行的请求数。该读方法主要用于优化ORDER BY ... DESC。
-- Handler_read_rnd ：根据固定位置读一行的请求数。如果你正执行大量查询并需要对结果进行排序该值较高。
-- 你可能使用了大量需要MySQL扫描整个表的查询或你的连接没有正确使用键。这个值较高，意味着运行效率低，应
-- 该建立索引来补救。
-- Handler_read_rnd_next：在数据文件中读下一行的请求数。如果你正进行大量的表扫描，该值较高。通常说
-- 明你的表索引不正确或写入的查询没有利用索引。

-- 批量插入优化方法
-- 主键顺序插入
-- 关闭唯一校验  set UNIQUE_CHECKS=0;
-- 手动提交事务  set autocommit=0; set autocommit=1;
-- 忽略索引 
explain select * from tb_seller ignore index(idx_seller_name) where name = '小米科
技';
-- 使用某个索引
explain select * from tb_seller use index(idx_seller_name) where name = '小米科
技';
-- 强制使用索引 
explain select * from tb_seller force index(idx_seller_name) where name = '小米科
技';
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