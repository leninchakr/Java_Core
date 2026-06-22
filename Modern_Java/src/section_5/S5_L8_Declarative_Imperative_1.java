package section_5;

import java.util.stream.IntStream;

public class S5_L8_Declarative_Imperative_1 {

	public static void main(String[] args) {

		/**
		 * Imperative Style of Programming
		 * 
		 * Idea : It focuses on BOTH what you want to achieve, AND how to do it.
		 */
		int sum = 0;

		for (int i = 0; i <= 100; i++) {

			// Object Mutability happens here. May become problem in Mulit-Thread
			// environment
			sum += i;
		}

		System.out
				.println("Sum using Imperative Approach : " + sum);

		/**
		 * Declarative Style of Programming (Make use of Functions!)
		 *
		 * Idea : It focuses on what you want to achieve, not how to do it.
		 */
		int sum_1 = IntStream
				.rangeClosed(0, 100)
				.parallel()
				.sum();

		System.out
				.println("Sum using Declartive Approach : " + sum_1);

	}
}
