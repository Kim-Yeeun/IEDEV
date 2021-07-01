package com.iedev.web.controller.post;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iedev.web.entity.Apply;
import com.iedev.web.service.PostService;

@WebServlet("/post/apply")
public class ApplyController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String id = (String)session.getAttribute("id");
		String content = req.getParameter("content");
		int pno = Integer.parseInt(req.getParameter("pno"));
		
		Apply apply = new Apply();
		apply.setWriterId(id);
		apply.setContent(content);
		apply.setPno(pno);
		
		PostService service = new PostService();
		service.insertApply(apply);
		
		res.sendRedirect("detail?no="+pno);
	}
}
