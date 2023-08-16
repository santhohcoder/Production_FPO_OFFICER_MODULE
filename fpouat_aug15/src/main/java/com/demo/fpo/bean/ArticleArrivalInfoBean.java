package com.demo.fpo.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ArticleArrivalInfoBean {
	@Id
	private String articleId;
	private String recptId;
	private String ooe;
	private Date ooeDate;
	private String scanReport;

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public String getRecptId() {
		return recptId;
	}

	public void setRecptId(String recptId) {
		this.recptId = recptId;
	}

	public String getOoe() {
		return ooe;
	}

	public void setOoe(String ooe) {
		this.ooe = ooe;
	}

	public Date getOoeDate() {
		return ooeDate;
	}

	public void setOoeDate(Date ooeDate) {
		this.ooeDate = ooeDate;
	}

	public String getScanReport() {
		return scanReport;
	}

	public void setScanReport(String scanReport) {
		this.scanReport = scanReport;
	}

}
