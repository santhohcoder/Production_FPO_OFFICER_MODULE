package com.demo.fpo.bean;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RegulationRecptIDBean {

	@Id
	private String article_id;
	private String scan_dt;
	private String bag_no;
	private String recd_event_dt;
	private String recept_id;
	private String bag_type;
	private String ooc_dt;
	public String getArticle_id() {
		return article_id;
	}
	public void setArticle_id(String article_id) {
		this.article_id = article_id;
	}
	public String getScan_dt() {
		return scan_dt;
	}
	public void setScan_dt(String scan_dt) {
		this.scan_dt = scan_dt;
	}
	public String getBag_no() {
		return bag_no;
	}
	public void setBag_no(String bag_no) {
		this.bag_no = bag_no;
	}
	public String getRecd_event_dt() {
		return recd_event_dt;
	}
	public void setRecd_event_dt(String recd_event_dt) {
		this.recd_event_dt = recd_event_dt;
	}
	public String getRecept_id() {
		return recept_id;
	}
	public void setRecept_id(String recept_id) {
		this.recept_id = recept_id;
	}
	public String getBag_type() {
		return bag_type;
	}
	public void setBag_type(String bag_type) {
		this.bag_type = bag_type;
	}
	public String getOoc_dt() {
		return ooc_dt;
	}
	public void setOoc_dt(String ooc_dt) {
		this.ooc_dt = ooc_dt;
	}

}
