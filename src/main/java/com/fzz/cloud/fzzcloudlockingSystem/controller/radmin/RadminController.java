package com.fzz.cloud.fzzcloudlockingSystem.controller.radmin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fzz.cloud.fzzcloudlockingSystem.entity.mqtt.TopicMqttv3;
import com.fzz.cloud.fzzcloudlockingSystem.entity.radmin.Radmin;
import com.fzz.cloud.fzzcloudlockingSystem.service.mqtt.MqttService;
import com.fzz.cloud.fzzcloudlockingSystem.service.radmin.RadminService;
import com.fzz.cloud.fzzcloudlockingSystem.util.FastDFSClientWrapper;
import com.fzz.cloud.fzzcloudlockingSystem.util.Mqttv3Util;
import com.fzz.cloud.fzzcloudlockingSystem.util.ReturnUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "授权公寓管理员", description = "授权公寓管理员", position = 100, protocols = "http")
@RestController
@RequestMapping("/radmin")
public class RadminController {
	
	@Autowired
	private RadminService radminService;

	@ApiOperation(value = "添加管理员", notes = "添加管理员")
	@PostMapping("/insertBatch")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userId", value = "用户id", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "adName", value = "授权管理员名称", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "parentId", value = "父级用户id", required = false, dataType = "String", paramType = "query")})
	public ReturnUtil insertBatchUserSubset(String userId,String adName, String parentId) throws Exception {

		ReturnUtil returnUtil = new ReturnUtil();

		try {
			radminService.insertRadmin(userId,adName,parentId);
			returnUtil.setCode(1001);
		} catch (Exception e) {
			e.printStackTrace();
			returnUtil.setCode(1002);
		}

		return returnUtil;
	}
	
	
	
	@ApiOperation(value = "查询管理员", notes = "查询管理员")
	@GetMapping("/selectRadmin")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userId", value = "用户id", required = false, dataType = "String", paramType = "query")})
	public ReturnUtil selectRadmin(String userId) throws Exception {

		ReturnUtil returnUtil = new ReturnUtil();

		try {
			List<Radmin> radminList = radminService.selectRadmin(userId);
			returnUtil.setCode(1001);
			returnUtil.setData(radminList);
		} catch (Exception e) {
			e.printStackTrace();
			returnUtil.setCode(1002);
		}

		return returnUtil;
	}
	
	@ApiOperation(value = "删除管理员", notes = "删除管理员")
	@GetMapping("/deleteRadmin")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "记录id", required = false, dataType = "String", paramType = "query")})
	public ReturnUtil deleteRadmin(String id) throws Exception {

		ReturnUtil returnUtil = new ReturnUtil();

		try {
			radminService.deleteRadminById(id);
			returnUtil.setCode(1001);
		} catch (Exception e) {
			e.printStackTrace();
			returnUtil.setCode(1002);
		}

		return returnUtil;
	}
	
	

}
