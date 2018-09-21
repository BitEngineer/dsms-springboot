package com.lonton.dsms.sys.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lonton.dsms.common.bean.BaseResponseCode;
import com.lonton.dsms.common.exception.BusinessException;
import com.lonton.dsms.common.exception.ServiceProcessException;
import com.lonton.dsms.common.exception.ValidationException;
import com.lonton.dsms.common.util.DateUtil;
import com.lonton.dsms.common.util.StringUtil;
import com.lonton.dsms.sys.bean.Staff;
import com.lonton.dsms.sys.bean.StaffQueryDTO;
import com.lonton.dsms.sys.constants.Constants;
import com.lonton.dsms.sys.mapper.UserMapper;
import com.lonton.dsms.sys.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 自动生成用户编号，用户编号保证在单表下唯一
	 * @throws SQLException
	 */
	public String generateStaffCode() {
		return DateUtil.format(new Date(), DateUtil.PATTERN_17);
	}

	/**
	 * 新增用户
	 * @param staff
	 * @throws ServiceProcessException 
	 */
	/*
	 * @Transactional 默认情况下只会对RumtimeException.class进行回滚，而检查异常不会回滚
	 */
	@Transactional
	public void addUser(Staff staff, String operationUserId) {
		// 方法通常的参数校验
		if(staff == null) {
			throw new RuntimeException("staff is null");
		}
		if(operationUserId == null) {
			throw new RuntimeException("operationUserId is null");
		}
		
		// 设置非用户输入字段
		staff.setStaffId(StringUtil.uuid());
		staff.setStaffCode(generateStaffCode());
		staff.setLoginName(staff.getStaffCode());  // 登录名默认与编码一致
		staff.setPassword(Constants.USER_PASSWORD_DEFAULT);
		staff.setStatus(Constants.USER_STATUS_NORMAL);
		staff.setUserType(Constants.USER_TYPE_NORMAL);
		// TODO orgid
		staff.setOrgId("-1");
		staff.setDeleteFlag(Constants.USER_DELETE_FLAG_EFFECTIVE);
		staff.setUpdateUser(operationUserId);
		staff.setUpdateTime(new Date());
		
		// 校验
		// 用户编号staffCode唯一
		Map<String, Object> params1 = new HashMap<String, Object>();
		params1.put("staffCode", staff.getStaffCode());
		// 查询结果为空，返回空集合，而不是null
		List<StaffQueryDTO> staffs = userMapper.select(params1);
		if( staffs != null && staffs.size() > 0 ) {
			throw new BusinessException(BaseResponseCode.ERROR_51002);
		}
		
		// 外键校验
		// 用户机构号org_id必须来自a_org
		
		// 数据插入
		userMapper.insert(staff);
	}
	
	/**
	 * 删除用户：逻辑删除
	 * @param staff
	 * @throws SQLException
	 * @throws ValidationException
	 */
	public void delUser(String staffId, String operationUserId) {
			// 避免误更新，使用新的参数：即只更新几个字段
			Staff newStaff = new Staff();
			newStaff.setStaffId(staffId);
			newStaff.setDeleteFlag(Constants.USER_DELETE_FLAG_DELETED);
			newStaff.setUpdateUser(operationUserId);
			newStaff.setUpdateTime(new Date());
			userMapper.updateByStaffId(newStaff);
	}
	
	/**
	 * 更新用户表记录
	 * @param staff
	 * @throws SQLException
	 * @throws ValidationException
	 * @throws ServiceProcessException 
	 */
	public void updateUser(Staff staff, String operationUserId) {
			
			// 多字段之间的校验
			// 用户状态为锁定时，锁定时间不能为空
			
			staff.setUpdateTime(new Date());
			staff.setUpdateUser(operationUserId);
			userMapper.updateByStaffId(staff);
	}
	
	/**
	 * 分页查询用户信息
	 * @param staffDTO
	 * @return
	 * @throws SQLException
	 * @throws ValidationException 
	 * @throws ServiceProcessException 
	 */
	public List<StaffQueryDTO> selectPage(Map<String, Object> params) {
		List<StaffQueryDTO> results = null;
			results = userMapper.selectPage(params);
		
		return results;
	}
	
	/**
	 * 分页查询时，符合条件的总记录数
	 * @param staffDTO
	 * @return
	 * @throws SQLException
	 */
	@Override
	public int selectPageTotal(Map<String, Object> params) {
		int results = userMapper.selectPageTotal(params);
		return results;
	}

	@Override
	public Staff selectObject(String staffId) {
		return userMapper.selectObject(staffId);
	}
}
