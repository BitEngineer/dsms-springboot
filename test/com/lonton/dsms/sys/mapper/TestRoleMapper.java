package com.lonton.dsms.sys.mapper;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.annotation.Resource;

import org.junit.Test;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.lonton.dsms.AbstractMapperTest;
import com.lonton.dsms.sys.bean.Role;

/**
 * 用户管理数据接口测试类:
 *  使用Dbunit;
 *  使用spring-test-dbunit整合spring和Dbunit;
 * @author 邓键
 */
public class TestRoleMapper extends AbstractMapperTest{
	@Resource
	private RoleMapper roleMapper;
	
	@Test
	@DatabaseSetup({"classpath:/com/lonton/dsms/sys/a_role_pre.xml"})
    @ExpectedDatabase(assertionMode=DatabaseAssertionMode.NON_STRICT,value="classpath:/com/lonton/dsms/sys/a_role_insert_exp.xml") 
	public void testInsert() throws SQLException, ParseException{
		Role role = new Role();
		role.setRoleId("6");
		role.setRoleName("人力资源主管");
		role.setDescription("人力资源主管");
		role.setDeleteFlag("1");
		role.setOrgClass("1");
		role.setUpdateUser("admin");
		role.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2017-09-29 17:20:41"));
		role.setExt1("ext");
		role.setExt2("ext");
		role.setExt3("ext");
		roleMapper.insert(role);
	}
	
}