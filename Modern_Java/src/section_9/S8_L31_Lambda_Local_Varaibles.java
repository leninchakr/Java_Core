package section_9;

import java.util.List;
import java.util.function.Consumer;

import section_7.data.Student;
import section_7.data.StudentDataBase;

/**
 * Local Variables with Lambda
 * 
 * 	-	Lambda has TWO restriction regarding usage of Local-Variables
 * 
 * 		1.	Lambda-Expression-Parameter can be same as  Local-Variable of object
 * 
 * 		2.	Lambda-Expression can't change the Local-Variable of the Object
 * 				-	BUT No-Restriction for Instance-Variables
 * 				-	Lambda-Expression can change the Instance-Variable Value
 * 
 * 		
 */
public class S8_L31_Lambda_Local_Varaibles {

	public static List<Student> studentsList = StudentDataBase
			.getAllStudents();

	public static Integer some_instance_varaible = 0;

	public static void main(String[] args) {

		Integer o_val = 7;

		/**
		 * 1.	Lambda-Expression-Parameter can be same as  Local-Variable of object
		 * 
		 */
		Consumer<Integer> c1 = (o_val) -> {

			System.out
					.println(o_val);
		};

		/**
		 * 2.	Lambda-Expression can't change the Local-Variable of the Object
		 * 
		 */

		Integer some_local_varaible = 8;

		Consumer<Integer> c2 = o -> {

			some_local_varaible = 7;

			System.out
					.println(o);
		};

		/**
		 * 
		 * 2.1 Lambda-Expression can change the Instance-Variable Value
		 */

		Consumer<Integer> c3 = o -> {

			some_instance_varaible = 9;

			System.out
					.println(o);
		};
	}
}
