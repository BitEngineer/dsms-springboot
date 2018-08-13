package com.lonton.dsms.sys.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lonton.dsms.sys.bean.Role;
import com.lonton.dsms.sys.service.RoleService;
import com.lonton.dsms.common.bean.BaseResponse;
import com.lonton.dsms.common.exception.ServiceProcessException;
import com.lonton.dsms.common.util.WebUtils;

/**
 * 角色管理控制器
 * @author 邓键
 *
 */
@Controller
@RequestMapping(value="/app/role/")
public class RoleController {
	
	private static final Logger logger = LoggerFactory.getLogger(RoleController.class);
	
	@Resource
	private RoleService roleService;
	
	/**
	 * 新增角色
	 * {
	 *   roleName,
	 *   description
	 * }
	 * @param request
	 * @param role
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping(value="", method=RequestMethod.POST)
	@ResponseBody
	public BaseResponse add(HttpServletRequest request, Role role) {
		try {
			roleService.add(role, WebUtils.getCurrLoginUser(request));
			return BaseResponse.success();
		} catch (Exception e) {
			logger.error("新增角色出错", e);
			return BaseResponse.error();
		}
	}

//	@RequestMapping("/get")
//	@ResponseBody
//	public Map<String, Object> get(HttpServletRequest request) {
//		Map<String, Object> params = RequestUtil.getRequestParams(request);
//		int pageNo = Integer.parseInt(params.get("page") + "");
//		int rows = Integer.parseInt(params.get("rows") + "");
//		params.put("start", (pageNo - 1) * rows + 1);
////		params.put("end", pageNo * rows);
//		params.put("pageSize", rows);
//		Map<String, Object> returnMap = new HashMap<String, Object>();
//		returnMap.put("success", true);
//		returnMap.put("data", this.mapper.select(params));
//		return returnMap;
//	}
//	
//	@RequestMapping("/add")
//	@ResponseBody
//	public Map<String, Object> add(HttpServletRequest request) {
//		Map<String, Object> params = RequestUtil.getRequestParams(request);
//		
//		Map<String, Object> returnMap = new HashMap<String, Object>();
//		if(!checkConstraint(params, false)){
//			returnMap.put("success", false);
//			returnMap.put("errorMsg", "已存在的角色名称！");
//			return returnMap;
//		}
//		params.put("deleteFlag", "1");
//		params.put("updateUser", WebUtils.getCurrLoginUser(request).getStaffId());
//		
//		returnMap.put("success", true);
//		returnMap.put("data", this.mapper.insert(params));
//		return returnMap;
//	}
//	
//	@RequestMapping("/edit")
//	@ResponseBody
//	public Map<String, Object> edit(HttpServletRequest request) {
//		Map<String, Object> params = RequestUtil.getRequestParams(request);
//		Map<String, Object> returnMap = new HashMap<String, Object>();
//		
//		if(params.containsKey("roleName") && !checkConstraint(params, true)){
//			returnMap.put("success", false);
//			returnMap.put("errorMsg", "已存在的角色名称！");
//			return returnMap;
//		}
//		params.put("updateUser", WebUtils.getCurrLoginUser(request).getStaffId());
//		returnMap.put("success", true);
//		returnMap.put("data", this.mapper.update(params));
//		return returnMap;
//	}
//	
//	@RequestMapping("/del")
//	@ResponseBody
//	public Map<String, Object> del(HttpServletRequest request) {
//		Map<String, Object> params = RequestUtil.getRequestParams(request);
//		Map<String, Object> returnMap = new HashMap<String, Object>();
//		params.put("updateUser", WebUtils.getCurrLoginUser(request).getStaffId());
//		returnMap.put("success", true);
//		returnMap.put("data", this.mapper.delete(params));
//		return returnMap;
//	}
//	
//	/**
//	 * 检查唯一性约束
//	 * @param params
//	 * @param isUpdate
//	 * @return 
//	 * <ul>
//	 * 	<li>true，通过检查；</li>
//	 * 	<li>false，未通过检查。</li>
//	 * </ul>
//	 */
//	private boolean checkConstraint(Map<String, Object> params, boolean isUpdate){
//		Map<String, Object> checkParams = new HashMap<String, Object>();
//		checkParams.put("roleName", params.get("roleName"));
//		checkParams.put("roleNameEq", true);
//		checkParams.put("start", 1);
//		checkParams.put("end", 2);
//		List<Map<String, Object>> list = mapper.select(checkParams);
//		if(0 == list.size()) { return true;}
//		if(!isUpdate) { return false;}
//		
//		if(1 == list.size()){
//			return list.get(0).get("roleId").equals(params.get("roleId"));
//		}
//		return false;
//	}
}
