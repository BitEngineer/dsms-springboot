package com.lonton.dsms.sys.bean;

import java.util.List;

/**
 * 有关表 A_STAFF 查询相关的DTO，可作为输入或输出，该DTO在DO类Staff的基础上扩展而来
 * @author 13967
 *
 */
public class StaffQueryOutputDTO{

	private List<StaffQueryDTO> rows;
	private int total;
	/**
	 * @return the rows
	 */
	public List<StaffQueryDTO> getRows() {
		return rows;
	}
	/**
	 * @param rows the rows to set
	 */
	public void setRows(List<StaffQueryDTO> rows) {
		this.rows = rows;
	}
	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}
	
}
