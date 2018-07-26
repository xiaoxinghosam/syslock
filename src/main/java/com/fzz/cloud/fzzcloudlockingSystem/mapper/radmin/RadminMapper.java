package com.fzz.cloud.fzzcloudlockingSystem.mapper.radmin;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fzz.cloud.fzzcloudlockingSystem.entity.radmin.Radmin;

public interface RadminMapper {
	
    int insert(Radmin record);

    int insertSelective(Radmin record);

	List<Radmin> selectRadminByPage(String userId);

	void deleteRadminById(String id);

	String selectRadmin(@Param(value = "userId") String userId,@Param(value = "parentId") String parentId);
}