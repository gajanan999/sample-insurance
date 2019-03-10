package com.insurer.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QuoteVo {

	private String insurer;
	private String cashless;
	private Double idv;
	private Double zeroDepricationAddon;
	private Double premium;
	
	
	@Override
	public String toString() {
		return "QuoteVo [insurer=" + insurer + ", cashless=" + cashless + ", idv=" + idv + ", zeroDepricationAddon="
				+ zeroDepricationAddon + ", premium=" + premium + "]";
	}
	public String getInsurer() {
		return insurer;
	}
	public void setInsurer(String insurer) {
		this.insurer = insurer;
	}
	public String getCashless() {
		return cashless;
	}
	public void setCashless(String cashless) {
		this.cashless = cashless;
	}
	public Double getIdv() {
		return idv;
	}
	public void setIdv(Double idv) {
		this.idv = idv;
	}
	public Double getZeroDepricationAddon() {
		return zeroDepricationAddon;
	}
	public void setZeroDepricationAddon(Double zeroDepricationAddon) {
		this.zeroDepricationAddon = zeroDepricationAddon;
	}
	public Double getPremium() {
		return premium;
	}
	public void setPremium(Double premium) {
		this.premium = premium;
	}
	
	
	
	
	
}
