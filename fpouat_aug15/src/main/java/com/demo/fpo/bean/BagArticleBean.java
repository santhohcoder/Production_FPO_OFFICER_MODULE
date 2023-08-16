package com.demo.fpo.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class BagArticleBean {
	@Id
	private String articleId;
	private String eadExist;
	private String destinationFpo;
	private String examOrderExist;
	private String scanReport;
	private String originCountry;
	private String mapped;
	private String clrsite;
	public String getClrsite() {
		return clrsite;
	}

	public void setClrsite(String clrsite) {
		this.clrsite = clrsite;
	}

	public String getMapped() {
		return mapped;
	}

	public void setMapped(String mapped) {
		this.mapped = mapped;
	}

	@Transient
	private ArticleArrivalInfoBean articleArrivalInfo;

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public String getEadExist() {
		return eadExist;
	}

	public void setEadExist(String eadExist) {
		this.eadExist = eadExist;
	}

	public String getDestinationFpo() {
		return destinationFpo;
	}

	public void setDestinationFpo(String destinationFpo) {
		this.destinationFpo = destinationFpo;
	}

	public String getExamOrderExist() {
		return examOrderExist;
	}

	public void setExamOrderExist(String examOrderExist) {
		this.examOrderExist = examOrderExist;
	}

	public String getScanReport() {
		return scanReport;
	}

	public void setScanReport(String scanReport) {
		this.scanReport = scanReport;
	}

	public String getOriginCountry() {
		return originCountry;
	}

	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}

	public ArticleArrivalInfoBean getArticleArrivalInfo() {
		return articleArrivalInfo;
	}

	public void setArticleArrivalInfo(ArticleArrivalInfoBean articleArrivalInfo) {
		this.articleArrivalInfo = articleArrivalInfo;
	}

}
