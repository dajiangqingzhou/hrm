package org.deepsl.hrm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.deepsl.hrm.dao.NoticeDao;
import org.deepsl.hrm.domain.Notice;
import org.deepsl.hrm.service.NoticeService;
import org.deepsl.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NoticeServiceImpl implements NoticeService{

	@Autowired
	NoticeDao dao;
	
	@Transactional
	@Override
	public void addNotice(Notice notice) {
		// TODO Auto-generated method stub
		dao.save(notice);
		
	}

	@Override
	public PageModel getPageModel(String num) {
		// TODO Auto-generated method stub
		HashMap<String, Object> params = new HashMap<String,Object>();
		Integer recordCount = dao.count(params);
		
		int pageIndex =Integer.parseInt(num);
		PageModel pageModel = new PageModel();
		pageModel.setRecordCount(recordCount);
		pageModel.setPageIndex(pageIndex);
		
		return pageModel;
		
	}

	@Override
	public List<Notice> getNotices(PageModel pageModel) {
		// TODO Auto-generated method stub
		int limit = pageModel.getPageSize();
		int offset = pageModel.getFirstLimitParam();
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("limit", limit);
		params.put("offset", offset);
		List<Notice> notices = dao.selectByPage(params);
		return notices;
	}

	@Override
	public Notice getNotice(Integer id) {
		// TODO Auto-generated method stub
		return dao.selectById(id);
	}
	
	@Transactional
	@Override
	public void updateNotice(Notice notice) {
		// TODO Auto-generated method stub
		dao.update(notice);
	}

	@Override
	public PageModel getPageModel(String num, String title, String content) {
		// TODO Auto-generated method stub
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("title", "%"+title+"%");
		params.put("content", "%"+content+"%");
		Integer recordCount = dao.count(params);
		System.out.println("NoticeServiceImpl.getPageModel()"+recordCount);
		int pageIndex =Integer.parseInt(num);
		PageModel pageModel = new PageModel();
		pageModel.setRecordCount(recordCount);
		pageModel.setPageIndex(pageIndex);
		return pageModel;
	}
	
	@Override
	public List<Notice> getNotices(PageModel pageModel, String title,
			String content) {
		// TODO Auto-generated method stub
		int limit = pageModel.getPageSize();
		int offset = pageModel.getFirstLimitParam();
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("title", "%"+title+"%");
		params.put("content","%"+content+"%");
		params.put("limit", limit);
		params.put("offset", offset);
		List<Notice> notices = dao.selectByPage(params);
		return notices;
	}

	@Transactional
	@Override
	public void removeNotice(Integer[] ids) {
		// TODO Auto-generated method stub
		for (Integer id : ids) {
			dao.deleteById(id);
		}
	}

	

	


	
	
}
