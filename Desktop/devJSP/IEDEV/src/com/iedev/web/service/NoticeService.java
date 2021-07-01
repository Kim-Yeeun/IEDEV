package com.iedev.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.iedev.web.entity.Notice;

public class NoticeService {

	public int insert(Notice notice) {
		int result = 0;
		
		String sql = "INSERT INTO NOTICE(TITLE, WRITERID, CONTENT, FILES) VALUES (?, ?, ?, ?)";
	
		String url = "jdbc:mysql://localhost:3306/IEDEV";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "admin", "kimyeeun");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, notice.getTitle());
			st.setString(2, notice.getWriterId());
			st.setString(3, notice.getContent());
			st.setString(4, notice.getFiles());
			
			result = st.executeUpdate();
			
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int delete(int no) {
		int result = 0;
		
		String sql = "DELETE FROM NOTICE WHERE NO = ?";
		
		String url = "jdbc:mysql://localhost:3306/IEDEV";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "admin", "kimyeeun");
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, no);
			
			st.executeUpdate();
			
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int update(Notice notice) {
		int result = 0;
		
		String sql = "UPDATE NOTICE SET TITLE = ?, CONTENT = ?, FILES = ? WHERE NO = ?";
		
		String url = "jdbc:mysql://localhost:3306/IEDEV";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "admin", "kimyeeun");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, notice.getTitle());
			st.setString(2, notice.getContent());
			st.setString(3, notice.getFiles());
			st.setInt(4, notice.getNo());
			
			result = st.executeUpdate();
			
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public List<Notice> getList() {
		
		return getList("title", "", 1);
	}
	
	public List<Notice> getList(int page) {
		
		
		return getList("title", "", page);
	}
	
	public List<Notice> getList(String field, String query, int page){
		List<Notice> list = new ArrayList<>(); 
		
		String sql = "SELECT * FROM ("
				+ "SELECT @ROWNUM:=@ROWNUM+1 NUM, "
				+ "N.* "
				+ "FROM NOTICE N, "
				+ "(SELECT @ROWNUM:=0) R "
				+ "WHERE "+field+" LIKE ? "
				+ "ORDER BY REGDATE DESC "
				+ ") list "
				+ "WHERE NUM >= ? AND NUM <= ?";
		
		String url = "jdbc:mysql://localhost:3306/IEDEV";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "admin", "kimyeeun");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%"+query+"%");
			st.setInt(2, 10*page-9);
			st.setInt(3, 10*page);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				int no = rs.getInt("NO");
				String title = rs.getString("TITLE"); 
			 	Timestamp regDate = rs.getTimestamp("REGDATE"); 
			 	String writerId = rs.getString("WRITERID"); 
			 	int views = rs.getInt("VIEWS"); 
			 	String files = rs.getString("FILES");
			 	String content = rs.getString("content");
			 	
			 	Notice notice = new Notice(
			 			no,
		 				title,
		 				regDate,
		 				writerId,
		 				views,
		 				files,
		 				content
		 			);
			 	
			 	list.add(notice);
			}
			
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return list;
	}
	
	public int getCount() {
	
		return getCount("title", "");
	}
	
	public int getCount(String field, String query) {
		int count = 0;
		
		String sql = "SELECT COUNT(NO) COUNT FROM ("
				+ "SELECT @ROWNUM:=@ROWNUM+1 NUM, "
				+ "N.* "
				+ "FROM NOTICE N, "
				+ "(SELECT @ROWNUM:=0) R "
				+ "WHERE "+field+" LIKE ?  "
				+ "ORDER BY REGDATE DESC "
				+ ") list";
		
		String url = "jdbc:mysql://localhost:3306/IEDEV";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "admin", "kimyeeun");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%"+query+"%");
			ResultSet rs = st.executeQuery();
			
			if (rs.next())
				count = rs.getInt("count");
			
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}
	
	public Notice getInfo(int no) {
		Notice notice = null;
		
		String sql = "SELECT * FROM NOTICE WHERE NO = ?";
		
		String url = "jdbc:mysql://localhost:3306/IEDEV";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "admin", "kimyeeun");
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, no);
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				int no_ = rs.getInt("NO");
				String title = rs.getString("TITLE"); 
				Timestamp regDate = rs.getTimestamp("REGDATE");
			 	String writerId = rs.getString("WRITERID"); 
			 	int views = rs.getInt("VIEWS"); 
			 	String files = rs.getString("FILES"); 
			 	String content = rs.getString("CONTENT");
			 	
			 	notice = new Notice(
			 			no_,
		 				title,
		 				regDate,
		 				writerId,
		 				views,
		 				files,
		 				content
		 			);
			}
			
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return notice;
	}
	
	public Notice getNextNotice(int no) {
		Notice notice = null;
		
		String sql = "SELECT * FROM NOTICE WHERE NO = (SELECT MIN(NO) FROM NOTICE WHERE NO > ?)";
		
		String url = "jdbc:mysql://localhost:3306/IEDEV";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "admin", "kimyeeun");
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, no);
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				int no_ = rs.getInt("NO");
				String title = rs.getString("TITLE"); 
				Timestamp regDate = rs.getTimestamp("REGDATE"); 
			 	String writerId = rs.getString("WRITERID"); 
			 	int views = rs.getInt("VIEWS"); 
			 	String files = rs.getString("FILES"); 
			 	String content = rs.getString("CONTENT");
			 	
			 	notice = new Notice(
			 			no_,
		 				title,
		 				regDate,
		 				writerId,
		 				views,
		 				files,
		 				content
		 			);
			}
			
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return notice;
	}
	
	public Notice getPrevNotice(int no) {
		Notice notice = null;
		
		String sql = "SELECT * FROM NOTICE WHERE NO = (SELECT MAX(NO) FROM NOTICE WHERE NO < ?)";
		
		String url = "jdbc:mysql://localhost:3306/IEDEV";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "admin", "kimyeeun");
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, no);
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				int no_ = rs.getInt("NO");
				String title = rs.getString("TITLE"); 
				Timestamp regDate = rs.getTimestamp("REGDATE");
			 	String writerId = rs.getString("WRITERID"); 
			 	int views = rs.getInt("VIEWS");
			 	String files = rs.getString("FILES"); 
			 	String content = rs.getString("CONTENT");
			 	
			 	notice = new Notice(
			 			no_,
		 				title,
		 				regDate,
		 				writerId,
		 				views,
		 				files,
		 				content
		 			);
			}
			
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return notice;
	}
	
	public void updateViews(int no) {
		String sql = "UPDATE NOTICE SET VIEWS = VIEWS + 1 WHERE NO = ?";
		
		String url = "jdbc:mysql://localhost:3306/IEDEV";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "admin", "kimyeeun");
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, no);
			st.executeUpdate();
			
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
