package gabordicso.quicktip.algparams.xml;

import javax.xml.bind.annotation.XmlElement;

public class XmlAlgParams_Alg2 extends XmlAlgParamsBase {
	private int panelCount;

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
		return 90;
	}

}
