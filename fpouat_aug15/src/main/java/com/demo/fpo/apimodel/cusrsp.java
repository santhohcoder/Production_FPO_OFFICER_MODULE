package com.demo.fpo.apimodel;

import java.math.BigDecimal;
//import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

//import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@Table(name = "C_CUSRSP")
public class cusrsp {
	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO, generator = "idsequence3")
//	@SequenceGenerator(allocationSize=1, name="idsequence3", sequenceName="cusrsp_id")
	@GenericGenerator(name = "C_CUSRSP_ID", strategy = "com.seq.gen.C_CUSRSP_IdGenerator")
	@GeneratedValue(generator = "C_CUSRSP_ID")
	@Column(name = "cusrsp_id")
	private Long cusrsp_id;
	
//	@Id
//	@GenericGenerator(name = "idsequence5", strategy = "com.fpo.idgen.FpoIdGenerator")
//	@GeneratedValue(generator = "idsequence5")  
//	@Column(name = "CUSRSP_ID")
//	private long CUSRSP_ID;

	@Column(name = "CIN_NO")
	private String CIN_NO;
	
	@Column(name = "CUS_SITE")
	private String CUS_SITE;
	
	public String getCUS_SITE() {
		return CUS_SITE;
	}
	
	public void setCUS_SITE(String cUS_SITE) {
		CUS_SITE = cUS_SITE;
	}

	public String getMESG_TYPE_CD() {
		return MESG_TYPE_CD;
	}

	public void setMESG_TYPE_CD(String mESG_TYPE_CD) {
		MESG_TYPE_CD = mESG_TYPE_CD;
	}

	@Column(name = "MESG_TYPE_CD")
	private String MESG_TYPE_CD;
	@Column(name = "ITEM_ID")
	private String ITEM_ID;

	@Column(name = "POSTINGDT")
	private String POSTINGDT;

	@Column(name = "ENTITY_STATE")
	private Long ENTITY_STATE;

	@Column(name = "DESTPOST_ORGCD")
	private String DESTPOST_ORGCD;

	@Column(name = "MAIL_CLASS_CD")
	private String MAIL_CLASS_CD;

	@Column(name = "ORIGPOST_ORGCD")
	private String ORIGPOST_ORGCD;

	@Column(name = "MAIL_PID")
	private String MAIL_PID;

	@Column(name = "NATURE_TYPE_CD")
	private String NATURE_TYPE_CD;

	@Column(name = "MAIL_STATE_CD")
	private String MAIL_STATE_CD;

	@Column(name = "CUST_ORG_CD")
	private String CUST_ORG_CD;

	@Column(name = "POST_ORG_CD")
	private String POST_ORG_CD;

	@Column(name = "CLEARANCE_DT")
	private String CLEARANCE_DT;

	@Column(name = "DECI_CD")
	private Long DECI_CD;
	
	@Column(name = "SENT_DT")
	private java.sql.Date sqlDate;
	
	public Long getCusrsp_id() {
		return cusrsp_id;
	}

	public void setCusrsp_id(Long cusrsp_id) {
		this.cusrsp_id = cusrsp_id;
	}

	public java.sql.Date getSqlDate() {
		return sqlDate;
	}

	public void setSqlDate(java.sql.Date sqlDate) {
		this.sqlDate = sqlDate;
	}

		public Long getDECI_CD() {
		return DECI_CD;
	}

	public void setDECI_CD(Long dECI_CD) {
		DECI_CD = dECI_CD;
	}

	public Long getDECI_REAS_CD() {
		return DECI_REAS_CD;
	}

	public void setDECI_REAS_CD(Long dECI_REAS_CD) {
		DECI_REAS_CD = dECI_REAS_CD;
	}

	public String getTOT_FEE_CURRCD() {
		return TOT_FEE_CURRCD;
	}

	public void setTOT_FEE_CURRCD(String tOT_FEE_CURRCD) {
		TOT_FEE_CURRCD = tOT_FEE_CURRCD;
	}

	@Column(name = "DECI_REAS_CD")
	private Long DECI_REAS_CD;

	@Column(name = "DECI_REAS_NM")
	private String DECI_REAS_NM;

	@Column(name = "DUTIABLE")
	private String DUTIABLE;

	@Column(name = "TOT_DUTY")
	private BigDecimal TOT_DUTY;

	@Column(name = "TOT_FEE")
	private BigDecimal TOT_FEE;

	@Column(name = "TOT_FEE_CURRCD")
	private String TOT_FEE_CURRCD;

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

	public String getPOSTINGDT() {
		return POSTINGDT;
	}

	public void setPOSTINGDT(String pOSTINGDT) {
		POSTINGDT = pOSTINGDT;
	}

	public Long getENTITY_STATE() {
		return ENTITY_STATE;
	}

	public void setENTITY_STATE(Long eNTITY_STATE) {
		ENTITY_STATE = eNTITY_STATE;
	}

	public String getDESTPOST_ORGCD() {
		return DESTPOST_ORGCD;
	}

	public void setDESTPOST_ORGCD(String dESTPOST_ORGCD) {
		DESTPOST_ORGCD = dESTPOST_ORGCD;
	}

	public String getMAIL_CLASS_CD() {
		return MAIL_CLASS_CD;
	}

	public void setMAIL_CLASS_CD(String mAIL_CLASS_CD) {
		MAIL_CLASS_CD = mAIL_CLASS_CD;
	}

	public String getORIGPOST_ORGCD() {
		return ORIGPOST_ORGCD;
	}

	public void setORIGPOST_ORGCD(String oRIGPOST_ORGCD) {
		ORIGPOST_ORGCD = oRIGPOST_ORGCD;
	}

	public String getMAIL_PID() {
		return MAIL_PID;
	}

	public void setMAIL_PID(String mAIL_PID) {
		MAIL_PID = mAIL_PID;
	}

	public String getNATURE_TYPE_CD() {
		return NATURE_TYPE_CD;
	}

	public void setNATURE_TYPE_CD(String nATURE_TYPE_CD) {
		NATURE_TYPE_CD = nATURE_TYPE_CD;
	}

	public String getMAIL_STATE_CD() {
		return MAIL_STATE_CD;
	}

	public void setMAIL_STATE_CD(String mAIL_STATE_CD) {
		MAIL_STATE_CD = mAIL_STATE_CD;
	}

	public String getCUST_ORG_CD() {
		return CUST_ORG_CD;
	}

	public void setCUST_ORG_CD(String cUST_ORG_CD) {
		CUST_ORG_CD = cUST_ORG_CD;
	}

	public String getPOST_ORG_CD() {
		return POST_ORG_CD;
	}

	public void setPOST_ORG_CD(String pOST_ORG_CD) {
		POST_ORG_CD = pOST_ORG_CD;
	}

	public String getCLEARANCE_DT() {
		return CLEARANCE_DT;
	}

	public void setCLEARANCE_DT(String cLEARANCE_DT) {
		CLEARANCE_DT = cLEARANCE_DT;
	}

	public String getDECI_REAS_NM() {
		return DECI_REAS_NM;
	}

	public void setDECI_REAS_NM(String dECI_REAS_NM) {
		DECI_REAS_NM = dECI_REAS_NM;
	}

	public String getDUTIABLE() {
		return DUTIABLE;
	}

	public void setDUTIABLE(String dUTIABLE) {
		DUTIABLE = dUTIABLE;
	}

	public BigDecimal getTOT_DUTY() {
		return TOT_DUTY;
	}

	public void setTOT_DUTY(BigDecimal tOT_DUTY) {
		TOT_DUTY = tOT_DUTY;
	}

	public BigDecimal getTOT_FEE() {
		return TOT_FEE;
	}

	public void setTOT_FEE(BigDecimal tOT_FEE) {
		TOT_FEE = tOT_FEE;
	}

	

}
