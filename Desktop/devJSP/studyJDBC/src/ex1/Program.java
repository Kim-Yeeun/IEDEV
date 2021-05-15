package ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Program {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		String title = "JDBC Test";
		String writer_id = "Kim";
		String content = "JDBC 테스트 중입니다.";
		String files = "";
		
		String url = "jdbc:mysql://localhost:3306/study";
		String sql = "INSERT INTO NOTICE(title, writer_id, content, files) VALUES (?, ?, ?, ?)";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, "jspexam", "jsppasswd");
		// Statement st = con.createStatement();
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, title);
		st.setString(2, writer_id);
		st.setString(3, content);
		st.setString(4, files);
		
		int result = st.executeUpdate();
		
		System.out.println(result);
		
		st.close();
		con.close();

	}

}
