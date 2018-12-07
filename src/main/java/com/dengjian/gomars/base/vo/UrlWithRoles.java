package com.dengjian.gomars.base.vo;

import java.util.List;

/**
 * URL及可访问其的角色<br/>
 * 通过SecurityManager加载
 * 仅加载相对路径地址
 */
public class UrlWithRoles {

	private String url;
	private List<String> roleIds;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<String> getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(List<String> roleIds) {
		this.roleIds = roleIds;
	}
	
	
}
