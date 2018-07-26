package com.fzz.cloud.fzzcloudlockingSystem.mapper.subset;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fzz.cloud.fzzcloudlockingSystem.entity.subset.UserSubset;

public interface UserSubsetMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserSubset record);

    int insertSelective(UserSubset record);

    int updateByPrimaryKey(UserSubset record);

	void insertUserSubset(UserSubset userSubset);

	UserSubset selectUserSubsetByLockIdAndUserId(@Param(value = "lockId") String lockId, @Param(value = "userId") String userId);
	
	//测试
	List<String> sqlGroup(String lockId);

	List<UserSubset> selectListUserSubset(String lockId);

	void updateUserSubset(UserSubset userSubset);

	List<UserSubset> selectUserSubsetPage(@Param(value = "lockId") String lockId, @Param(value = "userIds") List<String> userIds);
	
	
	void updateUserSubsetByIds(List<String> ids);

	List<UserSubset> selectUserSubsetByUserId(@Param(value = "userId") String userId);

	
	void updateBatchUserSubset(@Param(value = "updateList") List<UserSubset> updateList);

	void insertBatchUserSubset(@Param(value = "addList")List<UserSubset> addList);

	void updateSysLockName(@Param(value = "id")String id,@Param(value = "keyName") String keyName);

	UserSubset selectUserSubsetInfo(String id);
	
	/**
	 * 根据多个id查询钥匙
	 * @param ids
	 * @return
	 */
	List<UserSubset> selectSubsetByIds(@Param(value = "ids") List<String> ids);
}