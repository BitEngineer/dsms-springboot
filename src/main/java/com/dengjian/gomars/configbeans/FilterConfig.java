package com.dengjian.gomars.configbeans;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import com.dengjian.gomars.common.web.filter.CorsFilter;
import com.dengjian.gomars.common.web.filter.SessionFilter;
import com.dengjian.gomars.common.web.filter.WithoutAuthFilter;

@Configuration
public class FilterConfig {
	
	@Bean
	@Order(1)
	public FilterRegistrationBean withoutAuthFilter() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		
		registration.setFilter(new WithoutAuthFilter());
		registration.addUrlPatterns("/*");
        registration.addInitParameter("withoutAuth", "true");
        registration.setName("withoutAuthFilter");
        // 设置有限级，值越小的优先级越高，可以使用注解@Order实现
//        registration.setOrder(Integer.MAX_VALUE-1);
		
		return registration;
	}
	
	@Bean
	@Order(2)
	public FilterRegistrationBean sessionFilter() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		
		registration.setFilter(new SessionFilter());
		registration.addUrlPatterns("/*");
        registration.addInitParameter("redirectURL", "/login.jsp");
        registration.addInitParameter("excludeURLs", "/login.do,/login.jsp");
        registration.setName("sessionFilter");
//        registration.setOrder(Integer.MAX_VALUE);
		
		return registration;
	}
	
	@Bean
	@Order(3)
	public FilterRegistrationBean corsFilter() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		
		registration.setFilter(new CorsFilter());
		registration.addUrlPatterns("/*");
		
		return registration;
	}
	
}
