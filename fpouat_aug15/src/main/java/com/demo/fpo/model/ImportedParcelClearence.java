package com.demo.fpo.model;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class ImportedParcelClearence {

	@Id
	private String id;
	private String opening_balance;
	private String month_receipt;
	private String uptomonth_receipt;
	private String ooc_without_exam_ass;
	private String ooc_exam;
	private String ooc_ass_exam;
	private String detained;
	private String total;
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
		id = id;
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
	 * @return the ooc_without_exam_ass
	 */
	public String getOoc_without_exam_ass() {
		return ooc_without_exam_ass;
	}
	/**
	 * @param ooc_without_exam_ass the ooc_without_exam_ass to set
	 */
	public void setOoc_without_exam_ass(String ooc_without_exam_ass) {
		this.ooc_without_exam_ass = ooc_without_exam_ass;
	}
	/**
	 * @return the ooc_exam
	 */
	public String getOoc_exam() {
		return ooc_exam;
	}
	/**
	 * @param ooc_exam the ooc_exam to set
	 */
	public void setOoc_exam(String ooc_exam) {
		this.ooc_exam = ooc_exam;
	}
	/**
	 * @return the ooc_ass_exam
	 */
	public String getOoc_ass_exam() {
		return ooc_ass_exam;
	}
	/**
	 * @param ooc_ass_exam the ooc_ass_exam to set
	 */
	public void setOoc_ass_exam(String ooc_ass_exam) {
		this.ooc_ass_exam = ooc_ass_exam;
	}
	/**
	 * @return the detained
	 */
	public String getDetained() {
		return detained;
	}
	/**
	 * @param detained the detained to set
	 */
	public void setDetained(String detained) {
		this.detained = detained;
	}
	/**
	 * @return the total
	 */
	public String getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(String total) {
		this.total = total;
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
