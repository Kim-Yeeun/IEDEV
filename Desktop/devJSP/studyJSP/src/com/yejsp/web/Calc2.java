package com.yejsp.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/calc2")
public class Calc2 extends HttpServlet{
	protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		ServletContext application = req.getServletContext();
		HttpSession session = req.getSession();
		Cookie[] cookies = req.getCookies();
		
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=UTF-8");
		
		String n_ = req.getParameter("n");
		String op = req.getParameter("operator");
		
		int n = 0;
		int result = 0;
		
		if (!n_.equals(""))
			n = Integer.parseInt(n_);
		
		if (op.equals("=")) {
			//int x = (Integer)application.getAttribute("num");
			//int x = (Integer)session.getAttribute("num");
			int x = 0;
			for (Cookie c : cookies) {
				if (c.getName().equals("num")) {
					x = Integer.parseInt(c.getValue());
					break;
				}			
			}

			//String operator = (String)application.getAttribute("op");
			//String operator = (String)session.getAttribute("op");
			String operator = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("op")) {
					operator = c.getValue();
					break;
				}			
			}
			
			if (operator.equals("+"))
				result = x + n;
			else
				result = x - n;
			
			PrintWriter out = res.getWriter();
			 
			out.println("답 : " + result);
		} else {
//			application.setAttribute("num", n);
//			application.setAttribute("op", op);
//			session.setAttribute("num", n);
//			session.setAttribute("op", op);
			
			Cookie numCookie = new Cookie("num", String.valueOf(n));
			Cookie opCookie = new Cookie("op", op);
			numCookie.setPath("/calc2");
			numCookie.setMaxAge(60);
			opCookie.setPath("/calc2");
			opCookie.setMaxAge(60);
			
			res.addCookie(numCookie);
			res.addCookie(opCookie);
			
			res.sendRedirect("calc2.html");
		}	
	}
}
