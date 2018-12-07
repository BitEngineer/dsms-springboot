package com.dengjian.gomars.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.dengjian.gomars.base.vo.User;
import com.dengjian.gomars.common.CommonConstants;

/**
 * web相关工具类,依赖于Java Web API,及spring对web对象request的管理
 */
public class WebUtils {
	
	/**
	 * 从session中获取当前登录用户信息,session会在调用getSession方法时被创建
	 * @param request
	 * @return
	 */
	public static User getCurrLoginUser(HttpServletRequest request) {
		User loginUser = (User) request.getSession().getAttribute(CommonConstants.SESSION_KEY_LOGIN_USER);
		return loginUser;
	}
	
	/**
	 * 从session中获取当前登录用户信息,session会在调用getSession方法时被创建
	 * @param request
	 * @return
	 */
	// 该方法不好的地方在：无法明确告诉调用者，方法依赖于request对象和spring，容易被滥用
	@Deprecated
	public static User getCurrLoginUser() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		User loginUser = (User) request.getSession().getAttribute(CommonConstants.SESSION_KEY_LOGIN_USER);
		return loginUser;
	}
	
	/**
	 * 将当前登录用户User存储到Session中
	 * @param request
	 * @param loginUser
	 */
	public static void setCurrLoginUserToSession (HttpServletRequest request, User loginUser) {
		if(loginUser == null) {
			throw new IllegalArgumentException("loginUser is null");
		}
		request.getSession().setAttribute(CommonConstants.SESSION_KEY_LOGIN_USER, loginUser);
	}
	
	public static String urlEncode (String s) throws UnsupportedEncodingException {
		URLEncoder.encode(s);
		String results = URLEncoder.encode(s, "UTF-8");
		return results;
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		/* %B5%CB */
		System.out.println(URLEncoder.encode("邓", "gbk"));
		
		/* %E9%82%93 */
		// 与 chrome 浏览器直接输入url访问的结果一致
		System.out.println(URLEncoder.encode("邓", "UTF-8"));
		
		System.out.println(URLDecoder.decode("%E9%82%93", "UTF-8"));
	}
	
}
