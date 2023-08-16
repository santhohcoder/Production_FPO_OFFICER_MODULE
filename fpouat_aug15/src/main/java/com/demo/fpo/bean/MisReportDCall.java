package com.demo.fpo.bean;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MisReportDCall {
	
	@Id
	private String id;
	private String item_id;
	private String post_dt;
	private String dcallno;
	private String dcalldate;
	private String pushdcalldate;
	private String officer;
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
	public String getDcallno() {
		return dcallno;
	}
	public void setDcallno(String dcallno) {
		this.dcallno = dcallno;
	}
	public String getDcalldate() {
		return dcalldate;
	}
	public void setDcalldate(String dcalldate) {
		this.dcalldate = dcalldate;
	}
	public String getPushdcalldate() {
		return pushdcalldate;
	}
	public void setPushdcalldate(String pushdcalldate) {
		this.pushdcalldate = pushdcalldate;
	}
	public String getOfficer() {
		return officer;
	}
	public void setOfficer(String officer) {
		this.officer = officer;
	}
	
	

}
