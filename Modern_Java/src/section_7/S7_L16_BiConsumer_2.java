package section_7;

import java.util.Map;
import java.util.function.BiConsumer;

/**
 * @BiConsumer works well with @Map
 * 
 * Important:	@Map's .forEach(
 * 							(a,b) -> ...
 * 						) accepts two inputs
 * 
 * 	-	IoW, @Map's .forEach( @BiConsumer )
 */
public class S7_L16_BiConsumer_2 {

	public static Map<String, Double> myMap = Map
			.of("John", 3.8, "Mary", 3.9, "Raju", 3.1);

	public static void main(String[] args) {

		/**
		 * Step 1: Create @BiConsumer
		 */
		BiConsumer<String, Double> consume_map = (o1, o2) -> System.out
				.println(o1 + " -> " + o2);

		/**
		 * Step 2: Consumer Map
		 */
		myMap
				.forEach(consume_map);
	}
}
