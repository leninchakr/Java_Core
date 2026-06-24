package section_7;

import java.util.List;
import java.util.function.Predicate;

import section_7.data.Student;
import section_7.data.StudentDataBase;

/**
 * Predicate in Real-World-Data
 */
public class S7_L18_Predicate {

	public static List<Student> studentsList = StudentDataBase
			.getAllStudents();

	public static void main(String[] args) {

		System.out
				.println("--------- .test(T) -----------");
		test_method();

		System.out
				.println("--------- .and(P<T>) -----------");
		and_method();

		System.out
				.println("--------- .or(P<T>) -----------");
		or_method();

		System.out
				.println("--------- .negate(P<T>) -----------");
		negate_method();
	}

	public static void test_method() {

		/**
		 * Objective : Check, if the students 
		 * 
		 * -	grade level is >= 3
		 */

		// Step 1: test-Mechanism
		Predicate<Student> is_Grade_Level_Above_3 = o -> o
				.getGradeLevel() >= 3;

		studentsList
				.forEach(o -> {

					Boolean state = is_Grade_Level_Above_3
							.test(o);

					if (state) {
						System.out
								.println(o);
					}
				});

	}

	public static void and_method() {

		/**
		 * Objective : Check, if the students 
		 * 
		 * 	-	grade level is >= 3
		 * 	-	gpa >= 3.9
		 */

		// Step 1: test-Mechanism
		Predicate<Student> is_Grade_Level_Above_3 = o -> o
				.getGradeLevel() >= 3;

		Predicate<Student> is_gpa_above_3_9 = o -> o
				.getGpa() >= 3.9;

		studentsList
				.forEach(o -> {

					// .and(P<T>)
					Boolean state = is_Grade_Level_Above_3
							.and(is_gpa_above_3_9)
							.test(o);

					if (state) {
						System.out
								.println(o);
					}
				});
	}

	public static void or_method() {

		/**
		 * Objective : Check, if the students 
		 * 
		 * 	-	grade level is >= 3
		 * 	-	gpa >= 3.9
		 */

		// Step 1: test-Mechanism
		Predicate<Student> is_male_student = o -> o
				.getGender()
				.equals("male");

		Predicate<Student> is_gpa_above_3_9 = o -> o
				.getGpa() >= 3.9;

		studentsList
				.forEach(o -> {

					// .and(P<T>)
					Boolean state = is_male_student
							.or(is_gpa_above_3_9)
							.test(o);

					if (state) {
						System.out
								.println(o);
					}
				});
	}

	public static void negate_method() {

		/**
		 * Objective : Check, if the students 
		 * 	-	NOT
		 * 	-	grade level is >= 3
		 * 	-	gpa >= 3.9
		 */

		// Step 1: test-Mechanism
		Predicate<Student> is_Grade_Level_Above_3 = o -> o
				.getGradeLevel() >= 3;

		Predicate<Student> is_gpa_above_3_9 = o -> o
				.getGpa() >= 3.9;

		studentsList
				.forEach(o -> {

					// .and(P<T>)
					Boolean state = is_Grade_Level_Above_3
							.and(is_gpa_above_3_9)
							.negate()
							.test(o);

					if (state) {
						System.out
								.println(o);
					}
				});
	}

}
