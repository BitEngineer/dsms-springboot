package com.lonton.dsms.base.web;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lonton.dsms.base.mapper.SecurityMapper;
import com.lonton.dsms.base.vo.Menu;
import com.lonton.dsms.base.vo.Res;
import com.lonton.dsms.base.vo.Role;
import com.lonton.dsms.base.vo.User;
import com.lonton.dsms.common.util.RequestUtil;
import com.lonton.dsms.common.util.WebUtils;

/**
 * 权限控制器<br/>
 * 用来获取用户菜单项等
 * @author 邓键
 */
@Controller
@RequestMapping(value="/base")
public class SecurityController {
	@Resource
	private SecurityMapper mapper;
	
	/**
	 * 登录验证和用户信息获取
	 */
	@RequestMapping("/login")
	public String checkLogin(HttpServletRequest request){
		Map<String,Object> params = RequestUtil.getRequestParams(request);
		
		//检查用户是否存在
		int n = mapper.checkLogin(params);
		if(n == 0){
			request.setAttribute("error", true);
			return "login";
		}else{
			User loginUser = mapper.getLoginUserInfo(params);
			WebUtils.setCurrLoginUserToSession(request, loginUser);
			return "index";
		}
	}
	
	/**
	 * 退出系统
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request){
		// session 销毁
		request.getSession().invalidate();
		// 返回jsp时，session再次创建，但是此时创建的session对象中已经不包含之前set进去的属性
		return "login";
	}
	
	/**
	 * 加载用户的菜单项
	 */
	@RequestMapping("/getMenu")
	@ResponseBody
	public Map<String,Object> loadMenu(HttpServletRequest request){
//		@SuppressWarnings("unchecked")
//		List<Role> roles = (List<Role>)SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		//如果session超时会报空指针,所以要用filter做登录超时验证
		User loginUser = (User) request.getSession().getAttribute("loginUser");
		//获取用户角色
		List<Role> roles = loginUser.getRoles();
		
		List<Res> resList = new LinkedList<Res>();
		Menu menus = null;
		if(roles.size() != 0){
			//获取角色对应的资源
			resList = mapper.loadResByUserRole(roles);
			if(resList.size() != 0){
				//将资源整理为菜单
				menus = this.formatMenu(resList);
			}
		}
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("success", true);
		returnMap.put("data", menus);
		return returnMap;
	}
	
	/**
	 * 将菜单类型的资源列表整理为菜单树的结构
	 * @param resList
	 * @return
	 */
	private Menu formatMenu(List<Res> resList){
		List<Menu> menus = new LinkedList<Menu>();
		
		for(Res res : resList){
			Menu menu = new Menu(res);
			menus.add(menu);
		}
		
		Menu rootMenu = null;
		for(Menu subMenu : menus){
			if(subMenu.getParentId() == null){
				rootMenu = subMenu;
			}
			for(Menu menu : menus){
				if(subMenu.getParentId() != null &&
						subMenu.getParentId().equals(menu.getResId())){
					menu.addChild(subMenu);
				}
			}
		}
		
		return rootMenu;
	}

}
