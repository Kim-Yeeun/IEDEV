package com.iedev.web.controller.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.iedev.web.entity.Member;
import com.iedev.web.service.MemberService;

@WebServlet("/member/login")
public class LoginController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/view/member/login.jsp").forward(req, res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		
		MemberService service = new MemberService();
		int t = service.checkLogin(id, password);
		
		if(t == 0 || t == 1 || t == 2) {
			HttpSession session = req.getSession();
			session.setAttribute("id", id);
		}
		
		PrintWriter out = res.getWriter();
		out.println(t);
	}
	
}
