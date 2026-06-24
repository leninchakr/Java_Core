package section_8;

import java.util.List;
import java.util.function.Predicate;

import section_7.data.Student;
import section_7.data.StudentDataBase;

public class S8_L29_Method_Reference_Cannot_Used_Refactor {

	public static List<Student> studentsList = StudentDataBase
			.getAllStudents();

	public static void main(String[] args) {

		/**
		 * Lambda
		 */
		Predicate<Student> p1 = o -> o
				.getGradeLevel() >= 3;

		System.out
				.println(p1
						.test(StudentDataBase.studentSupplier
								.get()));

		/**
		 * Question : How to implement Method-Reference
		 * 
		 *	Answer : Refactor the Code 
		 */
		Predicate<Student> p2 = S8_L29_Method_Reference_Cannot_Used_Refactor::greaterThanGradeLevel;

		System.out
				.println(p2
						.test(StudentDataBase.studentSupplier
								.get()));

	}

	public static boolean greaterThanGradeLevel(Student o) {

		return o
				.getGradeLevel() >= 3;
	}
}
