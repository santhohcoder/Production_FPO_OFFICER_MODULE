package com.demo.fpo.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class grandtotinfo {
	@Id
	private String cin_no;
	private Float assval;
	private Float bcd;
	private Float igst;
	private Float sws;
	private Float oth;
	private Float dutysum;
	private Float totduty;
	private float fine;
	private float penalty;
	private Float totpay;

	public String getCin_no() {
		return cin_no;
	}

	public void setCin_no(String cin_no) {
		this.cin_no = cin_no;
	}

	public float getFine() {
		return fine;
	}

	public void setFine(float fine) {
		this.fine = fine;
	}

	public float getPenalty() {
		return penalty;
	}

	public void setPenalty(float penalty) {
		this.penalty = penalty;
	}

	public Float getTotpay() {
		return totpay;
	}

	public void setTotpay(Float totpay) {
		this.totpay = totpay;
	}

	public Float getAssval() {
		return assval;
	}

	public void setAssval(Float assval) {
		this.assval = assval;
	}

	public Float getBcd() {
		return bcd;
	}

	public void setBcd(Float bcd) {
		this.bcd = bcd;
	}

	public Float getIgst() {
		return igst;
	}

	public void setIgst(Float igst) {
		this.igst = igst;
	}

	public Float getSws() {
		return sws;
	}

	public void setSws(Float sws) {
		this.sws = sws;
	}

	public Float getOth() {
		return oth;
	}

	public void setOth(Float oth) {
		this.oth = oth;
	}

	public Float getDutysum() {
		return dutysum;
	}

	public void setDutysum(Float dutysum) {
		this.dutysum = dutysum;
	}

	public Float getTotduty() {
		return totduty;
	}

	public void setTotduty(Float totduty) {
		this.totduty = totduty;
	}
}
