package extra;

import java.util.List;
import java.util.stream.Collectors;

import section_7.data.StudentDataBase;

/**
 * @Collector<T, A, R> - Interface
 * 
 * Parameters:
 * 	-	T	->	Input-Type			
 * 			: 	What comes from Stream. 
 * 			:	Eg: 
 * 					-	Stream<String>
 * 					-	Stream<Integer>
 * 					-	Stream<Employee::GetName>
 * 
 * 	-	A	->	Accumulation-Type	
 * 			: A MUTABLE-Container to accumulate data from stream
 * 			:	Eg:
 * 					-	ArrayList
 * 					-	HashSet
 * 					-	StringBuilder
 * 					-	Map
 * 	
 * 	-	R	->	Result-Type			: What is container-datatype
 * 
 * Why This Interface
 * 		:	To Accumulate-Data from Stream in Specific-Way
 * 
 * 
 * Syntax:
 * 	-	stream.collect( @Collector )
 * 
 * A Collector defines 4 main behaviors:
 * 
 * 	(1)	-	Create a Container
 * 				-	A Container = supplier.get()
 * 				-	Eg:	ArrayList<String>
 * 
 * 	(2)	-	Add Elements (Accumulator)
 * 				-	accumulator.accept( @Container, @Element-From-Stream )
 * 				-	list.add( @Element)
 * 
 * 	(3)	-	Merge (Combine) - { only for Parallel-Stream }
 * 		
 * 	(4)	-	Finisher
 * 				-	Convert working @Container -> @Result
 * 				-	R result = finisher.apply(container)
 * 				-	Eg:	Collections.unmodifiableList(list)
 * 
 * 	-	
 * 	Visual Model:
 * 
 * 		Stream<T>
 * 			|
 * 		Supplier -> A (empty container)
 * 			|
 * 		Accumulator (fill it)
 * 			|
 * 		Combiner	(if, parallel-stream)
 * 			|
 * 		Finisher
 * 			|
 * 			R (final result)
 * 	
 * 	Why Collection is designed in this way:
 * 
 * 		-	Because collection is NOT single operation
 * 		-	It is FOUR-Step-Protocol
 * 
 * Important Fact:
 * 	
 * 	-	You rarely implement Collector yourself.
 * 	-	You almost never use the Collector interface directly.
 * 	-	Instead use @Collectors
 */
public class Collector_Full {

	public static List<section_7.data.Student> studentsList = StudentDataBase
			.getAllStudents();

	public static void main(String[] args) {

	}
}
