package com.iedev.web.controller.notice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iedev.web.entity.Notice;
import com.iedev.web.service.NoticeService;

@WebServlet("/notice/modifyView")
public class ModifyViewController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int no = Integer.parseInt(req.getParameter("no"));
		
		NoticeService service = new NoticeService();
		Notice notice = service.getInfo(no);
		
		req.setAttribute("notice", notice);
		
		req.getRequestDispatcher("/WEB-INF/view/notice/modify.jsp").forward(req, res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}
