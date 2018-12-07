package com.dengjian.gomars.common.exception;

import com.dengjian.gomars.common.bean.BaseResponseCode;

/**
 * 基础业务异常，该异常通常用于与用户操作相关，通常而言就是，提醒用户错误信息
 *
 */
public class BusinessException extends RuntimeException{

	private static final long serialVersionUID = 3642893154173825454L;
	
	private String code;
	private String msg;
	
	public BusinessException(String code, String msg) {
		super(msg);
		this.code = code;
		this.msg = msg;
	}
	
	public BusinessException(String msg, Throwable cause) {
		super(msg, cause);
		this.msg = msg;
	}
	
	public BusinessException(String code, String msg, Throwable cause) {
		super(msg, cause);
		this.code = code;
		this.msg = msg;
	}
	
	public BusinessException(BaseResponseCode responseCode) {
		super(responseCode.getMsg());
		this.code = responseCode.getCode();
		this.msg = responseCode.getMsg();
	}
	
	public BusinessException(BaseResponseCode responseCode, Throwable cause) {
		super(responseCode.getMsg(), cause);
		this.code = responseCode.getCode();
		this.msg = responseCode.getMsg();
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
	
}
