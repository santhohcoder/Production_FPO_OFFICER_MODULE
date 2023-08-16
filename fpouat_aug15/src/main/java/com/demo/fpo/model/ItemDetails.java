package com.demo.fpo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ItemDetails {

	@Id
	private String item_no;
	private String item_desc;
	private String cth;
	private String nou;
	private String assess_val;
	private String duty;
	private String bcd;
	private String igst;
	private String sws;
	private String other_duty;
	
	
	/**
	 * @return the item_no
	 */
	public String getItem_no() {
		return item_no;
	}
	/**
	 * @param item_no the item_no to set
	 */
	public void setItem_no(String item_no) {
		this.item_no = item_no;
	}
	/**
	 * @return the item_desc
	 */
	public String getItem_desc() {
		return item_desc;
	}
	/**
	 * @param item_desc the item_desc to set
	 */
	public void setItem_desc(String item_desc) {
		this.item_desc = item_desc;
	}
	/**
	 * @return the cth
	 */
	public String getCth() {
		return cth;
	}
	/**
	 * @param cth the cth to set
	 */
	public void setCth(String cth) {
		this.cth = cth;
	}
	/**
	 * @return the nou
	 */
	public String getNou() {
		return nou;
	}
	/**
	 * @param nou the nou to set
	 */
	public void setNou(String nou) {
		this.nou = nou;
	}
	/**
	 * @return the assess_val
	 */
	public String getAssess_val() {
		return assess_val;
	}
	/**
	 * @param assess_val the assess_val to set
	 */
	public void setAssess_val(String assess_val) {
		this.assess_val = assess_val;
	}
	/**
	 * @return the duty
	 */
	public String getDuty() {
		return duty;
	}
	/**
	 * @param duty the duty to set
	 */
	public void setDuty(String duty) {
		this.duty = duty;
	}
	/**
	 * @return the bcd
	 */
	public String getBcd() {
		return bcd;
	}
	/**
	 * @param bcd the bcd to set
	 */
	public void setBcd(String bcd) {
		this.bcd = bcd;
	}
	/**
	 * @return the igst
	 */
	public String getIgst() {
		return igst;
	}
	/**
	 * @param igst the igst to set
	 */
	public void setIgst(String igst) {
		this.igst = igst;
	}
	/**
	 * @return the sws
	 */
	public String getSws() {
		return sws;
	}
	/**
	 * @param sws the sws to set
	 */
	public void setSws(String sws) {
		this.sws = sws;
	}
	/**
	 * @return the other_duty
	 */
	public String getOther_duty() {
		return other_duty;
	}
	/**
	 * @param other_duty the other_duty to set
	 */
	public void setOther_duty(String other_duty) {
		this.other_duty = other_duty;
	}
	
	
	
	
	
	
}
