package jdbc;

import java.sql.DriverManager;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class DBCPInit extends HttpServlet {

	public void init() throws ServletException {
		loadJDBCDriver();
		initConnectionPool();
	}
	
	private void loadJDBCDriver() {
		try {
			// JDBC 드라이버를 로딩 
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			throw new RuntimeException("fail to load JDBC Driver", ex);
		}
	}
	
	private void initConnectionPool() {
		try {
			String jdbcUrl = "jdbc:mysql://localhost:3306/chap14?" + "useUnicode=true&characterEncoding=utf8";
			String username = "jspexam";
			String pw = "jsppasswd";
			
			// 커넥션 풀이 새로운 커넥션을 생성할 때 사용할 커넥션 팩토리를 생성
			ConnectionFactory connFactory = new DriverManagerConnectionFactory(jdbcUrl, username, pw);
			
			// 커넥션 풀에 커넥션을 보관할 때 사용할 PoolableConnection을 생성 
			PoolableConnectionFactory poolableConnFactory = new PoolableConnectionFactory(connFactory, null);
			// 커넥션의 유효 여부 검사 
			poolableConnFactory.setValidationQuery("select 1");
			
			GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
			poolConfig.setTimeBetweenEvictionRunsMillis(1000L * 60L * 5L);
			poolConfig.setTestWhileIdle(true);
			poolConfig.setMinIdle(4);
			poolConfig.setMaxTotal(50);
			
			// 커넥션 풀 생성 
			GenericObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnFactory, poolConfig);
			poolableConnFactory.setPool(connectionPool);
			
			// 커넥션 풀을 제공하는 JDBC 드라이버를 등록 
			Class.forName("org.apache.commons.dbcp2.PoolingDriver");
			PoolingDriver driver = (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");
			
			// 커넥션 드라이버에 생성한 커넥션 풀 등록 
			driver.registerPool("chap14", connectionPool);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
