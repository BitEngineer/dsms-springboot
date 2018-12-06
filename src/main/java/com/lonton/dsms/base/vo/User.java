package com.lonton.dsms.base.vo;

import java.io.Serializable;
import java.util.List;

import com.lonton.dsms.sys.bean.Staff;

/**
 * 用户
 */
public class User extends Staff implements Serializable {
	private static final long serialVersionUID = 6462363694593416554L;
	
	public static final String STATUS_CANCEL="0";
	public static final String STATUS_NORMAL="1";
	public static final String STATUS_LOCK="2";
	
	public static final String DELETE_FLAG_DELETE = "0";
	public static final String DELETE_FLAG_VALID = "1";
	
	private List<Role> roles;

	/**
	 * @return the roles
	 */
	public List<Role> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	
}
