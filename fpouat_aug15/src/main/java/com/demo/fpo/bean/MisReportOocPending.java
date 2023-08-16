package com.demo.fpo.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class MisReportOocPending {
	
	@Id
	private String id;
	private String item_id;
	private String cussite;
	private String post_dt;
	private String coo;
	private String mail_class;
	private String item_category;
	private String noofitem;
	private String pendingqueue;
	private String pendingrole;
	private String pendingofficer;
	private String officername;
	
	
	
	
	public String getId() {
		return id;
	}
	public String getCussite() {
		return cussite;
	}
	public void setCussite(String cussite) {
		this.cussite = cussite;
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
	public String getPost_dt() {
		return post_dt;
	}
	public void setPost_dt(String post_dt) {
		this.post_dt = post_dt;
	}
	public String getCoo() {
		return coo;
	}
	public void setCoo(String coo) {
		this.coo = coo;
	}
	public String getMail_class() {
		return mail_class;
	}
	public void setMail_class(String mail_class) {
		this.mail_class = mail_class;
	}
	public String getItem_category() {
		return item_category;
	}
	public void setItem_category(String item_category) {
		this.item_category = item_category;
	}
	public String getNoofitem() {
		return noofitem;
	}
	public void setNoofitem(String noofitem) {
		this.noofitem = noofitem;
	}
	public String getPendingqueue() {
		return pendingqueue;
	}
	public void setPendingqueue(String pendingqueue) {
		this.pendingqueue = pendingqueue;
	}
	public String getPendingrole() {
		return pendingrole;
	}
	public void setPendingrole(String pendingrole) {
		this.pendingrole = pendingrole;
	}
	public String getPendingofficer() {
		return pendingofficer;
	}
	public void setPendingofficer(String pendingofficer) {
		this.pendingofficer = pendingofficer;
	}
	public String getOfficername() {
		return officername;
	}
	public void setOfficername(String officername) {
		this.officername = officername;
	}
	
	
	

}
