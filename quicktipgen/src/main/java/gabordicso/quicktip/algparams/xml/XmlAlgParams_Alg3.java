package gabordicso.quicktip.algparams.xml;

import javax.xml.bind.annotation.XmlElement;

public class XmlAlgParams_Alg3 extends XmlAlgParamsBase {
	private int panelCount;
	private int range;

	@Override
	public int getPanelCount() {
		return panelCount;
	}

	@XmlElement
	public void setPanelCount(int panelCount) {
		this.panelCount = panelCount;
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
