package com.dengjian.gomars.sys.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dengjian.gomars.base.vo.User;
import com.dengjian.gomars.common.exception.ServiceProcessException;
import com.dengjian.gomars.common.util.StringUtil;
import com.dengjian.gomars.sys.bean.Role;
import com.dengjian.gomars.sys.bean.RoleExample;
import com.dengjian.gomars.sys.mapper.RoleMapper;

@Service("roleService")
public class RoleService {

	@Resource
	private RoleMapper roleMapper;
	
	public void add(Role role, User loginUser) throws ServiceProcessException {
		try {
			// 主键
			role.setRoleId(StringUtil.uuid());
			
			// roleName 唯一性校验
			RoleExample roleEx1 = new RoleExample();
			roleEx1.createCriteria().andRoleNameEqualTo(role.getRoleName());
			List<Role> roles1 = roleMapper.selectByExample(roleEx1);
			if(roles1 != null && roles1.size() > 0) {
				throw  ServiceProcessException.instanceWrapValidationException("roleName必须唯一");
			}
			
			role.setDeleteFlag("1");
			role.setUpdateUser(loginUser == null ? null : loginUser.getStaffId());
			role.setUpdateTime(new Date());
			roleMapper.insertSelective(role);
		} catch (ServiceProcessException e) {
			throw e;
		} catch (Exception e) {
			throw ServiceProcessException.instanceWrapUnkownException(e);
		}
	}
}
