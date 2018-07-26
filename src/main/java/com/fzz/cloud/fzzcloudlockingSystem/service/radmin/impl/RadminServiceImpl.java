package com.fzz.cloud.fzzcloudlockingSystem.service.radmin.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fzz.cloud.fzzcloudlockingSystem.entity.radmin.Radmin;
import com.fzz.cloud.fzzcloudlockingSystem.mapper.radmin.RadminMapper;
import com.fzz.cloud.fzzcloudlockingSystem.service.radmin.RadminService;

@Service
@Transactional
public class RadminServiceImpl implements RadminService {
	
	@Autowired
	private RadminMapper radminMapper;

	@Override
	public void insertRadmin(String userId, String adName, String parentId) throws Exception {
		
		String id = radminMapper.selectRadmin(userId,parentId);
		
		if(StringUtils.isBlank(id)) {
			Radmin radmin = new Radmin();
			radmin.setUserId(userId);
			radmin.setParentId(parentId);
			radmin.setAdName(adName);
			radminMapper.insertSelective(radmin);
		} else {
			throw new Exception("此管理员存在");
		}
	}

/*	@Override
	public ReturnPaging selectRadmin(String userId, Integer pageSize, Integer currentPage) {
		
		ReturnPaging returnPaging = new ReturnPaging();

		PageHelper.startPage(currentPage, pageSize);
		
		List<Radmin> radminList = radminMapper.selectRadminByPage(userId);
		
		PageInfo<Radmin> pageInfo = new PageInfo<>(radminList);
		long total = pageInfo.getTotal();

		returnPaging.setData(radminList);
		returnPaging.setRecordsTotal((int) total);
		returnPaging.setPageSize(pageSize);
		
		return returnPaging;
	}*/

	@Override
	public void deleteRadminById(String id) {
		radminMapper.deleteRadminById(id);
	}

	@Override
	public List<Radmin> selectRadmin(String userId) {
		return radminMapper.selectRadminByPage(userId);
	}
}
