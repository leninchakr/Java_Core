package extra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

/**
 * Why @Comparable?
 * 
 * 	-	To define default-soring-order (Natural order)
 */
public class Comparator_Comparable {

	public static void main(String[] args) {

		/**
		 * =========================================================
		 * STUDENT OBJECT CREATION
		 * =========================================================
		 * - Student class does NOT implement Comparable
		 * - So there is NO natural ordering defined
		 * - We must explicitly define sorting logic using Comparator
		 */
		Student s1 = new Student();
		s1.studId = "S101";
		s1.studAge = 20;
		s1.studDept = "CSE";

		Student s2 = new Student();
		s2.studId = "S102";
		s2.studAge = 22;
		s2.studDept = "ECE";

		Student s3 = new Student();
		s3.studId = "S103";
		s3.studAge = 19;
		s3.studDept = "MECH";

		Student s4 = new Student();
		s4.studId = "S104";
		s4.studAge = 21;
		s4.studDept = "CSE";

		Student s5 = new Student();
		s5.studId = "S105";
		s5.studAge = 23;
		s5.studDept = "EEE";

		// Creating a mutable list from immutable List.of()
		List<Student> students_list = new ArrayList<>(List
				.of(s1, s2, s3, s4, s5));

		System.out
				.println("------------ Before Sorting --------- ");

		// Display only selected fields using Stream API
		students_list
				.stream()
				.map(o -> o.studId + " - " + o.studAge)
				.forEach(System.out::println);

		/**
		 * =========================================================
		 * SORTING STUDENTS USING Comparator
		 * =========================================================
		 * We use Comparator.comparing() which internally needs:
		 *   1. Key extractor → tells WHAT to compare (studAge)
		 *   2. Comparator logic → tells HOW to compare the key
		 */

		// Step 1: Extract sorting key (student age)
		Function<Student, Integer> keyExtractorFunction_Var_to_Compare = stud -> stud.studAge;

		// Step 2: Define comparison logic for extracted key
		Comparator<Integer> keyComparator_Comparing_Logic = (o1, o2) -> Integer
				.compare(o1, o2);

		// Step 3: Combine both → final comparator for Student objects
		Comparator<Student> studentComparator_for_SORT = Comparator
				.comparing(keyExtractorFunction_Var_to_Compare, keyComparator_Comparing_Logic);

		// Apply sorting
		students_list
				.sort(studentComparator_for_SORT);

		System.out
				.println("------------ After Sorting --------- ");

		students_list
				.stream()
				.map(o -> o.studId + " - " + o.studAge)
				.forEach(System.out::println);

		/*******************************************************************************/

		/**
		 * =========================================================
		 * STAFF OBJECT CREATION
		 * =========================================================
		 * - Staff class implements Comparable
		 * - So it already defines NATURAL ORDER (by staffAge)
		 * - No need to pass explicit Comparator logic
		 */
		Staff st1 = new Staff();
		st1.staffId = "ST101";
		st1.staffAge = 45;
		st1.staffDept = "HR";
		st1.staffSalary = 12.65;

		Staff st2 = new Staff();
		st2.staffId = "ST102";
		st2.staffAge = 30;
		st2.staffDept = "Finance";
		st2.staffSalary = 12.65;

		Staff st3 = new Staff();
		st3.staffId = "ST103";
		st3.staffAge = 30;
		st3.staffDept = "IT";
		st3.staffSalary = 12.65;

		Staff st4 = new Staff();
		st4.staffId = "ST104";
		st4.staffAge = 28;
		st4.staffDept = "Admin";
		st4.staffSalary = 12.65;

		Staff st5 = new Staff();
		st5.staffId = "ST105";
		st5.staffAge = 50;
		st5.staffDept = "Operations";
		st5.staffSalary = 12.65;

		List<Staff> staffs = new ArrayList<>(List
				.of(st1, st2, st3, st4, st5));

		/**
		 * Even though Comparable exists, we still use Comparator.comparing()
		 * for flexibility and functional style.
		 */

		// Key extractor (same idea: we sort by age)
		Function<Staff, Integer> staffKeyExtractor_Var_to_Compare = obj -> obj.staffAge;

		// Comparator created without custom compare logic
		// because Integer already has natural ordering
		Comparator<Staff> staffComparator_for_SORT = Comparator
				.comparing(staffKeyExtractor_Var_to_Compare);

		System.out
				.println("------------ Before Sorting --------- ");

		staffs
				.stream()
				.map(o -> o.staffId + " - " + o.staffAge)
				.forEach(System.out::println);

		// Sort using comparator
		staffs
				.sort(staffComparator_for_SORT);

		System.out
				.println("------------ After Sorting --------- ");

		staffs
				.stream()
				.map(o -> o.staffId + " - " + o.staffAge)
				.forEach(System.out::println);

		/**
		 * Practical way of Implementing @Comparator (without/with @Comprable)
		 */

		// For Student (without @Comparable)
		List<Student> stud_list_new = new ArrayList<Student>(List
				.of(s1, s2, s3, s4, s5));

		Comparator<Student> comprator_for_Student_SORT = Comparator
				.comparing(Student::getStudAge, (o1, o2) -> Integer
						.compare(o1, o2));

		stud_list_new
				.sort(comprator_for_Student_SORT);

		// For Student (with @Comparable)
		List<Staff> staffs_list_new = new ArrayList<>(List
				.of(st1, st2, st3, st4, st5));

		Comparator<Staff> comprator_for_Staff_SORT = Comparator
				.comparing(Staff::getStaffAge);

		// Way - 1
		staffs_list_new
				.sort(comprator_for_Staff_SORT);

		// Way - 2
		Collections
				.sort(staffs_list_new);

		System.out
				.println("------------ After Sorting --------- ");

		staffs_list_new
				.stream()
				.map(o -> o.staffId + " - " + o.staffAge)
				.forEach(System.out::println);
	}
}

/**
 * =========================================================
 * STUDENT CLASS
 * =========================================================
 * - Does NOT implement Comparable
 * - Hence NO default sorting order
 * - Sorting must always be defined externally using Comparator
 */
class Student {

	public String studId;
	public int studAge;
	public String studDept;

	public int getStudAge() {
		return this.studAge;
	}
}

/**
 * =========================================================
 * STAFF CLASS
 * =========================================================
 * - Implements Comparable → defines natural ordering
 * - Natural order here: staffAge (ascending)
 * - This allows Collections.sort() or stream sorting without Comparator
 */
class Staff implements Comparable<Staff> {

	public String staffId;
	public Integer staffAge;
	public String staffDept;
	public double staffSalary;

	public int getStaffAge() {
		return this.staffAge;
	}

	/**
	 * Natural ordering logic
	 * Returns:
	 *   - negative → this < other
	 *   - zero     → equal
	 *   - positive → this > other
	 */
	@Override
	public int compareTo(Staff o) {
		return Integer
				.compare(this.staffAge, o.staffAge);
	}
}