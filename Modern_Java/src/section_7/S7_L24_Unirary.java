package section_7;

import java.util.List;
import java.util.function.UnaryOperator;

import section_7.data.Student;
import section_7.data.StudentDataBase;

/**
 * @UnaryOperator
 * 
 * 	-	Implements @Function<T,T> interface
 * 	-	Methods:
 * 			-	identity()
 * 			- 	and Methods from @Function<T,T>
 * 
 * 	-	USE-CASE:	Input & Output Type are Same
 */
public class S7_L24_Unirary {

	public static List<Student> studentsList = StudentDataBase
			.getAllStudents();

	public static void main(String[] args) {

		make_name_caps();
	}

	private static void make_name_caps() {

		// Both Input & Output are Same Type 'String'
		UnaryOperator<String> covner_str = o -> o
				.toUpperCase() + " Java Developer";

		System.out
				.println(covner_str
						.apply("Lenin"));

	}
}
