package com.lonton.dsms.sys.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lonton.dsms.sys.bean.Staff;
import com.lonton.dsms.sys.bean.StaffQueryDTO;
import com.lonton.dsms.sys.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import com.lonton.dsms.common.bean.BaseResponse;
import com.lonton.dsms.common.bean.PageResponseData;
import com.lonton.dsms.common.exception.ServiceProcessException;
import com.lonton.dsms.common.util.RequestUtil;
import com.lonton.dsms.common.util.WebUtils;

/**
 * 用户管理控制器
 * @author 邓键
 *
 */
@Controller
@RequestMapping(value="/app/user")
@Api(value="/app/user")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Resource
	private UserService userService;
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "分页查询用户信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name="page", value = "页码", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name="pageSize", value = "每页的数据量", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name="page", value = "用户编码", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name="page", value = "用户姓名", required = false, paramType = "query", dataType = "String")
	})
	public BaseResponse page(
			@RequestParam(name="page", required=true) String page,
			@RequestParam(name="pageSize", required=true) String pageSize,
			@RequestParam(name="staffCode", required=false) String staffCode,
			@RequestParam(name="staffName", required=false) String staffName,
			HttpServletRequest request) throws Exception {
		try {
			Map<String, Object> params = RequestUtil.getRequestParams(request);
			int pageNo = Integer.parseInt(((String)params.get("page")));
			int rows = Integer.parseInt((String)params.get("pageSize"));
			params.put("start", (pageNo - 1) * rows + 1);
			params.put("pageSize", rows);
			
			List<StaffQueryDTO> records = userService.selectPage(params);
			int total = userService.selectPageTotal(params);
			return BaseResponse.success().data(new PageResponseData<StaffQueryDTO>(total, records));
		} catch(Exception e) {
			logger.error("分页查询用户信息异常", e);
			return BaseResponse.error();
		}
	}
	
	@RequestMapping(value="/{staffId}", method=RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "查询指定用户信息")
	@ApiImplicitParam(name="staffId", value = "用户id", required = true, paramType = "path", dataType = "String")
	public BaseResponse query(HttpServletRequest request, @PathVariable(name="staffId") String staffId) {
		try {
			Staff resultData = userService.selectObject(staffId);
			BaseResponse result = BaseResponse.success().data(resultData);
			return result;
		} catch (Exception e) {
			logger.error("查询用户出错", e);
			return BaseResponse.error();
		}
	}
	
	
	@RequestMapping(value="", method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "新增用户")
	public BaseResponse add(@ApiParam @RequestBody Staff staff, HttpServletRequest request) {
		try {
			userService.addUser(staff, WebUtils.getCurrLoginUser(request).getStaffId());
			return BaseResponse.success();
		} catch (ServiceProcessException e) {
			logger.error("新增用户出错", e);
			return BaseResponse.error();
		}
	}

	@RequestMapping(value="/{staffId}", method=RequestMethod.DELETE)
	@ResponseBody
	@ApiOperation(value = "查询指定用户信息")
	@ApiImplicitParam(name="staffId", value = "用户id", required = true, paramType = "path", dataType = "String")
	public BaseResponse del(HttpServletRequest request, @PathVariable String staffId) {
		try {
			userService.delUser(staffId, WebUtils.getCurrLoginUser(request).getStaffId());
			return BaseResponse.success();
		} catch (Exception e) {
			logger.error("用户删除出错", e);
			return BaseResponse.error();
		}
	}
	
	/**
	 * 用户信息更新
	 * @param request
	 * @param staff
	 * @return
	 */
	@RequestMapping(value="", method=RequestMethod.PUT)
	@ResponseBody
	@ApiOperation(value = "更新用户信息")
	public BaseResponse edit(HttpServletRequest request, @ApiParam @RequestBody Staff staff) {
		try {
			userService.updateUser(staff, WebUtils.getCurrLoginUser(request).getStaffId());
			return BaseResponse.success();
		} catch (Exception e) {
			logger.error("用户更新出错", e);
			return BaseResponse.error();
		}
	}
	
}
