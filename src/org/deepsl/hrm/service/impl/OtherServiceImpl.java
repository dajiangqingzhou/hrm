package org.deepsl.hrm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.deepsl.hrm.dao.DocumentDao;
import org.deepsl.hrm.dao.JobDao;
import org.deepsl.hrm.domain.Dept;
import org.deepsl.hrm.domain.Document;
import org.deepsl.hrm.domain.Employee;
import org.deepsl.hrm.domain.Job;
import org.deepsl.hrm.domain.Notice;
import org.deepsl.hrm.service.OtherServiceInterface;
import org.deepsl.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
public class OtherServiceImpl implements OtherServiceInterface{

	@Autowired
	JobDao jobDao;
	
	@Autowired
	DocumentDao documentDao;
	
	@Override
	public List<Employee> findEmployee(Employee employee, PageModel pageModel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeEmployeeById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Employee findEmployeeById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyEmployee(Employee employee) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Dept> findDept(Dept dept, PageModel pageModel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Dept> findAllDept() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeDeptById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addDept(Dept dept) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Dept findDeptById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modifyDept(Dept dept) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Job> findAllJob() {
		// TODO Auto-generated method stub
		return jobDao.selectAllJob();
	}

	@Override
	public List<Job> findJob(Job job, PageModel pageModel) {
		// TODO Auto-generated method stub
		Map<String,Object> params = new HashMap<>();
		if(job.getName()!=null){
			params.put("name", "%"+job.getName()+"%");
		}else{
			params.put("name","%%");
		}
		Integer count = jobDao.count(params);
		
		pageModel.setRecordCount(count);
		int pageIndex = pageModel.getPageIndex();
		int pageSize = pageModel.getPageSize();
		
		params.put("offset",(pageIndex-1)*pageSize);
		params.put("limit",pageSize);
		List<Job> selectByPage = jobDao.selectByPage(params);
		return selectByPage;
	}

	@Override
	public void removeJobById(Integer id) {
		// TODO Auto-generated method stub
		jobDao.deleteById(id);
	}

	@Override
	public void addJob(Job job) {
		// TODO Auto-generated method stub
		jobDao.save(job);
	}

	@Override
	public Job findJobById(Integer id) {
		// TODO Auto-generated method stub
		return jobDao.selectById(id);
	}

	@Override
	public void modifyJob(Job job) {
		// TODO Auto-generated method stub
		jobDao.update(job);
	}

	@Override
	public List<Notice> findNotice(Notice notice, PageModel pageModel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Notice findNoticeById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeNoticeById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addNotice(Notice notice) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyNotice(Notice notice) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Document> findDocument(Document document, PageModel pageModel) {
		// TODO Auto-generated method stub
		Map<String,Object> params = new HashMap<>();
		if(document.getTitle() !=null){
			params.put("title", "%"+document.getTitle() +"%");
		}else{
			params.put("title","%%");
		}
		Integer count = documentDao.count(params);
		
		pageModel.setRecordCount(count);
		int pageIndex = pageModel.getPageIndex();
		int pageSize = pageModel.getPageSize();
		
		params.put("offset",(pageIndex-1)*pageSize);
		params.put("limit",pageSize);
		List<Document> selectByPage = documentDao.selectByPage(params);
		return selectByPage;
		
	}

	@Override
	public void addDocument(Document document) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Document findDocumentById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeDocumentById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyDocument(Document document) {
		// TODO Auto-generated method stub
		
	}

}
