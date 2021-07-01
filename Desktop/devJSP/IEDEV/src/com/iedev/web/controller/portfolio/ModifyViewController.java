package com.iedev.web.controller.portfolio;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iedev.web.entity.Member;
import com.iedev.web.entity.Portfolio;
import com.iedev.web.service.MemberService;
import com.iedev.web.service.PortfolioService;

@WebServlet("/portfolio/modifyView")
public class ModifyViewController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		HttpSession session = req.getSession();
//		String id = (String)session.getAttribute("id");
		String id = req.getParameter("id");
		
		PortfolioService p_service = new PortfolioService();
		Portfolio portfolio = p_service.getInfo(id);
		MemberService m_service = new MemberService();
		Member member = m_service.getInfo(id);
		
		req.setAttribute("portfolio", portfolio);
		req.setAttribute("member", member);
		
		req.getRequestDispatcher("/WEB-INF/view/portfolio/modify.jsp").forward(req, res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}
