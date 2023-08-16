package com.demo.fpo.bean;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CtryWsMidPercentage {
	@Id
	private BigDecimal letters;
	private BigDecimal ltrVal;
	private BigDecimal ems;
	private BigDecimal emsVal;
	private BigDecimal parcels;
	private BigDecimal parVal;
	private BigDecimal empRecep;
	private BigDecimal empRecepVal;
	public BigDecimal getLtrVal() {
		return ltrVal;
	}
	public void setLtrVal(BigDecimal ltrVal) {
		this.ltrVal = ltrVal;
	}
	public BigDecimal getEmsVal() {
		return emsVal;
	}
	public void setEmsVal(BigDecimal emsVal) {
		this.emsVal = emsVal;
	}
	public BigDecimal getParVal() {
		return parVal;
	}
	public void setParVal(BigDecimal parVal) {
		this.parVal = parVal;
	}
	public BigDecimal getEmpRecepVal() {
		return empRecepVal;
	}
	public void setEmpRecepVal(BigDecimal empRecepVal) {
		this.empRecepVal = empRecepVal;
	}
	public BigDecimal getLetters() {
		return letters;
	}
	public void setLetters(BigDecimal letters) {
		this.letters = letters;
	}
	public BigDecimal getEms() {
		return ems;
	}
	public void setEms(BigDecimal ems) {
		this.ems = ems;
	}
	public BigDecimal getParcels() {
		return parcels;
	}
	public void setParcels(BigDecimal parcels) {
		this.parcels = parcels;
	}
	public BigDecimal getEmpRecep() {
		return empRecep;
	}
	public void setEmpRecep(BigDecimal empRecep) {
		this.empRecep = empRecep;
	}
	
	
}
