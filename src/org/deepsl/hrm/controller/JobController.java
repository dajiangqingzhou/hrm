package org.deepsl.hrm.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.type.ObjectTypeHandler;
import org.deepsl.hrm.domain.Job;
import org.deepsl.hrm.service.HrmService;
import org.deepsl.hrm.service.OtherServiceInterface;
import org.deepsl.hrm.service.impl.OtherServiceImpl;
import org.deepsl.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

/**   
 * @Description: 处理职位请求控制器  
 * @version V1.0   
 */

@Controller
@RequestMapping("job")
public class JobController {

	@Autowired
	OtherServiceInterface otherService;

	@RequestMapping("selectJob")
	public String selectJob(Model model,Job job,PageModel pageModel){

		List<Job> findJob = otherService.findJob(job, pageModel);
		model.addAttribute("jobs", findJob);
		model.addAttribute("name", job.getName());
		return "job/job";
	}

	@RequestMapping("removeJob")
	public void removeJob(HttpServletRequest req, HttpServletResponse resp, int[] ids) throws ServletException, IOException, MySQLIntegrityConstraintViolationException{

		try{
			for (int id : ids) {
				otherService.removeJobById(id);
			}
		}
		catch(Exception e){
			req.setAttribute("error", "请先删除职位对应的员工");
			e.printStackTrace();
		}
		req.getRequestDispatcher("/job/selectJob").forward(req, resp);
	}

	@RequestMapping("updateJob")
	public String updateJob(String flag,Job job,Model model,HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{

		if(flag.equals("1")){
			Job findJobById = otherService.findJobById(job.getId());
			model.addAttribute("job", findJobById);
			return "job/showUpdateJob";
		}
		otherService.modifyJob(job);
		req.getRequestDispatcher("/job/selectJob").forward(req, resp);
		return null;
	}

	@RequestMapping("addJob")
	public String addJob(String flag,Model model,Job job,HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{

		if(flag.equals("1")){	
			return "job/showAddJob";
		}
		otherService.addJob(job);
		req.getRequestDispatcher("/job/selectJob").forward(req, resp);
		return null;
	}
}
