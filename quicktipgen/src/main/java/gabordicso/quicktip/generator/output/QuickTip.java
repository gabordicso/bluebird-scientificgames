package gabordicso.quicktip.generator.output;

import java.util.ArrayList;
import java.util.List;

import gabordicso.quicktip.generator.params.QuickTipGeneratorParams;

public class QuickTip {
	private final QuickTipGeneratorParams params;
	
	private List<Sheet> sheets = new ArrayList<>();

	public List<Sheet> getSheets() {
		return sheets;
	}
	
	public QuickTip(final QuickTipGeneratorParams params) {
		this.params = params;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder("Params used for generating sheets: ");
		sb.append(params);
		sb.append("\nGenerated Sheets:\n");
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

	public boolean containsPanel(Panel panel) {
		if (panel != null) {
			for (Sheet sheet : sheets) {
				if (sheet.containsPanel(panel)) {
					return true;
				}
			}
		}
		return false;
	}
}
