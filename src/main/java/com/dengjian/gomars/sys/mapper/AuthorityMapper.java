package com.dengjian.gomars.sys.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.dengjian.gomars.base.vo.Res;

/**
 * 授权管理-数据库访问接口
 */
@Mapper
public interface AuthorityMapper {
	public List<Res> searchRoleRelatedRes(Map<String,Object> params);
}
