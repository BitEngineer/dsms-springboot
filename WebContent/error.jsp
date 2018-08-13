<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
	<% 
		//out.clear();  
		//out = pageContext.pushBody();
		String path = request.getContextPath(); 
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		//Exception ex = (Exception) request.getAttribute("exception");
		//ex = null != ex ? ex : (Exception) request.getAttribute("Exception");
	%> 
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>出错了-数据采集上报系统</title>
	<link rel="stylesheet" type="text/css" href="assets/css/error.css"></link>
</head>
<body class="easyui-layout" fit="true">
	<%-- 展示区 --%>
	<div class="error" region="center">
		<div>
			<div class="error-label">
				<h1>Error！</h1>
			</div>
			<div class="error-info">
				<H2>您所访问的页面出错了。</H2>
				<div>
					<p>错误信息：</p>
					<%-- 
					<p><%=null != ex ? ex.getMessage() : "抱歉，错误原因暂不清楚，请联系管理员解决。"%></p>
					--%>
					<p>抱歉，错误原因暂不清楚，请联系管理员解决。</p>
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