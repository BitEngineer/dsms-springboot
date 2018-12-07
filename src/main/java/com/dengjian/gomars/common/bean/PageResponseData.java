package com.dengjian.gomars.common.bean;

import java.util.List;

public class PageResponseData<T> {
	private int total;
	private List<T> records;
	
	public PageResponseData(int total, List<T> records) {
		this.total = total;
		this.records = records;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}
	
	
	
}
