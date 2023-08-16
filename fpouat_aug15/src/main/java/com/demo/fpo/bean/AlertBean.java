package com.demo.fpo.bean;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AlertBean {
	
	@Id
	private BigDecimal generic;
	private BigDecimal specific;
	public BigDecimal getGeneric() {
		return generic;
	}
	public void setGeneric(BigDecimal generic) {
		this.generic = generic;
	}
	public BigDecimal getSpecific() {
		return specific;
	}
	public void setSpecific(BigDecimal specific) {
		this.specific = specific;
	}


	
}
