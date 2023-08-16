package com.demo.fpo.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DetainedArticleBean {
	@Id
	private String articleId;
	private Date articleDate;
	private String originCountry;
	private String mailClass;
	private String itemCategory;
	private Date arrivalOOEDate;
	private Date arrivalFPODate;
	private Date detentionDate;
	private String currentOfficerRole;

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

	public String getCurrentOfficerRole() {
		return currentOfficerRole;
	}

	public void setCurrentOfficerRole(String currentOfficerRole) {
		this.currentOfficerRole = currentOfficerRole;
	}

}
