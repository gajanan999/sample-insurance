package com.insurer.vo;

import java.util.Date;

public class CustomerDetails {

	private String tpnr;
	private String custName;
	private String dob;
	private String drivingExp;
	public String getTpnr() {
		return tpnr;
	}
	public void setTpnr(String tpnr) {
		this.tpnr = tpnr;
	}

	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getDrivingExp() {
		return drivingExp;
	}
	public void setDrivingExp(String drivingExp) {
		this.drivingExp = drivingExp;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	@Override
	public String toString() {
		return "CustomerDetails [tpnr=" + tpnr + ", custName=" + custName + ", dob=" + dob + ", drivingExp="
				+ drivingExp + "]";
	}
	
	
	
}
