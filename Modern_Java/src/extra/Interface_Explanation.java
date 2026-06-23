package extra;

public class Interface_Explanation {

	public static void main(String[] args) {

		/**
		 * 1. Create Object from Lamda-Expression
		 */
		Interface_All_Method_Types<Integer> sample_inter_obj_lambda = (a) -> a * a;

		System.out
				.println("Square of 7 is " + sample_inter_obj_lambda
						.some_logic(7));

		/**
		 * 2. Calling 'Static' Methods 
		 */
		// Valid ! :)
		Interface_All_Method_Types
				.one_time_implemented();

		// In-valid! :(
		// 'Static' Method can be accessed by only using @Interface Name. 
		//	Not by any object
		sample_inter_obj
				.one_time_implemented();

		/**
		 * 3. @OverRide 'Default' Methods
		 * - Lambda Expression cannot be used
		 * - Use AIC instead
		 */
		Interface_All_Method_Types<Integer> sample_inter_obj_aic = new Interface_All_Method_Types<Integer>() {

			/**
			 * Abstract method implementation
			 */
			@Override
			public Integer some_logic(Integer o) {
				return o * o;
			}

			/**
			 * 'Default' method is over-ridden here!
			 */
			@Override
			public void any_time_implemented_by_class() {

				System.out
						.println("Defalut Method is over-ridden in Class-Object");
			}

		};

		/**
		 * 4. Calling 'Default' method using @Interface name. Not using Object!
		 * - Not allwoed
		 */
		Interface_All_Method_Types
				.any_time_implemented_by_class();

		/**
		 * 5. Calling 'private' method in Class
		 * - Not allowed
		 */
		sample_inter_obj_aic
				.helper_method();
	}

}
