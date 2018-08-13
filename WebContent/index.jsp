<%@page import="com.lonton.dsms.base.vo.Staff"%>
<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<% 
		String path = request.getContextPath(); 
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
	%> 
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>首页-数据采集上报系统</title>
	<link rel="stylesheet" type="text/css" href="lib/easyui/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="lib/easyui/themes/icon.css" />
	<link rel="stylesheet" type="text/css" href="assets/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="assets/css/manage.css"/>
	<script type="text/javascript" src="lib/jquery.min.js"></script>
	<script type="text/javascript" src="lib/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="lib/easyui/easyui-lang-zh_CN.js" ></script>
	<script type="text/javascript" src="lib/jquery.noty.packaged.min.js" ></script>
	<script type="text/javascript" src="assets/js/common.js"></script>
	<script type="text/javascript" src="assets/js/manage.js"></script>
	<script type="text/javascript" src="assets/js/index.js"></script>
</head>
<body class="easyui-layout" fit="true">
	<%-- 标题栏区 --%>
	<div class="navbar" region="north" >
		<div class="navbar-container">
			<span class="navbar-title">数据采集上报系统</span>
			<div class="navbar-info">
				<span class="navbar-user">您好</span>
				<span id="modifyPwd" style="cursor:pointer;text-decoration:underline">修改密码</span>
				<a href="logout.do">退出</a>
			</div>
		</div>
	</div>
	<%-- 菜单树区 --%>
	<div id="menu" region="west" title="菜单树" split="true" style="width:210px;height:100%;overflow:hidden;padding-bottom:20px">
		<div style="text-align:center;width:100%">
			<input id="searchTree" style="width:98%"/>
		</div>
		<div style="overflow-x:hidden;overflow-y:auto;height:100%;width:100%;">
			<ul id="menuTree" class="easyui-tree" data-options="lines:true, animate:true"></ul>
		</div>
	</div>
	<%-- 展示区 --%>
	<div region="center">
		<div id="displayArea" class="easyui-tabs" border="false" style="height:100%;"></div>
	</div>
	<%-- 密码修改弹窗 --%>
	<div id="pwdWin" class="easyui-dialog" data-options="closed:true, buttons:'#modifyPwdButtons', modal:true">
		<form id="pwdForm">
			<table>
				<tr>
					<td><span class="required"> * </span>原密码：</td>
					<td><input id="oldPwd" type="password" class="easyui-textbox" required="true"/></td>
				</tr>
				<tr>
					<td><span class="required"> * </span>新密码：</td>
					<td><input id="newPwd" type="password" class="easyui-textbox" required="true"/></td>
				</tr>
				<tr>
					<td><span class="required"> * </span>确认密码：</td>
					<td><input id="confirmPwd" type="password" class="easyui-textbox"  required="true"/></td>
				</tr>
			</table>	
			<div id="modifyPwdButtons">
				<a class="easyui-linkbutton" name="savePwdWin">确定</a>
				<a class="easyui-linkbutton" name="resetPwdWin">重置</a>
			</div>	
		</form>
	</div>	
	<div class="footer" region="south" style="height:18px;">
		<span>©Since 2014 Lonton</span>
	</div>
</body>
</html>