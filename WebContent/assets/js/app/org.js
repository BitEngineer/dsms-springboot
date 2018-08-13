/**
 * 机构管理
 * @author 岳文君
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
	this.rootUri = "/app/org/";
	this.dataTotal = 8;
	this.staffDataTotal = 5;
	
	this.orgCodeUniqueness = false;
	this.orgNameUniqueness = false;
	this.orgFullNameUniqueness = false;

	this.status = {"01":"生效","02":"失效"};
	this.deleteFlag = {"0":"删除","1":"有效"};
	this.orgClass = {"01":"总行","02":"分行","03":"支行","04":"部门"};
	this.orgType = {"11":"总行","21":"省直分行","22":"省分行","23":"省辖分行","24":
		"海外分行","31":"直属支行","32":"一级支行","33":"二级支行","34":"异地支行","41":"一级部门","42":"二级部门"};
	this.orgLevel = {"1":"level1","2":"level2"};
	this.countryCode = {"100010":"中国","100011":"美国"}
	this.provinceCode = {"100010":"湖南","100011":"北京"}
	this.cityCode = {"100010":"长沙","100011":"上海"}
	this.countyCode = {"100010":"岳麓区","100011":"天心区"}
	this.directly = {"0":"否","1":"是"}
	this.peopleStatus = {"0":"注销","1":"正常","2":"锁定"};
	
	$("#sDeleteFlag").combobox('setValue', '1');
	$("#sStatus").combobox('setValue', '01');
	Common.setSelectOption($("#sPeopleStatus"), _self.peopleStatus);
	Common.setSelectOption($("#sDeleteFlag"), _self.deleteFlag);
	Common.setSelectOption($("#sStatus"), _self.status);
	Common.setSelectOption($("#status"), _self.status);
	Common.setSelectOption($("#deleteFlag"), _self.deleteFlag);
	Common.setSelectOption($("#orgClass"), _self.orgClass);
	Common.setSelectOption($("#orgType"), _self.orgType);
	Common.setSelectOption($("#orgLevel"), _self.orgLevel);
	Common.setSelectOption($("#countryCode"), _self.countryCode);
	Common.setSelectOption($("#provinceCode"), _self.provinceCode);
	Common.setSelectOption($("#cityCode"), _self.cityCode);
	Common.setSelectOption($("#countyCode"), _self.countyCode);
	Common.setSelectOption($("#directly"), _self.directly);
	
	this.editObj = null;
	this.currentNode = null;
	this.orgId = null;
	this.orgName = "";
	this.treePath = "";
	
	this.initOrgTree();
//	this.initDataGrid();
};


/**
 * 获取查询条件
 */
Page.prototype.getSearchParams = function() {
	return Common.formData("#searchHead", "s");
};

Page.prototype.getPeopleSearchParams = function() {
	return Common.formData("#peopleSearchHead", "s");
};

Page.prototype.getStaffSearchParams = function() {
	return Common.formData("#staffSearchHead", "sPeople");
};

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
			"parentId" : data[i].parentId,
			"treePath":data[i].treePath
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

Page.prototype.generateTree = function(){
	$.ajax({
		url:Common.getRootPath()+"/app/org/getTree.do",
		type:"post",
		success:function(result){
			//数据整理
			var data = _self.formatTreeData(result);
	
			//形成树
			$("#orgTree").tree({
				data:data,
				onClick:function(node){
					_self.currentNode=node;
					_self.orgId=node.id;
					_self.orgName=node.text;
					_self.treePath=node.treePath;
					_self.loadDataGrid(node.id);
				},
				onLoadSuccess:function(node,data){
					//设置当前节点高亮
					if(_self.currentNode != null){
						$(_self.currentNode.target).addClass("tree-node-selected");
					}else{
						$("#orgTree li:eq(0)").find("div").addClass("tree-node-selected");      
				        var rootNode = $("#orgTree").tree("getSelected");   
				        if(rootNode!=null){   
				        	//相当于默认点击了一下第一个节点，执行onSelect方法
				        	$("#orgTree").tree("select",rootNode.target);
				        	//模拟点击根节点
				        	//初始化机构和人员界面
				        //	$(rootNode.target).trigger("click");
				        }  
					}
				}
			});
		}
	});	
};

//TODO
/**
 * 初始化机构树--异步加载
 */
Page.prototype.initOrgTree = function(){
	//初始化
	var data = {"parentId":'-1'};
	$.ajax({
		url : "app/org/findOrgByParent.do",
		data : data,
		type : "post",
		success : function(result) {
			//数据整理
			var data = new Array();
			data.push({"id":result[0].orgId,"text":result[0].orgName,"parentId":result[0].parentId,
				"treePath":result[0].treePath,"state":"closed"});
		
			// 形成树
			$("#orgTree").tree({
				data : data,
				onClick:function(node){
					_self.currentNode = node;
					_self.orgId=node.id;
					_self.orgName=node.text;
					_self.loadDataGrid(node.id);
				},
			});
			
			//树形成之后，初始化机构
			_self.initDataGrid();
		}
	});
	
	//为树节点增加展开方法
	$("#orgTree").tree({
		onBeforeExpand:function(node){
			var currentNode = node;
		
			$.ajax({
				url: "app/org/findOrgByParent.do",
				type:"post",
				data:{"parentId":node.id},
				async: false,
				success:function(result){
					if(result.length > 0){
						var children = new Array();
						for(var i=0;i<result.length;i++){
							var child = {"id":result[i].orgId,"text":result[i].orgName,"parentId":result[i].parentId,
									"treePath":result[i].treePath,"state":"closed"};
							children.push(child);
						}
						//清空之前追加的子节点
						var preChildren = $('#orgTree').tree('getChildren', currentNode.target);
						for(var j=0;j<preChildren.length;j++){
							$('#orgTree').tree('remove', preChildren[j].target);
						}
						//追加子节点到父节点
						$('#orgTree').tree('append', {
							parent: currentNode.target,
							data: children
						});	
					}else{  //说明是叶子节点，修改样式
						/*
						 $(icons[0]).addClass("tree-file");
		                 $(icons[0]).removeClass("tree-offline");
		                 */
						$(currentNode.target).find("span.tree-icon").removeClass("tree-folder");
						$(currentNode.target).find("span.tree-icon").removeClass("tree-folder-open");
						$(currentNode.target).find("span.tree-hit").remove();
						$(currentNode.target).find("span.tree-icon").addClass("tree-file");
					}
				}
			});
        }
	});
};

/**
 * 初始化数据集
 */
Page.prototype.initDataGrid = function(){
	//设置根节点高亮
	$("#orgTree li:eq(0)").find("div").addClass("tree-node-selected");    
	var rootNode = $("#orgTree").tree("getSelected");  
	if(rootNode != null){
		//单击根节点
		$(rootNode.target).trigger("click");
	}  
}

/**
 * 载入数据集
 */
Page.prototype.loadDataGrid = function() {
	//初始化人员界面
	var pageSize = 20;
	$("#peopleDataTable").datagrid({
		url:Common.getRootPath() + "/app/org/getUser.do?orgId="+_self.orgId,
		singleSelect: false,  
		columns : [[
		            {field:"ck", checkbox:true},
		            {field:"staffId", hidden:true},
		            {field:"staffCode", title:"用户编号" ,width:50, align:'center', sortable:true},
		            {field:"staffName", title:"用户名称" , width:50, align:'center', sortable:true},
		            {field:"orgId", hidden:true},
		            {field:"orgName", title:"所属机构" ,  width:50,align:'center', sortable:false},
		            {field:"status", title:"用户状态", width:50, align:'center', sortable:true,
						formatter:function(value) {
							return _self.peopleStatus[value];
						}
		            }
		            
		]],
		loadFilter : function(data) {
			$("#peopelToolbar a[name='delPeople']").linkbutton("disable");
			return _self.processData(data);
		},
		onSelect:function(index, row){
			$("#peopleToolbar a[name='delPeople']").linkbutton("enable");
		},
		onLoadSuccess:function(){
	    	$("#sCurrentOrg").html(_self.orgName);
	    },
		pageSize:pageSize,
		pageList:[pageSize],
		toolbar:"#peopleToolbar",
		queryParams : _self.getStaffSearchParams()
	});
	//初始化机构界面
	$("#dataTable").datagrid({
		url:Common.getRootPath() + _self.rootUri + "get.do?parentId="+_self.orgId,
		columns : [[
            {field:"orgId", hidden:true},
            {field:"treePath", hidden:true},
            {field:"parentId", hidden:true},
            {field:"orgCode", title:"机构编号" ,align:'center', sortable:true},
            {field:"orgName", width:50,title:"机构名称" , align:'center', sortable:true},
            {field:"orgFullName",width:70,title:"机构全称" , align:'center', sortable:true},
            {field:"parentName", width:50,title:"所属机构" , align:'center', sortable:true},
            {field:"orgType", title:"机构类型" , align:'center', sortable:true,
            	formatter:function(value) {
					return _self.orgType[value];
				}
            },
            {field:"status", title:"机构状态",align:'center', sortable:true,
				formatter:function(value) {
					return _self.status[value];
				}
            },
            {field:"deleteFlag", title:"删除标志",align:'center', sortable:true,
				formatter:function(value) {
					return _self.deleteFlag[value];
				}
            }
		]],
		loadFilter : function(data) {
			$("#toolbar a[name='edit']").linkbutton("disable");
			$("#toolbar a[name='del']").linkbutton("disable");
			$("#toolbar a[name='unDel']").linkbutton("disable");
			return _self.processData(data);
		},
	    onSelect:function(index, row){
			$("#toolbar a[name='edit']").linkbutton("enable");
			$("#toolbar a[name='del']").linkbutton("enable");
			if("0" == row.deleteFlag){
				$("#toolbar a[name='del']").linkbutton("disable");
				$("#toolbar a[name='unDel']").linkbutton("enable");
			} else {
				$("#toolbar a[name='del']").linkbutton("enable");
				$("#toolbar a[name='unDel']").linkbutton("disable");
			}
	    },
	    onLoadSuccess:function(){
	    	$("#oCurrentOrg").html(_self.orgName);
	    },
		pageSize:pageSize,
		pageList:[pageSize],
		toolbar:"#toolbar",
		queryParams : _self.getSearchParams()
	});
	
};


Page.prototype.initSearchStaffDataGrid = function() {
	var pageSize = 8;
	$("#peopleSearchDataTable").datagrid({
		url:Common.getRootPath() + "/app/org/searchUser.do",
		singleSelect: false,
		columns : [[
		            {field:"ck", checkbox:true},
		            {field:"staffId", hidden:true},
		            {field:"staffCode", title:"用户编号" , align:'center', sortable:true},
		            {field:"staffName", title:"用户名称" , align:'center', sortable:true},
		            {field:"orgId", hidden:true},
		            {field:"orgName", title:"所属机构" , align:'center', sortable:false},
		            {field:"status", title:"用户状态", width:50, align:'center', sortable:true,
						formatter:function(value) {
							return _self.peopleStatus[value];
						}
		            },
		]],
		loadFilter : function(data) {
			return _self.processSearchStaffData(data);
		},
		pageSize:pageSize,
		pageList:[pageSize],
		queryParams : _self.getPeopleSearchParams()
	});
};

/**
 * 处理datagrid的数据
 * @param data 待处理的数据
 * @returns rows 待展示的数据
 * @returns total 总记录数
 */
Page.prototype.processData = function(data) {
	if(!jQuery.isArray(data)) {
		return data;
	}
	_self.dataTotal = Common.getDataTotal($("#dataTable"), 
			_self.dataTotal, data.length);
	return {
		rows : data,
		total : _self.dataTotal
	};
};

Page.prototype.processSearchStaffData = function(data) {
	if(!jQuery.isArray(data)) {
		return data;
	}
	_self.staffDataTotal = Common.getDataTotal($("#peopleSearchDataTable"), 
			_self.staffDataTotal, data.length);
	return {
		rows : data,
		total : _self.staffDataTotal
	};
};

/**
 * 事件监听
 */
Page.prototype.bindEvent = function() {
	//机构树搜索
	/*
	$("#searchTree").searchbox({
		searcher: function(value,name){
			$("#orgTree").tree("search", value);
		},
		prompt:'输入后回车搜索菜单'
	});
	*/
	//异步
	$("#searchTree").searchbox({
		searcher: function(value,name){
			if(value != ""){
				//获取搜索数据
				$.ajax({
					url : "app/org/searchTree.do",
					data : {orgName:value},
					type : "post",
					success : function(result) {
						//形成树结构数据
						var data = _self.formatTreeData(result);
						
						//形成搜索机构树
						$("#orgTree").tree({
							data : data,
							onClick : function(node) {
								_self.currentNode = node;
								_self.orgId = node.id;
								_self.orgName = node.text;
								_self.loadDataGrid(node.id);
							} 
						});	
					}
				});	
			}else{
				_self.initOrgTree();
			}	
		},
		prompt:'输入后回车搜索机构'
	});
	
	//工具栏事件绑定
	//机构查询
	$("a[name='search']").click(function() {
		$("#dataTable").datagrid("load", _self.getSearchParams());
	});
	//查询重置
	$("a[name='reset']").click(function() {
		Common.formReset("#searchHead",{deleteFlag: '1'},  "s");
	});
	//机构新增
	$("#toolbar a[name='add']").click(function() {
		//判断父机构是否为空
		if(_self.orgId == "" || _self.orgId == null){
			Common.showInfo("warning", "先选中所属机构");
		}else{
			$(".idArea").show();
			_self.editObj = null;
			Common.formReset("#form", _self.editObj);
			$('#editWin').window({iconCls:'fa fa-plus', title:'新增'});
			$("#buttons").show();
			$('#editWin').window('open');
		}
	});
	//机构编辑
	$("#toolbar a[name='edit']").click(function(){
		$(".idArea").hide();
		_self.editObj = $("#dataTable").datagrid("getSelected");
		$('#editWin').window({iconCls:'fa fa-edit', title:'修改'});
		Common.formReset("#form", _self.editObj);
		$("#buttons").show();
		$('#editWin').window('open');
	});
	//机构删除
	$("#toolbar a[name='del']").click(function(){
		//TODO
		//校验存在子机构的节点不能被删除
		var data =  $("#dataTable").datagrid("getSelected");
		var exist = _self.hasChildren(data);
		if(!exist){
			data.deleteFlag = "0";
			_self.edit("删除", data)
		}else{
			Common.showInfo("warning", "存在下级机构，不能删除");
		}
	});
	//删除恢复
	$("#toolbar a[name='unDel']").click(function(){
		var data =  $("#dataTable").datagrid("getSelected");
		data.deleteFlag = "1";
		_self.edit("恢复", data)
	});
	
	$("a[name='resetOrg']").click(function() {
		Common.formReset("#searchHead",null);
	});

    //机构保存按钮
	$("a[name='saveWin']").click(_self.saveWin);
	
	//机构重置按钮
	$("a[name='resetWin']").click(function(){
		Common.formReset("#form", _self.editObj);
	});
	
	//员工查询
	$("a[name='staffSearch']").click(function() {
		var staffSearchParams = _self.getStaffSearchParams();
		$("#peopleDataTable").datagrid("load", staffSearchParams);
	});

	$("a[name='staffReset']").click(function() {
		Common.formReset("#staffSearchHead",{deleteFlag: '1'},  "s");
	});

	//人员添加按钮
	$("#peopleToolbar a[name='addPeople']").click(function() {
		$(".idArea").show();
		$('#peopleWin').window({iconCls:'fa fa-plus', title:'添加人员'});
		$('#peopleWin').window('open');
		_self.initSearchStaffDataGrid();
	});
    
	//人员移除按钮
	$("#peopleToolbar a[name='delPeople']").click(function(){
		var data =  $("#peopleDataTable").datagrid("getSelections");
		data.orgId = "0";
		_self.editPeople("移除", data);
	});

	//用户查询按钮
	$("a[name='peopleSearch']").click(function() {
		var pSearchParams = _self.getPeopleSearchParams();
		$("#peopleSearchDataTable").datagrid("reload",  pSearchParams);
	});
	
	//用户查询重置按钮
	$("a[name='peopleReset']").click(function(){
		Common.formReset("#searchPeople", null);
	});
	
	//人员确认按钮
	$("a[name='peopleSaveWin']").click(function(){
		var data =  $("#peopleSearchDataTable").datagrid("getSelections");
		data.orgId = _self.orgId;
		_self.editPeople("添加", data)
		$('#peopleWin').window('close');
	});
	
	//人员取消按钮
	$("a[name='peopleCloseWin']").click(function(){
		$('#peopleWin').window('close');
	});
	
	//唯一性校验
	/*
	 $("#editWin #orgCode").next().find(":text").blur(function () { 
		 var orgCode = $("#editWin #orgCode").textbox('getValue');
		 var title = $("#editWin").panel('options').title;
		 if(title == "修改"){
			 if(orgCode != _self.editObj.orgCode){
				 $.ajax({
					url:Common.getRootPath() + _self.rootUri + "check.do",
					data:{"orgCode":orgCode},
					success:function(data) {
						if(data != 0) {
							_self.orgCodeUniqueness = false;
							Common.showInfo("error", "机构编号已存在，请重新输入");
						}else{
							_self.orgCodeUniqueness = true;
						}
					}
				}); 
			 }else{
				 _self.orgCodeUniqueness = true;
			 }
		 }else if(title == "新增"){
			 $.ajax({
				url:Common.getRootPath() + _self.rootUri + "check.do",
				data:{"orgCode":orgCode},
				success:function(data) {
					if(data != 0) {
						_self.orgCodeUniqueness = false;
						Common.showInfo("error", "机构编号已存在，请重新输入");
					}else{
						_self.orgCodeUniqueness = true;
					}
				}
			}); 
		 } 
     });
	 $("#editWin #orgName").next().find(":text").blur(function () {
		 var orgName = $("#editWin #orgName").textbox('getValue');
		 var title = $("#editWin").panel('options').title;
		 if(title == "修改"){
			 if(orgName != _self.editObj.orgName){
				 $.ajax({
					url:Common.getRootPath() + _self.rootUri + "check.do",
					data:{"orgName":orgName},
					success:function(data) {
						if(data != 0) {
							_self.orgNameUniqueness = false;
							Common.showInfo("error", "机构名已存在，请重新输入");
						}else{
							_self.orgNameUniqueness = true;
						}
					}
				});
			 }else{
				 _self.orgNameUniqueness = true;
			 }
		 }else if(title == "新增"){
			 $.ajax({
				url:Common.getRootPath() + _self.rootUri + "check.do",
				data:{"orgName":orgName},
				success:function(data) {
					if(data != 0) {
						_self.orgNameUniqueness = false;
						Common.showInfo("error", "机构名已存在，请重新输入");
					}else{
						_self.orgNameUniqueness = true;
					}
				}
			});
		 } 
	 });
	 $("#editWin #orgFullName").next().find(":text").blur(function () {
		 var orgFullName = $("#editWin #orgFullName").textbox('getValue');
		 var title = $("#editWin").panel('options').title;
		 if(title == "修改"){
			 if(orgFullName != _self.editObj.orgFullName){
				 $.ajax({
					url:Common.getRootPath() + _self.rootUri + "check.do",
					data:{"orgFullName":orgFullName},
					success:function(data) {
						if(data != 0) {
							_self.orgFullNameUniqueness = false;
							Common.showInfo("error", "机构全名已存在，请重新输入");
						}else{
							_self.orgFullNameUniqueness = true;
						}
					}
				});
			 }else{
				 _self.orgFullNameUniqueness = true;
			 }
		 }else if(title == "新增"){
			 $.ajax({
				url:Common.getRootPath() + _self.rootUri + "check.do",
				data:{"orgFullName":orgFullName},
				success:function(data) {
					if(data != 0) {
						_self.orgFullNameUniqueness = false;
						Common.showInfo("error", "机构全名已存在，请重新输入");
					}else{
						_self.orgFullNameUniqueness = true;
					}
				}
			});
		 } 
	 });
	 */
};

//TODO
//校验功能重写
Page.prototype.checkOrgCode = function () { 
	 var orgCode = $("#editWin #orgCode").textbox('getValue');
	 var title = $("#editWin").panel('options').title;
	 if(title == "修改"){
		 if(orgCode != _self.editObj.orgCode){
			 $.ajax({
				url:Common.getRootPath() + _self.rootUri + "check.do",
				data:{"orgCode":orgCode},
				async:false,
				success:function(data) {
					if(data != 0) {
						_self.orgCodeUniqueness = false;
						Common.showInfo("error", "机构编号已存在，请重新输入");
					}else{
						_self.orgCodeUniqueness = true;
					}
				}
			}); 
		 }else{
			 _self.orgCodeUniqueness = true;
		 }
	 }else if(title == "新增"){
		 $.ajax({
			url:Common.getRootPath() + _self.rootUri + "check.do",
			data:{"orgCode":orgCode},
			async:false,
			success:function(data) {
				if(data != 0) {
					_self.orgCodeUniqueness = false;
					Common.showInfo("error", "机构编号已存在，请重新输入");
				}else{
					_self.orgCodeUniqueness = true;
				}
			}
		}); 
	 } 
}

Page.prototype.checkOrgName = function () {
	 var orgName = $("#editWin #orgName").textbox('getValue');
	 var title = $("#editWin").panel('options').title;
	 if(title == "修改"){
		 if(orgName != _self.editObj.orgName){
			 $.ajax({
				url:Common.getRootPath() + _self.rootUri + "check.do",
				data:{"orgName":orgName},
				async:false,
				success:function(data) {
					if(data != 0) {
						_self.orgNameUniqueness = false;
						Common.showInfo("error", "机构名已存在，请重新输入");
					}else{
						_self.orgNameUniqueness = true;
					}
				}
			});
		 }else{
			 _self.orgNameUniqueness = true;
		 }
	 }else if(title == "新增"){
		 $.ajax({
			url:Common.getRootPath() + _self.rootUri + "check.do",
			data:{"orgName":orgName},
			async:false,
			success:function(data) {
				if(data != 0) {
					_self.orgNameUniqueness = false;
					Common.showInfo("error", "机构名已存在，请重新输入");
				}else{
					_self.orgNameUniqueness = true;
				}
			}
		});
	 } 
}

Page.prototype.checkOrgFullName = function () {
	 var orgFullName = $("#editWin #orgFullName").textbox('getValue');
	 var title = $("#editWin").panel('options').title;
	 if(title == "修改"){
		 if(orgFullName != _self.editObj.orgFullName){
			 $.ajax({
				url:Common.getRootPath() + _self.rootUri + "check.do",
				data:{"orgFullName":orgFullName},
				async:false,
				success:function(data) {
					if(data != 0) {
						_self.orgFullNameUniqueness = false;
						Common.showInfo("error", "机构全名已存在，请重新输入");
					}else{
						_self.orgFullNameUniqueness = true;
					}
				}
			});
		 }else{
			 _self.orgFullNameUniqueness = true;
		 }
	 }else if(title == "新增"){
		 $.ajax({
			url:Common.getRootPath() + _self.rootUri + "check.do",
			data:{"orgFullName":orgFullName},
			async:false,
			success:function(data) {
				if(data != 0) {
					_self.orgFullNameUniqueness = false;
					Common.showInfo("error", "机构全名已存在，请重新输入");
				}else{
					_self.orgFullNameUniqueness = true;
				}
			}
		});
	 } 
}

/**
 * 删除机构时，判断是否存在子机构
 * 
 */
Page.prototype.hasChildren = function(data){
	var exist = true;
	$.ajax({
		url:"app/org/findOrgByParent.do",
		type:"post",
		data:{"parentId":data.orgId},
		async: false,
		success:function(result) {
			if(result.length == 0){
				exist = false;
			}
		}
	});
	return exist;
}

//TODO
//异步加载后，新增、编辑、删除的回调逻辑需要改变
/**
 * 保存字段修改
 * @param type
 * @param data
 */
Page.prototype.edit = function(type, data){
	$.ajax({
		url:Common.getRootPath() + _self.rootUri + "edit.do",
		data:data,
		success:function(result) {
			if(result != 0) {
				$("#dataTable").datagrid("reload",  _self.getSearchParams());
				//TODO
				//删除成功，移除节点
				if(type == "删除"){
					//判断父节点(当前节点)是否展开
					var children = $('#orgTree').tree('getChildren', _self.currentNode.target);
					if(children.length != 0){  //展开了
						_self.editObj = $("#dataTable").datagrid("getSelected");
						//找到被编辑的节点
						var editNode = $('#orgTree').tree('find', _self.editObj.orgId);
						//删除节点
						$('#orgTree').tree('remove', editNode.target);
					}
				}
				//恢复删除成功，添加节点
				if(type == "恢复"){
					//判断父节点(当前节点)是否展开
					var children = $('#orgTree').tree('getChildren', _self.currentNode.target);
					if(children.length != 0){  //展开了
						//新增一个节点
						$('#orgTree').tree('append', {
							parent: _self.currentNode.target,
							data: [{
								id: data.orgId,
								text: data.orgName,
								parentId: data.parentId,
								treePath: data.treePath
							}]
						});
					}  
				}
				Common.showInfo("alert", type + "成功");
			} else {
				Common.showInfo("warning", type + "失败");
			}
		}
	});
};

/**
 * 新增编辑机构保存操作
 */
Page.prototype.saveWin = function() {
	if($("#form").form("validate")){  //必填字段校验
		//TODO
		//唯一性校验顺序
		_self.checkOrgCode();
		if(_self.orgCodeUniqueness){
			_self.checkOrgName();
			if(_self.orgNameUniqueness){
				_self.checkOrgFullName();
				if(_self.orgFullNameUniqueness){
					_self.saveEdit();
				}
			}
		}	
	}
};

Page.prototype.saveEdit = function(){
	var data = Common.formData("#form");
	data.parentId = _self.orgId;
	data.parentTreePath = _self.currentNode.treePath;

	if (null != _self.editObj) {
		data = $.extend(_self.editObj, data);
	}
	url = Common.getRootPath() + _self.rootUri
			+ (null == _self.editObj ? "add.do" : "edit.do");
	var action = "";
	if(null == _self.editObj){
		action = "add";
	}else{
		action = "edit";
	}
	$.ajax({
		url : url,
		data : data,
		success : function(result) {
			if (result) {
				$('#editWin').window('close');
				$("#dataTable").datagrid("reload", _self.getSearchParams());
				//TODO
				//编辑后，对树的修改，修改当前树节点属性
				if(action == "edit"){
					//判断父节点(当前节点)是否展开
					var children = $('#orgTree').tree('getChildren', _self.currentNode.target);
					if(children.length != 0){  //展开了
						//找到被编辑的节点
						var editNode = $('#orgTree').tree('find', _self.editObj.orgId);
						//更新节点数据，节点中的数据只有orgName会被修改
						$('#orgTree').tree('update', {
							target : editNode.target,
							text : data.orgName
						});
					}
				}
				//新增后，增加一个树节点
				if(action == "add"){
					//判断父节点(当前节点)是否展开
					var children = $('#orgTree').tree('getChildren', _self.currentNode.target);
					if(children.length != 0){  //展开了
						//新增一个节点
						$('#orgTree').tree('append', {
							parent: _self.currentNode.target,
							data: [{
								id: result.orgId,
								text: result.orgName,
								parentId: result.parentId,
								treePath: result.treePath
							}]
						});
					}
				}
				Common.showInfo("alert", (null == _self.editObj ? "添加成功" : "编辑成功"));
			} else {
				Common.showInfo("warning", (null == _self.editObj ? "添加失败" : "编辑失败"));
			}
		}
	});
}

/**
 * 保存人员字段修改，修改orgId
 */ 
Page.prototype.editPeople = function(type, data){
	var staffId=data[0].staffId;
	for(var i=1;i<data.length;i++){
		 staffId = staffId + "," + data[i].staffId;
	}	
	var data = {"staffId":staffId,"orgId":data.orgId};
	$.ajax({
		url:Common.getRootPath() + _self.rootUri + "editUsers.do",
		data:data,
		success:function(data) {
			if(data != 0){
				$("#peopleDataTable").datagrid("reload",  _self.getStaffSearchParams());
		    	Common.showInfo("alert", type + "成功");
			} else {
				Common.showInfo("warning", type + "失败");
			}		
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


$.extend($.fn.validatebox.defaults.rules, {   
	comboxValidate : {        
		validator : 
			function(value, param,missingMessage) {   
          if($('#'+param).combobox('getValue')!='' && $('#'+param).combobox('getValue')!=null)
		  {              
        	  return true;       
		  }         
		  return false;      
		  },       
		  message : "{1}"    
	} 
});

