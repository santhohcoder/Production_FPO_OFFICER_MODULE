package com.demo.fpo.bean;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CtryWsAssCountPercentage {
	@Id
	private String country;
	private BigDecimal volumePercentage;
	private BigDecimal len;
	private BigDecimal totArt;
	private BigDecimal noOfValue;
	private String lastMonth;
	private BigDecimal totalArtCount;
	public BigDecimal getNoOfValue() {
		return noOfValue;
	}
	public void setNoOfValue(BigDecimal noOfValue) {
		this.noOfValue = noOfValue;
	}
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
	public String getLastMonth() {
		return lastMonth;
	}
	public void setLastMonth(String lastMonth) {
		this.lastMonth = lastMonth;
	}
	public BigDecimal getTotalArtCount() {
		return totalArtCount;
	}
	public void setTotalArtCount(BigDecimal totalArtCount) {
		this.totalArtCount = totalArtCount;
	}
	
}
