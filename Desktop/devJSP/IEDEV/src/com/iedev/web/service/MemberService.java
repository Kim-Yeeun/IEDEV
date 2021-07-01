package com.iedev.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.iedev.web.entity.Member;

public class MemberService {
	
	private static MemberService instance = new MemberService();
	
	public static MemberService getInstance() {
		return instance;
	}

	public int insert(Member member) {
		int result = 0;
		
		String sql = "INSERT INTO MEMBER(TYPE, ID, NAME, PASSWORD, EMAIL, PHONE) VALUES (?, ?, ?, ?, ?, ?)";
		
		String url = "jdbc:mysql://localhost:3306/IEDEV";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "admin", "kimyeeun");
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, member.getType());
			st.setString(2, member.getId());
			st.setString(3, member.getName());
			st.setString(4, member.getPassword());
			st.setString(5, member.getEmail());
			st.setString(6, member.getPhone());
			
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
	
	public int delete(String id) {
		int result = 0;
		
		String sql = "DELETE FROM MEMBER WHERE ID = ?";
		
		String url = "jdbc:mysql://localhost:3306/IEDEV";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "admin", "kimyeeun");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, id);
			
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
	
	public int update(Member member) {
		int result = 0;
		
		String sql = "UPDATE MEMBER SET NAME = ?, PASSWORD = ?, EMAIL = ?, PHONE = ? WHERE ID = ?";
		
		String url = "jdbc:mysql://localhost:3306/IEDEV";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "admin", "kimyeeun");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, member.getName());
			st.setString(2, member.getPassword());
			st.setString(3, member.getEmail());
			st.setString(4, member.getPhone());
			st.setString(5, member.getId());
			
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
	
	public ArrayList<Member> getList(){
		ArrayList<Member> members = new ArrayList<Member>();
		
		String sql = "SELECT * FROM MEMBER";
		
		String url = "jdbc:mysql://localhost:3306/IEDEV";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "admin", "kimyeeun");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()) {
				Member member = new Member();
				member.setType(rs.getInt("type"));
				member.setId(rs.getString("id"));
				member.setName(rs.getString("name"));
				member.setPassword(rs.getString("password"));
				member.setEmail(rs.getString("email"));
				member.setPhone(rs.getString("phone"));
				members.add(member);
			}
			
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return members;
	}
	
	public Member getInfo(String id) {
		Member member = null;
		
		String sql = "SELECT * FROM MEMBER WHERE ID = ?";
		
		String url = "jdbc:mysql://localhost:3306/IEDEV";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "admin", "kimyeeun");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, id);
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				int type = rs.getInt("type");
				String nid = rs.getString("id");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				Timestamp regDate = rs.getTimestamp("regDate");
				
				member = new Member(type,
									nid,
									name,
									password,
									email,
									phone,
									regDate);
			}
			
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return member;
	}
	
	public int checkLogin(String id, String password) {
		int t = -1;
		
		String sql = "SELECT PASSWORD, TYPE FROM MEMBER WHERE ID = '"+id+"'";
		
		String url = "jdbc:mysql://localhost:3306/IEDEV";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "admin", "kimyeeun");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			if(rs.next()) {
				if(rs.getString("password").equals(password)) {
					t = rs.getInt("type");
				} else {
					t = 3;
				}
			}
			
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return t;
	}
	
	public String checkId(String id) {
		String result = "y";
		
		String sql = "SELECT * FROM MEMBER WHERE ID ='"+id+"'";
		
		String url = "jdbc:mysql://localhost:3306/IEDEV";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "admin", "kimyeeun");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			if(rs.next()) {
				result = "n";
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
