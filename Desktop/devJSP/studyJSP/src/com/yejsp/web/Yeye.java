package com.yejsp.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// request는 입력도구, response는 출력도구 

@WebServlet("/hi")
public class Yeye extends HttpServlet {
	protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = res.getWriter();
		
		String s = req.getParameter("cnt");
		int cnt = 5;
		
		if (s != null && !s.equals("")) {
			cnt = Integer.parseInt(s);
		}
		
		for(int i = 0; i < cnt; i++) {
			out.println((i+1) + ": 안녕? Hello!<br>");
		}
	}
}
