package com.newworld.app.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.newworld.app.entity.Notice;

public class NoticeService {
	private String url = "jdbc:mysql://localhost:3306/study";
	private String uid = "jspexam";
	private String pwd = "jsppasswd";
	private String driver = "com.mysql.cj.jdbc.Driver";
	
	// select 하기 위한 서비스 생성 
	public List<Notice> getList(int page, String field, String query) throws ClassNotFoundException, SQLException{
		int start = 1 + (page - 1) * 10;
		int end = 10 * page;
		
		String sql = "SELECT * FROM "
				+ "("
				+ "	SELECT @ROWNUM:=@ROWNUM+1 NUM,"
				+ "	N.*"
				+ "    FROM NOTICE N,"
				+ "    (SELECT @ROWNUM:=0) R"
				+ "    WHERE 1=1"
				+ "    ORDER BY REGDATE DESC"
				+ ") list"
				+ " WHERE "+ field +" LIKE ? AND NUM >= ? AND NUM <= ?";
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, uid, pwd);
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%"+query+"%");
		st.setInt(2, start);
		st.setInt(3, end);
		
		ResultSet rs = st.executeQuery();
		
		List<Notice> list = new ArrayList<Notice>();
		
		while(rs.next()) {
			int id = rs.getInt("ID");
			String title = rs.getString("title");
			String writerId = rs.getString("WRITER_ID");
			Date regDate = rs.getDate("REGDATE");
			String content = rs.getString("CONTENT");
			int hit = rs.getInt("HIT");
			String files = rs.getString("FILES");
			
			Notice notice = new Notice(
								id,
								title,
								writerId,
								regDate,
								content,
								hit,
								files
					);
			
			list.add(notice);
		}

		rs.close();
		st.close();
		con.close();
		
		return list;
	}
	
	public int getTotal() throws ClassNotFoundException, SQLException {
		int total = 0;
		
		String sql = "SELECT COUNT(TITLE) TOTAL FROM "
				+ "("
				+ "	SELECT @ROWNUM:=@ROWNUM+1 NUM,"
				+ "	N.*"
				+ "    FROM NOTICE N,"
				+ "    (SELECT @ROWNUM:=0) R"
				+ "    WHERE 1=1"
				+ "    ORDER BY REGDATE DESC"
				+ ") list";
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, uid, pwd);
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		if (rs.next())
			total = rs.getInt("TOTAL");	

		rs.close();
		st.close();
		con.close();
		
		return total;
	}

	// 사용자가 입력한 값이 notice로 전달 
	public int insert(Notice notice) throws ClassNotFoundException, SQLException {
		String title = notice.getTitle();
		String writerId = notice.getWriterId();
		String content = notice.getContent();
		String files = notice.getFiles();
		
		
		String sql = "INSERT INTO NOTICE(title, writer_id, content, files) VALUES (?, ?, ?, ?)";
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, uid, pwd);
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, title);
		st.setString(2, writerId);
		st.setString(3, content);
		st.setString(4, files);
		
		int result = st.executeUpdate();
		
		st.close();
		con.close();
		
		return result;
	}
	
	public int update(Notice notice) throws ClassNotFoundException, SQLException {
		String title = notice.getTitle();
		String content = notice.getContent();
		String files = notice.getFiles();
		int id = notice.getId();
		
		String sql = "UPDATE NOTICE SET TITLE = ?, CONTENT = ?, FILES = ? WHERE ID = ?";
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, uid, pwd);

		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, title);
		st.setString(2, content);
		st.setString(3, files);
		st.setInt(4, id);
		
		int result = st.executeUpdate();
		
		st.close();
		con.close();
		
		return result;
	}

	public int delete(int id) throws ClassNotFoundException, SQLException {
		String sql = "DELETE FROM NOTICE WHERE ID = ?";
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, uid, pwd);

		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, id);
	
		int result = st.executeUpdate();
		
		System.out.println(result);
		
		st.close();
		con.close();
		
		return result;
	}

}
