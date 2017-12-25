package org.deepsl.hrm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.deepsl.hrm.domain.User;
import org.deepsl.hrm.service.HrmService;
import org.deepsl.hrm.service.UserService;
import org.deepsl.hrm.util.common.HrmConstants;
import org.deepsl.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * 处理用户请求控制器
 * */
@Controller
public class UserController {
	
	/**
	 * 自动注入UserService
	 * */
	@Autowired
	UserService userService;
		
	/**
	 * 处理登录请求
	 * @param String loginname  登录名
	 * @param String password 密码
	 * @return 跳转的视图
	 * */
	@RequestMapping(value="/login")
	 public ModelAndView login(@RequestParam("loginname") String loginname,
			 @RequestParam("password") String password,
			 HttpSession session,
			 ModelAndView mv){
		// 调用业务逻辑组件判断用户是否可以登录
		User user = userService.login(loginname, password);
		if(user != null){
			// 将用户保存到HttpSession当中
			session.setAttribute(HrmConstants.USER_SESSION, user);
			// 客户端跳转到main页面
			mv.setViewName("redirect:/main");
		}else{
			// 设置登录失败提示信息
			mv.addObject("message", "登录名或密码错误!请重新输入");
			// 服务器内部跳转到登录页面
			mv.setViewName("forward:/loginForm");
		}
		return mv;
		
	}

	/**
	 * 查询（条件与非条件，以及分页）
	 * @param modelAndView
	 * @param username
	 * @param status
	 * @param pageIndex
	 * @param request
	 * @return
	 */
 	@RequestMapping("/user/selectUser")
	public ModelAndView selectUser(ModelAndView modelAndView, String username,Integer status,
								   Integer pageIndex,HttpServletRequest request) {
 		PageModel pageModel = new PageModel();
		User user = new User();
		if (pageIndex == null) {
			pageIndex = 1;
			request.getSession().setAttribute("user",user);
		} else {
			user = (User) request.getSession().getAttribute("user");
		}
		pageModel.setPageIndex(pageIndex);

		if ((username != "" && username != null) || status != null) {
			String str = "%" + username + "%";
			user.setUsername(str);
			user.setStatus(status);
			request.getSession().setAttribute("user",user);
		}
		List<User> users = userService.findUser(user, pageModel);
		modelAndView.addObject("users",users);
		modelAndView.addObject("pageModel",pageModel);
		modelAndView.setViewName("/user/user");
		return modelAndView;
	}
	
	/**
	 * 处理删除用户请求
	 * @param String ids 需要删除的id字符串
	 * @param ModelAndView mv
	 * */
	@RequestMapping("/user/removeUser")
 	public ModelAndView removeUser(String ids,int pageIndex, ModelAndView mv) {
		String[] split = ids.split(",");
		List<Integer> idsList = new ArrayList<>();
		for (int i = 0; i < split.length; i++){
			idsList.add(Integer.parseInt(split[i]));
		}
		userService.removeUserByIds(idsList);
		mv.setViewName("redirect:/user/selectUser?pageIndex=" + pageIndex);
 		return mv;
	}


	@RequestMapping("/user/updateUser")
	public String toUpdateUser(Integer id, User user, Model model,Integer flag) {
		if (flag == 1) {
			User userById = userService.findUserById(id);
			model.addAttribute("user", userById);
			return "/user/showUpdateUser";
		} else {
            userService.modifyUser(user);
            return "redirect:/user/selectUser";
        }
	}


	@RequestMapping("/user/addUser")
	public String addUser(User user,int flag) {
		if (flag == 1) {
			return "/user/showAddUser";
		}
		userService.addUser(user);
		return "redirect:/user/selectUser";
	}
	
 	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "/loginForm";
	}
	 
	
}
