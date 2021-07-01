package com.iedev.web.controller.portfolio;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

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

@WebServlet("/portfolio/reg")
public class RegController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/view/portfolio/reg.jsp").forward(req, res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String id = (String)session.getAttribute("id");
		String name = req.getParameter("name");
		String birth = req.getParameter("birth");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		String certificate = req.getParameter("certificate");
		String language = req.getParameter("language");
		String project = req.getParameter("project");
		String introduce = req.getParameter("introduce");
		
		Portfolio portfolio = new Portfolio();
		portfolio.setId(id);
		portfolio.setName(name);
		portfolio.setBirth(birth);
		portfolio.setEmail(email);
		portfolio.setPhone(phone);
		portfolio.setCertificate(certificate);
		portfolio.setLanguage(language);
		portfolio.setProject(project);
		portfolio.setIntroduce(introduce);
		
		PortfolioService p_service = new PortfolioService();
		p_service.insert(portfolio);
		MemberService m_service = new MemberService();
		Member member = m_service.getInfo(id);
		
		req.setAttribute("portfolio", portfolio);
		req.setAttribute("member", member);
		
		req.getRequestDispatcher("/WEB-INF/view/portfolio/detail.jsp?id="+id).forward(req, res);
	}
	
}
