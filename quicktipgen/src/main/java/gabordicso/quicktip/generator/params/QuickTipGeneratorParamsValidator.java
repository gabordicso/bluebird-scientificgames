package gabordicso.quicktip.generator.params;

import java.security.InvalidParameterException;

import gabordicso.quicktip.generator.params.alg.QuickTipAlgParams;

public class QuickTipGeneratorParamsValidator {
	public void validate(final QuickTipGeneratorParams params) throws InvalidParamException {
		performBasicValidation(params);
		performSanityCheck(params);
	}

	private void performBasicValidation(QuickTipGeneratorParams params) throws InvalidParamException {
		if (params.getSheetCount() < 1) {
			throw new InvalidParamException("Sheet count must be greater than 0.");
		}
		QuickTipAlgParams algParams = params.getAlgParams();
		if (algParams.getNumbersToDraw() < 1) {
			throw new InvalidParamException("Numbers to draw must be greater than 0.");
		}
		if (algParams.getPanelCount() < 1) {
			throw new InvalidParamException("Panel count must be greater than 0.");
		}
		if (algParams.getRange() < 1) {
			throw new InvalidParamException("Range must be greater than 0.");
		}
	}

	private void performSanityCheck(final QuickTipGeneratorParams params) throws InvalidParamException {
		/* 
		 * Check that the params make sense:
		 * - range should be greater than the number count
		 * - it should be possible to generate the requested number of unique panels based on the range and number count
		 * - it should also be possible to generate the requested number of sheets in a way that there are no duplicate panels
		 *   (there is a console flag for this check, as this is not in the requirements)
		 */
		QuickTipAlgParams algParams = params.getAlgParams();
		performRangeCheck(algParams);
		performUniquePanelsCheck(algParams);
		boolean allowOverlappingSheets = params.getAllowOverlappingSheets();
		if (!allowOverlappingSheets) {
			performNoOverlappingSheetsCheck(algParams, params.getSheetCount());
		}
	}

	private void performRangeCheck(final QuickTipAlgParams params) {
		int range = params.getRange();
		int numbersToDraw = params.getNumbersToDraw();
		if (range <= numbersToDraw) {
			throw new InvalidParameterException(String.format("Can not generate random tips with these parameters: range = %d, numbers to draw = %d", range, numbersToDraw));
		}
	}

	private void performUniquePanelsCheck(final QuickTipAlgParams params) {
		int range = params.getRange();
		int numbersToDraw = params.getNumbersToDraw();
		int panelCount = params.getPanelCount();
		long possibleCombinations = getPossibleCombinations(range, numbersToDraw);
		if (panelCount > possibleCombinations) {
			throw new InvalidParameterException(String.format("Can not generate the requested number of unique panels on one sheet with these parameters: range = %d, numbers to draw = %d, panel count = %d, number of possible combinations = %d", range, numbersToDraw, panelCount, possibleCombinations));
		}
	}

	private void performNoOverlappingSheetsCheck(final QuickTipAlgParams params, final int sheetCount) {
		int range = params.getRange();
		int numbersToDraw = params.getNumbersToDraw();
		int panelCount = params.getPanelCount();
		long overallPanelCount = panelCount * sheetCount;
		long possibleCombinations = getPossibleCombinations(range, numbersToDraw);
		if (overallPanelCount > possibleCombinations) {
			throw new InvalidParameterException(String.format("Can not generate the requested number of sheets all containing unique panels with these parameters: range = %d, numbers to draw = %d, panel count = %d, sheet count = %d, number of possible combinations = %d", range, numbersToDraw, panelCount, sheetCount, possibleCombinations));
		}
	}

	private long getPossibleCombinations(final int range, final int numbersToDraw) {
		final int start = range;
		final int stop = range - numbersToDraw + 1;
		long combinations = 1;
		for (int i = start; i >= stop; i--) {
			combinations *= i;
		}
		for (int i = 1; i <= numbersToDraw; i++) {
			combinations /= i;
		}
		return combinations;
	}


}
