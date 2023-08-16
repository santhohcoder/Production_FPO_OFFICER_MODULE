package com.demo.fpo.bean;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MailclassCountryWise {
	@Id
	private String country;
	private BigDecimal totAssVal;
	private BigDecimal totAssValLtr;
	private BigDecimal totAssValEms;
	private BigDecimal totAssValPar;
	private BigDecimal totAssValEmp;
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public BigDecimal getTotAssVal() {
		return totAssVal;
	}
	public void setTotAssVal(BigDecimal totAssVal) {
		this.totAssVal = totAssVal;
	}
	public BigDecimal getTotAssValLtr() {
		return totAssValLtr;
	}
	public void setTotAssValLtr(BigDecimal totAssValLtr) {
		this.totAssValLtr = totAssValLtr;
	}
	public BigDecimal getTotAssValEms() {
		return totAssValEms;
	}
	public void setTotAssValEms(BigDecimal totAssValEms) {
		this.totAssValEms = totAssValEms;
	}
	public BigDecimal getTotAssValPar() {
		return totAssValPar;
	}
	public void setTotAssValPar(BigDecimal totAssValPar) {
		this.totAssValPar = totAssValPar;
	}
	public BigDecimal getTotAssValEmp() {
		return totAssValEmp;
	}
	public void setTotAssValEmp(BigDecimal totAssValEmp) {
		this.totAssValEmp = totAssValEmp;
	}
	
	
	
}
