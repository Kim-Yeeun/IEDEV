package com.yejsp.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add2")
public class Add2 extends HttpServlet{
	protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=UTF-8");
		
		String[] num_ = req.getParameterValues("num");
		
		int result = 0;
		
		for (int i = 0; i < num_.length; i++) {
			int num = 0;
			if (!num_[i].equals(""))
				num = Integer.parseInt(num_[i]);
			result += num;
		}
		
		PrintWriter out = res.getWriter();
		
		out.println("답 : " + result);	
	}
}
