package section_7;

import java.util.function.Predicate;

/**
 * @Predicate
 * 
 * Advantage:	Code-Reusability
 */
public class S7_L17_Predicate {

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
		 * Objective : Check given number is even or not.
		 */

		/**
		 * Step 1: Create Testing Mechanism
		 */
		Predicate<Integer> isEven = o -> o % 2 == 0;

		// Step 2: Use the test-mechanism
		System.out
				.println("Is the 6 Even : " + isEven
						.test(6));
		// Step 2: Use the test-mechanism AGAIN
		System.out
				.println("Is the 7 Even : " + isEven
						.test(7));
	}

	public static void and_method() {

		Predicate<Integer> isEven = o -> o % 2 == 0;
		Predicate<Integer> isDivisibleBy5 = o -> o % 5 == 0;

		System.out
				.println(isEven
						.and(isDivisibleBy5)
						.test(50));

	}

	public static void or_method() {

		Predicate<Integer> isEven = o -> o % 2 == 0;
		Predicate<Integer> isDivisibleBy5 = o -> o % 5 == 0;

		System.out
				.println(isEven
						.or(isDivisibleBy5)
						.test(52));

	}

	public static void negate_method() {

		Predicate<Integer> isEven = o -> o % 2 == 0;
		Predicate<Integer> isDivisibleBy5 = o -> o % 5 == 0;

		System.out
				.println(isEven
						.and(isDivisibleBy5)
						.negate()
						.test(52));

	}
}
