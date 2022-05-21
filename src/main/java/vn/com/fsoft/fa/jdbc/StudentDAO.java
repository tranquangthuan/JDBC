package vn.com.fsoft.fa.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

	public int insertWithStatement() {
		int result = 0;

		String sql = "INSERT INTO student(name, age ,address) VALUES (N'Tiến Linh', 20, N'Đà Nẵng, Việt Nam');";
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = SQLServerConnectionUtils.getConnection();
			stmt = conn.createStatement();

			int rowCount = stmt.executeUpdate(sql);
			System.out.println("Row Count affected = " + rowCount);
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
		return result;
	}

	public int insert(String name, int age, String address) {
		int result = 0;

		String sql = "INSERT INTO student(name, age ,address) VALUES (?, ?, ?);";
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = SQLServerConnectionUtils.getConnection();
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, name);
			stmt.setInt(2, age);
			stmt.setString(3, address);

			int rowCount = stmt.executeUpdate();
			System.out.println("Row Count affected = " + rowCount);
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
		return result;
	}

	public List<Student> getAlll() {
		List<Student> result = new ArrayList<Student>();

		String sql = "SELECT * FROM Student";
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = SQLServerConnectionUtils.getConnection();
			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				result.add(
						new Student(rs.getInt("id"), rs.getString("name"), rs.getInt("age"), rs.getString("address")));
			}
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
		return result;
	}

}
