package org.deepsl.hrm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.ResultMap;
import org.deepsl.hrm.domain.Dept;
import org.deepsl.hrm.domain.Employee;
import org.deepsl.hrm.domain.EmployeeCondition;
import org.deepsl.hrm.domain.Job;
import org.deepsl.hrm.service.DeptService;
import org.deepsl.hrm.service.EmployeeService;
import org.deepsl.hrm.service.HrmService;
import org.deepsl.hrm.service.OtherServiceInterface;
import org.deepsl.hrm.service.impl.OtherServiceImpl;
import org.deepsl.hrm.util.common.HrmConstants;
import org.deepsl.hrm.util.tag.PageModel;
import org.deepsl.hrm.util.tag.PagerTag;
import org.eclipse.equinox.log.ExtendedLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @version V1.0
 * @Description: 处理员工请求控制器
 */

@Controller
@RequestMapping("employee")
public class EmployeeController {

    public EmployeeController() {
        System.out.println("----EmployeeController");
    }

    @Autowired
    EmployeeService employeeService;

    @Autowired
    OtherServiceInterface otherService;

    @Autowired
    DeptService deptService;

    @RequestMapping("addEmployee")
    public String addEmployee(String flag, Employee employee) {
        if (flag != null) {
            //flag=1只是跳转到添加员工页面
            if ("1".equals(flag)) {
//                需要到数据库获取部门depts数据，及职位jobs

                return "employee/showAddEmployee";
            }
            //flag=2表示添加员工
            if ("2".equals(flag) && employee != null) {
                System.out.println("----" + employee);
                employeeService.addEmployee(employee);
                return "employee/employee";
            }
        }
        return null;
    }

    @RequestMapping("selectEmployee")
    public String selectEmployee(HttpServletRequest request,
                                 PageModel pageModel, EmployeeCondition condition, Model model) {

        List<Job> jobs = otherService.findAllJob();
        model.addAttribute("jobs", jobs);
        List<Dept> depts = deptService.selectAllDept();
        model.addAttribute("depts", depts);
//如果不带页码 表示点击搜索按钮来查的

        String method = request.getMethod();
        if ("POST".equals(method)) {
            Map<String, Object> conditionMap = bean2Map(condition);
            conditionMap.put("limit", HrmConstants.PAGE_DEFAULT_SIZE);
            int recordCound = employeeService.countEmployeesByCondition(conditionMap);
            pageModel.setRecordCount(recordCound);
            conditionMap.put("recordCound", recordCound);
            List<Employee> employees = employeeService.findEmployeesByCondition(conditionMap);
            request.getSession().setAttribute("conditionMap", conditionMap);
            model.addAttribute("employees", employees);
            return "employee/employee";
        } else {//get方式提交

            Map<String, Object> conditionMap = (Map<String, Object>) request.getSession().getAttribute("conditionMap");
            if (conditionMap != null) {
//                查出符合条件的
                int recordCound = (int) conditionMap.get("recordCound");
                pageModel.setRecordCount(recordCound);
                int pageIndex = pageModel.getPageIndex();
                System.out.println("----pageIndex" + pageIndex);
                conditionMap.put("offset", (pageIndex - 1) * pageModel.getPageSize());
                List<Employee> employees = employeeService.findEmployeesByCondition(conditionMap);
                request.getSession().setAttribute("conditionMap", conditionMap);
                model.addAttribute("employees", employees);
            }
            return "employee/employee";
        }

    }

    @RequestMapping("updateEmployee")
    public String updateEmployee(String flag, int id, Model model, Employee employee) {
        if ("1".equals(flag)) {
            Employee employee1 = employeeService.findEmployeeById(id);
            List<Job> jobs = otherService.findAllJob();
            model.addAttribute("jobs", jobs);
            model.addAttribute("employee", employee1);
            return "/employee/showUpdateEmployee";
        } else if ("2".equals(flag)) {
            employeeService.updateEmployee(employee);

            return null;
        }
        return null;
    }

    private Map<String, Object> bean2Map(EmployeeCondition condition) {
        HashMap<String, Object> map = new HashMap<>();
        if (condition != null) {
            String cardId = condition.getCardId();
            if (cardId != null && !"".equals(cardId)) {
                map.put("cardId", cardId);
            }
            Integer dept_id = condition.getDept_id();
            if (dept_id != null && dept_id.intValue() != 0) {
                map.put("dept_id", dept_id);
            }
            Integer job_id = condition.getJob_id();
            if (job_id != null && job_id.intValue() != 0) {
                map.put("job_id", job_id);
            }
            String name = condition.getName();
            if (name != null && !"".equals(name)) {
                map.put("name", name);
            }
            String phone = condition.getPhone();
            if (phone != null && !"".equals(phone)) {
                map.put("phone", phone);
            }
            Integer sex = condition.getSex();
            if (sex != null && sex.intValue() != 0) {
                map.put("sex", sex);
            }
        }
        return map;
    }

}
