package com.demo.fpo.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DetentionTrack {
	@Id
	private String articleId;
	private Date detentionDate;
	private String detentionOfficer;
	private String decisionTaken;
	private Date decisionDate;
	private String decisionOfficer;

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public Date getDetentionDate() {
		return detentionDate;
	}

	public void setDetentionDate(Date detentionDate) {
		this.detentionDate = detentionDate;
	}

	public String getDetentionOfficer() {
		return detentionOfficer;
	}

	public void setDetentionOfficer(String detentionOfficer) {
		this.detentionOfficer = detentionOfficer;
	}

	public String getDecisionTaken() {
		return decisionTaken;
	}

	public void setDecisionTaken(String decisionTaken) {
		this.decisionTaken = decisionTaken;
	}

	public Date getDecisionDate() {
		return decisionDate;
	}

	public void setDecisionDate(Date decisionDate) {
		this.decisionDate = decisionDate;
	}

	public String getDecisionOfficer() {
		return decisionOfficer;
	}

	public void setDecisionOfficer(String decisionOfficer) {
		this.decisionOfficer = decisionOfficer;
	}

}
