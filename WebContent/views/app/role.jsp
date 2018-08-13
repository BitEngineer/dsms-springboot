<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<% 
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/"; 
	%> 
	<base href="<%=basePath%>">
	<title>角色管理</title>
	<link rel="stylesheet" type="text/css" href="lib/easyui/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="lib/font-awesome/css/font-awesome.min.css"/>
	<link rel="stylesheet" type="text/css" href="assets/css/common.css"/>
	<script type="text/javascript" src="lib/jquery.min.js"></script>
	<script type="text/javascript" src="lib/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="lib/easyui/easyui-lang-zh_CN.js" ></script>
	<script type="text/javascript" src="lib/jquery.noty.packaged.min.js" ></script>
	<script type="text/javascript" src="assets/js/common.js"></script>
	<script type="text/javascript" src="assets/js/app/role.js"></script>
</head>
<body class="easyui-layout" fit="true" style="margin:5px;">
	<div region="north" id="searchHead">
		<div>
			角色名称：<input id="sRoleName" class="easyui-textbox" validType="length[1,64]"/>
		</div>
		<div>
			删除标志：<select class="easyui-combobox" id="sDeleteFlag"></select>
		</div>
		<div>
			<a class="easyui-linkbutton" name="search">查询</a>
			<a class="easyui-linkbutton" name="reset">重置</a>
		</div>		
	</div>
	<div region="center" border=false>
		<div id="toolbar">
			<a name="add" class="easyui-linkbutton" plain="true"><i class="fa fa-plus"></i>新增</a>
			<a name="edit" class="easyui-linkbutton"plain="true"><i class="fa fa-edit"></i>编辑</a>
			<a name="del" class="easyui-linkbutton" plain="true"><i class="fa fa-trash"></i>删除</a>
			<a name="unDel" class="easyui-linkbutton" plain="true"><i class="fa fa-reply"></i>恢复</a>
		</div>
		<table id="dataTable" class="easyui-datagrid" ></table>
	</div>
	
	<div id="editWin" class="easyui-dialog" data-options="closed:true, buttons:'#buttons', modal:true">
		<form id="form">
			<table>
				<tr>
					<td><span class="required"> * </span>角色名称：</td>
					<td><input id="roleName" class="easyui-textbox" validType="length[1,32]" required="true"/></td>
				</tr>
				<tr>
					<td>机构大类：</td>
					<td><select class="easyui-combobox" id="orgClass"></select></td>
				</tr>
				<tr>
					<td>角色类型：</td>
					<td><select class="easyui-combobox" id="ext1"></select></td>
				</tr>
				<tr>
					<td>角色描述：</td>
					<td><input id="description" class="easyui-textbox" validType="length[1,255]" multiline="true" style="height:100px"/></td>
				</tr>
			</table>	
			<div id="buttons">
				<a class="easyui-linkbutton" name="saveWin">保存</a>
				<a class="easyui-linkbutton" name="resetWin" >重置</a>
			</div>	
		</form>		
	</div> 
</body>
</html>
  