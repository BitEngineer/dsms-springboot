<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<% 
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/"; 
	%> 
	<base href="<%=basePath%>">
	<title>代码类型维护</title>
	<link rel="stylesheet" type="text/css" href="lib/easyui/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="lib/font-awesome/css/font-awesome.min.css"/>
	<link rel="stylesheet" type="text/css" href="assets/css/common.css"/>
	<script type="text/javascript" src="lib/jquery.min.js"></script>
	<script type="text/javascript" src="lib/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="lib/easyui/easyui-lang-zh_CN.js" ></script>
	<script type="text/javascript" src="lib/jquery.noty.packaged.min.js" ></script>
	<script type="text/javascript" src="assets/js/common.js"></script>
	<script type="text/javascript" src="assets/js/app/codetype.js"></script>
</head>
<body class="easyui-layout" fit="true" style="margin:5px;">
	<div region="north" id="searchHead">
		<div>
			类型代码：<input id="sCodetypeCode" class="easyui-textbox" validType="length[0,32]"/>
		</div>
		<div>
			类型名称：<input id="sCodetypeName" class="easyui-textbox" validType="length[0,64]"/>
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
			<div class="toolbarDiv">
				<a name="add" class="easyui-linkbutton" plain="true"><i class="fa fa-plus"></i>新增</a>
				<a name="edit" class="easyui-linkbutton"plain="true"><i class="fa fa-edit"></i>编辑</a>
				<a name="del" class="easyui-linkbutton" plain="true"><i class="fa fa-trash"></i>删除</a>
				<a name="unDel" class="easyui-linkbutton" plain="true"><i class="fa fa-reply"></i>恢复</a>
			</div>
			<a name="maintain" class="easyui-linkbutton" plain="true"><i class="fa fa-wrench"></i>字典维护</a>
		</div>
		<table id="dataTable" class="easyui-datagrid" ></table>
	</div>
	
	<div id="editWin" class="easyui-dialog" data-options="closed:true, buttons:'#buttons', modal:true">
		<form id="form">
			<table>
				<tr>
					<td><span class="required"> * </span>类型编码：</td>
					<td><input id="codetypeCode" class="easyui-textbox" validType="length[1,32]" required="true"/></td>
				</tr>
				<tr>
					<td><span class="required"> * </span>类型名称：</td>
					<td><input id="codetypeName" class="easyui-textbox" validType="length[1,64]" required="true"/></td>
				</tr>
				<tr>
					<td>排序号：</td>
					<td><input id="orderNo" class="easyui-numberbox" validType="length[1,8]" /></td>
				</tr>
				<tr>
					<td>是否支持层级：</td>
					<td><select class="easyui-combobox" id="supportLevel"></td>
				</tr>
				<tr>
					<td>报表描述：</td>
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
  