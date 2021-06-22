package com.iedev.web.controller.member;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.iedev.web.entity.Member;
import com.iedev.web.service.MemberService;

@WebServlet("/member/join")
public class JoinController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/view/member/join.jsp").forward(req, res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Member member = new Member();
		member.setType(Integer.parseInt(req.getParameter("type")));
		member.setId(req.getParameter("id"));
		member.setName(req.getParameter("name"));
		member.setPassword(req.getParameter("password"));
		member.setEmail(req.getParameter("email"));
		member.setPhone(req.getParameter("phone"));
		
		MemberService service = new MemberService();
		service.insert(member);
		
		res.sendRedirect("login");
	}
	
}
