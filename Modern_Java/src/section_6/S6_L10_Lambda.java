package section_6;

import java.util.function.BiFunction;

/**
 * Lambda: 
 * - Anonymous Function 
 * - Used @FunctionalInterface Interface (SAM)
 * 
 * - Lambda = object of a functional interface created implicitly by Java :-)
 */
public class S6_L10_Lambda {

	public static void main(String[] args) {

		BiFunction<Integer, Integer, Integer> sum_func = (x, y) -> {
			return x + y;
		};

		System.out
				.println(sum_func
						.apply(3, 4));
	}

}
