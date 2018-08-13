/**
 * 管理界面
 * @author 李岳南
 */
$(function() {
	new Manage();
});

function Manage() {
	this.$menuTree = $("#menuTree");
	this.$displayArea = $("#displayArea");
	
	this.init();
	this.bindEvent();
};

/**
 * 初始化
 * @author 李岳南
 */
Manage.prototype.init = function() {
	this.showTreeMenu();
};

/**
 * 展示菜单树
 * @author 阳畅
 */
Manage.prototype.showTreeMenu = function() {
	var _self = this;
	var data = [];
	
	$.ajax({
		url:Common.getRootPath() + "/base/getMenu.do",
		data:data,
		success:function(menu) {
			if(menu) {
				data.push(menu.data);
				initMenu();
			} else {
				Common.showInfo("warning", "加载菜单失败");
			}
		}
	});
	
	function initMenu(){
		_self.$menuTree.tree({
			data:data,
			onClick:function(node){
				if($(this).tree("isLeaf", node.target)) {
					var title = node.text;
					if(_self.$displayArea.tabs("exists", title)) {
						_self.$displayArea.tabs("select", title);
					} else {
						showData(node);
					}
					
				} 
			}
		});
		var node = _self.$menuTree.tree('getRoot');
		
		if(node){
			/* 选中第一个叶子节点 */
			while(!$(this).tree("isLeaf", node.target)){
				node = _self.$menuTree.tree('getChildren',node.target)[0];
			}
			
			_self.$menuTree.tree("select",node.target);
			showData(node);
		}
	}
	
	function showData(item) {
		var title = item.text;
		var url = item.resUrl;
		var $iframe = '<iframe src=' + url + ' scrolling="yes" frameborder="0" height="100%" width="100%"  frameborder="4px" ></iframe>';
		_self.$displayArea.tabs("add", {
			title : title,
			content : $iframe,
			closable : true
		});
	}
};

/**
 * 事件监听
 * @author 李岳南
 */
Manage.prototype.bindEvent = function() {
	var _self = this;
	$("#searchTree").searchbox({
		searcher: function(value,name){
			_self.$menuTree.tree("search", value);
		},
		prompt:'输入后回车搜索菜单'
	});
};
