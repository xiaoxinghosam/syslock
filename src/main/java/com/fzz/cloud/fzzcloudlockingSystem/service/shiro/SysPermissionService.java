package com.fzz.cloud.fzzcloudlockingSystem.service.shiro;
import java.util.List;

import com.fzz.cloud.fzzcloudlockingSystem.entity.SysPermission;
import com.fzz.cloud.fzzcloudlockingSystem.entity.SysUser;

public interface SysPermissionService {
	
	//直接去数据库查找，是否有一样的
	public List<SysUser> selectByMap(String nickName,String pasd);
	
	//更新用户
	public void updateById(SysUser user);
	
	//根据用户id查询权限范围的菜单
	public List<SysPermission> findMenuListByUserId(String userid) throws Exception;
		
	//根据用户id查询权限范围的url
	public List<String> findPermissionListByUserId(String userid) throws Exception;
}
