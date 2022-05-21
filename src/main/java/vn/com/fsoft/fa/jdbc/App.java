package vn.com.fsoft.fa.jdbc;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
//		 SQLUtils.createTable();
		StudentDAO studentDAO = new StudentDAO();
//		studentDAO.insertWithStatement();
//		studentDAO.insert("Thuấn", 30, "Đà Nẵng");
		List<Student> allStudent = studentDAO.getAlll();
		allStudent.forEach(s -> System.out.println(s));
		// studentDAO.update(30, "ĐÀ NẴNG, VIỆT NAM");
		studentDAO.updateWithResultSet();
		allStudent = studentDAO.getAlll();
		allStudent.forEach(s -> System.out.println(s));
	}
}
