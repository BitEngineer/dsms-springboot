package com.dengjian.gomars.sys.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 码表类型管理数据接口层
 */
@Mapper
public interface CodetypeMapper {
	List<Map<String, Object>> select(Map<String, Object> params);
	int insert(Map<String, Object> params);
	int update(Map<String, Object> params);
	int delete(Map<String, Object> params);
}
