package section_7;

import java.util.function.Consumer;

import extra.Employee;

public class S7_L14_Consumer {

	public static void main(String[] args) {

		Employee emp_1 = new Employee();
		emp_1.empId = "Lenin";
		emp_1.age = 27;
		emp_1.salary = 1530.0;

		/**
		 * Step 1: Create Consumer 
		 * 
		 * Instantiate @Consumer Interface
		 * 
		 * This consumer consumes an Object type of 'Student'
		 */
		Consumer<Employee> consume_data = obj -> System.out
				.println(obj.empId
						.toUpperCase());

		/**
		 * Step 2: Use the Consumer using .accept() method
		 */
		consume_data
				.accept(emp_1);

	}

}
