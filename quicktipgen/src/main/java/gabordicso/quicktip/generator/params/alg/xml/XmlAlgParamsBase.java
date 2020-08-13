package gabordicso.quicktip.generator.params.alg.xml;

import javax.xml.bind.annotation.XmlElement;

import gabordicso.quicktip.generator.params.alg.QuickTipAlgParams;

public abstract class XmlAlgParamsBase implements QuickTipAlgParams {
	private int numbersToDraw;
	
	@Override
	public int getNumbersToDraw() {
		return numbersToDraw;
	}

	@XmlElement
	public void setNumbersToDraw(int numbersToDraw) {
		this.numbersToDraw = numbersToDraw;
	}
}
