package com.fzz.cloud.fzzcloudlockingSystem.entity.lock;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class SysLock {

	private String id;

	private String lockNumber;

	private String lockName;

	private String allow;

	private String electricity;

	private String adminPsw;
	
	private String adminUserId;
	
	private String endTime;
	
	private String startTime;
	
	private String secretKey;
	
	private String parentId;
	
	private String keyId;
	

	public SysLock() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SysLock(String id, String adminUserId, String lockNumber, String lockName, String allow, String electricity, String adminPsw,String secretKey) {
		super();
		this.id = id;
		this.lockNumber = lockNumber;
		this.lockName = lockName;
		this.allow = allow;
		this.electricity = electricity;
		this.adminPsw = adminPsw;
		this.adminUserId = adminUserId;
		this.secretKey = secretKey;
	}
	
	public String getKeyId() {
		return keyId;
	}

	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}

	@JsonIgnore
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	public String getAdminUserId() {
		return adminUserId;
	}

	public void setAdminUserId(String adminUserId) {
		this.adminUserId = adminUserId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getLockNumber() {
		return lockNumber;
	}

	public void setLockNumber(String lockNumber) {
		this.lockNumber = lockNumber == null ? null : lockNumber.trim();
	}

	public String getLockName() {
		return lockName;
	}

	public void setLockName(String lockName) {
		this.lockName = lockName == null ? null : lockName.trim();
	}

	public String getAllow() {
		return allow;
	}

	public void setAllow(String allow) {
		this.allow = allow == null ? null : allow.trim();
	}

	public String getElectricity() {
		return electricity;
	}

	public void setElectricity(String electricity) {
		this.electricity = electricity == null ? null : electricity.trim();
	}

	public String getAdminPsw() {
		return adminPsw;
	}

	public void setAdminPsw(String adminPsw) {
		this.adminPsw = adminPsw == null ? null : adminPsw.trim();
	}
}