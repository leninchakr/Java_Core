package extra;

import java.util.List;

import section_7.data.Student;
import section_7.data.StudentDataBase;

/**
 * @Collections - Class
 * 
 * 	-	Implements @Collection
 * 	-	Many @static methods
 * 
 * 	Methods:
 * 	-	
 */
public class Collections_Full {

	public static List<Student> studentsList = StudentDataBase
			.getAllStudents();

	public static void main(String[] args) {

	}
}
