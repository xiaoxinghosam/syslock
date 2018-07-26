package com.fzz.cloud.fzzcloudlockingSystem.entity.lock;

public class LockUser {
	
    private String id;

    private String lockId;

    private String uid;
    
    public LockUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LockUser(String lockId, String uid) {
		super();
		this.lockId = lockId;
		this.uid = uid;
	}
	
	public LockUser(String id, String lockId, String uid) {
		super();
		this.id = id;
		this.lockId = lockId;
		this.uid = uid;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getLockId() {
        return lockId;
    }

    public void setLockId(String lockId) {
        this.lockId = lockId == null ? null : lockId.trim();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

}