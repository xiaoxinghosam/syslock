package com.fzz.cloud.fzzcloudlockingSystem.service.subset.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.fzz.cloud.fzzcloudlockingSystem.entity.subset.UserSubset;
import com.fzz.cloud.fzzcloudlockingSystem.entity.subset.UserSubsetModel;
import com.fzz.cloud.fzzcloudlockingSystem.feign.AuthorizationService;
import com.fzz.cloud.fzzcloudlockingSystem.mapper.subset.UserSubsetMapper;
import com.fzz.cloud.fzzcloudlockingSystem.service.subset.UserSubsetService;
import com.fzz.cloud.fzzcloudlockingSystem.util.ReturnPaging;
import com.fzz.cloud.fzzcloudlockingSystem.util.UserSubsetTreeUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
@Transactional
public class UserSubsetServiceImpl implements UserSubsetService {

	@Autowired
	private UserSubsetMapper userSubsetMapper;
	
	@Autowired
	private AuthorizationService authorizationService;
	
	@Override
	public void insertBatchUserSubset(String userId,String keyName, String parentId) {
		
		//父id的所有锁
		List<UserSubset> parentUserSubsetList = userSubsetMapper.selectUserSubsetByUserId(parentId);
		//子id的所有锁有可能为空也有可能是管理
		List<UserSubset> userSubsetList = userSubsetMapper.selectUserSubsetByUserId(userId);
		
		List<UserSubset> updateList = new ArrayList<>();
		
		List<UserSubset> addList = new ArrayList<>();
		
		for (UserSubset parentUserSubset : parentUserSubsetList) {
			if(parentUserSubset.getParentId() == null) {
				UserSubset userSubset = new UserSubset(parentUserSubset.getLockId(), userId, parentUserSubset.getId(), keyName, null, null);
				addList.add(userSubset);
				if(userSubsetList != null && userSubsetList.size() > 0) {
					for (UserSubset userSubset2 : userSubsetList) {
						if(parentUserSubset.getLockId().equals(userSubset2.getLockId()) && userSubset2.getParentId() != null) {
							//有同样的锁但是不是管理员修改父级为父用户修改权限
							userSubset2.setParentId(parentUserSubset.getId());
							userSubset2.setEndTime(null);
							updateList.add(userSubset2);
						} else {
							continue;
						}
					}
				} 
			} else {
				continue;
			}
		}
		
		
		if(updateList != null && updateList.size() > 0) {
			userSubsetMapper.updateBatchUserSubset(updateList);
		}
		
		if(addList != null && addList.size() > 0) {
			userSubsetMapper.insertBatchUserSubset(addList);
		}
	}

	@Override
	public String insertUserSubset(UserSubset userSubset) throws Exception {
		String id = UUID.randomUUID().toString();
		userSubset.setId(id);
		String parentUserId = userSubset.getParentId();
		// 查找这个用户层级是否出现在这把锁上
		String lockId = userSubset.getLockId();
		String userId = userSubset.getUserId();
		
		UserSubset subset = userSubsetMapper.selectUserSubsetByLockIdAndUserId(lockId,userId);
		
//		List<UserSubset> listUserSubset = userSubsetMapper.selectListUserSubset(userSubset.getLockId());
		List<String> sqlGroup = userSubsetMapper.sqlGroup(lockId);
		List<UserSubset> selectListUserSubset = userSubsetMapper.selectListUserSubset(lockId);
		Map<String, List<UserSubset>> returnArrMap = UserSubsetTreeUtils.returnArrMap(sqlGroup, selectListUserSubset);
		List<UserSubset> mapForSubset = UserSubsetTreeUtils.MapForSubset(returnArrMap);
		//查找添加父节点
		boolean parentFlag = false;
		UserSubset parent = userSubsetMapper.selectUserSubsetByLockIdAndUserId(userSubset.getLockId(),parentUserId);
		if(!StringUtils.isEmpty(parent)) {
			if(parent.getParentId() == null) {
				parentFlag = true;//第一级
			} else {
				parentFlag = UserSubsetTreeUtils.isInsert(mapForSubset, parent.getParentId());//如果所有的父节点都存在没有被删除可添加节点
			}
		}
		if (StringUtils.isEmpty(subset)) {//如果为空表示不再层级上
			if(parentFlag) {
				userSubset.setParentId(parent.getId());
				userSubsetMapper.insertUserSubset(userSubset);
				return "1001";
			} else {
				return "1010";//throw new Exception("父节点已删除");
			}
		} else {
			boolean flag = UserSubsetTreeUtils.isInsert(mapForSubset, subset.getParentId());//如果所有的父节点都存在没有被删除说明层级关系已存在
			if(flag) {
				return "1012";//throw new Exception("创建的层级关系已存在");
			} else if(parentFlag) {//父节点不为空，并且父节点上面的所有父父节点没有被删除更新当前这个节点的父节点为父节点
				subset.setStatu(1);
				userSubsetMapper.updateUserSubset(subset);//删除原来的数据
				userSubset.setParentId(parent.getId());//设置新的参数数据
				userSubsetMapper.insertUserSubset(userSubset);
				return "1001";
			}
		}
		
		/*List<String> list = new ArrayList<>();
		list.add("83a33756-7b89-11e8-9505-00163e06d99e");
		list.add("83a3378a-7b89-11e8-9505-00163e06d99e");
		AuthorizationModel authorizationModel = new AuthorizationModel("69712753416", "76774072-34cd-4010-bd05-c037939ce11d", "69715640858", list);
		String jsonString = JSON.toJSONString(authorizationModel);
		String sharePermissionsController = authorizationService.sharePermissionsController(jsonString);
		System.out.println("=====================日志===========" + sharePermissionsController);*/
		
		return "1010";
	}
	

	@Override
	public ReturnPaging selectUserSubsetPage(UserSubsetModel userSubsetModel) {
		
		UserSubsetTreeUtils.returnList.clear();
		
		Integer currentPage = userSubsetModel.getCurrentPage();
		Integer pageSize = userSubsetModel.getPageSize();
	    ReturnPaging returnPaging = new ReturnPaging();
	    
		List<String> sqlGroup = userSubsetMapper.sqlGroup(userSubsetModel.getLockId());
		List<UserSubset> selectListUserSubset = userSubsetMapper.selectListUserSubset(userSubsetModel.getLockId());
		Map<String, List<UserSubset>> returnArrMap = UserSubsetTreeUtils.returnArrMap(sqlGroup, selectListUserSubset);
		List<UserSubset> mapForSubset = UserSubsetTreeUtils.MapForSubset(returnArrMap);
		
		UserSubset userSubset = userSubsetMapper.selectUserSubsetByLockIdAndUserId(userSubsetModel.getLockId(), userSubsetModel.getUserId());
		
		List<String> subset = new ArrayList<>();
		
		if(!StringUtils.isEmpty(userSubset)) {
			
			subset = UserSubsetTreeUtils.isSubset(mapForSubset, userSubset.getId());
		}
		
		if(subset != null && subset.size() > 0) {
			if(subset.contains(userSubsetModel.getUserId())) {
				subset.remove(userSubsetModel.getUserId());
			}
		}
		
		System.out.println("指定父节点下面的子节点为：" + subset);
		
		if(subset.size() > 0) {
			
			PageHelper.startPage(currentPage, pageSize);
			List<UserSubset> userSubsetList = userSubsetMapper.selectUserSubsetPage(userSubsetModel.getLockId(), subset);
			
			PageInfo<UserSubset> pageInfo = new PageInfo<>(userSubsetList);
			long total = pageInfo.getTotal();
			
			returnPaging.setData(userSubsetList);
			returnPaging.setRecordsTotal((int) total);
			returnPaging.setPageSize(pageSize);
			
			return returnPaging;
		}
		return null;
		
	}


	@Override
	public void deleteUserSubset(List<String> ids) {
		//删除钥匙
		userSubsetMapper.updateUserSubsetByIds(ids);
	
	}

	@Override
	public UserSubset selectUserSubsetInfo(String id) {
		return userSubsetMapper.selectUserSubsetInfo(id);
	}

	@Override
	public void updateUserSubset(UserSubset userSubset) {
		userSubsetMapper.updateUserSubset(userSubset);
	}
}
