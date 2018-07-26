package com.fzz.cloud.fzzcloudlockingSystem.mapper.shiro;
/**
 * 操作shiro权限的mapper
 * @author Administrator
 *
 */

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fzz.cloud.fzzcloudlockingSystem.entity.SysUser;

public interface SyspermssionMapper {
	//拿到user用户
	List<SysUser> selectByMap(@Param("nickname")String name,@Param("pswd")String password);
	//根据userid去拿到应有的权限 id 为用户的id
	List<String> findMenuListByUserId(@Param("id")String id);
	//更新用户
	void updateByUser(SysUser user);
}
