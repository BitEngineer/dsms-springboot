/**
 * 码表条目信息管理
 * @author 郭宇航
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
	this.urlParams = Common.getUrlParams();
	
	this.dataTotal = 3000;

	this.deleteFlag = {"0":"删除","1":"有效"};
	this.supportLevel = {"0":"否","1":"是"};
	
	this.editObj = null;

	Common.setSelectOption($("#supportLevel"), _self.supportLevel);
	Common.setSelectOption($("#sDeleteFlag"), _self.deleteFlag);
	$("#sDeleteFlag").combobox('setValue', '1');
	
	this.initDataGrid();
};

/**
 * 获取查询条件
 * @author 李岳南
 */
Page.prototype.getSearchParams = function() {
	return Common.formData("#searchHead", "s");
};

/**
 * 初始化数据集
 */
Page.prototype.initDataGrid = function() {
	var pageSize = parseInt((window.parent.$(".panel-fit").height() -225)/25);	
	$("#dataTable").datagrid({
		url:Common.getControllerPath() + "get.do",
		columns : [[
            {field:"codetypeId", hidden:true},
            {field:"codetypeCode", title:"字典类型编码" , sortable:true},
            {field:"codetypeName", title:"字典类型名称" , sortable:true},
            {field:"supportLevel", title:"是否支持层级", sortable:true,
				formatter:function(value) {
					return _self.supportLevel[value];
				}
            },
            {field:"orderNo", title:"排序号" , sortable:true},
            {field:"deleteFlag", title:"删除标志", sortable:true,
				formatter:function(value) {
					return _self.deleteFlag[value];
				}
            },
            {field:"description", title:"报表描述" , sortable:false},
            {field:"updateUser", title:"最后修改人" , sortable:true},
            {field:"updateTime", title:"最后修改时间" , sortable:true}
		]],
		loadFilter : function(data) {
			$("#toolbar a[name='edit']").linkbutton("disable");
			$("#toolbar a[name='del']").linkbutton("disable");
			$("#toolbar a[name='unDel']").linkbutton("disable");
			$("#toolbar a[name='maintain']").linkbutton("disable");
			return _self.processData(data);
		},
	    onSelect:function(index, row){
			if("0" == row.deleteFlag){
				$("#toolbar a[name='del']").linkbutton("disable");
				$("#toolbar a[name='unDel']").linkbutton("enable");
				$("#toolbar a[name='maintain']").linkbutton("disable");
			} else {
				$("#toolbar a[name='edit']").linkbutton("enable");
				$("#toolbar a[name='del']").linkbutton("enable");
				$("#toolbar a[name='unDel']").linkbutton("disable");
				$("#toolbar a[name='maintain']").linkbutton("enable");
			}
	    },
		pageSize:pageSize,
		pageList:[pageSize],
		toolbar:"#toolbar",
		queryParams : _self.getSearchParams()
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

/**
 * 事件监听
 */
Page.prototype.bindEvent = function() {
	$("a[name='search']").click(function() {
		$("#dataTable").datagrid("load", _self.getSearchParams());
	});
	
	$("a[name='reset']").click(function() {
		Common.formReset("#searchHead",{deleteFlag: '1'},  "s");
	});

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

	$("#toolbar a[name='del']").click(function(){
		var data =  $("#dataTable").datagrid("getSelected");
		data.deleteFlag = "0";
		_self.edit("删除", data)
	});
	$("#toolbar a[name='unDel']").click(function(){
		var data =  $("#dataTable").datagrid("getSelected");
		data.deleteFlag = "1";
		_self.edit("撤销删除", data)
	});

	$("#toolbar a[name='maintain']").click(function(){
		var data =  $("#dataTable").datagrid("getSelected");
		var title = "字典维护-"+data.codetypeName;
		var url = "views/app/codeinfo.jsp?codetype=" + JSON.stringify(data);
		Common.addTabs(title, url);
	});
	$("a[name='saveWin']").click(_self.saveWin);
	$("a[name='resetWin']").click(function(){
		Common.formReset("#form", _self.editObj);
	});

};

/**
 * 保存字段修改
 * @param type
 * @param data
 */
Page.prototype.edit = function(type, data){
	$.ajax({
		url:Common.getControllerPath() + "edit.do",
		data:data,
		success:function(data) {
			if(data != 0) {
				$("#dataTable").datagrid("reload",  _self.getSearchParams());
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
		
		if(null != _self.editObj){
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
		}
		url = Common.getControllerPath() + 
			(null == _self.editObj ? "add.do" : "edit.do");
		$.ajax({
			url:url,
			data:data,
			success:function(flag) {
				if(flag) {
					$('#editWin').window('close');
					$("#dataTable").datagrid("reload",  _self.getSearchParams());
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
