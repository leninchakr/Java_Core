package extra;

import java.util.Collections;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import section_7.data.Student;
import section_7.data.StudentDataBase;

/**
 * Java Stream Methods:
 * 
 * 1. Stream-Creation
 * 
 * 		-	.stream()
 * 		-	.parallelStream()
 * 
 * 		-	Stream.of()
 * 		-	Stream.empty()
 * 		-	Stream.generate()
 * 		-	Stream.iterate()
 * 
 * 		-	Arrays.stream()
 * 
 * 2. Intermediate Operations
 * 
 * 		-	.filter()		: @Predicate<T>
 * 
 * 		-	.map()			: @Function<T, R>
 * 		-	.mapToInt()		: @ToIntFunction<T>
 * 				// sum(), average(), min(), max(), summaryStatistics()
 * 		-	.mapToLong()	:
 * 		-	.mapToDouble()	: @ToDoubleFunction<T>
 * 		
 * 		-	.flatMap()		: @Function<List<T>, Stream<T>> 
 * 		-	.flatMapToInt()	: 
 * 		-	.flatMapToLong():
 * 		-	.flatMapToDouble:
 * 		
 * 		-	.distinct()		: na
 * 		-	.sorted()		: @Comparator.comparing()
 * 		-	.peek()			: @Consumer<T>
 * 		-	.limit()
 * 		-	.skip()
 * 
 * 		-	.takeWhile()
 * 		-	.dropWhile()
 * 		-	.boxed()
 * 		-	.unordered()
 * 
 * 		-	.sequential()
 * 		-	.parallel()
 * 		-	.onClose()	
 * 
 * 		-	.mapMulti()		:
 * 
 * 	3. Terminal Operations	
 * 
 * 		-	.forEach()		:
 * 		-	.forEachOrdered()
 * 		
 * 		-	.toList()
 * 		-	.collect()		:	Collectors.**
 * 		
 * 			-	.reduce()	:	(0, @BiFunction )
 * 			-	.count()	: na
 * 			-	.min()		: na
 * 			-	.max()		: na
 * 			-	.findFirst()
 * 			-	.findAny()
 * 		
 * 		-	.anyMatch()
 * 		-	.allMatch()
 * 		-	.noneMatch()
 * 		
 * 		- 	.findFirst()
 * 		-	.findAny() 
 * 
 * 		-	.iterator()
 * 		-	.spliterator()
 * 		-	.toArray()
 * 		
 * 		-	.close()
 * 
 * 	4. Primitive Stream-Specific Methods
 * 
 * 		:	.mapToInt/ Long/ Double
 * 		:	.flatMapToInt/ Long/ Double
 * 
 * 		-	.sum()
 * 		-	.average()
 * 		-	.summaryStatistics()
 * 
 * 		-	.range()		- IntStream/ LongStream : Outputs Streams
 * 		-	.rangeClosed()	- IntStream/ LongStream : Outputs Streams
 */
public class Stream_Api_Full {

	public static List<Student> studentsList = StudentDataBase
			.getAllStudents();

	public static void main(String[] args) {

		stream_creation_methods();
		intermediate_methods_all();
		terminal_methods_all();
		premitive_stream_method();

	}

	private static void stream_creation_methods() {

		create_stream_primitives();
		create_stream_objects();
	}

	private static void premitive_stream_method() {

		range_rangedClosed_stream_Object();
	}

	private static void intermediate_methods_all() {

		filter_map_methods();

		mapTo_Int_Double_stream_object();

		flatMap_Stream_object();

		distinct_method();

		sorted_method();

		peek_method();

		limit_skip_methods();

	}

	private static void limit_skip_methods() {

		/**
		 * 	.limit(2)
		 * 
		 * 	-	Process first-two elements in the stream
		 */
		System.out
				.println("------ .limit( Long ) ----------");

		studentsList
				.stream()
				.limit(2)
				.forEach(System.out::println);

	}

	private static void peek_method() {

		/**
		 * 	.peek( @Consumer<T> )
		 * 
		 * 	-	For debugging purpose only
		 * 	-	Will Not Affect the Stream !
		 */

		System.out
				.println("------ .peek( Consumer<Student> ) ----------");
		studentsList
				.stream()
				.peek(o -> System.out
						.println("Peek--> : " + o
								.getName()))
				.sorted(Comparator
						.comparing(Student::getGpa))
				.forEach(System.out::println);

	}

	private static void filter_map_methods() {

		/**
		 * It used @Predicate
		 */

		Predicate<Student> isMale = o -> o
				.getGender()
				.equals("male");

		List<String> male_studnets_list = studentsList
				.stream()
				.filter(isMale)
				.map(Student::getName)
				.distinct()
				.toList();

		System.out
				.println("------ .filter( Predicate ) ----------");
		System.out
				.println("Male Students List : " + male_studnets_list);

	}

	private static void sorted_method() {

		/**
		 * Sort By GPA - Descending Order
		 */
		System.out
				.println("------ .sorted( comparator ) By Gpa ----------");
		studentsList
				.stream()
				.sorted(Comparator
						.comparing(Student::getGpa, Double::compareTo))
				.forEach(System.out::println);

		/**
		 * Sort By Student-Name Descending Order
		 */
		System.out
				.println("------ .sorted( comparator ) By Name ----------");
		studentsList
				.stream()
				.sorted(Comparator
						.comparing(Student::getName, String::compareTo)
						.reversed())
				.forEach(System.out::println);

		/*********************************************************/

		/**
		 * Way 1.1 : .compare() By GPA - Descending Order
		 * 
		 * 	Manual : 
		 * 
		 * 	-	Extract Field
		 * 	-	Compare with Custom-Logic
		 * 
		 * 	-	POOR Approach :( :(
		 */
		Comparator<Student> gpa_compare_MANUAL = (o1, o2) -> Double
				.compare(o1
						.getGpa(),
						o2
								.getGpa());

		System.out
				.println("------ Way 1.1 : .sorted( comparator.reversed() ) By GPA ----------");
		studentsList
				.stream()
				.sorted(gpa_compare_MANUAL
						.reversed())
				.forEach(System.out::println);

		/**
		 * Way 1.2: @Comparator.comparing( @Function ) Descending
		 * 
		 *  Automatic : 
		 * 	-	Extract Field
		 * 	-	Compare with Custom-Logic
		 */
		System.out
				.println("------ Way 1.2 : .sorted( @Comparator.comparing(Key) ) ----------");
		studentsList
				.stream()
				.sorted(Comparator
						.comparing(Student::getGpa)
						.reversed())
				.forEach(System.out::println);

		/**
		 * Way 1.3: @Comparator.comparing( @Function, @Comparator ) Descending
		 * 
		 *  Automatic : 
		 * 	-	Extract Field
		 * 	
		 * 	Manual:
		 * 	-	Compare with Custom-Logic
		 */
		System.out
				.println("------ Way 1.3 : .sorted( @Comparator.comparing(Key, custom_logic) ) ----------");

		Comparator<Double> custom_logic_compare = (o1, o2) -> Double
				.compare(o1, o2);

		studentsList
				.stream()
				.sorted(Comparator
						.comparing(Student::getGpa, custom_logic_compare)
						.reversed())
				.forEach(System.out::println);

		/**
		 * Way 2: @List.sort( @Compartor ) Descending
		 */
		System.out
				.println("------ Way 2 : @List.sort( @Comparator.comparing(Key) ) ----------");

		studentsList
				.sort(Comparator
						.comparing(Student::getGpa)
						.reversed());

		studentsList
				.stream()
				.forEach(System.out::println);

		/*********************************************************/

		/**
		 * Way 3: @Collections.sort() - Descending
		 */
		System.out
				.println("------ Way 3 : @Collections.sort( list, comparator ) ----------");
		Collections
				.sort(studentsList, Comparator
						.comparing(Student::getGpa)
						.reversed());

		studentsList
				.stream()
				.forEach(System.out::println);

		/*********************************************************/

	}

	private static void distinct_method() {
		/**
		 * .distinct()
		 */
		List<String> all_activties_with_duplicate = studentsList
				.stream()
				.map(o -> o
						.getActivities())
				.flatMap(list -> list
						.stream())
				.toList();

		System.out
				.println("--------- .distinct().toList() ----------");
		System.out
				.println("All Activities With Duplicate : " + all_activties_with_duplicate);

		List<String> all_activities = studentsList
				.stream()
				.map(Student::getActivities)
				.flatMap(List::stream)
				.distinct()
				.toList();

		System.out
				.println("All Activities Unique : " + all_activities);

	}

	private static void terminal_methods_all() {

		count_method();

		findFirst_findAny_methods();

		match_methods();

		collect_method_HEAVY_LIFTING();

	}

	private static void collect_method_HEAVY_LIFTING() {

		common_collectors_methods();

		groupingBY_methods();

		partitioningBy_Methods();

	}

	private static void partitioningBy_Methods() {

	}

	private static void groupingBY_methods() {

		/**
		 * 	1.	(Key)	:	Input-Data-Type == VALUE-Data-Type in Map
		 * 	
		 * 	Input 	: List<T>
		 * 
		 * 	Output	: Map<K, List<T>>
		 */
		Map<String, List<Student>> male_female_group_Map = studentsList
				.stream()
				.collect(Collectors
						.groupingBy(Student::getGender));

		/**
		 * 2.	What is we want different Data-Type for VALUE
		 * 
		 * -	(key, Supplier_of_Collection)
		 * 
		 * -	groupingBy() with downstream collector
		 * 
		 * Returns	: 	Map<K, D> 
		 * 
		 * 			(where D is the result of downstream collector)
		 */

		/**
		 * 2a. Count elements in each group
		 * 
		 * 	-	Count Male/Female
		 */
		Map<String, Long> male_female_count = studentsList
				.stream()
				.collect(Collectors
						.groupingBy(Student::getGender, Collectors
								.counting()));

		System.out
				.println("--------- Collectors.groupingBy(K, downstream: count() ) ----------");
		System.out
				.println(male_female_count);

		/**
		 * 2b.	Sum values in each group
		 * 
		 * 	-	Sum of GPA for male/Female
		 */
		Map<String, Double> male_female_gpa_sum = studentsList
				.stream()
				.collect(Collectors
						.groupingBy(Student::getGender, Collectors
								.summingDouble(Student::getGpa)));

		System.out
				.println("--------- Collectors.groupingBy(K, downstream: summingDouble() ) ----------");
		System.out
				.println(male_female_gpa_sum);

		/**
		 * 2c.	Sum values in each group
		 * 
		 * 	-	Average of GPA for male/Female
		 */
		Map<String, Double> male_female_gpa_avg = studentsList
				.stream()
				.collect(Collectors
						.groupingBy(Student::getGender, Collectors
								.averagingDouble(Student::getGpa)));

		System.out
				.println("--------- Collectors.groupingBy(K, downstream: averagingDouble() ) ----------");
		System.out
				.println(male_female_gpa_avg);

		/**
		 * 2d.	Find Max in each group
		 * 
		 * -	Max value in Each Group
		 */
		Map<String, Optional<Student>> male_female_gpa_max = studentsList
				.stream()
				.collect(Collectors
						.groupingBy(Student::getGender, Collectors
								.maxBy(Comparator
										.comparing(Student::getGpa))));

		System.out
				.println("--------- Collectors.groupingBy(K, downstream: maxBy() ) ----------");
		System.out
				.println(male_female_gpa_max);

		/**
		 * 2e. Collect to Set instead of List
		 */
		Map<String, Set<Student>> male_female_group_set = studentsList
				.stream()
				.collect(Collectors
						.groupingBy(Student::getGender, Collectors
								.toSet()));
		System.out
				.println("--------- Collectors.groupingBy(K, downstream: toSet() ) ----------");
		System.out
				.println(male_female_group_set);

		/**
		 * 2g. Filter within groups (Java 9+)
		 */
		Map<String, Set<Student>> male_female_group_filtering = studentsList
				.stream()
				.collect(Collectors
						.groupingBy(Student::getGender, Collectors
								.filtering(o -> o
										.getGpa() > 4.0, Collectors
												.toSet())));
		System.out
				.println("--------- Collectors.groupingBy(K, downstream: filtering() ) ----------");
		System.out
				.println(male_female_group_filtering);

	}

	private static void common_collectors_methods() {

		/**
		 * 	1.	.toList()
		 * 
		 * 	Returns :	List<T>
		 */
		List<String> to_list_object = studentsList
				.stream()
				.map(Student::getName)
				.collect(Collectors
						.toList());
		System.out
				.println("--------- Collectors.toList() ----------");
		to_list_object
				.forEach(System.out::println);

		/**
		 * 2.	.toSet()
		 * 
		 * Returns	:	Set<T>
		 */
		Set<String> to_set_object = studentsList
				.stream()
				.map(Student::getName)
				.collect(Collectors
						.toSet());
		System.out
				.println("--------- Collectors.toSet() ----------");
		to_set_object
				.forEach(System.out::println);

		/**
		 * 3.	.toMap()	-	<K,V>
		 * 
		 * Returns	:	Map<K,V>
		 */
		Map<String, List<String>> stud_activities_map = studentsList
				.stream()
				.collect(Collectors
						.toMap(Student::getName, Student::getActivities, (o1, o2) -> o1));

		System.out
				.println("--------- Collectors.toMap() ----------");
		stud_activities_map
				.forEach((name, activities) -> {
					System.out
							.println("Student: " + name + " | Activities: " + activities);
				});

		/**
		 * 4.	.toCollection()
		 */
		LinkedList<String> names_ll = studentsList
				.stream()
				.map(Student::getName)
				.distinct()
				.collect(Collectors
						.toCollection(LinkedList::new));

		System.out
				.println("--------- Collectors.toCollection() ----------");
		names_ll
				.forEach(System.out::println);

		/**
		 * 5.	.joining
		 */
		String joined_string = studentsList
				.stream()
				.map(Student::getName)
				.collect(Collectors
						.joining(","));

		System.out
				.println("--------- Collectors.joining() ----------");
		System.out
				.println("Joined String : " + joined_string);

		/**
		 * 6. .summarizingDouble()
		 */
		DoubleSummaryStatistics dobule_stat = studentsList
				.stream()
				.collect(Collectors
						.summarizingDouble(Student::getGpa));

		System.out
				.println("--------- Collectors.summarizingDouble() ----------");
		System.out
				.println("Summarize Double : " + dobule_stat);
	}

	private static void match_methods() {

		/**
		 * 	.anyMatch( @Predicate<T> )
		 * 
		 * 	-	Returns 
		 * 			TRUE	-	At least one Element matches
		 * 			FALSE	-	No elements matches
		 */
		Predicate<Student> is_male = o -> o
				.getGender()
				.equals("male");

		Boolean is_co_ed_class = studentsList
				.stream()
				.anyMatch(is_male);
		System.out
				.println("--------- .anyMatch( @Predicate<T>) ----------");
		System.out
				.println("Is the class mixed of Male and Female : " + is_co_ed_class);

		/**
		 * 	.allMatch( @Predicate<T> )
		 * 
		 * 	-	Returns 
		 * 			-	TRUE	-	All elements must PASS
		 * 			-	FALSE	-	Else
		 */
		Predicate<Student> is_god = o -> o
				.getGender()
				.equals("god");

		Boolean is_god_in_class = studentsList
				.stream()
				.allMatch(is_god);

		System.out
				.println("--------- .allMatch( @Predicate<T>) ----------");
		System.out
				.println("Is god in class : " + is_god_in_class);

		/**
		 * 	.noneMatch( @Predicate<T> )
		 * 
		 * 	-	
		 */
		Boolean is_Not_god_in_class = studentsList
				.stream()
				.noneMatch(is_god);

		System.out
				.println("--------- .noneMatch( @Predicate<T>) ----------");
		System.out
				.println("Is NO god in class : " + is_Not_god_in_class);

	}

	private static void findFirst_findAny_methods() {

		/**
		 * -	.findFirst()
		 */

		Optional<Student> first_find = studentsList
				.stream()
				.filter(o -> o
						.getGpa() > 4)
				.findFirst();

		System.out
				.println("--------- .findFirst() ----------");
		System.out
				.println(first_find
						.get());

		/**
		 * -	.findAny()
		 * -	Returns ANY element (non-deterministic)
		 */
		Optional<Student> find_any = studentsList
				.stream()
				.findAny();

		System.out
				.println("--------- .findAny() ----------");
		System.out
				.println(find_any
						.get());

	}

	private static void count_method() {

		/**
		 * .count()
		 */

		Long total_activities = studentsList
				.stream()
				.map(Student::getActivities)
				.flatMap(List::stream)
				.distinct()
				.count();

		System.out
				.println("--------- .distinct().count() ----------");
		System.out
				.println("Total Number of Activities : " + total_activities);
	}

	private static void flatMap_Stream_object() {

		/**
		 * Basic Idea of flatMap():
		 *
		 * Input  : Stream<T>
		 * Output : Stream<R>
		 *
		 * map() produces a nested structure:
		 *    Stream<T> → Stream<List<R>> or Stream<Stream<R>>
		 *
		 * flatMap() removes this nesting by:
		 *    1. Converting each element into a Stream
		 *    2. Flattening all resulting Streams into a single Stream
		 *
		 * Example:
		 *    Student_Stream
		 *        → map(Student::getActivities)
		 *        → Stream<List<String>>
		 *        → flatMap(List::stream)
		 *        → Stream<String> (flattened activities)
		 *
		 * Result:
		 *    A single continuous stream of elements instead of nested collections.
		 */
		//	List<String> → Stream<String>
		Function<List<String>, Stream<String>> activities_stream = o -> o
				.stream();

		List<String> list_of_activites = studentsList
				.stream()						// 	Stream<Student>
				.map(Student::getActivities)	//	Stream<List<String>>
				.flatMap(activities_stream)		//	Stream<String>
				.distinct()						//	Remove Duplicates
				.toList();

		System.out
				.println("------- .flatMap(List::Stream) -------");
		System.out
				.println("List of Full Activities : " + list_of_activites);

		/**
		 * Another Example
		 */
		List<String> batch_1 = List
				.of("1", "2", "3", "4", "5");
		List<String> batch_2 = List
				.of("6", "7", "8", "9", "10");

		List<List<String>> full_batch = List
				.of(batch_1, batch_2);

		List<String> full_flat = full_batch
				.stream()
				.flatMap(o -> o
						.stream())
				.distinct()
				.toList();

		System.out
				.println("------- .flatMap(List::Stream) -------");
		System.out
				.println("List of Full Batch : " + full_flat);

	}

	private static void create_stream_objects() {

		/**
		 *  Stream.of(o1, o2, o3. o4) - .partitioningBy(P, C)
		 */
		Student stud_1 = studentsList
				.get(0);
		Student stud_2 = studentsList
				.get(1);
		Student stud_3 = studentsList
				.get(2);
		Student stud_4 = studentsList
				.get(3);
		Student stud_5 = studentsList
				.get(5);

		Stream<Student> all_stud_stream = Stream
				.of(stud_1, stud_2, stud_3, stud_4, stud_5);

		// True/False Condition of Partitioning
		Predicate<Student> isMale = o -> o
				.getGender()
				.equals("male");

		Collector<Student, ?, List<String>> name_list = Collectors
				.mapping(Student::getName, Collectors
						.toList());

		Map<Boolean, List<String>> male_female_map = all_stud_stream
				.collect(Collectors
						.partitioningBy(isMale, name_list));

		System.out
				.println("------- Stream<Object> Stream.of(..., ...., ...., ....) -------");
		System.out
				.println("Stream.of() Objects with partitioning : " + male_female_map);

		/**
		 *  Stream.of(o1, o2, o3. o4) - .groupingBy(F)
		 */
		Stream<Student> all_stud_stream_again = Stream
				.of(stud_1, stud_2, stud_3, stud_4, stud_5);

		Map<String, List<Student>> group_by_gender = all_stud_stream_again
				.collect(Collectors
						.groupingBy(Student::getGender));

		System.out
				.println("------- Stream<Object> Stream.of(..., ...., ...., ....) -------");
		System.out
				.println("Stream.of() Objects with Group-By Geneder : " + group_by_gender);

		/**
		 *  Stream.of(o1, o2, o3. o4) - .groupingBy(F, D)
		 *  
		 *  -	By Default, Java gives @HashMap<K, D>
		 */
		Stream<Student> all_stud_stream_again_2 = Stream
				.of(stud_1, stud_2, stud_3, stud_4, stud_5);

		// Custom-Value Type
		Collector<Student, ?, List<String>> diff_val_type = Collectors
				.mapping(Student::getName, Collectors
						.toList());

		Map<String, List<String>> group_by_gender_2 = all_stud_stream_again_2
				.collect(Collectors
						.groupingBy(Student::getGender, diff_val_type));

		System.out
				.println("------- Stream<Object> Stream.of(..., ...., ...., ....) -------");
		System.out
				.println("Stream.of() Objects with Group-By Geneder + Custom-Value : " + group_by_gender_2);

		/**
		 *  Stream.of(o1, o2, o3. o4) - .groupingBy(F, D)
		 *  
		 *  -	if we want , @TreeMap<K, D>
		 */
		Stream<Student> all_stud_stream_again_3 = Stream
				.of(stud_1, stud_2, stud_3, stud_4, stud_5);

		// Which Value-Type to form
		Collector<Student, ?, List<String>> list_of_string_value = Collectors
				.mapping(Student::getName, Collectors
						.toList());

		// Output should be @TreeMap<K, D>. Not @HashMap<K, D>

		TreeMap<String, List<String>> tree_map_gender = all_stud_stream_again_3
				.collect(Collectors
						.groupingBy(Student::getGender, TreeMap::new, list_of_string_value));

		System.out
				.println("------- Stream<Object> Stream.of(..., ...., ...., ....) -------");
		System.out
				.println("Stream.of() Objects with Group-By Geneder + Custom-Value + TreeMap: " + tree_map_gender);

	}

	private static void create_stream_primitives() {

		/**
		 * Create a Stream of String
		 */
		Stream<String> str_stream = Stream
				.of("one", "two", "three", "four", "five");

		String joined_str = str_stream
				.collect(Collectors
						.joining(",", "<", ">"));

		System.out
				.println("------- Stream<String> Stream.of(..., ...., ...., ....) -------");
		System.out
				.println("Collectors.Joining(comma) : " + joined_str);

		/**
		 * Create a Stream of Double
		 */
		Stream<Double> double_stream = Stream
				.of(1.2, 3.4, 5.6, 7.8, 9.10, 11.12, 12.13);

		Double sum_of_doudble = double_stream
				.reduce(0.0, (a, b) -> a + b);
		System.out
				.println("------- Stream<Double> Stream.of(..., ...., ...., ....) -------");
		System.out
				.println("Sum of Double : " + sum_of_doudble);
	}

	private static void range_rangedClosed_stream_Object() {

		/**
		 * 	.range(a, b)	:	a to (b-1)
		 */
		int sum_range = IntStream
				.range(1, 101)
				.sum();

		System.out
				.println("------- IntStream.range() -------");
		System.out
				.println("Sum of 1 to 100 : " + sum_range);

		/**
		 * 	.rangeClosed(a, b)	:	a to b
		 */
		Long sum_range_closed = LongStream
				.rangeClosed(1, 100)
				.sum();

		System.out
				.println("------- LongStream.rangeClosed() -------");
		System.out
				.println("Sum of 1 to 100 : " + sum_range_closed);
	}

	private static void mapTo_Int_Double_stream_object() {

		/**
		 * -	map(Student::getGradeLevel) 		-> 	Returns Stream<Integer>
		 * -	mapToInt(Student::getGradeLevel)	->	Returns IntStream
		 * 		
		 * 			-	So we can use "4. Primitive Stream-Specific Methods"
		 */

		// .count()
		Long element_count = studentsList
				.stream()
				.mapToInt(Student::getGradeLevel)
				.count();

		System.out
				.println("-------- .mapToInt().count() --------");
		System.out
				.println("Total Students : " + element_count);

		// .max()
		OptionalInt max_grade_of_students = studentsList
				.stream()
				.mapToInt(Student::getGradeLevel)
				.max();

		System.out
				.println("-------- .mapToInt().max() --------");
		System.out
				.println("Max Grade of Students : " + max_grade_of_students
						.getAsInt());

		// .min()
		OptionalInt min_grade_of_students = studentsList
				.stream()
				.mapToInt(Student::getGradeLevel)
				.min();

		System.out
				.println("-------- .mapToInt().min() --------");
		System.out
				.println("Min Grade of Students : " + min_grade_of_students
						.getAsInt());

		// .sum()
		Double sum_of_all_grades = studentsList
				.stream()
				.mapToDouble(Student::getGpa)
				.sum();

		System.out
				.println("-------- .mapToIDouble().sum() --------");
		System.out
				.println("Min Grade of Students : " + sum_of_all_grades);

		// .average()
		OptionalDouble class_avg_gpa = studentsList
				.stream()
				.mapToDouble(Student::getGpa)
				.average();

		System.out
				.println("-------- .mapToIDouble().average() --------");
		System.out
				.println("Average GPA of Class : " + class_avg_gpa
						.getAsDouble());

		// .summaryStatistics()
		DoubleSummaryStatistics double_summary_stat = studentsList
				.stream()
				.mapToDouble(Student::getGpa)
				.summaryStatistics();

		System.out
				.println("-------- .mapToIDouble().summaryStatistics() --------");
		System.out
				.println("Summary State : " + double_summary_stat);
	}
}
