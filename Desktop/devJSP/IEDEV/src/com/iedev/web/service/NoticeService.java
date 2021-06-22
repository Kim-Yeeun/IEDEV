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
import com.iedev.web.entity.NoticeView;

public class NoticeService {

	// 공지사항 등록 서비스 
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
	
	public int delete(int id) {
		int result = 0;
		
		String sql = "DELETE FROM NOTICE WHERE ID = ?";
		
		String url = "jdbc:mysql://localhost:3306/IEDEV";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "admin", "kimyeeun");
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			
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
	
	public int update(Notice notice) {
		int result = 0;
		
		String sql = "UPDATE NOTICE SET TITLE = ?, CONTENT = ?, FILES = ? WHERE ID = ?";
		
		String url = "jdbc:mysql://localhost:3306/IEDEV";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "admin", "kimyeeun");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, notice.getTitle());
			st.setString(2, notice.getContent());
			st.setString(3, notice.getFiles());
			st.setInt(4, notice.getId());
			
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
	
	// 공지사항 목록 서비스 
	public List<NoticeView> getList() {
		
		return getList("title", "", 1);
	}
	
	public List<NoticeView> getList(int page) {
		
		
		return getList("title", "", page);
	}
	
	public List<NoticeView> getList(String field, String query, int page){
		List<NoticeView> list = new ArrayList<>(); 
		
		String sql = "SELECT * FROM ("
				+ "SELECT @ROWNUM:=@ROWNUM+1 NUM, "
				+ "N.* "
				+ "FROM NOTICE_VIEW N, "
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
				int id = rs.getInt("ID");
				String title = rs.getString("TITLE"); 
			 	Timestamp regDate = rs.getTimestamp("REGDATE"); 
			 	String writerId = rs.getString("WRITERID"); 
			 	int hit = rs.getInt("HIT"); 
			 	String files = rs.getString("FILES"); 
			 	//String content = rs.getString("CONTENT");
			 	int cmtCount = rs.getInt("CMT_COUNT");
			 	
			 	NoticeView notice = new NoticeView(
		 				id,
		 				title,
		 				regDate,
		 				writerId,
		 				hit,
		 				files,
		 				cmtCount
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
		
		String sql = "SELECT COUNT(ID) COUNT FROM ("
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
	
	public Notice getInfo(int id) {
		Notice notice = null;
		
		String sql = "SELECT * FROM NOTICE WHERE ID = ?";
		
		String url = "jdbc:mysql://localhost:3306/IEDEV";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "admin", "kimyeeun");
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				int nid = rs.getInt("ID");
				String title = rs.getString("TITLE"); 
				Timestamp regDate = rs.getTimestamp("REGDATE");
			 	String writerId = rs.getString("WRITERID"); 
			 	int hit = rs.getInt("HIT"); 
			 	String files = rs.getString("FILES"); 
			 	String content = rs.getString("CONTENT");
			 	
			 	notice = new Notice(
		 				nid,
		 				title,
		 				regDate,
		 				writerId,
		 				hit,
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
	
	public Notice getNextNotice(int id) {
		Notice notice = null;
		
		String sql = "SELECT * FROM NOTICE WHERE ID = (SELECT MIN(ID) FROM NOTICE WHERE ID > ?)";
		
		String url = "jdbc:mysql://localhost:3306/IEDEV";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "admin", "kimyeeun");
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				int nid = rs.getInt("ID");
				String title = rs.getString("TITLE"); 
				Timestamp regDate = rs.getTimestamp("REGDATE"); 
			 	String writerId = rs.getString("WRITERID"); 
			 	int hit = rs.getInt("HIT"); 
			 	String files = rs.getString("FILES"); 
			 	String content = rs.getString("CONTENT");
			 	
			 	notice = new Notice(
		 				nid,
		 				title,
		 				regDate,
		 				writerId,
		 				hit,
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
	
	public Notice getPrevNotice(int id) {
		Notice notice = null;
		
		String sql = "SELECT * FROM NOTICE WHERE ID = (SELECT MAX(ID) FROM NOTICE WHERE ID < ?)";
		
		String url = "jdbc:mysql://localhost:3306/IEDEV";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "admin", "kimyeeun");
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				int nid = rs.getInt("ID");
				String title = rs.getString("TITLE"); 
				Timestamp regDate = rs.getTimestamp("REGDATE");
			 	String writerId = rs.getString("WRITERID"); 
			 	int hit = rs.getInt("HIT"); 
			 	String files = rs.getString("FILES"); 
			 	String content = rs.getString("CONTENT");
			 	
			 	notice = new Notice(
		 				nid,
		 				title,
		 				regDate,
		 				writerId,
		 				hit,
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
}
