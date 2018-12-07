package com.dengjian.gomars.common.bean;

import java.io.Serializable;

/*
 * 特别注意：需要json序列化的类，一定要实现get，set方法
 */
public class BaseResponse implements Serializable{
	
	private static final long serialVersionUID = -9074409403398678672L;
	
	private String code;
	private String msg;
	private Object data;
	
	public BaseResponse(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public static BaseResponse success() {
		return new BaseResponse(BaseResponseCode.SUCCESS.getCode(), BaseResponseCode.SUCCESS.getMsg());
	}
	
	public static BaseResponse success(String msg) {
		return new BaseResponse(BaseResponseCode.SUCCESS.getCode(), msg);
	}
	
	public static BaseResponse success(String code, String msg) {
		return new BaseResponse(code, msg);
	}
	
	public static BaseResponse success(BaseResponseCode baseResponseCode) {
		return new BaseResponse(baseResponseCode.getCode(), baseResponseCode.getMsg());
	}
	
	public static BaseResponse error() {
		return new BaseResponse(BaseResponseCode.ERROR_50000.getCode(), BaseResponseCode.ERROR_50000.getMsg());
	}
	
	public static BaseResponse error(String msg) {
		return new BaseResponse(BaseResponseCode.ERROR_50000.getCode(), msg);
	}
	
	public static BaseResponse error(String code, String msg) {
		return new BaseResponse(code, msg);
	}
	
	public static BaseResponse error(BaseResponseCode baseResponseCode) {
		return new BaseResponse(baseResponseCode.getCode(), baseResponseCode.getMsg());
	}
	
	public BaseResponse data(Object data) {
		this.data = data;
		return this;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	
}
