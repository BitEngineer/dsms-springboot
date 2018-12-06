package com.lonton.dsms.common.util;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * http的request工具类
 */
public class RequestUtil {
	
	/**
	 * <p>获取request中的参数</p>
	 * HTTP-MIME type 为application/x-www-form-urlencoded，默认的表单提交方式
	 * 包括，获取URL中的参数；HTTP-MIME type 为 application/json，则获取出来的参数与预计的不一样
	 * 
	 * 表单提交和json提交都很容易使用spring mvc的参数绑定功能，但是表单提交对复杂结构的参数提交会出现困难，比如：
	 * 提交一个数组
	 * @param request
	 * @return 参数的map形式
	 */
	/*
	 * 这种方式处理存在的问题：
	 * 1)get请求或者post请求中含有非ascii的字符时，获取结果会产生乱码
	 */
	public static Map<String, Object> getRequestParams(HttpServletRequest request) {
		try {
			// 请求体使用 UTF-8 解码
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			
		}
		Map<String, Object> params = new HashMap<String, Object>();
		Enumeration<String> keys = request.getParameterNames(); 
	       
        for(; keys.hasMoreElements(); ) {  
            String key = keys.nextElement();  
            String value = request.getParameter(key);
            if(value != null && "null".equalsIgnoreCase(value)){
            	value = "";
    		}
            params.put(key, value);  
        }
		return params;
	}
}
