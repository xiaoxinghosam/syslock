package com.fzz.cloud.fzzcloudlockingSystem.mapper.unlock;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fzz.cloud.fzzcloudlockingSystem.entity.unlock.Unlock;
import com.fzz.cloud.fzzcloudlockingSystem.entity.unlock.UnlockModel;

public interface UnlockMapper {

	void insertUnlock(Unlock unlock);

	List<Unlock> selectUnlockByPage(UnlockModel unlockModel);

	void delectUnlockIds(@Param(value = "ids") String[] ids);

	Unlock selectUnlockById(String id);

	void updateUnlock(Unlock unlock);

	void deleteUnlockAll(@Param(value = "lockId")String lockId,@Param(value = "type") Integer type);

}
