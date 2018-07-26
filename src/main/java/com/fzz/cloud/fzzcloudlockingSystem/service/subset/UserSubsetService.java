package com.fzz.cloud.fzzcloudlockingSystem.service.subset;

import java.util.List;

import com.fzz.cloud.fzzcloudlockingSystem.entity.subset.UserSubset;
import com.fzz.cloud.fzzcloudlockingSystem.entity.subset.UserSubsetModel;
import com.fzz.cloud.fzzcloudlockingSystem.util.ReturnPaging;

public interface UserSubsetService {
	
	/**
	 * 添加用户钥匙
	 * @param userSubset
	 */
	String insertUserSubset(UserSubset userSubset) throws Exception;
	
	/**
	 * 分页查询用户钥匙包括子集
	 * @param userSubsetModel
	 * @return
	 */
	ReturnPaging selectUserSubsetPage(UserSubsetModel userSubsetModel);
	
	/**
	 * 根据多个id删除对象
	 * @param ids
	 */
	void deleteUserSubset(List<String> ids);
	
	/**
	 * 添加管理员
	 * @param userSubset
	 * @return
	 */
	void insertBatchUserSubset(String userId,String keyName, String parentId);
	
	
	/**
	 * 根据id查询钥匙详情
	 * @param id
	 * @return
	 */
	UserSubset selectUserSubsetInfo(String id);
	
	/**
	 * 根据id修改钥匙
	 * @param userSubset
	 */
	void updateUserSubset(UserSubset userSubset);

}
