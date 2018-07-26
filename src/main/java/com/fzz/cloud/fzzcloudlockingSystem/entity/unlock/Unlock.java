package com.fzz.cloud.fzzcloudlockingSystem.entity.unlock;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Unlock {
	
    private String id;
    
    private String lockId;
    
    private String unlockName;//开锁方式名称
    
    private String unlockFlag;//开锁标识(指纹标识，ic卡标识，身份证标识，密码标识后台生成)
    
    private String addPerson;
    
    private String forWay;// 循环方式(循环密码有值)
    
    private String startTime;
    
    private String endTime;
    
    private Integer addType;//添加类型0：IC卡，1：身份证，2：指纹，3：密码
    
    private Integer unlockType;//开锁类型0：限时，1永久(永久指纹时间只有起始时间)2自定义，3循环，4清空
    
    private String allow;
    
   public Unlock() {}

	public Unlock(String lockId, String unlockName, String unlockFlag, String addPerson, String forWay,
			String startTime, String endTime, Integer addType, Integer unlockType) {
		super();
		this.lockId = lockId;
		this.unlockName = unlockName;
		this.unlockFlag = unlockFlag;
		this.addPerson = addPerson;
		this.forWay = forWay;
		this.startTime = startTime;
		this.endTime = endTime;
		this.addType = addType;
		this.unlockType = unlockType;
	}

	@JsonIgnore
	public String getAllow() {
		return allow;
	}

	public void setAllow(String allow) {
		this.allow = allow;
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

	public String getUnlockName() {
		return unlockName;
	}

	public void setUnlockName(String unlockName) {
		this.unlockName = unlockName;
	}

	public String getUnlockFlag() {
		return unlockFlag;
	}

	public void setUnlockFlag(String unlockFlag) {
		this.unlockFlag = unlockFlag;
	}

	public String getAddPerson() {
		return addPerson;
	}

	public void setAddPerson(String addPerson) {
		this.addPerson = addPerson;
	}

	public String getForWay() {
		return forWay;
	}

	public void setForWay(String forWay) {
		this.forWay = forWay;
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

	public Integer getAddType() {
		return addType;
	}

	public void setAddType(Integer addType) {
		this.addType = addType;
	}

	public Integer getUnlockType() {
		return unlockType;
	}

	public void setUnlockType(Integer unlockType) {
		this.unlockType = unlockType;
	}

	@Override
	public String toString() {
		return "Unlock [id=" + id + ", lockId=" + lockId + ", unlockName=" + unlockName + ", unlockFlag=" + unlockFlag
				+ ", addPerson=" + addPerson + ", forWay=" + forWay + ", startTime=" + startTime + ", endTime="
				+ endTime + ", addType=" + addType + ", unlockType=" + unlockType + "]";
	}
	
}