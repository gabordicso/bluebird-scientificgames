package gabordicso.quicktip.generator;

import gabordicso.quicktip.console.ConsoleParams;
import gabordicso.quicktip.generator.params.InvalidParamException;
import gabordicso.quicktip.generator.params.QuickTipGeneratorParams;
import gabordicso.quicktip.generator.params.alg.QuickTipAlgParams;
import gabordicso.quicktip.generator.params.alg.QuickTipAlgParamsFactory;

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
