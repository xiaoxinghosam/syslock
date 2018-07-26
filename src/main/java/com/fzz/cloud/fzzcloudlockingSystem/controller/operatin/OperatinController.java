package com.fzz.cloud.fzzcloudlockingSystem.controller.operatin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.fzz.cloud.fzzcloudlockingSystem.entity.operatin.OperatinModel;
import com.fzz.cloud.fzzcloudlockingSystem.entity.subset.AuthorizationModel;
import com.fzz.cloud.fzzcloudlockingSystem.feign.AuthorizationService;
import com.fzz.cloud.fzzcloudlockingSystem.service.operatin.OperatinService;
import com.fzz.cloud.fzzcloudlockingSystem.util.ReturnPaging;
import com.fzz.cloud.fzzcloudlockingSystem.util.ReturnUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "锁操作记录接口文档", description = "锁操作记录接口文档", position = 100, protocols = "http")
@RestController
@RequestMapping("/operatin")
public class OperatinController {

	@Autowired
	private OperatinService operatinService;
	
	@Autowired
	private AuthorizationService authorizationService;
	
	@GetMapping("/test2")
	public ReturnUtil test2() throws Exception {

		ReturnUtil returnUtil = new ReturnUtil();

		try {
			String sharePermissionsController = authorizationService.test2("wo lai shi shi");
			System.out.println("=====================日志===========" + sharePermissionsController);
		} catch (Exception e) {
			e.printStackTrace();
			returnUtil.setCode(1002);
		}

		return returnUtil;
	}
	
	
	@GetMapping("/test")
	public ReturnUtil test() throws Exception {

		ReturnUtil returnUtil = new ReturnUtil();

		try {
			List<String> list = new ArrayList<>();
			list.add("83a33756-7b89-11e8-9505-00163e06d99e");
			list.add("83a3378a-7b89-11e8-9505-00163e06d99e");
			AuthorizationModel authorizationModel = new AuthorizationModel("69712753416", "76774072-34cd-4010-bd05-c037939ce11d", "69715640858", list);
			String jsonString = JSON.toJSONString(authorizationModel);
			String sharePermissionsController = authorizationService.sharePermissionsController(jsonString);
			System.out.println("=====================日志===========" + sharePermissionsController);
		} catch (Exception e) {
			e.printStackTrace();
			returnUtil.setCode(1002);
		}

		return returnUtil;
	}
	
	

	@ApiOperation(value = "分页查询操作记录", notes = "分页查询操作记录")
	@GetMapping("/selectPage")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageSize", value = "每页显示的条数", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "currentPage", value = "当前页", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "key", value = "搜索的关键字", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "lockId", value = "锁id", required = false, dataType = "String", paramType = "query") })
	public ReturnUtil selectOperatinPage(String operatinModel) throws Exception {

		ReturnUtil returnUtil = new ReturnUtil();

		try {

			OperatinModel model = JSON.parseObject(operatinModel, OperatinModel.class);
			ReturnPaging paging = operatinService.selectOperatinByPage(model);

			if (!StringUtils.isEmpty(paging)) {
				returnUtil.setData(paging);
				returnUtil.setCode(1001);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnUtil.setCode(1002);
		}

		return returnUtil;
	}

	@ApiOperation(value = "添加操作记录", notes = "添加操作记录")
	@PostMapping("/insert")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "unlockPwd", value = "密码解锁(密码开锁有值）", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "unlockTime", value = "开锁时间", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "unlockType", value = "开锁类型0：密码开锁，1：蓝牙开锁，2：指纹开锁，3：IC卡开锁，4：身份证开锁", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "lockId", value = "锁id", required = false, dataType = "String", paramType = "query") })
	public ReturnUtil insertOperation(String operatinModel) throws Exception {

		ReturnUtil returnUtil = new ReturnUtil();

		try {

			OperatinModel model = JSON.parseObject(operatinModel, OperatinModel.class);
			operatinService.insertOperation(model);
			returnUtil.setCode(1001);

		} catch (Exception e) {
			e.printStackTrace();
			returnUtil.setCode(1002);
		}

		return returnUtil;
	}

	@ApiOperation(value = "删除操作记录", notes = "删除操作记录")
	@GetMapping("/delete")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "type", value = "开锁类型0：密码开锁，1：蓝牙开锁，2：指纹开锁，3：IC卡开锁，4：身份证开锁", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "lockId", value = "锁id", required = false, dataType = "String", paramType = "query") })
	public ReturnUtil deleteOperation(String type, String lockId) throws Exception {

		ReturnUtil returnUtil = new ReturnUtil();

		try {

			operatinService.deleteOperation(type, lockId);
			returnUtil.setCode(1001);

		} catch (Exception e) {
			e.printStackTrace();
			returnUtil.setCode(1002);
		}

		return returnUtil;
	}
	
}
