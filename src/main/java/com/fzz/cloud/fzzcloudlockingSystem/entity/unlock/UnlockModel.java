package com.fzz.cloud.fzzcloudlockingSystem.entity.unlock;

import java.util.List;

public class UnlockModel {
	
    private String id;
    
    private Integer pageSize;
    
    private Integer currentPage;
    
    private Integer addType;
    
    private String lockId;
    
    private String addPerson;
    
    private List<String> childrenList;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getChildrenList() {
		return childrenList;
	}

	public void setChildrenList(List<String> childrenList) {
		this.childrenList = childrenList;
	}

	public String getAddPerson() {
		return addPerson;
	}

	public void setAddPerson(String addPerson) {
		this.addPerson = addPerson;
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

	public Integer getAddType() {
		return addType;
	}

	public void setAddType(Integer addType) {
		this.addType = addType;
	}

	public String getLockId() {
		return lockId;
	}

	public void setLockId(String lockId) {
		this.lockId = lockId;
	}
    
}