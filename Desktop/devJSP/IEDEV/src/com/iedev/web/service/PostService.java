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
import com.iedev.web.entity.Post;

public class PostService {

	public int insert(Post post) {
		int result = 0;
		
		String sql = "INSERT INTO POST(TITLE, WRITERID, FILES, CONTENT) VALUES (?, ?, ?, ?)";
		
		String url = "jdbc:mysql://localhost:3306/IEDEV";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "admin", "kimyeeun");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, post.getTitle());
			st.setString(2, post.getWriterId());
			st.setString(3, post.getFiles());
			st.setString(4, post.getContent());
			
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
		
		String sql = "DELETE FROM POST WHERE ID = ?";
		
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
	
	public int update(Post post) {
		int result = 0;
		
		String sql = "UPDATE POST SET TITLE = ?, FILES = ?, CONTENT = ? WHERE ID = ?";
		
		String url = "jdbc:mysql://localhost:3306/IEDEV";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "admin", "kimyeeun");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, post.getTitle());
			st.setString(2, post.getFiles());
			st.setString(3, post.getContent());
			st.setInt(4, post.getId());
			
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
	
	public List<Post> getList() {
		
		return getList("title", "", 1);
	}
	
	public List<Post> getList(int page) {
		
		
		return getList("title", "", page);
	}
	
	public List<Post> getList(String field, String query, int page) {
		List<Post> list = new ArrayList<>();
		
		String sql = "SELECT * FROM (SELECT @ROWNUM:=@ROWNUM+1 NUM, P.* FROM POST P, (SELECT @ROWNUM:=0) R"
				+ " WHERE "+field+" LIKE ? ORDER BY REGDATE DESC) list WHERE NUM >= ? AND NUM <= ?";
		
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
			 	String content = rs.getString("CONTENT");
			 	
			 	Post post = new Post(
		 				id,
		 				title,
		 				regDate,
		 				writerId,
		 				hit,
		 				files,
		 				content
		 			);
			 	
			 	list.add(post);
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
		
		String sql = "SELECT COUNT(ID) COUNT FROM (SELECT @ROWNUM:=@ROWNUM+1 NUM, P.* FROM POST P, (SELECT @ROWNUM:=0) R"
				+ " WHERE "+field+" LIKE ? ORDER BY REGDATE DESC) list";
		
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
	
	public Post getInfo(int id) {
		Post post = null;
		
		String sql = "SELECT * FROM POST WHERE ID = ?";
		
		String url = "jdbc:mysql://localhost:3306/IEDEV";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "admin", "kimyeeun");
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				int nid = rs.getInt("ID");
				String title = rs.getString("TITLE"); 
				Timestamp regDate = rs.getTimestamp("REGDATE");
			 	String writerId = rs.getString("WRITERID"); 
			 	int hit = rs.getInt("HIT"); 
			 	String files = rs.getString("FILES"); 
			 	String content = rs.getString("CONTENT");
			 	
			 	post = new Post(
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
		
		return post;
	}
	
	public Post getNextPost(int id) {
		Post post = null;
		
		String sql = "SELECT * FROM POST WHERE ID = (SELECT MIN(ID) FROM POST WHERE ID > ?)";
		
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
			 	
			 	post = new Post(
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
		
		return post;
	}
	
	public Post getPrevPost(int id) {
		Post post = null;
		
		String sql = "SELECT * FROM POST WHERE ID = (SELECT MAX(ID) FROM POST WHERE ID < ?)";
		
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
			 	
			 	post = new Post(
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
		
		return post;
	}
}
