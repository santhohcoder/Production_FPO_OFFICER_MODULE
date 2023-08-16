package com.demo.fpo.bean;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MisReportOOEReceptacleProcess {
	
	@Id
	//private String id;
	private String RECP_ID;
	private String coo;
	private String RECD_EVENT_DT;
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
	public String getRECP_ID() {
		return RECP_ID;
	}
	public void setRECP_ID(String rECP_ID) {
		RECP_ID = rECP_ID;
	}
	public String getCoo() {
		return coo;
	}
	public void setCoo(String coo) {
		this.coo = coo;
	}
	public String getRECD_EVENT_DT() {
		return RECD_EVENT_DT;
	}
	public void setRECD_EVENT_DT(String rECD_EVENT_DT) {
		RECD_EVENT_DT = rECD_EVENT_DT;
	}
	
	
	
	
	
	

}
