package extra;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.TreeMap;
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
 * 		-	.filter()		: @Predicate
 * 
 * 		-	.map()			: @Function
 * 		-	.mapToInt()		: @ToIntFunction
 * 				// sum(), average(), min(), max(), summaryStatistics()
 * 		-	.mapToLong()	:
 * 		-	.mapToDouble()	: @ToDoubleFunction
 * 		
 * 		-	.flatMap()		:
 * 		-	.flatMapToInt()	:
 * 		-	.flatMapToLong():
 * 		-	.flatMapToDouble:
 * 		
 * 		-	.distinct()		:
 * 		-	.sorted()		: @Comparator
 * 		-	.peek()
 * 		-	.limit()
 * 		-	.skip()
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
 * 			-	.reduce()		:	(0, @BiFunction )
 * 			-	.count()
 * 			-	.min()
 * 			-	.max()
 * 			-	.findFirst()
 * 			-	.findAny()
 * 		
 * 		-	.anyMatch()
 * 		-	.allMatch()
 * 		-	.noneMatch()
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

		create_stream_primitives();
		create_stream_objects();

		mapTo_Int_Double_stream_object();

		range_rangedClosed_stream_Object();

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
