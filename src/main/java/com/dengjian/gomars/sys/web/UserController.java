package com.dengjian.gomars.sys.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dengjian.gomars.sys.bean.Staff;
import com.dengjian.gomars.sys.bean.StaffQueryDTO;
import com.dengjian.gomars.sys.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import com.dengjian.gomars.common.bean.BaseResponse;
import com.dengjian.gomars.common.bean.BaseResponseCode;
import com.dengjian.gomars.common.bean.PageResponseData;
import com.dengjian.gomars.common.exception.BusinessException;
import com.dengjian.gomars.common.util.RequestUtil;
import com.dengjian.gomars.common.util.WebUtils;

/**
 * 用户管理
 *
 */
@Controller
@RequestMapping(value="/api/app/user")
@Api(value="/api/app/user")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Resource
	private UserService userService;
	
	@RequestMapping(value="/page", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@ApiOperation(value = "分页查询用户信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name="page", value = "页码", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name="pageSize", value = "每页的数据量", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name="staffCode", value = "用户编码", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name="staffName", value = "用户姓名", required = false, paramType = "query", dataType = "String")
	})
	@ApiResponses({
		@ApiResponse(response=BaseResponse.class, code = 200, message = "成功") })
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
			params.put("start", (pageNo - 1) * rows);
			params.put("pageSize", rows);
			
			List<StaffQueryDTO> records = userService.selectPage(params);
			int total = userService.selectPageTotal(params);
			return BaseResponse.success().data(new PageResponseData<StaffQueryDTO>(total, records));
		} catch(Exception e) {
			logger.error("分页查询用户信息异常", e);
			return BaseResponse.error();
		}
	}
	
	@RequestMapping(value="/{staffId}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@ApiOperation(value = "查询指定用户信息")
	@ApiImplicitParam(name="staffId", value = "用户id", required = true, paramType = "path", dataType = "String")
	@ApiResponse(response=BaseResponse.class, code = 200, message = "成功")
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
	
	
	@RequestMapping(value="", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@ApiOperation(value = "新增用户")
	@ApiResponse(response=BaseResponse.class, code = 200, message = "成功")
	public BaseResponse add(@ApiParam @RequestBody Staff staff, HttpServletRequest request) {
		try {
			userService.addUser(staff, WebUtils.getCurrLoginUser(request).getStaffId());
			return BaseResponse.success();
		} catch (BusinessException e) {
			logger.error(e.getMsg(), e);
			return BaseResponse.error(e.getCode(), e.getMessage());
		} catch(Throwable e) {
			logger.error(e.getMessage(), e);
			return BaseResponse.error(BaseResponseCode.ERROR_51001);
		}
	}

	@RequestMapping(value="/{staffId}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@ApiOperation(value = "删除指定用户信息")
	@ApiImplicitParam(name="staffId", value = "用户id", required = true, paramType = "path", dataType = "String")
	@ApiResponse(response=BaseResponse.class, code = 200, message = "成功")
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
	@RequestMapping(value="", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@ApiOperation(value = "更新用户信息")
	@ApiResponse(response=BaseResponse.class, code = 200, message = "成功")
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
