package section_7;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import section_7.data.Student;
import section_7.data.StudentDataBase;

/**
 * @Function(Input_Type, Output_Type)
 * 
 * Conversion of a Object
 * 
 * Input:
 * 	-	Single Object
 * Output
 * 	-	A Function !!!!!
 * 	-	No Object
 * 
 * Methods:
 * 	-	.apply(T)
 * 	-	.compose(F<T>)
 * 	-	.andThen(F<T>)
 * 	-	.identity()
 */
public class S7_L21_Function {

	public static List<Student> studentsList = StudentDataBase
			.getAllStudents();

	public static void main(String[] args) {

		System.out
				.println("--------- .apply() ------------");
		apply_method();

		System.out
				.println("--------- .compose() ------------");
		compose_method();

		System.out
				.println("--------- .andThen() ------------");
		andThen_method();
	}

	private static void andThen_method() {

		// Step 1: Conversion : Student -> String (Upper)
		Function<Student, String> extract_name_upper = o -> o
				.getName()
				.toUpperCase();

		// Step 2: Conversion : String(Upper) -> String(Lower)
		Function<String, String> extract_name_lower = o -> o
				.toLowerCase();

		// Step 2:
		Function<Student, String> composed = extract_name_upper
				.andThen(extract_name_lower);

		// 
		studentsList
				.forEach(o -> System.out
						.println(composed
								.apply(o)));
	}

	private static void compose_method() {

		// Step 1: Conversion : Student -> String (Upper)
		Function<Student, String> extract_name_upper = o -> o
				.getName()
				.toUpperCase();

		// Step 2: Conversion : String(Upper) -> String(Lower)
		Function<String, String> extract_name_lower = o -> o
				.toLowerCase();

		// Step 2:
		Function<Student, String> composed = extract_name_lower
				.compose(extract_name_upper);

		// 
		studentsList
				.forEach(o -> System.out
						.println(composed
								.apply(o)));

	}

	private static void apply_method() {
		// Step 1: 
		Function<Student, String> extract_name = o -> o
				.getName()
				.toUpperCase();

		// 
		studentsList
				.forEach(o -> System.out
						.println(extract_name
								.apply(o)));
	}
}
