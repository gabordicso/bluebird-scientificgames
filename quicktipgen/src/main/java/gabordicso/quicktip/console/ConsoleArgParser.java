package gabordicso.quicktip.console;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import gabordicso.quicktip.generator.params.InvalidParamException;
import gabordicso.quicktip.generator.params.alg.AlgType;

public class ConsoleArgParser {
	enum ParamNames {
		ALG_TYPE("a", "algType"),
		INPUT_FILE_PATH("i", "inputFilePath"),
		SHEET_COUNT("s", "sheetCount"),
		ALLOW_OVERLAPPING_SHEETS("o", "overlappingSheetsAllowed");

		private final String shortName;
		private final String longName;

		public String getShortName() {
			return shortName;
		}
		public String getLongName() {
			return longName;
		}

		private ParamNames(String shortName, String longName) {
			this.shortName = shortName;
			this.longName = longName;
		}
	}

	public ConsoleParams parseArgs(final String[] args) throws ParseException, InvalidParamException {

		Options options = new Options()
			.addRequiredOption(ParamNames.ALG_TYPE.getShortName(), ParamNames.ALG_TYPE.getLongName(), true, "The type of algorithm to use (must be an integer number between 1 and 3)")
			.addRequiredOption(ParamNames.INPUT_FILE_PATH.getShortName(), ParamNames.INPUT_FILE_PATH.getLongName(), true, "Path to the XML file containing the input parameters (must be an existing file)")
			.addRequiredOption(ParamNames.SHEET_COUNT.getShortName(), ParamNames.SHEET_COUNT.getLongName(), true, "The number of sheets to generate (must be an integer number greater than 0)")
			.addOption(ParamNames.ALLOW_OVERLAPPING_SHEETS.getShortName(), ParamNames.ALLOW_OVERLAPPING_SHEETS.getLongName(), true, "Allow overlapping sheets to be generated; if set to false, each panel in any of the returned sheets is guaranteed to be unique overall (optional, default true; set to \"0\", \"no\" or \"false\" for false)");

		CommandLineParser parser = new DefaultParser();
		CommandLine commandLine = parser.parse(options, args);
		
		AlgType algType = parseAlgTypeOrThrow(commandLine);
		int sheetCount = parseSheetCountOrThrow(commandLine);
		String inputFilePath = parseInputFilePath(commandLine);
		boolean allowOverlappingSheets = parseAllowOverlappingSheets(commandLine);

		return new ConsoleParams(algType, sheetCount, inputFilePath, allowOverlappingSheets);
	}

	private AlgType parseAlgTypeOrThrow(CommandLine commandLine) throws InvalidParamException {
		try {
			String algTypeStr = commandLine.getOptionValue(ParamNames.ALG_TYPE.getShortName());
			int algTypeInt = Integer.parseInt(algTypeStr);
			return AlgType.fromValue(algTypeInt);
		} catch (NumberFormatException nfe) {
			throw new InvalidParamException(String.format("Illegal value provided for parameter %s", ParamNames.ALG_TYPE.getLongName()));
		}
	}

	private int parseSheetCountOrThrow(CommandLine commandLine) throws InvalidParamException {
		try {
			String sheetCountStr = commandLine.getOptionValue(ParamNames.SHEET_COUNT.getShortName());
			return Integer.parseInt(sheetCountStr);
		} catch (NumberFormatException nfe) {
			throw new InvalidParamException(String.format("Illegal value provided for parameter %s", ParamNames.SHEET_COUNT.getLongName()));
		}
	}
	
	private String parseInputFilePath(CommandLine commandLine) {
		return commandLine.getOptionValue(ParamNames.INPUT_FILE_PATH.getShortName());
	}

	private boolean parseAllowOverlappingSheets(CommandLine commandLine) {
		if (commandLine.hasOption(ParamNames.ALLOW_OVERLAPPING_SHEETS.getShortName())) {
			String allowOverlappingSheetsStr = commandLine.getOptionValue(ParamNames.ALLOW_OVERLAPPING_SHEETS.getShortName());
			if (allowOverlappingSheetsStr == null) {
				allowOverlappingSheetsStr = "";
			}
			allowOverlappingSheetsStr = allowOverlappingSheetsStr.trim().toLowerCase();
			if (allowOverlappingSheetsStr.equals("0")
					|| allowOverlappingSheetsStr.equals("no")
					|| allowOverlappingSheetsStr.equals("false")) {
				return false;
			}
		}
		return true;
	}

}
