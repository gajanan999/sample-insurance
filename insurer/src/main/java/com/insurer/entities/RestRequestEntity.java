package com.insurer.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="rest_requests")
public class RestRequestEntity {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String insurerName;
	private String url;
	private String username;
	private String password;
	private String methodType;

	@Override
	public String toString() {
		return "RestRequestEntity [id=" + id + ", insurerName=" + insurerName + ", url=" + url + ", username="
				+ username + ", password=" + password + ", methodType=" + methodType + "]";
	}


	public String getInsurerName() {
		return insurerName;
	}


	public void setInsurerName(String insurerName) {
		this.insurerName = insurerName;
	}



	public Long getId() {
		return id;
	}


	public void setId(Long id) {
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

	
}
