package com.dengjian.gomars.base.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.dengjian.gomars.base.vo.Res;
import com.dengjian.gomars.base.vo.Role;
import com.dengjian.gomars.base.vo.User;

/**
 * 用户登录相关的数据库访问接口
 */
@Mapper
public interface SecurityMapper{
	public int checkLogin(Map<String,Object> params);
	public User getLoginUserInfo(Map<String,Object> params);
	public List<Res> loadResByUserRole(List<Role> params);
}
