package com.fzz.cloud.fzzcloudlockingSystem.entity.operatin;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Operatin {
	
	private String id;
	
    private String lockId;//锁id

    private String unlockPwd;//密码解锁(密码开锁有值）

    private String unlockTime;//开锁时间

    private Integer unlockType;//开锁类型0：密码开锁，1：蓝牙开锁，2：指纹开锁，3：IC卡开锁，4：身份证开锁

	public Operatin() {
		super();
	}

	public Operatin(String lockId, String unlockPwd, String unlockTime, Integer unlockType) {
		super();
		this.lockId = lockId;
		this.unlockPwd = unlockPwd;
		this.unlockTime = unlockTime;
		this.unlockType = unlockType;
	}
	
	public Operatin(String lockId, String unlockTime, Integer unlockType) {
		super();
		this.lockId = lockId;
		this.unlockTime = unlockTime;
		this.unlockType = unlockType;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@JsonIgnore
	public String getLockId() {
		return lockId;
	}

	public void setLockId(String lockId) {
		this.lockId = lockId;
	}

	public String getUnlockPwd() {
		return unlockPwd;
	}

	public void setUnlockPwd(String unlockPwd) {
		this.unlockPwd = unlockPwd;
	}

	public String getUnlockTime() {
		return unlockTime;
	}

	public void setUnlockTime(String unlockTime) {
		this.unlockTime = unlockTime;
	}

	public Integer getUnlockType() {
		return unlockType;
	}

	public void setUnlockType(Integer unlockType) {
		this.unlockType = unlockType;
	}

}