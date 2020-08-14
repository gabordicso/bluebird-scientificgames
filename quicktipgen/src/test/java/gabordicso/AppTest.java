package gabordicso;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.commons.cli.ParseException;
import org.junit.Assert;
import org.junit.Test;

import gabordicso.quicktip.console.ConsoleArgParser;
import gabordicso.quicktip.console.ConsoleParams;
import gabordicso.quicktip.generator.QuickTipGenerator;
import gabordicso.quicktip.generator.QuickTipGeneratorFactory;
import gabordicso.quicktip.generator.params.InvalidParamException;
import gabordicso.quicktip.generator.params.QuickTipGeneratorParams;
import gabordicso.quicktip.generator.params.alg.QuickTipAlgParams;
import gabordicso.quicktip.generator.params.alg.xml.XmlAlgParamsBase;
import gabordicso.quicktip.generator.params.alg.xml.XmlAlgParams_Alg1;
import gabordicso.quicktip.generator.params.alg.xml.XmlAlgParams_Alg2;
import gabordicso.quicktip.generator.params.alg.xml.XmlAlgParams_Alg3;

public class AppTest {
	/*
	 * TODO implement test cases
	 * 
	 * test cases for parsing command line arguments:
	 * - missing arg
	 * -- arg and value both missing
	 * -- arg specified with no value (e.g. "--inputFilePath= ")
	 * - short name used in argument list, parsing for long name
	 * - long name used in argument list, parsing for short name
	 * - complete argument list with short names
	 * - complete argument list with long names
	 * - complete argument list with short and long names mixed
	 * 
	 * test cases for validating command line arguments:
	 * - invalid arg type (e.g. String instead of int)
	 * - invalid arg value
	 * -- numbers: out of range (1-3 for argType, >0 for sheetCount)
	 * -- inputFile: nonexistent file, not an XML file, illegal XML file (structure not matching expected structure), invalid XML file (one or more values invalid), incomplete XML file (missing argument that is required for a particular algorithm type)
	 * - invalid alg parameter XML for the selected alg (e.g. XML for Alg2 passed as parameter XML for Alg1)
	 * - valid values
	 * 
	 * must also make a sanity check on the parameters in the input file and that needs to be tested too, but that test belongs to testing the logic
	 * */
	
	class TestQuickTipAlgParams implements QuickTipAlgParams {
		private final int numbersToDraw;
		private final int panelCount;
		private final int range;
		@Override
		public int getNumbersToDraw() {
			return numbersToDraw;
		}
		@Override
		public int getPanelCount() {
			return panelCount;
		}
		@Override
		public int getRange() {
			return range;
		}
		public TestQuickTipAlgParams(int numbersToDraw, int panelCount, int range) {
			this.numbersToDraw = numbersToDraw;
			this.panelCount = panelCount;
			this.range = range;
		}
	}
	
//    @Test
    public void test() {
    	ConsoleArgParser parser = new ConsoleArgParser();
		String[] args = new String[]{ "--algType=3", "-i=src\\test\\resources\\test_alg3_invalid3.xml", "-s=100" };
    	try {
    		ConsoleParams params = parser.parseArgs(args);
    		QuickTipGenerator gen = QuickTipGeneratorFactory.create(params);
			System.out.println(gen.generate());
		} catch (ParseException e) {
			e.printStackTrace();
			Assert.fail();
		} catch (InvalidParamException e) {
			e.printStackTrace();
			Assert.fail();
		}
    }
    
    @Test
    public void test2() {
    	QuickTipAlgParams algParams = new TestQuickTipAlgParams(5, 2, 90);
		QuickTipGeneratorParams params = new QuickTipGeneratorParams(10, false, algParams);
		QuickTipGenerator gen = QuickTipGeneratorFactory.create(params);
		try {
			System.out.println(gen.generate());
		} catch (InvalidParamException e) {
			e.printStackTrace();
		}
    }
    
//    @Test
    public void test3() {
    	QuickTipAlgParams algParams = new TestQuickTipAlgParams(5, 2, 6);
		QuickTipGeneratorParams params = new QuickTipGeneratorParams(10, true, algParams);
		QuickTipGenerator gen = QuickTipGeneratorFactory.create(params);
		try {
			System.out.println(gen.generate());
		} catch (InvalidParamException e) {
			e.printStackTrace();
		}
    }
    
    private <T extends XmlAlgParamsBase> void createXml(final T xmlParams, final String fileName, final Class<T> paramsClass) {
		try {
			File file = new File(fileName);
			JAXBContext jaxbContext = JAXBContext.newInstance(paramsClass);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(xmlParams, file);
			jaxbMarshaller.marshal(xmlParams, System.out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
    }
    
//    @Test
	public void createAlg1Xml() {
		XmlAlgParams_Alg1 xmlParams = new XmlAlgParams_Alg1();
		xmlParams.setNumbersToDraw(5);
		xmlParams.setRange(90);
		createXml(xmlParams, "test_alg1.xml", XmlAlgParams_Alg1.class);
    }
    
//    @Test
	public void createAlg2Xml() {
		XmlAlgParams_Alg2 xmlParams = new XmlAlgParams_Alg2();
		xmlParams.setNumbersToDraw(5);
		xmlParams.setPanelCount(6);
		createXml(xmlParams, "test_alg2.xml", XmlAlgParams_Alg2.class);
    }
    
//    @Test
	public void createAlg3Xml() {
		XmlAlgParams_Alg3 xmlParams = new XmlAlgParams_Alg3();
		xmlParams.setNumbersToDraw(5);
		xmlParams.setPanelCount(6);
		xmlParams.setRange(90);
		createXml(xmlParams, "test_alg3.xml", XmlAlgParams_Alg3.class);
    }
}
