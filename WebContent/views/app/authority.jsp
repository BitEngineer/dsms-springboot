<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<% 
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/"; 
		//JSP页面的异常	
		//text/html controller中的异常经过了SimpleMappingExceptionResolver,但JSP页面的异常没有经过
		/*
		basePath = null;
		basePath.split(",");
		*/
	%> 
	<base href="<%=basePath%>">
	<title>资源配置</title>
	<link rel="stylesheet" type="text/css" href="lib/easyui/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="assets/css/common.css"/>
	<script type="text/javascript" src="lib/jquery.min.js"></script>
	<script type="text/javascript" src="lib/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="lib/easyui/easyui-lang-zh_CN.js" ></script>
	<script type="text/javascript" src="lib/jquery.noty.packaged.min.js" ></script>
	<script type="text/javascript" src="assets/js/common.js"></script>
	<script type="text/javascript" src="assets/js/app/authority.js"></script>
</head>
<body class="easyui-layout" fit="true" style="margin:5px;">
	<div region="center" border="false" split="true">
		<!-- 选项卡区 -->
		<div class="easyui-tabs" fit="true">
			<div title="角色资源关联">
				<%-- easyui layout 对于easyui tabs的某个tab应该放在这,否则内部的datagrid会有问题 --%>
				<div class="easyui-layout" fit="true">
					<div id="roleSearchHead" region="north" class="searchHead">
						<div>
							角色编号：<input id="sRoleId" class="easyui-textbox"/>
						</div>
						<div>
							角色名称：<input id="sRoleName" class="easyui-textbox"/>
						</div>
						<div>
							<a id="searchRoleBtn" class="easyui-linkbutton">查询</a>
							<a id="resetRoleBtn" class="easyui-linkbutton">重置</a>
						</div>	
					</div>
					<div region="center">
						<div id="roleToolbar">
							<a id="searchResBtn" class="easyui-linkbutton" 
								data-options="iconCls:'icon-search', plain:'false'">查看关联资源</a>
							<a id="relateResBtn" class="easyui-linkbutton" 
								data-options="iconCls:'icon-add', plain:'false'">关联资源</a>
						</div>
						<table id="roleTable" class="easyui-datagrid"></table>
					</div>
				</div>
			</div>
			<div title="用户角色关联">
				<div id="resToolbar">
					<a name="relateRole" class="easyui-linkbutton" plain="true"><i class="fa fa-users"></i>分配角色</a>
				</div>
				<table id="resTable" class="easyui-datagrid"></table>
			</div>
		</div>
	</div>
	
	<%-- 角色关联资源:查看窗口 --%>
	<div id="resSearchWin" class="easyui-dialog" style="width:200px;height:300px;"
		data-options="closed:true, buttons:'#searchOrgButtons', modal:true">
		<ul id="resSearchTree" class="easyui-tree"></ul>
	</div>
	
	<%-- 角色关联资源:关联窗口 --%>
	<div id="resRelateWin" class="easyui-dialog" style="width:200px;height:300px;"
		data-options="closed:true, buttons:'#searchOrgButtons', modal:true">
		<ul id="resRelateTree" class="easyui-tree"></ul>
	</div>
	
</body>
</html>