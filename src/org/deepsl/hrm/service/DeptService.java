package org.deepsl.hrm.service;

import java.util.List;
import java.util.Map;

import org.deepsl.hrm.domain.Dept;
import org.deepsl.hrm.util.tag.PageModel;

public interface DeptService {


	// 动态查询
	//List<Dept> selectByPage(Map<String, Object> params);
	
	//Integer count(Map<String, Object> params);
	
	List<Dept> selectAllDept();
	
	Dept selectById(int id);

	// 根据id删除部门
	void deleteById(Integer id);
	
	// 动态插入部门
	void save(Dept dept);
	
	// 动态修改
	void update(Dept dept);
    //批量删除
	void removeDeptByIds(String ids);
    //多条件查询
	List<Dept> selectByKey();

	List<Dept> selectByPage(Dept dept, PageModel pageModel);
}
