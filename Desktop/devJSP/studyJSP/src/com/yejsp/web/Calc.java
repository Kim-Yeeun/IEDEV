package com.yejsp.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calc")
public class Calc extends HttpServlet{
	protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=UTF-8");
		
		String first_ = req.getParameter("first");
		String second_ = req.getParameter("second");
		String op = req.getParameter("operator");
		
		int first = 0;
		int second = 0;
		int result = 0;
		
		if (!first_.equals(""))
			first = Integer.parseInt(first_);
		
		if (!second_.equals(""))
			second = Integer.parseInt(second_);
		
		if (op.equals("덧셈"))
			result = first + second;
		else
			result = first - second;
		
		PrintWriter out = res.getWriter();
		
		out.println("답 : " + result);	
	}
}
