package section_8;

import java.util.List;
import java.util.function.Function;

import section_7.data.Student;
import section_7.data.StudentDataBase;

public class S8_L27_Method_Reference_With_Function {

	public static List<Student> studentsList = StudentDataBase
			.getAllStudents();

	public static void main(String[] args) {

		/**
		 * Lambda Express
		 */
		Function<String, String> to_upper = o -> o
				.toUpperCase();

		System.out
				.println(to_upper
						.apply("abc"));

		/**
		 * Lambda Express With Method-Reference
		 */
		Function<String, String> to_upper_MR = String::toUpperCase;

		System.out
				.println(to_upper_MR
						.apply("abc"));
	}
}
