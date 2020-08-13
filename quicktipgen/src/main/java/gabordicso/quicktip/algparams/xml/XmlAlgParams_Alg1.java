package gabordicso.quicktip.algparams.xml;

import javax.xml.bind.annotation.XmlElement;

public class XmlAlgParams_Alg1 extends XmlAlgParamsBase {
	private int range;

	@Override
	public int getPanelCount() {
		return 1;
	}

	@Override
	public int getRange() {
		return range;
	}

	@XmlElement
	public void setRange(int range) {
		this.range = range;
	}

}
