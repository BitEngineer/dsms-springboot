<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<% 
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/"; 
	%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>用户管理</title> 
	<base href="<%=basePath%>">
	<link rel="stylesheet" type="text/css" href="lib/easyui/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="lib/font-awesome/css/font-awesome.min.css"/>
	<link rel="stylesheet" type="text/css" href="lib/easyui/themes/icon.css"/>
	<link rel="stylesheet" type="text/css" href="assets/css/common.css"/>
	<script type="text/javascript" src="lib/jquery.min.js"></script>
	<script type="text/javascript" src="lib/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="lib/easyui/easyui-lang-zh_CN.js" ></script>
	<script type="text/javascript" src="lib/jquery.noty.packaged.min.js" ></script>
	<script type="text/javascript" src="assets/js/common.js"></script>
	<script type="text/javascript" src="assets/js/app/user.js"></script>
</head>
<body  class="easyui-layout" fit="true" style="margin:5px;">
	<!-- 主页面 -->
	<div region="north" id="searchHead">
		<div>
			用户编号：<input id="sStaffCode" class="easyui-textbox" />
		</div>
		<div>
			用户名称：<input id="sStaffName" class="easyui-textbox" />
		</div>
		<div>
			用户状态：<select id="sStatus" class="easyui-combobox" panelHeight="auto"></select>
		</div>
		<div>
			删除标志：<select id="sDeleteFlag" class="easyui-combobox" panelHeight="auto"></select>
		</div>
		<div>
			所属机构：<input id="sOrgName" class="easyui-textbox" editable="false" data-options="buttonText:'...',prompt:'选择机构...'" >
		</div>
		<div>
			<a class="easyui-linkbutton" name="search">查询</a>
			<a class="easyui-linkbutton" name="reset">重置</a>
		</div>		
	</div>
	<div region="center" border=false>
		<div id="toolbar">
			<!--  
			<a name="add" text="新增" class="easyui-linkbutton" iconCls="icon-add" plain="true"></a>
			<a name="edit" text="编辑" class="easyui-linkbutton" iconCls="icon-edit" plain="true"></a>
			<a name="modifyPwd" text="修改密码" class="easyui-linkbutton" iconCls="icon-edit" plain="true"></a>
			<a name="del" text="删除" class="easyui-linkbutton" iconCls="icon-remove" plain="true"></a>
			<a name="unDel" text="恢复" class="easyui-linkbutton" iconCls="icon-undo" plain="true"></a>
			<span class="datagrid-btn-separator" style="vertical-align: middle; height: 15px;display:inline-block;float:none"></span> 
			<a name="lock" text="锁定" class="easyui-linkbutton" iconCls="icon-ok" plain="true"></a>
			<a name="unlock" text="解锁" class="easyui-linkbutton" iconCls="icon-no" plain="true"></a>
			<span class="datagrid-btn-separator" style="vertical-align: middle; height: 15px;display:inline-block;float:none"></span>
			<a name="manageRole" text="角色管理" class="easyui-linkbutton" iconCls="icon-maintain" plain="true"></a>
			<span class="datagrid-btn-separator" style="vertical-align: middle; height: 15px;display:inline-block;float:none"></span>
			<a name="selectRights" text="查看权限" class="easyui-linkbutton" iconCls="icon-search" plain="true"></a>
			-->
			<a name="add" class="easyui-linkbutton" plain="true"><i class="fa fa-plus"></i>新增</a>
			<a name="edit" class="easyui-linkbutton" plain="true"><i class="fa fa-edit"></i>编辑</a>
			<a name="resetPwd" class="easyui-linkbutton" plain="true"><i class="fa fa-reply"></i>重置密码</a>
			<a name="del" class="easyui-linkbutton" plain="true"><i class="fa fa-trash"></i>删除</a>
			<a name="unDel" class="easyui-linkbutton" plain="true"><i class="fa fa-reply"></i>恢复</a>
			<a name="lock" class="easyui-linkbutton"  plain="true"><i class="fa fa-lock"></i>锁定</a>
			<a name="unlock" class="easyui-linkbutton" plain="true"><i class="fa fa-unlock"></i>解锁</a>
			<a name="manageRole" class="easyui-linkbutton" plain="true"><i class="fa fa-wrench"></i>角色管理</a>
			<a name="selectRights" class="easyui-linkbutton" plain="true"><i class="fa fa-search"></i>查看权限</a>
		</div>
		<table id="dataTable" class="easyui-datagrid" ></table>
	</div>
	
	
	<!-- 新增和编辑弹窗 -->
	<div id="editWin" class="easyui-dialog" data-options="closed:true, buttons:'#editButtons', modal:true">
		<form id="form">
			<table>
				<tr>
					<td><input id="staffId"  style="display:none;"/></td>
				</tr>
				<tr>
					<td><span class="required"> * </span>用户编号：</td>
					<td><input id="staffCode" class="easyui-textbox"  required="true"/></td>
				</tr>
				<tr>
					<td><span class="required"> * </span>用户名称：</td>
					<td><input id="staffName" class="easyui-textbox"  required="true"/></td>
				</tr>
				<tr>
					<td><span class="required"> * </span>登录名称：</td>
					<td><input id="loginName" class="easyui-textbox"  required="true"/></td>
				</tr>
				<tr>
					<td><span class="required"> * </span>用户类型：</td>
					<td>
						<select id="userType" class="easyui-combobox" panelHeight="auto"  required="true">
							<!-- 在js中设置 -->
							<!--   
    						<option value="1">普通员工</option>   
    						<option value="2">高管</option>
    						-->    
						</select> 
					</td>
				</tr>
				<tr>
					<td><span class="required"> * </span>所属机构：</td>
					<td><input id="orgName" class="easyui-textbox" editable="false" data-options="buttonText:'...',prompt:'选择机构...'" required="true">
					</td>
				</tr>
				<tr>
					<td>性别：</td>
					<td>
						<select id="sex" class="easyui-combobox" panelHeight="auto" >
							<!-- 在js中设置 -->
							<!--   
    						<option value="0">男</option>   
    						<option value="1">女</option>
    						--> 
						</select>  
					</td>
				</tr>
				<tr>
					<td>出生日期：</td>
					<td><input id="birthday" class="easyui-datebox"  editable="false" value="today()"/></td>
				</tr>
				<tr>
					<td>身份证号：</td>
					<td><input id="idCardNo" class="easyui-textbox" /></td>
				</tr>
				<tr>
					<td>电话号码：</td>
					<td><input id="phone" class="easyui-textbox" /></td>	
				</tr>
				<tr>
					<td>邮件：</td>
					<td><input id="email" class="easyui-textbox"  /></td>
				</tr>
				<tr>
					<td>地址：</td>
					<td><input id="address" class="easyui-textbox" /></td>
				</tr>
			</table>	
			<div id="editButtons">
				<a class="easyui-linkbutton" name="saveEditWin">保存</a>
				<a class="easyui-linkbutton" name="resetEditWin">重置</a>
			</div>	
		</form>		
	</div>
	
	<!-- 搜索栏机构选择弹窗 -->
	<div id="searchOrgWin" class="easyui-dialog" data-options="closed:true, buttons:'#searchOrgButtons', modal:true"
		style="width:350px;height:450px;">
		<div>
			<input id="sSearchTree" class="easyui-searchbox" style="width:100%"/>
		</div>
		<div>
			<ul id="searchOrgTree" class="easyui-tree" data-options="lines:true, animate:true"></ul>
		</div>
		<div id="searchOrgButtons">
			<a class="easyui-linkbutton" name="confirmWin">确定</a>
		</div>		
	</div>
	
	<!-- 编辑和新增机构选择弹窗 -->
	<div id="editOrgWin" class="easyui-dialog" data-options="closed:true, buttons:'#editOrgButtons', modal:true"
		style="width:350px;height:450px;">
		<div>
			<input id="searchTree" class="easyui-searchbox" style="width:100%"/>
		</div>
		<div>
			<ul id="searchOrgTree" class="easyui-tree" data-options="lines:true, animate:true"></ul>
			<ul id="editOrgTree" class="easyui-tree" data-options="lines:true, animate:true"></ul>
		</div>
		<div id="editOrgButtons">
			<a class="easyui-linkbutton" name="confirmWin">确定</a>
		</div>			
	</div>
	
	<!-- 角色管理弹窗 -->
	<div id="roleWin" class="easyui-dialog" data-options="closed:true, buttons:'#roleButtons', modal:true">
		<div class="easyui-layout" style="width:800px;height:350px;">
			<div data-options="region:'west'" style="width:370px;">
				<div data-options="region:'north'" style="height:30px" id="searchRole">
					<input id="sRoles" class="easyui-searchbox" prompt="选择角色" style="width:100%"></input>
				</div>
				<div style="height:317px;">
					<table id="unusedRoleDatatable" class="easyui-datagrid" 
						align="center" rownumbers="true" fitColumn="true" 
						singleSelect="false" checkbox="true">
					</table>
				</div>
			</div>
			<div data-options="region:'east'" style="width:370px;">
				<div data-options="region:'north'" style="height:30px">
					<a>&nbsp;</a>
				</div>
				<table id="usedRoleDatatable" class="easyui-datagrid" 
					align="center" rownumbers="true" fitColumn="true" 
					singleSelect="false" checkbox="true">
				</table>
			</div>
			<div data-options="region:'center'" >
				<div style="height:150px"></div>
				<div id="lineButtons" style="text-align:center;">
					<a class="easyui-linkbutton" name="addWin">添加&gt;&gt;</a>
					<a>&nbsp;</a>
					<a class="easyui-linkbutton" name="delWin" >删除&lt;&lt;</a>
				</div>
			</div>
			<div id="roleButtons" >
				<a class="easyui-linkbutton" name="saveRoleWin">确定</a>
				<a class="easyui-linkbutton" name="cancelRoleWin">取消</a>
			</div>	
		</div>
	</div>
	
	<!-- 权限查看弹窗 -->
	<div id="rightsWin" class="easyui-dialog" data-options="closed:true, buttons:'#rightButtons', modal:true"
		style="width:300px;height:400px;">
		<table id="rightsDatatable" class="easyui-datagrid"></table>
		<div id="rightButtons">
			<a class="easyui-linkbutton" name="confirmRightsWin">确定</a>
		</div>
	</div>
</body>
</html>