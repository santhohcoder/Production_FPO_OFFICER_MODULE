package com.demo.fpo.bean;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.transaction.Transactional;

@Entity
public class TotalCountMailClass {

	@Id
	private Long totcoultr;
	private Long totcouems;
	private Long totcoupar;
	private Long totcouemp;
	public Long getTotcoultr() {
		return totcoultr;
	}
	public void setTotcoultr(Long totcoultr) {
		this.totcoultr = totcoultr;
	}
	public Long getTotcouems() {
		return totcouems;
	}
	public void setTotcouems(Long totcouems) {
		this.totcouems = totcouems;
	}
	public Long getTotcoupar() {
		return totcoupar;
	}
	public void setTotcoupar(Long totcoupar) {
		this.totcoupar = totcoupar;
	}
	public Long getTotcouemp() {
		return totcouemp;
	}
	public void setTotcouemp(Long totcouemp) {
		this.totcouemp = totcouemp;
	}
	
}
