package com.demo.fpo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class PostalediColumns {
	@Id

	private String item_id;
	private String cin_no;
	private String ead_date;
	private String mail_category;
	private String org_cd;
	

	public String getCin_no() {
		return cin_no;
	}
	public void setCin_no(String cin_no) {
		this.cin_no = cin_no;
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
	
	
	

}
