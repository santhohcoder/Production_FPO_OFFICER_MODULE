package com.demo.fpo.bean;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ActiveuserLSM {
	
	@Id
	private String role;
	private BigDecimal counts;
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public BigDecimal getCounts() {
		return counts;
	}
	public void setCounts(BigDecimal counts) {
		this.counts = counts;
	}
	


	
}
