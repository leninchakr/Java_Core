package extra;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Predicate;

import section_7.data.Student;
import section_7.data.StudentDataBase;

/**
 * @Function(Input_Type, Input_Type, Output_Type)
 * 
 * Conversion of a Object
 * 
 * Input:
 * 	-	Two Objects
 * Output
 * 	-	A Function !!!!!
 * 	-	No Object
 * 
 * Methods:
 * 	-	.apply(T)
 * 	-	.andThen(F<T>)
 */
public class BiFunction_Full {

	public static List<Student> studentsList = StudentDataBase
			.getAllStudents();

	/**
	 * @BiFunction
	 * 
	 * 	Input-1	:	@List<Student>	
	 * 	Input-2	:	@Pridicate
	 * 	Output	:	Map<name, GPA>
	 */
	public static Predicate<Student> filter_data = o -> o
			.getGradeLevel() >= 3;

	public static BiFunction<List<Student>, Predicate<Student>, Map<String, Double>> bi_function_grade_3_4 = (o1,
			o2) -> {

		Map<String, Double> myMap = new HashMap<>();

		o1
				.forEach(o -> {

					if (o2
							.test(o)) {
						myMap
								.put(o
										.getName(),
										o
												.getGpa());
					}
				});

		return myMap;

	};

	public static void main(String[] args) {

		bi_fuction_practical_example();
	}

	private static void bi_fuction_practical_example() {

		/**
		 * Get Result from @BiFunction using .apply(o1,o2)
		 */
		Map<String, Double> result = bi_function_grade_3_4
				.apply(studentsList, filter_data);

		System.out
				.println(result);

	}
}
