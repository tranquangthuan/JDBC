package vn.com.fsoft.fa.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import vn.com.fsoft.fa.jdbc.Student;

public class StudentMapper implements Mapper<Student> {

	@Override
	public Student map(ResultSet rs) throws SQLException {
		return new Student(rs.getInt("id"), rs.getString("name"), rs.getInt("age"), rs.getString("address"));
	}

}
