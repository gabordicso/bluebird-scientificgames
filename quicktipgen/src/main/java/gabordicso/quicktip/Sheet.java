package gabordicso.quicktip;

import java.util.ArrayList;
import java.util.List;

public class Sheet {
	private List<Panel> panels = new ArrayList<>();

	public List<Panel> getPanels() {
		return panels;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		int currentPanel = 1;
		for (Panel panel : panels) {
			sb.append("Panel ")
				.append(currentPanel)
				.append(": ")
				.append(panel);
			currentPanel++;
		}
		return sb.toString();
	}

}
