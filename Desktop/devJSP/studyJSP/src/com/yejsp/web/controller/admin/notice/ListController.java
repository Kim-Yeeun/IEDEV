package com.yejsp.web.controller.admin.notice;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
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

@WebServlet("/admin/board/notice/list")
public class ListController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String[] openIds = req.getParameterValues("open-id");
		String[] delIds = req.getParameterValues("del-id");
		String cmd = req.getParameter("cmd");
		String ids_ = req.getParameter("ids");
		String[] ids = ids_.trim().split(" ");
		
		NoticeService service = new NoticeService();
		
		switch(cmd) {
		case "일괄공개":
			for(String openId : openIds)
				System.out.printf("open id : %s\n", openId);
			
			List<String> oids = Arrays.asList(openIds);
			List<String> cids = new ArrayList(Arrays.asList(ids));
			cids.removeAll(oids);
			
			service.pubNoticeAll(oids, cids);
			 
			break;
		case "일괄삭제":
			
			int[] ids1 = new int[delIds.length];
			for(int i = 0; i < delIds.length; i++)
				ids1[i] = Integer.parseInt(delIds[i]);
			int result = service.deleteNoticeAll(ids1);
			break;
		}
		
		res.sendRedirect("list");
	}
	
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
		List<NoticeView> list = service.getNoticeList(field, query, page);	 	
		int count = service.getNoticeCount(field, query);
		
		req.setAttribute("list", list);
		req.setAttribute("count", count);
		
		req.getRequestDispatcher("/WEB-INF/view/admin/board/notice/list.jsp").forward(req, res);
	}
}

