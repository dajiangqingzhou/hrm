package org.deepsl.hrm.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.deepsl.hrm.domain.Notice;
import org.deepsl.hrm.domain.User;
import org.deepsl.hrm.service.HrmService;
import org.deepsl.hrm.service.NoticeService;
import org.deepsl.hrm.util.common.HrmConstants;
import org.deepsl.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**   
 * @Description: 处理公告请求控制器
 * @version V1.0   
 */

@RequestMapping("notice")
@Controller
public class NoticeController {

	
	
	@Autowired
	NoticeService service;
	
	
	@RequestMapping("addNotice")
	public ModelAndView addNotice(Notice notice,Integer flag,HttpSession session){
		
		ModelAndView mv = new ModelAndView();
		if (flag==1) {
			mv.setViewName("notice/showAddNotice");
		}else {
			long currentTime = System.currentTimeMillis();		
			Date createDate = new Date(currentTime);	
			notice.setCreateDate(createDate);
			User user = (User) session.getAttribute(HrmConstants.USER_SESSION);
			
			notice.setUser(user);
			service.addNotice(notice);
			mv.addObject("notice", notice);
			mv.setViewName("notice/previewNotice");
		}
		
		return mv;

	}
	
	@RequestMapping("selectNotice")
	public ModelAndView selectNotice(HttpServletRequest request){
		
		ModelAndView mv = new ModelAndView();
		String title = request.getParameter("title");
		String content = request.getParameter("content");	
		String num = request.getParameter("pageIndex");
		if (title==null&&content==null) {			
			PageModel pageModel = service.getPageModel(num);
			List<Notice> notices = service.getNotices(pageModel);
			request.setAttribute("notices", notices);
			request.setAttribute("pageModel", pageModel);
			mv.addObject("request", request);
			mv.setViewName("notice/notice");
		}else {
			PageModel pageModel = service.getPageModel(num,title,content);
			List<Notice> notices = service.getNotices(pageModel,title,content);
			request.setAttribute("notices", notices);
			request.setAttribute("pageModel", pageModel);
			mv.addObject("request", request);
			mv.setViewName("notice/notice");
		}
		
		return mv;
	}
	
	@RequestMapping("previewNotice")
	public ModelAndView previewNotice(Integer id){
		ModelAndView mv = new ModelAndView();
		Notice notice = service.getNotice(id);
		mv.addObject("notice", notice);
		mv.setViewName("notice/previewNotice");
		return mv;
	}
	
	@RequestMapping("updateNotice")
	public ModelAndView updateNotice(Integer flag, Integer id, Notice notice,
			HttpSession session) {
		
		ModelAndView mv = new ModelAndView();
		if (flag==1) {
			Notice notice2 = service.getNotice(id);
			mv.addObject("notice", notice2);
			mv.setViewName("notice/showUpdateNotice");
		}else if (flag==2) {
			long currentTime = System.currentTimeMillis();		
			Date createDate = new Date(currentTime);	
			notice.setCreateDate(createDate);
			User user = (User) session.getAttribute("user");
			notice.setUser(user);
			service.updateNotice(notice);
			mv.setViewName("redirect:/notice/selectNotice?pageIndex=1");
		}
		return mv;
	}
	
	@RequestMapping("removeNotice")
	public ModelAndView removeNotice(Integer[] ids){
		ModelAndView modelAndView = new ModelAndView();
		service.removeNotice(ids);
		modelAndView.setViewName("redirect:/notice/selectNotice?pageIndex=1");
		return modelAndView;
	}
}
