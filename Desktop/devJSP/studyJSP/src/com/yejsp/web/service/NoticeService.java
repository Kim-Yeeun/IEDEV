package com.yejsp.web.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.sql.Timestamp;
import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.Remove;

import com.yejsp.web.entity.Notice;
import com.yejsp.web.entity.NoticeView;

public class NoticeService {
	public int deleteNoticeAll(int[] ids) {
		int result = 0;
		
		String params = "";
		
		for(int i = 0; i < ids.length; i++) {
			params += ids[i];
			if(i < ids.length-1)
				params += ",";
		}
		
		String sql = "DELETE FROM NOTICE WHERE ID IN ("+params+")";

		String url = "jdbc:mysql://localhost:3306/study";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "jspexam", "jsppasswd");
			Statement st = con.createStatement();
			result = st.executeUpdate(sql);
			
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int pubNoticeAll(int[] oids, int[] cids) {
		List<String> oidsList = new ArrayList<>();
		for(int i = 0; i < oids.length; i++)
			oidsList.add(String.valueOf(oids[i]));
		
		List<String> cidsList = new ArrayList<>();
		for(int i = 0; i < cids.length; i++)
			cidsList.add(String.valueOf(cids[i]));
		
		return pubNoticeAll(oidsList, cidsList);
	}
	
	public int pubNoticeAll(List<String> oids, List<String> cids) {

		String oidsCSV = String.join(",", oids);
		String cidsCSV = String.join(",", cids);
		
		return pubNoticeAll(oidsCSV, cidsCSV);
	}
	
	public int pubNoticeAll(String oidsCSV, String cidsCSV) {
		int result = 0;
		
		String sqlOpen = String.format("UPDATE NOTICE SET PUB=1 WHERE ID IN(%s)", oidsCSV);
		String sqlClose = String.format("UPDATE NOTICE SET PUB=0 WHERE ID IN(%s)", cidsCSV);
		
		String url = "jdbc:mysql://localhost:3306/study";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "jspexam", "jsppasswd");
			
			Statement stOpen = con.createStatement();
			result += stOpen.executeUpdate(sqlOpen);
			
			Statement stClose = con.createStatement();
			result += stClose.executeUpdate(sqlClose);
			
			stOpen.close();
			stClose.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int insertNotice(Notice notice) {
		int result = 0;
		
		String sql = "INSERT INTO NOTICE(ID, TITLE, WRITER_ID, CONTENT, PUB, FILES) VALUES ((SELECT get_seq('id')), ?, ?, ?, ?, ?)";

		String url = "jdbc:mysql://localhost:3306/study";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "jspexam", "jsppasswd");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, notice.getTitle());
			st.setString(2, notice.getWriterId());
			st.setString(3, notice.getContent());
			st.setBoolean(4, notice.getPub());
			st.setString(5, notice.getFiles());
			
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
	
	public int deleteNotice(int id) {
		
		return 0;
	}
	
	public int updateNotice(Notice notice) {
		
		return 0;
	}
	
	List<Notice> getNoticeNewestList(){
		
		return null;
	}
	
	
	// 공지사항 목록 서비스 
	public List<NoticeView> getNoticeList() {
		
		return getNoticeList("title", "", 1);
	}
	
	public List<NoticeView> getNoticeList(int page) {
		
		
		return getNoticeList("title", "", page);
	}

	public List<NoticeView> getNoticeList(String field, String query, int page) {
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
		
		String url = "jdbc:mysql://localhost:3306/study";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "jspexam", "jsppasswd");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%"+query+"%");
			st.setInt(2, 10*page-9);
			st.setInt(3, 10*page);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("ID");
				String title = rs.getString("TITLE"); 
			 	Timestamp regDate = rs.getTimestamp("REGDATE"); 
			 	String writerId = rs.getString("WRITER_ID"); 
			 	int hit = rs.getInt("HIT"); 
			 	String files = rs.getString("FILES"); 
			 	//String content = rs.getString("CONTENT");
			 	int cmtCount = rs.getInt("CMT_COUNT");
			 	boolean pub = rs.getBoolean("PUB");
			 	
			 	NoticeView notice = new NoticeView(
		 				id,
		 				title,
		 				regDate,
		 				writerId,
		 				hit,
		 				files,
		 				//content,
		 				pub,
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
	
	public List<NoticeView> getNoticePubList(String field, String query, int page) {
List<NoticeView> list = new ArrayList<>(); 
		
		String sql = "SELECT * FROM ("
				+ "SELECT @ROWNUM:=@ROWNUM+1 NUM, "
				+ "N.* "
				+ "FROM NOTICE_VIEW N, "
				+ "(SELECT @ROWNUM:=0) R "
				+ "WHERE "+field+" LIKE ? "
				+ "ORDER BY REGDATE DESC "
				+ ") list "
				+ "WHERE PUB = 1 AND NUM >= ? AND NUM <= ?";
		
		String url = "jdbc:mysql://localhost:3306/study";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "jspexam", "jsppasswd");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%"+query+"%");
			st.setInt(2, 10*page-9);
			st.setInt(3, 10*page);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("ID");
				String title = rs.getString("TITLE"); 
			 	Timestamp regDate = rs.getTimestamp("REGDATE"); 
			 	String writerId = rs.getString("WRITER_ID"); 
			 	int hit = rs.getInt("HIT"); 
			 	String files = rs.getString("FILES"); 
			 	//String content = rs.getString("CONTENT");
			 	int cmtCount = rs.getInt("CMT_COUNT");
			 	boolean pub = rs.getBoolean("PUB");
			 	
			 	NoticeView notice = new NoticeView(
		 				id,
		 				title,
		 				regDate,
		 				writerId,
		 				hit,
		 				files,
		 				//content,
		 				pub,
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
	
	public int getNoticeCount() {
		
		
		return getNoticeCount("title", "");
	}
	
	public int getNoticeCount(String field, String query) {
		int count = 0;
		
		String sql = "SELECT COUNT(ID) COUNT FROM ("
				+ "SELECT @ROWNUM:=@ROWNUM+1 NUM, "
				+ "N.* "
				+ "FROM NOTICE N, "
				+ "(SELECT @ROWNUM:=0) R "
				+ "WHERE "+field+" LIKE ?  "
				+ "ORDER BY REGDATE DESC "
				+ ") list";
		
		String url = "jdbc:mysql://localhost:3306/study";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "jspexam", "jsppasswd");
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
	
	public Notice getNotice(int id) {
		Notice notice = null;
		
		String sql = "SELECT * FROM NOTICE WHERE ID = ?";
		
		String url = "jdbc:mysql://localhost:3306/study";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "jspexam", "jsppasswd");
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				int nid = rs.getInt("ID");
				String title = rs.getString("TITLE"); 
				Timestamp regDate = rs.getTimestamp("REGDATE");
			 	String writerId = rs.getString("WRITER_ID"); 
			 	int hit = rs.getInt("HIT"); 
			 	String files = rs.getString("FILES"); 
			 	String content = rs.getString("CONTENT");
			 	boolean pub = rs.getBoolean("PUB");
			 	
			 	notice = new Notice(
		 				nid,
		 				title,
		 				regDate,
		 				writerId,
		 				hit,
		 				files,
		 				content,
		 				pub
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
		
		String url = "jdbc:mysql://localhost:3306/study";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "jspexam", "jsppasswd");
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				int nid = rs.getInt("ID");
				String title = rs.getString("TITLE"); 
				Timestamp regDate = rs.getTimestamp("REGDATE"); 
			 	String writerId = rs.getString("WRITER_ID"); 
			 	int hit = rs.getInt("HIT"); 
			 	String files = rs.getString("FILES"); 
			 	String content = rs.getString("CONTENT");
			 	boolean pub = rs.getBoolean("PUB");
			 	
			 	notice = new Notice(
		 				nid,
		 				title,
		 				regDate,
		 				writerId,
		 				hit,
		 				files,
		 				content,
		 				pub
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
		
		String url = "jdbc:mysql://localhost:3306/study";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "jspexam", "jsppasswd");
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				int nid = rs.getInt("ID");
				String title = rs.getString("TITLE"); 
				Timestamp regDate = rs.getTimestamp("REGDATE");
			 	String writerId = rs.getString("WRITER_ID"); 
			 	int hit = rs.getInt("HIT"); 
			 	String files = rs.getString("FILES"); 
			 	String content = rs.getString("CONTENT");
			 	boolean pub = rs.getBoolean("PUB");
			 	
			 	notice = new Notice(
		 				nid,
		 				title,
		 				regDate,
		 				writerId,
		 				hit,
		 				files,
		 				content,
		 				pub
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
