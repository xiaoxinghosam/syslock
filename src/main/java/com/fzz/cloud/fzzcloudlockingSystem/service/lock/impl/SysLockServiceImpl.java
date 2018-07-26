package com.fzz.cloud.fzzcloudlockingSystem.service.lock.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fzz.cloud.fzzcloudlockingSystem.entity.lock.SysLock;
import com.fzz.cloud.fzzcloudlockingSystem.entity.lock.SysLockModel;
import com.fzz.cloud.fzzcloudlockingSystem.entity.subset.UserSubset;
import com.fzz.cloud.fzzcloudlockingSystem.mapper.lock.SysLockMapper;
import com.fzz.cloud.fzzcloudlockingSystem.mapper.room.RoomMapper;
import com.fzz.cloud.fzzcloudlockingSystem.mapper.subset.UserSubsetMapper;
import com.fzz.cloud.fzzcloudlockingSystem.service.lock.SysLockService;
import com.fzz.cloud.fzzcloudlockingSystem.util.UserSubsetTreeUtils;

@Service
@Transactional
public class SysLockServiceImpl implements SysLockService {
	
	@Autowired
	private SysLockMapper sysLockMapper;
	
	@Autowired
	private UserSubsetMapper userSubsetMapper;
	
	@Autowired
	private RoomMapper roomMapper;
	
	@Override
	public SysLock selectSysLockByLockNumber(String lockNumber) {
		return sysLockMapper.selectSysLockByLockNumber(lockNumber);
	}


	@Override
	public Map<String, Object> selectSysLockByPage(String userId, String parentId) {

		Map<String, Object> resourceMap = new HashMap<>();

		List<SysLock> userLocks = sysLockMapper.selectSysLockByPage(userId);
		
		List<SysLock> userList = new ArrayList<>();
		
		for (SysLock sysLock : userLocks) {
			
			List<String> sqlGroup = userSubsetMapper.sqlGroup(sysLock.getId());
			List<UserSubset> selectListUserSubset = userSubsetMapper.selectListUserSubset(sysLock.getId());
			Map<String, List<UserSubset>> returnArrMap = UserSubsetTreeUtils.returnArrMap(sqlGroup, selectListUserSubset);
			List<UserSubset> mapForSubset = UserSubsetTreeUtils.MapForSubset(returnArrMap);
			boolean insert = UserSubsetTreeUtils.isInsert(mapForSubset, sysLock.getParentId());//如果所有的父节点都存在没有被删除可添加节点
			if(insert) {
				userList.add(sysLock);
			}
			
		}
		resourceMap.put("userLock", userList);
		if(StringUtils.isNotBlank(parentId)) {
			List<SysLock> parentLocks = sysLockMapper.selectSysLockByPage(parentId);
			List<SysLock> parentList = new ArrayList<>();
			for (SysLock sysLock : parentLocks) {
				
				List<String> sqlGroup = userSubsetMapper.sqlGroup(sysLock.getId());
				List<UserSubset> selectListUserSubset = userSubsetMapper.selectListUserSubset(sysLock.getId());
				Map<String, List<UserSubset>> returnArrMap = UserSubsetTreeUtils.returnArrMap(sqlGroup, selectListUserSubset);
				List<UserSubset> mapForSubset = UserSubsetTreeUtils.MapForSubset(returnArrMap);
				boolean insert = UserSubsetTreeUtils.isInsert(mapForSubset, sysLock.getParentId());//如果所有的父节点都存在没有被删除可添加节点
				if(insert) {
					parentList.add(sysLock);
				}
				
			}
			resourceMap.put("parentLock", parentList);
		}
		
		return resourceMap;
	}

	@Override
	public void updateBySysLock(SysLockModel sysLockModel) {

		sysLockMapper.updateBySysLockModel(sysLockModel);
	}

	@Override
	public Boolean deleteBySysLockIds(String[] ids) {
		sysLockMapper.deleteBySysLockIds(ids);
		return null;
	}

	@Override
	public String initLockModel(SysLockModel sysLockModel) {
		
		String id = UUID.randomUUID().toString();
		String subsetId = UUID.randomUUID().toString();
		SysLock sysLock = new SysLock(id, sysLockModel.getAdminUserId(), sysLockModel.getLockNumber(), sysLockModel.getLockName(), sysLockModel.getAllow(), sysLockModel.getElectricity(), sysLockModel.getAdminPsw(),sysLockModel.getSecretKey());
		
		UserSubset userSubset = new UserSubset();
		userSubset.setId(subsetId);
		userSubset.setLockId(id);
		userSubset.setKeyName(sysLockModel.getLockName());
		userSubset.setUserId(sysLockModel.getAdminUserId());
		
		//添加锁用户子集关系为顶级父
		userSubsetMapper.insertUserSubset(userSubset);
		sysLockMapper.insertSysLock(sysLock);
		
		if(null != sysLockModel.getRoomId() && !"".equals(sysLockModel.getRoomId())) {
			roomMapper.updateRoomByLockId(sysLockModel.getRoomId(), id);
		}
		
		return id;
	}

	@Override
	public void deleteBySysLockId(String id) {
		sysLockMapper.deleteBySysLockId(id);
	}


	@Override
	public SysLock selectSysLockByLockId(String id) {
		return sysLockMapper.selectSysLockByLockId(id);
	}

	
	@Override
	public void installLockBinding(SysLockModel sysLockModel) {
		
		sysLockMapper.installLockBinding(sysLockModel.getInstallId(), sysLockModel.getId());
		roomMapper.updateRoomByLockId(sysLockModel.getRoomId(), sysLockModel.getId());
	}
	

	@Override
	public void updateSysLockName(String id, String lockName) {
		sysLockMapper.updateSysLockName(id, lockName);
	}
}
