package extra;

import java.util.function.BiConsumer;

/**
 * Consumer has only TWO methods
 * 
 * 1.	.accept(T, U)	
 * 			-	Abstract Method
 * 			-	Tell How to consume the data
 * 			-	IMPORTANT: Accept TWO inputs
 * 
 * 2.	.andThen(@BiConsumer bc)
 * 			-	Cascading Consuming
 * 
 */
public class BiConsumer_Learning_Simple {

	public static void main(String[] args) {

		/**
		 * Simple Usage of @BiConsumer
		 */
		simple_biConsumer_String();
		simple_biConsumer_Integer();
		simple_biConsimer_andThen();

	}

	public static void simple_biConsumer_String() {

		/**
		 * Step 1: Create Bi-Consumer
		 */
		BiConsumer<String, String> bi_consumer_add_string = (o1, o2) -> System.out
				.println(o1 + " - " + o2);

		/**
		 * Step 2: Consume Data
		 */
		bi_consumer_add_string
				.accept("Java", "1.8");
	}

	public static void simple_biConsumer_Integer() {
		/**
		 * Step 1: Create @BiConsumer
		 */
		BiConsumer<Integer, Integer> bi_consumer_multiple_integer = (o1, o2) -> System.out
				.println("Mutiplication of " + o1 + " and " + o2 + " : " + o1 * o2);

		/**
		 * Step 2: Process the Data with the consumer
		 */
		bi_consumer_multiple_integer
				.accept(4, 7);

	}

	public static void simple_biConsimer_andThen() {

		/**
		 * Step 1: Create @BiConsumer
		 */
		BiConsumer<Integer, Integer> multiply = (o1, o2) -> System.out
				.println(o1 * o2);

		BiConsumer<Integer, Integer> divide = (o1, o2) -> System.out
				.println(o1 / o2);

		/**
		 * Step 2: Create Chain of @BiConsumer's
		 * 
		 * (l,r) -> {
		 * 
		 * 		multiply.accept(l,r);
		 * 		divide.accept(1,r)
		 * }
		 * 
		 * this is .accept() implementation for final 'combined' @BiConsumer
		 * 
		 */
		BiConsumer<Integer, Integer> combined = multiply
				.andThen(divide);

		/**
		 * Step 3: Process the Data with the Consolidated @BiConsumer's 
		 * 
		 * 	-	with .accept(t,u)
		 * 
		 * 	-	Chain all consumers
		 * 	-	Pass inputs at end with .apply(T,U)
		 * 
		 * (85,4) -> {
		 * 
		 * 		multiply.accept(85,4);
		 * 		divide.accept(85,4)
		 * }
		 * 
		 * Wow!!!!!!!!!!!!!!!
		 * 
		 */
		combined
				.accept(84, 4);
	}

}
