package com.demo.fpo.bean;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OOCDuty {
	
	@Id
	private BigDecimal totDuty;
	private BigDecimal totDutyFg;
	private BigDecimal totAmtPayable;
	private BigDecimal finePenal;
	public BigDecimal getTotDuty() {
		return totDuty;
	}
	public void setTotDuty(BigDecimal totDuty) {
		this.totDuty = totDuty;
	}
	public BigDecimal getTotDutyFg() {
		return totDutyFg;
	}
	public void setTotDutyFg(BigDecimal totDutyFg) {
		this.totDutyFg = totDutyFg;
	}
	public BigDecimal getTotAmtPayable() {
		return totAmtPayable;
	}
	public void setTotAmtPayable(BigDecimal totAmtPayable) {
		this.totAmtPayable = totAmtPayable;
	}
	public BigDecimal getFinePenal() {
		return finePenal;
	}
	public void setFinePenal(BigDecimal finePenal) {
		this.finePenal = finePenal;
	}
}
