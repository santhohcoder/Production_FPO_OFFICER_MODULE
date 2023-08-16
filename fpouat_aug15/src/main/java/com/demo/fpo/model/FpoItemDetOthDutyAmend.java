package com.demo.fpo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "FPO_AMEND_ITEM_DET_OTHDUTY")
public class FpoItemDetOthDutyAmend {
	
	@Id
	@GenericGenerator(name = "OTHER_ITEM_UNIQUE_AMEND_SEQ", strategy = "com.seq.gen.OTHER_ITEM_UNIQUE_AMEND_SEQIdGenerator")
	@GeneratedValue(generator = "OTHER_ITEM_UNIQUE_AMEND_SEQ")
	@Column(name = "ID")
	public Long id;

	@Column(name = "CIN_DT")
	private Date CIN_DT;

	@Column(name = "CIN_NO")
	private String CIN_NO;

	@Column(name = "ITEM_ID")
	private String ITEM_ID;

	@Column(name = "CUS_SITE")
	private String CUS_SITE;

	@Column(name = "POSTINGDT")
	private String POSTINGDT;
	
	@Column(name = "ITEM_NO")
	private Long ITEM_NO;
	
	@Column(name = "AMEND_SERIAL_NO")
	private Long AMEND_SERAIL_NO;
	
	@Column(name = "AMEND_DT")
	private Date AMEND_DT;
	
	@Column(name = "AMEND_FLAG")
	private String AMEND_FLAG;
	
	@Column(name = "ROLE")
	private String ROLE;
	
	@Column(name = "OFF_ID")
	private String OFF_ID;

	@Column(name = "DUTY_CD")
	private Long DUTY_CD;
	
	@Column(name = "DUTY_RTA")
	private Float DUTY_RTA;
	
	@Column(name = "DUTY_AMT")
	private Float DUTY_AMT;
	
	@Column(name = "DUTY_FG")
	private Float DUTY_FG;
	
	@Column(name = "DUTY_NOTN")
	private String DUTY_NOTN;
	
	@Column(name = "DUTY_SLNO")
	private String DUTY_SLNO;
	
	@Column(name = "ASS_DT")
	private Date ASS_DT;
	
	@Column(name = "CTH")
	private String CTH;
	
	@Column(name = "DUTY_DESC")
	private String DUTY_DESC;
	
	public String getDUTY_DESC() {
		return DUTY_DESC;
	}

	public void setDUTY_DESC(String dUTY_DESC) {
		DUTY_DESC = dUTY_DESC;
	}

	public String getCTH() {
		return CTH;
	}

	public void setCTH(String cTH) {
		CTH = cTH;
	}

	public Date getASS_DT() {
		return ASS_DT;
	}

	public void setASS_DT(Date aSS_DT) {
		ASS_DT = aSS_DT;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCIN_DT() {
		return CIN_DT;
	}

	public void setCIN_DT(Date cIN_DT) {
		CIN_DT = cIN_DT;
	}

	public String getCIN_NO() {
		return CIN_NO;
	}

	public void setCIN_NO(String cIN_NO) {
		CIN_NO = cIN_NO;
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

	public Long getITEM_NO() {
		return ITEM_NO;
	}

	public void setITEM_NO(Long iTEM_NO) {
		ITEM_NO = iTEM_NO;
	}

	public Long getAMEND_SERAIL_NO() {
		return AMEND_SERAIL_NO;
	}

	public void setAMEND_SERAIL_NO(Long aMEND_SERAIL_NO) {
		AMEND_SERAIL_NO = aMEND_SERAIL_NO;
	}

	public Date getAMEND_DT() {
		return AMEND_DT;
	}

	public void setAMEND_DT(Date aMEND_DT) {
		AMEND_DT = aMEND_DT;
	}

	public String getAMEND_FLAG() {
		return AMEND_FLAG;
	}

	public void setAMEND_FLAG(String aMEND_FLAG) {
		AMEND_FLAG = aMEND_FLAG;
	}

	public String getROLE() {
		return ROLE;
	}

	public void setROLE(String rOLE) {
		ROLE = rOLE;
	}

	public String getOFF_ID() {
		return OFF_ID;
	}

	public void setOFF_ID(String oFF_ID) {
		OFF_ID = oFF_ID;
	}

	public Long getDUTY_CD() {
		return DUTY_CD;
	}

	public void setDUTY_CD(Long dUTY_CD) {
		DUTY_CD = dUTY_CD;
	}

	public Float getDUTY_RTA() {
		return DUTY_RTA;
	}

	public void setDUTY_RTA(Float dUTY_RTA) {
		DUTY_RTA = dUTY_RTA;
	}

	public Float getDUTY_AMT() {
		return DUTY_AMT;
	}

	public void setDUTY_AMT(Float dUTY_AMT) {
		DUTY_AMT = dUTY_AMT;
	}

	public Float getDUTY_FG() {
		return DUTY_FG;
	}

	public void setDUTY_FG(Float dUTY_FG) {
		DUTY_FG = dUTY_FG;
	}

	public String getDUTY_NOTN() {
		return DUTY_NOTN;
	}

	public void setDUTY_NOTN(String dUTY_NOTN) {
		DUTY_NOTN = dUTY_NOTN;
	}

	public String getDUTY_SLNO() {
		return DUTY_SLNO;
	}

	public void setDUTY_SLNO(String dUTY_SLNO) {
		DUTY_SLNO = dUTY_SLNO;
	}
	
}
