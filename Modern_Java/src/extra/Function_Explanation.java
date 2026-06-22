package extra;

import java.util.function.Function;

/**
 * Function<T, R> is a Functional Interface in Java.
 *
 * It represents a pure transformation (mapping):
 *      Input (T)  → Output (R)
 *
 * Key idea:
 *      - It does NOT modify the original object
 *      - It produces a new result based on input
 *
 * Common analogy:
 *      Stream.map()
 */
public class Function_Explanation {

	public static void main(String[] args) {

		/**
		 * Sample Employee objects
		 */
		Employee_New emp_1 = new Employee_New();
		emp_1.empId = "123";
		emp_1.age = 27;
		emp_1.salary = 1530.0;

		Employee_New emp_2 = new Employee_New();
		emp_2.empId = "456";
		emp_2.age = 18;
		emp_2.salary = 1500.0;

		/**
		 * Function<Employee_New, Integer>
		 *
		 * Meaning:
		 *      Input  : Employee_New
		 *      Output : Integer
		 *
		 * Purpose:
		 *      Extracts age from Employee object
		 */
		Function<Employee_New, Integer> my_map_expanded = (Employee_New o) -> (Integer) o.age;

		/**
		 * Type inference example:
		 *
		 * Java automatically infers the parameter type
		 * from the left-hand side declaration.
		 */
		// Important : .apply() method's definition is said here
		Function<Employee_New, Integer> my_map_auto_type_match = (o) -> o.age;

		/**
		 * ================================
		 * [1] Applying a Function:
		 * ================================
		 *
		 * .apply(input) executes the transformation
		 */
		// Important : .apply(emp_1) method's call is happening here
		System.out
				.println("Age of the Employee 2 is " + my_map_auto_type_match
						.apply(emp_2));

		/**
		 * ================================
		 * [2] FUNCTION COMPOSITION (compose)
		 * ================================
		 *
		 * Definition:
		 *      f.compose(g) = x -> f(g(x))
		 *
		 * Execution order:
		 *      1. g runs FIRST (before function)
		 *      2. f runs SECOND (current function)
		 *
		 * Think:
		 *      RIGHT → LEFT execution
		 *
		 * --------------------------------
		 * Example pipeline:
		 *
		 * 1. double_the_salary : Employee_New → Double
		 * 2. deduct_10_percent : Double → Double
		 *
		 * Final flow:
		 *      Employee_New → Double → Double
		 *      (salary × 2) → (reduce 10%)
		 */
		Function<Employee_New, Double> double_the_salary = (o) -> o.salary * 2.0;

		Function<Double, Double> deduct_10_percent = (o) -> o * 0.9;

		/**
		 * Function Composition:
		 *
		 * deduct_10_percent.compose(double_the_salary)
		 *
		 * Means:
		 *      deduct_10_percent(double_the_salary(emp))
		 *
		 * Execution steps:
		 *      Employee_New
		 *          ↓
		 *      double_the_salary (first executed)
		 *          ↓
		 *      Double (intermediate result)
		 *          ↓
		 *      deduct_10_percent (second executed)
		 *          ↓
		 *      Final Double result
		 */
		// Important : It is 'instance' method-call
		Function<Employee_New, Double> final_salary_compose = deduct_10_percent
				.compose(double_the_salary);

		/**
		 * What Happen when we do "deduct_10_percent.compose(double_the_salary)"
		 * 
		 * 	Step 1 : 'this' 	= deduct_10_percent	<Double, Double>		:	.apply() is declared Explicitly before :-)
		 *  Step 2 : 'before'	= double_the_salary <Employee_New, Double>	:	.apply() is declared Explicitly before :-)
		 * 
		 * 		returns (V v) -> this.apply(before.apply(v));
		 * 
		 * Step 3 :	
		 * 
		 * 		returns (Employee_New v) -> this.apply(before.apply(v));
		 * 
		 * Step 4 :
		 * 	
		 * 		returns (Employee_New v) -> ()		
		 * 			:	Lambda Expression. This sets .apply() by "Type-Caste!!!" for 3rd Function<Employee_New, Double> final_salary_compose.
		 * 
		 * Step 5: Finally use 3_rd_function.apply(input) !!
		 * 
		 */

		/**
		 * Apply composed function
		 */
		System.out
				.println("New Salary of Employee 1 by .compose() : " + final_salary_compose
						.apply(emp_1));

		/**
		 * ================================
		 * 3. FUNCTION COMPOSITION (andThen)
		 * ================================
		 *
		 * Opposite of compose()
		 * 
		 * Why This : It’s easier to read mentally.
		 *
		 * Definition:
		 *      f.andThen(g) = x -> g(f(x))
		 *
		 * Execution order:
		 *      1. f runs FIRST
		 *      2. g runs SECOND
		 *
		 * --------------------------------
		 * Example pipeline:
		 *
		 * 1. double_the_salary : Employee_New → Double
		 * 2. deduct_10_percent : Double → Double
		 *
		 * Final flow:
		 *      Employee_New → Double → Double
		 *      (salary × 2) → (reduce 10%)
		 */
		// Important : It is 'instance' method-call
		Function<Employee_New, Double> final_salary_andThen = double_the_salary
				.andThen(deduct_10_percent);

		/**
		 * What Happen when we do "double_the_salary.andThen(deduct_10_percent)"
		 * 
		 * Step 1 : this 	= "double_the_salary" <Employee_new, Double>
		 * Step 2 : after 	= "deduct_10_percent"	<Double, Double>
		 * 
		 * Step 3 : return Syntax of "double_the_salary"
		 * 
		 * 				return (T t) -> after.apply(this.apply(t)); // From "double_the_salary". So T=Employee_new
		 * 
		 * 				return (Employee_new t) -> after.apply(this.apply(t))
		 * 
		 * 				this.apply(t) is already created BEFORE :)			----	(1)
		 * 
		 * 				after.apply(...) is also already created BEFORE :)	----	(2)
		 * 
		 * 				(Employee_new t) -> (....) is Lambda-Expression and it is TYPE-CASTED while return for Function<Employee_New, Double>
		 * 			
		 * 					-	This is setting .apply() for 3rd Function !	----	(3)
		 * 
		 * Finally : 	This implicitly defines the .apply() for "final_salary_andThen", WHILE-RETURNING !!
		 * 
		 */

		System.out
				.println("New Salary of Employee 1 by .andThen() : " + final_salary_andThen
						.apply(emp_1));

		/**
		 * ================================
		 * 3. FUNCTION COMPOSITION (identity)
		 * ================================
		 * 
		 * - Returns the same input!
		 * - 'Static' method. So access by @Interface name-itself.
		 * 
		 */
		Function<Employee_New, Employee_New> my_id_func = Function
				.identity();

		System.out
				.println("Identify Static Function 	: " + my_id_func
						.apply(emp_1));
		System.out
				.println("Normal Object 			: " + emp_1);

	}
}

/**
 * Simple POJO (Plain Old Java Object)
 */
class Employee_New {

	public String empId;
	public Integer age;
	public Double salary;
}