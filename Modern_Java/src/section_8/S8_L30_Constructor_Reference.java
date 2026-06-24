package section_8;

import java.util.List;
import java.util.function.Supplier;

import section_7.data.Student;
import section_7.data.StudentDataBase;

public class S8_L30_Constructor_Reference {

	public static List<Student> studentsList = StudentDataBase
			.getAllStudents();

	public static void main(String[] args) {

		/**
		 * Syntax for Constructor-Reference
		 * 
		 * 	ClassName::new
		 */

		/**
		 * 
		 */
		Supplier<Student> student_supplier = StudentDataBase.studentSupplier;

		System.out
				.println(student_supplier
						.get());
	}
}
