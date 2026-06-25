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
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import section_7.data.Student;
import section_7.data.StudentDataBase;

/**
 * What it is:
 * 
 * 		-	It is helper-class to collect/consolidate data
 * 		-	It has many @static methods	
 * 		-	It is @final class
 * 		-	It doesn't depend on any @Interface / @Class	
 * 
 * Why we need it:
 * 
 * 		-	Mostly used with @.collect( @Collectors )
 * 		-	To consolidate data in some-form
 * 		-	Eg:
 * 				-	@List
 * 				-	@Set
 * 				-	@Map
 * 				-	@String
 * 				-	@Number
 * 
 * Methods:
 * 		1.	.toList()
 * 				-	List<input_type> = .collect(Collectors.toList())
 * 				-	The returned @List is @Immutable by default.
 * 		2.	.toSet()
 * 				-	Same as .toList()
 * 				-	But won't add a value, if it already exits 
 * 
 * Methods : 8 major categories
 * 
 * 		1.	Collection Creation						
 * 				->	toList(), toSet(), toCollection()
 * 		2.	Map Creation
 * 				->	toMap(), toCollectionMap()
 * 		3.	Grouping
 * 		4.	Partitioning
 * 		5.	Aggregation / Statistics
 * 		6.	String Joining
 * 		7.	Reduction
 * 		8.	Downstream / Transformation Collectors
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

		groupingBy_method_object_stream();

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
		 * What is does:
		 * 		-	Group based on F_Key
		 * 		-	RT : Map< F_Key, List<T> >
		 * 
		 * 	Syntax: (6)
		 * 		-	( @Function keyExtractor)
		 * 		-	( @Function keyExtractor, @Supplier mapFactory, @Collector ds)
		 */

		/**
		 *  Type - 1:	( @Function keyExtractor)
		 *  	-	Return Type : Map< F_Key, List<T> >
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

		/**
		 *  Type - 2:
		 *  
		 *  	-	( @Function keyExtractor, @Supplier mapFactory, @Collector Interface ds)
		 *  
		 *  		:	keyExtractor
		 *  			-	Key ( @Function) for key-selection
		 *  		:	mapFactory 
		 * 				-	@Supplier decides the Map implementation
		 * 				-	Eg:
		 * 					HashMap / TreeMap / LinkedHashMap / ConcurrentMap
		 * 			:	ds
		 * 				-	What happens to the Grouped-Values
		 * 				-	Eg:
		 * 					count/ average/ max/ mapping/ summarizing	
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
		Function<Student, String> name_classifier_type_2 = o -> o
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
		Function<Student, Double> gpa_val = o -> o
				.getGpa();

		Collector<Student, ?, List<Double>> downStream = Collectors
				.mapping(gpa_val, Collectors
						.toList());

		Map<String, List<Double>> groupBy_Name = studentsList
				.stream()
				.collect(Collectors
						.groupingBy(name_classifier_type_2, which_map_to_use, downStream));

		System.out
				.println("---------- Type - 2 ::: .groupingBy(Func_Classifier, Supp_mapFactory, ds)  ------------");
		System.out
				.println(groupBy_Name);

	}

}
