package com.iedev.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.iedev.web.entity.Portfolio;

public class PortfolioService {

	private static PortfolioService instance = new PortfolioService();
	
	public static PortfolioService getInstance() {
		return instance;
	}
	
	public int insert(Portfolio portfolio) {
		int result = 0;
		
		String sql = "INSERT INTO PORTFOLIO(ID, NAME, BIRTH, EMAIL, PHONE, CERTIFICATE, LANGUAGE, PROJECT, INTRODUCE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		String url = "jdbc:mysql://localhost:3306/IEDEV";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "admin", "kimyeeun");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, portfolio.getId());
			st.setString(2, portfolio.getName());
			st.setString(3, portfolio.getBirth());
			st.setString(4, portfolio.getEmail());
			st.setString(5, portfolio.getPhone());
			st.setString(6, portfolio.getCertificate());
			st.setString(7, portfolio.getLanguage());
			st.setString(8, portfolio.getProject());
			st.setString(9, portfolio.getIntroduce());
			
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
	
	public int update(Portfolio portfolio) {
		int result = 0;
		
		String sql = "UPDATE PORTFOLIO SET NAME = ?, BIRTH = ?, EMAIL = ?, PHONE = ?, CERTIFICATE = ?, LANGUAGE = ?, PROJECT = ?, INTRODUCE = ? WHERE ID = ?";
		
		String url = "jdbc:mysql://localhost:3306/IEDEV";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "admin", "kimyeeun");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, portfolio.getName());
			st.setString(2, portfolio.getBirth());
			st.setString(3, portfolio.getEmail());
			st.setString(4, portfolio.getPhone());
			st.setString(5, portfolio.getCertificate());
			st.setString(6, portfolio.getLanguage());
			st.setString(7, portfolio.getProject());
			st.setString(8, portfolio.getIntroduce());
			st.setString(9, portfolio.getId());
			
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
	
	public Portfolio getInfo(String id) {
		Portfolio portfolio = null;
		
		String sql = "SELECT * FROM PORTFOLIO WHERE ID = ?";
		
		String url = "jdbc:mysql://localhost:3306/IEDEV";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "admin", "kimyeeun");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, id);
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				String nid = rs.getString("id");
				String name = rs.getString("name");
				String birth = rs.getString("birth");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				String certificate = rs.getString("certificate");
				String language = rs.getString("language");
				String project = rs.getString("project");
				String introduce = rs.getString("introduce");
				Timestamp regDate = rs.getTimestamp("regDate");
				
				portfolio = new Portfolio(nid,
										  name,
										  birth,
										  email,
										  phone,
										certificate,
										language,
										project,
										introduce,
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
		
		return portfolio;
	}
	
}
