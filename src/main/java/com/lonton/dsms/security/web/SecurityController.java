package com.lonton.dsms.security.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lonton.dsms.common.bean.BaseResponse;
import com.lonton.dsms.security.service.SecurityService;

@Controller
@RequestMapping(value="/api/auth")
public class SecurityController {
	
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(SecurityController.class);
	
	@Resource
	private SecurityService securityService;
	
	/**
	 * 认证
	 */
	@RequestMapping("/login")
	@ResponseBody
	public BaseResponse checkLogin(String userName, String password){
		try {
			boolean isPass = securityService.checkUser(userName, password);
			Map<String, String> data = new HashMap<String, String>();
			if(isPass) {
				data.put("token", userName);
				return BaseResponse.success().data(data);
			}else {
				return BaseResponse.error("用户名或密码错误");
			}
			
		} catch (Exception e) {
			logger.error("登录异常", e);
			return BaseResponse.error();
		}
	}
	
}
