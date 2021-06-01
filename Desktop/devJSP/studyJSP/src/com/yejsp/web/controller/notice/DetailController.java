package com.yejsp.web.controller.notice;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yejsp.web.entity.Notice;
import com.yejsp.web.service.NoticeService;

//이 파일을 통해 jsp 파일이 실행된다.
@WebServlet("/notice/detail")
public class DetailController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));

		NoticeService service = new NoticeService();
		Notice notice = service.getNotice(id);
		req.setAttribute("n", notice);
		
		// 이 파일에서 사용중인 내용을 forward 함수를 통해 전달해준다.
		req.getRequestDispatcher("/WEB-INF/view/notice/detail.jsp").forward(req, res);
	}
}
