package extra;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;

/**
 * =========================================================
 * Comparator Cheat Sheet (Core Idea)
 * =========================================================
 *
 * Comparator is used to define custom sorting rules.
 *
 * Main building blocks:
 *
 * 1. compare()
 *    → Core abstract method (a vs b comparison logic)
 *
 * 2. naturalOrder() / reverseOrder()
 *    → Uses Comparable (default ordering of a type)
 *    → naturalOrder(): ascending order
 *    → reverseOrder(): descending order
 *
 * 3. comparing(keyExtractor)
 *    → Extract key from object
 *    → Uses natural ordering of key (if Comparable)
 *
 * 4. comparing(keyExtractor, customComparator)
 *    → Extract key from object
 *    → Apply custom comparison logic for the extracted key
 *
 * 5. comparingInt / comparingLong / comparingDouble
 *    → Optimized for primitive keys (avoids boxing)
 *
 * 6. reversed()
 *    → Reverses any existing comparator (any level in chain)
 *
 * 7. thenComparing()
 *    → Used for multi-level sorting (tie-breaker logic)
 *
 *    How it works:
 *      - First comparator is applied (primary sorting key)
 *      - If values are equal, next comparator is used
 *      - Continues chaining for further tie-breakers
 *
 *    Overloads:
 *
 *    1. thenComparing(keyExtractor)
 *       → Used when KEY TYPE implements Comparable
 *       → Uses natural ordering of extracted key
 *
 *    2. thenComparing(keyExtractor, customComparator)
 *       → Used when custom comparison logic is required for the key
 *       → NOT dependent on whether class implements Comparable
 *
 *    3. thenComparing(customComparator)
 *       → Direct custom comparator for next level sorting
 *       → Used when full comparison logic is explicitly defined
 */
/**
 * Important : What .sort() method accepts are parameter
 */
public class Comparator_Methods {

	public static void main(String[] args) {

		/**
		 * =========================================================
		 * STUDENT OBJECT CREATION
		 * =========================================================
		 * - Student class does NOT implement Comparable
		 * - So there is NO natural ordering defined
		 * - We must explicitly define sorting logic using Comparator
		 */
		Student stud_data = new Student();

		List<Student> students_list = stud_data
				.getAllStudents();

		/**
		 * =========================================================
		 * STAFF OBJECT CREATION
		 * =========================================================
		 * - Staff class implements Comparable
		 * - So it already defines NATURAL ORDER (by staffAge)
		 * - No need to pass explicit Comparator logic
		 */
		Staff staff_data = new Staff();

		List<Staff> staffs_list = staff_data
				.getAllStaffs();

		/****************************************************************/

		/**
		 * =========================================================
		 * Method 3: Comparator.comparing(keyExtractor, comparator)
		 * =========================================================
		 *
		 * - Used when we want CUSTOM comparison logic for the KEY
		 * - NOT dependent on whether the class is Comparable
		 *
		 * Steps:
		 * 1. Define key extractor
		 * 2. Define comparison logic for KEY
		 * 3. Combine both into final comparator
		 */
		// Step 1: Define comparison logic for KEY : using .compare()
		Comparator<Integer> comparing_logic_Student = (o1, o2) -> Integer
				.compare(o1, o2);

		// Step 2: Key extractor
		Function<Student, Integer> var_to_compare_Student = o -> o.studAge;

		// Step 3: Combine key + logic
		Comparator<Student> sort_comparator_Student = Comparator
				.comparing(var_to_compare_Student, comparing_logic_Student);

		// Step 4, Finally Sorting
		students_list
				.sort(sort_comparator_Student);

		System.out
				.println("------------ comparing(keyExtractor, comparator) ----------");

		students_list
				.stream()
				.map((o) -> o.studId + " - " + o.studAge)
				.forEach(System.out::println);

		/**
		 * =========================================================
		 * Method 2: Comparator.reversed()
		 * =========================================================
		 *
		 *	-	Sorting logic is reversed!
		 *	-	Applied to existing final_Comparator!
		 *	
		 *	Step 1: Create regular-comparing-logic
		 *	Step 2: append .reversed()
		 */
		Comparator<Student> sort_comparator_Student_reverse = Comparator
				.comparing(var_to_compare_Student, comparing_logic_Student)
				.reversed();

		students_list
				.sort(sort_comparator_Student_reverse);

		System.out
				.println("------------ reversed() ----------");

		students_list
				.stream()
				.map((o) -> o.studId + " - " + o.studAge)
				.forEach(System.out::println);

		/**
		 * =========================================================
		 * STEP 1: Extract ages from Student list
		 * =========================================================
		 *
		 * We convert List<Student> → List<Integer>
		 * so we can directly apply natural ordering on Integer values.
		 *
		 * Stream flow:
		 * Student → studAge → Integer list
		 */
		List<Integer> ages = new ArrayList<>(students_list
				.stream()
				.map(s -> s.studAge)
				.toList());

		/**
		 * =========================================================
		 * Method 2: Comparator.naturalOrder()
		 * =========================================================
		 *
		 * - Sorts elements in ascending order
		 * - Uses natural ordering defined by Comparable
		 * - Integer already implements Comparable<Integer>
		 *
		 * So:
		 *   smallest → largest
		 *   19 → 20 → 21 → 22 → 23
		 */
		ages
				.sort(Comparator
						.naturalOrder());

		System.out
				.println("------------ Comparator.naturalOrder() ----------");
		System.out
				.println(ages);

		/**
		 * =========================================================
		 * Method 2: Comparator.reverseOrder()
		 * =========================================================
		 *
		 * - Sorts elements in descending order
		 * - Reverses the natural ordering
		 *
		 * So:
		 *   largest → smallest
		 *   23 → 22 → 21 → 20 → 19
		 */
		ages
				.sort(Comparator
						.reverseOrder());

		System.out
				.println("------------ Comparator.reverseOrder() ----------");
		System.out
				.println(ages);

		/**
		 * =========================================================
		 * Method 3: Comparator.comparing(keyExtractor)
		 * =========================================================
		 *
		 * - Used when KEY TYPE has NATURAL ORDERING (Comparable)
		 * - No custom comparison logic required
		 * - NOT dependent on whether the class is Comparable
		 *
		 * Here, Integer (KEY type) implements Comparable
		 */
		// Step 1
		Function<Staff, Integer> var_to_compare_Staff = o -> o.staffAge;

		// Step 2: Comparator using natural order of KEY "var_to_compare_Staff"
		Comparator<Staff> sort_comparator_Staff = Comparator
				.comparing(var_to_compare_Staff);

		// Step 3, Finally Sorting
		staffs_list
				.sort(sort_comparator_Staff);

		System.out
				.println("------------ comparing(keyExtractor) ----------");

		staffs_list
				.stream()
				.map((o) -> o.staffId + " - " + o.staffAge)
				.forEach(System.out::println);

		/**
		 * Method 4: Comparator.comparingInt/ comparingLong/ comparingDouble
		 * 
		 * 	-	ToIntFunction/ ToLongFunction/ ToDoubleFunction
		 *	
		 *	-	KEY : int, long, double -> Has Natural-Ordering
		 *	-	So .compare() is not needed 
		 */
		// For 'int'
		ToIntFunction<Staff> int_key_to_compare = o -> o.staffAge;

		Comparator<Staff> int_comparator_sort = Comparator
				.comparingInt(int_key_to_compare);

		staffs_list
				.sort(int_comparator_sort);

		System.out
				.println("------------ comparingInt() ----------");

		staffs_list
				.stream()
				.map((o) -> o.staffId + " - " + o.staffAge)
				.forEach(System.out::println);

		// For 'double'
		ToDoubleFunction<Staff> double_key_to_compare = o -> o.staffSalary;

		Comparator<Staff> double_comparator_sort = Comparator
				.comparingDouble(double_key_to_compare);

		staffs_list
				.sort(double_comparator_sort);

		System.out
				.println("------------ comparingDouble() ----------");

		staffs_list
				.stream()
				.map((o) -> o.staffId + " - " + o.staffSalary)
				.forEach(System.out::println);

		/**
		 * =========================================================
		 * Method 7: thenComparing(keyExtractor, customComparator)
		 * =========================================================
		 *
		 * - Used for MULTI-LEVEL SORTING (tie-breaker logic)
		 * - Each level can have its own custom comparison rule
		 *
		 * Flow:
		 *   1. Primary sorting is applied (AGE)
		 *   2. If AGE is equal, secondary sorting is applied (SALARY)
		 *   3. SALARY sorting uses CUSTOM logic (reversed order here)
		 *
		 * Important:
		 * - Each level is independently defined using:
		 *     keyExtractor + comparator for that key
		 * - Useful when different sorting rules are needed at each level
		 */

		/**
		 * =========================
		 * Stage 1: AGE (Primary Key)
		 * =========================
		 *
		 * Extract AGE from Staff object
		 */
		Function<Staff, Integer> key_var_to_compare_AGE = o -> o.staffAge;

		/**
		 * Comparator logic for AGE
		 * Natural ascending order (small → large)
		 */
		Comparator<Integer> compare_logic_AGE = (o1, o2) -> Integer
				.compare(o1, o2);

		/**
		 * =========================
		 * Stage 2: SALARY (Secondary Key)
		 * =========================
		 *
		 * Extract SALARY from Staff object
		 */
		Function<Staff, Double> key_var_to_compare_SALARY = o -> o.staffSalary;

		/**
		 * Comparator logic for SALARY
		 * Natural ascending order (low → high)
		 */
		Comparator<Double> compare_logic_SALARY = (o1, o2) -> Double
				.compare(o1, o2);

		/**
		 * =========================================================
		 * Final Comparator (Multi-Level Sorting)
		 * =========================================================
		 *
		 * Step 1: Compare by AGE
		 * Step 2: If AGE is same → compare by SALARY
		 * Step 3: SALARY is sorted in REVERSED order
		 *
		 * Final behavior:
		 *   AGE → ascending
		 *   SALARY → descending (tie-breaker)
		 */
		Comparator<Staff> comparator_KEY_COMP_SORT = Comparator
				.comparing(key_var_to_compare_AGE, compare_logic_AGE)
				.thenComparing(key_var_to_compare_SALARY, compare_logic_SALARY
						.reversed());

		/**
		 * Apply sorting
		 */
		staffs_list
				.sort(comparator_KEY_COMP_SORT);

		System.out
				.println("------------ thenComparing(keyExtractor, customComparator) ----------");

		staffs_list
				.stream()
				.map(o -> o.staffId + " - " + o.staffAge + " - " + o.staffSalary)
				.forEach(System.out::println);

		/**
		 * =========================================================
		 * Method 7: thenComparing(keyExtractor)
		 * =========================================================
		 *
		 * - Used for multi-level sorting (tie-breaker logic)
		 * - Used when KEY TYPE implements Comparable (natural ordering)
		 *
		 * Flow:
		 *   1. Primary sorting is applied
		 *   2. If values are equal → next comparator is applied
		 *   3. Each level uses natural ordering of extracted key
		 *
		 * Note:
		 * - No custom comparator is required for keys
		 * - Java automatically uses Comparable logic of the key
		 */

		/**
		 * Primary Key: AGE (Comparable → Integer)
		 */
		Function<Staff, Integer> key_age = o -> o.staffAge;

		/**
		 * Secondary Key: SALARY (Comparable → Double)
		 */
		Function<Staff, Double> key_salary = o -> o.staffSalary;

		/**
		 * Multi-level sorting using natural ordering of keys
		 * AGE → primary
		 * SALARY → secondary
		 */
		Comparator<Staff> comparator = Comparator
				.comparing(key_age)
				.thenComparing(key_salary)
				.reversed();

		/**
		 * Apply sorting
		 */
		staffs_list
				.sort(comparator);

		System.out
				.println("------------ thenComparing(keyExtractor) ----------");

		staffs_list
				.stream()
				.map(o -> o.staffId + " - " + o.staffAge + " - " + o.staffSalary)
				.forEach(System.out::println);

		/**
		 * =========================================================
		 * Method 7: thenComparing(Comparator<? super T> other)
		 * =========================================================
		 *
		 * - Used for multi-level sorting (tie-breaker logic)
		 * - This version accepts a FULL Comparator (not a key extractor)
		 *
		 * Flow:
		 *   1. Primary comparator is applied first
		 *   2. If values are equal → secondary comparator is used
		 *   3. Secondary comparator works on FULL objects (Staff)
		 *
		 * Important:
		 * - Unlike key-based thenComparing, this does NOT extract a field
		 * - It directly compares the whole object using another Comparator
		 */

		/**
		 * PRIMARY comparator: Age (ascending)
		 *
		 * - Extracts staffAge from Staff
		 * - Uses natural ordering of Integer
		 */
		Comparator<Staff> ageComparator = Comparator
				.comparing(s -> s.staffAge);

		/**
		 * SECONDARY comparator: Salary (descending)
		 *
		 * - Works on FULL Staff object
		 * - First extracts salary, then applies reversed order
		 */
		Comparator<Staff> salaryComparator = Comparator
				.comparing((Staff s) -> s.staffSalary)
				.reversed();

		/**
		 * FINAL COMPARATOR (multi-level sorting)
		 *
		 * Step 1: Compare by Age
		 * Step 2: If Age is equal → compare using salaryComparator
		 *
		 * salaryComparator is applied on FULL Staff objects
		 */
		Comparator<Staff> finalComparator = ageComparator
				.thenComparing(salaryComparator);

		/**
		 * Apply sorting
		 */
		staffs_list
				.sort(finalComparator);

		System.out
				.println("------------ thenComparing(customComparator) ----------");

		staffs_list
				.stream()
				.map(o -> o.staffId + " - " + o.staffAge + " - " + o.staffSalary)
				.forEach(System.out::println);

	}
}
