package vn.com.fsoft.fa.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import vn.com.fsoft.fa.jdbc.mapper.Mapper;
import vn.com.fsoft.fa.jdbc.mapper.StudentMapper;

public class StudentDAO {

	public int insertWithStatement() {
		int result = 0;

		String sql = "INSERT INTO student(name, age ,address) VALUES (N'Tiến Linh', 20, N'Đà Nẵng, Việt Nam')";
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

		String sql = "INSERT INTO student(name, age ,address) VALUES (?, ?, ?)";
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
			Mapper<Student> studentMapper = new StudentMapper();
			// StudentMapper studentMapper = new StudentMapper();
			while (rs.next()) {
				result.add(
						new Student(rs.getInt("id"), rs.getString("name"), rs.getInt("age"), rs.getString("address")));
//				result.add(studentMapper.map(rs));
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

	public int update(int age, String address) {
		int result = 0;

		String sql = "UPDATE student set address = ? where age = ? ";
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = SQLServerConnectionUtils.getConnection();
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, address);
			stmt.setInt(2, age);

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

	public boolean updateWithResultSet() {
		boolean result = false;

		String sql = "SELECT * FROM Student";
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = SQLServerConnectionUtils.getConnection();
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

			ResultSet rs = stmt.executeQuery(sql);
			rs.last();
			rs.updateString("name", "Update name of last row");
			rs.updateRow();
			System.out.println("Row updated");
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

	public void insertWithoutBatch() {
		long startTime = System.currentTimeMillis();
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = SQLServerConnectionUtils.getConnection();
			stmt = conn.createStatement();
			List<Student> student = UserUtils.init();
			System.out.println("Without Batch - Inserting... ");
			for (Student st : student) {
				String sql = "INSERT INTO student(name, age ,address) VALUES (N'" + st.getName() + "', " + st.getAge()
						+ ", N'" + st.getAddress() + "');";
				stmt.executeUpdate(sql);
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
		long endTime = System.currentTimeMillis();
		System.out.println("Total time with out batch: " + (endTime - startTime));
	}

	public void insertWithBatch(int batchSize) {
		long startTime = System.currentTimeMillis();
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = SQLServerConnectionUtils.getConnection();
			stmt = conn.createStatement();
			conn.setAutoCommit(false);
			List<Student> student = UserUtils.init();
			System.out.println("Without Batch - Inserting... ");
			for (int i = 0; i < student.size(); i++) {
				Student st = student.get(i);
				String sql = "INSERT INTO student(name, age ,address) VALUES (N'" + st.getName() + "', " + st.getAge()
						+ ", N'" + st.getAddress() + "');";
				stmt.addBatch(sql);
				if (i % batchSize == 0) {
					stmt.executeBatch();
				}
			}
			stmt.executeBatch();
			conn.commit();
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
		long endTime = System.currentTimeMillis();
		System.out.println("Total time with batch: " + (endTime - startTime));
	}
}
