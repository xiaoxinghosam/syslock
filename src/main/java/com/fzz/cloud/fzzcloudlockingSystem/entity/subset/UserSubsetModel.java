package com.fzz.cloud.fzzcloudlockingSystem.entity.subset;

public class UserSubsetModel {
	
    private String lockId;

    private String userId;
    
    private Integer pageSize;
    
    private Integer currentPage;

	public String getLockId() {
		return lockId;
	}

	public void setLockId(String lockId) {
		this.lockId = lockId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	@Override
	public String toString() {
		return "UserSubsetModel [lockId=" + lockId + ", userId=" + userId + ", pageSize=" + pageSize + ", currentPage="
				+ currentPage + "]";
	}
	
}