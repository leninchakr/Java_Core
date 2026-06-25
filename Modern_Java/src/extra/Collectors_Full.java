package extra;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import section_7.data.Student;
import section_7.data.StudentDataBase;

/**
 * ========================= COLLECTORS OVERVIEW =========================
 *
 * WHAT IS Collectors?
 * --------------------
 * Collectors is a utility (helper) class in Java Stream API that provides
 * predefined implementations of the Collector interface.
 *
 * It is used to accumulate stream elements into a final data structure.
 *
 * ----------------------------------------------------------------------
 * KEY CHARACTERISTICS:
 *
 * - Utility class with only static methods
 * - Cannot be instantiated
 * - Provides reusable Collector implementations
 * - Works with Stream.collect()
 *
 * ----------------------------------------------------------------------
 * WHY DO WE NEED IT?
 *
 * Collectors are used to transform and consolidate Stream data into
 * meaningful results.
 *
 * Common output forms include:
 *
 *      - List
 *      - Set
 *      - Map
 *      - String
 *      - Numeric results (count, average, sum, etc.)
 *
 * ----------------------------------------------------------------------
 * MAJOR CATEGORIES OF COLLECTORS:
 *
 * 1. COLLECTION CREATION
 *      - Convert Stream → Collection
 *      - Examples:
 *          toList()
 *          toSet()
 *          toCollection()
 *
 * 2. MAP CREATION
 *      - Convert Stream → Map
 *      - Examples:
 *          toMap()
 *          groupingBy()
 *          partitioningBy()
 *
 * 3. GROUPING
 *      - Classify elements into groups
 *      - Example:
 *          groupingBy()
 *
 * 4. PARTITIONING
 *      - Split data into two groups (true/false)
 *      - Example:
 *          partitioningBy()
 *
 * 5. AGGREGATION / STATISTICS
 *      - Perform numeric calculations
 *      - Examples:
 *          counting()
 *          averagingDouble()
 *          summingInt()
 *          summarizingDouble()
 *
 * 6. STRING JOINING
 *      - Combine elements into a single String
 *      - Example:
 *          joining()
 *
 * 7. REDUCTION
 *      - Reduce stream into a single result
 *      - Examples:
 *          reducing()
 *          maxBy()
 *          minBy()
 *
 * 8. DOWNSTREAM / TRANSFORMATION COLLECTORS
 *      - Used inside groupingBy / partitioningBy
 *      - Transforms grouped values
 *      - Examples:
 *          mapping()
 *          filtering()
 *          collectingAndThen()
 *
 * ======================================================================
 */
public class Collectors_Full {

	public static List<Student> studentsList = StudentDataBase
			.getAllStudents();

	public static void main(String[] args) {

		/**
		 * 1.	Collection Creation Methods (3)
		 * 
		 * 	-	.toList()
		 * 			-	RT:	@List<T>
		 * 
		 * 	-	.toSet()
		 * 			-	Duplicate elements are removed
		 * 			-	RT:	@Set<T>
		 * 
		 * 	-	.toCollection( @Supplier )
		 * 			-	Specific collection implementation supplied by the user
		 * 			-	RT: Any @Collection implementation
		 * 			-	Used for Custom-Collection
		 * 			-	Eg:
		 * 					@LinkedList<T>	-	@LinkedList::new
		 * 					@TreeSet<T>		-	@TreeSet::new
		 * 					@ArrayDeque<T>	-	@ArrayDeque::new
		 */
		Collection_Creation_methods();

		/**
		 * 2.	Map Creation Methods
		 * 
		 * 	-	.toMap()
		 * 			
		 * 	-	.toConcurrentMap()
		 */
		Map_Creation_methods();

	}

	public static void Collection_Creation_methods() {

		toList_method_string_stream();
		toList_method_object_stream();
		toSet_method_object_stream();
		toCollection_obj_stream_LinkeList();
		toCollection_obj_stream_TreeSet();

	}

	public static void toList_method_string_stream() {

		/**
		 * Method 1:	.toList()
		 * 
		 * 	-	Creates @List<String>
		 * 
		 * Objective : Create Student-Name-List from @List<Object>
		 */
		List<String> nameList = studentsList
				.stream()
				.map(o -> o
						.getName())
				/**
				 * Collectors::toList → “Here is a pointer to a method” (not usable here)
				 * Collectors.toList() → “Give me a recipe to collect into a List”
				 */
				.collect(Collectors
						.toList());

		System.out
				.println("---------- .toList() using String ------------");
		System.out
				.println(nameList);
	}

	public static void toList_method_object_stream() {

		/**
		 * Method 1:	.toList()
		 * 
		 * 	-	Creates @List<T>
		 * 
		 *	Objective : Create @List<Student> from @List<Student>
		 *
		 */

		List<Student> all_students_list = studentsList
				.stream()
				/**
				 * Collectors::toList → “Here is a pointer to a method” (not usable here)
				 * Collectors.toList() → “Give me a recipe to collect into a List”
				 */
				.collect(Collectors
						.toList());

		System.out
				.println("---------- .toList() using Object ------------");

		System.out
				.println(all_students_list);
	}

	public static void toSet_method_object_stream() {
		/**
		 * Method 2:	.toSet()
		 * 
		 * 	-	Creates @Set<T>
		 * 	-	Can't store duplicate values
		 * 
		 * Objective : Get Unique-Name from @List<Object>
		 */
		Set<String> unique_name_list = studentsList
				.stream()
				.map(Student::getName)
				.collect(Collectors
						.toSet());

		System.out
				.println("---------- .toSet() using Object ------------");

		System.out
				.println(unique_name_list);
	}

	public static void toCollection_obj_stream_LinkeList() {

		/**
		 * Method 3:	.toCollection( @Supplier<TR> )
		 * 
		 * 	-	Creates @LinedList<String>
		 * 
		 * Objective : Create @LinkedList<Studnet> from @List<Object>
		 */

		/**
		 * Step 1: Supplier: For .toCollection( @Supplier<T> )
		 */
		Supplier<LinkedList<Student>> get_linked_list = () -> new LinkedList();

		/**
		 * Step 2: Pass the supplier
		 */
		LinkedList<Student> students_ll = studentsList
				.stream()
				.collect(Collectors
						.toCollection(get_linked_list));

		System.out
				.println("---------- .toCollection(LinkedList::new) ------------");

		System.out
				.println(students_ll);
	}

	public static void toCollection_obj_stream_TreeSet() {

		/**
		 * Method 3:	.toCollection( @Supplier<TR> )
		 * 
		 * 	-	Creates @TreeSet<Studnet> With @Comparator
		 * 
		 * Objective : Create @TreeSet<Studnet> from @List<Object>
		 */

		/**
		 * Supplier: For .toCollection( @Supplier<T> )
		 */
		Supplier<TreeSet<Student>> get_tree_set = () -> {

			/**
			 * Important: Always Create @TreeSet with @Comparator
			 * 
			 * Reason: @TreeSet sort the element based on @Comparator/ @Comparable
			 */
			Comparator<Student> compare_gpa = (o1, o2) -> Double
					.compare(o1
							.getGpa(),
							o2
									.getGpa());

			return new TreeSet(compare_gpa);

		};

		TreeSet<Student> students_ts = studentsList
				.stream()
				.collect(Collectors
						.toCollection(get_tree_set));

		System.out
				.println("---------- .toCollection(Supplier<TreeSet<?>>)  ------------");
		System.out
				.println(students_ts);
	}

	public static void Map_Creation_methods() {

		toMap_method_object_stream();
		groupingBy_method_object_stream();
		partitioningBy_method_object_stream();
	}

	public static void toMap_method_object_stream() {

		/**
		 * -	.toMap() Syntax:
		 * 
		 * 		-	(F_Key, F_Val)
		 * 		-	(F_Key, F_Val, BiF_key_conflict)
		 * 		-	(F_Key, F_Val, BiF_key_conflict, S_Order) {Preserve order!}
		 */

		Function<Student, String> stud_name_key = o -> o
				.getName();
		Function<Student, Double> stud_gpa_val = o -> o
				.getGpa();

		// Always choose Latest Value
		BinaryOperator<Double> conflict_handle = (o1, o2) -> o2;

		/**
		 * Map<K,V> needs TWO @Function<I,K> interfaces & ONE @BinaryOperator
		 */
		studentsList
				.stream()
				.collect(Collectors
						.toMap(stud_name_key, stud_gpa_val, conflict_handle));

		/**
		 * Using Method-Reference
		 */
		Map<String, Double> stud_gpa_map = studentsList
				.stream()
				.collect(Collectors
						.toMap(Student::getName, Student::getGpa, conflict_handle));

		System.out
				.println("---------- .toMap(F_key, F_value, conflict_handle)  ------------");

		System.out
				.println(stud_gpa_map);

		/**
		 * toMap() Vs groupingBy()
		 * 
		 * 	-	toMap() 		
		 * 			: 	1-to-1 Mapping
		 * 			:	RT : Map<K, V>
		 * 	-	groupingBy()	
		 * 			:	1-to-Many Mapping
		 * 			:	RT : Map<K, List<T>>
		 */
	}

	public static void groupingBy_method_object_stream() {

		/**
		 * ========================= GROUPING IN STREAMS =========================
		 *
		 * groupingBy() is used to perform "classification-based aggregation".
		 * It groups stream elements into buckets based on a classifier function.
		 *
		 * Concept:
		 * --------
		 * One-to-Many relationship:
		 *      One key  →  Many values (grouped elements)
		 *
		 * -----------------------------------------------------------------------
		 * GENERAL BEHAVIOR:
		 *
		 * Input   : Stream<T>
		 * Process : Classify each element using Function<T, K>
		 * Output  : Map<K, List<T>>   (default behavior)
		 *
		 * -----------------------------------------------------------------------
		 * WHY MAP ALWAYS?
		 *
		 * Because grouping means:
		 *      - Create buckets (keys)
		 *      - Store matching elements in each bucket
		 *
		 * So the outer structure is ALWAYS a Map.
		 *
		 * -----------------------------------------------------------------------
		 * groupBy OVERLOADS:
		 *
		 * 1. groupingBy(Function classifier)
		 *      → Map<K, List<T>>
		 *
		 * 2. groupingBy(Function classifier, Collector downstream)
		 *      → Map<K, D>   (D depends on downstream collector)
		 *
		 * 3. groupingBy(Function classifier, Supplier mapFactory, Collector downstream)
		 *      → Custom Map implementation + Map<K, D>
		 */

		/*
		 * -----------------------------------------------------------------------
		 * TYPE - 1 (Basic grouping):
		 *
		 * Signature:
		 *      groupingBy(Function<T, K>)
		 *
		 * Flow:
		 *      Input  : List<T>
		 *      Stream : Stream<T>
		 *      Key    : Extracted using classifier Function<T, K>
		 *      Value  : List<T> (original elements grouped together)
		 *
		 * Result:
		 *      Map<K, List<T>>
		 *
		 * -----------------------------------------------------------------------
		 * IMPORTANT RULE:
		 *
		 * - Key type (K) comes from classifier function
		 * - Value type (T) is ALWAYS the original stream element type
		 * - Unless a downstream collector is used
		 *
		 * -----------------------------------------------------------------------
		 * KEY INSIGHT:
		 *
		 * groupingBy() does NOT transform elements by default.
		 * It only groups them into lists.
		 *
		 * =======================================================================
		 */
		Function<Student, String> name_classifier_type_1 = o -> o
				.getName();

		Map<String, List<Student>> groupBy_Name_type_1 = studentsList
				.stream()
				.collect(Collectors
						.groupingBy(name_classifier_type_1));

		System.out
				.println("---------- Type - 1 ::: .groupingBy(Func_Classifier)  ------------");
		System.out
				.println(groupBy_Name_type_1);

		/*
		 * ========================= GROUPING - TYPE 2 =========================
		 *
		 * Basic Idea:
		 * -----------
		 * To change the VALUE type of the resulting Map, we use a downstream collector.
		 *
		 * This allows transformation/aggregation of grouped elements.
		 *
		 * ----------------------------------------------------------------------
		 * SIGNATURE:
		 *
		 * groupingBy(Function<T, K>, Collector<T, A, D>)
		 *
		 * ----------------------------------------------------------------------
		 * PARAMETERS:
		 *
		 * 1. keyExtractor (Function<T, K>)
		 *      - Extracts the grouping key
		 *      - Determines how elements are grouped
		 *
		 * 2. downstream (Collector)
		 *      - Defines what happens to grouped values
		 *      - Transforms List<T> into another result type (D)
		 *
		 *      Examples:
		 *          Collectors.counting()
		 *          Collectors.averagingDouble()
		 *          Collectors.mapping()
		 *          Collectors.summarizingDouble()
		 *          Collectors.maxBy()
		 *
		 *      NOTE:
		 *          In most real-world cases, downstream collectors come from
		 *          Collectors.* utility class.
		 *
		 * ----------------------------------------------------------------------
		 * FLOW:
		 *
		 * Input   : List<T>
		 * Stream  : Stream<T>
		 * Step 1  : Group elements using keyExtractor → K
		 * Step 2  : Apply downstream collector on each group
		 *
		 * ----------------------------------------------------------------------
		 * RESULT:
		 *
		 * Map<K, D>
		 *
		 * where:
		 *      K → Type of grouping key
		 *      D → Result produced by downstream collector
		 *
		 * ----------------------------------------------------------------------
		 * IMPORTANT INSIGHT:
		 *
		 * - groupingBy() always creates a Map
		 * - Key is defined by classifier function
		 * - Value type is controlled by downstream collector
		 * - Without downstream, value defaults to List<T>
		 *
		 * ======================================================================
		 */

		/**
		 * Step 1: Get Key
		 */
		Function<Student, String> groupBy_Name_type_2 = o -> o
				.getName();

		/*
		 * ========================= DOWNSTREAM COLLECTORS =========================
		 *
		 * Step 2: Downstream Collector (used in advanced grouping/collecting)
		 *
		 * ------------------------------------------------------------------------
		 * PURPOSE:
		 *
		 * - Defines how grouped elements are processed after classification
		 * - Transforms the default List<T> into another result type (D)
		 *
		 * ------------------------------------------------------------------------
		 * GENERIC STRUCTURE:
		 *
		 * Collector<T, A, R>
		 *
		 * where:
		 *      T → Input element type (Stream element type)
		 *      A → Accumulator type (internal working memory)
		 *      R → Final result type
		 *
		 * ------------------------------------------------------------------------
		 * IMPORTANT INSIGHT:
		 *
		 * - In most real-world and built-in collectors (99% cases),
		 *   the accumulator type (A) is hidden and not required explicitly.
		 *
		 * - It is represented as "?"
		 *
		 * ------------------------------------------------------------------------
		 * WHY IS ACCUMULATOR OFTEN '?' ?
		 *
		 * - Java manages the accumulator internally
		 * - It acts as temporary working memory during collection
		 * - Developers do NOT interact with it directly
		 * - It becomes important only when writing CUSTOM collectors
		 *
		 * ------------------------------------------------------------------------
		 * COMMON USAGE PATTERN:
		 *
		 * Collector<T, ?, R>
		 *
		 * Example:
		 *      Collector<Student, ?, Long> using Collectors.counting()
		 *
		 * ------------------------------------------------------------------------
		 * SUMMARY:
		 *
		 * - T → Input type
		 * - ? → Internal accumulator (hidden by Java)
		 * - R → Output / result type
		 *
		 * ========================================================================
		 */
		Collector<Student, ?, Long> countStudents = Collectors
				.counting();

		Map<String, Long> student_count = studentsList
				.stream()
				.collect(Collectors
						.groupingBy(groupBy_Name_type_2, countStudents));

		System.out
				.println("---------- Type - 2 ::: .groupingBy(Func_Classifier, Collectors.** )  ------------");
		System.out
				.println(student_count);

		/*
		 * ========================= GROUPING - TYPE 3 =========================
		 *
		 * ADVANCED GROUPING WITH CUSTOM MAP IMPLEMENTATION
		 *
		 * ----------------------------------------------------------------------
		 * BASIC IDEA:
		 *
		 * - Used when you want full control over:
		 *      1. How data is grouped (keyExtractor)
		 *      2. How values are processed (downstream collector)
		 *      3. Which Map implementation is used (mapFactory)
		 *
		 * ----------------------------------------------------------------------
		 * SIGNATURE:
		 *
		 * groupingBy(Function<T, K>, Supplier<Map>, Collector<T, A, D>)
		 *
		 * ----------------------------------------------------------------------
		 * PARAMETERS:
		 *
		 * 1. keyExtractor (Function<T, K>)
		 *      - Extracts the grouping key
		 *      - Defines how elements are classified into groups
		 *
		 * 2. mapFactory (Supplier<Map>)
		 *      - Controls the concrete Map implementation
		 *      - Allows customization of result container
		 *
		 *      Examples:
		 *          HashMap        → default unordered map
		 *          LinkedHashMap  → preserves insertion order
		 *          TreeMap        → sorted keys
		 *          ConcurrentMap  → thread-safe map
		 *
		 * 3. downstream (Collector)
		 *      - Defines how grouped values are processed
		 *      - Transforms List<T> into another result type (D)
		 *
		 *      Examples:
		 *          counting()
		 *          averagingDouble()
		 *          mapping()
		 *          summarizingDouble()
		 *          maxBy()
		 *
		 * ----------------------------------------------------------------------
		 * FLOW:
		 *
		 * Input   : List<T>
		 * Stream  : Stream<T>
		 * Step 1  : Extract key using keyExtractor → K
		 * Step 2  : Group elements into Map using mapFactory
		 * Step 3  : Apply downstream collector on each group
		 *
		 * ----------------------------------------------------------------------
		 * RESULT:
		 *
		 * Map<K, D>
		 *
		 * where:
		 *      K → Key type from classifier
		 *      D → Result type from downstream collector
		 *
		 * ----------------------------------------------------------------------
		 * KEY INSIGHT:
		 *
		 * - groupingBy() ALWAYS returns a Map
		 * - mapFactory controls ONLY the Map implementation, not logic
		 * - downstream controls VALUE transformation
		 * - keyExtractor controls grouping logic
		 *
		 * ======================================================================
		 */

		/**
		 * Objective:	Group Each Student's GPA!
		 * 
		 * Expected Output:	Name → List<Double>
		 */

		/**
		 *	Step 1 : What is KEY 
		 *
		 *	-	“Use Student name as the grouping key”
		 */
		Function<Student, String> name_classifier_type_3 = o -> o
				.getName();

		/**
		 * Step 2: Which MAP implementation to use
		 * 	
		 * 	-	“Store result in a HashMap”
		 */
		Supplier<Map<String, List<Double>>> which_map_to_use = () -> new HashMap<String, List<Double>>();

		/**
		 * Step 3:	What to Group + How to Group
		 * 
		 * 	-	Value transformation : Extract GPA + Downstream collector
		 * 
		 * 		.mapping( @Function_for_Value, What_to_do)
		 */
		Collector<Student, ?, List<Double>> downStream = Collectors
				.mapping(o -> o
						.getGpa(), Collectors
								.toList());

		Map<String, List<Double>> groupBy_Name = studentsList
				.stream()
				.collect(Collectors
						.groupingBy(name_classifier_type_3, which_map_to_use, downStream));

		System.out
				.println(
						"---------- Type - 3 ::: .groupingBy(Func_Classifier, Supp_mapFactory, Downstream)  ------------");
		System.out
				.println(groupBy_Name);

	}

	public static void partitioningBy_method_object_stream() {

		/**
		 * ========================= PARTITIONING IN STREAMS =========================
		 *
		 * WHAT IS PARTITIONING?
		 * ---------------------
		 * Partitioning is a special type of stream collection that divides data
		 * into exactly TWO groups based on a condition.
		 *
		 * Unlike groupingBy(), which can create multiple groups,
		 * partitioning always produces only:
		 *      - TRUE group
		 *      - FALSE group
		 *
		 * ------------------------------------------------------------------------
		 * EXAMPLES:
		 *
		 * - Students based on Gender (male / female)
		 * - Students based on GPA threshold ( > 3.8 and <= 3.8 )
		 *
		 * ------------------------------------------------------------------------
		 * INPUT / OUTPUT MODEL:
		 *
		 * Input  : Stream<T>
		 * Process: Evaluate each element using a Predicate<T>
		 * Output : Map<Boolean, ...>
		 *
		 * ------------------------------------------------------------------------
		 * TYPES OF partitioningBy:
		 *
		 * 1. partitioningBy( @Predicate<T> )
		 *
		 *      - Splits elements based on a condition
		 *      - Produces two groups: true and false
		 *
		 *      Output:
		 *          Map<Boolean, List<T>>
		 *
		 * ------------------------------------------------------------------------
		 *
		 * 2. partitioningBy( @Predicate<T>, @Collector downstream)
		 *
		 *      - Splits elements into two groups
		 *      - Applies a downstream collector to each group
		 *
		 *      Output:
		 *          Map<Boolean, D>
		 *
		 *      Example downstream collectors:
		 *          counting()
		 *          averagingDouble()
		 *          mapping()
		 *          summarizingDouble()
		 *
		 * ------------------------------------------------------------------------
		 * KEY INSIGHT:
		 *
		 * - partitioningBy ALWAYS creates exactly two partitions
		 * - Keys are always Boolean (true / false)
		 * - Value type depends on whether downstream collector is used
		 *
		 * ========================================================================
		 */

		/*
		 * Type 1: partitioningBy( @Predicate<T> )
		 * 
		 * Note: Result is always @Map<Boolean, List<T>>
		 */
		Predicate<Student> isMale = o -> o
				.getGender()
				.equals("male");

		Map<Boolean, List<Student>> splitted_gender = studentsList
				.stream()
				.collect(Collectors
						.partitioningBy(isMale));

		System.out
				.println("---------- Type - 1 ::: .partitioningBy( @Predicate<T> )  ------------");
		System.out
				.println(splitted_gender);

		/*
		 * ========================= PARTITIONING - TYPE 2 =========================
		 *
		 * partitioningBy(Predicate<T>, Collector downstream)
		 *
		 * ------------------------------------------------------------------------
		 * PURPOSE:
		 *
		 * - Splits stream elements into TWO groups (true / false)
		 * - Applies a downstream collector to each partition
		 * - Transforms default List<T> into a custom result type (D)
		 *
		 * ------------------------------------------------------------------------
		 * NOTE:
		 *
		 * - Result type is always:
		 *          Map<Boolean, D>
		 *
		 * - Where:
		 *          Boolean → result of Predicate (true / false)
		 *          D       → output type of downstream collector
		 *
		 * ------------------------------------------------------------------------
		 * OBJECTIVE (EXAMPLE):
		 *
		 * Gender → List<String> (Student Names)
		 *
		 * Steps:
		 *      1. Partition students by gender (male / female)
		 *      2. Transform Student → Student Name
		 *      3. Collect names into List<String>
		 *
		 * ------------------------------------------------------------------------
		 * KEY INSIGHT:
		 *
		 * - partitioningBy ALWAYS creates exactly 2 groups
		 * - Key is always Boolean
		 * - Value type is controlled by downstream collector
		 * - Predicate decides grouping logic (true/false split)
		 *
		 * ========================================================================
		 */
		Predicate<Student> isMale_downstream = o -> o
				.getGender()
				.equals("male");

		// Final-Map-Value Type : List<String>!
		Function<Student, String> stud_name = o -> o
				.getName();
		Collector<Student, ?, List<String>> list_of_name_collector = Collectors
				.mapping(stud_name, Collectors
						.toList());

		Map<Boolean, List<String>> result = studentsList
				.stream()
				.collect(Collectors
						.partitioningBy(isMale_downstream, list_of_name_collector));

		System.out
				.println(
						"---------- Type - 2 ::: .partitioningBy( @Predicate<T>, @Collectors downstream )  ------------");
		System.out
				.println(result);

	}

}
