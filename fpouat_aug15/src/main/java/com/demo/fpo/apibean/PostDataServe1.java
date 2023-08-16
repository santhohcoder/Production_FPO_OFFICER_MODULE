package com.demo.fpo.apibean;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PostDataServe1 {
	@Id
	private String deliToken;
	private String deliEndpoint;
	private String deliDataEndpoint;

	public String getDeliToken() {
		return deliToken;
	}

	public void setDeliToken(String deliToken) {
		this.deliToken = deliToken;
	}

	public String getDeliEndpoint() {
		return deliEndpoint;
	}

	public void setDeliEndpoint(String deliEndpoint) {
		this.deliEndpoint = deliEndpoint;
	}

	public String getDeliDataEndpoint() {
		return deliDataEndpoint;
	}

	public void setDeliDataEndpoint(String deliDataEndpoint) {
		this.deliDataEndpoint = deliDataEndpoint;
	}

}
