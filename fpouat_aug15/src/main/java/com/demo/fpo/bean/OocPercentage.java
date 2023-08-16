package com.demo.fpo.bean;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OocPercentage {
	@Id
	private BigDecimal withDutyArticles;
	//private BigDecimal noDutyArticles;
	private BigDecimal noArticles;
	private BigDecimal dutyPayable;
	private BigDecimal withDutyPercentage;
	//private BigDecimal noDutyPercentage;
	private String lastMonth;
	private BigDecimal noDutyConcession;
	private BigDecimal noDutyConcessionPercentage;
	private BigDecimal totAmount;
	
	public BigDecimal getWithDutyArticles() {
		return withDutyArticles;
	}
	public void setWithDutyArticles(BigDecimal withDutyArticles) {
		this.withDutyArticles = withDutyArticles;
	}
	public BigDecimal getNoArticles() {
		return noArticles;
	}
	public void setNoArticles(BigDecimal noArticles) {
		this.noArticles = noArticles;
	}
	public BigDecimal getDutyPayable() {
		return dutyPayable;
	}
	public void setDutyPayable(BigDecimal dutyPayable) {
		this.dutyPayable = dutyPayable;
	}
	public BigDecimal getWithDutyPercentage() {
		return withDutyPercentage;
	}
	public void setWithDutyPercentage(BigDecimal withDutyPercentage) {
		this.withDutyPercentage = withDutyPercentage;
	}
	public String getLastMonth() {
		return lastMonth;
	}
	public void setLastMonth(String lastMonth) {
		this.lastMonth = lastMonth;
	}
	public BigDecimal getNoDutyConcession() {
		return noDutyConcession;
	}
	public void setNoDutyConcession(BigDecimal noDutyConcession) {
		this.noDutyConcession = noDutyConcession;
	}
	public BigDecimal getNoDutyConcessionPercentage() {
		return noDutyConcessionPercentage;
	}
	public void setNoDutyConcessionPercentage(BigDecimal noDutyConcessionPercentage) {
		this.noDutyConcessionPercentage = noDutyConcessionPercentage;
	}
	public BigDecimal getTotAmount() {
		return totAmount;
	}
	public void setTotAmount(BigDecimal totAmount) {
		this.totAmount = totAmount;
	}

	
}
