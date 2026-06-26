package extra;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import section_7.data.Student;
import section_7.data.StudentDataBase;

public class Comparator_Example {

	public static List<Student> studentsList = StudentDataBase
			.getAllStudents();

	public static void main(String[] args) {

		// What to Compare :: Student-Name
		Function<Student, String> key_extractor = o -> o
				.getName();

		// How to Compare The KEY! :: Student-Name-length of the KEY:TYPE
		// THIS : Compares @String
		Comparator<String> custom_logic_NAME_LEN_COMPARE = (o1, o2) -> Integer
				.compare(o1
						.length(),
						o2
								.length());

		// Create Final Comparator
		// THIS	: Compares @Student
		Comparator<Student> compare_student_by_name_length = Comparator
				.comparing(key_extractor, custom_logic_NAME_LEN_COMPARE);

		// Use the Comparator in .sorted()
		studentsList
				.stream()
				.sorted(compare_student_by_name_length
						.reversed())
				.forEach(System.out::println);

		System.out
				.println("*******************************************************************");

		/**
		 * Important: When to use Comparators.comparing()
		 * 
		 * 	-	We know which @Field to compare
		 * 	-	We Don't want to write comparison logic
		 */

		/*
		 *	Case 1: Without comparing() 
		 *	
		 *	We are responsible for BOTH
		 *		-	Extract KEY
		 *		-	Compare KEY
		 */
		Comparator<Student> gpaCompare_WO_comparing = (s1, s2) -> Double
				.compare(s1
						.getGpa(),
						s2
								.getGpa());

		/*
		 *	Case 2: With comparing( @KeyExtractor) 
		 *	
		 *	-	Now you only tell Java:	
		 *
		 *			-	"Use the GPA field"
		 *
		 *	Java Automatically do BOTH
		 *		-	Extract KEY
		 *		-	Compare KEY ( if the Key is @Comparable already)
		 */
		Comparator<Student> gpaCompare_W_comparing = Comparator
				.comparing(Student::getGpa);

		/*
		 *	Case 2: With comparing( @KeyExtractor) 
		 *	
		 *	-	Now you only tell Java:	
		 *
		 *			-	"Use the GPA field"
		 *			-	"How to Compare"
		 *
		 *	Java Automatically do BOTH
		 *		-	Extract KEY
		 *		-	Compare KEY ( if the Key is @Comparable already)
		 */

		Comparator<Double> custom_logic = (o1, o2) -> o1
				.compareTo(o2);

		Comparator<Student> gpaCompare_W_comparing_PLUS_logic = Comparator
				.comparing(Student::getGpa, custom_logic);

		// Use the Comparator in .sorted()
		studentsList
				.stream()
				.sorted(gpaCompare_W_comparing_PLUS_logic
						.reversed())
				.forEach(System.out::println);
	}
}
