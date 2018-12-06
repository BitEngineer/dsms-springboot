package com.lonton.dsms.sys.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

/**
 * 码表条目信息数据接口层
 */
@Mapper
public interface CodeinfoMapper {
	List<Map<String, Object>> select(Map<String, Object> params);
	/**
	 * 根据分类信息获取所有字典（无分页）
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> selectCodes(Map<String, Object> params);
	int insert(Map<String, Object> params);
	int update(Map<String, Object> params);
	int delete(Map<String, Object> params);
	int updateSubCodeinfo(Map<String, Object> params);
}
