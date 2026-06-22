package extra;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class Comparator_Learning_Full_Ex_1 {

	public static void main(String[] args) {

		Employee emp_1 = new Employee();
		emp_1.empId = "Lenin";
		emp_1.age = 27;
		emp_1.salary = 1530.0;

		Employee emp_2 = new Employee();
		emp_2.empId = "ALokesh";
		emp_2.age = 18;
		emp_2.salary = 1500.0;

		/**
		 * Key extractor (for Comparator usage)
		 */
		Function<Employee, String> keyExtractor = (o) -> o.empId;

		/**
		 * Method reference version (cleaner)
		 */
		Function<Employee, String> keyExtractor_mf = Employee::getEmpId;

		/**
		 * How to compare
		 */
		Comparator<String> keyCompartor = (o1, o2) -> o1
				.charAt(0)
				- o2
						.charAt(0);

		/**
		 * Final Comparator
		 */
		Comparator<Employee> my_final_compator = Comparator
				.comparing(keyExtractor_mf, keyCompartor);

		List<Employee> employees = new ArrayList<>();
		employees
				.add(emp_1);
		employees
				.add(emp_2);

		employees
				.sort(my_final_compator);

		employees
				.stream()
				.map((obj) -> obj.empId)
				.forEach(System.out::println);

		/**
		 * Extras
		 */
		Comparator<Employee> byAge = Comparator
				.comparing(Employee::getAge);
		Comparator<Employee> bySalary = Comparator
				.comparing(Employee::getSalary);
		Comparator<Employee> byEmpId = Comparator
				.comparing(Employee::getEmpId);
	}

}
