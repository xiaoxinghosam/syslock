package com.fzz.cloud.fzzcloudlockingSystem.entity.operatin;

import java.util.List;

public class OperatinModel {
	
	private String key;
	
    private String lockId;//锁id

	private Integer pageSize;
	
	private Integer currentPage;
	
	private List<Operatin> operatinList;
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<Operatin> getOperatinList() {
		return operatinList;
	}

	public void setOperatinList(List<Operatin> operatinList) {
		this.operatinList = operatinList;
	}

	public String getLockId() {
		return lockId;
	}

	public void setLockId(String lockId) {
		this.lockId = lockId;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
}