package com.fzz.cloud.fzzcloudlockingSystem.util;

public class ReturnPaging {

	private int recordsTotal;
	private int pageSize;
	private int totalPage;
	private Object data;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public int getRecordsTotal() {
		return recordsTotal;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	} 

	public int getTotalPage() {
		return this.recordsTotal % this.pageSize == 0 ? (this.recordsTotal / this.pageSize) : (this.recordsTotal / this.pageSize + 1);
	} 

}
