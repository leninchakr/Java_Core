package section_8;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import section_7.data.Student;
import section_7.data.StudentDataBase;

/**
 * Purpose : To simplify the implementation of Functional-Interfaces
 */
public class S8_L26_Method_Reference {

	public static List<Student> studentsList = StudentDataBase
			.getAllStudents();

	public static void main(String[] args) {

		/**
		 * Syntax:
		 * 
		 * 	ClassName::Instance-methodName
		 * 	ClassName::Static-methodName
		 * 	Instance::methodName
		 */

		/**
		 * Where to use:
		 * 
		 * 	-	Can be used in Lambda, Where it refers to a method DIRECRTLY!
		 * 
		 */

		Function<String, String> conver_to_uppercase = o -> o
				.toUpperCase();

		/**
		 * Breakdown : o.toUpperCase()
		 * 
		 *  	->	Instance.Method()
		 *  
		 *  Translates To:
		 *  	->	String::Method
		 *  
		 *  My Understanding:
		 *  
		 *  	o.toUpperCas();
		 *  	
		 *  		-	o	: String
		 *  	
		 *  	String::toUpperCase
		 */
		Function<String, String> conver_to_uppercase_MR = String::toUpperCase;

		/**
		 * Example, where Method-Reference is not applicable
		 */
		Predicate<Student> p_lambda = o -> o
				.getGradeLevel() > 3;

	}
}
