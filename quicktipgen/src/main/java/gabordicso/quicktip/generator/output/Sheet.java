package gabordicso.quicktip.generator.output;

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
				.append(": [")
				.append(panel)
				.append("]\n");
			currentPanel++;
		}
		return sb.toString();
	}

	public boolean containsPanel(Panel panel) {
		if (panel != null) {
			String panelStr = panel.toString();
			for (Panel currentPanel : panels) {
				String currentPanelStr = currentPanel.toString();
				if (currentPanelStr == panelStr) {
					return true;
				}
			}
		}
		
		return false;
	}
}
