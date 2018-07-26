package com.fzz.cloud.fzzcloudlockingSystem.mapper.lock;

import com.fzz.cloud.fzzcloudlockingSystem.entity.lock.LockUser;

public interface LockUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(LockUser record);

    void insertSelective(LockUser LockUser);

    LockUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LockUser record);

    int updateByPrimaryKey(LockUser record);
    
    /**
     * 添加用户数据中间表
     * @param lockUser
     */
	void insertLockUser(LockUser lockUser);
}