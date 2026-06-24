package section_8;

import java.util.List;
import java.util.function.Consumer;

import section_7.data.Student;
import section_7.data.StudentDataBase;

public class S8_L28_Method_Reference_With_Consumer {

	public static List<Student> studentsList = StudentDataBase
			.getAllStudents();

	public static void main(String[] args) {

		/**
		 * Sample @Consumer Interface implementation
		 */
		Consumer<Student> consume_data = o -> System.out
				.println(o
						.getActivities());

		/**
		 * 
		 */
		studentsList
				.forEach(consume_data);

		/**************************************************/

		/**
		 * Using Method-Reference
		 */
		Consumer<Student> consume_data_mr = System.out::println;

		/**
		 * 
		 */
		studentsList
				.forEach(consume_data_mr);

		/**************************************************/

		/**
		 * Using Method-Reference
		 */
		Consumer<Student> consume_data_mr_more = Student::getActivities;

		/**
		 * 
		 */
		studentsList
				.forEach(consume_data_mr_more);
	}
}
