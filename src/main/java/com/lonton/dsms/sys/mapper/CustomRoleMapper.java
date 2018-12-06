package com.lonton.dsms.sys.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 用户角色管理模块数据接口
 */
@Mapper
public interface CustomRoleMapper {
	List<Map<String,Object>> selectUsed(Map<String,Object> params);
	List<Map<String,Object>> selectUnused(Map<String,Object> params);
	List<Map<String,Object>> selectByName(Map<String,Object> params);
	List<Map<String,Object>> selectByNamePage(Map<String,Object> params);
	void insertIntoStaff(List<Map<String,Object>> params);
	void deleteFromStaff(List<Map<String,Object>> params);
	List<Map<String,Object>> selectUnusedByPage(Map<String,Object> params);
	List<Map<String,Object>> selectRights(Map<String,Object> params);
	List<Map<String,Object>> selectRightsByPage(Map<String,Object> params);
	
	List<Map<String, Object>> select(Map<String, Object> params);
	int selectRoleCount(Map<String,Object> params);
	
	int insert(Map<String, Object> params);
	int update(Map<String, Object> params);
	int delete(Map<String, Object> params);
}
