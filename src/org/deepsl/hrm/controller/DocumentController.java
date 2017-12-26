package org.deepsl.hrm.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public String updateDocument(Document document, Model model, Integer flag,HttpServletRequest request) {
         if (flag == 1) {
            Document documentById = documentService.findDocumentById(document.getId());
            model.addAttribute("document",documentById);
            return "/document/showUpdateDocument";
        } else {
             String originalFilename = document.getFile().getOriginalFilename();
             String s = UUID.randomUUID().toString();
             String str = s + originalFilename;
             String realPath = request.getServletContext().getRealPath("/upload");
             File uploadFile = new File(realPath,str);
             if (!uploadFile.getParentFile().exists()){
                 uploadFile.getParentFile().mkdirs();
             }
             document.setFileUrl(realPath + "\\" + str);
             User user = (User) request.getSession().getAttribute("user");
             document.setUser(user);
             document.setFileName(str);
             Document documentById = documentService.findDocumentById(document.getId());
             String fileUrl = documentById.getFileUrl();
             documentService.modifyDocument(document);
             try {
                 File file = new File(fileUrl);
                 file.delete();
                 document.getFile().transferTo(uploadFile);
             } catch (IOException e) {
                 e.printStackTrace();
             }
             return "redirect:/document/selectDocument?pageIndex=1";
        }
    }

    @RequestMapping("download")
    public void download(Integer id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Document documentById = documentService.findDocumentById(id);
        InputStream inputStream = null;
        OutputStream outputStream = null;
        if (documentById.getFileName() == null) {
            response.setCharacterEncoding("utf-8");
            response.getWriter().println("没有文件可供下载！！！");
        } else {
            try {
                outputStream = response.getOutputStream();

                // 设置文件输出类型
                response.setContentType("application/octet-stream;charset=UTF-8");
                //设置文件下载名

                response.setHeader("Content-disposition", "attachment; filename="
                        + new String(documentById.getFileName().getBytes("utf-8"), "utf-8"));
                inputStream = new FileInputStream(new File(documentById.getFileUrl()));
                byte[] bytes = new byte[1024];
                int length = 0;
                while ((length = inputStream.read(bytes, 0, bytes.length)) > 0) {
                    outputStream.write(bytes, 0, length);
                }

                outputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
	 * 添加文档
	 * */
    @RequestMapping("addDocument")
    public String addDocument(String flag,Model model,Document document,HttpServletRequest req,HttpServletResponse resp) throws IOException {
        if(flag.equals("1")){
            return "document/showAddDocument";
        }
        String originalFilename = document.getFile().getOriginalFilename();
        String uuid = UUID.randomUUID().toString();

        String fileName = uuid+originalFilename;
        document.setFileName(fileName);

        String fileUrl = req.getServletContext().getRealPath("/upload/"+fileName);
        document.setFileUrl(fileUrl);

        File file = new File(fileUrl);
        document.getFile().transferTo(file);

        document.setUser((User)req.getSession().getAttribute("user_session"));

        documentService.addDocument(document);
        return "redirect:/document/selectDocument";
    }
}
