package com.iedev.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.iedev.web.entity.Apply;
import com.iedev.web.entity.Post;
import com.mysql.cj.exceptions.RSAException;

public class PostService {

	public void insert(Post post) {
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
			st.executeUpdate();
			
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(int no) {
		String sql = "DELETE FROM POST WHERE NO = ?";
		
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
	
	public void update(Post post) {
		String sql = "UPDATE POST SET TITLE = ?, FILES = ?, CONTENT = ? WHERE NO = ?";
		
		String url = "jdbc:mysql://localhost:3306/IEDEV";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "admin", "kimyeeun");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, post.getTitle());
			st.setString(2, post.getFiles());
			st.setString(3, post.getContent());
			st.setInt(4, post.getNo());
			st.executeUpdate();
			
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
				int no = rs.getInt("NO");
				String title = rs.getString("TITLE"); 
			 	Timestamp regDate = rs.getTimestamp("REGDATE"); 
			 	String writerId = rs.getString("WRITERID"); 
			 	int views = rs.getInt("VIEWS"); 
			 	String files = rs.getString("FILES");
			 	String content = rs.getString("CONTENT");
			 	
			 	Post post = new Post(
			 			no,
		 				title,
		 				regDate,
		 				writerId,
		 				views,
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
		
		String sql = "SELECT COUNT(NO) COUNT FROM (SELECT @ROWNUM:=@ROWNUM+1 NUM, P.* FROM POST P, (SELECT @ROWNUM:=0) R"
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
	
	public Post getInfo(int no) {
		Post post = null;
		
		String sql = "SELECT * FROM POST WHERE NO = ?";
		
		String url = "jdbc:mysql://localhost:3306/IEDEV";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "admin", "kimyeeun");
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, no);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				int no_ = rs.getInt("NO");
				String title = rs.getString("TITLE"); 
				Timestamp regDate = rs.getTimestamp("REGDATE");
			 	String writerId = rs.getString("WRITERID"); 
			 	int views = rs.getInt("VIEWS"); 
			 	String files = rs.getString("FILES"); 
			 	String content = rs.getString("CONTENT");
			 	
			 	post = new Post(
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
		
		return post;
	}
	
	public Post getNextPost(int no) {
		Post post = null;
		
		String sql = "SELECT * FROM POST WHERE NO = (SELECT MIN(NO) FROM POST WHERE NO > ?)";
		
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
			 	
			 	post = new Post(
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
		
		return post;
	}
	
	public Post getPrevPost(int no) {
		Post post = null;
		
		String sql = "SELECT * FROM POST WHERE NO = (SELECT MAX(NO) FROM POST WHERE NO < ?)";
		
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
			 	
			 	post = new Post(
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
		
		return post;
	}
	
	public void updateViews(int no) {
		String sql = "UPDATE POST SET VIEWS = VIEWS + 1 WHERE NO = ?";
		
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
	
	public ArrayList<Apply> getListApply(int pno){
		ArrayList<Apply> applys = new ArrayList<Apply>();
		
		String sql = "SELECT * FROM APPLY WHERE PNO = ? ORDER BY CNO DESC";
		
		String url = "jdbc:mysql://localhost:3306/IEDEV";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "admin", "kimyeeun");
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, pno);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Apply apply = new Apply();
				apply.setPno(rs.getInt("pno"));
				apply.setCno(rs.getInt("cno"));
				apply.setContent(rs.getString("content"));
				apply.setRegDate(rs.getTimestamp("regDate"));
				apply.setWriterId(rs.getString("writerId"));
				applys.add(apply);
			}
			
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return applys;
	}
	
	public void insertApply(Apply apply) {
		String sql = "INSERT INTO APPLY(WRITERID, CONTENT, PNO) VALUES (?, ?, ?)";
		
		String url = "jdbc:mysql://localhost:3306/IEDEV";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "admin", "kimyeeun");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, apply.getWriterId());
			st.setString(2, apply.getContent());
			st.setInt(3, apply.getPno());
			st.executeUpdate();
			
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteApply(int cno) {
		String sql = "DELETE FROM APPLY WHERE CNO = ?";
		
		String url = "jdbc:mysql://localhost:3306/IEDEV";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "admin", "kimyeeun");
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, cno);
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
