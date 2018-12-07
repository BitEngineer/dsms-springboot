package com.dengjian.gomars.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * spring上下文工具类
 */
@Component
public class ApplicationContextUtil implements ApplicationContextAware{
	// 上下文实例：单例模式
	public static ApplicationContext context;

	/**
	 * 实现ApplicationContextAware接口方法，注入ApplicationContext到
	 */
	@Override
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		ApplicationContextUtil.context = ctx;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		return (T) context.getBean(name);
	}
	
	public static <T> T getBean(Class<T> type) {
		return context.getBean(type);
	}

}
