package section_7;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

import section_7.data.Student;
import section_7.data.StudentDataBase;

/**
 * @Function(Input_Type, Output_Type)
 * 
 * Real-world Example:
 * 
 * 	-	Convert @List<Student> to @Map<String, Double>
 * 
 * 	-	Map<Name, GPA>
 */
public class S7_L22_Function {

	public static List<Student> studentsList = StudentDataBase
			.getAllStudents();

	public static void main(String[] args) {

		basic_strucure();

		full_implementation();

		full_implementation_with_predicate();

	}

	private static void full_implementation_with_predicate() {

		/**
		 * Objective:	Convert @List to @Map
		 * 
		 * Filter: During Conversion Filter Data
		 * 
		 */

		Function<List<Student>, Map<String, Double>> list_to_map = object -> {

			Map<String, Double> student_gpa_map = new HashMap<>();

			Predicate<Student> grade_3 = o -> o
					.getGradeLevel() == 3;

			object
					.forEach(obj -> {

						if (grade_3
								.test(obj)) {

							student_gpa_map
									.put(obj
											.getName(),
											obj
													.getGpa());
						}

					});

			return student_gpa_map;
		};

		/**
		 * Use the .apply() the @List to Get the converted @Result
		 */
		Map<String, Double> result = list_to_map
				.apply(studentsList);

		System.out
				.println(result);

	}

	private static void full_implementation() {

		/**
		 * Objective:	Convert @List to @Map
		 * 
		 */

		Function<List<Student>, Map<String, Double>> list_to_map = object -> {

			Map<String, Double> student_gpa_map = new HashMap<>();

			object
					.forEach(obj -> student_gpa_map
							.put(obj
									.getName(),
									obj
											.getGpa()));

			return student_gpa_map;
		};

		/**
		 * Use the .apply() the @List to Get the converted @Result
		 */
		Map<String, Double> result = list_to_map
				.apply(studentsList);

		System.out
				.println(result);

	}

	private static void basic_strucure() {

		Function<List<Student>, Map<String, Double>> list_to_map = o -> {

			Map<String, Double> student_gpa_map = new HashMap<>();

			/**
			 *	Create Data for Return! 
			 */

			return student_gpa_map;
		};
	}
}
