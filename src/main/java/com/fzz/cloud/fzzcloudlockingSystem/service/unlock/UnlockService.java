package com.fzz.cloud.fzzcloudlockingSystem.service.unlock;

import com.fzz.cloud.fzzcloudlockingSystem.entity.unlock.Unlock;
import com.fzz.cloud.fzzcloudlockingSystem.entity.unlock.UnlockModel;
import com.fzz.cloud.fzzcloudlockingSystem.util.ReturnPaging;

public interface UnlockService {
	
	/**
	 * 添加开锁信息
	 * @param unlock
	 */
	String insertUnlock(Unlock unlock);
	
	/**
	 * 
	 * @param unlockModel
	 * @return
	 */
	ReturnPaging selectUnlockByPage(UnlockModel unlockModel);
	
	/**
	 * 根据ids删除开锁方式
	 * @param ids
	 */
	void delectUnlockByIds(String[] ids);
	
	/**
	 * 根据id查看开锁详情
	 * @param id
	 * @return
	 */
	Unlock selectUnlockById(String id);
	
	/**
	 * 根据id还有指定类型删除开锁方式
	 * @param id
	 * @param type
	 */
	void deleteUnlockByIdOrType(String id, Integer type, String lockId) throws Exception ;
	
	/**
	 * pc添加开锁方式密码开锁
	 * @param unlock
	 * @return
	 */
	String insertUnlockPwd(Unlock unlock);
	
	/**
	 * 修改开锁记录
	 * @param unlock
	 */
	void updateUnlock(Unlock unlock);
	

}
