package com.demo.fpo.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "FPO_AMEND_PENAL")
public class FpoPenalAmend {
	
	
	@Id
	@GenericGenerator(name = "FPO_PENAL_AMEND_SEQ", strategy = "com.seq.gen.FPO_PENAL_AMEND_SEQIdGenerator")
	@GeneratedValue(generator = "FPO_PENAL_AMEND_SEQ")
	@Column(name = "ID")
	public Long id;
	
	@Column(name = "CIN_NO")
	private String cinNo;
	
	@Column(name = "CIN_DT")
	private Date cinDT;
	
	@Column(name = "ITEM_ID")
	private String ITEM_ID;

	@Column(name = "CUS_SITE")
	private String CUS_SITE;
	
	@Column(name = "POSTINGDT")
	private String POSTINGDT;
	
	@Column(name = "PENAL_AMT")
	public Float penalAmt;

	@Column(name = "PENAL_US")
	private String penalUs;
	
	@Column(name = "OFF_ID")
	private String offId;

	@Column(name = "ROLE")
	private String role;
	
	@Column(name = "ASS_DT")
	private Date ass_DT; 
	
	
	@Column(name = "AMEND_DT")
	private Date AMEND_DT; 
	
	
	@Column(name = "AMEND_FLAG")
	private String AMEND_FLAG;

	public Date getAMEND_DT() {
		return AMEND_DT;
	}

	public void setAMEND_DT(Date aMEND_DT) {
		AMEND_DT = aMEND_DT;
	}

	public Date getAss_DT() {
		return ass_DT;
	}

	public void setAss_DT(Date ass_DT) {
		this.ass_DT = ass_DT;
	}

	public String getAMEND_FLAG() {
		return AMEND_FLAG;
	}

	public void setAMEND_FLAG(String aMEND_FLAG) {
		AMEND_FLAG = aMEND_FLAG;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCinNo() {
		return cinNo;
	}

	public void setCinNo(String cinNo) {
		this.cinNo = cinNo;
	}

	public Date getCinDT() {
		return cinDT;
	}

	public void setCinDT(Date cinDT) {
		this.cinDT = cinDT;
	}

	public String getITEM_ID() {
		return ITEM_ID;
	}

	public void setITEM_ID(String iTEM_ID) {
		ITEM_ID = iTEM_ID;
	}

	public String getCUS_SITE() {
		return CUS_SITE;
	}

	public void setCUS_SITE(String cUS_SITE) {
		CUS_SITE = cUS_SITE;
	}

	public String getPOSTINGDT() {
		return POSTINGDT;
	}

	public void setPOSTINGDT(String pOSTINGDT) {
		POSTINGDT = pOSTINGDT;
	}

	public Float getPenalAmt() {
		return penalAmt;
	}

	public void setPenalAmt(Float penalAmt) {
		this.penalAmt = penalAmt;
	}

	public String getPenalUs() {
		return penalUs;
	}

	public void setPenalUs(String penalUs) {
		this.penalUs = penalUs;
	}

	
	public String getOffId() {
		return offId;
	}

	public void setOffId(String offId) {
		this.offId = offId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
}
