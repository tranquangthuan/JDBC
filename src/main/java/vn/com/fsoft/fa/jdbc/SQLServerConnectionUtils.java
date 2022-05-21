package vn.com.fsoft.fa.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLServerConnectionUtils {
	public static Connection getConnection() {
		String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=jdbc";
		String USER_NAME = "sa";
		String PASSWORD = "gau@123@ABCD";
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
			System.out.println("connect successfully!");
		} catch (Exception ex) {
			System.out.println("connect failure!");
			ex.printStackTrace();
		}
		return conn;
	}
}
