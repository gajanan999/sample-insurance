package com.insurer.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Quote")
@XmlAccessorType(XmlAccessType.NONE)
public class Quote {

	/*@XmlAttribute(name="premium")*/
	private Double premium;

	public @XmlElement Double getPremium() {
		return premium;
	}

	public void setPremium(Double premium) {
		this.premium = premium;
	}

	@Override
	public String toString() {
		return "QuoteVoXML [premium=" + premium + "]";
	}
	
	
	
}
