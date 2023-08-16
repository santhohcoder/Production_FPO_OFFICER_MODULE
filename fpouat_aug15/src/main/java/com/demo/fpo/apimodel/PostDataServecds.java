package com.demo.fpo.apimodel;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PostDataServecds {
	@Id
	private String CusitmToken;
	private String Cusitmurl1;
	private String Cusitmurl2;
	private String CusrspToken;
	private String Cusrspurl1;
	private String Cusrspurl2;
	private String Cusrspusercd;
	public String getCusrspusercd() {
		return Cusrspusercd;
	}
	public void setCusrspusercd(String cusrspusercd) {
		Cusrspusercd = cusrspusercd;
	}
	public String getCusitmToken() {
		return CusitmToken;
	}
	public void setCusitmToken(String cusitmToken) {
		CusitmToken = cusitmToken;
	}
	public String getCusitmurl1() {
		return Cusitmurl1;
	}
	public void setCusitmurl1(String cusitmurl1) {
		Cusitmurl1 = cusitmurl1;
	}
	public String getCusitmurl2() {
		return Cusitmurl2;
	}
	public void setCusitmurl2(String cusitmurl2) {
		Cusitmurl2 = cusitmurl2;
	}
	public String getCusrspToken() {
		return CusrspToken;
	}
	public void setCusrspToken(String cusrspToken) {
		CusrspToken = cusrspToken;
	}
	public String getCusrspurl1() {
		return Cusrspurl1;
	}
	public void setCusrspurl1(String cusrspurl1) {
		Cusrspurl1 = cusrspurl1;
	}
	public String getCusrspurl2() {
		return Cusrspurl2;
	}
	public void setCusrspurl2(String cusrspurl2) {
		Cusrspurl2 = cusrspurl2;
	}
	

	
}
