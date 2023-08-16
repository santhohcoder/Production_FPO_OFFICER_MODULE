package com.demo.fpo.bean;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.transaction.Transactional;

@Entity
public class TotalArticlesCount {

	@Id
	private BigDecimal ead;
	private BigDecimal aaa;
	private BigDecimal aaf;
	public BigDecimal getEad() {
		return ead;
	}
	public void setEad(BigDecimal ead) {
		this.ead = ead;
	}
	public BigDecimal getAaa() {
		return aaa;
	}
	public void setAaa(BigDecimal aaa) {
		this.aaa = aaa;
	}
	public BigDecimal getAaf() {
		return aaf;
	}
	public void setAaf(BigDecimal aaf) {
		this.aaf = aaf;
	}

	
}
