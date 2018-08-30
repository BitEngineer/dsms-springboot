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

/**
 * 过滤器：处理跨域请求
 *
 */
public class CorsFilter implements Filter{
	
	private static final Logger logger = LoggerFactory.getLogger(CorsFilter.class);
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain fileterChain)
			throws IOException, ServletException {
		
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.setHeader("Access-Control-Allow-Origin", "*");
		httpResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		httpResponse.setHeader("Access-Control-Max-Age", "3600");
//		httpResponse.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		httpResponse.setHeader("Access-Control-Allow-Headers", "*");
		
		// 继续执行请求
		fileterChain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
