package com.fzz.cloud.fzzcloudlockingSystem.mapper.operatin;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fzz.cloud.fzzcloudlockingSystem.entity.operatin.Operatin;

public interface OperatinMapper {
    int deleteByPrimaryKey(String id);

    Operatin selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Operatin record);

    int updateByPrimaryKey(Operatin record);
    
    //===============================

	void insertOperatin(Operatin operatin);

	List<Operatin> selectOperatinByPage(@Param(value = "lockId") String lockId,@Param(value = "key") String key);

	void insertOperatinBatch(List<Operatin> operatinList);
}