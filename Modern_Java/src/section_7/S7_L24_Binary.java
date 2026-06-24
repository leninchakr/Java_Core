package section_7;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;

import section_7.data.Student;
import section_7.data.StudentDataBase;

/**
 * @UnaryOperator
 * 
 * 	-	Implements @BiFunction<T,T,T> interface
 * 	-	Methods:
 * 			-	minBy( @Compartor<T>)
 * 			-	maxBy( @Compartor<T>)
 * 			- 	and Methods from @BiFunction<T,T,T>
 * 
 * 	-	USE-CASE:	Both Inputs & Output Type are Same
 */
public class S7_L24_Binary {

	public static List<Student> studentsList = StudentDataBase
			.getAllStudents();

	public static void main(String[] args) {

		ex_1();
		ex_2();

		common_use_with_stream();

		maxBy_method();
		minBy_method();
	}

	private static void minBy_method() {

		/**
		 * Objective : Get SMALLER of two things
		 * 
		 * -	@BinaryOperator can do it. 
		 * -	But it does know how to compare for MIN
		 * -	Tell to it via an @Compartor
		 */

		List<Integer> nums = List
				.of(4, 2, 3, 1, 5, 6);

		/**
		 * Step 1: Say how to find SMALLER for given object
		 */
		Comparator<Integer> final_SMALLER_logic = (o1, o2) -> Integer
				.compare(o1, o2);

		/**
		 * Step 2: Tell @BinaryOperator how to compare two things for SMALLER
		 */
		BinaryOperator<Integer> smaller_bi_op = BinaryOperator
				.minBy(final_SMALLER_logic);

		/**
		 * 
		 */
		Optional<Integer> smaller_value = nums
				.stream()
				.reduce(smaller_bi_op);

		/**
		 * 
		 */
		System.out
				.println("Smaller Value : " + smaller_value
						.get());
	}

	private static void maxBy_method() {

		/**
		 * Objective : Get LARGER of two things
		 * 
		 * -	@BinaryOperator can do it. 
		 * -	But it does know how to compare for MAX
		 * -	Tell to it via an @Compartor
		 */

		List<Integer> nums = List
				.of(4, 2, 3, 1, 5, 6);

		/**
		 * Step 1: Say how to find SMALLER for given object
		 */
		Comparator<Integer> final_SMALLER_logic = (o1, o2) -> Integer
				.compare(o1, o2);

		/**
		 * Step 2: Tell @BinaryOperator how to compare two things for SMALLER
		 */
		BinaryOperator<Integer> smaller_bi_op = BinaryOperator
				.maxBy(final_SMALLER_logic);

		/**
		 * 
		 */
		Optional<Integer> smaller_value = nums
				.stream()
				.reduce(smaller_bi_op);

		/**
		 * 
		 */
		System.out
				.println("Larger Value : " + smaller_value
						.get());
	}

	private static void common_use_with_stream() {

		/**
		 * Do Summation of List of Number
		 */

		List<Integer> nums = List
				.of(1, 2, 3, 4, 5, 6);

		/**
		 * Step 1: Sum of Two Integers
		 */
		BinaryOperator<Integer> bi_op_sum = (a, b) -> a + b;

		Optional<Integer> result = nums
				.stream()
				.reduce(bi_op_sum);

		System.out
				.println(result
						.isPresent()
								? result
										.get()
								: "NA");

	}

	private static void ex_2() {
		/**
		 * Objective : Join Two Strings
		 * 
		 * Inputs 	: 	Two Strings
		 * Output	:	A String
		 */

		BinaryOperator<String> concat = (s1, s2) -> s1 + s2;

		String result = concat
				.apply("ABC", "DEF");

		System.out
				.println(result);
	}

	private static void ex_1() {

		/**
		 * Objective : Multiple Two Number
		 * 
		 * Inputs 	: 	Two Integers
		 * Output	:	A Integer
		 */

		BinaryOperator<Integer> bin_op_mul = (a, b) -> a * b;

		Integer result = bin_op_mul
				.apply(5, 7);

		System.out
				.println(result);
	}
}
