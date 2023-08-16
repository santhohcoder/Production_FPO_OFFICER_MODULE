package com.demo.fpo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DetainedParcelClearence {
	
	@Id
	private String id;
	private String opening_balance;
	private String month_receipt;
	private String uptomonth_receipt;
	private String detained_for_month;
	private String detained_upto_month;
	private String disposal_for_month;
	private String disposal_upto_month;
	private String closing_balance;
	private String customs_duty_month;
	private String customs_duty_upto_month;
	
	
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
	 * @return the opening_balance
	 */
	public String getOpening_balance() {
		return opening_balance;
	}
	/**
	 * @param opening_balance the opening_balance to set
	 */
	public void setOpening_balance(String opening_balance) {
		this.opening_balance = opening_balance;
	}
	/**
	 * @return the month_receipt
	 */
	public String getMonth_receipt() {
		return month_receipt;
	}
	/**
	 * @param month_receipt the month_receipt to set
	 */
	public void setMonth_receipt(String month_receipt) {
		this.month_receipt = month_receipt;
	}
	/**
	 * @return the uptomonth_receipt
	 */
	public String getUptomonth_receipt() {
		return uptomonth_receipt;
	}
	/**
	 * @param uptomonth_receipt the uptomonth_receipt to set
	 */
	public void setUptomonth_receipt(String uptomonth_receipt) {
		this.uptomonth_receipt = uptomonth_receipt;
	}
	/**
	 * @return the detained_for_month
	 */
	public String getDetained_for_month() {
		return detained_for_month;
	}
	/**
	 * @param detained_for_month the detained_for_month to set
	 */
	public void setDetained_for_month(String detained_for_month) {
		this.detained_for_month = detained_for_month;
	}
	/**
	 * @return the detained_upto_month
	 */
	public String getDetained_upto_month() {
		return detained_upto_month;
	}
	/**
	 * @param detained_upto_month the detained_upto_month to set
	 */
	public void setDetained_upto_month(String detained_upto_month) {
		this.detained_upto_month = detained_upto_month;
	}
	/**
	 * @return the disposal_for_month
	 */
	public String getDisposal_for_month() {
		return disposal_for_month;
	}
	/**
	 * @param disposal_for_month the disposal_for_month to set
	 */
	public void setDisposal_for_month(String disposal_for_month) {
		this.disposal_for_month = disposal_for_month;
	}
	/**
	 * @return the disposal_upto_month
	 */
	public String getDisposal_upto_month() {
		return disposal_upto_month;
	}
	/**
	 * @param disposal_upto_month the disposal_upto_month to set
	 */
	public void setDisposal_upto_month(String disposal_upto_month) {
		this.disposal_upto_month = disposal_upto_month;
	}
	/**
	 * @return the closing_balance
	 */
	public String getClosing_balance() {
		return closing_balance;
	}
	/**
	 * @param closing_balance the closing_balance to set
	 */
	public void setClosing_balance(String closing_balance) {
		this.closing_balance = closing_balance;
	}
	/**
	 * @return the customs_duty_month
	 */
	public String getCustoms_duty_month() {
		return customs_duty_month;
	}
	/**
	 * @param customs_duty_month the customs_duty_month to set
	 */
	public void setCustoms_duty_month(String customs_duty_month) {
		this.customs_duty_month = customs_duty_month;
	}
	/**
	 * @return the customs_duty_upto_month
	 */
	public String getCustoms_duty_upto_month() {
		return customs_duty_upto_month;
	}
	/**
	 * @param customs_duty_upto_month the customs_duty_upto_month to set
	 */
	public void setCustoms_duty_upto_month(String customs_duty_upto_month) {
		this.customs_duty_upto_month = customs_duty_upto_month;
	}
	
	
	
}
