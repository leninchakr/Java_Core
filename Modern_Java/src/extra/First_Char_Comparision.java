package extra;

public class First_Char_Comparision implements Comparable<First_Char_Comparision> {

	String value;

	public First_Char_Comparision(String value) {
		this.value = value;
	}

	@Override
	public int compareTo(First_Char_Comparision o) {

		return Character
				.compare(this.value
						.charAt(0),
						o.value
								.charAt(0));
	}

}
