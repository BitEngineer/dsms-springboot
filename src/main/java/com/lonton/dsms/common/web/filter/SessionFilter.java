package com.lonton.dsms.common.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lonton.dsms.base.vo.User;
import com.lonton.dsms.common.util.WebUtils;

/**
 * 过滤器：处理session超时的请求
 *
 */
public class SessionFilter implements Filter{
	
	private static final Logger logger = LoggerFactory.getLogger(SessionFilter.class);
	
	private static final String INIT_PARAM_KEY_REDIRECT_URL = "redirectURL";
	private static final String INIT_PARAM_KEY_EXCLUDE_URLS = "excludeURLs";
	
	/*
	 * 所有的url都是相对于ServletContext的
	 */
	/** session超时后的跳转URL */
	private String redirectURL;
	/** 该filter不拦截的请求列表，使用逗号分隔 */
	private String excludeURLs;

	@Override
	public void destroy() {
		logger.debug("Filter sessionFilter is destroyed");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain fileterChain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		if(isExcludeURL(httpRequest)) {  // filter不拦截的请求
			logger.debug("request uri" + httpRequest.getRequestURI() + " is in exclude urls");
		}else {
			User loginUser = WebUtils.getCurrLoginUser(httpRequest);
			if(loginUser == null) {  // 未登录,或session超时
				logger.debug("user is not login, or session is time out");
				// 页面跳转（重定向）
				httpResponse.sendRedirect(httpRequest.getContextPath() + redirectURL);
				// 不再继续请求
				return;
			}
		}
		
		// 继续执行请求
		fileterChain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.debug("Filter sessionFilter is started");
		
		// 初始化Filter参数
		String inputRedirectURL = filterConfig.getInitParameter(INIT_PARAM_KEY_REDIRECT_URL);
		if(inputRedirectURL != null) {
			this.redirectURL = inputRedirectURL;
		}
		String inputExcludeURLs = filterConfig.getInitParameter(INIT_PARAM_KEY_EXCLUDE_URLS);
		if(inputExcludeURLs != null) {
			this.excludeURLs = inputExcludeURLs;
		}
		
		logger.debug("Filter sessionFilter init is completed");
	}
	
	/**
	 * request中包含的uri是否在filter不拦截的范围（excludeURLs）内，是返回true，否则返回false；
	 * @param request
	 * @return
	 */
	private boolean isExcludeURL(HttpServletRequest request) {
		if(request == null) {
			throw new IllegalArgumentException("request is null");
		}
		
		String[] excludeURLArr = null;
		if(excludeURLs != null && !"".equals(excludeURLs.trim())) {
			excludeURLArr = excludeURLs.split(",");
		}
		if(excludeURLArr != null && excludeURLArr.length > 0) {
			for(String excludeURL : excludeURLArr) {
				// url配置是相对于ServletContext的context path的
				if(request.getRequestURI().equals(request.getContextPath()+excludeURL)) {
					return true;
				}
			}
		}
		return false;
	}

}
