package com.demo.fpo.bean;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.transaction.Transactional;

@Entity
public class EADInboundArticles {

	@Id
	private String country;
	private BigDecimal noArticles;
	private BigDecimal totAssVal;
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public BigDecimal getNoArticles() {
		return noArticles;
	}
	public void setNoArticles(BigDecimal noArticles) {
		this.noArticles = noArticles;
	}
	public BigDecimal getTotAssVal() {
		return totAssVal;
	}
	public void setTotAssVal(BigDecimal totAssVal) {
		this.totAssVal = totAssVal;
	}
	
}
