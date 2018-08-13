------码表类型表
truncate table A_CODETYPE;

insert into A_CODETYPE ( CODETYPE_ID, CODETYPE_CODE, CODETYPE_NAME, DESCRIPTION, ORDER_NO, SUPPORT_LEVEL, DELETE_FLAG,UPDATE_USER,UPDATE_TIME)
values ('1001', '1001', 'DELETE_FLAG','删除标志，1：有效，0：删除',null,null,'1','admin', now());

insert into A_CODETYPE ( CODETYPE_ID, CODETYPE_CODE, CODETYPE_NAME, DESCRIPTION, ORDER_NO, SUPPORT_LEVEL, DELETE_FLAG,UPDATE_USER,UPDATE_TIME)
values ('1002', '1002', 'USER_STATUS','用户状态：0：注销，1：正常，2：锁定',null,null,'1','admin', now());

insert into A_CODETYPE ( CODETYPE_ID, CODETYPE_CODE, CODETYPE_NAME, DESCRIPTION, ORDER_NO, SUPPORT_LEVEL, DELETE_FLAG,UPDATE_USER,UPDATE_TIME)
values ('1003', '1003', 'SEX','性别：0：男，1：女',null,null,'1','admin', now());

insert into A_CODETYPE ( CODETYPE_ID, CODETYPE_CODE, CODETYPE_NAME, DESCRIPTION, ORDER_NO, SUPPORT_LEVEL, DELETE_FLAG,UPDATE_USER,UPDATE_TIME)
values ('1004', '1004', 'USER_TYPE','用户类型：1：普通员工，2：高管',null,null,'1','admin', now());

insert into A_CODETYPE ( CODETYPE_ID, CODETYPE_CODE, CODETYPE_NAME, DESCRIPTION, ORDER_NO, SUPPORT_LEVEL, DELETE_FLAG,UPDATE_USER,UPDATE_TIME)
values ('1005', '1005', 'ORG_CLASS','机构大类：00：全行汇总，10：总行20：分行汇总，21：一级分行22：二级分行，31：一级支行32：二级支行，41：部门',null,null,'1','admin', now());

insert into A_CODETYPE ( CODETYPE_ID, CODETYPE_CODE, CODETYPE_NAME, DESCRIPTION, ORDER_NO, SUPPORT_LEVEL, DELETE_FLAG,UPDATE_USER,UPDATE_TIME)
values ('1006', '1006', 'ORG_TYPE','机构类型：00：全行汇总，10：总行20：分行汇总，21：一级分行22：二级分行，31：一级支行32：二级支行，41：一级部门42：二级部门，43：三级部门',null,null,'1','admin', now());

insert into A_CODETYPE ( CODETYPE_ID, CODETYPE_CODE, CODETYPE_NAME, DESCRIPTION, ORDER_NO, SUPPORT_LEVEL, DELETE_FLAG,UPDATE_USER,UPDATE_TIME)
values ('1007', '1007', 'ORG_LEVEL','机构级别：1：level1，2：level2',null,null,'1','admin', now());

insert into A_CODETYPE ( CODETYPE_ID, CODETYPE_CODE, CODETYPE_NAME, DESCRIPTION, ORDER_NO, SUPPORT_LEVEL, DELETE_FLAG,UPDATE_USER,UPDATE_TIME)
values ('1008', '1008', 'STATUS','状态：01：生效，02：失效',null,null,'1','admin', now());

insert into A_CODETYPE ( CODETYPE_ID, CODETYPE_CODE, CODETYPE_NAME, DESCRIPTION, ORDER_NO, SUPPORT_LEVEL, DELETE_FLAG,UPDATE_USER,UPDATE_TIME)
values ('1009', '1009', 'ROLE_TYPE','角色类型：01:填报人角色， 02:审核人角色',null,null,'1','admin', now());

insert into A_CODETYPE ( CODETYPE_ID, CODETYPE_CODE, CODETYPE_NAME, DESCRIPTION, ORDER_NO, SUPPORT_LEVEL, DELETE_FLAG,UPDATE_USER,UPDATE_TIME)
values ('1010', '1010', 'RES_TYPE','资源类型：CD：菜单，GN：按钮LJ：路径，SJ：字段集合JG：机构集合，AQ：安全策略JS：角色',null,null,'1','admin', now());

insert into A_CODETYPE ( CODETYPE_ID, CODETYPE_CODE, CODETYPE_NAME, DESCRIPTION, ORDER_NO, SUPPORT_LEVEL, DELETE_FLAG,UPDATE_USER,UPDATE_TIME)
values ('1011', '1011', 'FREQUENCY','报表频率：D-日，W-周，M-月，Q-季，Y-年，O-一次',null,null,'1','admin', now());

insert into A_CODETYPE ( CODETYPE_ID, CODETYPE_CODE, CODETYPE_NAME, DESCRIPTION, ORDER_NO, SUPPORT_LEVEL, DELETE_FLAG,UPDATE_USER,UPDATE_TIME)
values ('1012', '1012', 'START_TYPE','偏移类型：01-月初、02-月末、11-季初、12-季末',null,null,'1','admin', now());

insert into A_CODETYPE ( CODETYPE_ID, CODETYPE_CODE, CODETYPE_NAME, DESCRIPTION, ORDER_NO, SUPPORT_LEVEL, DELETE_FLAG,UPDATE_USER,UPDATE_TIME)
values ('1013', '1013', 'TYPE_WEEK','填报周：2-周一，3-周二，4-周三，5-周四，6-周五，7-周六，1-周日',null,null,'1','admin', now());

insert into A_CODETYPE ( CODETYPE_ID, CODETYPE_CODE, CODETYPE_NAME, DESCRIPTION, ORDER_NO, SUPPORT_LEVEL, DELETE_FLAG,UPDATE_USER,UPDATE_TIME)
values ('1014', '1014', 'REPORT_STATUS','报表状态：01:已发布,02:已停用',null,null,'1','admin', now());

insert into A_CODETYPE ( CODETYPE_ID, CODETYPE_CODE, CODETYPE_NAME, DESCRIPTION, ORDER_NO, SUPPORT_LEVEL, DELETE_FLAG,UPDATE_USER,UPDATE_TIME)
values ('1015', '1015', 'SUBMISSION_TYPE','上报类型：01：自主上报,02:任务推动',null,null,'1','admin', now());

insert into A_CODETYPE ( CODETYPE_ID, CODETYPE_CODE, CODETYPE_NAME, DESCRIPTION, ORDER_NO, SUPPORT_LEVEL, DELETE_FLAG,UPDATE_USER,UPDATE_TIME)
values ('1016', '1016', 'REPORT_BATCH_STATUS','报表批次状态：01上报中，,02归档',null,null,'1','admin', now());

insert into A_CODETYPE ( CODETYPE_ID, CODETYPE_CODE, CODETYPE_NAME, DESCRIPTION, ORDER_NO, SUPPORT_LEVEL, DELETE_FLAG,UPDATE_USER,UPDATE_TIME)
values ('1017', '1017', 'FLOW_NODE','流程环节：总-分-二分-支，总-分',null,null,'1','admin', now());

insert into A_CODETYPE ( CODETYPE_ID, CODETYPE_CODE, CODETYPE_NAME, DESCRIPTION, ORDER_NO, SUPPORT_LEVEL, DELETE_FLAG,UPDATE_USER,UPDATE_TIME)
values ('1018', '1018', 'M_STATUS','上报状态：00:保存 01:待审核 02:驳回填报人 03:审核中      04:驳回审核人 05:征询中 06:征询结束 07:已完成 08:已废弃',null,null,'1','admin', now());

insert into A_CODETYPE ( CODETYPE_ID, CODETYPE_CODE, CODETYPE_NAME, DESCRIPTION, ORDER_NO, SUPPORT_LEVEL, DELETE_FLAG,UPDATE_USER,UPDATE_TIME)
values ('1019', '1019', 'SUIT_TYPE','适用类型：01通用,02特殊',null,null,'1','admin', now());

insert into A_CODETYPE ( CODETYPE_ID, CODETYPE_CODE, CODETYPE_NAME, DESCRIPTION, ORDER_NO, SUPPORT_LEVEL, DELETE_FLAG,UPDATE_USER,UPDATE_TIME)
values ('1020', '1010', 'DATA_STATUS','数据状态：01:新建 02:保存 03:提交 04:已发布',null,null,'1','admin', now());

insert into A_CODETYPE ( CODETYPE_ID, CODETYPE_CODE, CODETYPE_NAME, DESCRIPTION, ORDER_NO, SUPPORT_LEVEL, DELETE_FLAG,UPDATE_USER,UPDATE_TIME)
values ('1021', '1021', 'PROCESS_TYPE','过程类型：01：填报 02：审核:03：转审:04：征询',null,null,'1','admin', now());

insert into A_CODETYPE ( CODETYPE_ID, CODETYPE_CODE, CODETYPE_NAME, DESCRIPTION, ORDER_NO, SUPPORT_LEVEL, DELETE_FLAG,UPDATE_USER,UPDATE_TIME)
values ('1022', '1022', 'ITEM_LEVEL','科目级别：01:一级，02:二级，3:三级',null,null,'1','admin', now());

insert into A_CODETYPE ( CODETYPE_ID, CODETYPE_CODE, CODETYPE_NAME, DESCRIPTION, ORDER_NO, SUPPORT_LEVEL, DELETE_FLAG,UPDATE_USER,UPDATE_TIME)
values ('1023', '1023', 'BOOLEAN','是否类型，1：是，0：否',null,null,'1','admin', now());

insert into A_CODETYPE ( CODETYPE_ID, CODETYPE_CODE, CODETYPE_NAME, DESCRIPTION, ORDER_NO, SUPPORT_LEVEL, DELETE_FLAG,UPDATE_USER,UPDATE_TIME)
values ('1024', '1024', 'IS_ONLINE','在线状态，1：在线，0：离线',null,null,'1','admin', now());

insert into A_CODETYPE ( CODETYPE_ID, CODETYPE_CODE, CODETYPE_NAME, DESCRIPTION, ORDER_NO, SUPPORT_LEVEL, DELETE_FLAG,UPDATE_USER,UPDATE_TIME)
values ('1025', '1025', 'OPER_TYPE','操作类型',null,null,'1','admin', now());

insert into A_CODETYPE ( CODETYPE_ID, CODETYPE_CODE, CODETYPE_NAME, DESCRIPTION, ORDER_NO, SUPPORT_LEVEL, DELETE_FLAG,UPDATE_USER,UPDATE_TIME)
values ('1026', '1026', 'COUNTRY_CODE','国家编码：标准国家地区编码',null,'1','1','admin', now());

insert into A_CODETYPE ( CODETYPE_ID, CODETYPE_CODE, CODETYPE_NAME, DESCRIPTION, ORDER_NO, SUPPORT_LEVEL, DELETE_FLAG,UPDATE_USER,UPDATE_TIME)
values ('1027', '1027', 'PROVINCE_CODE','省区编码：标准国家地区编码',null,'1','1','admin', now());

insert into A_CODETYPE ( CODETYPE_ID, CODETYPE_CODE, CODETYPE_NAME, DESCRIPTION, ORDER_NO, SUPPORT_LEVEL, DELETE_FLAG,UPDATE_USER,UPDATE_TIME)
values ('1028', '1028', 'CITY_CODE','市区编码：标准国家地区编码',null,'1','1','admin', now());

insert into A_CODETYPE ( CODETYPE_ID, CODETYPE_CODE, CODETYPE_NAME, DESCRIPTION, ORDER_NO, SUPPORT_LEVEL, DELETE_FLAG,UPDATE_USER,UPDATE_TIME)
values ('1029', '1029', 'COUNTY_CODE','县区编码：标准国家地区编码',null,'1','1','admin', now());

insert into A_CODETYPE ( CODETYPE_ID, CODETYPE_CODE, CODETYPE_NAME, DESCRIPTION, ORDER_NO, SUPPORT_LEVEL, DELETE_FLAG,UPDATE_USER,UPDATE_TIME)
values ('1030', '1030', 'CURRENCY','币种：156-人民币元，124-加元，036-澳大利亚元，344-香港元，392-日元，702-新加坡元，826-英镑，840-美元，978-欧元（EUR）',null,null,'1','admin', now());
commit;