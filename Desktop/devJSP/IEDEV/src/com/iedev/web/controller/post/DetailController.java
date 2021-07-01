package com.iedev.web.controller.post;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iedev.web.entity.Apply;
import com.iedev.web.entity.Post;
import com.iedev.web.service.PostService;

@WebServlet("/post/detail")
public class DetailController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int no = Integer.parseInt(req.getParameter("no"));

		PostService service = new PostService();
		Post post = service.getInfo(no);
		
		Post postNext = new Post();
		postNext = service.getNextPost(no);
		
		Post postPrev = new Post();
		postPrev = service.getPrevPost(no);
		
		service.updateViews(no);
		
		ArrayList<Apply> applys = service.getListApply(no);
		
		req.setAttribute("p", post);
		req.setAttribute("next", postNext);
		req.setAttribute("prev", postPrev);
		req.setAttribute("applys", applys);
		
		req.getRequestDispatcher("/WEB-INF/view/post/detail.jsp").forward(req, res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	}
}
