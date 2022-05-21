package vn.com.fsoft.fa.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLUtils {
	public static void createTable() {
		String sqlCreatetable = "CREATE TABLE student (\r\n" + "  id INT IDENTITY(1, 1) PRIMARY KEY NOT NULL, \r\n"
				+ "  name NVARCHAR (50) NOT NULL, \r\n" + "  age int NOT NULL, \r\n"
				+ "  address NVARCHAR (50) NOT NULL\r\n" + ");";
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = SQLServerConnectionUtils.getConnection();
			stmt = conn.createStatement();

			stmt.execute(sqlCreatetable);
			System.out.println("Table created =");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// Close connection
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
