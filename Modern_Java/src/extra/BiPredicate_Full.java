package extra;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

import section_7.data.Student;
import section_7.data.StudentDataBase;

/**
 * @BiPredicate
 * 
 * Methods:
 * 	-	.test(T)		-	Abstract Method
 * 	-	.and(P<T>)
 * 	-	.or(P<T>)
 * 	-	.negate(P<T>)
 */
public class BiPredicate_Full {

	public static List<Student> studentsList = StudentDataBase
			.getAllStudents();

	public static void main(String[] args) {

		System.out
				.println("---------- .test() ----------");
		test_method();

		System.out
				.println("---------- .and() ----------");
		and_method();

		System.out
				.println("---------- .or() ----------");
		or_method();

		System.out
				.println("---------- .negate() ----------");
		negate_method();
	}

	private static void negate_method() {

	}

	private static void or_method() {

	}

	private static void and_method() {

	}

	private static void test_method() {

		/**
		 * Step 1:
		 */
		BiPredicate<Student, Double> is_grade_level_above_3 = (o, gpa) -> o
				.getGradeLevel() >= 3 && gpa >= 3.8;

		/**
		 * Step 2:
		 */
		Consumer<Student> wrapper = o -> {

			Boolean status = is_grade_level_above_3
					.test(o, o
							.getGpa());

			if (status) {
				System.out
						.println(o);
			}
		};

		/**
		 * Step 3:
		 */
		// Final : 
		studentsList
				.forEach(wrapper);
	}

}
