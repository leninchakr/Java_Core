package extra;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class Comparator_Learning_Full {

	public static void main(String[] args) {

		Employee emp_1 = new Employee();
		emp_1.empId = "Lenin";
		emp_1.age = 27;
		emp_1.salary = 1530.0;

		Employee emp_2 = new Employee();
		emp_2.empId = "Apple";
		emp_2.age = 18;
		emp_2.salary = 1500.0;

		/**
		 * Logic for Comparing Two-Objects
		 * 
		 */
		Comparator<Employee> my_custom_comparator = (o1, o2) -> Integer
				.compare(o1.age, o2.age);

		/**
		 * ================================
		 * Comparator.comparing() concept
		 * ================================
		 *
		 * This is a functional-style way to build comparators.
		 *
		 * It works in 2 steps:
		 *
		 * 1. Key Extraction:
		 *    Function<Employee, Double>
		 *    → Extracts a comparable value (salary) from Employee
		 *
		 * 2. Key Comparison:
		 *    Comparator<Double>
		 *    → Defines how extracted values should be compared
		 *
		 * Final idea:
		 * Employee → extract salary → compare salaries
		 */

		/**
		 * Key Extractor using lambda
		 * Employee → salary
		 */
		Function<Employee, Double> keyExtractor_Varaible_to_Compare = (o) -> o.salary;

		/**
		 * Key Extractor using Method Reference (cleaner form)
		 */
		Function<Employee, Double> keyExtractor_Method_Ref = Employee::getSalary;

		/**
		 * Key Comparator for extracted values (Double)
		 * Defines how two salary values are compared
		 */
		Comparator<Double> keyComparator_Logic = (o1, o2) -> Double
				.compare(o1, o2);

		/**
		 * Better alternative (recommended):
		 * Comparator<Double> keyComparator = Double::compare;
		 */

		/**
		 * Final Comparator Composition:
		 *
		 * Step 1: Extract salary from Employee ::: Keys
		 * Step 2: Compare extracted salary values using keyComparator ::: Values
		 *
		 * Internally equivalent to:
		 *
		 * (e1, e2) ->
		 *     keyComparator.compare(
		 *         keyExtractor.apply(e1),
		 *         keyExtractor.apply(e2)
		 *     )
		 */
		/**
		 * Final Comparator (used with .sort() method) "Two Objects" + With Generic-Custom-Comparator Approach
		 */
		Comparator<Employee> comparator_for_SORT = Comparator
				.comparing(keyExtractor_Varaible_to_Compare, keyComparator_Logic);

		/**
		 * NOTE:
		 * In real-world code, this simplifies to:
		 *
		 * Comparator<Employee> my_comparing_comparator =
		 *     Comparator.comparing(Employee::getSalary);
		 */

		/**
		 * Practical Approach
		 */
		Comparator<Employee> comparator_for_SORT_Practical = Comparator
				.comparing(Employee::getSalary, Double::compare);

		/**
		 * HOW TO USE THE COMPARATOR
		 */
		List<Employee> employees = new ArrayList<>();
		employees
				.add(emp_1);
		employees
				.add(emp_2);

		employees
				.sort(comparator_for_SORT);

		/**
		 * HOW SORTING WORKS IN THIS CODE (USING keyExtractor + keyComparator)
		 *
		 * employees.sort(my_comparing_comparator);
		 *
		 * where:
		 * Comparator<Employee> my_comparing_comparator =
		 *     Comparator.comparing(keyExtractor, keyComparator);
		 *
		 * ---------------------------------------------------
		 * STEP 1: Comparator.compare is called internally
		 * ---------------------------------------------------
		 * Java internally does:
		 *
		 *     my_comparing_comparator.compare(emp1, emp2)
		 *
		 * ---------------------------------------------------
		 * STEP 2: Key Extraction (keyExtractor)
		 * ---------------------------------------------------
		 * keyExtractor = (Employee e) -> e.salary
		 *
		 * So:
		 *
		 *     emp1 → 1530.0
		 *     emp2 → 1500.0
		 *
		 * Now comparison becomes:
		 *
		 *     keyComparator.compare(1530.0, 1500.0)
		 *
		 * ---------------------------------------------------
		 * STEP 3: Key Comparison (keyComparator)
		 * ---------------------------------------------------
		 * keyComparator = Double::compare
		 *
		 * So internally:
		 *
		 *     Double.compare(1530.0, 1500.0)
		 *
		 * Result:
		 *     positive value (> 0)
		 *
		 * ---------------------------------------------------
		 * STEP 4: Meaning of result
		 * ---------------------------------------------------
		 * Comparator rules:
		 *
		 *     negative → first comes before second
		 *     zero     → equal
		 *     positive → first comes after second
		 *
		 * So here:
		 *
		 *     1530.0 > 1500.0
		 *     → emp1 comes AFTER emp2
		 *
		 * ---------------------------------------------------
		 * FINAL SORTED ORDER (ascending salary)
		 * ---------------------------------------------------
		 * Apple  → 1500.0
		 * Lenin  → 1530.0
		 *
		 * ---------------------------------------------------
		 * INTERNAL FORM (IMPORTANT IDEA)
		 * ---------------------------------------------------
		 * This line:
		 *
		 *     Comparator.comparing(keyExtractor, keyComparator)
		 *
		 * is equivalent to:
		 *
		 *     (e1, e2) -> keyComparator.compare(
		 *                     keyExtractor.apply(e1),
		 *                     keyExtractor.apply(e2)
		 *                 )
		 *
		 * ---------------------------------------------------
		 * NOTE:
		 * Java uses TimSort internally:
		 * - Hybrid of Merge Sort + Insertion Sort
		 * - Stable sorting algorithm
		 */

		employees
				.stream()
				.map((obj) -> obj.empId)
				.forEach(System.out::println);

	}
}