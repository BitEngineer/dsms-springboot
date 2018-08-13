package com.lonton.dsms.sys.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Resource
	private UserMapper userMapper;
	
	/**
	 * 自动生成用户编号，用户编号保证在单表下唯一
	 * @throws SQLException
	 */
	public String generateStaffCode() throws SQLException {
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
	public void addUser(Staff staff, String operationUserId) throws ServiceProcessException {
		try {
			if(staff == null) {
				throw new ServiceProcessException(ServiceProcessException.ERROR_CODE_VALIDATION_EXCEPTION, "staff不能为空");
			}
			if(operationUserId == null) {
				logger.warn("loginUser is null");
			}
			
			staff.setStaffId(StringUtil.uuid());
			staff.setStaffCode(generateStaffCode());
			// 登录名默认与编码一致
			staff.setLoginName(staff.getStaffCode());
			staff.setPassword(Constants.USER_PASSWORD_DEFAULT);
			staff.setStatus(Constants.USER_STATUS_NORMAL);
			staff.setUserType(Constants.USER_TYPE_NORMAL);
			staff.setDeleteFlag(Constants.USER_DELETE_FLAG_EFFECTIVE);
			staff.setUpdateUser(operationUserId);
			staff.setUpdateTime(new Date());
			
			// 校验
			// 用户编号staffCode唯一
			StaffQueryDTO staffDTO = new StaffQueryDTO();
			staffDTO.setStaffCode(staff.getStaffCode());
			// 查询结果为空，返回空集合，而不是null
			List<StaffQueryDTO> staffs = userMapper.select(staffDTO);
			if( staffs != null && staffs.size() > 0 ) {
				throw new ServiceProcessException(ServiceProcessException.ERROR_CODE_VALIDATION_EXCEPTION, "用户编号staff_code已存在");
			}
			// 登录名loginName唯一
			StaffQueryDTO staffDTO_2 = new StaffQueryDTO();
			staffDTO_2.setLoginName(staff.getLoginName());
			List<StaffQueryDTO> staffs_2 = userMapper.select(staffDTO_2);
			if(staffs_2 != null && staffs_2.size() > 0) {
				throw new ServiceProcessException(ServiceProcessException.ERROR_CODE_VALIDATION_EXCEPTION, "用户登录名login_name已存在");
			}
			
			// 外键校验
			// 用户机构号org_id必须来自a_org
			
			// 数据插入
			userMapper.insert(staff);
		} catch(ServiceProcessException e) {
			throw e;
		} catch(SQLException e) {
			throw new ServiceProcessException(ServiceProcessException.ERROR_CODE_SQL_EXCEPTION, "sql执行异常", e);
		} catch(Exception e) {
			throw new ServiceProcessException(ServiceProcessException.ERROR_CODE_UNKNOWN_EXCEPTION, "未知异常", e);
		}
	}
	
	/**
	 * 删除用户：逻辑删除
	 * @param staff
	 * @throws SQLException
	 * @throws ValidationException
	 */
	public void delUser(String staffId, String operationUserId) throws ServiceProcessException {
		try {
			// 避免误更新，使用新的参数：即只更新几个字段
			Staff newStaff = new Staff();
			newStaff.setStaffId(staffId);
			newStaff.setDeleteFlag(Constants.USER_DELETE_FLAG_DELETED);
			newStaff.setUpdateUser(operationUserId);
			newStaff.setUpdateTime(new Date());
			userMapper.updateByStaffId(newStaff);
		} catch (SQLException e) {
			throw new ServiceProcessException(ServiceProcessException.ERROR_CODE_SQL_EXCEPTION, "sql执行异常", e);
		} catch (Exception e) {
			throw new ServiceProcessException(ServiceProcessException.ERROR_CODE_UNKNOWN_EXCEPTION, "未知异常", e);
		}
	}
	
	/**
	 * 更新用户表记录
	 * @param staff
	 * @throws SQLException
	 * @throws ValidationException
	 * @throws ServiceProcessException 
	 */
	public void updateUser(Staff staff, String operationUserId) throws ServiceProcessException {
		try {
			if(staff.getStaffId() == null) {
				throw new ServiceProcessException(ServiceProcessException.ERROR_CODE_VALIDATION_EXCEPTION, "用户id为空");
			}
			
			// 多字段之间的校验
			// 用户状态为锁定时，锁定时间不能为空
			
			staff.setUpdateTime(new Date());
			staff.setUpdateUser(operationUserId);
			userMapper.updateByStaffId(staff);
		} catch (ServiceProcessException e) {
			throw e;
		} catch (SQLException e) {
			throw new ServiceProcessException(ServiceProcessException.ERROR_CODE_SQL_EXCEPTION, "sql执行异常", e);
		} catch (Exception e) {
			throw new ServiceProcessException(ServiceProcessException.ERROR_CODE_UNKNOWN_EXCEPTION, "未知异常", e);
		}
	}
	
	/**
	 * 分页查询用户信息
	 * @param staffDTO
	 * @return
	 * @throws SQLException
	 * @throws ValidationException 
	 * @throws ServiceProcessException 
	 */
	public List<StaffQueryDTO> selectPage(Map<String, Object> params) throws ServiceProcessException {
		List<StaffQueryDTO> results = null;
		try {
			results = userMapper.selectPage(params);
		} catch (SQLException e) {
			throw ServiceProcessException.instanceWrapSqlException(e);
		} catch (Exception e) {
			throw ServiceProcessException.instanceWrapUnkownException(e);
		}
		
		return results;
	}
	
	/**
	 * 分页查询时，符合条件的总记录数
	 * @param staffDTO
	 * @return
	 * @throws SQLException
	 */
	@Override
	public int selectPageTotal(Map<String, Object> params) throws SQLException {
		int results = userMapper.selectPageTotal(params);
		return results;
	}

	@Override
	public Staff selectObject(String staffId) throws Exception {
		return userMapper.selectObject(staffId);
	}
}
