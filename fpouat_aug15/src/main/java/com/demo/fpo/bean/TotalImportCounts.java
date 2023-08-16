package com.demo.fpo.bean;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.transaction.Transactional;

@Entity
public class TotalImportCounts {

	@Id
	private BigDecimal personalImports;
	private BigDecimal detained;
	private BigDecimal commercialImports;
	public BigDecimal getPersonalImports() {
		return personalImports;
	}
	public void setPersonalImports(BigDecimal personalImports) {
		this.personalImports = personalImports;
	}
	public BigDecimal getDetained() {
		return detained;
	}
	public void setDetained(BigDecimal detained) {
		this.detained = detained;
	}
	public BigDecimal getCommercialImports() {
		return commercialImports;
	}
	public void setCommercialImports(BigDecimal commercialImports) {
		this.commercialImports = commercialImports;
	}
	

}
