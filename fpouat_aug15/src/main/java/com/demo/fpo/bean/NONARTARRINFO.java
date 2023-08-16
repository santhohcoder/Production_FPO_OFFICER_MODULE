package com.demo.fpo.bean;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class NONARTARRINFO {
	@Id
	
	private String articleId;
	
	private String postingdt;
	private String cinno;
	private String cindt;
	private String codeDesc;
	private String cntryCD;
	private String cussite;
	private String ooe;
	private String recdeventDt;
	private String recdfpo;
	private String recdDt;
	private String category;
	private String precpid;
    private String arecpid;
    private String bagno;
    
	public String getPostingdt() {
		return postingdt;
	}

	public void setPostingdt(String postingdt) {
		this.postingdt = postingdt;
	}

	public String getCussite() {
		return cussite;
	}

	public void setCussite(String cussite) {
		this.cussite = cussite;
	}

	public String getPrecpid() {
		return precpid;
	}

	public void setPrecpid(String precpid) {
		this.precpid = precpid;
	}

	public String getArecpid() {
		return arecpid;
	}

	public void setArecpid(String arecpid) {
		this.arecpid = arecpid;
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

	public String getCinno() {
		return cinno;
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


	
	
}
