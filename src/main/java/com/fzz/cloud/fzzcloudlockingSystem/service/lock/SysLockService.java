package com.fzz.cloud.fzzcloudlockingSystem.service.lock;

import java.util.Map;

import com.fzz.cloud.fzzcloudlockingSystem.entity.lock.SysLock;
import com.fzz.cloud.fzzcloudlockingSystem.entity.lock.SysLockModel;

public interface SysLockService {

	/**
	 * 根据锁lockNumber（蓝牙mak地址）查询锁信息
	 * @param lockId
	 * @return
	 * @throws Exception
	 */
	SysLock selectSysLockByLockNumber(String lockNumber);
	

	/**
	 * 批量删除锁对象，修改status1失效
	 * @param ids
	 * @throws Exception
	 */
	Boolean deleteBySysLockIds(String[] ids);

	
	/**
	 * 分页查询锁对象
	 * @param userId 
	 * @param id
	 * @param pageSize
	 * @return
	 */
	Map<String, Object> selectSysLockByPage(String userId,String parentId);
	
	
	/**
	 * 修改锁信息
	 * @param sysLock
	 * @throws Exception
	 */
	void updateBySysLock(SysLockModel model);
	
	
	//=====================================================以上待定==========================================================
	
	/**
	 * 第一次安装锁需要对 锁的操作(添加锁)
	 * @param model  初始化锁模型
	 * @return  lockNumber 锁的蓝牙MAK地址
	 *  		 electricity   锁电量
	 *  		 admin   管理员密码
	 * 			 allow   被允许开锁的标识
	 * @throws Exception
	 */
	String initLockModel(SysLockModel sysLockModel);
	
	/**
	 * 根据id删除锁对象
	 * @param id   锁id
	 * @return
	 */
	void deleteBySysLockId(String id);

	/**
	 * 根据id查找锁对象
	 * @param id
	 * @return
	 */
	SysLock selectSysLockByLockId(String id);

	
	/**
	 * 安装锁的时候绑定安装人员与房间
	 * @param lockInstallToRoomModel
	 */
	void installLockBinding(SysLockModel sysLockModel);

	
	/**
	 * 根据锁id修改锁的名字
	 * @param id
	 * @param lockName
	 */
	void updateSysLockName(String id, String lockName);

}
