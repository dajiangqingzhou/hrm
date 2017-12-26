package org.deepsl.hrm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.deepsl.hrm.dao.DeptDao;
import org.deepsl.hrm.domain.Dept;
import org.deepsl.hrm.service.DeptService;
import org.deepsl.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class DeptServiceImpl implements DeptService{

	@Autowired
	DeptDao dao;
	
/*	@Transactional
	@Override
	public List<Dept> selectByPage(Map<String, Object> params) {
        
		return null;
	}

	@Transactional
	@Override
	public Integer count(Map<String, Object> params) {
		// TODO Auto-generated method stub22333
		return null;
	}*/

	@Transactional
	@Override
	public List<Dept> selectAllDept() {
		// TODO Auto-generated method stub
		return dao.selectAllDept();
	}

	@Transactional
	@Override
	public Dept selectById(int id) {
		// TODO Auto-generated method stub
		return dao.selectById(id);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
		
	}

	@Transactional
	@Override
	public void save(Dept dept) {
		dao.save(dept);
	}

	@Transactional
	@Override
	public void update(Dept dept) {
		dao.update(dept);
	}
//批量删除
	@Override
	public void removeDeptByIds(String ids) {
		// TODO Auto-generated method stub
		System.out.println("DeptServiceImpl.removeDeptByIds()"+ids);
		//分割字符串
		String sourceStr=ids;
		String[] sourceStrArray = sourceStr.split(",");
        for (int i = 0; i < sourceStrArray.length; i++) {
        	
            System.out.println(sourceStrArray[i]);
            
            int id = Integer.parseInt(sourceStrArray[i]);
            
            dao.deleteById(id);
        }
		
	}

@Override
public List<Dept> selectByKey() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public List<Dept> selectByPage(Dept dept, PageModel pageModel) {
	 Map<String,Object> map = new HashMap<>();
	 map.put("dept", dept);
	 map.put("pageModel", pageModel);
	 //查询数量
	 Integer count = dao.count(map);
	 if (count==0) {
		return null;
	}
	 //封装数据到pageModel
	 pageModel.setRecordCount(count);
	 return dao.selectByPage(map);
}
	


}
