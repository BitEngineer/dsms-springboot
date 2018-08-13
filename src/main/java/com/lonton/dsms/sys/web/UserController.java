package com.lonton.dsms.sys.web;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.lonton.dsms.sys.constants.Constants;
import com.lonton.dsms.sys.mapper.OrgMapper;
import com.lonton.dsms.sys.mapper.CustomRoleMapper;
import com.lonton.dsms.sys.mapper.UserMapper;
import com.lonton.dsms.sys.service.UserService;
import com.lonton.dsms.cache.UserCacheDao;
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
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Resource
	private UserMapper userMapper;
	@Resource(name="orgMapper")
	private OrgMapper orgMapper;
	@Resource
	private CustomRoleMapper customRoleMapper;
	
	@Resource
	private UserService userService;
	@Resource
	private UserCacheDao userCacheDao;
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	@ResponseBody
	public BaseResponse page(
			@RequestParam(name="page") String page,
			@RequestParam(name="pageSize") String pageSize,
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
	public BaseResponse add(Staff staff, HttpServletRequest request) {
		try {
			userService.addUser(staff, WebUtils.getCurrLoginUser(request).getStaffId());
			return BaseResponse.success();
		} catch (ServiceProcessException e) {
			logger.error("新增用户出错", e);
			return BaseResponse.error();
		}
	}

	/**
	 * 用户删除
	 * <p>
	 * {
	 *   staffId
	 * }
	 * </p>
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/{staffId}", method=RequestMethod.DELETE)
	@ResponseBody
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
	 * 用户数据更新
	 * <p>
	 * {
	 *  staffId, staffName, loginName, password, orgId, 
	 *  userType, idCardNo, birthday, sex, phone, 
	 *  email, address
	 * }
	 * </p>
	 * @param request
	 * @param staff
	 * @return
	 */
	@RequestMapping(value="", method=RequestMethod.PUT)
	@ResponseBody
	public BaseResponse edit(HttpServletRequest request, @RequestParam Staff staff) {
		try {
			userService.updateUser(staff, WebUtils.getCurrLoginUser(request).getStaffId());
			return BaseResponse.success();
		} catch (Exception e) {
			logger.error("用户更新出错", e);
			return BaseResponse.error();
		}
	}
	
	
	
	@RequestMapping("/findOrg")
	@ResponseBody
	public Map<String,Object> findOrg(HttpServletRequest request){
		Map<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("data", this.orgMapper.selectTree());
		returnMap.put("success", true);
		return returnMap;
	}
	
	@RequestMapping("/findOrgByName")
	@ResponseBody
	public Map<String,Object> findOrgByName(HttpServletRequest request){
		Map<String, Object> params = RequestUtil.getRequestParams(request);
		Map<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("data", this.orgMapper.selectOrgByName(params));
		returnMap.put("success", true);
		return returnMap;
	}
	
	@RequestMapping("/searchTree")
	@ResponseBody
	public Map<String,Object> searchTree(HttpServletRequest request){
		Map<String, Object> params = RequestUtil.getRequestParams(request);
		List<Map<String,Object>> searchOrgs = this.orgMapper.selectOrgByName(params);
		List<Map<String,Object>> allOrgs = this.orgMapper.selectTree();
		
		List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();
		data.addAll(searchOrgs);
		
		for(Map<String,Object> m : searchOrgs){
			Map<String,Object> currentOrg = m;
			while(true){
				if(currentOrg.get("parentId").equals(Constants.ORG_ROOT_PARENT_ID)){
					break;
				}
				for(Map<String,Object> mm : allOrgs){
					if(mm.get("orgId").equals(currentOrg.get("parentId"))){
						currentOrg = mm;
						if(!data.contains(mm)){
							data.add(mm);
						}
					}
				}
			}
		}
		
		Map<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("data", data);
		returnMap.put("success", true);
		return returnMap;
	}
	
	@RequestMapping("/findOrgByParent")
	@ResponseBody
	public Map<String,Object> findOrgByParentId(HttpServletRequest request){
		Map<String,Object> params = RequestUtil.getRequestParams(request);
		
		Map<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("data", this.orgMapper.selectTreeByParentId(params));
		returnMap.put("success", true);
		return returnMap;
	}
	
	@RequestMapping("/findUsedRoles")
	@ResponseBody
	public Map<String, Object> findUsed(HttpServletRequest request) {
		Map<String, Object> params = RequestUtil.getRequestParams(request);
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("success", true);
		returnMap.put("data", this.customRoleMapper.selectUsed(params));
		return returnMap;
	}
	
	@RequestMapping("/findUnusedRoles")
	@ResponseBody
	public Map<String, Object> findUnsed(HttpServletRequest request) {
		Map<String, Object> params = RequestUtil.getRequestParams(request);
		int pageNo = Integer.parseInt(params.get("page") + "");
		int rows = Integer.parseInt(params.get("rows") + "");
		params.put("start", (pageNo - 1) * rows + 1);
		params.put("end", pageNo * rows);
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("success", true);
		returnMap.put("data", this.customRoleMapper.selectUnusedByPage(params));
		return returnMap;
	}
	
	@RequestMapping("/searchRoles")
	@ResponseBody
	public Map<String, Object> searchRoles(HttpServletRequest request) {
		Map<String, Object> params = RequestUtil.getRequestParams(request);
		int pageNo = Integer.parseInt(params.get("page") + "");
		int rows = Integer.parseInt(params.get("rows") + "");
		params.put("start", (pageNo - 1) * rows + 1);
		params.put("end", pageNo * rows);
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("success", true);
		returnMap.put("data", this.customRoleMapper.selectByNamePage(params));
		return returnMap;
	}
	
	@RequestMapping("/manageRoles")
	@ResponseBody
	public Map<String, Object> manageRoles(HttpServletRequest request) {
		Map<String, Object> params = RequestUtil.getRequestParams(request);
		String staffId = (String) params.get("staffId");
		String addRoles = (String) params.get("addRoles");
		String delRoles = (String) params.get("delRoles");
		
		if(!"".equals(addRoles)){
			String[] addRoleArr = addRoles.split(",");
			
			//获取当前登录用户
			Staff staff = (Staff) WebUtils.getCurrLoginUser(request);
			String updateUser = staff.getLoginName();
			
			List<Map<String,Object>> addParams = new ArrayList<Map<String,Object>>();
			for(String s : addRoleArr){
				Map<String,Object> m = new HashMap<String,Object>();
				m.put("staffId", staffId);
				m.put("roleId", s);
				m.put("updateUser", updateUser);
				addParams.add(m);
			}
			
			this.customRoleMapper.insertIntoStaff(addParams);
		}
		
		if(!"".equals(delRoles)){
			String[] delRoleArr = delRoles.split(",");
			
			List<Map<String,Object>> delParams = new ArrayList<Map<String,Object>>();
			for(String s : delRoleArr){
				Map<String,Object> m = new HashMap<String,Object>();
				m.put("staffId", staffId);
				m.put("roleId", s);
				delParams.add(m);
			}
			
			this.customRoleMapper.deleteFromStaff(delParams);
		}
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("success", true);
		return returnMap;
	}
	
	@RequestMapping("/findRights")
	@ResponseBody
	public Map<String, Object> findRights(HttpServletRequest request) {
		Map<String, Object> params = RequestUtil.getRequestParams(request);
		int pageNo = Integer.parseInt(params.get("page") + "");
		int rows = Integer.parseInt(params.get("rows") + "");
		params.put("start", (pageNo - 1) * rows + 1);
		params.put("end", pageNo * rows);
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("success", true);
		returnMap.put("data", this.customRoleMapper.selectRightsByPage(params));
		return returnMap;
	}
	
	
	@RequestMapping("/check")
	@ResponseBody
	public Map<String, Object> checkUniqueness(HttpServletRequest request) throws Exception {
		Map<String, Object> params = RequestUtil.getRequestParams(request);
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		if(params.get("staffCode") != null){
			returnMap.put("success", true);
			returnMap.put("data", (this.userMapper.checkStaffCode(params)).size());
		}else if(params.get("loginName") != null){
			returnMap.put("success", true);
			returnMap.put("data", (this.userMapper.checkLoginName(params)).size());
		}else if(params.get("password") != null){
			Staff staff = (Staff) WebUtils.getCurrLoginUser(request);
			params.put("staffId", staff.getStaffId());
			returnMap.put("success", true);
			returnMap.put("data", (this.userMapper.checkPassword(params)).size());
		}
		return returnMap;
	}
	
	@RequestMapping("/findCodes")
	@ResponseBody
	public Map<String, Object> findCodes(HttpServletRequest request) throws Exception {
		Map<String, Object> params = RequestUtil.getRequestParams(request);
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("success", true);
		returnMap.put("data", this.userMapper.selectCodesByType(params));
		return returnMap;
	}
}
