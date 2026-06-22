package section_6;

import java.util.Comparator;

/**
 * Like @Runnable, implement for @Comparator
 * 
 * -	Compare Two-Objects
 */
public class S6_L12_Implement_Comparator_Lambda {

	public static void main(String[] args) {

		Employee emp_1 = new Employee();
		emp_1.empId = "123";
		emp_1.age = 27;

		Employee emp_2 = new Employee();
		emp_2.empId = "456";
		emp_2.age = 18;

		/**
		 * Approach 1 : Prior to Java 8 : AIC-Approach (Legacy Way)
		 */
		Comparator<Employee> my_comparator = new Comparator<Employee>() {

			/**
			 * Comparison Logic : ANY LOGIC
			 */
			@Override
			public int compare(Employee emp1, Employee emp2) {

				if (emp1.age > emp2.age) {
					return 1;
				}

				return 0;
			}
		};

		System.out
				.println("is_emp1_elder (AIC-Approach)    ? " + my_comparator
						.compare(emp_1, emp_2));

		/**
		 * Approach 2 : Java 8 Lambda Syntax : Lamdba-Approach
		 * 
		 * "(emp1, emp2) -> emp1.age > emp2.age" lamda-expression gives Object! 
		 */
		Comparator<Employee> my_comparator_lambda = (emp1, emp2) -> {

			/**
			 * Comparison Logic : ANY LOGIC
			 */
			if (emp1.age > emp2.age) {
				return 1;
			}

			return 0;
		};

		System.out
				.println("is_emp1_elder (Lamdba-Approach) ? " + my_comparator_lambda
						.compare(emp_1, emp_2));

		/**
		 * Practical Approach ! :-)
		 * 
		 * o1.compareTo(o2) 
		 * 	-> o1 == o2 : Returns 0
		 * 	-> o1 > o2  : Returns 1
		 * 	-> o1 < o2  : Returns -1
		 */
		Comparator<Employee> my_comparator_lambda_new = (emp1, emp2) -> (emp1.age
				.compareTo(emp2.age));

		System.out
				.println("is_emp1_elder (Lamdba-Approach - Shorter!) ? " + my_comparator_lambda_new
						.compare(emp_1, emp_2));
	}
}

/**
 * For Object
 */
class Employee {
	public String empId;
	public Integer age;
}
