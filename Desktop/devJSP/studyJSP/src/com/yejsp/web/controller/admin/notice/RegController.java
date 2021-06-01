package com.yejsp.web.controller.admin.notice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.yejsp.web.entity.Notice;
import com.yejsp.web.service.NoticeService;

@MultipartConfig(
		fileSizeThreshold = 1024*1024, // 1MB
		maxFileSize = 1024*1024*5,
		maxRequestSize = 1024*1024*5*5
)
@WebServlet("/admin/board/notice/reg")
public class RegController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/view/admin/board/notice/reg.jsp").forward(req, res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String isOpen = req.getParameter("open");
		boolean pub = false;
		
		Collection<Part> parts = req.getParts();
		StringBuilder builder = new StringBuilder();
		for(Part p : parts) {
			if(!p.getName().equals("file"))
				continue;
			
			if(p.getSize() == 0)
				continue;
			
			Part filePart = p;
			String fileName = filePart.getSubmittedFileName();
			builder.append(fileName);
			builder.append(",");
			
			InputStream fis = filePart.getInputStream();
			
			String realPath = req.getServletContext().getRealPath("/upload");
			
			File path = new File(realPath);
			if(!path.exists())
				path.mkdirs();
			
			String filePath = realPath + File.separator + fileName;
			FileOutputStream fos = new FileOutputStream(filePath);
			
			byte[] buf = new byte[1024];
			int size = 0;
			while((size = fis.read(buf)) != -1)
				fos.write(buf, 0, size);
			
			fos.close();
			fis.close();
		}
		
		builder.delete(builder.length()-1, builder.length());
			
		
		if (isOpen != null)
			pub = true;
		
		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setContent(content);
		notice.setPub(pub);
		notice.setWriterId("kim");
		notice.setFiles(builder.toString());
		
		NoticeService service = new NoticeService();
		service.insertNotice(notice);

		// 새로운 페이지(컨트롤러)를 요청한다.
		res.sendRedirect("list");
	}
	
}
