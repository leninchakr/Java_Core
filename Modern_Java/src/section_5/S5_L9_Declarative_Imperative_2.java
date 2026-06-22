package section_5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Problem : Remove Duplicates from List<Integer> "1, 2, 3, 3, 4, 5, 5, 6, 7, 8,
 * 8, 9, 10"
 */

public class S5_L9_Declarative_Imperative_2 {

	public static void main(String[] args) {

		/**
		 * Create input list
		 */
		List<Integer> integerList = Arrays.asList(1, 2, 3, 3, 4, 5, 5, 6, 7, 8, 8, 9, 10);

		/**
		 * Imperative Approach
		 */
		List<Integer> uniqueList = new ArrayList<Integer>();

		for (Integer elem : integerList) {

			if (!uniqueList.contains(elem)) {
				uniqueList.add(elem);
			}
		}

		System.out.println("Unique List by Imperative Programming  : " + uniqueList);

		/**
		 * Declarative Approach
		 */
		List<Integer> uniqueList_1 = integerList.stream().distinct().collect(Collectors.toList());

		System.out.println("Unique List by Declarative Programming : " + uniqueList_1);

	}
}
