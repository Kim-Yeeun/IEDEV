package com.iedev.web.controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iedev.web.entity.Member;
import com.iedev.web.service.MemberService;

@WebServlet("/member/modify")
public class ModifyController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
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
		int result = service.update(member);
		
		HttpSession session = req.getSession();
		if(result == 1) {
			session.invalidate();
			res.sendRedirect("login");
		}
	}
}
