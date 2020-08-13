package gabordicso.quicktip.console;

import gabordicso.quicktip.generator.params.InvalidParamException;
import gabordicso.quicktip.generator.params.alg.AlgType;

public class ConsoleParams {
	
	private final AlgType algType;
	private final int sheetCount;
	private final String inputFilePath;
	private final boolean allowOverlappingSheets;
	
	public ConsoleParams(AlgType algType, int sheetCount, String inputFilePath, boolean allowOverlappingSheets) throws InvalidParamException {
		if (algType == null) {
			throw new InvalidParamException("Parameter algType is invalid");
		}
		if (sheetCount <= 0) {
			throw new InvalidParamException("Parameter sheetCount is invalid");
		}
		if (inputFilePath == null || inputFilePath.trim().equals("")) {
			throw new InvalidParamException("Parameter inputFilePath is empty");
		}
		this.algType = algType;
		this.sheetCount = sheetCount;
		this.inputFilePath = inputFilePath;
		this.allowOverlappingSheets = allowOverlappingSheets;
	}

	public AlgType getAlgType() {
		return algType;
	}

	public int getSheetCount() {
		return sheetCount;
	}

	public String getInputFilePath() {
		return inputFilePath;
	}

	public boolean getAllowOverlappingSheets() {
		return allowOverlappingSheets;
	}
}
