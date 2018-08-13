/**
 * 首页
 * @author 邓键
 */
$(function() {
	new Page();
});

function Page() {
	_self = this;
	
	this.isRightPwd = false;
	
	this.$menuTree = $("#menuTree");
	this.$displayArea = $("#displayArea");
	
	this.bindEvent();
};


/**
 * 事件监听
 * @author 邓键
 */
Page.prototype.bindEvent = function() {
	//密码修改
	//弹窗形式
	$("#modifyPwd").click(function(){
		$('#pwdWin').window({iconCls:'fa fa-edit', title:'修改密码'});
		//重置输入框
		Common.formReset("#pwdForm", null);
		$('#pwdWin').window('open');
	});
	
	//密码修改确定
	$("a[name='savePwdWin']").click(_self.savePwdWin);
	
	//密码修改取消
	$("a[name='resetPwdWin']").click(function(){
		Common.formReset("#pwdForm", null);
	});
	
};

Page.prototype.checkPwd = function() { 
	 var oldPwd = $("#oldPwd").textbox('getValue');
	 $.ajax({
			url:"app/user/check.do",
			data:{"password":oldPwd},
			async:false,
			success:function(data) {
				if(data == 0) {
					_self.isRightPwd = false;
				}else{
					_self.isRightPwd = true;
				}
			}
	 });	 
}

Page.prototype.savePwdWin = function() {
	if($("#pwdForm").form("validate")){  //必填项校验
		var data = Common.formData("#pwdForm");
		if (data.newPwd != data.confirmPwd) {  // 确认密码一致性校验
			Common.showInfo("warning", "密码输入不一致");
		} else {
			_self.checkPwd();
			if(_self.isRightPwd){  //原密码正确性校验
				//修改密码
				$.ajax({
					url : "app/user/modifyPwd.do",
					data : {"password":data.newPwd},
					success : function(flag) {
						if (flag) {
							$('#pwdWin').window('close');
							Common.showInfo("alert", "密码修改成功");
						} else {
							Common.showInfo("warning", "密码修改失败");
						}
					}
				});
			}else{
				Common.showInfo("error", "密码不正确，重新输入");
			}	
		}
	};
}
