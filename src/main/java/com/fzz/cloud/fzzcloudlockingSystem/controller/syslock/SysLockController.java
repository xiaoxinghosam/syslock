package com.fzz.cloud.fzzcloudlockingSystem.controller.syslock;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.fzz.cloud.fzzcloudlockingSystem.entity.lock.SysLock;
import com.fzz.cloud.fzzcloudlockingSystem.entity.lock.SysLockModel;
import com.fzz.cloud.fzzcloudlockingSystem.service.lock.SysLockService;
import com.fzz.cloud.fzzcloudlockingSystem.util.ReturnUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 锁接口
 * 
 */
@Api(value = "锁操作接口文档", description = "锁操作接口文档", position = 100, protocols = "http")
@RestController
@RequestMapping("/sysLock")
public class SysLockController {

	@Autowired
	private SysLockService sysLockService;
	

	@ApiOperation(value = "查询锁对象详情", notes = "查询锁对象详情")
	@GetMapping("/selectById")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "锁id", required = false, dataType = "String", paramType = "query") })
	public ReturnUtil selectSysLockById(String id) throws Exception {

		ReturnUtil returnUtil = new ReturnUtil();

		try {

			SysLock sysLock = sysLockService.selectSysLockByLockId(id);
			returnUtil.setData(sysLock);
			returnUtil.setCode(1001);

		} catch (Exception e) {
			e.printStackTrace();
			returnUtil.setCode(1002);
		}

		return returnUtil;
	}
	
	@ApiOperation(value = "查询锁对象详情", notes = "查询锁对象详情") // 5499 4599
	@GetMapping("/selectMakNum")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "lockNumber", value = "蓝牙Mak地址", required = false, dataType = "String", paramType = "query") })
	public ReturnUtil selectSysLockByLockNumber(String lockNumber) throws Exception {

		ReturnUtil returnUtil = new ReturnUtil();

		try {

		    SysLock sysLock = sysLockService.selectSysLockByLockNumber(lockNumber);
			returnUtil.setData(sysLock);
			returnUtil.setCode(1001);

		} catch (Exception e) {
			e.printStackTrace();
			returnUtil.setCode(1002);
		}

		return returnUtil;
	}

	@ApiOperation(value = "查询用户下所有锁对象", notes = "查询用户下所有锁对象")
	@GetMapping("/selectAll")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "parentId", value = "管理员id", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "userId", value = "用户id", required = false, dataType = "String", paramType = "query") })
	public ReturnUtil selectSysLockPage(String userId,String parentId) throws Exception {
		
		ReturnUtil returnUtil = new ReturnUtil();

		try {
			// 查询数据库锁对象
			Map<String, Object> selectSysLockByPage = sysLockService.selectSysLockByPage(userId,parentId);
			returnUtil.setData(selectSysLockByPage);
			returnUtil.setCode(1001);

		} catch (Exception e) {
			e.printStackTrace();
			returnUtil.setCode(1002);
		}

		return returnUtil;
	}

	@ApiOperation(value = "删除锁信息包括蓝牙管理员", notes = "根据被允许开锁的标识,锁id,管理员密码,删除只是修改状态")
	@GetMapping("/delete")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "锁id", required = false, dataType = "String", paramType = "query") })
	public ReturnUtil delectSysLock(String id) throws Exception {

		ReturnUtil returnUtil = new ReturnUtil();

		try {

			sysLockService.deleteBySysLockId(id);
			returnUtil.setCode(1001);

		} catch (Exception e) {
			e.printStackTrace();
			returnUtil.setCode(1002);
		}

		return returnUtil;
	}

	@ApiOperation(value = "修改锁信息", notes = "修改锁信息")
	@PostMapping("/update")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "锁id", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "adminUserId", value = "(蓝牙管理员用户)用户id", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "lockName", value = "锁名称", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "adminPsw", value = "管理员密码", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "electricity", value = "锁电量", required = false, dataType = "String", paramType = "query") })
	public ReturnUtil updateSysLock(String sysLockModel) throws Exception {

		ReturnUtil returnUtil = new ReturnUtil();

		try {

			SysLockModel model = JSON.parseObject(sysLockModel, SysLockModel.class);
			sysLockService.updateBySysLock(model);
			returnUtil.setCode(1001);

		} catch (Exception e) {
			e.printStackTrace();
			returnUtil.setCode(1002);
		}

		return returnUtil;
	}

	@ApiOperation(value = "第一次安装锁需要对 锁的操作(初始化锁)", notes = "第一次安装锁需要对 锁的操作(初始化锁)")
	@PostMapping("/initLockModel")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "adminUserId", value = "(蓝牙管理员用户)用户id", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "lockNumber", value = "锁蓝牙mak地址", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "lockName", value = "锁的名字", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "secretKey", value = "锁的秘钥", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "allow", value = "开锁标识(6位的字符串数字)", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "electricity", value = "锁电量", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "roomId", value = "房间id可以为空", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "adminPsw", value = "管理员密码", required = false, dataType = "String", paramType = "query") })
	public ReturnUtil initLockModel(String sysLockModel) throws Exception {

		ReturnUtil returnUtil = new ReturnUtil();

		try {

			SysLockModel parseObject = JSON.parseObject(sysLockModel, SysLockModel.class);

			String id = sysLockService.initLockModel(parseObject);
			returnUtil.setData(id);
			returnUtil.setCode(1001);

		} catch (Exception e) {
			e.printStackTrace();
			returnUtil.setCode(1002);
		}

		return returnUtil;

	}
	
	
	@ApiOperation(value = "绑定操作", notes = "安装人员与锁房间绑定")
	@PostMapping("/binding")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "installId", value = "安装人员id", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "roomId", value = "房间id", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "id", value = "锁id", required = false, dataType = "String", paramType = "query") })
	public ReturnUtil installLockBinding(String sysLockModel) throws Exception {

		ReturnUtil returnUtil = new ReturnUtil();

		try {
			sysLockService.installLockBinding(JSON.parseObject(sysLockModel, SysLockModel.class));
			returnUtil.setCode(1001);

		} catch (Exception e) {
			e.printStackTrace();
			returnUtil.setCode(1002);
		}

		return returnUtil;

	}

}
