package com.demo.fpo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DeliveryArticlesPendingColumns {
	
	@Id
	private Long id;
	private String item_id;
	private String ooc_dt;
	private String status;
	
	
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
	 * @return the ooc_dt
	 */
	public String getOoc_dt() {
		return ooc_dt;
	}
	/**
	 * @param ooc_dt the ooc_dt to set
	 */
	public void setOoc_dt(String ooc_dt) {
		this.ooc_dt = ooc_dt;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
