package com.lonton.dsms.common.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lonton.dsms.base.vo.User;
import com.lonton.dsms.common.util.WebUtils;

/**
 * 过滤器：测试时不需要登录认证，方便测试
 *
 */
public class WithoutAuthFilter implements Filter{
	
	private static final Logger logger = LoggerFactory.getLogger(WithoutAuthFilter.class);
	
	private static final String INIT_PARAM_KEY_IS_TEST = "withoutAuth";
	
	/*
	 * 所有的url都是相对于ServletContext的
	 */
	/** 是否是测试，是：跳过登录认证 */
	private boolean withoutAuth;

	@Override
	public void destroy() {
		logger.debug("Filter " + this.getClass().getName() +" is destroyed");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain fileterChain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		if(withoutAuth) {
			User testUser = new User();
			testUser.setStaffId("withoutAuth");
			WebUtils.setCurrLoginUserToSession(httpRequest, testUser);
		}
		
		// 继续执行请求
		fileterChain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.debug("Filter " + this.getClass().getName() + " is started");
		
		// 初始化Filter参数
		String isTestStr = filterConfig.getInitParameter(INIT_PARAM_KEY_IS_TEST);
		if(isTestStr != null) {
			this.withoutAuth = "true".equals(isTestStr) ? true : false;
		}
		
		logger.debug("Filter " + this.getClass().getName() + " init is completed");
	}
	
}
