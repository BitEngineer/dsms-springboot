package com.lonton.dsms.common.bean;

public enum BaseResponseCode {
	
	SUCCESS("20000", "操作成功"),

	ERROR_50000("50000", "操作失败，未知异常"),
	
	// ==========  用户模块   ========== 
	ERROR_51001("51001", "新增用户失败，未知异常"),
	ERROR_51002("51002", "新增用户失败，用户编码已存在");
	
	private String code;
	private String msg;
	
	private BaseResponseCode(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public String getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}
	
}
