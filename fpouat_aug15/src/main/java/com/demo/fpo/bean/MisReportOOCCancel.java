package com.demo.fpo.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class MisReportOOCCancel {
	
	@Id
	private String id;
	private String item_id;
	private String post_dt;
	private String coo;
	private String OOC_DT;
	private String OOC_CancelDT;
	private String TOT_ASS_VAL;
	private String TOT_DUTY;
	private String TOT_DUTY_FG;
	private String TOT_AMT_PAYABLE;
	private String bcd_amt;
	private String igst_amt;
	private String sw_amt;
	private String other_duty;
	private String RevisedOOC_DT;
	@Transient
	private String status;
	private String cussite;
	
	public String getCussite() {
		return cussite;
	}
	public void setCussite(String cussite) {
		this.cussite = cussite;
	}
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
	public String getPost_dt() {
		return post_dt;
	}
	public void setPost_dt(String post_dt) {
		this.post_dt = post_dt;
	}
	public String getCoo() {
		return coo;
	}
	public void setCoo(String coo) {
		this.coo = coo;
	}
	public String getOOC_DT() {
		return OOC_DT;
	}
	public void setOOC_DT(String oOC_DT) {
		OOC_DT = oOC_DT;
	}
	public String getOOC_CancelDT() {
		return OOC_CancelDT;
	}
	public void setOOC_CancelDT(String oOC_CancelDT) {
		OOC_CancelDT = oOC_CancelDT;
	}
	public String getTOT_ASS_VAL() {
		return TOT_ASS_VAL;
	}
	public void setTOT_ASS_VAL(String tOT_ASS_VAL) {
		TOT_ASS_VAL = tOT_ASS_VAL;
	}
	public String getTOT_DUTY() {
		return TOT_DUTY;
	}
	public void setTOT_DUTY(String tOT_DUTY) {
		TOT_DUTY = tOT_DUTY;
	}
	public String getTOT_DUTY_FG() {
		return TOT_DUTY_FG;
	}
	public void setTOT_DUTY_FG(String tOT_DUTY_FG) {
		TOT_DUTY_FG = tOT_DUTY_FG;
	}
	public String getTOT_AMT_PAYABLE() {
		return TOT_AMT_PAYABLE;
	}
	public void setTOT_AMT_PAYABLE(String tOT_AMT_PAYABLE) {
		TOT_AMT_PAYABLE = tOT_AMT_PAYABLE;
	}
	public String getBcd_amt() {
		return bcd_amt;
	}
	public void setBcd_amt(String bcd_amt) {
		this.bcd_amt = bcd_amt;
	}
	public String getIgst_amt() {
		return igst_amt;
	}
	public void setIgst_amt(String igst_amt) {
		this.igst_amt = igst_amt;
	}
	public String getSw_amt() {
		return sw_amt;
	}
	public void setSw_amt(String sw_amt) {
		this.sw_amt = sw_amt;
	}
	public String getOther_duty() {
		return other_duty;
	}
	public void setOther_duty(String other_duty) {
		this.other_duty = other_duty;
	}
	public String getRevisedOOC_DT() {
		return RevisedOOC_DT;
	}
	public void setRevisedOOC_DT(String revisedOOC_DT) {
		RevisedOOC_DT = revisedOOC_DT;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	

}
