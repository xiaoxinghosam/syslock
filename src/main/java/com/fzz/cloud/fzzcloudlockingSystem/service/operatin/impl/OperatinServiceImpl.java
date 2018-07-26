package com.fzz.cloud.fzzcloudlockingSystem.service.operatin.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fzz.cloud.fzzcloudlockingSystem.entity.operatin.Operatin;
import com.fzz.cloud.fzzcloudlockingSystem.entity.operatin.OperatinModel;
import com.fzz.cloud.fzzcloudlockingSystem.mapper.operatin.OperatinMapper;
import com.fzz.cloud.fzzcloudlockingSystem.service.operatin.OperatinService;
import com.fzz.cloud.fzzcloudlockingSystem.util.ReturnPaging;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
@Transactional
public class OperatinServiceImpl implements OperatinService {
	
	@Autowired
	private OperatinMapper operatinMapper;
	
//	@Autowired
//	private SysLockMapper sysLockMapper;

	@Override
	public ReturnPaging selectOperatinByPage(OperatinModel operatinModel) {
		
		Integer currentPage = operatinModel.getCurrentPage();
		
		Integer pageSize = operatinModel.getPageSize();
		
		ReturnPaging returnPaging = new ReturnPaging();

		PageHelper.startPage(currentPage, pageSize);
		
		List<Operatin> operatinList = operatinMapper.selectOperatinByPage(operatinModel.getLockId(), operatinModel.getKey());
		
		PageInfo<Operatin> pageInfo = new PageInfo<>(operatinList);
		long total = pageInfo.getTotal();

		returnPaging.setData(operatinList);
		returnPaging.setRecordsTotal((int) total);
		returnPaging.setPageSize(pageSize);
		
		return returnPaging;
		
	}

	@Override
	public void insertOperation(OperatinModel operatinModel) {
		
		List<Operatin> operatinList = operatinModel.getOperatinList();
		operatinMapper.insertOperatinBatch(operatinList);
	}

	@Override
	public void deleteOperation(String type, String lockId) {
		// TODO Auto-generated method stub
	}
}
