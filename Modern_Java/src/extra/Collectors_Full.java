package extra;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
* Common output forms:
*      List, Set, Map, String, Numbers, Statistics
*
* ======================================================================
* MAJOR CATEGORIES OF COLLECTORS (WITH OVERLOADS - JAVA 21)
* ======================================================================
*
* 1. COLLECTION CREATION
* ----------------------------------------------------------------------
*
* toList()
*      - toList()
*
* toSet()
*      - toSet()
*
* toCollection()
*      - toCollection(Supplier<C> collectionFactory)
*
* IMMUTABLE COLLECTIONS:
*      toUnmodifiableList()
*          - toUnmodifiableList()
*
*      toUnmodifiableSet()
*          - toUnmodifiableSet()
*
*      toUnmodifiableMap()
*          - toUnmodifiableMap(keyMapper, valueMapper)
*          - toUnmodifiableMap(keyMapper, valueMapper, mergeFunction)
*
* ----------------------------------------------------------------------
*
* 2. MAP CREATION
* ----------------------------------------------------------------------
*
* toMap() OVERLOADS:
*      - toMap(keyMapper, valueMapper)
*      - toMap(keyMapper, valueMapper, mergeFunction)
*      - toMap(keyMapper, valueMapper, mergeFunction, mapFactory)
*
* toConcurrentMap() OVERLOADS:
*      - toConcurrentMap(keyMapper, valueMapper)
*      - toConcurrentMap(keyMapper, valueMapper, mergeFunction)
*      - toConcurrentMap(keyMapper, valueMapper, mergeFunction, mapFactory)
*
* ----------------------------------------------------------------------
*
* 3. GROUPING
* ----------------------------------------------------------------------
*
* groupingBy() OVERLOADS:
*      - groupingBy(classifier)
*      - groupingBy(classifier, downstream)
*      - groupingBy(classifier, mapFactory, downstream)
*
* groupingByConcurrent() OVERLOADS:
*      - groupingByConcurrent(classifier)
*      - groupingByConcurrent(classifier, downstream)
*      - groupingByConcurrent(classifier, mapFactory, downstream)
*
* ----------------------------------------------------------------------
*
* 4. PARTITIONING
* ----------------------------------------------------------------------
*
* partitioningBy() OVERLOADS:
*      - partitioningBy(predicate)
*      - partitioningBy(predicate, downstream)
*
* OUTPUT ALWAYS:
*      Map<Boolean, T> OR Map<Boolean, D>
*
* ----------------------------------------------------------------------
*
* 5. AGGREGATION / STATISTICS
* ----------------------------------------------------------------------
*
* counting()
*      - counting()
*
* minBy()
*      - minBy(Comparator comparator)
*
* maxBy()
*      - maxBy(Comparator comparator)
*
* averagingInt()
*      - averagingInt(ToIntFunction mapper)
*
* averagingLong()
*      - averagingLong(ToLongFunction mapper)
*
* averagingDouble()
*      - averagingDouble(ToDoubleFunction mapper)
*
* summarizingInt()
*      - summarizingInt(ToIntFunction mapper)
*
* summarizingLong()
*      - summarizingLong(ToLongFunction mapper)
*
* summarizingDouble()
*      - summarizingDouble(ToDoubleFunction mapper)
*
* ----------------------------------------------------------------------
*
* 6. STRING JOINING
* ----------------------------------------------------------------------
*
* joining() OVERLOADS:
*      - joining()
*      - joining(CharSequence delimiter)
*      - joining(CharSequence delimiter,
*                CharSequence prefix,
*                CharSequence suffix)
*
* ----------------------------------------------------------------------
*
* 7. REDUCTION
* ----------------------------------------------------------------------
*
* reducing() OVERLOADS:
*      - reducing(BinaryOperator<T> op)
*      - reducing(T identity, BinaryOperator<T> op)
*      - reducing(U identity,
*                 Function<T,U> mapper,
*                 BinaryOperator<U> op)
*
* ----------------------------------------------------------------------
*
* 8. TRANSFORMATION / DOWNSTREAM COLLECTORS
* ----------------------------------------------------------------------
*
* mapping()
*      - mapping(Function<T,U> mapper,
*               Collector<U,A,R> downstream)
*
* filtering() (Java 9+)
*      - filtering(Predicate<T> predicate,
*                 Collector<T,A,R> downstream)
*
* flatMapping() (Java 9+)
*      - flatMapping(Function<T,Stream<U>> mapper,
*                    Collector<U,A,R> downstream)
*
* collectingAndThen()
*      - collectingAndThen(Collector<T,A,R> downstream,
*                         Function<R,RR> finisher)
*
* ----------------------------------------------------------------------
*
* 9. ADVANCED COMBINER
* ----------------------------------------------------------------------
*
* teeing() (Java 12+)
*      - teeing(Collector<T,?,R1> c1,
*               Collector<T,?,R2> c2,
*               BiFunction<R1,R2,R> merger)
*
* ----------------------------------------------------------------------
*
* SUMMARY:
* ----------------------------------------------------------------------
* Collectors = Stream final-stage transformation toolkit:
*      - Collection creation
*      - Map creation
*      - Grouping / Partitioning
*      - Aggregation
*      - Reduction
*      - Transformation
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
		 * 2. MAP CREATION
		 */
		Map_Creation_methods();

		/**
		 * 8. TRANSFORMATION / DOWNSTREAM COLLECTORS
		 */
		transform_downstream_methods();

		/**
		 * 5. AGGREGATION / STATISTICS
		 */
		aggregation_stat_methods();

		/**
		 * 7. REDUCTION
		 */
		reduction_methods();

		/**
		 * 6. STRING JOINING
		 */
		joining_methods();
	}

	private static void joining_methods() {

		/**
		 * 	-	()
		 * 	-	(delimiter)
		 * 	-	(delimiter, prefix, suffix)
		 */

		/**
		 * 1
		 */
		String join_no_delimiter = studentsList
				.stream()
				.map(Student::getName)
				.collect(Collectors
						.joining());
		System.out
				.println("----------- Collectors.joining() -----------");
		System.out
				.println(join_no_delimiter);

		/**
		 * 2
		 */
		String join_comma_delimieter = studentsList
				.stream()
				.map(Student::getName)
				.collect(Collectors
						.joining(","));

		System.out
				.println("----------- Collectors.joining(delimieter) -----------");
		System.out
				.println(join_comma_delimieter);

		/**
		 * 3
		 */
		String join_prefix_comma_suffix = studentsList
				.stream()
				.map(Student::getName)
				.collect(Collectors
						.joining(",", "<", ">"));

		System.out
				.println("----------- Collectors.joining(delimieter, prefix, suffix) -----------");
		System.out
				.println(join_prefix_comma_suffix);

	}

	private static void reduction_methods() {

		/**
		 * 	Important : @.reduce() must return the same type as stream element
		 * 
		 *      - ( @BinaryOperator<T> op)
		 *      - ( @T identity, @BinaryOperator<T> op)
		 *      - ( @U identity, @Function<T,U> mapper, @BinaryOperator<U> op)
		 */

		/**
		 * 1
		 */
		Optional<Double> sum_1 = studentsList
				.stream()
				.map(o -> o
						.getGpa())
				.collect(Collectors
						.reducing((a, b) -> a + b));

		System.out
				.println("----------- Collectors.reducing( @BinaryOperator ) -----------");
		System.out
				.println(sum_1
						.get());

		/**
		 * 2	:	This approach is to 
		 * 				-	sets initial-value for the reduction
		 * 				-	avoid @Optional non-sense !
		 */
		Double sum_2 = studentsList
				.stream()
				.map(o -> o
						.getGpa())
				.collect(Collectors
						.reducing(0.0, (a, b) -> a + b));

		System.out
				.println("----------- Collectors.reducing( @T identity, @BinaryOperator<T> op ) -----------");
		System.out
				.println(sum_2);

		/**
		 * 3	:	This approach is to 
		 * 				-	sets initial-value for the reduction
		 * 				-	avoid @Optional non-sense !
		 * 				-	avoid .map() call
		 */
		Double sum_3 = studentsList
				.stream()
				//				.map(o -> o
				//						.getGpa())
				.collect(Collectors
						.reducing(0.0, o -> o
								.getGpa(), (a, b) -> a + b));

		System.out
				.println(
						"----------- Collectors.reducing( @U identity, @Function<T,U> mapper, @BinaryOperator<U> op ) -----------");
		System.out
				.println(sum_3);
	}

	private static void aggregation_stat_methods() {

		/**
		 * ========================= 5. AGGREGATION / STATISTICS =========================
		 *
		 * PURPOSE:
		 * - Used to compute single result from stream elements
		 * - Examples: count, sum, average, min, max, statistics
		 *
		 * INPUT  : Stream<T>
		 * OUTPUT : Single value (Long / Double / Optional<T> / Statistics)
		 *
		 * ======================================================================
		 *
		 * 1. counting()
		 * - Counts number of elements
		 * - Return: Long
		 *
		 * 2. minBy() / maxBy()
		 * - Finds min / max element using Comparator
		 * - Return: Optional<T>
		 *
		 * 3. averagingInt / Long / Double
		 * - Calculates average of numeric values
		 * - Return: Double
		 *
		 * 4. summingInt / Long / Double
		 * - Calculates sum of values
		 * - Return: primitive number (int/long/double)
		 *
		 * 5. summarizingInt / Long / Double
		 * - Gives full stats (count, sum, min, max, avg)
		 * - Return: SummaryStatistics object
		 *
		 * 6. reducing()
		 * - Custom aggregation logic
		 * - Return: Optional<T> or custom result
		 *
		 * ======================================================================
		 *
		 * KEY IDEA:
		 * - Converts many elements → one result
		 * - Used for analytics, reporting, calculations
		 *
		 * ======================================================================
		 */

		counting_from_stream_object();
		minBy_maxby_from_stream_object();
		averageDouble_from_stream_object();
		summingDouble_from_stream_object();
		summarizingDouble_from_stream_object();

	}

	private static void summarizingDouble_from_stream_object() {

		DoubleSummaryStatistics double_stat = studentsList
				.stream()
				.collect(Collectors
						.summarizingDouble(o -> o
								.getGpa()));

		System.out
				.println("----------- Collectors.summarizingDouble( @Function ) -----------");
		System.out
				.println("Count : " + double_stat
						.getCount());
		System.out
				.println("Max : " + double_stat
						.getMax());
		System.out
				.println("Min : " + double_stat
						.getMin());
	}

	private static void summingDouble_from_stream_object() {

		Double total_gpa_class = studentsList
				.stream()
				.collect(Collectors
						.summingDouble(o -> o
								.getGpa()));

		System.out
				.println("----------- Collectors.summingDouble( @Function ) -----------");
		System.out
				.println(total_gpa_class);

	}

	private static void averageDouble_from_stream_object() {

		Double avg_gpa_class = studentsList
				.stream()
				.collect(Collectors
						.averagingDouble(o -> o
								.getGpa()));

		System.out
				.println("----------- Collectors.averagingDouble( @Function ) -----------");
		System.out
				.println(avg_gpa_class);

	}

	private static void minBy_maxby_from_stream_object() {

		Comparator<Student> min_max_comparator = (o1, o2) -> Double
				.compare(o1
						.getGpa(),
						o2
								.getGpa());

		Optional<Student> low_gpa_stud = studentsList
				.stream()
				.collect(Collectors
						.minBy(min_max_comparator));

		System.out
				.println("----------- Collectors.minBy( @Comparator ) -----------");
		System.out
				.println(low_gpa_stud
						.get());

		Optional<Student> high_gpa_stud = studentsList
				.stream()
				.collect(Collectors
						.maxBy(min_max_comparator));

		System.out
				.println("----------- Collectors.maxBy( @Comparator ) -----------");
		System.out
				.println(high_gpa_stud
						.get());

	}

	private static void counting_from_stream_object() {

		Long count = studentsList
				.stream()
				.collect(Collectors
						.counting());

		System.out
				.println("----------- Collectors.counting() -----------");
		System.out
				.println(count);

	}

	private static void transform_downstream_methods() {

		mapping_method_downstreamm_collector();

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
		 *      - Eg:
		 *      		male   → List<Student>
		 *      		female → List<Student>
		 *
		 * 2. groupingBy(Function classifier, Collector downstream)
		 *      → Map<K, D>   (D depends on downstream collector)
		 *      
		 *      - Eg:
		 *      		male   → [Adam, John]
		 *      		female → [Jenny, Emily]
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

	public static void mapping_method_downstreamm_collector() {

		/**
		 * ========================= COLLECTORS.mapping() =========================
		 *
		 * .mapping() is a downstream collector used inside other collectors.
		 *
		 * ------------------------------------------------------------------------
		 * PURPOSE:
		 *
		 * - Used when data is already being collected (grouped/partitioned)
		 * - Performs transformation + collection in a single step
		 *
		 * ------------------------------------------------------------------------
		 * SIGNATURE:
		 *
		 * Collectors.mapping(Function<T, U>, Collector<U, A, R>)
		 *
		 * ------------------------------------------------------------------------
		 * MEANING:
		 *
		 * - Function<T, U>
		 *      → Transforms each input element (T → U)
		 *
		 * - Collector<U, A, R>
		 *      → Defines how transformed elements are collected
		 *
		 * ------------------------------------------------------------------------
		 * CORE IDEA:
		 *
		 * - First transform elements
		 * - Then collect them using a downstream collector
		 *
		 * ------------------------------------------------------------------------
		 * WHERE IS mapping() USED?
		 *
		 * 1. Inside groupingBy()
		 *      - To transform grouped values
		 *      - Example: Student → Student Name
		 *
		 * 2. Inside partitioningBy()
		 *      - To transform partitioned values
		 *      - Example: Student → Student Name / ID / GPA
		 *
		 * ------------------------------------------------------------------------
		 * KEY INSIGHT:
		 *
		 * - mapping() is NOT a standalone collector
		 * - It always works as a helper inside other collectors
		 * - It avoids extra Stream.map() before collecting
		 *
		 * ========================================================================
		 */

		// Common in this example
		Collector<Student, ?, List<String>> downstream = Collectors
				.mapping(Student::getName, Collectors
						.toList());

		/**
		 * 	-	groupingBy(..., downstream) example
		 */
		Map<String, List<String>> gender_cat_list = studentsList
				.stream()
				.collect(Collectors
						.groupingBy(o -> o
								.getGender(), downstream));

		System.out
				.println("---------- Type - 1 ::: .groupingBy(..., downstream) ------------");
		System.out
				.println(gender_cat_list);

		/**
		 * PartitionBy(...., downstream) example
		 */
		Map<Boolean, List<String>> male_female = studentsList
				.stream()
				.collect(Collectors
						.partitioningBy(o -> o
								.getGender()
								.equals("male"), downstream));

		System.out
				.println("---------- Type - 2 ::: .partitionBy(..., downstream) ------------");
		System.out
				.println(male_female);
	}

}
