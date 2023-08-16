package com.demo.fpo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class DetainedColumns {
	@Id
	private String id;
	private String item_id;
	private String ead_date;
	private String mail_category;
	private String org_cd;
	private String item_no;
	private String nou;
	private String cth;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getItem_id() {
		return item_id;
	}
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	public String getEad_date() {
		return ead_date;
	}
	public void setEad_date(String ead_date) {
		this.ead_date = ead_date;
	}
	public String getMail_category() {
		return mail_category;
	}
	public void setMail_category(String mail_category) {
		this.mail_category = mail_category;
	}
	public String getOrg_cd() {
		return org_cd;
	}
	public void setOrg_cd(String org_cd) {
		this.org_cd = org_cd;
	}
	public String getItem_no() {
		return item_no;
	}
	public void setItem_no(String item_no) {
		this.item_no = item_no;
	}
	public String getNou() {
		return nou;
	}
	public void setNou(String nou) {
		this.nou = nou;
	}
	public String getCth() {
		return cth;
	}
	public void setCth(String cth) {
		this.cth = cth;
	}
	
	
	
	

}
