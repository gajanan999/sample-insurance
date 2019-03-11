package com.insurer.vo;

import java.util.List;

import com.insurer.entities.RestRequestEntity;

public class RestRequestsDetails {

	
	public RestRequestsDetails() {
		
	}
	
	public List<RestRequestEntity> requestList;
	
	

	@Override
	public String toString() {
		return "RestRequestsDetails [requestList=" + requestList + "]";
	}

	public List<RestRequestEntity> getRequestList() {
		return requestList;
	}

	public void setRequestList(List<RestRequestEntity> requestList) {
		this.requestList = requestList;
	}
	
}


