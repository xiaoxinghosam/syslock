package com.fzz.cloud.fzzcloudlockingSystem.service.radmin;

import java.util.List;

import com.fzz.cloud.fzzcloudlockingSystem.entity.radmin.Radmin;

public interface RadminService {

	void insertRadmin(String userId, String adName, String parentId) throws Exception;

	//ReturnPaging selectRadmin(String userId, Integer pageSize, Integer currentPage);

	void deleteRadminById(String id);

	List<Radmin> selectRadmin(String userId);

}
