<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<% 
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/"; 
	%> 
	<base href="<%=basePath%>">
	<title>资源配置</title>
	<link rel="stylesheet" type="text/css" href="lib/easyui/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="lib/font-awesome/css/font-awesome.min.css"/>
	<link rel="stylesheet" type="text/css" href="assets/css/common.css"/>
	<script type="text/javascript" src="lib/jquery.min.js"></script>
	<script type="text/javascript" src="lib/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="lib/easyui/easyui-lang-zh_CN.js" ></script>
	<script type="text/javascript" src="lib/jquery.noty.packaged.min.js" ></script>
	<script type="text/javascript" src="assets/js/common.js"></script>
	<script type="text/javascript" src="assets/js/app/res.js"></script>
</head>
<body class="easyui-layout" fit="true" style="margin:5px;">
	<div id="menu" region="west" title="资源树" split="true" 
		style="width:17%;height:700px;overflow:hidden;padding-bottom:20px">
		<div style="text-align:center;width:100%">
			<input id="searchTree" style="width:98%"/>
		</div>
		<div style="height:100%;overflow-x:hidden;overflow-y:auto;">
			<ul id="resTree" class="easyui-tree" data-options="animate:true,lines:true"></ul>
		</div>
	</div>
	<div region="center" border="false" split="true">
		<!-- 选项卡区 -->
		<div class="easyui-tabs" fit="true">
			<div title="资源">
				<div id="resToolbar">
					<a name="add" class="easyui-linkbutton" plain="true"><i class="fa fa-plus"></i>新增</a>
					<a name="edit" class="easyui-linkbutton"plain="true"><i class="fa fa-edit"></i>编辑</a>
					<a name="del" class="easyui-linkbutton" plain="true"><i class="fa fa-trash"></i>删除</a>
					<a name="unDel" class="easyui-linkbutton" plain="true"><i class="fa fa-reply"></i>恢复</a>
				</div>
				<table id="resTable" class="easyui-datagrid" ></table>
			</div>
			<div title="URL">
				<div id="urlToolbar">
					<a name="relateUrl" class="easyui-linkbutton" plain="true"><i class="fa fa-link"></i>关联URL</a>
				</div>
				<table id="urlTable" class="easyui-datagrid" ></table>
			</div>
			<div title="角色">
				<div id="roleToolbar">
					<a name="relateRole" class="easyui-linkbutton" plain="true"><i class="fa fa-users"></i>分配角色</a>
				</div>
				<table id="roleTable" class="easyui-datagrid" ></table>
			</div>
		</div>
	</div>

	<div id="editWin" class="easyui-dialog" data-options="closed:true, buttons:'#buttons', modal:true">
		<form id="form">
			<table>
				<tr>
					<td><span class="required"> * </span>资源编号：</td>
					<td><input id="resCode" class="easyui-textbox" validType="length[1,32]" required="true"/></td>
				</tr>
				<tr>
					<td><span class="required"> * </span>资源名称：</td>
					<td><input id="resName" class="easyui-textbox" validType="length[1,64]" required="true"/></td>
				</tr>
				<tr>
					<td><span class="required"> * </span>资源类型：</td>
					<td><input id="resType" class="easyui-combobox" required="true"/></td>
				</tr>
				<tr>
					<td>资源URL：</td>
					<td><input id="resUrl" class="easyui-textbox" validType="length[1,255]"/></td>
				</tr>
				<tr>
					<td>是否相对路径：</td>
					<td><input id="relative" class="easyui-combobox" /></td>
				</tr>
				<tr>
					<td>资源图片：</td>
					<td><input id="resIcon" class="easyui-textbox" validType="length[1,255]"/></td>
				</tr>
				<tr>
					<td>排序号：</td>
					<td><input id="orderNo" class="easyui-numberbox" validType="length[1,8]" /></td>
				</tr>
				<tr>
					<td>是否可见：</td>
					<td><select class="easyui-combobox" id="visible"></td>
				</tr>
			</table>	
			<div id="buttons">
				<a class="easyui-linkbutton" name="saveWin">保存</a>
				<a class="easyui-linkbutton" name="resetWin">重置</a>
			</div>	
		</form>		
	</div>
	
	<div id="relateUrl" title="关联URL" iconCls="fa fa-link" class="easyui-dialog" data-options="
		closed:true, buttons:'#buttonsUrl', modal:true">
		<table>
			<tr>
				<td>
					<div id="searchUrlHead">
						<input id="sUrlPath" class="easyui-searchbox" prompt="输入URL，回车搜索" style="width:100%"></input>
					</div>
					<table id="dataTableUrlLeft" class="easyui-datagrid" 
						fit="false"
						style="width:400px;height:400px"
						halign="center" rownumbers="true" singleSelect="false" checkbox="true"></table>
				</td>
				<td>
					<div>
						<a class="easyui-linkbutton" name="chooseUrl">添加--></a>
					</div>
					
					<div>
						<a class="easyui-linkbutton" name="delUrl" ><--移除</a>
					</div>
				</td>
				<td>
					<table id="dataTableUrlRight" class="easyui-datagrid" 
						fit="false"
						style="width:300px;height:100%"
						halign="center" rownumbers="true" fitColumns="true" singleSelect="false" checkbox="true"></table>
				</td>
			</tr>
		</table>
			
		<div id="buttonsUrl" >
			<a class="easyui-linkbutton" name="saveUrlWin">确定</a>
			<a class="easyui-linkbutton" name="resetUrlWin">重置</a>
		</div>	
	</div>
	
	<div id="relateRole" title="分配角色" iconCls="fa fa-link" class="easyui-dialog" 
		data-options="closed:true, buttons:'#buttonsRole', modal:true">
		<table>
			<tr>
				<td>
					<div id="searchRoleHead">
						<input id="sRoleName" class="easyui-searchbox" prompt="输入角色名称，回车搜索" style="width:100%"></input>
					</div>
					<table id="dataTableRoleLeft" class="easyui-datagrid" 
						fit="false"
						style="width:400px;height:400px"
						halign="center" rownumbers="true" singleSelect="false" checkbox="true"></table>
				</td>
				<td>
					<div>
						<a class="easyui-linkbutton" name="chooseRole">添加--></a>
					</div>
					
					<div>
						<a class="easyui-linkbutton" name="delRole"><--移除</a>
					</div>
				</td>
				<td>
					<table id="dataTableRoleRight" class="easyui-datagrid" 
						fit="false"
						style="width:300px;height:100%"
						halign="center" rownumbers="true" fitColumns="true" singleSelect="false" checkbox="true"></table>
				</td>
			</tr>
		</table>
		
		<div id="buttonsRole" >
			<a class="easyui-linkbutton" name="saveRoleWin">确定</a>
			<a class="easyui-linkbutton" name="resetRoleWin">重置</a>
		</div>	
	</div>
</body>
</html>