package com.dengjian.gomars.common.web.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OnlineStatusListener implements HttpSessionListener {
	
	private static final Logger logger = LoggerFactory.getLogger(OnlineStatusListener.class);
	
	/** 在线人数 */
	private int onlineNum = 0;

	@Override
	public void sessionCreated(HttpSessionEvent sessionEvent) {
		onlineNum ++;
		logger.debug("creating session, number of session now is " + onlineNum);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent sessionEvent) {
		onlineNum --;
		logger.debug("destroying session, number of session now is " + onlineNum);
		//throw new RuntimeException("测试在listener中抛出异常");
	}

}
