------码表条目信息表

truncate table meta.A_CODEINFO;

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1001', '1', '有效', '1001', '删除标志', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1002', '0', '删除', '1001', '删除标志', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1003', '0', '注销', '1002', '用户状态', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1004', '1', '正常', '1002', '用户状态', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1005', '2', '锁定', '1002', '用户状态', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1006', '0', '男', '1003', '性别', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1007', '1', '女', '1003', '性别', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1008', '1', '普通员工', '1004', '用户类型', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1009', '2', '高管', '1004', '用户类型', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1010', '00', '全行汇总', '1005', '机构大类', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1011', '10', '总行', '1005', '机构大类', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1012', '20', '分行汇总', '1005', '机构大类', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1013', '21', '一级分行', '1005', '机构大类', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1014', '22', '二级分行', '1005', '机构大类', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1015', '31', '一级支行', '1005', '机构大类', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1016', '32', '二级支行', '1005', '机构大类', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1017', '41', '部门', '1005', '机构大类', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1018', '00', '全行汇总', '1006', '机构类型', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1019', '10', '总行', '1006', '机构类型', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1020', '20', '分行汇总', '1006', '机构类型', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1021', '21', '一级分行', '1006', '机构类型', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1022', '22', '二级分行', '1006', '机构类型', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1023', '31', '一级支行', '1006', '机构类型', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1024', '32', '二级支行', '1006', '机构类型', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1025', '41', '一级部门', '1006', '机构类型', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1026', '42', '二级部门', '1006', '机构类型', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1027', '43', '三级部门', '1006', '机构类型', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1028', '1', 'level1', '1007', '机构级别', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1029', '2', 'level2', '1007', '机构级别', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1030', '01', '生效', '1008', '状态', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1031', '02', '失效', '1008', '状态', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1032', '01', '填报人', '1009', '角色类型', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1033', '02', '审核人', '1009', '角色类型', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1034', 'CD', '菜单', '1010', '资源类型', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1035', 'CN', '按钮', '1010', '资源类型', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1036', 'LJ', '路径', '1010', '资源类型', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1037', 'SJ', '字段集合', '1010', '资源类型', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1038', 'JG', '机构集合', '1010', '资源类型', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1039', 'AQ', '安全策略', '1010', '资源类型', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1040', 'JS', '角色', '1010', '资源类型', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1041', 'D', '日', '1011', '报表频率', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1042', 'W', '周', '1011', '报表频率', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1043', 'M', '月', '1011', '报表频率', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1044', 'Q', '季', '1011', '报表频率', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1045', 'Y', '年', '1011', '报表频率', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1046', 'O', '一次', '1011', '报表频率', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1047', '01', '月初', '1012', '开始偏移类型', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1048', '02', '月末', '1012', '开始偏移类型', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1049', '11', '季初', '1012', '开始偏移类型', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1050', '12', '季末', '1012', '开始偏移类型', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1051', '2', '周一', '1013', '开始填报周', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1052', '3', '周二', '1013', '开始填报周', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1053', '4', '周三', '1013', '开始填报周', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1054', '5', '周四', '1013', '开始填报周', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1055', '6', '周五', '1013', '开始填报周', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1056', '7', '周六', '1013', '开始填报周', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1057', '1', '周日', '1013', '开始填报周', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1058', '01', '已发布', '1014', '报表状态', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1059', '02', '已停用', '1014', '报表状态', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1060', '01', '自主上报', '1015', '上报类型', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1061', '02', '任务推动', '1015', '上报类型', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1062', '01', '上报中', '1016', '报表批次状态', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1063', '02', '归档', '1016', '报表批次状态', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1064', '01', '总-分-二分-支', '1017', '流程环节', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1065', '02', '总-分', '1017', '流程环节', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1066', '00', '保存', '1018', '上报状态', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1067', '01', '待审核', '1018', '上报状态', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1068', '02', '驳回填报人', '1018', '上报状态', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1069', '03', '审核中', '1018', '上报状态', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1070', '04', '驳回审核人', '1018', '上报状态', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1071', '05', '征询中', '1018', '上报状态', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1072', '06', '征询结束', '1018', '上报状态', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1073', '07', '已完成', '1018', '上报状态', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1074', '08', '已废弃', '1018', '上报状态', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1075', '01', '通用', '1019', '适用类型', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1076', '02', '特殊', '1019', '适用类型', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1077', '01', '新建', '1020', '数据状态', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1078', '02', '保存', '1020', '数据状态', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1079', '03', '提交', '1020', '数据状态', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1080', '04', '已发布', '1020', '数据状态', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1081', '01', '填报', '1021', '过程类型', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1082', '02', '审核', '1021', '过程类型', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1083', '03', '转审', '1021', '过程类型', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1084', '04', '征询', '1021', '过程类型', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1085', '01', '一级', '1022', '科目级别', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1086', '02', '二级', '1022', '科目级别', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1087', '03', '三级', '1022', '科目级别', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1088', '1', '是', '1023', '直属地区', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1089', '0', '否', '1023', '直属地区', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1090', '1', '在线', '1024', '用户在线状态', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1091', '0', '离线', '1024', '用户在线状态', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1092', '156', '人民币元', '1030', '人命币元', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1093', '124', '加元', '1030', '加元', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1094', '036', '澳大利亚元', '1030', '澳大利亚元', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1095', '344', '香港元', '1030', '香港元', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1096', '392', '日元', '1030', '日元', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1097', '702', '新加坡元', '1030', '新加坡元', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1098', '826', '英镑', '1030', '英镑', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1099', '840', '美元', '1030', '美元', null, null, null, null, null, '1', '1', 'admin', now());

insert into META.A_CODEINFO (CODEINFO_ID, CODEINFO_CODE, CODEINFO_VALUE, CODETYPE_ID, DESCRIPTION, ORDER_NO, ROOT_ID, PARENT_ID, CODE_LEVEL, TREE_PATH, VISIBLE, DELETE_FLAG, UPDATE_USER, UPDATE_TIME)
values ('1100', '978', '欧元', '1030', '欧元', null, null, null, null, null, '1', '1', 'admin', now());

commit;
