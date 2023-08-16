package com.demo.fpo.bean;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MisReportByMailClass {
	
	@Id
	private String id;
	private String coo;
	private String SEND_CNTRY_CD;
	private String tot_article;
	private String mail_class;
	private String TOT_ASS_VAL;
	private String cussite;
	
	public String getCussite() {
		return cussite;
	}
	public void setCussite(String cussite) {
		this.cussite = cussite;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCoo() {
		return coo;
	}
	public void setCoo(String coo) {
		this.coo = coo;
	}
	public String getSEND_CNTRY_CD() {
		return SEND_CNTRY_CD;
	}
	public void setSEND_CNTRY_CD(String sEND_CNTRY_CD) {
		SEND_CNTRY_CD = sEND_CNTRY_CD;
	}
	public String getTot_article() {
		return tot_article;
	}
	public void setTot_article(String tot_article) {
		this.tot_article = tot_article;
	}
	public String getMail_class() {
		return mail_class;
	}
	public void setMail_class(String mail_class) {
		this.mail_class = mail_class;
	}
	public String getTOT_ASS_VAL() {
		return TOT_ASS_VAL;
	}
	public void setTOT_ASS_VAL(String tOT_ASS_VAL) {
		TOT_ASS_VAL = tOT_ASS_VAL;
	}
	
	
	

}
