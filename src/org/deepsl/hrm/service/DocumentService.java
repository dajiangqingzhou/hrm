package org.deepsl.hrm.service;

import org.deepsl.hrm.domain.Document;
import org.deepsl.hrm.util.tag.PageModel;

import java.util.List;

public interface DocumentService {
    /**
     * 获得所有文档
     * @return Document对象的List集合
     * */
    List<Document> findDocument(Document document, PageModel pageModel);

    /**
     * 添加文档
     * @param Document 文件对象
     * */
    void addDocument(Document document);

    /**
     * 根据id查询文档
     * @param id
     * @return 文档对象
     * */
    Document findDocumentById(Integer id);

    /**
     * 根据id删除文档
     * @param id
     * */
    public void removeDocumentById(Integer id);

    /**
     * 修改文档
     * @param Document 公告对象
     * */
    void modifyDocument(Document document);

    void deleteDocumentByIds(List<Integer> idsList);
}
