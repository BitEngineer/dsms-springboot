<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<% 
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/"; 
	%> 
	<base href="<%=basePath%>">
	<title>登录页-数据上报采集系统</title>
	<link rel="stylesheet" type="text/css" href="lib/easyui/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="lib/font-awesome/css/font-awesome.min.css"/>
	<link rel="stylesheet" type="text/css" href="assets/css/common.css"/>
	<script type="text/javascript" src="lib/jquery.min.js"></script>
	<script type="text/javascript" src="lib/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="lib/easyui/easyui-lang-zh_CN.js" ></script>
	<script type="text/javascript" src="assets/js/common.js"></script>
</head>
<style>
	body{
	/*	background-image:url(assets/img/bg.png);*/
		background-color:RGB(3,108,173);
		background-repeat:repeat-x;
	}
	.content{
		background-image:url(assets/img/login_2.png);
		background-repeat:no-repeat;
		width:960px;
		height:540px;
		margin: 0 auto;
	}
	.sub_btn{
		cursor:pointer;
		width:81px;
		height:66px;
		position:relative;
		top:29px;
		left:470px;
		border:none;
		background-image:url(assets/img/btn.png);
		background-repeat:no-repeat;
	}
</style>
<body onload="document.form.username.focus();" style="margin:0 auto;padding-top:200px">
	<div id="content">
		<div class="easyui-window1" title="" 
			minimizable="false" maximizable="false" resizable="false" collapsible="false">
			<% if (request.getParameter("error") != null) { %>
				<span class="required" style="position: relative;left: 457px;top: 128px;">
				用户名或密码错误
				</span>
			<% } %>
		<form name="form" action="base/login.do" method="POST" class="content">
			<table style="position:relative;top:134px;left:227px">
				<tr>
					<td width="50">&nbsp;&nbsp;&nbsp;</td>
					<td><input type='text' name='username' class="easyui-textbox" required="true" style="width:155px"/></td>
				</tr>
				<tr height="7">
				<td colspan="2">
				</td>
				<tr>
					<td></td>
					<td><input type='password' name='password' class="easyui-textbox" required="true" style="width:155px"></td>
				</tr>
				<tr height="7">
				<td colspan="2">
				</td>
				<tr>
				<tr>
					<td align="right"><input type="checkbox" name="remember-me"></td>
					<td><span style="font-weight:bold;color:RGB(229,254,255)">记住我</span></td>
				</tr>
				<!-- 
				<tr>
					<td><input name="submit" type="submit" value="登录"/></td>
					<td><input name="reset" type="reset" value="重置"/></td>
				</tr>
				 -->
			</table>
			<input name="submit" type="submit" class="sub_btn" value=""/>
		</form>
		</div>
	</div>
</body>
</html>