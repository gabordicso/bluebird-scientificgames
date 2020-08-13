package gabordicso.quicktip.generator.output;

import java.util.Set;
import java.util.TreeSet;

public class Panel {
	private final Set<Integer> drawnNumbers = new TreeSet<>();

	public Set<Integer> getDrawnNumbers() {
		return drawnNumbers;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for (Integer i : drawnNumbers) {
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
