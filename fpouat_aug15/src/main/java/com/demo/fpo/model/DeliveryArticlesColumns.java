package com.demo.fpo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DeliveryArticlesColumns {
	@Id
	private Long id;
	private String item_id;
	private String deli_po;
	private String deli_dt;
	private String deli_status;
	private String deli_mode;
	private String mailclass;
	private String eadstatus;
	private String postingdt;
	private String cussite;
	private String oocdt;
	public String getMailclass() {
		return mailclass;
	}
	public void setMailclass(String mailclass) {
		this.mailclass = mailclass;
	}
	public String getEadstatus() {
		return eadstatus;
	}
	public void setEadstatus(String eadstatus) {
		this.eadstatus = eadstatus;
	}
	public String getPostingdt() {
		return postingdt;
	}
	public void setPostingdt(String postingdt) {
		this.postingdt = postingdt;
	}
	public String getCussite() {
		return cussite;
	}
	public void setCussite(String cussite) {
		this.cussite = cussite;
	}
	public String getOocdt() {
		return oocdt;
	}
	public void setOocdt(String oocdt) {
		this.oocdt = oocdt;
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the item_id
	 */
	public String getItem_id() {
		return item_id;
	}
	/**
	 * @param item_id the item_id to set
	 */
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	/**
	 * @return the deli_po
	 */
	public String getDeli_po() {
		return deli_po;
	}
	/**
	 * @param deli_po the deli_po to set
	 */
	public void setDeli_po(String deli_po) {
		this.deli_po = deli_po;
	}
	/**
	 * @return the deli_dt
	 */
	public String getDeli_dt() {
		return deli_dt;
	}
	/**
	 * @param deli_dt the deli_dt to set
	 */
	public void setDeli_dt(String deli_dt) {
		this.deli_dt = deli_dt;
	}
	/**
	 * @return the deli_status
	 */
	public String getDeli_status() {
		return deli_status;
	}
	/**
	 * @param deli_status the deli_status to set
	 */
	public void setDeli_status(String deli_status) {
		this.deli_status = deli_status;
	}
	/**
	 * @return the deli_mode
	 */
	public String getDeli_mode() {
		return deli_mode;
	}
	/**
	 * @param deli_mode the deli_mode to set
	 */
	public void setDeli_mode(String deli_mode) {
		this.deli_mode = deli_mode;
	}
	
	
	
	

}
