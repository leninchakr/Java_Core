package extra;

import java.util.ArrayList;
import java.util.List;

public class Employee {
	public String empId;
	public Integer age;
	public Double salary;

	public String getEmpId() {
		return this.empId;
	}

	public Double getSalary() {
		return this.salary;
	}

	public Integer getAge() {
		return this.age;
	}

	public List<Employee> getAllEmployees() {

		Employee emp_1 = new Employee();
		emp_1.empId = "Lenin";
		emp_1.age = 27;
		emp_1.salary = 1530.0;

		Employee emp_2 = new Employee();
		emp_2.empId = "Apple";
		emp_2.age = 18;
		emp_2.salary = 1500.0;

		List<Employee> emps = new ArrayList<Employee>(List
				.of(emp_1, emp_2));
		
		return emps;

	}

}