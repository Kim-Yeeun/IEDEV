package com.iedev.web.controller.post;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iedev.web.entity.Post;
import com.iedev.web.service.PostService;

@WebServlet("/post/modifyView")
public class ModifyViewController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int no = Integer.parseInt(req.getParameter("no"));
		
		PostService service = new PostService();
		Post post = service.getInfo(no);
		
		req.setAttribute("post", post);
		
		req.getRequestDispatcher("/WEB-INF/view/post/modify.jsp").forward(req, res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}
