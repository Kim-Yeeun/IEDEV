package ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Program3 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		int id = 5;
		
		String url = "jdbc:mysql://localhost:3306/study";
		String sql = "DELETE FROM NOTICE WHERE ID = ?";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, "jspexam", "jsppasswd");

		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, id);
		
		// update 완료되면 result에 1 저장 
		int result = st.executeUpdate();
		
		System.out.println(result);
		
		st.close();
		con.close();

	}

}
