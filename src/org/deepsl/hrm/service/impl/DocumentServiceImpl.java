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
        Map<String,Object> map = new HashMap<>();
        map.put("document",document);
        map.put("pageModel",pageModel);
        Integer count = documentDao.count(map);
        if (count == 0) {
            return null;
        }
        pageModel.setRecordCount(count);
        List<Document> documents = documentDao.selectByPage(map);
        return documents;

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
        documentDao.update(document);

    }

    @Override
    public void deleteDocumentByIds(List<Integer> idsList) {
        Map<String,List<Integer>> map = new HashMap<>();
        map.put("idsList",idsList);
        documentDao.deleteDocumentByIds(map);
    }

}
