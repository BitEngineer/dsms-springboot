/**
 * 授权管理
 * @author 邓键
 */
$(function() {
	new Page();
	
	/*定义资源树节点*/
	var ResTreeNode = function(res){
		this.text = res.resName;
		this.state = "open";
		this.checked = false;
		
		this.resId = res.resId;
		this.parentId = res.ParentId;
		this.children = [];
	};
	ResTreeNode.prototype.addChild = function(resTreeNode){
		this.children.push(resTreeNode);
	};
	ResTreeNode.prototype.isLeaf = function(){
		if(this.children.length === 0){
			return true;
		}else{
			return false;
		}
	};
	
});

function Page() {
	_self = this;
	
	//单击role datagrid时,选中的item
	this.selectedRole = {};
	
	this.init();
	this.bindEvent();
};

/**
 * 初始化
 */
Page.prototype.init = function(){
//	this.initDataGrids();
	this.initPage();
};

Page.prototype.initPage = function(){
	$.ajax({
		url : Common.getRootPath() + "/sys/authority/searchRoleInfoCopy.do",
		type : "POST"
	});
};

/**
 * 初始化datagrid
 */
Page.prototype.initDataGrids = function() {
	
	//初始化角色datagrid
	$("#roleTable").datagrid({
		url : Common.getRootPath() + "/sys/authority/searchRoleInfo.do",
		columns : [[
			{field:"roleId", title:"角色编码" , sortable:true},
			{field:"roleName", title:"角色名称" , sortable:true}
		]],
		loadFilter : function(data){
			/*
			 * easyui-datagrid 的合法数据模型
			 */
			return {
				rows : data.rows,
				total : data.total
			};
		},
		onLoadSuccess : function(){
			$("#searchResBtn").linkbutton("enable");
			$("#relateResBtn").linkbutton("enable");
		},
	    onSelect:function(index, row){
			$("#searchResBtn").linkbutton("enable");
			$("#relateResBtn").linkbutton("enable");
			_self.selectedRole = row;
	    },
	    pagination : true,
		pagePosition : "bottom",
		pageNumber : 1,
		pageSize : 10,
		pageList : [8,9,10],
		toolbar : "#roleToolbar",
		queryParams : Common.formData("#roleSearchHead", "s")
	});
	
	
	
	
};

/**
 * 初始化选中角色关联的资源树
 */
Page.prototype.initResSearchTree = function(){
	$("#resSearchTree").tree({
		url : Common.getRootPath() + "/sys/authority/searchRoleRelatedRes.do",
		method : "POST",
		queryParams : {"roleId":_self.selectedRole.roleId},
		checkbox : true,
		cascadeCheck : true,
		loadFilter : function(data){
			var resTreeData = [];
			resTreeData.push(data);
			return resTreeData;
		},
	});
};

/**
 * 初始化选中角色的可被授权的资源树,已授权的将被标识
 */
Page.prototype.initResRelateTree = function(){
	$("#resRelateTree").tree({
		url : Common.getRootPath() + "/sys/authority/searchAuthorityRes.do",
		method : "POST",
		queryParams : {"roleId":_self.selectedRole.roleId},
		checkbox : true,
		cascadeCheck : true,
		loadFilter : function(data){
			//定义根节点
			var rootNode = {};
			//形成树节点数组
			var treeNodeArr = [];
			var i = 0;
			var j = 0;
//			var res = {"resName":"xxx","resId":"000","parentId":""}
			for(i=0;i<data.length;i++){
				treeNodeArr.push(new ResTreeNode(data[i]));
			}
			//形成树结构
			for(i=0; i<treeNodeArr.length; i++){
				if(treeNodeArr[i].parentId == null){
					rootNode = treeNodeArr[i];
				}
				for(j=0; j<treeNodeArr.length; j++){
					if(treeNodeArr[i].parentId != null &&
							treeNodeArr[i].parentId == treeNodeArr[j].resId){
						treeNodeArr[j].addChild(treeNodeArr[i]);
					}
				}
			}
			
			var rootNodeArr = [];
			rootNodeArr.push(rootNode);
			return rootNodeArr;
		}
	});
};


/**
 * 事件监听
 */
Page.prototype.bindEvent = function() {
	//角色查询:按钮
	$("#searchRoleBtn").linkbutton({
		onClick : function(){
			//重新加载 role datagrid
			$("#roleTable").datagrid("load");
		}
	});
	
	//查看关联资源:按钮
	$("#searchResBtn").linkbutton({
		onClick : function(){
			//弹出窗口
			$("#resSearchWin").window("open", "查看角色所关联的资源");
			//初始化 res datagrid
			_self.initResSearchTree();
		}
	});
	
	//关联资源:按钮
	$("#relateResBtn").linkbutton({
		onClick : function(){
			//弹出窗口
			$("#resRelateWin").window("open", "角色关联资源");
			//初始化 res datagrid
			_self.initResRelateTree();
		}
	});
	
	
	
};

