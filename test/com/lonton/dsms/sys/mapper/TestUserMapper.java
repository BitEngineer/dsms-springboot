package com.lonton.dsms.sys.mapper;
import java.sql.SQLException;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.lonton.dsms.AbstractSpringTest;
import com.lonton.dsms.common.util.StringUtil;
import com.lonton.dsms.sys.bean.Staff;

/**
 * 用户管理数据接口测试类
 * @author 邓键
 */
public class TestUserMapper extends AbstractSpringTest{
	@Resource
	private UserMapper userMapper;
	
	@Before
	public void init() {
		
	}
	
	/*
	 * dao层的单元测试，如果直接设置事务回滚的话，只能说明在执行sql的过程中没有发生异常，且断言正确，
	 * 并不能说明数据更改的正确性
	 */
	@Test
	public void testInsert() throws SQLException{
		Staff staff = new Staff();
		// 主键不是自动在数据库生成
		staff.setStaffId(StringUtil.uuid());
		staff.setStaffCode("100006");
		staff.setStaffName("test1");
		staff.setLoginName("test1");
		staff.setPassword("123456");
		staff.setOrgId("100000002");
		staff.setUserType("1");
		staff.setStatus("1");

		int result = userMapper.insert(staff);
		Assert.assertTrue("操作失败！", result == 1);
	}
	
}