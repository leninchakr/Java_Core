package section_7;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import section_7.data.Student;
import section_7.data.StudentDataBase;

/**
 * Predicate in Real-World-Data
 */
public class S7_L19_Predicate_Consumer {

	public static List<Student> studentsList = StudentDataBase
			.getAllStudents();

	public static void main(String[] args) {

		print_name_activities();
	}

	private static void print_name_activities() {
		/**
		 * Objective : Print Students
		 * 	
		 * 	-	AND
		 * 
		 * 	-	Grade >= 3
		 * 	-	GPA >= 3.8
		 */

		// Predicate to Filter 1
		Predicate<Student> is_grade_level_above_3 = o -> o
				.getGradeLevel() >= 3;

		// Predicate to Filter 2
		Predicate<Student> is_gpa_above_3_8 = o -> o
				.getGpa() > 3.8;

		// Final Test-Predicate
		Predicate<Student> combined = is_grade_level_above_3
				.and(is_gpa_above_3_8);

		/**
		 * Create @Consumer
		 */
		Consumer<Student> wrapper = o -> {

			Boolean status = combined
					.test(o);

			if (status) {
				System.out
						.println(o);
			}
		};

		// Final : 
		studentsList
				.forEach(wrapper);

		System.out
				.println("------------------");

		studentsList
				.forEach(o -> {
					Boolean status = combined
							.test(o);

					if (status) {
						System.out
								.println(o);
					}
				});
	}
}
