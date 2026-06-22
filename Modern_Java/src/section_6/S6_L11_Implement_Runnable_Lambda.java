package section_6;

/**
 * What is @Runnable Interface is used for ?
 * -	It is @FunctionalInterface
 * -	It represents a task we want to execute in a separate Thread.
 * -	
 */
public class S6_L11_Implement_Runnable_Lambda {

	public static void main(String[] args) {

		/**
		 * Approach 1 : Prior to Java 8 : AIC-Approach (Legacy Way)
		 */
		Runnable runnable_prior_java_8 = new Runnable() {
			@Override
			public void run() {
				myOwnTask_1();
			}
		};

		Thread my_thread_1 = new Thread(runnable_prior_java_8);
		my_thread_1
				.start();

		/**
		 * Approach 2 : Java 8 Lambda Syntax : Lamdba-Approach
		 */
		Runnable runnable_lambda = () -> {
			myOwnTask();
		};

		Thread my_thread_2 = new Thread(runnable_lambda);

		my_thread_2
				.start();

		/**
		 * Practical Approach ! :-)
		 * Note : 
		 * 	-	Thread() expect a @Runnable object
		 * 	-	But () -> myOwnTask() don't have @Runable specified
		 */
		new Thread(() -> myOwnTask())
				.start();
	}

	/**
	 * My Business Examples
	 */
	public static void myOwnTask_1() {

		System.out
				.println("AKA : Anonymous Inner Class Implementation (AIC) - Legacy Way !");
	}

	public static void myOwnTask() {

		System.out
				.println("AKA: Lamdba-Approach !");
	}
}
