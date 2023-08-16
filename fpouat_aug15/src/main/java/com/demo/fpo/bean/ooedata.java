package com.demo.fpo.bean;


import javax.persistence.Id;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ooedata {
	private String recp_id;
	@Id
	private String item_id;
	public String getRecp_id() {
		return recp_id;
	}
	public void setRecp_id(String recp_id) {
		this.recp_id = recp_id;
	}
	public String getItem_id() {
		return item_id;
	}
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	
	
	
	
	

}
