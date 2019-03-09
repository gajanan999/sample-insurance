package com.insurer.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Quote")
@XmlAccessorType(XmlAccessType.NONE)
public class QuoteVoXML {

	@XmlAttribute(name="premium")
	private Double premium;

	public Double getPremium() {
		return premium;
	}

	public void setPremium(Double premium) {
		this.premium = premium;
	}
	
	
	
}
