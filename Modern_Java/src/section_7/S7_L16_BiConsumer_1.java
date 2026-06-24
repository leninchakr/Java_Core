package section_7;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import section_7.data.Student;
import section_7.data.StudentDataBase;

/**
 * @Consumer
 * 
 * -	Takes TWO Objects Input & Consumes it
 * 
 * -	Generally, it passed to @List's .forEach()
 * 			-	this calls .accept(T) inside.
 * 
 * -	Methods:
 * 			-	.accept(T)
 * 			-	.andThen(T)
 * 
 */
public class S7_L16_BiConsumer_1 {

	public static List<Student> stud_list = StudentDataBase
			.getAllStudents();

	public static void main(String[] args) {

		// Simple Consumer
		simple_bi_consumer();
		System.out
				.println("-------------------------------");
		cascading_bi_consumer();

	}

	public static void simple_bi_consumer() {

		/**
		 * Step 1: Define A @BiConsumer
		 */
		BiConsumer<Student, Student> how_process_two_objects = (o1, o2) -> System.out
				.println(o1
						.getName()
						.toUpperCase() + " -> "
						+ o2
								.getActivities());

		/**
		 * Step 2: Create @Consumer Wrapper to use it on @List's .forEach()
		 */
		Consumer<Student> wrapper = o -> how_process_two_objects
				.accept(o, o);

		/**
		 * Step 3: Consume List Data
		 */
		stud_list
				.forEach(wrapper);

	}

	private static void cascading_bi_consumer() {

		/**
		 * Step 1: Define A @BiConsumer
		 */
		BiConsumer<Student, Student> how_process_two_objects_1 = (o1, o2) -> System.out
				.println(o1
						.getName()
						.toUpperCase() + " -> "
						+ o2
								.getActivities());

		/**
		 * Step 2: Define Another @BiConsumer
		 */
		BiConsumer<Student, Student> how_process_two_objects_2 = (o1, o2) -> System.out
				.println(o1
						.getGender() + " -> "
						+ o2
								.getGpa());

		/**
		 * Step 3: Create @Consumer Wrapper to use it on @List's .forEach()
		 */
		Consumer<Student> wrapper = o -> how_process_two_objects_1
				.andThen(how_process_two_objects_2)
				.accept(o, o);

		/**
		 * Step 4: Consume List Data
		 */
		stud_list
				.forEach(wrapper);

	}
}
