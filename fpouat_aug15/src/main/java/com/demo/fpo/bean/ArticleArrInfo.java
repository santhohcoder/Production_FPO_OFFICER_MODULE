package com.demo.fpo.bean;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ArticleArrInfo {
	@Id
	
	private String articleId;
	private String articleDate;
	private String eadstatus;
	private String cinno;
	private String cindt;
	private String codeDesc;
	private String cntryCD;
	private String ooe;
	private String recdeventDt;
	private String recdfpo;
	private String recdDt;
	private String category;
	private String eaddeci;
    private String currentStatus;
    private String recpid;
    private String bagno;
    
    public String getRecpid() {
		return recpid;
	}

	public void setRecpid(String recpid) {
		this.recpid = recpid;
	}

	public String getBagno() {
		return bagno;
	}

	public void setBagno(String bagno) {
		this.bagno = bagno;
	}

  
	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	public String getArticleDate() {
		return articleDate;
	}
	public void setArticleDate(String articleDate) {
		this.articleDate = articleDate;
	}
	public String getEadstatus() {
		return eadstatus;
	}
	public void setEadstatus(String eadstatus) {
		this.eadstatus = eadstatus;
	}
	public String getCinno() {
		return cinno;
	}
	public String getEaddeci() {
		return eaddeci;
	}
	public void setEaddeci(String eaddeci) {
		this.eaddeci = eaddeci;
	}
	public void setCinno(String cinno) {
		this.cinno = cinno;
	}
	public String getCindt() {
		return cindt;
	}
	public void setCindt(String cindt) {
		this.cindt = cindt;
	}
	public String getCodeDesc() {
		return codeDesc;
	}
	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
	}
	public String getCntryCD() {
		return cntryCD;
	}
	public void setCntryCD(String cntryCD) {
		this.cntryCD = cntryCD;
	}
	public String getOoe() {
		return ooe;
	}
	public void setOoe(String ooe) {
		this.ooe = ooe;
	}
	public String getRecdeventDt() {
		return recdeventDt;
	}
	public void setRecdeventDt(String recdeventDt) {
		this.recdeventDt = recdeventDt;
	}
	public String getRecdfpo() {
		return recdfpo;
	}
	public void setRecdfpo(String recdfpo) {
		this.recdfpo = recdfpo;
	}
	public String getRecdDt() {
		return recdDt;
	}
	public void setRecdDt(String recdDt) {
		this.recdDt = recdDt;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCurrentStatus() {
		return currentStatus;
	}
	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	
	
}
