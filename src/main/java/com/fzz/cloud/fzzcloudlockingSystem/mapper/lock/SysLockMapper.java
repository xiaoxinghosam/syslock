package com.fzz.cloud.fzzcloudlockingSystem.mapper.lock;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fzz.cloud.fzzcloudlockingSystem.entity.lock.SysLock;
import com.fzz.cloud.fzzcloudlockingSystem.entity.lock.SysLockModel;

public interface SysLockMapper {
    int deleteByPrimaryKey(String id);

    int insertSelective(SysLock record);

    int updateByPrimaryKeySelective(SysLock record);

    int updateByPrimaryKey(SysLock record);
	
	/**
	 * 根据锁蓝牙mak地址查询锁对象
	 * @param lockNumber
	 * @return
	 */
	SysLock selectSysLockByLockNumber(String lockNumber);
	
	/**
	 * 根据锁对象添加锁
	 * @param lock
	 */
	void insertSysLock(SysLock lock);
	
	/**
	 * 根据ids查找多个锁对象
	 * @param ids
	 * @return
	 */
	List<SysLock> selectSysLockByIds(@Param(value = "ids") String[] ids);
	
	/**
	 * 根据ids删除锁对象包括删除指纹，ic卡，密码，操作记录修改状态statu=1
	 * @param ids
	 */
	void deleteBySysLockIds(@Param(value = "ids") String[] ids);
	
	/**
	 * 根据id删除锁对象包括删除指纹，ic卡，密码，操作记录修改状态statu=1
	 * @param id
	 */
	void deleteBySysLockId(@Param(value = "id") String id);

	/**
	 * 根据id查询锁对象
	 * @param id
	 * @return
	 */
	SysLock selectSysLockByLockId(String id);
	
	/**
	 * 根据SysLockModel修改锁对象
	 * @param sysLockModel
	 */
	void updateBySysLockModel(SysLockModel sysLockModel);
	
	/**
	 * 根据用户id分页查询锁对象
	 * @param userId
	 * @return
	 */
	List<SysLock> selectSysLockByPage(@Param(value = "userId")String userId);
	
	
	void installLockBinding(@Param(value = "installId") String installId, @Param(value = "lockId") String lockId);

	String selectSysLockAllow(String id);

	void updateSysLockName(@Param(value = "id")String id, @Param(value = "lockName") String lockName);
}