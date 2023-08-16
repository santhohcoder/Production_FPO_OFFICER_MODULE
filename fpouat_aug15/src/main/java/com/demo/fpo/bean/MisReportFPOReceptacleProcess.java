package com.demo.fpo.bean;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MisReportFPOReceptacleProcess {

	
	@Id
	//private String id;
	private String BAG_NO;
	private String OOE;
	private String RECD_DT;
	private String cussite;
	
	
	
	public String getCussite() {
		return cussite;
	}
	public void setCussite(String cussite) {
		this.cussite = cussite;
	}

	/*
	 * public String getId() { return id; } public void setId(String id) { this.id =
	 * id; }
	 */
	public String getBAG_NO() {
		return BAG_NO;
	}
	public void setBAG_NO(String bAG_NO) {
		BAG_NO = bAG_NO;
	}
	public String getOOE() {
		return OOE;
	}
	public void setOOE(String oOE) {
		OOE = oOE;
	}
	public String getRECD_DT() {
		return RECD_DT;
	}
	public void setRECD_DT(String rECD_DT) {
		RECD_DT = rECD_DT;
	}
	
	
	

}
