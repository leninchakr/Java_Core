package extra;

@FunctionalInterface
public interface Sample_Interface<T> {

	/*
	 * Type 1 : Abstract method
	 */
	public T some_logic(T o);

	/*
	 * Type 2 : 'Static' method
	 * 
	 * - Called by using @Interface name
	 * - Can't be over-ridden by any class
	 * - Can't be called by any object of the Class!
	 */
	public static void one_time_implemented() {
		System.out
				.println("Called by using @Interface name");

		System.out
				.println("Can't be over-ridden by any Class");
	}

	/*
	 * Type 3 : 'Default' method
	 * 
	 * - Called by using @Interface name or Object of the Class
	 * - Can BE over-ridden by any class
	 * - Can BE called by any object of the Class!
	 */
	public default void any_time_implemented_by_class() {
		System.out
				.println("Called by using @Interface name or Object of the Class");

		System.out
				.println("Can't be over-ridden by any Class");

		System.out
				.println("Can BE called by any object of the Class!");
	}

	/*
	 * Type 4 : 'Private' method
	 * 
	 * - Helper methods
	 * - Can be called within @Interface's static/defalut/private methods only.
	 */
	private void helper_method() {
		System.out
				.println("Helper methods");
		System.out
				.println("Can be called within @Interface's static/defalut/private methods only.");
	}
}
