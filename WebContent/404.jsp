<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
	<% 
		String path = request.getContextPath(); 
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	%>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>页面不存在-数据采集上报系统</title>
	<link rel="stylesheet" type="text/css" href="lib/easyui/themes/default/easyui.css"></link>
	<link rel="stylesheet" type="text/css" href="assets/css/error.css"></link>
	<script type="text/javascript" src="lib/jquery.min.js"></script>
	<script type="text/javascript" src="lib/easyui/jquery.easyui.min.js"></script>
</head>
<body class="easyui-layout" fit="true">
	<%-- 展示区 --%>
	<div class="error" region="center">
		<div>
			<div class="error-label">
				<h1>404！</h1>
			</div>
			<div class="error-info">
				<H2>您所访问的页面不存在了。</H2>
				<div>
					<p>可能的原因：</p>
					<p>1. 在地址栏中输入了错误的地址；</p>
					<p>2. 你点击的某个链接已过期。</p>
				</div>
				<div>
					<br/>
					<a target="_top" href="login.jsp">回到首页</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>