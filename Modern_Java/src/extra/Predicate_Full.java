package extra;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import section_7.data.Student;
import section_7.data.StudentDataBase;

/**
 * 	@Predicate
 * 
 * 	Number of Inputs:
 * 		-	Accepts ONLY-ONE Input
 * 
 * 	Why:
 * 		-	Code-Reusablity
 * 
 * 	Methods:
 * 		-	.test(T)	-	SAM
 * 				-	Returns @Boolean
 * 		-	.and(T)		-	Cascading AND Previous
 * 				-	Returns Another @Predicate
 * 		-	.or(T)		-	Cascading OR Previous
 * 				-	Returns Another @Predicate
 * 		-	.negate(T)	-	Cascading NOT Previous
 * 				-	Returns Another @Predicate
 * 		-	.isEqual(Object target) - 	Cascading Comparison with Previous
 * 				-	Returns Another @Predicate
 * 
 */
public class Predicate_Full {

	public static List<Student> studentsList = StudentDataBase
			.getAllStudents();

	public static void main(String[] args) {

		System.out
				.println("----------- .test(T) -----------");
		test_method();

		System.out
				.println("----------- Predicate on @List ------");
		list_predicate();

		System.out
				.println("----------- .and(P<T>) --------");
		and_method();

		System.out
				.println("----------- .or(P<T>) --------");
		or_method();

		System.out
				.println("----------- .negate(P<T>) --------");
		negate_method();

		System.out
				.println("----------- .isEqual(Object target) --------");
		isEqual_method();
	}

	private static void test_method() {

		/**
		 * Step 1: Create @Predicate
		 */
		Predicate<Integer> isEven = n -> n % 2 == 0;

		/**
		 * Step 2: use .test()
		 */
		System.out
				.println("17 is Even  Number : " + isEven
						.test(17));
		System.out
				.println("10 is Even  Number : " + isEven
						.test(10));
	}

	private static void and_method() {

		/**
		 * Objective: Print Students with Following Condition.
		 * 
		 * 	-	>= 3.8 GPA
		 * 	-	Male
		 * 
		 * 	-	Where GPA >= 3.8 @AND GENDER = 'male'
		 * 
		 * 	-	if(GPA >= 3.8 @AND GENDER == 'male')
		 */

		/**
		 * Step 1: Define @Predicate 1
		 */
		Predicate<Student> isHighGpa = o -> o
				.getGpa() >= 3.9;

		/**
		 * Step 2: Define @Predicate 2
		 */
		Predicate<Student> isMale = o -> o
				.getGender()
				.equals("male");

		/**
		 * Step 3: Use @and() method to cascade
		 */
		Predicate<Student> whr_condi_predicate = isHighGpa
				.and(isMale);

		/**
		 * Step 4: Wrap inside an @Consumer for using in @List's @forEach()
		 */
		Consumer<Student> wrapper = o -> {

			Boolean status = whr_condi_predicate
					.test(o);

			if (status) {
				System.out
						.println(o
								.getName() + " -> "
								+ o
										.getGpa()
								+ " -> " + o
										.getGender());
			}

		};

		/**
		 * Step 5: Use it over a @List of Objects
		 */
		studentsList
				.forEach(wrapper);
	}

	private static void list_predicate() {

		/**
		 * Step 1: Create @Predicate
		 */
		Predicate<Student> isOutStanding_predicate = o -> o
				.getGpa() > 3.8;

		/**
		 * Step 2: Use it in @List's @forEach()
		 * 
		 * -	Note: @forEach needs a consumer
		 */
		Consumer<Student> test_student = o -> {

			Boolean status = isOutStanding_predicate
					.test(o);

			System.out
					.println(o
							.getName() + " is " + (status ? "Out-Standing" : "Good") + " student");
		};

		/**
		 * Step 3: Use it in @List of Objects
		 */
		studentsList
				.forEach(test_student);

		/**
		 * Practical Way
		 */
		studentsList
				.forEach(o -> System.out
						.println(o
								.getName() + " is "
								+ (isOutStanding_predicate
										.test(o) ? "Out-Standing" : "Good")
								+ " student"));

	}

	private static void or_method() {

		/**
		 * Objective: Print Students with either of Condition.
		 * 
		 * 	-	"soccer" 
		 * 	-	"dancing"
		 * 
		 * 	-	Where ACTIVITY @in ('soccer', 'dancing')
		 * 
		 * 	-	if(ACTIVITY == 'soccer' @OR ACTIVITY == 'dancing')
		 */

		/**
		 * Step 1: @Predicate 1
		 * 
		 * 	-	Test, if the Student plays "soccer"
		 */
		Predicate<Student> is_playing_soccer = o -> o
				.getActivities()
				.contains("soccer");

		/**
		 * Step 2: 
		 */
		Predicate<Student> is_dancing = o -> o
				.getActivities()
				.contains("dancing");

		/**
		 * Step 3: Combine both @Predicate's
		 */
		Predicate<Student> is_soccer_or_dancing = is_playing_soccer
				.or(is_dancing);

		/**
		 * Step 4: Create wrapper @Consumer to use the @Predicate with @List 
		 */
		Consumer<Student> wrapper = o -> {

			Boolean status = is_soccer_or_dancing
					.test(o);

			if (status) {
				System.out
						.println(o
								.getName() + " -> "
								+ o
										.getActivities());
			}
		};

		/**
		 * Step 5: Use it in the list
		 */
		studentsList
				.forEach(wrapper);

		/**
		 * Practical Approach.
		 * 
		 * -	Iterate all student		
		 * 			:	studentsList.forEach(...)
		 * 
		 * -	Consume each Student	
		 * 			:	studentsList.forEach(o -> {
		 *						... 
		 * 				})
		 * 
		 * 	-	Test each Object with Predicate-chain
		 * 
		 * 			:	studentsList.forEach(o -> {
		 *						// Predicate 
		 * 				})
		 */
		studentsList
				.forEach(o -> {

					Predicate<Student> p1 = p -> p
							.getActivities()
							.contains("soccer");

					Predicate<Student> p2 = p -> p
							.getActivities()
							.contains("dancing");

					if (p1
							.or(p2)
							.test(o)) {
						System.out
								.println(o
										.getName() + " -> "
										+ o
												.getActivities());
					}
				});

	}

	private static void negate_method() {

		/**
		 * Objective: Print Students with either of Condition.
		 * 	
		 * 	->	NOT in
		 * 
		 * 	-	"soccer" 
		 * 	-	"dancing"
		 * 
		 * 	-	Where ACTIVITY @NOT_IN ('soccer', 'dancing')
		 * 
		 * 	-	if( @NOT (ACTIVITY == 'soccer' @OR ACTIVITY == 'dancing') )
		 */

		/**
		 * Step 1: @Predicate 1
		 * 
		 * 	-	Test, if the Student plays "soccer"
		 */
		Predicate<Student> is_playing_soccer = o -> o
				.getActivities()
				.contains("soccer");

		/**
		 * Step 2: 
		 */
		Predicate<Student> is_dancing = o -> o
				.getActivities()
				.contains("dancing");

		/**
		 * Step 3: Combine both @Predicate's
		 * 
		 * 	-	Reverse the testing-pass conditions using .negate()!!
		 */
		Predicate<Student> is_soccer_or_dancing = is_playing_soccer
				.or(is_dancing)
				.negate();

		/**
		 * Step 4: Create wrapper @Consumer to use the @Predicate with @List 
		 */
		Consumer<Student> wrapper = o -> {

			Boolean status = is_soccer_or_dancing
					.test(o);

			if (status) {
				System.out
						.println(o
								.getName() + " -> "
								+ o
										.getActivities());
			}
		};

		/**
		 * Step 5: Use it in the list
		 */
		studentsList
				.forEach(wrapper);

	}

	private static void isEqual_method() {

		/**
		 * Objective: Print Male-Students 
		 *
		 * 	Compare with Target-Object!	
		 */
		Predicate<String> isMale = Predicate
				.isEqual("male");

		/**
		 * 
		 */
		studentsList
				.forEach(o -> {

					if (isMale
							.test(o
									.getGender())) {
						System.out
								.println(o
										.getGender());
					}

				});

	}

}
