package com.demo.fpo.bean;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.transaction.Transactional;

@Entity
public class PincodeCount {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigDecimal id;
	private BigDecimal no_articles;
	private String cus_site;
	public BigDecimal getNo_articles() {
		return no_articles;
	}
	public void setNo_articles(BigDecimal no_articles) {
		this.no_articles = no_articles;
	}
	public String getCus_site() {
		return cus_site;
	}
	public void setCus_site(String cus_site) {
		this.cus_site = cus_site;
	}
	

}
