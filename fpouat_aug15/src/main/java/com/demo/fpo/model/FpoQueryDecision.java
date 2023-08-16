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
@Table(name = "FPO_QRY_DECI")
public class FpoQueryDecision {
	
	@Id
	@GenericGenerator(name = "FPO_QRY_DECI_SEQ", strategy = "com.seq.gen.FPO_QRY_DECI_SEQIdGenerator")
	@GeneratedValue(generator = "FPO_QRY_DECI_SEQ")
	@Column(name = "ID")
	public Long id;

	@Column(name = "CIN_NO")
	private String CIN_NO;

	@Column(name = "ITEM_ID")
	private String ITEM_ID;

	@Column(name = "CUS_SITE")
	private String CUS_SITE;
	
	@Column(name = "DECI_CD")
	private Long DECI_CD;
	
	@Column(name = "QRY_NO")
	private Long QRY_NO;
	
	@Column(name = "QRY_TYPE")
	private String QRY_TYPE;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getDECI_CD() {
		return DECI_CD;
	}

	public void setDECI_CD(Long dECI_CD) {
		DECI_CD = dECI_CD;
	}

	public Long getQRY_NO() {
		return QRY_NO;
	}

	public void setQRY_NO(Long qRY_NO) {
		QRY_NO = qRY_NO;
	}

	public String getQRY_TYPE() {
		return QRY_TYPE;
	}

	public void setQRY_TYPE(String qRY_TYPE) {
		QRY_TYPE = qRY_TYPE;
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

	public Date getQRY_DT() {
		return QRY_DT;
	}

	public void setQRY_DT(Date qRY_DT) {
		QRY_DT = qRY_DT;
	}
	

	public String getCIN_NO() {
		return CIN_NO;
	}

	public void setCIN_NO(String cIN_NO) {
		CIN_NO = cIN_NO;
	}

	@Column(name = "OFF_ID")
	private String OFF_ID;
	
	@Column(name = "ROLE")
	private String ROLE;

	@Column(name = "QRY_DT")
	private Date QRY_DT = new Date();
	
	@Transient
	private String pen_cin;

	public String getPen_cin() {
		return pen_cin;
	}

	public String setPen_cin(String pen_cin) {
		return this.pen_cin = pen_cin;
	}
    
	@Transient
	private String Stage_name;

	public String getStage_name() {
		return Stage_name;
	}

	public void setStage_name(String stage_name) {
		Stage_name = stage_name;
	}

	@Transient 
	private String dep_comments;

	public String getDep_comments() {
		return dep_comments;
	}

	public void setDep_comments(String dep_comments) {
		this.dep_comments = dep_comments;
	}
	
}
