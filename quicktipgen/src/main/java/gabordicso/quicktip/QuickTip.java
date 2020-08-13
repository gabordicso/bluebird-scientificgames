package gabordicso.quicktip;

import java.util.ArrayList;
import java.util.List;

public class QuickTip {
	private List<Sheet> sheets = new ArrayList<>();

	public List<Sheet> getSheets() {
		return sheets;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int currentSheet = 1;
		for (Sheet sheet : sheets) {
			sb.append("Sheet ")
				.append(currentSheet)
				.append(":\n")
				.append(sheet);
			currentSheet++;
		}
		return sb.toString();
	}
}
