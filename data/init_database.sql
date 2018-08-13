--测试表空间和测试用户
create tablespace test_data datafile '/oracle/test.dbf' size 2G autoextend off extent management local segment space management auto;
create temporary tablespace test_temp tempfile '/oracle/test_temp01.dbf' size 2G autoextend off extent management local;
create user test identified by test default tablespace test_data temporary tablespace test_temp;
grant connect, resource, create view ,unlimited tablespace to test;


--创建meta表空间
--create tablespace meta_data datafile '/oracle/meta.dbf' size 2G autoextend off extent management local segment space management auto;
--创建data表空间
--create tablespace data_data datafile '/oracle/data.dbf' size 2G autoextend off extent management local segment space management auto;
--创建meta、source、work、report用户
create user meta identified by meta default tablespace test_data temporary tablespace test_temp;
create user source identified by source default tablespace test_data temporary tablespace test_temp;
create user work identified by work default tablespace test_data temporary tablespace test_temp;
create user report identified by report default tablespace test_data temporary tablespace test_temp;

--授权，meta用户权限大
grant connect,resource,unlimited tablespace,alter any procedure,alter any trigger,alter any table,comment any table,analyze any,create any directory,create any trigger,create any index,create any procedure,create any table,create any type,create any view,debug connect session,delete any table,drop any procedure,drop any table,drop any type,drop any trigger,drop any view,execute any procedure,execute any type,global query rewrite,insert any table,resumable,select any dictionary,select any sequence,create any sequence,select any table,select any transaction,update any table to meta ;
grant connect, resource, create view ,unlimited tablespace to source;
grant connect, resource, create view ,unlimited tablespace to work;
grant connect, resource, create view ,unlimited tablespace to report;
