package com.demo.fpo.apibean;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PostDataServe {
	@Id
	private String arrToken;
	private String arrEndpoint;
	public String getArrToken() {
		return arrToken;
	}
	public void setArrToken(String arrToken) {
		this.arrToken = arrToken;
	}
	public String getArrEndpoint() {
		return arrEndpoint;
	}
	public void setArrEndpoint(String arrEndpoint) {
		this.arrEndpoint = arrEndpoint;
	}
	public String getArrDataEndpoint() {
		return arrDataEndpoint;
	}
	public void setArrDataEndpoint(String arrDataEndpoint) {
		this.arrDataEndpoint = arrDataEndpoint;
	}
	private String arrDataEndpoint;
}
