package com.demo.fpo.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class QueryReplyHistoryBean {
	@Id
	private Long id;
	private String articleId;
	private Date articleDate;
	private String originCountry;
	private String mailClass;
	private String itemCategory;
	private Date arrivalOOEDate;
	private Date arrivalFPODate;
	private Date replyDate;
	private String officerId;
	private String cinNo;
	private String articleStage;
	private Boolean additionalQry;
	@Transient
	private String currentStatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Date getReplyDate() {
		return replyDate;
	}

	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
	}

	public String getOfficerId() {
		return officerId;
	}

	public void setOfficerId(String officerId) {
		this.officerId = officerId;
	}

	public String getCinNo() {
		return cinNo;
	}

	public void setCinNo(String cinNo) {
		this.cinNo = cinNo;
	}

	public String getArticleStage() {
		return articleStage;
	}

	public void setArticleStage(String articleStage) {
		this.articleStage = articleStage;
	}

	public Boolean getAdditionalQry() {
		return additionalQry;
	}

	public void setAdditionalQry(Boolean additionalQry) {
		this.additionalQry = additionalQry;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

}
