package gabordicso.quicktip.generator.params.alg;

import java.io.File;
import java.security.InvalidParameterException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import gabordicso.quicktip.console.ConsoleParams;
import gabordicso.quicktip.generator.params.InvalidParamException;
import gabordicso.quicktip.generator.params.alg.xml.XmlAlgParamsBase;
import gabordicso.quicktip.generator.params.alg.xml.XmlAlgParams_Alg1;
import gabordicso.quicktip.generator.params.alg.xml.XmlAlgParams_Alg2;
import gabordicso.quicktip.generator.params.alg.xml.XmlAlgParams_Alg3;

public class QuickTipAlgParamsFactory {
	
	public static QuickTipAlgParams create(ConsoleParams consoleParams) throws InvalidParamException {
		final QuickTipAlgParams params = parseAlgParams(consoleParams);
		performSanityCheck(params, consoleParams);
		return params;
	}

	private static QuickTipAlgParams parseAlgParams(ConsoleParams consoleParams) {
		final String inputFilePath = consoleParams.getInputFilePath();
		final AlgType algType = consoleParams.getAlgType();
		try {
			final QuickTipAlgParams params;
			switch(algType) {
			case ALG1:
				params = parseAlgParams(inputFilePath, XmlAlgParams_Alg1.class);
				break;
			case ALG2:
				params = parseAlgParams(inputFilePath, XmlAlgParams_Alg2.class);
				break;
			case ALG3:
				params = parseAlgParams(inputFilePath, XmlAlgParams_Alg3.class);
				break;
			default:
				params = null;
				break;
			}
			return params;
		} catch (JAXBException e) {
			throw new InvalidParameterException(String.format("Could not parse algorithm param XML file for the selected algorithm, input file path: %s, algorithm type: %s", inputFilePath, algType));
		}
	}
	
	@SuppressWarnings("unchecked")
	private static <T extends XmlAlgParamsBase> QuickTipAlgParams parseAlgParams(final String inputFilePath, final Class<T> algParamsClass) throws JAXBException {
		File file = new File(inputFilePath);
		JAXBContext jaxbContext = JAXBContext.newInstance(algParamsClass);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		return (T) jaxbUnmarshaller.unmarshal(file);
	}

	private static void performSanityCheck(final QuickTipAlgParams params, final ConsoleParams consoleParams) throws InvalidParamException {
		/* 
		 * Check that the params make sense:
		 * - range should be greater than the number count
		 * - it should be possible to generate the requested number of unique panels based on the range and number count
		 * - it should also be possible to generate the requested number of sheets in a way that there are no duplicate panels
		 *   (there is a console flag for this check, as this is not in the requirements)
		 */
		performRangeCheck(params);
		performUniquePanelsCheck(params);
		boolean allowOverlappingSheets = consoleParams.getAllowOverlappingSheets();
		if (!allowOverlappingSheets) {
			performNoOverlappingSheetsCheck(params, consoleParams.getSheetCount());
		}
	}

	private static void performRangeCheck(final QuickTipAlgParams params) {
		int range = params.getRange();
		int numbersToDraw = params.getNumbersToDraw();
		if (range <= numbersToDraw) {
			throw new InvalidParameterException(String.format("Can not generate random tips with these parameters: range = %d, numbers to draw = %d", range, numbersToDraw));
		}
	}

	private static void performUniquePanelsCheck(final QuickTipAlgParams params) {
		int range = params.getRange();
		int numbersToDraw = params.getNumbersToDraw();
		int panelCount = params.getPanelCount();
		int possibleCombinations = getPossibleCombinations(range, numbersToDraw);
		if (panelCount > possibleCombinations) {
			throw new InvalidParameterException(String.format("Can not generate the requested number of unique panels with these parameters: range = %d, numbers to draw = %d, panel count = %d, number of possible combinations = %d", range, numbersToDraw, panelCount, possibleCombinations));
		}
	}

	private static void performNoOverlappingSheetsCheck(final QuickTipAlgParams params, final int sheetCount) {
		int range = params.getRange();
		int numbersToDraw = params.getNumbersToDraw();
		int panelCount = params.getPanelCount();
		int possibleCombinations = getPossibleCombinations(range, numbersToDraw);
		if (panelCount * sheetCount > possibleCombinations) {
			throw new InvalidParameterException(String.format("Can not generate the requested number of sheets all containing unique panels with these parameters: range = %d, numbers to draw = %d, panel count = %d, sheet count = %d, number of possible combinations = %d", range, numbersToDraw, panelCount, sheetCount, possibleCombinations));
		}
	}

	private static int getPossibleCombinations(final int range, final int numbersToDraw) {
		final int start = range;
		final int stop = range - numbersToDraw + 1;
		int combinations = 1;
		for (int i = start; i >= stop; i--) {
			combinations *= i;
		}
		for (int i = 1; i <= numbersToDraw; i++) {
			combinations /= i;
		}
		return combinations;
	}

	private QuickTipAlgParamsFactory() { }
}
