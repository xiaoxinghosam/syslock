package com.fzz.cloud.fzzcloudlockingSystem.entity.subset;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserSubset {
	
    private String id;

    private String lockId;

    private String userId;

    private String parentId;

    private String keyName;

    private String startTime;

    private String endTime;
    
    private UserSubset parentObj;
    
    private List<UserSubset> childrenList;
    
    private Integer statu;
    

	public UserSubset() {}
	
	
    
	public UserSubset(String lockId, String userId, String parentId, String keyName, String startTime, String endTime) {
		super();
		this.lockId = lockId;
		this.userId = userId;
		this.parentId = parentId;
		this.keyName = keyName;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	@JsonIgnore
    public Integer getStatu() {
		return statu;
	}

	public void setStatu(Integer statu) {
		this.statu = statu;
	}
	
	@JsonIgnore
	public UserSubset getParentObj() {
		return parentObj;
	}

	public void setParentObj(UserSubset parentObj) {
		this.parentObj = parentObj;
	}
	
	@JsonIgnore
	public List<UserSubset> getChildrenList() {
		return childrenList;
	}

	public void setChildrenList(List<UserSubset> childrenList) {
		this.childrenList = childrenList;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }
    
    @JsonIgnore
    public String getLockId() {
        return lockId;
    }

    public void setLockId(String lockId) {
        this.lockId = lockId == null ? null : lockId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName == null ? null : keyName.trim();
    }

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}