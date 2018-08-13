package com.lonton.dsms.sys.constants;

public class Constants {
	
	/*
	 * 员工模块
	 */
	/** 用户状态:正常 ，可登录*/
	public static final String USER_STATUS_NORMAL = "1";
	/** 用户状态：锁定，不能登录 */
	public static final String USER_STATUS_LOCK = "2";
	
	/** 用户类型：系统开发者，维护者 */
	public static final String USER_TYPE_DEVELOPER = "1";
	/** 用户类型：系统管理员 */
	public static final String USER_TYPE_ADMIN = "2";
	/** 用户类型：普通使用者 */
	public static final String USER_TYPE_NORMAL = "3";
	
	/** 用户表记录删除标志：有效 */
	public static final String USER_DELETE_FLAG_EFFECTIVE = "1";
	/** 用户表记录删除标志：删除 */
	public static final String USER_DELETE_FLAG_DELETED = "0";
	
	/** 用户密码：默认值 */
	public static final String USER_PASSWORD_DEFAULT = "123456";
	
	
	/*
	 * 机构模块
	 */
	/** 顶层机构父id */
	public static final String ORG_ROOT_PARENT_ID = "-1";
	
	/*
	 * 角色模块
	 */
	
}
