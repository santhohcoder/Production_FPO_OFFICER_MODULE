package com.demo.fpo.apimodel;

import java.math.BigDecimal;

//import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "FPO_ITEM_DET_OTHDUTY")
public class FpoItemDetothduty {
	@Id
	@Column(name = "CIN_NO")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "FPO_ITEM_DET_OTHDUTY_ID", strategy = "com.seq.gen.FPO_ITEM_DET_OTHDUTYIdGenerator")
	@GeneratedValue(generator = "FPO_ITEM_DET_OTHDUTY_ID")
	private String id;
	
	@Column(name = "CIN_DT") 
	private java.sql.Date CIN_DT;

	@Column(name = "ITEM_ID")
	private String ITEM_ID;

	@Column(name = "CUS_SITE")
	private String CUS_SITE;

	@Column(name = "POSTINGDT")
	private String POSTINGDT;

	@Column(name = "ITEM_NO")
	private Long ITEM_NO;

	@Column(name = "DUTY_CD")
	private Long DUTY_CD;

	@Column(name = "DUTY_NOTN")
	private String DUTY_NOTN;

	@Column(name = "DUTY_SLNO")
	private String DUTY_SLNO;
	
	@Column(name = "DUTY_RTA")
	private Long DUTY_RTA;

	@Column(name = "DUTY_AMT")
	private BigDecimal DUTY_AMT;

	@Column(name = "DUTY_FG")
	private Long DUTY_FG;

	@Column(name = "AMEND_SERIAL_NO")
	private Long AMEND_SERIAL_NO;
	
	@Column(name = "AMEND_DT") 
	private java.sql.Date AMEND_DT;

	@Column(name = "AMEND_FLAG")
	private String AMEND_FLAG ;

	@Column(name = "ROLE")
	private String ROLE;

	@Column(name = "OFF_ID")
	private String OFF_ID;
	
	@Column(name = "ASS_DT") 
	private java.sql.Date assdt;

	@Column(name = "RATE")
	private Long RT;

	@Column(name = "CTH")
	private String CTH;

	@Column(name = "ID")
	private Long ID;

	@Column(name = "DUTY_DESC")
	private String DUTY_DESC;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public java.sql.Date getCIN_DT() {
		return CIN_DT;
	}

	public void setCIN_DT(java.sql.Date cIN_DT) {
		CIN_DT = cIN_DT;
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

	public Long getDUTY_CD() {
		return DUTY_CD;
	}

	public void setDUTY_CD(Long dUTY_CD) {
		DUTY_CD = dUTY_CD;
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

	public Long getDUTY_RTA() {
		return DUTY_RTA;
	}

	public void setDUTY_RTA(Long dUTY_RTA) {
		DUTY_RTA = dUTY_RTA;
	}

	public BigDecimal getDUTY_AMT() {
		return DUTY_AMT;
	}

	public void setDUTY_AMT(BigDecimal dUTY_AMT) {
		DUTY_AMT = dUTY_AMT;
	}

	public Long getDUTY_FG() {
		return DUTY_FG;
	}

	public void setDUTY_FG(Long dUTY_FG) {
		DUTY_FG = dUTY_FG;
	}

	public Long getAMEND_SERIAL_NO() {
		return AMEND_SERIAL_NO;
	}

	public void setAMEND_SERIAL_NO(Long aMEND_SERIAL_NO) {
		AMEND_SERIAL_NO = aMEND_SERIAL_NO;
	}

	public java.sql.Date getAMEND_DT() {
		return AMEND_DT;
	}

	public void setAMEND_DT(java.sql.Date aMEND_DT) {
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

	public java.sql.Date getAssdt() {
		return assdt;
	}

	public void setAssdt(java.sql.Date assdt) {
		this.assdt = assdt;
	}

	public Long getRT() {
		return RT;
	}

	public void setRT(Long rT) {
		RT = rT;
	}

	public String getCTH() {
		return CTH;
	}

	public void setCTH(String cTH) {
		CTH = cTH;
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public String getDUTY_DESC() {
		return DUTY_DESC;
	}

	public void setDUTY_DESC(String dUTY_DESC) {
		DUTY_DESC = dUTY_DESC;
	}

	
}