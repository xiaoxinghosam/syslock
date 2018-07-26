package com.fzz.cloud.fzzcloudlockingSystem.service.shiro.impl;

import java.util.List;
import java.util.Map;

import com.fzz.cloud.fzzcloudlockingSystem.entity.SysPermission;
import com.fzz.cloud.fzzcloudlockingSystem.entity.SysUser;
import com.fzz.cloud.fzzcloudlockingSystem.service.shiro.SysPermissionService;

public class SysPermissionServiceImpl implements SysPermissionService {

	@Override
	public List<SysUser> selectByMap(String name,String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateById(SysUser user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<SysPermission> findMenuListByUserId(String userid) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> findPermissionListByUserId(String userid) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
