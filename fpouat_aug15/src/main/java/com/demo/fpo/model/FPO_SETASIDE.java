package com.demo.fpo.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "FPO_SETASIDE")
public class FPO_SETASIDE {


	@Id
	@GenericGenerator(name = "ORDER_ID", strategy = "com.seq.gen.ORDER_SEQIdGenerator")
	@GeneratedValue(generator = "ORDER_ID")
	@Column(name = "ID")
	public Long id;
	
	@Column(name = "CIN_NO")
	private String cin_No;

	@Column(name = "CUS_SITE")
	private String CUS_SITE;
	
	@Column(name = "ITEM_ID")
	private String ITEM_ID;


	public String getCUS_SITE() {
		return CUS_SITE;
	}

	public void setCUS_SITE(String cUS_SITE) {
		CUS_SITE = cUS_SITE;
	}

	public String getCin_No() {
		return cin_No;
	}

	public void setCin_No(String cin_No) {
		this.cin_No = cin_No;
	}

	public String getITEM_ID() {
		return ITEM_ID;
	}

	public void setITEM_ID(String iTEM_ID) {
		ITEM_ID = iTEM_ID;
	}

	public String getReas() {
		return reas;
	}

	public void setReas(String reas) {
		this.reas = reas;
	}

	public String getOFF_ID() {
		return OFF_ID;
	}

	public void setOFF_ID(String oFF_ID) {
		OFF_ID = oFF_ID;
	}

	public String getROLE() {
		return ROLE;
	}

	public void setROLE(String rOLE) {
		ROLE = rOLE;
	}


	public Date getREVOKE_DATE() {
		return REVOKE_DATE;
	}

	public void setREVOKE_DATE(Date rEVOKE_DATE) {
		REVOKE_DATE = rEVOKE_DATE;
	}

	@Column(name = "REASONS")
	private String reas;

	@Column(name = "OFF_ID")
	private String OFF_ID;

	@Column(name = "ROLE")
	private String ROLE;

	@Column(name = "ASIDE_DT")
	private Date ASIDE_DATE;

	
	public Date getASIDE_DATE() {
		return ASIDE_DATE;
	}

	public void setASIDE_DATE(Date aSIDE_DATE) {
		ASIDE_DATE = aSIDE_DATE;
	}

	@Column(name = "REVO_DT")
	private Date REVOKE_DATE;	
	
}
