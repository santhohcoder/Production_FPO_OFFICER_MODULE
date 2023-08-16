package com.demo.fpo.bean;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MisReportPBSOfficersArticles {
	
	@Id
	private String bagno;
	private String scandt;
	private String arrdt;
	private String cussite;
	public String getBagno() {
		return bagno;
	}
	public void setBagno(String bagno) {
		this.bagno = bagno;
	}
	public String getScandt() {
		return scandt;
	}
	public void setScandt(String scandt) {
		this.scandt = scandt;
	}
	public String getArrdt() {
		return arrdt;
	}
	public void setArrdt(String arrdt) {
		this.arrdt = arrdt;
	}
	public String getCussite() {
		return cussite;
	}
	public void setCussite(String cussite) {
		this.cussite = cussite;
	}

	
	
	
	
	
	

}
