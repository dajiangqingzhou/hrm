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
	
	@RequestMapping("showAddNotice")
	public String showAddNotice(){
		System.out.println("NoticeController.showAddNotice()");
		return "notice/showAddNotice";
	}
	
	@RequestMapping("addNotice")
	public String addNotice(Notice notice,HttpSession session){
		
		long currentTime = System.currentTimeMillis();		
		Date createDate = new Date(currentTime);	
		notice.setCreateDate(createDate);
		/*User user = (User) session.getAttribute("user");*/
		User user = new User();
		user.setId(1);
		notice.setUser(user);

		service.addNotice(notice);
		return "notice/showAddNotice";

	}
	
	@RequestMapping("selectNotice")
	public ModelAndView selectNotice(HttpServletRequest request){
		
		ModelAndView mv = new ModelAndView();
		String num = request.getParameter("pageIndex");
		PageModel pageModel = service.getPageModel(num);
		
		List<Notice> notices = service.getNotices(pageModel);
		request.setAttribute("notices", notices);
		request.setAttribute("pageModel", pageModel);
		mv.addObject("request", request);
		mv.setViewName("notice/notice");
		return mv;
	}
	
}
