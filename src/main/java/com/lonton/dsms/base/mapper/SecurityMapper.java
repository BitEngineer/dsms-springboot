package com.lonton.dsms.base.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.lonton.dsms.base.vo.Res;
import com.lonton.dsms.base.vo.Role;
import com.lonton.dsms.base.vo.User;

/**
 * 用户登录相关的数据库访问接口
 * @author 邓键
 */
@Mapper
public interface SecurityMapper{
	public int checkLogin(Map<String,Object> params);
	public User getLoginUserInfo(Map<String,Object> params);
	public List<Res> loadResByUserRole(List<Role> params);
}
