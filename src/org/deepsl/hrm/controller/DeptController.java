package org.deepsl.hrm.controller;

import java.util.List;

//import javax.mail.Flags.Flag;
import javax.servlet.http.HttpServletRequest;

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

//	@Autowired
////	DeptService service;
//
//	// 查找所有部门
//	@RequestMapping("selectAllDept")
//	public String selectAllDept(Model model) {
//		// 不分页的情况
//		List<Dept> selectAllDept = service.selectAllDept();
//		System.out.println("DeptController.selectAllDept()" + selectAllDept);
//		if (selectAllDept != null) {
//			model.addAttribute("depts", selectAllDept);
//
//			return "/dept/dept";
//		} else {
//			return "/dept/deptError";
//		}
//
//		// 分页查找
//		/*PageModel pageModel = new PageModel();*/
//	/*	
//		service.count();
//		pageModel.setPageIndex(pageIndex);
//		pageModel.setRecordCount(recordCount);
//		 service.selectByPage(params);
//		
//		 if (selectAllDept != null) {
//			 model.addAttribute("pageModel",pageModel);
//
//				return "/dept/dept";
//			} else {
//				return "/dept/deptError";
//			}
//*/
//	}
//	//按条件查询
//	@RequestMapping("selectDeptByKey")
//	public String selectDeptByKey(HttpServletRequest request,Model model) {
//		String key = request.getParameter("keyName");
//		
//		List<Dept>  selectByKey = service.selectByKey();
//		if (selectByKey != null) {
//			model.addAttribute("depts", selectByKey);
//			
//			return "/dept/dept";
//		} else {
//			return "/dept/deptError";
//		}
//		
//	}
//	
//   
//	// 添加部门
//	@RequestMapping("addDept")
//	public String addDept(HttpServletRequest request, Dept dept, Model model) {
//
//		System.out.println("DeptController.addDept()" + dept);
//		String flag = request.getParameter("flag");
//		int flag1 = Integer.parseInt(flag);
//		if (flag1 == 1) {
//			return "/dept/showAddDept";
//		} else if (dept != null && flag1 == 2) {
//			service.save(dept);
//			return "redirect:/dept/selectAllDept";
//		} else {
//			return "/dept/deptError";
//		}
//
//	}
//
//	// 删除部门
//
//	@RequestMapping("deleteDeptById")
//	public String deleteDeptById(int id) {
//		try {
//			service.deleteById(id);
//
//		} catch (Exception e) {
//			return "/dept/deptError";
//		}
//		return "/dept/dept";
//
//	}
//
//	// 批量删除
//	// "${ctx }/dept/removeDept?ids=" + ids.get();
//	@RequestMapping("removeDept")
//	public String removeDeptByIds(HttpServletRequest request) {
//		String ids = request.getParameter("ids");
//		if (ids == null || ids == "") {
//			// 没有选中返回列表
//			return "redirect:/dept/selectAllDept";
//		}
//		try {
//
//			service.removeDeptByIds(ids);
//
//		} catch (Exception e) {
//			return "/dept/deptError";
//		}
//		return "redirect:/dept/selectAllDept";
//
//	}
//
//	// 修改部门操作
//	@RequestMapping("updateDept")
//	public String updateDept(HttpServletRequest request, Dept dept, Model model) {
//
//		String flag = request.getParameter("flag");
//		int flag1 = Integer.parseInt(flag);
//		if (flag1 == 1) {
//			
//			String id = request.getParameter("id");
//			int id1 = Integer.parseInt(id);
//			
//			Dept selectById = service.selectById(id1);
//			model.addAttribute("dept", selectById);
//			
//			return "/dept/showUpdateDept";
//		} else if (dept != null && flag1 == 2) {
//			service.update(dept);
//			return "redirect:/dept/selectAllDept";
//		} else {
//			return "/dept/deptError";
//		}
//	}

}
