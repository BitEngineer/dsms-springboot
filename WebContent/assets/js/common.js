/**
 * 公用文件，定义Comm命名空间及其方法供外部使用
 * 
 * @author 李岳南
 */
(function(window, undefined) {
	var Common = {};
	window.Common = Common;

	/**
	 * 获取URL,到应用,例如 http://localhost:8080/dsmscopy/
	 * @author 李岳南
	 */
	Common.getRootPath = function() {
		//获取当前网址
	    var curWwwPath=window.document.location.href;  
	    //获取主机地址之后的目录
	    var pathName=window.document.location.pathname;  
	    var pos=curWwwPath.indexOf(pathName);  
	    //获取主机地址
	    var localhostPaht=curWwwPath.substring(0,pos);  
	    //获取带"/"的项目名
	    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);  
	    return (localhostPaht+projectName);  
	};
	
	/**
	 * 获取URL路径,到模块,例如 http://localhost:8080/dsmscopy/sys/authority/
	 */
	Common.getControllerPath = function(){
		var urlPath = window.document.location.pathname;
		var start = urlPath.indexOf("/views/");
		start = -1 == start ? 6 : start + 6;
		var end = urlPath.indexOf(".");
		
		var controller = urlPath.substring(start, end) + "/";
		return Common.getRootPath() + controller;
	};
	
	/**
	 * 获取URL中所有的参数值
	 * @param url url字符串
	 * @return map形式的参数
	 * @author 李岳南
	 */
	Common.getUrlParams = function(url) {
		var params = {}, hash;
		if (!url) {
			url = window.location.href;
		}
		
		// 无参数
		if (url.indexOf('?') == -1) return {};
		
		var hashes = url.slice(url.indexOf('?') + 1).split('&');
		for ( var i = 0; i < hashes.length; i++) {
			hash = hashes[i].split('=');
			var param = $.trim(decodeURI(hash[1]));
			param = param == "undefined" ? "" : param;
			try{
				//允许传入JSON
				var t = JSON.parse(param);
				param = t;
			} catch(e){}
			params[hash[0]] = param;
		}
		return params;
	};
	
	/**
	 * 根据参数拼接URL
	 * @author 王沅倩
	 */
	Common.getUrl = function(object){
		var url = '';
		for(var key in object){
			url = url + key + "=" + encodeURI(object[key]) + "&";
		}
		return url.substring(0,url.length-1);
	};
	
	/**
	 * 弹窗显示信息,基于jQuery通知插件noty
	 * @author 王沅倩
	 */
	Common.showInfo = function(type,info,$parent){
		var timeout = "error" == type ? 6000 : 3000;	//错误信息保留时间较长
		var options ={
			text:info,
			layout:'center',
			theme: 'relax', 
			type:type,
			timeout:timeout,
			killer:false
		};
		if(!$parent){
			noty(options);
		}else{
			$($parent).noty(options);
		}		
	};
	
	/**
	 * 深度复制对象
	 */
	Common.deepCopy = function(data){
		return data ? JSON.parse(JSON.stringify(data)) : null;
	};
	
})(window);


/**
 * easyui 组件工具方法
 * @param $
 */
(function(){
	/**
	 * 新建easyui的选项卡
	 * @param title 选项卡标题
	 * @param url 选项卡内容的url
	 * @author 李岳南
	 */
	Common.addTabs = function(title, url) {
		url = encodeURI(url);
		var $displayArea = window.parent.$("#displayArea");
		if($displayArea.tabs("exists", title)) {
//			$displayArea.tabs("select", title);
//			var tab = $displayArea.tabs("getSelected");
//			tab.find("iframe").attr("src",url)
			return;
		} 
		
		var $iframe = '<iframe src=' + url + ' scrolling="yes" frameborder="0" '
				+ 'height="100%" width="100%"  frameborder="4px" ></iframe>';
		$displayArea.tabs("add", {
			title : title,
			content : $iframe,
			closable : true
		});
	};
	
	/**
	 * 判断Easyui输入项的类型
	 */
	Common.inputType = function($input){
		if(null == $input){ return null;}
		$input = $($input);
		if($input.hasClass("datebox-f")){
			return "datebox";
		}else if($input.hasClass("combotree-f")){
			return "combotree";
		}else if($input.hasClass("combobox-f")){
			return "combobox";
		}else if($input.hasClass("numberbox-f")){
			return "numberbox";
		}else if($input.hasClass("textbox-f")){
			return "textbox";
		}
		return null;
	};
	
	/**
	 * 动态设置下拉框的内容
	 * @param $parent 父级元素对象
	 * @param data 数据
	 * @author 李岳南
	 */
	Common.setSelectOption = function($parent, data) {
		if(!$parent || !data) {
			return;
		}
		var dataArray = [];
		for(var key in data){	
			dataArray.push({"value":key, "text":data[key]});
		}
		
		$parent.empty();
		$parent.combobox({
			valueField: 'value',
			textField: 'text',
			data : dataArray,
			formatter: function(row){
				var opts = $(this).combobox('options');
				return row[opts.textField];
			}
		});
	};
	
	/**
	 * 获取EasyUI表单的值
	 */
	Common.formData = function($form, domIdPrefix){
		var data = {};
		if(null == $form){
			return data;
		} else {
			$($form).find("[id]").each(function(index, dom){
				dom = $(dom);
				var type = Common.inputType(dom);
				if(null == type){return;}
				var id = removePrefix(dom.attr("id"), domIdPrefix);
				data[id] = eval("dom." + type + "('getValue')");
			});
			return data;
		}
	};
	var removePrefix = function (s, prefix){
		if(null == prefix || 0 == prefix.length){ return s;}
		if(0 == s.indexOf(prefix)){
			s = s.charAt(prefix.length).toLowerCase() + s.substr(prefix.length + 1);
		}
		return s;
	};

	/**
	 * 重置EasyUI表单
	 */
	Common.formReset = function($form, valObj, domIdPrefix){
		if(null == $form){
			return ;
		} 
		valObj = valObj || {};
		$($form).find("[id]").each(function(index, dom){
			dom = $(dom);
			var type = Common.inputType(dom);
			if(null == type){return;}
			var id = removePrefix(dom.attr("id"), domIdPrefix);
			var v = valObj[id] || '';
			eval("dom." + type + "('setValue', '" + v + "')");
		});
	};
})();


/**
 * easyui设置默认属性值
 * @param $
 */
(function($){
	$.fn.textbox.defaults.missingMessage = "该输入项为必输项";
	$.fn.filebox.defaults.missingMessage = "该输入项为必输项";
	$.fn.filebox.defaults.prompt = "未选择文件";
	$.fn.filebox.defaults.buttonText = "上传";
	
	//定义datagrid默认行为
	$.fn.datagrid.defaults.fitColumns = true;
	$.fn.datagrid.defaults.fit = true;
	$.fn.datagrid.defaults.loadMsg = "加载中，请稍候...";
	$.fn.datagrid.defaults.pagination = true;
	$.fn.datagrid.defaults.rownumbers = true;
	$.fn.datagrid.defaults.remoteSort = false;
	$.fn.datagrid.defaults.singleSelect = true;
	$.fn.datagrid.defaults.striped = true;
	
	//定义pagination栏默认行为
	$.fn.pagination.defaults.beforePageText = '第';
	$.fn.pagination.defaults.afterPageText = '页';
//	$.fn.pagination.defaults.displayMsg = '共' + $.fn.pagination.defaults.total + '页';
//	$.fn.pagination.defaults.displayMsg = 'Displaying' + from 'to' + to + 'of' + total-1 + 'items';
	$.fn.pagination.defaults.showPageList = true;
	
})(jQuery);

/**
 * 扩展ajax请求
 */
(function(){
	/**
	 * 重写jQuery的ajax请求
	 * @author 李岳南
	 */
	/*
	Common._init = function() {
		var _self = this;
		var _ajax = $.ajax;
		this.errorMsg = {500:"服务器系统内部错误", 401:"未登录", 403:"无权限执行此操作", 404:"请求地址不存在", 408:"请求超时"};
		
		$.ajax = function(opt) {
			var success = opt.success;
			var error = opt.error;
			opt.async = null != opt.async ? opt.async : true;
			var _opt = $.extend(opt, {
    			type:"post",
    			dataType:"json",
	            error:function(request, textStatus, errorThrown){
	            	var errorMsg = request.responseJSON ? request.responseJSON.errorMsg : null;
	            	errorMsg = errorMsg || _self.errorMsg[request.status] || "您所访问的页面出错了，请联系管理员！";
					// 错误方法增强处理
	            	_self.showInfo("error", errorMsg);
	            	if(error){
	            		error(request, errorMsg, errorThrown);
	            	}
	            },     
	            success:function(data, textStatus){
	            	if(data.success){
	            		success(data.data, textStatus);
	            	} else {
		            	if(error){
		            		error(null, data.errorMsg || "您所访问的页面出错了,请联系管理员!", null);
		            	}else {
		            		_self.showInfo("error", data.errorMsg || "您所访问的页面出错了,请联系管理员!");
		            	}
	            	}
	            }
			});
			_ajax(_opt);
		};
	};
	Common._init();
	*/
	//全局的ajax行为:统一处理访问错误,和session超时
	/*
	$.ajaxSetup({
		global : false,
		error : function(request, textStatus, errorThrown){
			Common.showInfo("error", textStatus + "您所访问的页面出错了,请联系管理员!");
		},
		success : function(data, textStatus, jqXHR){
			var d = data;
		}
	});
	*/
	
	//ajax的全局事件
	/*
	$(document).ajaxSuccess(function(evt, request, settings){
		Common.showInfo("alert", "成功");
	});
	*/
	/*
	$(document).ajaxError(function(event,xhr,options,exc){
		Common.showInfo("error", xhr.status + "您所访问的页面出错了,请联系管理员!");
	});
	*/
})();

/**
 * 自定义String的trim方法
 * 解决低版本IE不存在trim方法的bug
 * @param $
 */
(function(){
	String.prototype.trim = function(){
		return this .replace(/^\s\s*/, '' ).replace(/\s\s*$/, '' );
	};
})();

/*
 * 扩展easyui-linkbutton
 * 处理easyui linkbutton disable仍然可以click的bug
 */
(function($){ 
	$.extend($.fn.linkbutton.methods, {
	    /**
	     * 激活选项（覆盖重写）
	     * @param {Object} jq
	     */
	    enable: function(jq){
	        return jq.each(function(){
	            var state = $.data(this, 'linkbutton');
	            if ($(this).hasClass('l-btn-disabled')) {
	            	if(state._eventsStore){
	                    var itemData = state._eventsStore;
	                    //恢复超链接
	                    if (itemData.href) {
	                        $(this).attr("href", itemData.href);
	                    }
	                    //回复点击事件
	                    if (itemData.onclicks) {
	                        for (var j = 0; j < itemData.onclicks.length; j++) {
	                            $(this).bind('click', itemData.onclicks[j]);
	                        }
	                    }
	                    //设置target为null，清空存储的事件处理程序
	                    itemData.href = null;
	                    itemData.onclicks = [];
	                    state._eventsStore = null;
	            	}
	                $(this).removeClass('l-btn-disabled');
	                $(this).removeClass('l-btn-plain-disabled');
	                
	            }
	        });
	    },
	    /**
	     * 禁用选项（覆盖重写）
	     * @param {Object} jq
	     */
	    disable: function(jq){
	        return jq.each(function(){
	            var state = $.data(this, 'linkbutton');
	            if (!$(this).hasClass('l-btn-plain-disabled') && !state._eventStore) {
	                var eventsStore = {};
	                eventsStore.target = this;
	                eventsStore.onclicks = [];
	                //处理超链接
	                var strHref = $(this).attr("href");
	                if (strHref) {
	                    eventsStore.href = strHref;
	                    $(this).attr("href", "javascript:void(0)");
	                }
	                //处理直接耦合绑定到onclick属性上的事件
	                var onclickStr = $(this).attr("onclick");
	                if (onclickStr && onclickStr != "") {
	                    eventsStore.onclicks[eventsStore.onclicks.length] = new Function(onclickStr);
	                    $(this).attr("onclick", "");
	                }
	                //处理使用jquery绑定的事件
	                var eventDatas = $(this).data("events") || $._data(this, 'events');
	                if (eventDatas["click"]) {
	                    var eventData = eventDatas["click"];
	                    for (var i = 0; i < eventData.length; i++) {
	                        if (eventData[i].namespace != "menu") {
	                            eventsStore.onclicks[eventsStore.onclicks.length] = eventData[i]["handler"];
	                            $(this).unbind('click', eventData[i]["handler"]);
	                            i--;
	                        }
	                    }
	                }
	                state._eventsStore = eventsStore;
	                $(this).addClass('l-btn-disabled');
	                $(this).addClass('l-btn-plain-disabled');
	            }
	        });
	    }
	});
})(jQuery); 

/**  
 * 1)扩展jquery easyui tree的节点检索方法。使用方法如下：  
 * $("#treeId").tree("search", searchText);    
 * 其中，treeId为easyui tree的根UL元素的ID，searchText为检索的文本。  
 * 如果searchText为空或""，将恢复展示所有节点为正常状态  
 */  
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
