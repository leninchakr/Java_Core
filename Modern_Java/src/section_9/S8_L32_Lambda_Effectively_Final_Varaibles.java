package section_9;

import java.util.function.Consumer;

public class S8_L32_Lambda_Effectively_Final_Varaibles {

	public static void main(String[] args) {

		/**
		 * 2.	Lambda-Expression can't change the Local-Variable of the Object
		 *
		 * 	-	'some_local_varaible' is not Final !
		 * 	-	But, treated inside as "Effectively Final"
		 */

		Integer some_local_varaible = 8;

		Consumer<Integer> c2 = o -> {

			some_local_varaible = 7;

			System.out
					.println(o);
		};
	}
}
