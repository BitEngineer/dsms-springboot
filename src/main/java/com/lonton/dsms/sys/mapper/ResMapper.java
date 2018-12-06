package com.lonton.dsms.sys.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.cache.annotation.CacheEvict;

/**
 * 资源管理接口层
 */
@Mapper
public interface ResMapper {
	List<Map<String, Object>> select(Map<String, Object> params);
	int insert(Map<String, Object> params);
	int update(Map<String, Object> params);
	int delete(Map<String, Object> params);
	List<Map<String, Object>> selectResUrl(Map<String, Object> params);
	List<Map<String, Object>> selectAllUrl(Map<String, Object> params);
	int clearResUrl(String resId);
	int insertResUrl(Map<String,Object> params);
	List<Map<String, Object>> selectResRole(Map<String, Object> params);
	List<Map<String, Object>> selectAllRole(Map<String, Object> params);
	int clearResRole(String resId);
	int insertResRole(Map<String,Object> params);
}
