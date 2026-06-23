package extra;

import java.security.KeyStore.Entry;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * @BiConsumer is most useful when you genuinely have two independent inputs
 */
public class BiConsumer_Learnin_Map {

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
