package com.demo.fpo.bean;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ViewArticle {
	@Id
	private String cinId;
	private String receiptOfCusItm;
	private String eadDecision;
	private String entryDate;
	private String exitDate;
	private String queryDate;
	private String orderDate;
	private String oocDate;
	private String status;

	public String getCinId() {
		return cinId;
	}

	public void setCinId(String cinId) {
		this.cinId = cinId;
	}

	public String getReceiptOfCusItm() {
		return receiptOfCusItm;
	}

	public void setReceiptOfCusItm(String receiptOfCusItm) {
		this.receiptOfCusItm = receiptOfCusItm;
	}

	public String getEadDecision() {
		return eadDecision;
	}

	public void setEadDecision(String eadDecision) {
		this.eadDecision = eadDecision;
	}

	public String getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	public String getExitDate() {
		return exitDate;
	}

	public void setExitDate(String exitDate) {
		this.exitDate = exitDate;
	}

	public String getQueryDate() {
		return queryDate;
	}

	public void setQueryDate(String queryDate) {
		this.queryDate = queryDate;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOocDate() {
		return oocDate;
	}

	public void setOocDate(String oocDate) {
		this.oocDate = oocDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
