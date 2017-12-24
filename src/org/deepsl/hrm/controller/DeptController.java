package org.deepsl.hrm.controller;

import java.util.List;

import org.deepsl.hrm.domain.Dept;

import org.deepsl.hrm.service.HrmService;
import org.deepsl.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**   
 * @Description: 处理部门请求控制器
 * @author   
 * @version V1.0   
 */
@RequestMapping("dept")
@Controller
public class DeptController {
	
/*	@Autowired
	DeptService service;
	
	@RequestMapping("findAllDept") 
	public String findAllDept(Model model){
		
		List<Dept> selectAllDept = service.selectAllDept();
		if (selectAllDept!=null) {
			model.addAttribute("depts", selectAllDept);
			
			return "/jsp/dept/dept.jsp";
		}else {
			return "/jsp/dept/deptError.jsp";
		}
						
	}
	
	@RequestMapping("deleteDeptById")
	public String deleteDeptById(int id){
		try{
		  service.deleteById(id);
						
		}catch(Exception e){
			return "/jsp/dept/deptError.jsp";
		}
		return "/jsp/dept/dept.jsp";

	}
 */
}
