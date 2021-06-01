package com.yejsp.web.controller.notice;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yejsp.web.entity.Notice;
import com.yejsp.web.entity.NoticeView;
import com.yejsp.web.service.NoticeService;

@WebServlet("/notice/list")
public class ListController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String field_ = req.getParameter("f");
		String query_ = req.getParameter("q");
		String page_ = req.getParameter("p");
		
		String field = "title";
		if (field_ != null && !field_.equals(""))
			field = field_;
		
		String query = "";
		if (query_ != null && !query_.equals(""))
			query = query_;
		
		int page = 1;
		if (page_ != null && !page_.equals(""))
			page = Integer.parseInt(page_);
		
		NoticeService service = new NoticeService();
		List<NoticeView> list = service.getNoticePubList(field, query, page);	 	
		int count = service.getNoticeCount(field, query);
		
		req.setAttribute("list", list);
		req.setAttribute("count", count);
		
		req.getRequestDispatcher("/WEB-INF/view/notice/list.jsp").forward(req, res);
	}
}
