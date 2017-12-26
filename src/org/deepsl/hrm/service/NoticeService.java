package org.deepsl.hrm.service;

import java.util.List;

import org.deepsl.hrm.domain.Notice;
import org.deepsl.hrm.util.tag.PageModel;

public interface NoticeService {

	void addNotice(Notice notice);

	PageModel getPageModel(String num);

	List<Notice> getNotices(PageModel pageModel);

	Notice getNotice(Integer id);

	void updateNotice(Notice notice);

	List<Notice> getNotices(PageModel pageModel, String title, String content);

	PageModel getPageModel(String num, String title, String content);

	void removeNotice(Integer[] ids);


}
