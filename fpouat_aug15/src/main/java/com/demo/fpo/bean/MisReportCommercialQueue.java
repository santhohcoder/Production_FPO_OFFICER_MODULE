package com.demo.fpo.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class MisReportCommercialQueue {
	@Id
	private String id;
	private String item_id;
	private String post_dt;
	private String coo;
	private String mail_class;
	private String item_category;
	private String noofitem;
	private String totdec;
	private String qrymodel;
	private String dcallno;
	private String dcalldt;
	@Transient
	private String currentque;
	private String cussite;
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
	public String getTotdec() {
		return totdec;
	}
	public void setTotdec(String totdec) {
		this.totdec = totdec;
	}
	public String getQrymodel() {
		return qrymodel;
	}
	public void setQrymodel(String qrymodel) {
		this.qrymodel = qrymodel;
	}
	public String getDcallno() {
		return dcallno;
	}
	public void setDcallno(String dcallno) {
		this.dcallno = dcallno;
	}
	public String getDcalldt() {
		return dcalldt;
	}
	public void setDcalldt(String dcalldt) {
		this.dcalldt = dcalldt;
	}
	
	public String getCurrentque() {
		return currentque;
	}
	public void setCurrentque(String currentque) {
		this.currentque = currentque;
	}
	public String getCussite() {
		return cussite;
	}
	public void setCussite(String cussite) {
		this.cussite = cussite;
	}
	
	
	
	
	
}
