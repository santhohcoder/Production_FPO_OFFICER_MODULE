package com.demo.fpo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OpeningReportColumns {
	@Id
	private String id;
	private String item_id;
	private String post_dt;
	private String recd_event_dt;
	private String org_cd;
	private String mail_class;
	private String mail_category;
	private String total_value;
	private String total_duty;
	private String total_item;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
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
	 * @return the post_dt
	 */
	public String getPost_dt() {
		return post_dt;
	}
	/**
	 * @param post_dt the post_dt to set
	 */
	public void setPost_dt(String post_dt) {
		this.post_dt = post_dt;
	}
	/**
	 * @return the recd_event_dt
	 */
	public String getRecd_event_dt() {
		return recd_event_dt;
	}
	/**
	 * @param recd_event_dt the recd_event_dt to set
	 */
	public void setRecd_event_dt(String recd_event_dt) {
		this.recd_event_dt = recd_event_dt;
	}
	/**
	 * @return the org_cd
	 */
	public String getOrg_cd() {
		return org_cd;
	}
	/**
	 * @param org_cd the org_cd to set
	 */
	public void setOrg_cd(String org_cd) {
		this.org_cd = org_cd;
	}
	/**
	 * @return the mail_class
	 */
	public String getMail_class() {
		return mail_class;
	}
	/**
	 * @param mail_class the mail_class to set
	 */
	public void setMail_class(String mail_class) {
		this.mail_class = mail_class;
	}
	/**
	 * @return the mail_category
	 */
	public String getMail_category() {
		return mail_category;
	}
	/**
	 * @param mail_category the mail_category to set
	 */
	public void setMail_category(String mail_category) {
		this.mail_category = mail_category;
	}
	/**
	 * @return the total_value
	 */
	public String getTotal_value() {
		return total_value;
	}
	/**
	 * @param total_value the total_value to set
	 */
	public void setTotal_value(String total_value) {
		this.total_value = total_value;
	}
	/**
	 * @return the total_duty
	 */
	public String getTotal_duty() {
		return total_duty;
	}
	/**
	 * @param total_duty the total_duty to set
	 */
	public void setTotal_duty(String total_duty) {
		this.total_duty = total_duty;
	}
	/**
	 * @return the total_item
	 */
	public String getTotal_item() {
		return total_item;
	}
	/**
	 * @param total_item the total_item to set
	 */
	public void setTotal_item(String total_item) {
		this.total_item = total_item;
	}
	
	
	
	
	

}
