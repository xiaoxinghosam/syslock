package com.fzz.cloud.fzzcloudlockingSystem.entity.lock;

public class SysLockModel {
	
	private String id;//锁id
	private String adminUserId;//管理员用户
	private String lockName;//锁名称
	private String adminPsw;//管理员密码
	private String electricity;//电量
	private String secretKey;//秘钥
	
	
	private String roomId;//房间id
	private String installId;//安装人员id
	
	private String lockNumber;//锁蓝牙mak地址
	private String allow;//锁唯一标识
	

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getInstallId() {
		return installId;
	}

	public void setInstallId(String installId) {
		this.installId = installId;
	}

	public String getLockNumber() {
		return lockNumber;
	}

	public void setLockNumber(String lockNumber) {
		this.lockNumber = lockNumber;
	}

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

	public String getAdminUserId() {
		return adminUserId;
	}

	public void setAdminUserId(String adminUserId) {
		this.adminUserId = adminUserId;
	}

	public String getLockName() {
		return lockName;
	}

	public void setLockName(String lockName) {
		this.lockName = lockName;
	}


	public String getAdminPsw() {
		return adminPsw;
	}

	public void setAdminPsw(String adminPsw) {
		this.adminPsw = adminPsw;
	}

	public String getElectricity() {
		return electricity;
	}

	public void setElectricity(String electricity) {
		this.electricity = electricity;
	}
	
}