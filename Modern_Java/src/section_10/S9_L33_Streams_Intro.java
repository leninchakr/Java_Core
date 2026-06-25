package section_10;

import java.util.List;

import section_7.data.Student;
import section_7.data.StudentDataBase;

/**
 * Stream : Sequence of data
 * 
 * Types:
 * 	-	Series-Stream	:	.stream()
 * 	-	Parallel-Stream	:	.parallelStream()
 */
public class S9_L33_Streams_Intro {

	public static List<Student> studentsList = StudentDataBase
			.getAllStudents();

	public static void main(String[] args) {

		/**
		 * Create Stream
		 */
		studentsList
				.stream();

		/**
		 * Create Parallel-Stream
		 */
		studentsList
				.parallelStream();
	}
}
