package extra;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import section_7.data.Student;
import section_7.data.StudentDataBase;

public class BiConsumer_List_Objects {

	public static List<Student> studentsList = StudentDataBase
			.getAllStudents();

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
		BiConsumer<Student, List<String>> bi_consume = (o1, o2) -> {
			System.out
					.println(o1
							.getName() + " - " + o2);
		};

		/**
		 * Step 2:	Create @Consumer and make it call @BiConsumer
		 */
		Consumer<Student> consume_forEach = (o) -> bi_consume
				.accept(o, o
						.getActivities());

		/**
		 * Step 3: Utilize it
		 */
		studentsList
				.stream()
				.forEach(consume_forEach);
	}

	public static void biConsumer_List_Object_chain() {

		/**
		 * Step 1: Create @BiConsumer
		 */
		BiConsumer<Student, Student> bi_consume_basic_info = (o1, o2) -> {
			System.out
					.println(o1
							.getName() + " - "
							+ o2
									.getGender());
		};

		/**
		 * Step 2: Create another @BiConsumer
		 */
		BiConsumer<Student, Student> bi_consume_activites = (o1, o2) -> {
			System.out
					.println(o1
							.getGpa() + " - "
							+ o2
									.getActivities());
		};

		/**
		 * Step 3: Create Chain of @BiConsumer's
		 */
		BiConsumer<Student, Student> bi_consum_chained = bi_consume_basic_info
				.andThen(bi_consume_activites);

		/**
		 * Step 4: Create @Consumer wrapper
		 */
		Consumer<Student> wrapper_consumer = o -> bi_consum_chained
				.accept(o, o);

		/**
		 * Step 5: Process Data
		 */
		studentsList
				.forEach(wrapper_consumer);

	}
}
