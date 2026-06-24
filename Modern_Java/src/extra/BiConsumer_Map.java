package extra;

import java.util.Map;
import java.util.function.BiConsumer;

/**
 * @BiConsumer 
 * 	
 * -	It is most useful when you genuinely have two independent inputs
 * 
 * User Cases
 * 
 * 	-	It is most used  in Processing a Map (Most Common)
 * 	-	Logging Changes
 */
public class BiConsumer_Map {

	/**
	 * Inputs
	 */
	public static Map<String, Double> gpas = Map
			.of("John", 3.8, "Mary", 3.9);

	public static void main(String[] args) {

		/**
		 * Simple Consumer
		 */
		biConsumer_List_Object();

		/**
		 * Cascading Consumers
		 */
		biConsumer_List_Object_chain();

	}

	public static void biConsumer_List_Object() {

		/**
		 * Step 1: Create @BiConsumer
		 */
		BiConsumer<String, Double> bi_consume = (o1, o2) -> System.out
				.println(o1 + " - " + o2);

		/**
		 * Step 2: Consumer the @BiConsumer
		 */
		gpas
				.forEach((key, value) -> bi_consume
						.accept(key, value));

	}

	public static void biConsumer_List_Object_chain() {

		/**
		 * Step 1: Create @BiConsumer
		 */
		BiConsumer<String, Double> printName = (name, gpa) -> System.out
				.println(name);

		/**
		 * Step 2: Create another @BiConsumer
		 */
		BiConsumer<String, Double> printGpa = (name, gpa) -> System.out
				.println(gpa);

		/**
		 * Step 3: Combine both @BiConsumer's
		 */
		BiConsumer<String, Double> combined = printName
				.andThen(printGpa);

		/**
		 * OR
		 */
		gpas
				.forEach(combined);
	}
}
