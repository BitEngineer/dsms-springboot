<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<% 
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/"; 
%> 
<base href="<%=basePath%>">
	<title>机构管理</title>
	<link rel="stylesheet" type="text/css" href="lib/easyui/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="lib/font-awesome/css/font-awesome.min.css"/>
	<link rel="stylesheet" type="text/css" href="lib/easyui/themes/icon.css"/>
	<link rel="stylesheet" type="text/css" href="assets/css/common.css"/>
	<script type="text/javascript" src="lib/jquery.min.js"></script>
	<script type="text/javascript" src="lib/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="lib/easyui/easyui-lang-zh_CN.js" ></script>
	<script type="text/javascript" src="lib/jquery.noty.packaged.min.js" ></script>
	<script type="text/javascript" src="assets/js/common.js"></script>
	<script type="text/javascript" src="assets/js/app/org.js"></script>
	
	<style type="text/css">
	#staffSearchHead{
	height: auto !important;
	margin-bottom:5px;
	padding:5px 0px;
    }
     #staffSearchHead div{
	display:inline-block;
	}
   #dataTable{
     height:300px important;
   }
	</style>
</head>
<body style="margin:5px;">
	<!-- 主页面 -->
	<div class="easyui-layout" fit="true">
	    <!-- 机构树区 -->
		<div id="menu" region="west" title="机构" split="true" style="width:20%;height:700px;overflow:hidden;padding-bottom:20px">
			<div style="text-align:center;width:100%">
				<input id="searchTree" class="easyui-searchbox" style="width:100%"/>
			</div>
			<div style="overflow-x:hidden;overflow-y:auto;height:100%;width:100%;">
				<ul id="orgTree" class="easyui-tree" data-options="lines:true, animate:true"></ul>
			</div>
		</div>
		<!-- 机构树区,完 -->
		<!-- 展示区 -->
		<div region="center" border=false style="height:70%;width:80%;">	
	    	<!-- 选项卡区 -->
			<div  class="easyui-tabs" fit="true">
				<!-- 机构 -->
				<div title="机构" style="padding:0px;" class="easyui-layout" fit="true">
					<div region="north" id="searchHead" style="border:1px #95B8E7 solid;" >
						<div>
							机构编号：<input id="sOrgCode" class="easyui-textbox" />
						</div>
						<div>
							机构名称：<input id="sOrgName" class="easyui-textbox" />
						</div>
						<div>
							机构状态：<select class="easyui-combobox" panelHeight="auto" id="sStatus"></select>
						</div>
						<div>
							删除标志：<select class="easyui-combobox" panelHeight="auto" id="sDeleteFlag"></select>
						</div>
					    <div>
					        <a class="easyui-linkbutton" name="search">查询</a>
			   		 	    <a class="easyui-linkbutton" name="reset">重置</a>
				        </div>		
					</div>
					<div region="center">
						<div id="toolbar">
							<a name="add" class="easyui-linkbutton" plain="true"><i class="fa fa-plus"></i>新增</a>
							<a name="edit" class="easyui-linkbutton"plain="true"><i class="fa fa-edit"></i>编辑</a>
							<a name="del" class="easyui-linkbutton" plain="true"><i class="fa fa-trash"></i>删除</a>
							<a name="unDel" class="easyui-linkbutton" plain="true"><i class="fa fa-reply"></i>恢复</a>
							<span style="font-size:12px;">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								当前操作的机构：
								<span id="oCurrentOrg" style="font-size:12px;"></span>
							</span>
						</div>
						<table id="dataTable" class="easyui-datagrid"></table>
					</div>
				</div>
				<!-- 机构,完 -->
				<!-- 员工 -->
				<div title="员工" style="padding:0px;" class="easyui-layout" fit="true">
					<div id="staffSearchHead" region="north" style="border:1px #95B8E7 solid;">
						<div>
							用户编号：<input id="sPeopleStaffCode" class="easyui-textbox" />
						</div>
						<div >
							用户名称：<input id="sPeopleStaffName" class="easyui-textbox" />
						</div>
						<div>
							用户状态：<select id="sPeopleStatus" class="easyui-combobox" panelHeight="auto" ></select>
						</div>
					    <div >
					        <a class="easyui-linkbutton" name="staffSearch">查询</a>
			   		 	    <a class="easyui-linkbutton" name="staffReset">重置</a>
				        </div>		
					</div>
					<div region="center">
						<div id="peopleToolbar">
					 		<a name="addPeople" class="easyui-linkbutton" plain="true"><i class="fa fa-plus"></i>添加人员</a>
							<a name="delPeople" class="easyui-linkbutton" plain="true"><i class="fa fa-trash"></i>删除</a>
					 		<span style="font-size:12px;">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								当前操作的机构：
								<span id="sCurrentOrg" style="font-size:12px;"></span>
							</span>
					 	</div>
					 	<table id="peopleDataTable" class="easyui-datagrid" ></table>
					 </div>
				</div>
				<!-- 员工,完 -->
			</div>
			<!-- 选项卡,完 -->
		</div>
		<!-- 展示区,完 -->
	</div>
	<!-- 主页面,完 -->

	<!-- 编辑弹窗 -->
	<div id="editWin" class="easyui-dialog" data-options="closed:true, buttons:'#buttons', modal:true" style="width:350px;height:440px ;">
		<form id="form">
	    	<div  class="easyui-tabs" fit="true" style="width:400px;height:300px;" >
				<div title="基本信息" >
					<table>
						<tr>
							<td><span class="required"> * </span>机构编号：</td>
							<td><input id="orgCode" class="easyui-textbox" required="true"/></td>
						</tr>
						<tr>
							<td><span class="required"> * </span>机构名称：</td>
							<td><input id="orgName" class="easyui-textbox" required="true"/></td>
						</tr>
						<tr>
							<td>机构全称：</td>
							<td><input id="orgFullName" class="easyui-textbox" /></td>
						</tr>
						<tr>
							<td><span class="required"> * </span>排序号：</td>
							<td><input id="orgOrder" class="easyui-textbox" required="true"/></td>
						</tr>
						<tr>
							<td><span class="required"> * </span>机构大类：</td>
							<td><input id="orgClass" class="easyui-combobox" panelHeight="auto" required="true"/></td>
						</tr>
						<tr>
							<td><span class="required"> * </span>机构类型：</td>
							<td><input id="orgType" class="easyui-combobox" panelHeight="auto" required="true"/></td>
						</tr>
						<tr>
							<td>机构级别：</td>
							<td><input id="orgLevel" class="easyui-combobox"  panelHeight="auto"/></td>
						</tr>
						<tr>
							<td><span class="required"> * </span>机构状态：</td>
							<td><input id="status" class="easyui-combobox" panelHeight="auto" required="true"/></td>
						</tr>
						<tr>
							<td> 成立日期：</td>
							<td><input id="establishDate" class="easyui-datebox" editable="false" value="today()" /></td>
						</tr>
						<tr>
							<td>撤销日期：</td>
							<td><input id="revocationDate" class="easyui-datebox" editable="false" value="2099-99-99" /></td>
						</tr>
					</table>	
				</div>
				<div title="联系信息">
					<table>
						<tr>
							<td>联系人名称：</td>
							<td><input id="contactName" class="easyui-textbox" /></td>
						</tr>
						<tr>
							<td>机构联系电话：</td>
							<td><input id="phone" class="easyui-textbox" /></td>
						</tr>
						<tr>
							<td>机构联系地址：</td>
							<td><input id="contactAddress" class="easyui-textbox" /></td>
						</tr>
						<tr>
							<td>国家：</td>
							<td><input id="countryCode" class="easyui-combobox" panelHeight="auto" /></td>
						</tr>
						<tr>
							<td>省：</td>
							<td><input id="provinceCode" class="easyui-combobox" panelHeight="auto" /></td>
						</tr>
						<tr>
							<td>市：</td>
							<td><input id="cityCode" class="easyui-combobox" panelHeight="auto" /></td>
						</tr>
						<tr>
							<td>县：</td>
							<td><input id="countyCode" class="easyui-combobox"  panelHeight="auto"/></td>
						</tr>
						<tr>
							<td>直属地区：</td>
							<td><input id="directly" class="easyui-combobox" panelHeight="auto" /></td>
						</tr>
					</table>	
				</div>
			</div>
			<div id="buttons" style="width:350px;height:30px ;">
				<a class="easyui-linkbutton" name="saveWin">保存</a>
				<a class="easyui-linkbutton" name="resetWin" >重置</a>
			</div>	
		</form>		
	</div>
	<!-- 编辑弹窗,完 -->

	<!-- 员工弹窗 -->
	<div id="peopleWin" class="easyui-dialog" data-options="closed:true, buttons:'#peoButtons', modal:true" 
		style="width:550px;height:368px">
		<form id="searchPeople">
			<div  id="peopleSearchHead" style="border:1px;border-color:#95B8E7 ;border-style: solid;">
				<div>
					用户名：<input id="sStaffName" class="easyui-textbox" />
					用户编号：<input id="sStaffCode" class="easyui-textbox" />
					<a class="easyui-linkbutton" name="peopleSearch">查询</a>
					<a class="easyui-linkbutton" name="peopleReset">重置</a>
				</div>	
			</div>
			<div style="height:260px">
				<table id="peopleSearchDataTable" class="easyui-datagrid"></table>
			</div>
			<div id="peoButtons" style="width:400px;height:30px ;">
				<a class="easyui-linkbutton" name="peopleSaveWin" >确认</a>
				<a class="easyui-linkbutton" name="peopleCloseWin" >取消</a>
			</div>	
		</form>
	</div>
	<!-- 员工弹窗,完 -->
	
</body>
</html>