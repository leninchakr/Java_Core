package section_7;

import java.util.List;
import java.util.function.Consumer;

import section_7.data.Student;
import section_7.data.StudentDataBase;

/**
 * @Consumer
 * 
 * -	Take ONE-ONLY Object & Consumes it
 * 
 * -	Generally, it passed to @List's .forEach()
 * 			-	this calls .accept(T) inside.
 * 
 * -	Methods:
 * 			-	.accept(T)
 * 			-	.andThen(T)
 * 
 */
public class S7_L15_Consumer_2 {

	public static List<Student> stud_list = StudentDataBase
			.getAllStudents();

	public static void main(String[] args) {

		// Simple Consumer
		simple_consumer();
		System.out
				.println("-------------------------------");
		cascading_consumer();
	}

	public static void simple_consumer() {

		/**
		 * Step 1: Define A @Consumer
		 */
		Consumer<Student> how_process_data = o -> System.out
				.println(o
						.getName()
						.toUpperCase());

		/**
		 * Step 2: Consume List Data
		 */
		stud_list
				.forEach(how_process_data);
	}

	public static void cascading_consumer() {

		/**
		 * Step 1: Define First-Consumer
		 */
		Consumer<Student> how_process_Name = o -> System.out
				.print(o
						.getName()
						.toUpperCase() + " - ");

		/**
		 * Step 2: Define Second-Consumer
		 */
		Consumer<Student> how_process_Activities = o -> System.out
				.println(o
						.getActivities());

		/**
		 * Step 3: Create Combined Consumer
		 */
		Consumer<Student> combined = how_process_Name
				.andThen(how_process_Activities);

		/**
		 * Step 4: Process Data
		 */
		stud_list
				.forEach(combined);

		/**
		 * Step 5: Process Data (Practical Way)
		 */
		stud_list
				.forEach(how_process_Name
						.andThen(how_process_Activities));

	}
}
