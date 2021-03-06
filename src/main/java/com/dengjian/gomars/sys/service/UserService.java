package com.dengjian.gomars.sys.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.dengjian.gomars.common.exception.ServiceProcessException;
import com.dengjian.gomars.common.exception.ValidationException;
import com.dengjian.gomars.sys.bean.Staff;
import com.dengjian.gomars.sys.bean.StaffQueryDTO;

/*
 * service层尽量不要依赖任何的web层特有的对象，比如：request，response；
 * 也不要提供和web相关的功能，文件上传下载等，只需提供文件和内存的读写即可；
 * 也就是说：把web层作为唯一的对外提供接口，这样的话service就能服务于多种类型的web
 */
public interface UserService {
	
	/**
	 * 自动生成用户编号，用户编号保证在单表下唯一
	 * @throws SQLException
	 */
	public String generateStaffCode();

	/**
	 * 新增用户
	 * @param staff
	 * @throws ServiceProcessException 
	 */
	public void addUser(Staff staff, String operationUserId);
	
	/**
	 * 删除用户：逻辑删除
	 * @param staff
	 * @throws SQLException
	 * @throws ValidationException
	 */
	public void delUser(String staffId, String operationUserId);
	
	/**
	 * 更新用户表记录
	 * @param staff
	 * @throws SQLException
	 * @throws ValidationException
	 * @throws ServiceProcessException 
	 */
	public void updateUser(Staff staff, String operationUserId);
	
	/**
	 * 分页查询用户信息
	 * @param staffDTO
	 * @return
	 * @throws SQLException
	 * @throws ValidationException 
	 * @throws ServiceProcessException 
	 */
	public List<StaffQueryDTO> selectPage(Map<String, Object> staffDTO);
	
	/**
	 * 分页查询时，符合条件的总记录数
	 * @param staffDTO
	 * @return
	 * @throws SQLException
	 */
	public int selectPageTotal(Map<String, Object> staffDTO);
	
	public Staff selectObject(String staffId);
	
}
