package com.fzz.cloud.fzzcloudlockingSystem.service.operatin;

import com.fzz.cloud.fzzcloudlockingSystem.entity.operatin.OperatinModel;
import com.fzz.cloud.fzzcloudlockingSystem.util.ReturnPaging;

public interface OperatinService {
	
	/**
	 * 分页查询操作记录
	 * @param operatinModel
	 * @return
	 */
	ReturnPaging selectOperatinByPage(OperatinModel operatinModel);
	
	/**
	 * 添加操作记录
	 * @param operatinModel
	 */
	void insertOperation(OperatinModel operatinModel);
	
	/**
	 * 根据类型删除操作记录
	 * @param type
	 * @param lockId
	 */
	void deleteOperation(String type, String lockId);

}
