package com.dengjian.gomars.base.vo;

import java.util.Date;

public class Org {
	/**
	 * @see 全行汇总
	 */
	public static final String ORG_TYPE_QHHZ = "00";
	/**
	 * @see 总行
	 */
	public static final String ORG_TYPE_ZHHZ = "10" ;
	/**
	 * @see 分行汇总
	 */
	public static final String ORG_TYPE_FHHZ = "20";
	/**
	 * @see 一级分行
	 */
	public static final String ORG_TYPE_YJFH = "21";
	/**
	 * @see 二级分行
	 */
	public static final String ORG_TYPE_EJFH = "22";
	/**
	 * @see 一级支行
	 */
	public static final String ORG_TYPE_YJZH = "31";
	/**
	 * @see 二级支行
	 */
	public static final String ORG_TYPE_EJZH = "32";
	/**
	 * @see 一级部门
	 */
	public static final String ORG_TYPE_YJBM = "41";
	/**
	 * @see 二级部门
	 */
	public static final String ORG_TYPE_EJBM = "42";
	/**
	 * @see 三级部门
	 */
	public static final String ORG_TYPE_SJBM = "43";
	
	private String orgId;
	private String orgCode;
	private String orgName;
	private String orgFullName;
	private String orgClass;
	private String orgType;
	private Integer orgOrder;
	private String orgLevel;
	private Date establishDate;
	private Date revocationDate;
	private String contactAddress;
	private String contactName;
	private String phone;
	private String parentId;
	private String status;
	private String countryCode;
	private String provinceCode;
	private String cityCode;
	private String countyCode;
	private String directly;
	private String treePath;
	private String updateUser;
	private Date updateTime;
	private String deleteFlag;
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrgFullName() {
		return orgFullName;
	}
	public void setOrgFullName(String orgFullName) {
		this.orgFullName = orgFullName;
	}
	public String getOrgClass() {
		return orgClass;
	}
	public void setOrgClass(String orgClass) {
		this.orgClass = orgClass;
	}
	public String getOrgType() {
		return orgType;
	}
	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}
	public Integer getOrgOrder() {
		return orgOrder;
	}
	public void setOrgOrder(Integer orgOrder) {
		this.orgOrder = orgOrder;
	}
	public String getOrgLevel() {
		return orgLevel;
	}
	public void setOrgLevel(String orgLevel) {
		this.orgLevel = orgLevel;
	}
	public Date getEstablishDate() {
		return establishDate;
	}
	public void setEstablishDate(Date establishDate) {
		this.establishDate = establishDate;
	}
	public Date getRevocationDate() {
		return revocationDate;
	}
	public void setRevocationDate(Date revocationDate) {
		this.revocationDate = revocationDate;
	}
	public String getContactAddress() {
		return contactAddress;
	}
	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getCountyCode() {
		return countyCode;
	}
	public void setCountyCode(String countyCode) {
		this.countyCode = countyCode;
	}
	public String getDirectly() {
		return directly;
	}
	public void setDirectly(String directly) {
		this.directly = directly;
	}
	public String getTreePath() {
		return treePath;
	}
	public void setTreePath(String treePath) {
		this.treePath = treePath;
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
	public String getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
	
}
