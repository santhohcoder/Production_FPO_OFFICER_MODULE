package com.demo.fpo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ItemAmountDetails {

	@Id
	private String id;
	private String item_id;
	private String recp_name;
	private String recp_addrs;
	private String gross_wt;
	private String tot_amt_payable;
	private String fine;
	private String penalty;
	private String mail_class;
	private String mail_category;
	private String delivery_status;
	
	
	/**
	 * @return the delivery_status
	 */
	public String getDelivery_status() {
		return delivery_status;
	}
	/**
	 * @param delivery_status the delivery_status to set
	 */
	public void setDelivery_status(String delivery_status) {
		this.delivery_status = delivery_status;
	}
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
	 * @return the recp_name
	 */
	public String getRecp_name() {
		return recp_name;
	}
	/**
	 * @param recp_name the recp_name to set
	 */
	public void setRecp_name(String recp_name) {
		this.recp_name = recp_name;
	}
	/**
	 * @return the recp_addrs
	 */
	public String getRecp_addrs() {
		return recp_addrs;
	}
	/**
	 * @param recp_addrs the recp_addrs to set
	 */
	public void setRecp_addrs(String recp_addrs) {
		this.recp_addrs = recp_addrs;
	}
	/**
	 * @return the gross_wt
	 */
	public String getGross_wt() {
		return gross_wt;
	}
	/**
	 * @param gross_wt the gross_wt to set
	 */
	public void setGross_wt(String gross_wt) {
		this.gross_wt = gross_wt;
	}
	/**
	 * @return the tot_amt_payable
	 */
	public String getTot_amt_payable() {
		return tot_amt_payable;
	}
	/**
	 * @param tot_amt_payable the tot_amt_payable to set
	 */
	public void setTot_amt_payable(String tot_amt_payable) {
		this.tot_amt_payable = tot_amt_payable;
	}
	/**
	 * @return the fine
	 */
	public String getFine() {
		return fine;
	}
	/**
	 * @param fine the fine to set
	 */
	public void setFine(String fine) {
		this.fine = fine;
	}
	/**
	 * @return the penalty
	 */
	public String getPenalty() {
		return penalty;
	}
	/**
	 * @param penalty the penalty to set
	 */
	public void setPenalty(String penalty) {
		this.penalty = penalty;
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
	
	
	
	
	
}
