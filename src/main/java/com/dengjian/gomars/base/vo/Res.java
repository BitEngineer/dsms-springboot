package com.dengjian.gomars.base.vo;

import java.util.Date;

/**
 * 资源项
 */
public class Res {
	private String resId;
	private String resCode;
	private String resName;
	
	public static final String  RES_TYPE_CD = "CD";
	public static final String  RES_TYPE_GN = "GN";
	public static final String  RES_TYPE_LJ = "LJ";
	public static final String  RES_TYPE_SJ = "SJ";
	public static final String  RES_TYPE_JG = "JG";
	public static final String  RES_TYPE_AQ = "AQ";
	public static final String  RES_TYPE_JS = "JS";

	private String resType;
	private String resUrl;
	private String relative;
	private String parentId;
	private String treePath;
	private String resOrder;
	private String resIcon;
	
	public static final String STATUS_TRUE = "1";
	public static final String STATUS_FALSE = "0";
	private String available;
	private String visible;
	private String updateUser;
	private Date updateTime;
	
	public String getResId() {
		return resId;
	}
	public void setResId(String resId) {
		this.resId = resId;
	}
	public String getResCode() {
		return resCode;
	}
	public void setResCode(String resCode) {
		this.resCode = resCode;
	}
	public String getResName() {
		return resName;
	}
	public void setResName(String resName) {
		this.resName = resName;
	}
	public String getResType() {
		return resType;
	}
	public void setResType(String resType) {
		this.resType = resType;
	}
	public String getResUrl() {
		return resUrl;
	}
	public void setResUrl(String resUrl) {
		this.resUrl = resUrl;
	}
	public String getRelative() {
		return relative;
	}
	public void setRelative(String relative) {
		this.relative = relative;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getTreePath() {
		return treePath;
	}
	public void setTreePath(String treePath) {
		this.treePath = treePath;
	}
	public String getResOrder() {
		return resOrder;
	}
	public void setResOrder(String resOrder) {
		this.resOrder = resOrder;
	}
	public String getResIcon() {
		return resIcon;
	}
	public void setResIcon(String resIcon) {
		this.resIcon = resIcon;
	}
	public String getAvailable() {
		return available;
	}
	public void setAvailable(String available) {
		this.available = available;
	}
	
	public String getVisible() {
		return visible;
	}
	public void setVisible(String visible) {
		this.visible = visible;
	}
	
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
