package gabordicso.quicktip.algparams.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import gabordicso.quicktip.algparams.QuickTipAlgParams;

@XmlRootElement
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
