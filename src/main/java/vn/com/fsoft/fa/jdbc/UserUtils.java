package vn.com.fsoft.fa.jdbc;

import java.util.ArrayList;
import java.util.List;

public class UserUtils {
	public static List<Student> init() {
		List<Student> students = new ArrayList<Student>();
		for (int i = 0; i < 10000; i++) {
			students.add(new Student("name" + i, 20, "address" + i));
		}

		return students;
	}
}
