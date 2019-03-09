package com.insurer.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QuoteVo {

	
	private String premium;
	private Double quote;
	public String getPremium() {
		return premium;
	}
	public void setPremium(String premium) {
		this.premium = premium;
	}
	public Double getValue() {
		return quote;
	}
	public void setValue(Double quote) {
		this.quote = quote;
	}
	@Override
	public String toString() {
		return "QuoteVo [premium=" + premium + ", quote=" + quote + "]";
	}
	
	
	
}
