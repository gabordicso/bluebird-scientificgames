package gabordicso.quicktip.generator;

import gabordicso.quicktip.algparams.QuickTipAlgParams;

public class QuickTipGeneratorParams {

	private final int sheetCount;
	private final boolean allowOverlappingSheets;
	private final QuickTipAlgParams algParams;
	
	public int getSheetCount() {
		return sheetCount;
	}

	public boolean isAllowOverlappingSheets() {
		return allowOverlappingSheets;
	}

	public QuickTipAlgParams getAlgParams() {
		return algParams;
	}

	public QuickTipGeneratorParams(final int sheetCount, final boolean allowOverlappingSheets, final QuickTipAlgParams algParams) {
		this.sheetCount = sheetCount;
		this.allowOverlappingSheets = allowOverlappingSheets;
		this.algParams = algParams;
	}

}
