package com.fzz.cloud.fzzcloudlockingSystem.controller.unlock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.fzz.cloud.fzzcloudlockingSystem.entity.unlock.Unlock;
import com.fzz.cloud.fzzcloudlockingSystem.entity.unlock.UnlockModel;
import com.fzz.cloud.fzzcloudlockingSystem.service.unlock.UnlockService;
import com.fzz.cloud.fzzcloudlockingSystem.util.ReturnPaging;
import com.fzz.cloud.fzzcloudlockingSystem.util.ReturnUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 开锁方式controller
 */
@Api(value = "开锁方式=指纹，ic卡，身份证，密码开锁接口文档", description = "开锁方式=指纹，ic卡，身份证，密码开锁接口文档", position = 100, protocols = "http")
@RestController
@RequestMapping("/unlock")

public class UnlockController {

	@Autowired
	private UnlockService unlockService;
	
	@ApiOperation(value = "(PC)添加密码开锁", notes = "添加密码开锁(限时：有结束时间，永久：生成时间)")
	@PostMapping("/insertPwd")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "lockId", value = "锁id", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "unlockName", value = "开锁方式名称", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "addPerson", value = "添加用户的userid", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "startTime", value = "生效时间2018-12-21-18-00", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "endTime", value = "失效时间2018-12-21-19-00", required = false, dataType = "String", paramType = "query")})
	public ReturnUtil insertUnlockPwd(String unlockModel) throws Exception {

		ReturnUtil returnUtil = new ReturnUtil();

		try {
			
			System.out.println("添加密码的模型PC：" + unlockModel);
			Unlock unlock = JSON.parseObject(unlockModel, Unlock.class);
			String pwd = unlockService.insertUnlockPwd(unlock);
			returnUtil.setData(pwd);
			returnUtil.setCode(1001);

		} catch (Exception e) {
			e.printStackTrace();
			returnUtil.setCode(1002);
		}

		return returnUtil;
	}

	@ApiOperation(value = "添加开锁方式", notes = "添加开锁方式")
	@PostMapping("/insert")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "lockId", value = "锁id", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "unlockName", value = "开锁方式名称（有就传入）", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "unlockFlag", value = "开锁标识(指纹标识，ic卡标识，身份证标识，密码标识后台生成自定义密码除外)", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "allow", value = "开锁标识例：54321五位的数字字符串", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "addPerson", value = "添加用户的userid", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "forWay", value = "循环方式(循环密码有值1-7：为普通星期，8：周末，9：工作日,0:每日)", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "startTime", value = "生效时间2018-12-21-18-00", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "endTime", value = "失效时间2018-12-21-18-00", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "addType", value = "添加类型0：IC卡，1：身份证，2：指纹，3：密码", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "unlockType", value = "开锁类型0：限时，1永久(永久指纹时间只有起始时间)2自定义，3循环，4清空", required = false, dataType = "String", paramType = "query") })
	public ReturnUtil insertUnlock(String unlockModel) throws Exception {

		ReturnUtil returnUtil = new ReturnUtil();

		try {
			System.out.println("insertUnlock添加开锁方式的模型:" + unlockModel);
			Unlock unlock = JSON.parseObject(unlockModel, Unlock.class);
			String pwd = unlockService.insertUnlock(unlock);
			returnUtil.setData(pwd);
			returnUtil.setCode(1001);
		} catch (Exception e) {
			e.printStackTrace();
			returnUtil.setCode(1002);
		}

		return returnUtil;
	}
	
	@ApiOperation(value = "修改ic卡/身份证", notes = "修改ic卡/身份证")
	@PostMapping("/update")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "lockId", value = "锁id", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "unlockName", value = "开锁方式名称（有就传入）", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "addPerson", value = "添加用户的userid", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "startTime", value = "生效时间2018-12-21-18-00", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "endTime", value = "失效时间2018-12-21-18-00", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "addType", value = "添加类型0：IC卡，1：身份证，2：指纹，3：密码", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "unlockType", value = "开锁类型0：限时，1永久(永久指纹时间只有起始时间)2自定义，3循环，4清空", required = false, dataType = "String", paramType = "query") })
	public ReturnUtil updateUnlock(String unlockModel) throws Exception {

		ReturnUtil returnUtil = new ReturnUtil();

		try {
			System.out.println("updateUnlock修改开锁方式的模型:" + unlockModel);
			Unlock unlock = JSON.parseObject(unlockModel, Unlock.class);
			unlockService.updateUnlock(unlock);
			returnUtil.setCode(1001);
		} catch (Exception e) {
			e.printStackTrace();
			returnUtil.setCode(1002);
		}

		return returnUtil;
	}


	@ApiOperation(value = "删除开锁方式", notes = "根据开锁方式id删除只是修改状态")
	@PostMapping("/delete")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "ids", value = "删除指定开锁ids(可以有多个用,分割)", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "lockId", value = "删除指定开锁ids(可以有多个用,分割)", required = false, dataType = "String", paramType = "query")})
	@ApiImplicitParam(name = "type", value = "添加类型0：IC卡，1：身份证，2：指纹，3：密码", required = false, dataType = "String", paramType = "query")
	public ReturnUtil deleteUnlockByIdOrType(String ids, Integer type, String lockId) throws Exception {

		ReturnUtil returnUtil = new ReturnUtil();

		try {

			unlockService.deleteUnlockByIdOrType(ids, type, lockId);
			returnUtil.setCode(1001);

		} catch (Exception e) {
			e.printStackTrace();
			returnUtil.setCode(1002);
		}

		return returnUtil;
	}
	

	@ApiOperation(value = "分页查询开锁方式详情", notes = "分页查询开锁方式详情")
	@GetMapping("/selectPage")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageSize", value = "每页显示的条数", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "currentPage", value = "当前页", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "addPerson", value = "用户id", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "addType", value = "添加类型0：IC卡,身份证，2：指纹，3：密码", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "lockId", value = "锁id", required = false, dataType = "String", paramType = "query") })
	public ReturnUtil selectUnlockByPage(String unlockModel) throws Exception {

		ReturnUtil returnUtil = new ReturnUtil();

		try {

			UnlockModel model = JSON.parseObject(unlockModel, UnlockModel.class);

			ReturnPaging paging = unlockService.selectUnlockByPage(model);

			returnUtil.setData(paging);
			returnUtil.setCode(1001);
		} catch (Exception e) {
			e.printStackTrace();
			returnUtil.setCode(1002);
		}

		return returnUtil;
	}

	@ApiOperation(value = "查询开锁方式详情", notes = "根据id查询开锁方式详情")
	@GetMapping("/selectById")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "开锁方式id", required = false, dataType = "String", paramType = "query") })
	public ReturnUtil selectUnlockById(@RequestParam(name = "id") String id) throws Exception {

		ReturnUtil returnUtil = new ReturnUtil();

		try {

			Unlock unlock = unlockService.selectUnlockById(id);
			returnUtil.setData(unlock);
			returnUtil.setCode(1001);

		} catch (Exception e) {
			e.printStackTrace();
			returnUtil.setCode(1002);
		}

		return returnUtil;
	}

}
