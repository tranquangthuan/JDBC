package vn.com.fsoft.fa.jdbc;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
//		SQLUtils.createTable();
//		insert();
//		insertWithStatement();
//		update();
//		compareInsertWithBatchAndWithoutBatch();
		listAll();
	}

	public static void insert() {
		StudentDAO studentDAO = new StudentDAO();
		studentDAO.insert("Thuấn", 30, "Đà Nẵng");
	}

	public static void insertWithStatement() {
		StudentDAO studentDAO = new StudentDAO();
		studentDAO.insertWithStatement();
	}

	public static void compareInsertWithBatchAndWithoutBatch() {
		StudentDAO studentDAO = new StudentDAO();
		studentDAO.insertWithoutBatch();
		studentDAO.insertWithBatch(200);
	}

	public static void listAll() {
		StudentDAO studentDAO = new StudentDAO();
		List<Student> allStudent = studentDAO.getAlll();
		allStudent.forEach(s -> System.out.println(s));
	}

	public static void update() {
		StudentDAO studentDAO = new StudentDAO();
		listAll();
		studentDAO.update(30, "ĐÀ NẴNG, VIỆT NAM");
		listAll();
	}

	public static void updateWithResultSet() {
		StudentDAO studentDAO = new StudentDAO();
		listAll();
		studentDAO.updateWithResultSet();
		listAll();
	}
}
