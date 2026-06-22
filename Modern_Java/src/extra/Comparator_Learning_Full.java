package extra;

import java.util.Comparator;
import java.util.function.Function;

public class Comparator_Learning_Full {

	public static void main(String[] args) {

		Employee emp_1 = new Employee();
		emp_1.empId = "123";
		emp_1.age = 27;

		Employee emp_2 = new Employee();
		emp_2.empId = "456";
		emp_2.age = 18;

		/**
		 * Comparator in Action!
		 */
		Comparator<Employee> my_cust_comprator = (o1, o2) -> Integer
				.compare(o1.age, o2.age);

		/**
		 * Lets use all 'Static' Methods
		 */
		/**
		 * Static 1 : Comparator comparing(keyExtractor, keyComparator)
		 */

		/**
		 * 
		 */

	}

}

/**
 * For Object
 */
class Employee {
	public String empId;
	public Integer age;
}
