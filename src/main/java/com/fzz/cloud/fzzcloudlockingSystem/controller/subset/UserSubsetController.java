package com.fzz.cloud.fzzcloudlockingSystem.controller.subset;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.fzz.cloud.fzzcloudlockingSystem.entity.subset.UserSubset;
import com.fzz.cloud.fzzcloudlockingSystem.entity.subset.UserSubsetModel;
import com.fzz.cloud.fzzcloudlockingSystem.service.subset.UserSubsetService;
import com.fzz.cloud.fzzcloudlockingSystem.util.ReturnPaging;
import com.fzz.cloud.fzzcloudlockingSystem.util.ReturnUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
/**
 *   ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 *    ┃　　　┃   
 *    ┃　　　┃   
 *    ┃　　　┗━━━┓
 *    ┃　　　　　　　┣┓
 *    ┃　　　　　　　┏┛
 *    ┗┓┓┏━┳┓┏┛
 *      ┃┫┫　┃┫┫
 *      ┗┻┛　┗┻┛
 *        神兽保佑 
 *        代码无BUG! 
 */
@Api(value = "发送钥匙接口文档", description = "发送钥匙接口文档", position = 100, protocols = "http")
@RestController
@RequestMapping("/subset")
public class UserSubsetController {

	@Autowired
	private UserSubsetService userSubsetService;
	
	
	@ApiOperation(value = "删除钥匙", notes = "删除钥匙")
	@GetMapping("/delete")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "ids", value = "删除钥匙的id集合", required = false, dataType = "String", paramType = "query")})
	public ReturnUtil deleteUserSubset(@RequestParam("ids") List<String> ids) throws Exception {

		ReturnUtil returnUtil = new ReturnUtil();

		try {
			userSubsetService.deleteUserSubset(ids);
			returnUtil.setCode(1001);

		} catch (Exception e) {
			e.printStackTrace();
			returnUtil.setCode(1002);
		}

		return returnUtil;
	}
	
	

	@ApiOperation(value = "添加钥匙", notes = "添加钥匙1010:父节点已删除,1012:创建的层级关系已存在")
	@PostMapping("/insert")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "lockId", value = "锁id", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "userId", value = "用户id", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "keyName", value = "钥匙名称", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "startTime", value = "开始时间2018-12-21-18-00", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "endTime", value = "结束时间2018-12-21-18-00", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "parentId", value = "父级id", required = false, dataType = "String", paramType = "query")})
	public ReturnUtil insertUserSubset(String userSubsetModel) throws Exception {

		ReturnUtil returnUtil = new ReturnUtil();

		try {
			System.out.println("添加钥匙的模型:" + userSubsetModel);
			UserSubset userSubset = JSON.parseObject(userSubsetModel, UserSubset.class);
			String insertUserSubset = userSubsetService.insertUserSubset(userSubset);
			if("1010".equals(insertUserSubset)) {
				returnUtil.setCode(1010);//父节点已删除
			} else if("1012".equals(insertUserSubset)) {
				returnUtil.setCode(1012);//创建的层级关系已存在
			} else {
				returnUtil.setCode(1001);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnUtil.setCode(1002);
		}

		return returnUtil;
	}
	
	

	

	@ApiOperation(value = "分页查询当前用户下所有子用户钥匙", notes = "分页查询当前用户下所有子用户钥匙")
	@GetMapping("/selectPage")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageSize", value = "每页显示的条数", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "currentPage", value = "当前页", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "lockId", value = "锁id", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "userId", value = "用户id", required = false, dataType = "String", paramType = "query") })
	public ReturnUtil selectUserSubsetPage(String userSubsetModel) throws Exception {

		ReturnUtil returnUtil = new ReturnUtil();

		try {

			UserSubsetModel model = JSON.parseObject(userSubsetModel, UserSubsetModel.class);
			ReturnPaging paging = userSubsetService.selectUserSubsetPage(model);
			returnUtil.setData(paging);
			returnUtil.setCode(1001);
		} catch (Exception e) {
			e.printStackTrace();
			returnUtil.setCode(1002);
		}

		return returnUtil;
	}
	
	
	@ApiOperation(value = "查询钥匙详情接口", notes = "查询钥匙详情接口")
	@GetMapping("/selectId")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "钥匙id", required = false, dataType = "String", paramType = "query") })
	public ReturnUtil selectUserSubsetInfo(String id) throws Exception {

		ReturnUtil returnUtil = new ReturnUtil();

		try {

			UserSubset userSubset = userSubsetService.selectUserSubsetInfo(id);
			returnUtil.setData(userSubset);
			returnUtil.setCode(1001);
		} catch (Exception e) {
			e.printStackTrace();
			returnUtil.setCode(1002);
		}

		return returnUtil;
	}
	
	@ApiOperation(value = "查询钥匙详情接口", notes = "查询钥匙详情接口")
	@PostMapping("/update")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "钥匙id", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "keyName", value = "钥匙名称", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "startTime", value = "开始时间2018-12-21-18-00", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "endTime", value = "结束时间2018-12-21-18-00", required = false, dataType = "String", paramType = "query")})
	public ReturnUtil updateUserSubset(String userSubsetModel) throws Exception {

		ReturnUtil returnUtil = new ReturnUtil();

		try {

			UserSubset userSubset = JSON.parseObject(userSubsetModel, UserSubset.class);
			userSubsetService.updateUserSubset(userSubset);
			returnUtil.setCode(1001);
		} catch (Exception e) {
			e.printStackTrace();
			returnUtil.setCode(1002);
		}

		return returnUtil;
	}
}
