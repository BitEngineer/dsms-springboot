truncate table A_RES;
insert into a_res (RES_ID, RES_CODE, RES_NAME, RES_TYPE, RES_URL, RELATIVE, PARENT_ID, TREE_PATH, RES_ORDER, RES_ICON, AVAILABLE, VISIBLE, UPDATE_USER, UPDATE_TIME)
values ('cd000', null, '系统菜单', 'CD', null, '1', null, null, null, null, '1', '1', 'admin', now());
insert into a_res (RES_ID, RES_CODE, RES_NAME, RES_TYPE, RES_URL, RELATIVE, PARENT_ID, TREE_PATH, RES_ORDER, RES_ICON, AVAILABLE, VISIBLE, UPDATE_USER, UPDATE_TIME)
values ('cd001', null, '系统管理', 'CD', null, '1', 'cd000', null, null, null, '1', '1', 'admin', now());
insert into a_res (RES_ID, RES_CODE, RES_NAME, RES_TYPE, RES_URL, RELATIVE, PARENT_ID, TREE_PATH, RES_ORDER, RES_ICON, AVAILABLE, VISIBLE, UPDATE_USER, UPDATE_TIME)
values ('cd002', null, '资源管理', 'CD', 'views/app/res.jsp', '1', 'cd001', null, null, null, '1', '1', 'admin', now());
insert into a_res (RES_ID, RES_CODE, RES_NAME, RES_TYPE, RES_URL, RELATIVE, PARENT_ID, TREE_PATH, RES_ORDER, RES_ICON, AVAILABLE, VISIBLE, UPDATE_USER, UPDATE_TIME)
values ('cd003', null, '字典管理', 'CD', 'views/app/codeinfo.jsp', '1', 'cd001', null, null, null, '1', '1', 'admin', now());
insert into a_res (RES_ID, RES_CODE, RES_NAME, RES_TYPE, RES_URL, RELATIVE, PARENT_ID, TREE_PATH, RES_ORDER, RES_ICON, AVAILABLE, VISIBLE, UPDATE_USER, UPDATE_TIME)
values ('cd004', null, '字典类型管理', 'CD', 'views/app/codetype.jsp', '1', 'cd001', null, null, null, '1', '1', 'admin', now());
insert into a_res (RES_ID, RES_CODE, RES_NAME, RES_TYPE, RES_URL, RELATIVE, PARENT_ID, TREE_PATH, RES_ORDER, RES_ICON, AVAILABLE, VISIBLE, UPDATE_USER, UPDATE_TIME)
values ('cd005', null, '科目管理', 'CD', 'views/meta/system/kmMgr.jsp', '1', 'cd001', null, null, null, '1', '1', 'admin', now());
insert into a_res (RES_ID, RES_CODE, RES_NAME, RES_TYPE, RES_URL, RELATIVE, PARENT_ID, TREE_PATH, RES_ORDER, RES_ICON, AVAILABLE, VISIBLE, UPDATE_USER, UPDATE_TIME)
values ('cd006', null, '白名单管理', 'CD', 'views/meta/system/bmdMgr.jsp', '1', 'cd001', null, null, null, '1', '1', 'admin', now());
insert into a_res (RES_ID, RES_CODE, RES_NAME, RES_TYPE, RES_URL, RELATIVE, PARENT_ID, TREE_PATH, RES_ORDER, RES_ICON, AVAILABLE, VISIBLE, UPDATE_USER, UPDATE_TIME)
values ('cd007', null, '权限管理', 'CD', null, '1', 'cd000', null, null, null, '1', '1', 'admin', now());
insert into a_res (RES_ID, RES_CODE, RES_NAME, RES_TYPE, RES_URL, RELATIVE, PARENT_ID, TREE_PATH, RES_ORDER, RES_ICON, AVAILABLE, VISIBLE, UPDATE_USER, UPDATE_TIME)
values ('cd008', null, '用户管理', 'CD', 'views/app/user.jsp', '1', 'cd007', null, null, null, '1', '1', 'admin', now());
insert into a_res (RES_ID, RES_CODE, RES_NAME, RES_TYPE, RES_URL, RELATIVE, PARENT_ID, TREE_PATH, RES_ORDER, RES_ICON, AVAILABLE, VISIBLE, UPDATE_USER, UPDATE_TIME)
values ('cd009', null, '机构管理', 'CD', 'views/app/org.jsp', '1', 'cd007', null, null, null, '1', '1', 'admin', now());
insert into a_res (RES_ID, RES_CODE, RES_NAME, RES_TYPE, RES_URL, RELATIVE, PARENT_ID, TREE_PATH, RES_ORDER, RES_ICON, AVAILABLE, VISIBLE, UPDATE_USER, UPDATE_TIME)
values ('cd010', null, '角色管理', 'CD', 'views/app/role.jsp', '1', 'cd007', null, null, null, '1', '1', 'admin', now());

insert into a_res (RES_ID, RES_CODE, RES_NAME, RES_TYPE, RES_URL, RELATIVE, PARENT_ID, TREE_PATH, RES_ORDER, RES_ICON, AVAILABLE, VISIBLE, UPDATE_USER, UPDATE_TIME)
values ('cd036', null, '授权管理', 'CD', 'views/app/authority.jsp', '1', 'cd007', null, null, null, '1', '1', 'admin', now());


insert into a_res (RES_ID, RES_CODE, RES_NAME, RES_TYPE, RES_URL, RELATIVE, PARENT_ID, TREE_PATH, RES_ORDER, RES_ICON, AVAILABLE, VISIBLE, UPDATE_USER, UPDATE_TIME)
values ('cd011', null, '上报管理', 'CD', null, '1', 'cd000', null, null, null, '1', '1', 'admin', now());
insert into a_res (RES_ID, RES_CODE, RES_NAME, RES_TYPE, RES_URL, RELATIVE, PARENT_ID, TREE_PATH, RES_ORDER, RES_ICON, AVAILABLE, VISIBLE, UPDATE_USER, UPDATE_TIME)
values ('cd012', null, '填报批次管理', 'CD', 'views/meta/flow/mgr/bbMgr.jsp', '1', 'cd011', null, null, null, '1', '1', 'admin', now());
insert into a_res (RES_ID, RES_CODE, RES_NAME, RES_TYPE, RES_URL, RELATIVE, PARENT_ID, TREE_PATH, RES_ORDER, RES_ICON, AVAILABLE, VISIBLE, UPDATE_USER, UPDATE_TIME)
values ('cd013', null, '流程管理', 'CD', 'views/meta/flow/mgr/proMgr.jsp', '1', 'cd011', null, null, null, '1', '1', 'admin', now());
insert into a_res (RES_ID, RES_CODE, RES_NAME, RES_TYPE, RES_URL, RELATIVE, PARENT_ID, TREE_PATH, RES_ORDER, RES_ICON, AVAILABLE, VISIBLE, UPDATE_USER, UPDATE_TIME)
values ('cd014', null, '报表管理', 'CD', null, '1', 'cd000', null, null, null, '1', '1', 'admin', now());
insert into a_res (RES_ID, RES_CODE, RES_NAME, RES_TYPE, RES_URL, RELATIVE, PARENT_ID, TREE_PATH, RES_ORDER, RES_ICON, AVAILABLE, VISIBLE, UPDATE_USER, UPDATE_TIME)
values ('cd015', null, '报表管理', 'CD', 'views/designer/report.jsp', '1', 'cd014', null, null, null, '1', '1', 'admin', now());
insert into a_res (RES_ID, RES_CODE, RES_NAME, RES_TYPE, RES_URL, RELATIVE, PARENT_ID, TREE_PATH, RES_ORDER, RES_ICON, AVAILABLE, VISIBLE, UPDATE_USER, UPDATE_TIME)
values ('cd017', null, '上报报表', 'CD', null, '1', 'cd000', null, null, null, '1', '1', 'admin', now());
insert into a_res (RES_ID, RES_CODE, RES_NAME, RES_TYPE, RES_URL, RELATIVE, PARENT_ID, TREE_PATH, RES_ORDER, RES_ICON, AVAILABLE, VISIBLE, UPDATE_USER, UPDATE_TIME)
values ('cd018', null, '科目类', 'CD', null, '1', 'cd017', null, null, null, '1', '1', 'admin', now());
insert into a_res (RES_ID, RES_CODE, RES_NAME, RES_TYPE, RES_URL, RELATIVE, PARENT_ID, TREE_PATH, RES_ORDER, RES_ICON, AVAILABLE, VISIBLE, UPDATE_USER, UPDATE_TIME)
values ('cd019', null, '账户类', 'CD', null, '1', 'cd017', null, null, null, '1', '1', 'admin', now());
insert into a_res (RES_ID, RES_CODE, RES_NAME, RES_TYPE, RES_URL, RELATIVE, PARENT_ID, TREE_PATH, RES_ORDER, RES_ICON, AVAILABLE, VISIBLE, UPDATE_USER, UPDATE_TIME)
values ('cd020', null, '损益类', 'CD', null, '1', 'cd017', null, null, null, '1', '1', 'admin', now());
insert into a_res (RES_ID, RES_CODE, RES_NAME, RES_TYPE, RES_URL, RELATIVE, PARENT_ID, TREE_PATH, RES_ORDER, RES_ICON, AVAILABLE, VISIBLE, UPDATE_USER, UPDATE_TIME)
values ('cd021', null, '其他', 'CD', null, '1', 'cd017', null, null, null, '1', '1', 'admin', now());
insert into a_res (RES_ID, RES_CODE, RES_NAME, RES_TYPE, RES_URL, RELATIVE, PARENT_ID, TREE_PATH, RES_ORDER, RES_ICON, AVAILABLE, VISIBLE, UPDATE_USER, UPDATE_TIME)
values ('cd022', null, '通用功能', 'CD', null, '1', 'cd000', null, null, null, '1', '1', 'admin', now());
insert into a_res (RES_ID, RES_CODE, RES_NAME, RES_TYPE, RES_URL, RELATIVE, PARENT_ID, TREE_PATH, RES_ORDER, RES_ICON, AVAILABLE, VISIBLE, UPDATE_USER, UPDATE_TIME)
values ('cd023', null, '委托', 'CD', 'views/meta/general/turnTrial.jsp', '1', 'cd022', null, null, null, '1', '1', 'admin', now());
insert into a_res (RES_ID, RES_CODE, RES_NAME, RES_TYPE, RES_URL, RELATIVE, PARENT_ID, TREE_PATH, RES_ORDER, RES_ICON, AVAILABLE, VISIBLE, UPDATE_USER, UPDATE_TIME)
values ('cd024', null, '上报历史', 'CD', 'views/meta/general/reportHistory.jsp', '1', 'cd022', null, null, null, '1', '1', 'admin', now());
insert into a_res (RES_ID, RES_CODE, RES_NAME, RES_TYPE, RES_URL, RELATIVE, PARENT_ID, TREE_PATH, RES_ORDER, RES_ICON, AVAILABLE, VISIBLE, UPDATE_USER, UPDATE_TIME)
values ('cd025', null, '我的任务', 'CD', 'views/meta/flow/run/main.jsp', '1', 'cd022', null, null, null, '1', '1', 'admin', now());
insert into a_res (RES_ID, RES_CODE, RES_NAME, RES_TYPE, RES_URL, RELATIVE, PARENT_ID, TREE_PATH, RES_ORDER, RES_ICON, AVAILABLE, VISIBLE, UPDATE_USER, UPDATE_TIME)
values ('cd026', null, '损益类科目板块分成比例', 'CD', 'views/meta/flow/run/batch.jsp?reportId=report001', '1', 'cd020', null, null, null, '1', '1', 'admin', now());
insert into a_res (RES_ID, RES_CODE, RES_NAME, RES_TYPE, RES_URL, RELATIVE, PARENT_ID, TREE_PATH, RES_ORDER, RES_ICON, AVAILABLE, VISIBLE, UPDATE_USER, UPDATE_TIME)
values ('cd027', null, '资产负债类科目板块分成比例', 'CD', 'views/meta/flow/run/batch.jsp?reportId=report002', '1', 'cd018', null, null, null, '1', '1', 'admin', now());
insert into a_res (RES_ID, RES_CODE, RES_NAME, RES_TYPE, RES_URL, RELATIVE, PARENT_ID, TREE_PATH, RES_ORDER, RES_ICON, AVAILABLE, VISIBLE, UPDATE_USER, UPDATE_TIME)
values ('cd028', null, '部门人数补录表', 'CD', 'views/meta/flow/run/batch.jsp?reportId=report003', '1', 'cd021', null, null, null, '1', '1', 'admin', now());
insert into a_res (RES_ID, RES_CODE, RES_NAME, RES_TYPE, RES_URL, RELATIVE, PARENT_ID, TREE_PATH, RES_ORDER, RES_ICON, AVAILABLE, VISIBLE, UPDATE_USER, UPDATE_TIME)
values ('cd029', null, '客户经理补录表', 'CD', 'views/meta/flow/run/batch.jsp?reportId=report004', '1', 'cd021', null, null, null, '1', '1', 'admin', now());
insert into a_res (RES_ID, RES_CODE, RES_NAME, RES_TYPE, RES_URL, RELATIVE, PARENT_ID, TREE_PATH, RES_ORDER, RES_ICON, AVAILABLE, VISIBLE, UPDATE_USER, UPDATE_TIME)
values ('cd030', null, '账户级项目补录', 'CD', 'views/meta/flow/run/batch.jsp?reportId=report005', '1', 'cd020', null, null, null, '1', '1', 'admin', now());
insert into a_res (RES_ID, RES_CODE, RES_NAME, RES_TYPE, RES_URL, RELATIVE, PARENT_ID, TREE_PATH, RES_ORDER, RES_ICON, AVAILABLE, VISIBLE, UPDATE_USER, UPDATE_TIME)
values ('cd031', null, '账户级板块分成比例', 'CD', 'views/meta/flow/run/batch.jsp?reportId=report006', '1', 'cd019', null, null, null, '1', '1', 'admin', now());
insert into a_res (RES_ID, RES_CODE, RES_NAME, RES_TYPE, RES_URL, RELATIVE, PARENT_ID, TREE_PATH, RES_ORDER, RES_ICON, AVAILABLE, VISIBLE, UPDATE_USER, UPDATE_TIME)
values ('cd032', null, '科目级的费率补录', 'CD', 'views/meta/flow/run/batch.jsp?reportId=report007', '1', 'cd018', null, null, null, '1', '1', 'admin', now());
insert into a_res (RES_ID, RES_CODE, RES_NAME, RES_TYPE, RES_URL, RELATIVE, PARENT_ID, TREE_PATH, RES_ORDER, RES_ICON, AVAILABLE, VISIBLE, UPDATE_USER, UPDATE_TIME)
values ('cd033', null, '损益类利润预测表', 'CD', 'views/meta/flow/run/batch.jsp?reportId=report008', '1', 'cd020', null, null, null, '1', '1', 'admin', now());
insert into a_res (RES_ID, RES_CODE, RES_NAME, RES_TYPE, RES_URL, RELATIVE, PARENT_ID, TREE_PATH, RES_ORDER, RES_ICON, AVAILABLE, VISIBLE, UPDATE_USER, UPDATE_TIME)
values ('cd034', null, '账户级资产负债补录表', 'CD', 'views/meta/flow/run/batch.jsp?reportId=report009', '1', 'cd019', null, null, null, '1', '1', 'admin', now());
insert into a_res (RES_ID, RES_CODE, RES_NAME, RES_TYPE, RES_URL, RELATIVE, PARENT_ID, TREE_PATH, RES_ORDER, RES_ICON, AVAILABLE, VISIBLE, UPDATE_USER, UPDATE_TIME)
values ('cd035', null, '中收流水补录表', 'CD', 'views/meta/flow/run/batch.jsp?reportId=report010', '1', 'cd019', null, null, null, '1', '1', 'admin', now());
commit;
