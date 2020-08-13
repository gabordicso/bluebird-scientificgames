package gabordicso.quicktip.generator;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import gabordicso.quicktip.generator.output.Panel;
import gabordicso.quicktip.generator.output.QuickTip;
import gabordicso.quicktip.generator.output.Sheet;
import gabordicso.quicktip.generator.params.InvalidParamException;
import gabordicso.quicktip.generator.params.QuickTipGeneratorParams;
import gabordicso.quicktip.generator.params.QuickTipGeneratorParamsValidator;

public class QuickTipGenerator {

	private final QuickTipGeneratorParams params;
	private final String prngAlgorithmName;

	QuickTipGenerator(QuickTipGeneratorParams params) {
		this.params = params;
		if (this.isWin()) {
			prngAlgorithmName = "Windows-PRNG";
		} else {
			prngAlgorithmName = "NativePRNG";
		}
	}
	
	private boolean isWin() {
		String osNameLower = System.getProperty("os.name").toLowerCase();
		return osNameLower.contains("windows");
	}

	public QuickTip generate() throws InvalidParamException {
		QuickTipGeneratorParamsValidator validator = new QuickTipGeneratorParamsValidator();
		validator.validate(params);

		QuickTip tip = new QuickTip(params);
		
		final int sheetCount = params.getSheetCount();
		for (int currentSheet = 0; currentSheet < sheetCount; currentSheet++) {
			Sheet sheet = generateSheet(tip);
			tip.getSheets().add(sheet);
		}
		
		return tip;
	}

	private Sheet generateSheet(QuickTip tip) {
		Sheet sheet = new Sheet();
		final int panelCount = params.getAlgParams().getPanelCount();
		for (int currentPanel = 0; currentPanel < panelCount; currentPanel++) {
			Panel panel = null;
			do {
				panel = generatePanel(tip);
				// check that the panel is not already on the sheet (and neither on any other sheets, if no overlapping sheets are allowed), generate new panel if necessary
			} while (sheet.containsPanel(panel) || (!params.getAllowOverlappingSheets() && tip.containsPanel(panel)));
			sheet.getPanels().add(panel);
		}
		return sheet;
	}

	private Panel generatePanel(QuickTip tip) {
		Panel panel = new Panel();
		
		final int numbersToDraw = params.getAlgParams().getNumbersToDraw();
		final int range = params.getAlgParams().getRange();
		final List<Integer> numberRepo = getNumberRepo(range);
		for (int currentNumberIndex = 0; currentNumberIndex < numbersToDraw; currentNumberIndex++) {
			int randomNumber = generateSecureRandom(range - currentNumberIndex); // between 0 inclusive and range exclusive initially
			int numberDrawn = drawFromRepo(numberRepo, randomNumber);
			if (numberDrawn == -1) {
				throw new RuntimeException(String.format("Error: numberDrawn == -1, this should not happen, please contact the system administrator with the following info: tip: %s, randomNumber: %s, panel: %s", tip, randomNumber, panel));
			}
			panel.getDrawnNumbers().add(numberDrawn);
		}
		
		return panel;
	}

	private int generateSecureRandom(int bound) {
		// generate a secure random integer between 0 inclusive and bound exclusive
		SecureRandom r;
		try {
			r = SecureRandom.getInstance(prngAlgorithmName); // Default constructor would have returned insecure SHA1PRNG algorithm, so make an explicit call.
			return r.nextInt(bound);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to initialize secure random number generator, please contact the system administrator");
		}
	}

	private int drawFromRepo(List<Integer> numberRepo, int indexToDraw) {
		// return the number with the given index and remove that number from the repo
		Iterator<Integer> iter = numberRepo.iterator();

		int number = -1;
		int currentNumberIndex = 0;
		while (iter.hasNext()) {
			int currentNumberInRepo = iter.next();
			if (currentNumberIndex == indexToDraw) {
				number = currentNumberInRepo;
				iter.remove();
				break;
			}
			currentNumberIndex++;
		}
		return number;

	}

	private List<Integer> getNumberRepo(int range) {
		List<Integer> repo = new ArrayList<>();
		for (int i = 1; i <= range; i++) {
			repo.add(i);
		}
		return repo;
	}

}
