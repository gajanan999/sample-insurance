package com.insurer.entities;

public class RestRequestEntity {

	private Integer id;
	private String url;
	private String username;
	private String password;
	private String methodType;
	private String consumeType;
	private String produceType;
	private String requestBody;
	
	
	@Override
	public String toString() {
		return "RestRequestEntity [id=" + id + ", url=" + url + ", username=" + username + ", password=" + password
				+ ", methodType=" + methodType + ", consumeType=" + consumeType + ", produceType=" + produceType
				+ ", requestBody=" + requestBody + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMethodType() {
		return methodType;
	}
	public void setMethodType(String methodType) {
		this.methodType = methodType;
	}
	public String getConsumeType() {
		return consumeType;
	}
	public void setConsumeType(String consumeType) {
		this.consumeType = consumeType;
	}
	public String getProduceType() {
		return produceType;
	}
	public void setProduceType(String produceType) {
		this.produceType = produceType;
	}
	public String getRequestBody() {
		return requestBody;
	}
	public void setRequestBody(String requestBody) {
		this.requestBody = requestBody;
	}
	
	
	
}
