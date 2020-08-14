package gabordicso.quicktip.console;

import org.apache.commons.cli.ParseException;

import gabordicso.quicktip.generator.QuickTipGenerator;
import gabordicso.quicktip.generator.QuickTipGeneratorFactory;
import gabordicso.quicktip.generator.output.QuickTip;
import gabordicso.quicktip.generator.params.InvalidParamException;

public class App {
	/* TODO
	 * perform manual tests from console
	 * implement JUnit tests
	 * update README.md with install and usage instructions, including sample uses for valid and invalid cases, warn about extremely long running time when providing unusually big values for sheet and panel count
	 */

	public static void main(final String[] args)
    {
		try {
			ConsoleArgParser parser = new ConsoleArgParser();
		    ConsoleParams consoleParams = parser.parseArgs(args);
		    System.out.println(String.format("Parsed console parameters: ", consoleParams));
		    QuickTipGenerator generator = QuickTipGeneratorFactory.create(consoleParams);
		    QuickTip tip = generator.generate();
		    System.out.println(String.format("Generated Quick Tip:\n%s", tip.toString()));
		}
		catch (ParseException exp) {
		    System.out.println("Error parsing arguments:\n" + exp.getMessage());
		    System.exit(-1);
		} catch (InvalidParamException e) {
		    System.out.println("Invalid arguments:\n" + e.getMessage());
		    System.exit(-2);
		}
    }
}
