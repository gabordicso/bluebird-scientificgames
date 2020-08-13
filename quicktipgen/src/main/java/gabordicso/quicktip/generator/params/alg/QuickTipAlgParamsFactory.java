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

	private QuickTipAlgParamsFactory() { }
}
