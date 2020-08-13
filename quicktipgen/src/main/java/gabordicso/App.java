package gabordicso;

import org.apache.commons.cli.ParseException;

import gabordicso.quicktip.QuickTip;
import gabordicso.quicktip.generator.QuickTipGenerator;
import gabordicso.quicktip.generator.QuickTipGeneratorFactory;

public class App
{

	public static void main(final String[] args)
    {
		try {
			ConsoleArgParser parser = new ConsoleArgParser();
		    ConsoleParams consoleParams = parser.parseArgs(args);
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
