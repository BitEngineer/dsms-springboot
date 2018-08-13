/**
 * 资源配置
 * @author 谭琛
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
	this.dataTotal = 3000;

	this.relative = {"0":"否","1":"是"};
	this.available = {"0":"删除","1":"有效"};
	this.visible = {"0":"否","1":"是"};
	this.resType = {"CD":"菜单","GN":"按钮","LJ":"路径","SJ":"字段集合","JG":"机构集合","AQ":"安全策略","JS":"角色"};
	
	this.treeMap = {};
	this.treeSelected = {};
	this.editObj = null;

	Common.setSelectOption($("#relative"), _self.relative);
	Common.setSelectOption($("#available"), _self.available);
	Common.setSelectOption($("#visible"), _self.visible);
	Common.setSelectOption($("#resType"), _self.resType);
	this.addUrlObj = new Array();
	this.delUrlObj = new Array();
	
	this.createTree();
};

/**
 * 获取查询条件
 * @author 谭琛
 */
Page.prototype.getSearchParams = function() {
	return {parentId: _self.treeSelected.resId};
};

Page.prototype.getSearchUrlParams = function() {
	var data = Common.formData("#searchUrlHead", "s");
	data.resId = _self.treeSelected.resId;
	return data;
};

Page.prototype.getSearchRoleParams = function() {
	var data = Common.formData("#searchRoleHead", "s");
	data.resId = _self.treeSelected.resId;
	return data;
};


Page.prototype.getSearchRelateParams = function() {
	return {resId: _self.treeSelected.resId};
};

/**
 * 初始化Datagrid
 * 在树形成后初始化
 */
Page.prototype.initDataGrids = function() {
	var pageSize = parseInt((window.parent.$(".panel-fit").height() -225)/25);	
	
	$("#resTable").datagrid({
		url:Common.getControllerPath() + "get.do",
		columns : [[
		    {field:"resId",hidden:true},
			{field:"resCode", title:"资源编码" , sortable:true},
			{field:"resName", title:"资源名称" , sortable:true},
			{field:"resType", title:"资源类型" , sortable:true,
				formatter:function(value) {
					return _self.resType[value];
				}
            },
            {field:"resUrl", title:"资源Url" , sortable:true},
			{field:"relative", title:"相对路径" , sortable:true,
				formatter:function(value) {
					return _self.relative[value];
				}
            },
            {field:"resOrder", title:"资源顺序号" , sortable:true},
            {field:"resIcon", title:"资源图片" , sortable:true},
            {field:"available", title:"删除标识" , sortable:true,
				formatter:function(value) {
					return _self.available[value];
				}
            },
            {field:"visible", title:"可见" , sortable:true,
				formatter:function(value) {
					return _self.visible[value];
				}
            },
            {field:"updateUser", title:"最后修改人" , sortable:true},
            {field:"updateTime", title:"最后修改时间" , sortable:true}
		]],
		onLoadSuccess : function() {
			$("#resToolbar a[name='edit']").linkbutton("disable");
			$("#resToolbar a[name='del']").linkbutton("disable");
			$("#resToolbar a[name='unDel']").linkbutton("disable");
		},
	    onSelect:function(index, row){
			if("0" == row.available){
				$("#resToolbar a[name='del']").linkbutton("disable");
				$("#resToolbar a[name='unDel']").linkbutton("enable");
			} else {
				$("#resToolbar a[name='edit']").linkbutton("enable");
				$("#resToolbar a[name='del']").linkbutton("enable");
				$("#resToolbar a[name='unDel']").linkbutton("disable");
			}
	    },
		pagination:false,
		toolbar:"#resToolbar",
		queryParams : _self.getSearchParams()
	});
	
	
	//初始化URL Datagrid
	$("#urlTable").datagrid({
		url:Common.getControllerPath() + "getResUrl.do",
		columns : [[
			{field:"urlId", hidden:true},
			{field:"urlPath", title:"URL路径" , sortable:true},
			{field:"description", title:"URL描述" , sortable:true}
		]],
		pagination:false,
		toolbar:"#urlToolbar",
		queryParams : _self.getSearchRelateParams()
	});
	
	var urlTotal = 20;
	//初始化未分配资源
	$("#dataTableUrlLeft").datagrid({
		url:Common.getControllerPath() + "getAllUrl.do",
		columns : [[
		    {field:"ck",checkbox:true},
			{field:"urlId", hidden:true},
			{field:"urlPath", title:"URL路径" , sortable:true},
			{field:"description", title:"URL描述" , sortable:true}
		]],
		loadFilter : function(data) {
			urlTotal = Common.getDataTotal($("#dataTableUrlLeft"), urlTotal, data.length);
			return {
				rows : data,
				total : urlTotal
			};
		},
		singleSelect:false,
		pageSize:12,
		toolbar:"#searchUrlHead",
		queryParams : _self.getSearchUrlParams()
	});
	
	//初始化已分配资源
	$("#dataTableUrlRight").datagrid({
		idField:"urlId",
		columns : [[
		    {field:"ck",checkbox:true},
			{field:"urlId", hidden:true},
			{field:"urlPath", title:"URL路径" , sortable:true},
			{field:"description", title:"URL描述" , sortable:true}
		]],
		singleSelect:false,
		pagination:false
	});
	
	//初始化角色 Datagrid
	$("#roleTable").datagrid({
		url:Common.getControllerPath() + "getResRole.do",
		columns : [[
			{field:"roleId", hidden:true},
			{field:"roleName", title:"角色名称" , sortable:true},
			{field:"description", title:"角色描述" , sortable:true}
		]],
		pagination:false,
		toolbar:"#roleToolbar",
		queryParams : _self.getSearchRelateParams()
	});
	
	//初始化未分配角色
	var roleTotal = 20;
	$("#dataTableRoleLeft").datagrid({
		url:Common.getControllerPath() + "getAllRole.do",
		columns : [[
		    {field:"ck",checkbox:true},
			{field:"roleId", hidden:true},
			{field:"roleName", title:"角色名称" , sortable:true},
			{field:"description", title:"角色描述" , sortable:true}
		]],
		loadFilter : function(data) {
			roleTotal = Common.getDataTotal($("#dataTableRoleLeft"), roleTotal, data.length);
			return {
				rows : data,
				total : roleTotal
			};
		},
		singleSelect:false,
		pageSize:12,
		toolbar:"#searchRoleHead",
		queryParams : _self.getSearchRoleParams()
	});
	
	//初始化已分配角色
	$("#dataTableRoleRight").datagrid({
		idField:"roleId",
		columns : [[
		    {field:"ck",checkbox:true},
			{field:"roleId", hidden:true},
			{field:"roleName", title:"角色名称" , sortable:true},
			{field:"description", title:"角色描述" , sortable:true}
		]],
		singleSelect:false,
		pagination:false
	});
	
};

/**
 * 刷新Datagrid
 * 在树形成后初始化
 */
Page.prototype.refreshDatagrids = function(){
	$("#resTable").datagrid('load', _self.getSearchParams());
	$("#urlTable").datagrid('load', _self.getSearchRelateParams());
	$("#roleTable").datagrid('load', _self.getSearchRelateParams());
}


/**
 * 事件监听
 */
Page.prototype.bindEvent = function() {
	$("#resToolbar a[name='add']").click(function() {
		_self.editObj = null;
		Common.formReset("#form", _self.editObj);
		$('#editWin').window({iconCls:'fa fa-plus', title:'新增'});
		$('#editWin').window({iconCls:'fa fa-plus', title:'新增'});
		$('#editWin').window('open');
	});

	$("#resToolbar a[name='edit']").click(function(){
		$(".idArea").hide();
		_self.editObj = $("#resTable").datagrid("getSelected");
		$('#editWin').window({iconCls:'fa fa-edit', title:'修改'});
		Common.formReset("#form", _self.editObj);
		$('#editWin').window('open');
	});

	$("#resToolbar a[name='del']").click(function(){
		_self.editObj = $("#resTable").datagrid("getSelected");
		_self.edit("删除", {available: "0", resId: _self.editObj.resId});
	});
	$("#resToolbar a[name='unDel']").click(function(){
		_self.editObj = $("#resTable").datagrid("getSelected");
		_self.edit("撤销删除", {available: "1", resId: _self.editObj.resId});
	});
	
	$("a[name='saveWin']").click(_self.saveWin);
	$("a[name='resetWin']").click(function(){
		Common.formReset("#form", _self.editObj);
	});

	$("a[name='relateUrl']").click(function(){
		$("a[name='sUrlPath']").searchbox('setValue', '');
		$("#dataTableUrlLeft").datagrid('load', _self.getSearchUrlParams());
		$("#dataTableUrlRight").datagrid('loadData', Common.deepCopy($("#urlTable").datagrid('getData')));
		$('#relateUrl').window('open');
	});
	
	$("a[name='chooseUrl']").click(function(){
		var list = $("#dataTableUrlLeft").datagrid('getSelections');
		for(var i = 0; i < list.length; i ++) {
			var obj = list[i];
			if(-1 != $("#dataTableUrlRight").datagrid('getRowIndex', obj.urlId)){ continue;}
			$("#dataTableUrlLeft").datagrid('deleteRow', $("#dataTableUrlLeft").datagrid('getRowIndex', obj));
			$("#dataTableUrlRight").datagrid('appendRow', obj);
		}
		$("#dataTableUrlLeft").datagrid("unselectAll");
	});
	
	$("a[name='delUrl']").click(function(){
		var list = $("#dataTableUrlRight").datagrid('getSelections');
		for(var i = list.length - 1; i >=0 ; i --) {
			var obj = list[i];
			$("#dataTableUrlRight").datagrid('deleteRow', $("#dataTableUrlRight").datagrid('getRowIndex', obj));
			$("#dataTableUrlLeft").datagrid('appendRow', obj);
		}
		$("#dataTableUrlRight").datagrid("unselectAll");
	});
	
	$("a[name='saveUrlWin']").click(function(){
		
		var before = $("#urlTable").datagrid('getRows');
		var list = $("#dataTableUrlRight").datagrid('getRows');
		if(0 == before.length + list.length){
			Common.showInfo("success", "分配URL成功！");
			$('#relateUrl').window('close');
			return;
		}
		
		var data = {resId: _self.treeSelected.resId};
		var ids = "";
		for(var i = 0; i < list.length; i ++){
			ids += list[i].urlId + ","
		}
		if(0 < ids.length){
			ids = ids.substr(0, ids.length - 1);
		}
		data.urlIds = ids;
		
		$.ajax({
			url:Common.getControllerPath() + "saveResUrl.do",
			data:data,
			success:function(data) {
				if(data != 0) {
					$("#urlTable").datagrid('loadData', Common.deepCopy($("#dataTableUrlRight").datagrid('getData')));
					Common.showInfo("success", "分配URL成功！");
					$('#relateUrl').window('close');
				} else {
					Common.showInfo("warning", "分配URL失败！");
				}
			}
		});
	});
	
	$("a[name='resetUrlWin']").click(function(){
		$("a[name='sUrlPath']").searchbox('setValue', '');
		$("#dataTableUrlLeft").datagrid('load', _self.getSearchUrlParams());
		$("#dataTableUrlRight").datagrid('loadData', Common.deepCopy($("#urlTable").datagrid('getData')));
	});
	
	
	$("a[name='relateRole']").click(function(){
		$("a[name='sRoleName']").searchbox('setValue', '');
		$("#dataTableRoleLeft").datagrid('load', _self.getSearchRoleParams());
		$("#dataTableRoleRight").datagrid('loadData', Common.deepCopy($("#roleTable").datagrid('getData')));
		$('#relateRole').window('open');
	});
	
	$("a[name='chooseRole']").click(function(){
		var list = $("#dataTableRoleLeft").datagrid('getSelections');
		for(var i = 0; i < list.length; i ++) {
			var obj = list[i];
			if(-1 != $("#dataTableRoleRight").datagrid('getRowIndex', obj.roleId)){ continue;}
			$("#dataTableRoleLeft").datagrid('deleteRow', $("#dataTableRoleLeft").datagrid('getRowIndex', obj));
			$("#dataTableRoleRight").datagrid('appendRow', obj);
		}
		$("#dataTableRoleLeft").datagrid("unselectAll");
	});
	
	$("a[name='delRole']").click(function(){
		var list = $("#dataTableRoleRight").datagrid('getSelections');
		for(var i = list.length - 1; i >=0 ; i --) {
			var obj = list[i];
			$("#dataTableRoleRight").datagrid('deleteRow', $("#dataTableRoleRight").datagrid('getRowIndex', obj));
			$("#dataTableRoleLeft").datagrid('appendRow', obj);
		}
		$("#dataTableRoleRight").datagrid("unselectAll");
	});
	

	$("a[name='saveRoleWin']").click(function(){
		var before = $("#roleTable").datagrid('getRows');
		
		var list = $("#dataTableRoleRight").datagrid('getRows');
		
		if(0 == before.length + list.length){
			Common.showInfo("success", "分配角色成功！");
			$('#relateRole').window('close');
			return;
		}
		
		var data = {resId: _self.treeSelected.resId};
		var ids = "";
		for(var i = 0; i < list.length; i ++){
			ids += list[i].roleId + ","
		}
		if(0 < ids.length){
			ids = ids.substr(0, ids.length - 1);
		}
		data.roleIds = ids;
		
		$.ajax({
			url:Common.getControllerPath() + "saveResRole.do",
			data:data,
			success:function(data) {
				if(data != 0) {
					$("#roleTable").datagrid('loadData', Common.deepCopy($("#dataTableRoleRight").datagrid('getData')));
					Common.showInfo("success", "分配角色成功！");
					$('#relateRole').window('close');
				} else {
					Common.showInfo("warning", "分配角色失败！");
				}
			}
		});
	});
	
	$("a[name='resetRoleWin']").click(function(){
		$("a[name='sRoleName']").searchbox('setValue', '');
		$("#dataTableRoleLeft").datagrid('load', _self.getSearchRoleParams());
		$("#dataTableRoleRight").datagrid('loadData', Common.deepCopy($("#roleTable").datagrid('getData')));
	});
	
	$("#searchTree").searchbox({
		searcher: function(value,name){
			$("#resTree").tree("search", value);
		},
		prompt:'输入后回车搜索资源'
	});
	

	$("#sUrlPath").searchbox({
		searcher: function(value,name){
			$("#dataTableUrlLeft").datagrid('load', _self.getSearchUrlParams());
		}
	});

	$("#sRoleName").searchbox({
		searcher: function(value,name){
			$("#dataTableRoleLeft").datagrid('load', _self.getSearchUrlParams());
		}
	});
};

/**
 * 保存字段修改
 * @param type
 * @param $data
 */
Page.prototype.edit = function(type, $data){
	$.ajax({
		url:Common.getControllerPath() + "edit.do",
		data:$data,
		success:function(data) {
			if(data) {
				$("#resTree").tree('reload');
				Common.showInfo("success", type + "成功");
			} else {
				Common.showInfo("warning", type + "失败");
			}
		}
	});
};

Page.prototype.saveWin = function(){
	if($("#form").form("validate")){
		var data = Common.formData("#form");
		if(_self.editObj){
			var changed = false;
			var temp = JSON.parse(JSON.stringify(_self.editObj));
			for(var i in data){
				if(temp[i] == data[i] || "" == data[i]){
					delete temp[i];
				} else {
					changed = true;
					temp[i] = data[i];
				}
			}
			if(!changed) {
				Common.showInfo("warning", "请修改后保存！");
				return;
			}
			data = temp;
		} else {
			data.parentId = _self.treeSelected.resId;
			data.parentTreePath = _self.treeSelected.treePath;
		}
		url = Common.getControllerPath() + 
			(null == _self.editObj ? "add.do" : "edit.do");
		$.ajax({
			url:url,
			data:data,
			success:function(flag) {
				if(flag) {
					$('#editWin').window('close');
					$("#resTree").tree('reload');
					Common.showInfo("success",
							(null == _self.editObj ? "添加成功" : "编辑成功"));
				} else {
					Common.showInfo("warning",
							(null == _self.editObj ? "添加失败" : "编辑失败"));
				}
			}
		});
	}
};

/**
 * 格式化返回的树数据
 * @param nodes
 */
Page.prototype.formatTreeData = function(nodes){
	var result = [];
	
	var map = {};
	for(var i = 0; i < nodes.length; i ++){
		var node = nodes[i];
		node.id = node.resId;
		node.text = node.resName;
		map[node.resId] = node;
	}
	
	for(i = 0; i < nodes.length; i ++){
		var node = nodes[i];
		if(node.parentId){
			var p = map[node.parentId];
			if(p){
				var children = p.children;
				if(!children){
					children = [];
					p.children = children;
				}
				children.push(node);
				continue;
			}
		}
		result.push(node);
	}
	
	var root = {id:"______root_______", text: "虚拟根节点", children:result};
	
	map[root.id] = root;
	_self.treeMap = map;
	return [root];
}

/**
 * 生成资源树
 */
Page.prototype.createTree = function(){
	//形成树
	$("#resTree").tree({
		url:Common.getControllerPath() + "get.do",
		loadFilter: function(data){
			return _self.formatTreeData(data);
		},
		formatter: function(node){
			var data = _self.treeMap[node.id];
			return node.text + ('1' != data.available && data.resId ? "<span class='l-btn-disabled'> - 已删除</span>" : "");
		},
		//树节点点击事件
		onClick:function(node){
			if(_self.treeSelected.resId != node.id){
				_self.treeSelected = _self.treeMap[node.id];
				_self.refreshDatagrids();
			}
		},
		onLoadSuccess: function(){
			var root = $("#resTree").tree('getRoot');
			nodes = $("#resTree").tree('getChildren',root.target);
			if(0 != nodes.length ){
				/* 选中第一个叶子节点 */
				$("#resTree").tree("select",nodes[0].target);
				_self.treeSelected = _self.treeMap[nodes[0].id];
			} else {
				_self.treeSelected = _self.treeMap[root.id];
				$("#resTree").tree("select",root.target);
			}
			_self.initDataGrids();
		}
	});
};
