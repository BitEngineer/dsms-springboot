package com.lonton.dsms.sys.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.lonton.dsms.base.vo.Res;

/**
 * 授权管理-数据库访问接口
 * @author 郭宇航
 */
@Mapper
public interface AuthorityMapper {
	public List<Res> searchRoleRelatedRes(Map<String,Object> params);
}
