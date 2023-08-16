package com.demo.fpo.bean;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CtryWsPercentage {
	@Id
	private String country;
	private BigDecimal volumePercentage;
	private BigDecimal len;
	private BigDecimal totArt;
	private BigDecimal noOfValue;
	private String lastMonth; 

	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public BigDecimal getVolumePercentage() {
		return volumePercentage;
	}
	public void setVolumePercentage(BigDecimal volumePercentage) {
		this.volumePercentage = volumePercentage;
	}
	public BigDecimal getLen() {
		return len;
	}
	public void setLen(BigDecimal len) {
		this.len = len;
	}
	public BigDecimal getTotArt() {
		return totArt;
	}
	public void setTotArt(BigDecimal totArt) {
		this.totArt = totArt;
	}
	public BigDecimal getNoOfValue() {
		return noOfValue;
	}
	public void setNoOfValue(BigDecimal noOfValue) {
		this.noOfValue = noOfValue;
	}
	public String getLastMonth() {
		return lastMonth;
	}
	public void setLastMonth(String lastMonth) {
		this.lastMonth = lastMonth;
	}
	
}
