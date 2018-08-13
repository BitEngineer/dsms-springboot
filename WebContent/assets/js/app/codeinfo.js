/**
 * 码表字典信息管理
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
	this.codetype = this.urlParams.codetype;
	this.parentCodes = {};
	if(this.codetype){
		$(".codetypeArea").hide();
		$("#sCodetypeCode").textbox("setValue", this.codetype.codetypeCode);
		_self.codeTypes = {};
		_self.codeTypes[this.codetype.codetypeId] = this.codetype;
		_self.codeTypeIds = {};
		_self.codeTypeIds[this.codetype.codetypeId] = this.codetype.codetypeCode;
		Common.setSelectOption($("#codetypeId"), _self.codeTypeIds);
		$("#codetypeId").combobox("setValue", this.codetype.codetypeId);
		_self.refreshParent();
	} else {
		$.ajax({
			url:Common.getRootPath() + "/app/codetype/get.do",
			data:{deleteFlag:'1', page:1, rows: 1000},
			success:function(data) {
				if(data) {
					_self.codeTypes = {};
					_self.codeTypeIds = {};
					for(var i = 0; i < data.length; i ++){
						var temp = data[i];
						_self.codeTypeIds[temp.codetypeId] = temp.codetypeCode;
						_self.codeTypes[temp.codetypeId] = temp;
					}
					Common.setSelectOption($("#codetypeId"), _self.codeTypeIds);
				} else {
					Common.showInfo("warning", "获取代码类型失败！");
				}
			}
		});
	}
	this.codetype = this.codetype || {};
	this.codetype.deleteFlag = '1';
	
	this.dataTotal = 3000;

	this.deleteFlag = {"0":"删除","1":"有效"};
	this.visible = {"0":"不可见","1":"可见"};
	
	this.editObj = null;

	Common.setSelectOption($("#visible"), _self.visible);
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
            {field:"codeinfoId", hidden:true},
            {field:"codeinfoCode", title:"字典编码" , sortable:true},
            {field:"codeinfoValue", title:"字典值" , sortable:true},
            {field:"codetypeCode", title:"字典类型编码" , sortable:true},
            {field:"codetypeName", title:"字典类型名称" , sortable:true},
            {field:"orderNo", title:"排序号" , sortable:true},
            {field:"rootCode", title:"根节点编码" , sortable:true},
            {field:"parentCode", title:"父节点编码" , sortable:true},
            {field:"codeLevel", title:"编码级别" , sortable:true},
            {field:"treePath", title:"全路径" , hidden:true},
            {field:"visible", title:"是否可见", sortable:true,
				formatter:function(value) {
					return _self.visible[value];
				}
            },
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
			return _self.processData(data);
		},
	    onSelect:function(index, row){
			if("0" == row.deleteFlag){
				$("#toolbar a[name='del']").linkbutton("disable");
				$("#toolbar a[name='unDel']").linkbutton("enable");
			} else {
				$("#toolbar a[name='edit']").linkbutton("enable");
				$("#toolbar a[name='del']").linkbutton("enable");
				$("#toolbar a[name='unDel']").linkbutton("disable");
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
		Common.formReset("#searchHead", _self.codetype,  "s");
	});

	$("#toolbar a[name='add']").click(function() {
		$(".idArea").show();
		_self.editObj = null;
		Common.formReset("#form", {codetypeId: _self.codetype.codetypeId});
		$('#editWin').window({iconCls:'fa fa-plus', title:'新增'});
		$('#editWin').window('open');
	});

	$("#toolbar a[name='edit']").click(function(){
		$(".idArea").hide();
		_self.editObj = $("#dataTable").datagrid("getSelected");
		$('#editWin').window({iconCls:'fa fa-edit', title:'修改'});
		Common.formReset("#form", _self.editObj);
		_self.refreshParent(_self.editObj.parentId);
		$('#editWin').window('open');
	});

	$("#toolbar a[name='del']").click(function(){
		_self.editObj = $("#dataTable").datagrid("getSelected");
		_self.edit("删除", {deleteFlag: "0", codeinfoId: _self.editObj.codeinfoId});
	});
	$("#toolbar a[name='unDel']").click(function(){
		_self.editObj = $("#dataTable").datagrid("getSelected");
		_self.edit("撤销删除", {deleteFlag: "1", codeinfoId: _self.editObj.codeinfoId});
	});

	$("a[name='saveWin']").click(_self.saveWin);
	$("a[name='resetWin']").click(function(){
		Common.formReset("#form", _self.editObj);
		_self.refreshParent();
		Common.formReset("#form", _self.editObj);
	});
	
	//选择父节点
	if(!_self.codetype){
		$("#codetypeId").combobox({
			onChange:function(){_self.refreshParent();}
		});
	}
};

Page.prototype.refreshParent = function(parentId){
	parentId = parentId || "";
	var codetypeId = $("#codetypeId").combobox('getValue');
	
	if("1" != _self.codeTypes[codetypeId].supportLevel){
		$(".parentArea").hide();
		return;
	}
	$(".parentArea").show();
	if(_self.parentCodes[codetypeId]){
		$("#parentId").combotree('loadData', _self.parentCodes[codetypeId]);
		$("#parentId").combotree('setValue', parentId);
		return;
	}
	$.ajax({
		async: false,
		url:Common.getControllerPath() + "getCodesTree.do",
		data:{codetypeId: codetypeId, idField: "codeinfoId"},
		success:function(data) {
			if(data) {
				_self.parentCodes[codetypeId] = data;
				$("#parentId").combotree('loadData', _self.parentCodes[codetypeId])
				$("#parentId").combotree('setValue', parentId);
			} else {
				Common.showInfo("warning", "获取可用父代码失败！");
			}
		}
	});
}


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
			if(data != 0) {
				$("#dataTable").datagrid("reload",  _self.getSearchParams());
				_self.parentCodes[_self.editObj.codetypeId] = null;
				_self.refreshParent();
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
			
			if(data.parentId){
				if(data.parentId == _self.editObj.codeinfoId){
					Common.showInfo("warning", "不能将父节点设置为自身！");
					return;
				}
				var node = $('#parentId').combotree('tree').tree('getSelected');
				data.rootId = node.rootId;
				data.treePath = node.treePath + "|" + data.codeinfoId;
				data.codeLevel = node.codeLevel + 1;
			}
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
					
					_self.parentCodes[null == _self.editObj ? data.codetypeId : _self.editObj.codetypeId] = null;
					_self.refreshParent();
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
