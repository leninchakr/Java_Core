package section_7;

import java.util.List;
import java.util.function.Consumer;

import section_7.data.Student;
import section_7.data.StudentDataBase;

/**
 * Real-Time Usage of @Consumer<T>
 */
public class S7_L15_Consumer_1 {

	public static void main(String[] args) {

		// Single Consuming
		printName();

		System.out
				.println("-------------------");

		//	Cascading-Consuming
		printNameActivities();

		System.out
				.println("-------------------");
	}

	/**
	 * Just Print Name only
	 */
	public static void printName() {

		List<Student> studentsList = StudentDataBase
				.getAllStudents();

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

		List<Student> studentsList = StudentDataBase
				.getAllStudents();

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
