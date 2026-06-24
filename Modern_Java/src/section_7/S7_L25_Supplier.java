package section_7;

import java.util.List;
import java.util.function.Supplier;

import section_7.data.Student;
import section_7.data.StudentDataBase;

/**
 * 	It will provide/produces/supplies data
 * 
 * 	Method:
 * 		-	get()
 */
public class S7_L25_Supplier {

	public static List<Student> studentsList = StudentDataBase
			.getAllStudents();

	public static void main(String[] args) {

		/**
		 * Create a supplier
		 */
		Supplier<List<Student>> data_from_db = () -> {

			/*
			 * Get Data from DB
			 */

			return StudentDataBase
					.getAllStudents();

		};

		/**
		 * Use the value
		 */

		System.out
				.println(data_from_db
						.get());

		/**
		 * 
		 */
		data_from_db
				.get()
				.stream()
				.forEach(o -> System.out
						.println(o));
	}
}
