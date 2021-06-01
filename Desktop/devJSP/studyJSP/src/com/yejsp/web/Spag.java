package com.yejsp.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/spag")
public class Spag extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int n = 0;
		String n_ = req.getParameter("n");
		if (n_ != null && !n_.equals(""))
			n = Integer.parseInt(n_);
		
		String result = "";
		if (n % 2 == 0) {
			result = "짝수";
		} else {
			result = "홀수";
		}
		
		req.setAttribute("result", result);
		
		String[] names = {"Kim", "Lee"};
		req.setAttribute("names", names);
		
		Map<String, Object> notice = new HashMap<>();
		notice.put("id", 1);
		notice.put("title", "EL은 유용하다.");
		req.setAttribute("notice", notice);
		
		// 데이터 저장소 역할을 한다.
		RequestDispatcher dispatcher = req.getRequestDispatcher("spag.jsp");
		// result 값을 req를 통해 전달해준다.
		dispatcher.forward(req, res);
	}
}
