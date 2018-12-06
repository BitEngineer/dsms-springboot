package com.lonton.dsms.sys.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 机构管理数据接口层
 */
@Mapper
public interface OrgMapper {
	List<Map<String, Object>> select(Map<String, Object> params);
	List<Map<String, Object>> selectOrg(Map<String, Object> params);
	List<Map<String, Object>> selectTree();
	List<Map<String, Object>> selectTreeByParentId(Map<String,Object> params);
	List<Map<String,Object>> selectOrgByName(Map<String,Object> params);
	int insert(Map<String, Object> params);
	int update(Map<String, Object> params);
	int delete(Map<String, Object> params);
	List<Map<String,Object>> checkOrgCode(Map<String,Object> params);
	List<Map<String,Object>> checkOrgName(Map<String,Object> params);
	List<Map<String,Object>> checkOrgFullName(Map<String,Object> params);
}
