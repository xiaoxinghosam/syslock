
package com.fzz.cloud.fzzcloudlockingSystem.entity.subset;

import java.util.List;

/**
 * 授权或者转移权限
 * @author Administrator
 *
 */
public class AuthorizationModel {
	
    private String parentId;//用户的id

    private String lockNum;//锁编号

    private String uid;//被授权人的id

    private List<String> firstPermissionId;//权限id
    

	public AuthorizationModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AuthorizationModel(String parentId, String lockNum, String uid, List<String> firstPermissionId) {
		super();
		this.parentId = parentId;
		this.lockNum = lockNum;
		this.uid = uid;
		this.firstPermissionId = firstPermissionId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getLockNum() {
		return lockNum;
	}

	public void setLockNum(String lockNum) {
		this.lockNum = lockNum;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public List<String> getFirstPermissionId() {
		return firstPermissionId;
	}

	public void setFirstPermissionId(List<String> firstPermissionId) {
		this.firstPermissionId = firstPermissionId;
	}
}
