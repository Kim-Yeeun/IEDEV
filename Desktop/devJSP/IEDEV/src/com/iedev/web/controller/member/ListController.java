package com.iedev.web.controller.member;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iedev.web.entity.Member;
import com.iedev.web.service.MemberService;

@WebServlet("/member/list")
public class ListController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		MemberService service = new MemberService();
		ArrayList<Member> members = service.getList();
		req.setAttribute("members", members);
		
		req.getRequestDispatcher("/WEB-INF/view/member/list.jsp").forward(req, res);
	}
	
	@Override 
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
}
