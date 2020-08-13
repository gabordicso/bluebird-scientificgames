package gabordicso.quicktip.generator.output;

import java.util.Set;
import java.util.TreeSet;

public class Panel {
	private final Set<Integer> checkedNumbers = new TreeSet<>();

	public Set<Integer> getCheckedNumbers() {
		return checkedNumbers;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for (Integer i : checkedNumbers) {
			if (first) {
				first = false;
			} else {
				sb.append(", ");
			}
			sb.append(i);
		}
		return sb.toString();
	}
}
