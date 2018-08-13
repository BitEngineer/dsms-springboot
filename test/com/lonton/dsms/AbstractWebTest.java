package com.lonton.dsms;

import javax.annotation.Resource;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * SpringWeb测试基类，所有SpringWeb测试类都要继承该类<br/>
 * 默认使用<b>test</b>用户登录，请注意在数据库中初始化该用户信息
 * @author 郭宇航
 */
@WithUserDetails("test")
public abstract class AbstractWebTest extends AbstractSpringTest{
	@Autowired  
    protected WebApplicationContext wac;  
	
	@Resource
	protected FilterChainProxy springSecurityFilterChain;
	
	protected static volatile MockMvc mockMvc;
	
	@Before
	public void setUp(){
		if(null == mockMvc){
			synchronized (AbstractWebTest.class) {
				if(null == mockMvc){
					mockMvc = MockMvcBuilders.webAppContextSetup(wac).apply(SecurityMockMvcConfigurers.springSecurity()).build(); 
				}
			}
		}
	}
}
