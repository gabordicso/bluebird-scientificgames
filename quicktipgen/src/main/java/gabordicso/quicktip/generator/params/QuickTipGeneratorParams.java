package gabordicso.quicktip.generator.params;

import gabordicso.quicktip.generator.params.alg.QuickTipAlgParams;

public class QuickTipGeneratorParams {

	private final int sheetCount;
	private final boolean allowOverlappingSheets;
	private final QuickTipAlgParams algParams;

	public QuickTipGeneratorParams(final int sheetCount, final boolean allowOverlappingSheets, final QuickTipAlgParams algParams) {
		this.sheetCount = sheetCount;
		this.allowOverlappingSheets = allowOverlappingSheets;
		this.algParams = algParams;
	}
	
	public int getSheetCount() {
		return sheetCount;
	}

	public boolean getAllowOverlappingSheets() {
		return allowOverlappingSheets;
	}

	public QuickTipAlgParams getAlgParams() {
		return algParams;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("QuickTipGeneratorParams [sheetCount=");
		builder.append(sheetCount);
		builder.append(", allowOverlappingSheets=");
		builder.append(allowOverlappingSheets);
		builder.append(", algParams=");
		builder.append(algParamsToString());
		builder.append("]");
		return builder.toString();
	}

	private String algParamsToString() {
		StringBuilder sb = new StringBuilder();
		if (algParams == null) {
			sb.append("null");
		} else {
			sb.append("[ numbersToDraw=");
			sb.append(algParams.getNumbersToDraw());
			sb.append(", panelCount=");
			sb.append(algParams.getPanelCount());
			sb.append(", range=");
			sb.append(algParams.getRange());
			sb.append("]");
		}
		return sb.toString();
	}
}
