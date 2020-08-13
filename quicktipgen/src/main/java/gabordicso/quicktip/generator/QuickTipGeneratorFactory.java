package gabordicso.quicktip.generator;

import gabordicso.ConsoleParams;
import gabordicso.InvalidParamException;
import gabordicso.quicktip.algparams.QuickTipAlgParams;
import gabordicso.quicktip.algparams.QuickTipAlgParamsFactory;

public class QuickTipGeneratorFactory {
	public static QuickTipGenerator create(ConsoleParams consoleParams) throws InvalidParamException {
		final int sheetCount = consoleParams.getSheetCount();
		final boolean allowOverlappingSheets = consoleParams.getAllowOverlappingSheets();
		final QuickTipAlgParams algParams = QuickTipAlgParamsFactory.create(consoleParams);
	    QuickTipGeneratorParams params = new QuickTipGeneratorParams(sheetCount, allowOverlappingSheets, algParams);
		return new QuickTipGenerator(params);
	}

	private QuickTipGeneratorFactory() { }
}
