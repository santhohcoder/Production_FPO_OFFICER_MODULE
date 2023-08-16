package com.demo.fpo.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
@Entity
public class MisReportArtcDetained {
	
	@Id
	private String id;
	private String item_id;
	private String coo;
	private String post_dt;
	private String TOT_ASS_VAL;
	private String TOT_DUTY;
	private String TOT_FINE;
	private String TOT_PENAL;
	private String scndt;
	private String onodt;
	@Transient
	private String status;
	@Transient
	private String queue;
	private String cussite;
	
	
	
	public String getCussite() {
		return cussite;
	}
	public void setCussite(String cussite) {
		this.cussite = cussite;
	}
	public String getQueue() {
		return queue;
	}
	public void setQueue(String queue) {
		this.queue = queue;
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
	public String getCoo() {
		return coo;
	}
	public void setCoo(String coo) {
		this.coo = coo;
	}
	public String getPost_dt() {
		return post_dt;
	}
	public void setPost_dt(String post_dt) {
		this.post_dt = post_dt;
	}
	public String getTOT_ASS_VAL() {
		return TOT_ASS_VAL;
	}
	public void setTOT_ASS_VAL(String tOT_ASS_VAL) {
		TOT_ASS_VAL = tOT_ASS_VAL;
	}
	public String getTOT_DUTY() {
		return TOT_DUTY;
	}
	public void setTOT_DUTY(String tOT_DUTY) {
		TOT_DUTY = tOT_DUTY;
	}
	public String getTOT_FINE() {
		return TOT_FINE;
	}
	public void setTOT_FINE(String tOT_FINE) {
		TOT_FINE = tOT_FINE;
	}
	public String getTOT_PENAL() {
		return TOT_PENAL;
	}
	public void setTOT_PENAL(String tOT_PENAL) {
		TOT_PENAL = tOT_PENAL;
	}
	public String getScndt() {
		return scndt;
	}
	public void setScndt(String scndt) {
		this.scndt = scndt;
	}
	public String getOnodt() {
		return onodt;
	}
	public void setOnodt(String onodt) {
		this.onodt = onodt;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	
	

}
