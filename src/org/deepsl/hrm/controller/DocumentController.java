package org.deepsl.hrm.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.deepsl.hrm.domain.Document;
import org.deepsl.hrm.domain.User;
import org.deepsl.hrm.service.DocumentService;
import org.deepsl.hrm.service.HrmService;
import org.deepsl.hrm.service.OtherServiceInterface;
import org.deepsl.hrm.service.impl.OtherServiceImpl;
import org.deepsl.hrm.util.common.HrmConstants;
import org.deepsl.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**   
 * @Description: 处理上传下载文件请求控制器
 * @version V1.0   
 */

@RequestMapping("document")
@Controller
public class DocumentController {

    @Autowired
    DocumentService documentService;

    @RequestMapping("selectDocument")
    public String selectDocument(Model model, String title, Integer pageIndex, HttpServletRequest request) {
        Document document = new Document();
        PageModel pageModel = new PageModel();
        if (pageIndex == null) {
            pageIndex = 1;
            request.getSession().setAttribute("document",document);
        } else {
            document = (Document) request.getSession().getAttribute("document");
        }
        pageModel.setPageIndex(pageIndex);
        if (title != null && !title.trim().isEmpty()){
            String str = "%" + title + "%";
            document.setTitle(str);
            request.getSession().setAttribute("document",document);
        }
        List<Document> documents = documentService.findDocument(document, pageModel);
        model.addAttribute("documents",documents);
        model.addAttribute("pageModel",pageModel);
        return "/document/document";
    }

    @RequestMapping("removeDocument")
    public String removeDocument(String ids) {
        String[] split = ids.split(",");
        List<Integer> idsList = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            idsList.add(Integer.parseInt(split[i]));
        }
        documentService.deleteDocumentByIds(idsList);
        return "redirect:/document/selectDocument?pageIndex=1";
    }

    @RequestMapping("updateDocument")
    public String updateDocument(Document document, Model model, Integer flag, MultipartFile file) {
        if (flag == 1) {
            Document documentById = documentService.findDocumentById(document.getId());
            model.addAttribute("document",documentById);
            return "/document/showUpdateDocument";
        } else {
            documentService.modifyDocument(document);
            return "redirect:/document/selectDocument?pageIndex=1";
        }
    }
}
