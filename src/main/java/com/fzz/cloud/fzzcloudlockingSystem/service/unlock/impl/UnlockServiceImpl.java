package com.fzz.cloud.fzzcloudlockingSystem.service.unlock.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import com.fzz.cloud.fzzcloudlockingSystem.entity.subset.UserSubset;
import com.fzz.cloud.fzzcloudlockingSystem.entity.unlock.Unlock;
import com.fzz.cloud.fzzcloudlockingSystem.entity.unlock.UnlockModel;
import com.fzz.cloud.fzzcloudlockingSystem.mapper.lock.SysLockMapper;
import com.fzz.cloud.fzzcloudlockingSystem.mapper.subset.UserSubsetMapper;
import com.fzz.cloud.fzzcloudlockingSystem.mapper.unlock.UnlockMapper;
import com.fzz.cloud.fzzcloudlockingSystem.service.unlock.UnlockService;
import com.fzz.cloud.fzzcloudlockingSystem.util.ArithmeticUtils;
import com.fzz.cloud.fzzcloudlockingSystem.util.ReturnPaging;
import com.fzz.cloud.fzzcloudlockingSystem.util.UserSubsetTreeUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class UnlockServiceImpl implements UnlockService {

	@Autowired
	private UnlockMapper unlockMapper;
	
	@Autowired
	private UserSubsetMapper userSubsetMapper;
	
	@Autowired
	private SysLockMapper sysLockMapper;

	@Override
	public String insertUnlockPwd(Unlock unlock) {
		
		String allow = sysLockMapper.selectSysLockAllow(unlock.getLockId());
		
		if(StringUtils.isNotBlank(allow)) {
			
			String startTime = unlock.getStartTime();
			
			String endTime = unlock.getEndTime();
			
			String unlockFlag;
			
			if (StringUtils.isNotBlank(endTime)) {
				// 限时密码
				unlock.setUnlockType(0);
				unlockFlag = ArithmeticUtils.getTimePasswordOrForPwd(startTime, endTime, allow ,null);
				
			} else {
				// 永久密码
				unlock.setUnlockType(1);
				unlockFlag = ArithmeticUtils.permanentPwd(startTime, allow,"0");
			}
			
			unlock.setAddType(3);//密码解锁
			unlock.setUnlockFlag(unlockFlag);
			unlockMapper.insertUnlock(unlock);
			return unlockFlag;
		}
		
		return null;
	}

	@Override
	public String insertUnlock(Unlock unlock) {
		String unlockFlag = "";
		if(unlock.getUnlockFlag() != null && !"".equals(unlock.getUnlockFlag())) {
			unlockFlag = unlock.getUnlockFlag();
		}
		Integer unlockType = unlock.getUnlockType();
		if (unlock.getAddType() == 3) {// 添加密码
			String startTime = unlock.getStartTime();
			String endTime = unlock.getEndTime();
			if (unlockType == 0) {// 开锁类型0：限时，1永久(永久指纹时间只有起始时间)2自定义，3循环，4清空//自定义密码标识已生成直接保存
				// 限时密码
				unlockFlag = ArithmeticUtils.getTimePasswordOrForPwd(startTime, endTime, unlock.getAllow(),null);
			} else if (unlockType == 1) {
				// 永久密码
				unlockFlag = ArithmeticUtils.permanentPwd(startTime, unlock.getAllow(),"0");
			} else if (unlockType == 3) {
				// 循环密码
				String forWay = unlock.getForWay();// 获取循环方式
				unlockFlag = ArithmeticUtils.getTimePasswordOrForPwd(startTime, endTime, unlock.getAllow(), forWay);
			} else if (unlockType == 4) {
				// 清空密码
//				unlockFlag = ArithmeticUtils.permanentPwd(startTime, unlock.getAllow(), "1");
			}
			unlock.setUnlockFlag(unlockFlag);
		}
		unlockMapper.insertUnlock(unlock);
		return unlockFlag;
	}

	@Override
	public ReturnPaging selectUnlockByPage(UnlockModel unlockModel) {
		
		UserSubsetTreeUtils.returnList.clear();
		
		ReturnPaging returnPaging = new ReturnPaging();

		List<String> sqlGroup = userSubsetMapper.sqlGroup(unlockModel.getLockId());
		List<UserSubset> selectListUserSubset = userSubsetMapper.selectListUserSubset(unlockModel.getLockId());
		UserSubset userSubset = userSubsetMapper.selectUserSubsetByLockIdAndUserId(unlockModel.getLockId(), unlockModel.getAddPerson());
		Map<String, List<UserSubset>> returnArrMap = UserSubsetTreeUtils.returnArrMap(sqlGroup, selectListUserSubset);
		List<UserSubset> mapForSubset = UserSubsetTreeUtils.MapForSubset(returnArrMap);
		
		List<String> subset = null;
		
		if(null != userSubset) {
			
			subset = UserSubsetTreeUtils.isSubset(mapForSubset, userSubset.getId());
		}
		
		System.out.println("指定父节点下面的子节点为：" + subset);
		unlockModel.setChildrenList(subset);
		
		PageHelper.startPage(unlockModel.getCurrentPage(), unlockModel.getPageSize());
		List<Unlock> unlockList = unlockMapper.selectUnlockByPage(unlockModel);

		PageInfo<Unlock> pageInfo = new PageInfo<>(unlockList);

		returnPaging.setData(unlockList);
		returnPaging.setRecordsTotal((int) pageInfo.getTotal());
		returnPaging.setPageSize(unlockModel.getPageSize());

		return returnPaging;

	}

	@Override
	public void delectUnlockByIds(String[] ids) {
		unlockMapper.delectUnlockIds(ids);
	}

	@Override
	public Unlock selectUnlockById(String id) {
		return unlockMapper.selectUnlockById(id);
	}

	@Override
	public void deleteUnlockByIdOrType(String id, Integer type, String lockId) throws Exception {
		String[] ids;
		if (id != null && !"".equals(id)) {
			if (id.contains(",")) {
				ids = id.split(",");
			} else {
				ids = new String[] { id };
			}
			unlockMapper.delectUnlockIds(ids);
			return;
		} else if (type != null && lockId != null && !"".equals(lockId)) {
			unlockMapper.deleteUnlockAll(lockId, type);
		} 
	}
	
	@JmsListener(destination="topic.test1")
	public void test(String message) {
		System.out.println("unlockMapper:" + message);
		
	}

	@Override
	public void updateUnlock(Unlock unlock) {
		unlockMapper.updateUnlock(unlock);
	}

}
