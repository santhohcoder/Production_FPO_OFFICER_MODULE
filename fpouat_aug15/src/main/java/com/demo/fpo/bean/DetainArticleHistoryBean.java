package com.demo.fpo.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DetainArticleHistoryBean {
	@Id
	private String articleId;
	private Date articleDate;
	private String originCountry;
	private String mailClass;
	private String itemCategory;
	private Date arrivalOOEDate;
	private Date arrivalFPODate;
	private Date detentionDate;
	private String detainDecision;
	private Long detainedNo;
	private Date detainDecisionDate;
	private String currentStatus;

	
	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public Date getArticleDate() {
		return articleDate;
	}

	public void setArticleDate(Date articleDate) {
		this.articleDate = articleDate;
	}

	public String getOriginCountry() {
		return originCountry;
	}

	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}

	public String getMailClass() {
		return mailClass;
	}

	public void setMailClass(String mailClass) {
		this.mailClass = mailClass;
	}

	public String getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

	public Date getArrivalOOEDate() {
		return arrivalOOEDate;
	}

	public void setArrivalOOEDate(Date arrivalOOEDate) {
		this.arrivalOOEDate = arrivalOOEDate;
	}

	public Date getArrivalFPODate() {
		return arrivalFPODate;
	}

	public void setArrivalFPODate(Date arrivalFPODate) {
		this.arrivalFPODate = arrivalFPODate;
	}

	public Date getDetentionDate() {
		return detentionDate;
	}

	public void setDetentionDate(Date detentionDate) {
		this.detentionDate = detentionDate;
	}

	public String getDetainDecision() {
		return detainDecision;
	}

	public void setDetainDecision(String detainDecision) {
		this.detainDecision = detainDecision;
	}

	public Long getDetainedNo() {
		return detainedNo;
	}

	public void setDetainedNo(Long detainedNo) {
		this.detainedNo = detainedNo;
	}

	public Date getDetainDecisionDate() {
		return detainDecisionDate;
	}

	public void setDetainDecisionDate(Date detainDecisionDate) {
		this.detainDecisionDate = detainDecisionDate;
	}
	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

}
