package com.lonton.dsms.sys.mapper;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.lonton.dsms.sys.bean.Staff;
import com.lonton.dsms.sys.bean.StaffQueryDTO;

/**
 * 用户管理数据接口
 * @author 邓键
 */
@Mapper
public interface UserMapper {
	
	int updateStaffOrgId(List<Map<String,Object>> params) throws SQLException;
	List<Map<String,Object>> checkLoginName(Map<String,Object> params) throws SQLException;
	List<Map<String,Object>> checkStaffCode(Map<String,Object> params) throws SQLException;
	List<Map<String,Object>> checkPassword(Map<String,Object> params) throws SQLException;
	List<Map<String,Object>> selectCodesByType(Map<String,Object> params) throws SQLException;
	
	/**
	 * 插入数据到表 A_STAFF
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	int insert(Staff params) throws SQLException;
	
	/**
	 * 删除表 A_STAFF 的数据
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	int delete(Staff params) throws SQLException;
	
	/**
	 * 更新表 A_STAFF 的数据
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	int updateByStaffId(Staff params) throws SQLException;
	
	/**
	 * 分页查询用户数据
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	List<StaffQueryDTO> selectPage(Map<String, Object> params) throws SQLException;
	
	/**
	 * 分页查询时，查询符合条件的用户数据的总记录数
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	int selectPageTotal(Map<String, Object> params) throws SQLException;
	
	Staff selectObject(String staffId) throws SQLException;
	
	/**
	 * 单表查询用户数据
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	List<StaffQueryDTO> select(StaffQueryDTO params) throws SQLException;
}