package com.lonton.dsms.sys.mapper;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.dbunit.Assertion;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.datasource.DataSourceUtils;

import com.lonton.dsms.AbstractSpringTest;
import com.lonton.dsms.sys.bean.Role;

/**
 * 用户管理数据接口测试类:单独使用dbunit编写
 * @author 邓键
 */
public class TestRoleMapper2 extends AbstractSpringTest{
	@Resource
	private RoleMapper roleMapper;
	// datasource，用于获取数据库连接
	// @Resource只针对对象，static的类变量无法注入，也无法用context上下文获取，因为生命周期不一样
//	private static DataSource dataSource;
	@Resource
	private DataSource dataSource;
	// dbunit的数据库连接
	private IDatabaseConnection conn;
	
//	@BeforeClass
//	public static void init() throws Exception {
//		ApplicationContext context = ContextUtil.ctx;
//		dataSource = (DataSource) context.getBean("dataSource");
//		// 初始化数据库连接
//		conn = new DatabaseConnection(DataSourceUtils.getConnection(dataSource));
//	}
	
	@Before
	public void before() throws Exception {
		// 初始化数据库连接
		conn = new DatabaseConnection(DataSourceUtils.getConnection(dataSource));
		// 初始化待操作表
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("com/lonton/dsms/sys/a_role_pre.xml");
		IDataSet origen = new FlatXmlDataSetBuilder().build(in);
		DatabaseOperation.CLEAN_INSERT.execute(conn, origen);
	}
	
	@After
	public void after() throws Exception {
		// 不能直接关闭连接，因为方法包含了事务操作，用@Transaction的方式切片到方法头尾，如果关闭连接，事务方法就会报错
//		conn.close();
	}

	/*
	 * 测试 int com.lonton.dsms.sys.mapper.RoleMapper.insert(Role record)
	 */
	@Test
	public void testInsert() throws IOException, DatabaseUnitException, SQLException, ParseException {
		// 初始化待操作表：放到 @Before
		
		// 执行待测试方法
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
		
		// 获取预期数据集和实际数据集
		// 预期数据集
		FlatXmlDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(
				this.getClass().getClassLoader().getResourceAsStream("com/lonton/dsms/sys/a_role_insert_exp.xml"));
		// 实际数据集：获取方法
		QueryDataSet actualDataSet = new QueryDataSet(conn);
		actualDataSet.addTable("a_role", "select * from a_role order by role_id");
		
		// 数据集对比(断言)
		Assertion.assertEquals(expectedDataSet, actualDataSet);
	}
	
	/*
	 * 测试 int com.lonton.dsms.sys.mapper.RoleMapper.updateByPrimaryKeySelective(Role record)
	 */
	@Test
	public void testUpdate() throws Exception {
		// 初始化待操作表：放到 @Before
				
		// 执行待测试方法
		Role role = new Role();
		role.setRoleId("5");
		role.setRoleName("分行销售员");
		role.setDescription("分行销售员");
		role.setOrgClass("2");
		role.setUpdateUser("sys");
		role.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2017-09-30 17:17:41"));
		roleMapper.updateByPrimaryKeySelective(role);
		
		// 获取预期数据集和实际数据集
		// 预期数据集
		FlatXmlDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(
				this.getClass().getClassLoader().getResourceAsStream("com/lonton/dsms/sys/a_role_update_exp1.xml"));
		// 实际数据集：获取方法
		QueryDataSet actualDataSet = new QueryDataSet(conn);
		actualDataSet.addTable("a_role", "select * from a_role order by role_id");
		
		// 数据集对比(断言)
		Assertion.assertEquals(expectedDataSet, actualDataSet);
	}
	
}