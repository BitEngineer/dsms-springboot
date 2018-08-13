/**
 * 用户管理
 * @author 邓键
 */
$(function() {
	new Page();
});

function Page() {
	_self = this;
	this.init();
	this.bindEvent();
};

/**
 * 初始化
 */
Page.prototype.init = function(){
	this.rootUri = "/app/user/";
	this.dataTotal = 1;
	this.unusedRolesDataTotal = 1;
	this.rightsDataTotal = 1;

	this.editObj = null;
	
	this.addRoleObj = new Array();
	this.delRoleObj = new Array();
	this.selectedOrgId = "";
	this.children = new Array();
	
	this.staffCodeUniqueness = false;
	this.loginNameUniqueness = false;
	
	//	TODO 码表数据动态初始化
	this.status = {"0":"注销","1":"正常","2":"锁定"};
	this.deleteFlag = {"0":"删除","1":"有效"};
	this.sex = {"0":"男","1":"女"};
	this.userType = {"1":"普通员工","2":"高管"};
	
	// 下拉列表初始化
	Common.setSelectOption($("#sStatus"), _self.status);
	Common.setSelectOption($("#sDeleteFlag"), _self.deleteFlag);
	Common.setSelectOption($("#sex"), _self.sex);
	Common.setSelectOption($("#userType"), _self.userType);
	// 设置下拉列表值
	$("#sDeleteFlag").combobox('setValue', '1');
	
	
	//码表数据初始化
//	this.initCodes();
	//数据集初始化
	this.initDataGrid();
};

/**
 * 获取查询条件
 */
Page.prototype.getSearchParams = function() {
	return Common.formData("#searchHead", "s");
};

/**
 * 初始化码表数据
 */
Page.prototype.initCodes = function(){
	//性别
	$.ajax({
		url : "app/user/findCodes.do",
		data : {codetypeId:"1003"},
		type : "post",
		success : function(result) {
			for(var i=0;i<result.length;i++){
				_self.sex[result[i].codeinfoCode] = result[i].codeinfoValue;
			}
			Common.setSelectOption($("#sex"), _self.sex);
		}
	});
	//员工类型
	$.ajax({
		url : "app/user/findCodes.do",
		data : {codetypeId:"1004"},
		type : "post",
		success : function(result) {
			for(var i=0;i<result.length;i++){
				_self.userType[result[i].codeinfoCode] = result[i].codeinfoValue;
			}
			Common.setSelectOption($("#userType"), _self.userType);
		}
	});
	//删除标志
	$.ajax({
		url : "app/user/findCodes.do",
		data : {codetypeId:"1001"},
		type : "post",
		success : function(result) {
			for(var i=0;i<result.length;i++){
				_self.deleteFlag[result[i].codeinfoCode] = result[i].codeinfoValue;
			}
			Common.setSelectOption($("#sDeleteFlag"), _self.deleteFlag);
			$("#sDeleteFlag").combobox('setValue', '1');
		}
	});
	//状态
	$.ajax({
		url : "app/user/findCodes.do",
		data : {codetypeId:"1002"},
		type : "post",
		success : function(result) {
			for(var i=0;i<result.length;i++){
				_self.status[result[i].codeinfoCode] = result[i].codeinfoValue;
			}
			Common.setSelectOption($("#sStatus"), _self.status);
		}
	});
}

/**
 * 初始化主页表格数据集
 */
Page.prototype.initDataGrid = function() {
	var pageSize = parseInt((window.parent.$(".panel-fit").height() -225)/25);	
	$("#dataTable").datagrid({
		url:Common.getRootPath() + _self.rootUri + "find.do",
		columns : [[
            {field:"staffId", hidden:true},
            {field:"staffCode", title:"用户编号" , sortable:true , align:"center" , width:"100px"},
            {field:"staffName", title:"用户名称" , sortable:true , align:"center" , width:"100px"},
            {field:"userType", title:"用户类型",  align:"center" , width:"100px",
            	formatter:function(value){
            		return _self.userType[value];
            	}
            },
            {field:"status", title:"用户状态", sortable:true,  align:"center" , width:"100px",
				formatter:function(value) {
					return _self.status[value];
				}
            },
            {field:"deleteFlag", title:"删除标志", sortable:true, align:"center" , width:"100px",
				formatter:function(value) {
					return _self.deleteFlag[value];
				}
            },
            {field:"orgId", hidden:true},
            {field:"orgName", title:"所属机构" , sortable:false , align:"center" , width:"200px"},
            {field:"updateUser", title:"最后修改人" , sortable:true , align:"center" , width:"100px"},
            {field:"updateTime", title:"最后修改时间" , sortable:true , align:"center" , width:"150px"},
            {field:"lockTime", title:"锁定时间" , sortable:true , align:"center" , width:"150px"}
		]],
		loadFilter : function(data) {
			$("#toolbar a[name='edit']").linkbutton("disable");
			$("#toolbar a[name='unDel']").linkbutton("disable");
			$("#toolbar a[name='del']").linkbutton("disable");
			$("#toolbar a[name='resetPwd']").linkbutton("disable");
			$("#toolbar a[name='lock']").linkbutton("disable");
			$("#toolbar a[name='unlock']").linkbutton("disable");
			$("#toolbar a[name='manageRole']").linkbutton("disable");
			$("#toolbar a[name='selectRights']").linkbutton("disable");
			return {
				rows: data.data.rows,
				total: data.data.total
			};
		},
	    onSelect:function(index, row){
			$("#toolbar a[name='edit']").linkbutton("enable");
			$("#toolbar a[name='resetPwd']").linkbutton("enable");
			$("#toolbar a[name='manageRole']").linkbutton("enable");
			$("#toolbar a[name='selectRights']").linkbutton("enable");
			if("0" == row.deleteFlag){
				$("#toolbar a[name='del']").linkbutton("disable");
				$("#toolbar a[name='unDel']").linkbutton("enable");
			} else {
				$("#toolbar a[name='del']").linkbutton("enable");
				$("#toolbar a[name='unDel']").linkbutton("disable");
			}
			if("1" == row.status){
				$("#toolbar a[name='lock']").linkbutton("enable");
				$("#toolbar a[name='unlock']").linkbutton("disable");
			}else if("2" == row.status){
				$("#toolbar a[name='unlock']").linkbutton("enable");
				$("#toolbar a[name='lock']").linkbutton("disable");
				$("#toolbar a[name='edit']").linkbutton("disable");
				$("#toolbar a[name='resetPwd']").linkbutton("disable");
				$("#toolbar a[name='del']").linkbutton("disable");
				$("#toolbar a[name='unDel']").linkbutton("disable");
				$("#toolbar a[name='manageRole']").linkbutton("disable");
				$("#toolbar a[name='selectRights']").linkbutton("disable");
			}
	    },
		pageSize:pageSize,
		pageList:[pageSize],
		toolbar:"#toolbar",
		queryParams : _self.getSearchParams()
	});
	
};

/**
 * 初始化角色管理窗口
 * @param staffId
 */
Page.prototype.initRoleDataGrid = function(data) {
	var staffId = data.staffId;
	
	//已分配的角色
	$("#usedRoleDatatable").datagrid({
		url:Common.getRootPath() + _self.rootUri + "findUsedRoles.do?staffId="+staffId,
		columns : [[
		    {field:"ck",checkbox:true},
            {field:"roleId", hidden:true},
            {field:"roleName", title:"角色名称", align:"center" , width:"120px"},
            {field:"description", title:"角色描述", align:"center" , width:"180px"},
		]],
		pagination:false,
		singleSelect:false
	});
	
	//可分配的角色
	$("#unusedRoleDatatable").datagrid({
		url:Common.getRootPath() + _self.rootUri + "findUnusedRoles.do?staffId="+staffId,
		columns : [[
		    {field:"ck",checkbox:true},
            {field:"roleId", hidden:true},
            {field:"roleName", title:"角色名称" , align:"center" , width:"120px"},
            {field:"description", title:"角色描述", align:"center" , width:"180px"},
		]],
		singleSelect:false,
		loadFilter : function(data) {
			return _self.processUnusedRoleData(data);
		}
	});
	
};

/**
 * 初始化权限查看窗口
 */
Page.prototype.initRightsDataGrid = function(data){
	var staffId = data.staffId;
	
	$("#rightsDatatable").datagrid({
		url:Common.getRootPath() + _self.rootUri + "findRights.do?staffId="+staffId,
		columns : [[
            {field:"resId", hidden:true},
            {field:"resName", title:"资源名称" , align:"center" , width:"200px"},
		]],
		loadFilter : function(data) {
			return _self.processRightData(data);
		}
	});
	
};

/**
 * 处理未使用角色datagrid的数据
 * @param data 待处理的数据
 * @returns rows 待展示的数据
 * @returns total 总记录数
 */
Page.prototype.processUnusedRoleData = function(data) {
	if(!jQuery.isArray(data)) {
		return data;
	}
//	_self.unusedRolesDataTotal = Common.getDataTotal("#unusedRoleDatatable", _self.unusedRolesDataTotal, data.length);
	var unusedRolesDataTotal = Common.getDataTotal("#unusedRoleDatatable", 1 , data.length);
	return {
		rows : data,
		total : unusedRolesDataTotal
	};
};

/**
 * 处理权限datagrid的数据
 * @param data 待处理的数据
 * @returns rows 待展示的数据
 * @returns total 总记录数
 */
Page.prototype.processRightData = function(data) {
	if(!jQuery.isArray(data)) {
		return data;
	}
	_self.rightsDataTotal = Common.getDataTotal($("#rightsDatatable"), 
			_self.rightsDataTotal, data.length);
	return {
		rows : data,
		total : _self.rightsDataTotal
	};
};


/**
 * 事件监听
 */
Page.prototype.bindEvent = function() {
	//搜索栏事件绑定
	$("a[name='search']").click(function() {
		var searchParams = _self.getSearchParams();
		if(_self.selectedOrgId != ""){
			searchParams.orgId = _self.selectedOrgId;
		}
		$("#dataTable").datagrid("load", searchParams);
	});
	
	$("a[name='reset']").click(function() {
		Common.formReset("#searchHead",{deleteFlag: '1'},  "s");
		//清除orgId
		_self.selectedOrgId = "";
	});
	
	//工具栏事件绑定
	$("#toolbar a[name='add']").click(function() {
		$(".idArea").show();
		_self.editObj = null;
		Common.formReset("#form", _self.editObj);
		$('#editWin').window({iconCls:'fa fa-plus', title:'新增'});
		$('#editWin').window('open');
	});

	$("#toolbar a[name='edit']").click(function(){
		$(".idArea").hide();
		_self.editObj = $("#dataTable").datagrid("getSelected");
		$('#editWin').window({iconCls:'fa fa-edit', title:'修改'});
		Common.formReset("#form", _self.editObj);
		$('#editWin').window('open');
	});
	$("#toolbar a[name='resetPwd']").click(function(){
		var data = $("#dataTable").datagrid("getSelected");
		_self.resetPwd(data);
	});
	
	$("#toolbar a[name='del']").click(function(){
		var data =  $("#dataTable").datagrid("getSelected");
		data.deleteFlag = "0";
		_self.del("删除", data)
	});
	
	$("#toolbar a[name='unDel']").click(function(){
		var data =  $("#dataTable").datagrid("getSelected");
		data.deleteFlag = "1";
		_self.del("撤销删除", data)
	});
	
	$("#toolbar a[name='lock']").click(function(){
		var data =  $("#dataTable").datagrid("getSelected");
		data.status = "2";
		//获取当前系统时间
		var date = new Date();
	    var seperator1 = "-";
	    var year = date.getFullYear();
	    var month = date.getMonth() + 1;
	    var strDate = date.getDate();
	    if (month >= 1 && month <= 9) {
	        month = "0" + month;
	    }
	    if (strDate >= 0 && strDate <= 9) {
	        strDate = "0" + strDate;
	    }
	    var currentdate = year + seperator1 + month + seperator1 + strDate;
	    //将时间绑定到data中
	    data.lockTime = currentdate;
	    
		_self.lock("锁定", data)
	});
	
	$("#toolbar a[name='unlock']").click(function(){
		var data =  $("#dataTable").datagrid("getSelected");	
		var newData = new Object();
		newData.staffId = data.staffId;
		newData.status = "1";
		_self.lock("解锁", newData)
	});
	
	
	//角色管理
	$("#toolbar a[name='manageRole']").click(function(){
		_self.editObj =  $("#dataTable").datagrid("getSelected");
		//弹出窗口
		$('#roleWin').window({iconCls:'fa fa-wrench',title:'角色管理'});
		$('#roleWin').window('open');
		//初始化全局变量
		_self.addRoleObj = [];
		_self.delRoleObj = [];
		
		//初始化角色管理窗口
		_self.initRoleDataGrid(_self.editObj);
	});
	
	//查看权限
	$("#toolbar a[name='selectRights']").click(function(){
		_self.editObj =  $("#dataTable").datagrid("getSelected");
		//弹出窗口
		$('#rightsWin').window({iconCls:'fa fa-search',title:'查看权限'});
		$('#rightsWin').window('open');
		//初始化权限查看窗口
		_self.initRightsDataGrid(_self.editObj);
	});
	
	
	
	//弹窗按钮
	//新增和编辑弹窗
	$("a[name='saveEditWin']").click(
			_self.saveEditWin);
	$("a[name='resetEditWin']").click(function(){
		Common.formReset("#form", _self.editObj);
	});
	//权限查看弹窗
	$("a[name='confirmRightsWin']").click(function(){
		$('#rightsWin').window('close');
	});
	
	//搜索栏机构选择文本框自带按钮
	$("#sOrgName").textbox({
		onClickButton:_self.generateSearchTree
	});
	$("#orgName").textbox({
		onClickButton:_self.generateEditTree
	});
	//机构选择确定按钮
	$("#searchOrgButtons a[name='confirmWin']").click(function(){
		$('#searchOrgWin').window('close');
	});
	$("#editOrgButtons a[name='confirmWin']").click(function(){
		$('#editOrgWin').window('close');
	});


	//角色管理--添加按钮
	$("#roleWin a[name='addWin']").click(function(){
		var datas = $("#unusedRoleDatatable").datagrid("getSelections");
		var dataIdxs = new Array();
		
		if(datas.length != 0){
			for(var i=0;i<datas.length;i++){
				var data = datas[i];
				var dataIdx = $("#unusedRoleDatatable").datagrid("getRowIndex",data);
				dataIdxs.push(dataIdx);
				$("#usedRoleDatatable").datagrid('appendRow',{"roleId":data.roleId,"roleName":data.roleName,
					"description":data.description});
				
				//更新全局变量
				if(_self.delRoleObj.length != 0){
					var index = new Object();
					var flag = new Object();
					flag = true;
					
					for(var j=0;j<_self.delRoleObj.length;j++){
						if(data.roleId == _self.delRoleObj[j]){
							flag = false;
							index = j;
							break;
						}
					}
					
					if(!flag){
						_self.delRoleObj.splice(index,1);
					}else{
						_self.addRoleObj.push(data.roleId);
					}
					
				}else{
					_self.addRoleObj.push(data.roleId);
				}	
			}
		
			while(dataIdxs.length != 0){
				var dataIdx = dataIdxs.pop();
				$("#unusedRoleDatatable").datagrid("deleteRow",dataIdx);
			}	
		}
		
	});
	
	//角色管理--删除按钮
	$("#roleWin a[name='delWin']").click(function(){
		var datas = $("#usedRoleDatatable").datagrid("getSelections");
		var dataIdxs = new Array();
		
		if(datas.length != 0){
			for(var i=0;i<datas.length;i++){
				var data = datas[i];
				var dataIdx = $("#usedRoleDatatable").datagrid("getRowIndex",data);
				dataIdxs.push(dataIdx);
				$("#unusedRoleDatatable").datagrid('appendRow',{"roleId":data.roleId,"roleName":data.roleName,
					"description":data.description});
				
				//更新全局变量
				if(_self.addRoleObj.length != 0){
					var index = new Object();
					var flag = new Object();
					flag = true;
					
					for(var j=0;j<_self.addRoleObj.length;j++){
						if(data.roleId == _self.addRoleObj[j]){
							flag = false;
							index = j;
							break;
						}
					}
					
					if(!flag){
						_self.addRoleObj.splice(index,1);
					}else{
						_self.delRoleObj.push(data.roleId);
					}
					
				}else{
					_self.delRoleObj.push(data.roleId);
				}
			}
			
			while(dataIdxs.length != 0){
				var dataIdx = dataIdxs.pop();
				$("#usedRoleDatatable").datagrid("deleteRow",dataIdx);
			}
		}
	});
	
	
	//角色管理取消按钮
	$("a[name='cancelRoleWin']").click(function(){
		//清空全局变量
		_self.addRoleObj = [];
		_self.delRoleObj = [];
		$("#roleWin").window('close');
	});
	
	//角色管理确定按钮
	$("a[name='saveRoleWin']").click(function(){
		//整理数据
		var addRoles = "";
		if(_self.addRoleObj.length == 1){
			addRoles = _self.addRoleObj[0];
		}else if(_self.addRoleObj.length >= 2){
			addRoles = _self.addRoleObj[0];
			for(var i=1;i<_self.addRoleObj.length;i++){
				addRoles = addRoles + "," + _self.addRoleObj[i];
			}
		}
		
		var delRoles = "";
		if(_self.delRoleObj.length == 1){
			delRoles = _self.delRoleObj[0];
		}else if(_self.delRoleObj.length >= 2){
			delRoles = _self.delRoleObj[0];
			for(var i=1;i<_self.delRoleObj.length;i++){
				delRoles = delRoles + "," + _self.delRoleObj[i];
			}
		}
		
		var staffId = _self.editObj.staffId;
		
		var data = {"staffId":staffId,"addRoles":addRoles,"delRoles":delRoles};
		
		$.ajax({
			url:Common.getRootPath() + _self.rootUri + "manageRoles.do",
			data:data,
			success:function() {
				Common.showInfo("alert", "角色编辑成功");
				$("#roleWin").window('close');
				//清空全局变量
				_self.addRoleObj = [];
				_self.delRoleObj = [];
			},
			error:function(){
				_self.addRoleObj = [];
				_self.delRoleObj = [];
			}
		});
		
	});
	
	//角色搜索
	$("#sRoles").searchbox({
		searcher:_self.searchRoles
	});
	
	//机构树搜索
	//搜索栏
	//机构树搜索功能改进---异步树的搜索功能
	$("#sSearchTree").searchbox({
		searcher: function(value,name){
			//获取搜索数据
			$.ajax({
				url : "app/user/searchTree.do",
				data : {orgName:value},
				type : "post",
				success : function(result) {
					//形成树结构数据
					var data = _self.formatTreeData(result);
					
					//形成搜索机构树
					// 形成树
					$("#searchOrgTree").tree({
						data : data,
						onClick : function(node) {
							// 将数据写到文本框中
							$("#sOrgName").textbox("setValue", node.text);
							// 获取id，放到全局变量中
							_self.selectedOrgId = node.id;
						} 
					});	
				}
			});	
		},
		prompt:'输入后回车搜索机构'
	});
	//编辑和新增弹窗
	$("#searchTree").searchbox({
		searcher: function(value,name){
			//获取搜索数据
			$.ajax({
				url : "app/user/searchTree.do",
				data : {orgName:value},
				type : "post",
				success : function(result) {
					//形成树结构数据
					var data = _self.formatTreeData(result);
					
					//形成搜索机构树
					// 形成树
					$("#editOrgTree").tree({
						data : data,
						onClick : function(node) {
							// 将数据写到文本框中
							$("#orgName").textbox("setValue", node.text);
							// 获取id，放到全局变量中
							_self.selectedOrgId = node.id;
						} 
					});	
				}
			});	
		},
		prompt:'输入后回车搜索机构'
	});
	 
};

/**
 * 编辑
 * @param type
 * @param data
 */
Page.prototype.edit = function(type, data){
	$.ajax({
		url:Common.getRootPath() + _self.rootUri + "edit.do",
		data:data,
		success:function(data) {
			if(data != 0) {
				$("#dataTable").datagrid("reload",  _self.getSearchParams());
				Common.showInfo("alert", type + "成功");
			} else {
				Common.showInfo("warning", type + "失败");
			}
		}
	});
};

/**
 * 删除及撤销
 * @param type
 * @param data
 */
Page.prototype.del = function(type, data){
	$.ajax({
		url:Common.getRootPath() + _self.rootUri + "del.do",
		data:data,
		success:function(data) {
			if(data != 0) {
				var searchParams = _self.getSearchParams();
				$("#dataTable").datagrid("reload", searchParams);
				Common.showInfo("alert", type + "成功");
			} else {
				Common.showInfo("warning", type + "失败");
			}
		}
	});
};

/**
 * 锁定和解锁
 * @param type
 * @param data
 */
Page.prototype.lock = function(type, data){
	$.ajax({
		url:Common.getRootPath() + _self.rootUri + "edit.do",
		data:data,
		success:function(data) {
			if(data != 0) {
				$("#dataTable").datagrid("reload",  _self.getSearchParams());
				Common.showInfo("alert", type + "成功");
			} else {
				Common.showInfo("warning", type + "失败");
			}
		}
	});
};


/**
 * 用户编号校验
 */
Page.prototype.checkStaffCode = function () { 
	 var staffCode = $("#editWin #staffCode").textbox('getValue');
	 var title = $("#editWin").panel('options').title;
	 if(title == "修改"){
		 if(staffCode != _self.editObj.staffCode){
			 $.ajax({
				url:Common.getRootPath() + _self.rootUri + "check.do",
				data:{"staffCode":staffCode},
				async:false,
				success:function(data) {
					if(data != 0) {
						_self.staffCodeUniqueness = false;
						Common.showInfo("error", "用户编号已存在，请重新输入");
					}else{
						_self.staffCodeUniqueness = true;
					}
				}
			}); 
		 }else{
			 _self.staffCodeUniqueness = true;
		 }
	 }else if(title == "新增"){
		 $.ajax({
			url:Common.getRootPath() + _self.rootUri + "check.do",
			data:{"staffCode":staffCode},
			async:false,
			success:function(data) {
				if(data != 0) {
					_self.staffCodeUniqueness = false;
					Common.showInfo("error", "用户编号已存在，请重新输入");
				}else{
					_self.staffCodeUniqueness = true;
				}
			}
		}); 
	 } 
}

/**
 * 用户登录名校验
 */
Page.prototype.checkLoginName = function () {
	 var loginName = $("#editWin #loginName").textbox('getValue');
	 var title = $("#editWin").panel('options').title;
	 if(title == "修改"){
		 if(loginName != _self.editObj.loginName){
			 $.ajax({
				url:Common.getRootPath() + _self.rootUri + "check.do",
				data:{"loginName":loginName},
				async:false,
				success:function(data) {
					if(data != 0) {
						_self.loginNameUniqueness = false;
						Common.showInfo("error", "登录名已存在，请重新输入");
					}else{
						_self.loginNameUniqueness = true;
					}
				}
			});
		 }else{
			 _self.loginNameUniqueness = true;
		 }
	 }else if(title == "新增"){
		 $.ajax({
			url:Common.getRootPath() + _self.rootUri + "check.do",
			data:{"loginName":loginName},
			async:false,
			success:function(data) {
				if(data != 0) {
					_self.loginNameUniqueness = false;
					Common.showInfo("error", "登录名已存在，请重新输入");
				}else{
					_self.loginNameUniqueness = true;
				}
			}
		});
	 }
	 
}

Page.prototype.saveEdit = function(){
	var data = Common.formData("#form");
	if (null != _self.editObj) {
		data = $.extend(_self.editObj, data);
	}
	data.orgId = _self.selectedOrgId;
	url = Common.getRootPath() + _self.rootUri
			+ (null == _self.editObj ? "add.do" : "edit.do");

	$.ajax({
		url : url,
		data : data,
		success : function(flag) {
			// 清空全局变量
			_self.selectedOrgId = "";
			if (flag) {
				$('#editWin').window('close');
				$("#dataTable").datagrid("reload", _self.getSearchParams());
				Common.showInfo("alert", (null == _self.editObj ? "添加成功"
						: "编辑成功"));
			} else {
				Common.showInfo("warning", (null == _self.editObj ? "添加失败"
						: "编辑失败"));
			}
		}
	});
}

/**
 * 
 * 修改和编辑界面保存按钮触发的事件
 */
Page.prototype.saveEditWin = function() {
	if($("#form").form("validate")){   //必填字段校验
		_self.checkStaffCode();  //校验用户编号
		if(_self.staffCodeUniqueness){  //编号校验通过
			_self.checkLoginName();  //校验登录名
			if(_self.loginNameUniqueness){  //登录名校验通过
				_self.saveEdit();  //保存
			}
		}
	}
};

/**
 * 重置密码
 */
Page.prototype.resetPwd = function(data) {
	var url = Common.getRootPath() + _self.rootUri + "resetPwd.do";
	var newData = data.staffId;
	$.ajax({
		url : url,
		data:data,
		success : function(flag) {
			if (flag) {
				$("#dataTable").datagrid("reload",_self.getSearchParams());
				Common.showInfo("alert", "密码重置成功");
			} else {
				Common.showInfo("warning", "密码重置失败");
			}
		}
	});
};
	

/**
 * 角色管理弹窗确定按钮
 */
Page.prototype.confirmWin = function() {
	var data = Common.formData("#pwdForm");

	if (null != _self.editObj) {
		data = $.extend(_self.editObj, data);
	}
	url = Common.getRootPath() + _self.rootUri + "edit.do";

	$.ajax({
		url : url,
		data : data,
		success : function(result) {
			if (result.success) {
				$('#pwdWin').window('close');
				$("#dataTable").datagrid("reload", _self.getSearchParams());
				Common.showInfo("alert", "密码修改成功");
			} else {
				Common.showInfo("warning", "密码修改失败");
			}
		}
	});
};


/**
 * 异步树加载
 */
Page.prototype.generateSearchTree = function(){
	$('#searchOrgWin').window({iconCls:'fa fa-search', title:'选择机构'});
	$('#searchOrgWin').window('open');
	
	//初始化
	var data = {"parentId":'-1'};
	$.ajax({
		url : "app/user/findOrgByParent.do",
		data : data,
		type : "post",
		success : function(result) {
			//数据整理
			var data = new Array();
			data.push({"id":result[0].orgId,"text":result[0].orgName,"parentId":result[0].parentId,
				"state":"closed"});
		
			// 形成树
			$("#searchOrgTree").tree({
				data : data,
				onClick : function(node) {
					// 将数据写到文本框中
					$("#sOrgName").textbox("setValue", node.text);
					// 获取id，放到全局变量中
					_self.selectedOrgId = node.id;
				} 
			});	
		}
	});
	
	//为树节点增加展开方法
	$("#searchOrgTree").tree({
		onBeforeExpand:function(node){
			var currentNode = node;
		
			$.ajax({
				url: "app/user/findOrgByParent.do",
				type:"post",
				data:{"parentId":node.id},
				async: false,
				success:function(result){
					if(result.length > 0){
						var children = new Array();
						for(var i=0;i<result.length;i++){
							var child = {"id":result[i].orgId,"text":result[i].orgName,"parentId":result[i].parentId,
									"state":"closed"};
							children.push(child);
						}
						//清空之前追加的子节点
						var preChildren = $('#searchOrgTree').tree('getChildren', currentNode.target);
						for(var j=0;j<preChildren.length;j++){
							$('#searchOrgTree').tree('remove', preChildren[j].target);
						}
						//追加子节点到父节点
						$('#searchOrgTree').tree('append', {
							parent: currentNode.target,
							data: children
						});
						
					}
				}
			});
        }
	});
	
};


Page.prototype.generateEditTree = function(){
	$('#editOrgWin').window({iconCls:'fa fa-search', title:'选择机构'});
	$('#editOrgWin').window('open');
	
	//初始化
	var data = {"parentId":'-1'};
	$.ajax({
		url : "app/user/findOrgByParent.do",
		data : data,
		type : "post",
		success : function(result) {
			//数据整理
			var data = new Array();
			data.push({"id":result[0].orgId,"text":result[0].orgName,"parentId":result[0].parentId,
				"state":"closed"});
		
			// 形成树
			$("#editOrgTree").tree({
				data : data,
				onClick : function(node) {
					// 将数据写到文本框中
					$("#orgName").textbox("setValue", node.text);
					// 获取id，放到全局变量中
					_self.selectedOrgId = node.id;
				} 
			});	
		}
	});
	
	//为树节点增加展开方法
	$("#editOrgTree").tree({
		onBeforeExpand:function(node){
			var currentNode = node;
		
			$.ajax({
				url: "app/user/findOrgByParent.do",
				type:"post",
				data:{"parentId":node.id},
				async: false,
				success:function(result){
					if(result.length > 0){
						var children = new Array();
						for(var i=0;i<result.length;i++){
							var child = {"id":result[i].orgId,"text":result[i].orgName,"parentId":result[i].parentId,
									"state":"closed"};
							children.push(child);
						}
						//清空之前追加的子节点
						var preChildren = $('#editOrgTree').tree('getChildren', currentNode.target);
						for(var j=0;j<preChildren.length;j++){
							$('#editOrgTree').tree('remove', preChildren[j].target);
						}
						//追加子节点到父节点
						$('#editOrgTree').tree('append', {
							parent: currentNode.target,
							data: children
						});
						
					}
				}
			});
        }
	});
	
};

/**
 * 搜索栏机构选择
 */
/*
Page.prototype.generateSearchTree = function(){
	$('#searchOrgWin').window({iconCls:'fa fa-search', title:'选择机构'});
	$('#searchOrgWin').window('open');
	
	//获取机构树数据
	// 生成机构树
	$.ajax({
		url : "app/user/findOrg.do",
		type : "post",
		success : function(result) {
			//数据整理
			var data = _self.formatTreeData(result);
		
			// 形成树
			$("#searchOrgTree").tree({
				data : data,
				onClick : function(node) {
					// 将数据写到文本框中
					$("#sOrgName").textbox("setValue", node.text);
					// 获取id，放到全局变量中
					_self.selectedOrgId = node.id;
				}
			});
		}
	});
	
}
*/

/**
 * 新增和编辑窗口机构选择
 */
/*
Page.prototype.generateEditTree = function(){
	$('#editOrgWin').window({iconCls:'fa fa-search', title:'选择机构'});
	$('#editOrgWin').window('open');
	
	//获取机构树数据
	// 生成机构树
	$.ajax({
		url : "app/user/findOrg.do",
		type : "post",
		success : function(result) {
			//数据整理
			var data = _self.formatTreeData(result);
		
			// 形成树
			$("#editOrgTree").tree({
				data : data,
				onClick : function(node) {
					// 将数据写到文本框中
					$("#editWin #orgName").textbox("setValue", node.text);
					// 获取id，放到全局变量中
					_self.selectedOrgId = node.id;
				}
			});
		}
	});
}
*/

/**
 * 将数组数据转换为树结构的数据
 * @param data 原始的数组
 * @returns {Array} 树结构的数组
 */
Page.prototype.formatTreeData = function(data){
	// 修改key
	var nodes = new Array();
	for (var i = 0; i < data.length; i++) {
		nodes.push({
			"id" : data[i].orgId,
			"text" : data[i].orgName,
			"parentId" : data[i].parentId
		});
	}

	var rows = new Array();
	rows = nodes;

	// 获取根节点
	var rootNode = new Object();
	var rootIdx = new Object();
	for (var i = 0; i < rows.length; i++) {
		if (rows[i].parentId == '-1') {
			rootNode = rows[i];
			rootIdx = i;
		}
	}

	// 节点挂载
	for (var i = 0; i < rows.length; i++) {
		var node = rows[i];

		// 根节点不用再寻找父节点
		if (i != rootIdx) { 
			var parent = new Object();
			for (var j = 0; j < rows.length; j++) {
				if (rows[j].id == node.parentId) {
					parent = rows[j];
					break;
				}
			}
			if (parent != null) {
				if (parent.children != null) {
					parent.children.push(node);
				} else {
					parent.children = new Array();
					parent.children.push(node);
				}
			}
		}

	}

	// 返回过滤的数据--返回的数据应该是一个数组类型的json
	var returnData = new Array();
	returnData.push(rootNode);
	return returnData;
};


/**
 * 角色搜索
 * @param value
 */
Page.prototype.searchRoles = function(value){
	$("#unusedRoleDatatable").datagrid({
		url:Common.getRootPath() + _self.rootUri + "searchRoles.do",
		queryParams: {
			roleName: value
		},
		columns : [[
		    {field:"ck", align:"center",checkbox:"true"},
            {field:"roleId", hidden:true},
            {field:"roleName", title:"角色名称" , align:"center" , width:"120px"},
            {field:"description", title:"角色描述", align:"center" , width:"180px"},
		]],
		loadFilter:function(datas){
			return _self.processUnusedRoleData(datas);
		}
	});
};


(function($) {    
    $.extend($.fn.tree.methods, {  
        /**  
         * 扩展easyui tree的搜索方法  
         * @param tree easyui tree的根DOM节点(UL节点)的jQuery对象  
         * @param searchText 检索的文本  
         * @param this-context easyui tree的tree对象  
         */  
        search: function(jqTree, searchText) {  
            //easyui tree的tree对象。可以通过tree.methodName(jqTree)方式调用easyui tree的方法  
            var tree = this;  
              
            //获取所有的树节点  
            var nodeList = getAllNodes(jqTree, tree);  
              
            //如果没有搜索条件，则展示所有树节点  
            searchText = $.trim(searchText);  
            if (searchText == "") {  
                for (var i=0; i<nodeList.length; i++) {  
                    $(".tree-node-targeted", nodeList[i].target).removeClass("tree-node-targeted");  
                    $(nodeList[i].target).show();  
                }  
                //展开已选择的节点（如果之前选择了）  
                var selectedNode = tree.getSelected(jqTree);  
                if (selectedNode) {  
                    tree.expandTo(jqTree, selectedNode.target);  
                }  
                return;  
            }  
              
            //搜索匹配的节点并高亮显示  
            var matchedNodeList = [];  
            if (nodeList && nodeList.length>0) {  
                var node = null;  
                for (var i=0; i<nodeList.length; i++) {  
                    node = nodeList[i];  
                    if (isMatch(searchText, node.text)) {  
                        matchedNodeList.push(node);  
                    }  
                }  
                  
                //隐藏所有节点  
                for (var i=0; i<nodeList.length; i++) {  
                    $(".tree-node-targeted", nodeList[i].target).removeClass("tree-node-targeted");  
                    $(nodeList[i].target).hide();  
                }             
                  
                //折叠所有节点  
                tree.collapseAll(jqTree);  
                  
                //展示所有匹配的节点以及父节点              
                for (var i=0; i<matchedNodeList.length; i++) {  
                    showMatchedNode(jqTree, tree, matchedNodeList[i]);  
                }  
            }      
        },  
          
        /**  
         * 展示节点的子节点（子节点有可能在搜索的过程中被隐藏了）  
         * @param node easyui tree节点  
         */  
        showChildren: function(jqTree, node) {  
            //easyui tree的tree对象。可以通过tree.methodName(jqTree)方式调用easyui tree的方法  
            var tree = this;  
              
            //展示子节点  
            if (!tree.isLeaf(jqTree, node.target)) {  
                var children = tree.getChildren(jqTree, node.target);  
                if (children && children.length>0) {  
                    for (var i=0; i<children.length; i++) {  
                        if ($(children[i].target).is(":hidden")) {  
                            $(children[i].target).show();  
                        }  
                    }  
                }  
            }     
        },  
          
        /**  
         * 将滚动条滚动到指定的节点位置，使该节点可见（如果有滚动条才滚动，没有滚动条就不滚动）  
         * @param param {  
         *    treeContainer: easyui tree的容器（即存在滚动条的树容器）。如果为null，则取easyui tree的根UL节点的父节点。  
         *    targetNode:  将要滚动到的easyui tree节点。如果targetNode为空，则默认滚动到当前已选中的节点，如果没有选中的节点，则不滚动  
         * }   
         */  
        scrollTo: function(jqTree, param) {  
            //easyui tree的tree对象。可以通过tree.methodName(jqTree)方式调用easyui tree的方法  
            var tree = this;  
              
            //如果node为空，则获取当前选中的node  
            var targetNode = param && param.targetNode ? param.targetNode : tree.getSelected(jqTree);  
              
            if (targetNode != null) {  
                //判断节点是否在可视区域                 
                var root = tree.getRoot(jqTree);  
                var $targetNode = $(targetNode.target);  
                var container = param && param.treeContainer ? param.treeContainer : jqTree.parent();  
                var containerH = container.height();  
                var nodeOffsetHeight = $targetNode.offset().top - container.offset().top;  
                if (nodeOffsetHeight > (containerH - 30)) {  
                    var scrollHeight = container.scrollTop() + nodeOffsetHeight - containerH + 30;  
                    container.scrollTop(scrollHeight);  
                }                             
            }  
        }  
    });  
      
    /**  
     * 展示搜索匹配的节点  
     */  
    function showMatchedNode(jqTree, tree, node) {  
        //展示所有父节点  
        $(node.target).show();  
        $(".tree-title", node.target).addClass("tree-node-targeted");  
        var pNode = node;  
        while ((pNode = tree.getParent(jqTree, pNode.target))) {  
            $(pNode.target).show();               
        }  
        //展开到该节点  
        tree.expandTo(jqTree, node.target);  
        //如果是非叶子节点，需折叠该节点的所有子节点  
        if (!tree.isLeaf(jqTree, node.target)) {  
            tree.collapse(jqTree, node.target);  
        }  
    }      
      
    /**  
     * 判断searchText是否与targetText匹配  
     * @param searchText 检索的文本  
     * @param targetText 目标文本  
     * @return true-检索的文本与目标文本匹配；否则为false.  
     */  
    function isMatch(searchText, targetText) {  
        return $.trim(targetText)!="" && targetText.indexOf(searchText)!=-1;  
    }  
      
    /**  
     * 获取easyui tree的所有node节点  
     */  
    function getAllNodes(jqTree, tree) {  
        var allNodeList = jqTree.data("allNodeList");  
        if (!allNodeList) {  
            var roots = tree.getRoots(jqTree);  
            allNodeList = getChildNodeList(jqTree, tree, roots);  
            jqTree.data("allNodeList", allNodeList);  
        }  
        return allNodeList;  
    }  
      
    /**  
     * 定义获取easyui tree的子节点的递归算法  
     */  
    function getChildNodeList(jqTree, tree, nodes) {  
        var childNodeList = [];  
        if (nodes && nodes.length>0) {             
            var node = null;  
            for (var i=0; i<nodes.length; i++) {  
                node = nodes[i];  
                childNodeList.push(node);  
                if (!tree.isLeaf(jqTree, node.target)) {  
                    var children = tree.getChildren(jqTree, node.target);  
                    childNodeList = childNodeList.concat(getChildNodeList(jqTree, tree, children));  
                }  
            }  
        }  
        return childNodeList;  
    }  
})(jQuery);  
