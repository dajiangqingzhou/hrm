package org.deepsl.hrm.service.impl;

import org.deepsl.hrm.dao.DocumentDao;
import org.deepsl.hrm.domain.Document;
import org.deepsl.hrm.service.DocumentService;
import org.deepsl.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DocumentServiceImpl implements DocumentService {
    @Autowired
    DocumentDao documentDao;
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
