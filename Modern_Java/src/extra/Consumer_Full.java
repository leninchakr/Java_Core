package extra;

import java.util.List;
import java.util.function.Consumer;

import section_7.data.Student;
import section_7.data.StudentDataBase;

/**
 * Consumer has only TWO methods
 * 
 * 1.	.accept(T)	
 * 			-	Abstract Method
 * 			-	Tell How to consume the data
 * 			-	IMPORTANT: Accept SINGLE input
 * 
 * 2.	.andThen(@Consumer c)
 * 			-	Cascading Consuming
 * 
 */
public class Consumer_Full {

	public static List<Student> studentsList = StudentDataBase
			.getAllStudents();

	public static void main(String[] args) {

		// Single Consuming
		printName();

		System.out
				.println("-------------------");

		//	Cascading-Consuming
		printNameActivities();

		System.out
				.println("-------------------");

		/**
		 * Practical Approach :)
		 */
		studentsList
				.forEach(o -> System.out
						.println(o));
	}

	/**
	 * Just Print Name only
	 */
	public static void printName() {

		/**
		 * Step 1: Create a Consumer {How we are going to consume the data}
		 */
		Consumer<Student> consume_data = o -> System.out
				.println(o
						.getName());

		/**
		 * Step 2: Use the @Consumer<Studnet> to process the data
		 */
		studentsList
				.forEach(consume_data);

	}

	/**
	 * Just Print Name & List of Activities
	 */
	public static void printNameActivities() {

		/**
		 * Step 1: Create a Consumer {How we are going to consume the data}
		 */
		Consumer<Student> consume_name = o -> System.out
				.print(o
						.getName() + " ");
		Consumer<Student> consume_activites = o -> System.out
				.println(o
						.getActivities());

		/**
		 * Step 2: Use the @Consumer<Studnet> to process the data
		 */
		studentsList
				.forEach(consume_name
						.andThen(consume_activites));

	}
}
