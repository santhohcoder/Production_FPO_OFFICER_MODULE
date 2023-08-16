package com.demo.fpo.bean;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MisReportOocGivenDeliStatus {
	
	@Id
	private String id;
	private String tot_article;
	//private String mail_class;
	private String letter;
	private String ems;
	private String parcel;
	private String empty;
	private String TOT_ASS_VAL;
	private String TOT_DUTY;
	private String TOT_DUTY_FG;
	private String TOT_AMT_PAYABLE;
	private String bcd_amt;
	private String igst_amt;
	private String sw_amt;
	private String other_duty;
	private String delivered;
	private String notdelivered;
	private String deliveryack;
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
	public String getTot_article() {
		return tot_article;
	}
	public void setTot_article(String tot_article) {
		this.tot_article = tot_article;
	}
	public String getLetter() {
		return letter;
	}
	public void setLetter(String letter) {
		this.letter = letter;
	}
	public String getEms() {
		return ems;
	}
	public void setEms(String ems) {
		this.ems = ems;
	}
	public String getParcel() {
		return parcel;
	}
	public void setParcel(String parcel) {
		this.parcel = parcel;
	}
	public String getEmpty() {
		return empty;
	}
	public void setEmpty(String empty) {
		this.empty = empty;
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
	public String getDelivered() {
		return delivered;
	}
	public void setDelivered(String delivered) {
		this.delivered = delivered;
	}
	public String getNotdelivered() {
		return notdelivered;
	}
	public void setNotdelivered(String notdelivered) {
		this.notdelivered = notdelivered;
	}
	public String getDeliveryack() {
		return deliveryack;
	}
	public void setDeliveryack(String deliveryack) {
		this.deliveryack = deliveryack;
	}
	
	
	

}
