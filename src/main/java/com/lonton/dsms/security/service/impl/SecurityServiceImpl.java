package com.lonton.dsms.security.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lonton.dsms.security.service.SecurityService;
import com.lonton.dsms.sys.mapper.UserMapper;

@Service("securityService")
public class SecurityServiceImpl implements SecurityService{
	
	@Resource
	private UserMapper userMapper;

	@Override
	public boolean checkUser(String userCode, String password) throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("staffCode", userCode);
		params.put("staffCode", password);
		userMapper.select(params);
		return false;
	}

	
}
