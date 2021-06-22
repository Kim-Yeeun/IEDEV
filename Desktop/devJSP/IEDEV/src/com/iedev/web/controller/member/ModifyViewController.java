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

@WebServlet("/member/modifyView")
public class ModifyViewController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String id = (String)session.getAttribute("id");
		
		MemberService service = new MemberService();
		Member member = service.getInfo(id);
		
		req.setAttribute("member", member);
		
		req.getRequestDispatcher("/WEB-INF/view/member/modify.jsp").forward(req, res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}
