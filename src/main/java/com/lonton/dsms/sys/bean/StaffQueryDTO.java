package com.lonton.dsms.sys.bean;

import javax.validation.constraints.NotNull;

import com.lonton.dsms.sys.validation.StaffValidationGroup;

/**
 * 有关表 A_STAFF 查询相关的DTO，可作为输入或输出，该DTO在DO类Staff的基础上扩展而来
 *
 */
public class StaffQueryDTO extends Staff{

	/** 机构名 */
	private String orgName;
	/** 分页查询：页码 */
	@NotNull(groups = {StaffValidationGroup.StaffQueryRequest.class})
	private String page;
	/** 分页查询：每页的大小 */
	@NotNull(groups = {StaffValidationGroup.StaffQueryRequest.class})
	private String rows;
	/** 分页查询：每页的大小 */
	@NotNull(groups = {StaffValidationGroup.StaffQueryDB.class}, message = "pageSize不能为空")
	private Integer pageSize;
	/** 分页查询：每页的开始索引 */
	@NotNull(groups = {StaffValidationGroup.StaffQueryDB.class}, message = "start不能为空")
	private Integer start;
	/**
	 * @return the orgName
	 */
	public String getOrgName() {
		return orgName;
	}
	/**
	 * @param orgName the orgName to set
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	/**
	 * @return the page
	 */
	public String getPage() {
		return page;
	}
	/**
	 * @param page the page to set
	 */
	public void setPage(String page) {
		this.page = page;
	}
	/**
	 * @return the rows
	 */
	public String getRows() {
		return rows;
	}
	/**
	 * @param rows the rows to set
	 */
	public void setRows(String rows) {
		this.rows = rows;
	}
	/**
	 * @return the pageSize
	 */
	public Integer getPageSize() {
		return pageSize;
	}
	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	/**
	 * @return the start
	 */
	public Integer getStart() {
		return start;
	}
	/**
	 * @param start the start to set
	 */
	public void setStart(Integer start) {
		this.start = start;
	}
	
}
