package com.demo.fpo.bean;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ImportExam {
	
	@Id
	private String id;
	private String item_id;
	private String cin_dt;
	private String coo;
	private String item_category;
	private String arrival_ooe;
	private String recd_event_dt;
	private String recd_fpo;
	private String arrivalfpo_date;
	
	
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
	public String getCin_dt() {
		return cin_dt;
	}
	public void setCin_dt(String cin_dt) {
		this.cin_dt = cin_dt;
	}
	public String getCoo() {
		return coo;
	}
	public void setCoo(String coo) {
		this.coo = coo;
	}
	public String getItem_category() {
		return item_category;
	}
	public void setItem_category(String item_category) {
		this.item_category = item_category;
	}
	public String getArrival_ooe() {
		return arrival_ooe;
	}
	public void setArrival_ooe(String arrival_ooe) {
		this.arrival_ooe = arrival_ooe;
	}
	public String getRecd_event_dt() {
		return recd_event_dt;
	}
	public void setRecd_event_dt(String recd_event_dt) {
		this.recd_event_dt = recd_event_dt;
	}
	public String getRecd_fpo() {
		return recd_fpo;
	}
	public void setRecd_fpo(String recd_fpo) {
		this.recd_fpo = recd_fpo;
	}
	public String getArrivalfpo_date() {
		return arrivalfpo_date;
	}
	public void setArrivalfpo_date(String arrivalfpo_date) {
		this.arrivalfpo_date = arrivalfpo_date;
	}
	
	
	
	

}
