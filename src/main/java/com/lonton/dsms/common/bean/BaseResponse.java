package com.lonton.dsms.common.bean;

import java.io.Serializable;

/*
 * 特别注意：需要json序列化的类，一定要实现get，set方法
 */
public class BaseResponse implements Serializable{
	
	private static final long serialVersionUID = -9074409403398678672L;
	/** 处理业务逻辑时，失败 */
	public static final String CODE_500 = "500";
	/** 处理业务逻辑时，成功 */
	public static final String CODE_200 = "200";
	
	/** CODE_500 默认的message */
	public static final String MSG_500 = "系统内部处理异常";
	/** CODE_200 默认的message */
	public static final String MSG_200 = "成功";
	
	private String code;
	private String msg;
	private Object data;
	
	public BaseResponse(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public static BaseResponse success() {
		return new BaseResponse(CODE_200, MSG_200);
	}
	
	public static BaseResponse success(String msg) {
		return new BaseResponse(CODE_200, msg);
	}
	
	public static BaseResponse error() {
		return new BaseResponse(CODE_500, MSG_500);
	}
	
	public static BaseResponse error(String msg) {
		return new BaseResponse(CODE_500, msg);
	}
	
	public BaseResponse data(Object data) {
		this.data = data;
		return this;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}
	
	
}
