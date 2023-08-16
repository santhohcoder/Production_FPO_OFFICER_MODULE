package com.demo.fpo.bean;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MisReportColumns {
	@Id
	private String id;
	private String item_id;
	private String cussite;
	private String post_dt;
	private String coo;
	private String mail_class;
	private String item_category;
	private String noofitem;
	private String totassval;
	private String totduty;
	private String dutyfg;
	private String dutycharged;
	private String oocdt;
	private String deldt;
	private String bcd_amt;
	private String igst_amt;
	private String sw_amt;
	private String other_duty;
	private String commercial;
	private String be_no;
	
	
	
	
	public String getCommercial() {
		return commercial;
	}
	public void setCommercial(String commercial) {
		this.commercial = commercial;
	}
	public String getBe_no() {
		return be_no;
	}
	public void setBe_no(String be_no) {
		this.be_no = be_no;
	}
	public String getCussite() {
		return cussite;
	}
	public void setCussite(String cussite) {
		this.cussite = cussite;
	}
	public String getBcd_amt() {
		return bcd_amt;
	}
	public void setBcd_amt(String bcd_amt) {
		this.bcd_amt = bcd_amt;
	}
	public String getIgst_amt() {
		return igst_amt;
	}
	public void setIgst_amt(String igst_amt) {
		this.igst_amt = igst_amt;
	}
	public String getSw_amt() {
		return sw_amt;
	}
	public void setSw_amt(String sw_amt) {
		this.sw_amt = sw_amt;
	}
	public String getOther_duty() {
		return other_duty;
	}
	public void setOther_duty(String other_duty) {
		this.other_duty = other_duty;
	}
	
	
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
	public String getTotassval() {
		return totassval;
	}
	public void setTotassval(String totassval) {
		this.totassval = totassval;
	}
	public String getTotduty() {
		return totduty;
	}
	public void setTotduty(String totduty) {
		this.totduty = totduty;
	}
	public String getDutyfg() {
		return dutyfg;
	}
	public void setDutyfg(String dutyfg) {
		this.dutyfg = dutyfg;
	}
	public String getDutycharged() {
		return dutycharged;
	}
	public void setDutycharged(String dutycharged) {
		this.dutycharged = dutycharged;
	}
	public String getOocdt() {
		return oocdt;
	}
	public void setOocdt(String oocdt) {
		this.oocdt = oocdt;
	}
	public String getDeldt() {
		return deldt;
	}
	public void setDeldt(String deldt) {
		this.deldt = deldt;
	}
	
	
}
