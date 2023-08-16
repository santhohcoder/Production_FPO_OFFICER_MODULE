package com.demo.fpo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "FPO_AMEND_ITEM_DET")
public class FPO_AMEND {
	
	@Id
	@GenericGenerator(name = "amend_seq", strategy = "com.seq.gen.FpoItemAmendIdGenerator")
	@GeneratedValue(generator = "amend_seq")
	@Column(name = "amend_seq")
	public Long amend_seq;

	@Column(name="CIN_NO")
	private String CIN_NO;
	
	public String getCIN_NO() {
		return CIN_NO;
	}

	public void setCIN_NO(String cIN_NO) {
		CIN_NO = cIN_NO;
	}

	@Column(name = "ITEM_ID")
	private String ITEM_ID;

	@Column(name = "CUS_SITE")
	private String CUS_SITE;

	@Column(name = "POSTINGDT")
	private String POSTINGDT;
	
	@Column(name = "CIN_DT")
	private Date cin_DT;
	
	public Date getCin_DT() {
		return cin_DT;
	}

	public void setCin_DT(Date cin_DT) {
		this.cin_DT = cin_DT;
	}

	@Column(name = "AMEND_SERIAL_NO")
	private Long AMEND_SERIAL_NO;
	
	@Column(name = "AMEND_DT")
	private Date AMEND_DT;
	
	@Column(name = "MESG_TYPE_CD")
	private String MESG_TYPE_CD;
	
	@Column(name = "ITEM_NO")
	private Long ITEM_NO;

	@Column(name = "NOU")
	private Long NOU;

	@Column(name = "NETWT")
	private Float NETWT;

	@Column(name = "DECL_VAL")
	private Float DECL_VAL;

	@Column(name = "CURRCD")
	private String CURRCD;

	@Column(name = "ITEM_DESC")
	private String ITEM_DESC;

	@Column(name = "CTH")
	private String CTH;
	
	@Column(name = "CTH_REVISED")
	private String CTH_REVISED;
	
	public String getCTH_REVISED() {
		return CTH_REVISED;
	}

	public void setCTH_REVISED(String cTH_REVISED) {
		CTH_REVISED = cTH_REVISED;
	}

	public String getITEM_REVISEDDESC() {
		return ITEM_REVISEDDESC;
	}

	public void setITEM_REVISEDDESC(String iTEM_REVISEDDESC) {
		ITEM_REVISEDDESC = iTEM_REVISEDDESC;
	}

	public String getGEN_CTH() {
		return GEN_CTH;
	}

	public void setGEN_CTH(String gEN_CTH) {
		GEN_CTH = gEN_CTH;
	}

	@Column(name = "ITEM_REVISEDDESC")
	private String ITEM_REVISEDDESC;

	@Column(name = "GEN_CTH")
	private String GEN_CTH;

	@Column(name = "ORIGCNTRYCD")
	private String ORIGCNTRYCD;
	
	@Column(name = "BCD_NOTN")
	private String BCD_NOTN;

	@Column(name = "BCD_NSNO")
	private String BCD_NSNO;

	@Column(name = "DUTY")
	private Float DUTY;

	@Column(name = "DUTY_FG")
	private Float DUTY_FG;
	
	@Column(name = "BCD_RTA")
	private Float BCD_RTA;
	
	@Column(name = "BCD_AMT")
	private Float BCD_AMT;
	
	@Column(name = "BCD_AMT_FG")
	private Float BCD_AMT_FG;
	
	@Column(name = "IGST_NOTN")
	private String IGST_NOTN;
	
	@Column(name = "IGST_NSNO")
	private String IGST_NSNO;
	
	@Column(name = "IGST_RTA")
	private Float IGST_RTA;
	
	@Column(name = "IGST_AMT")
	private Float IGST_AMT;
	
	@Column(name = "IGST_AMT_FG")
	private Float IGST_AMT_FG;
	
	@Column(name = "SW_NOTN")
	private String SW_NOTN;
	
	@Column(name = "SW_NSNO")
	private String SW_NSNO;
	
	@Column(name = "SW_AMT")
	private Float SW_AMT;
	
	@Column(name = "SW_RTA")
	private Float SW_RTA;
	
	@Column(name = "SW_AMT_FG")
	private Float SW_AMT_FG;
	
	@Column(name = "CURR_RATE")
	private Float CURR_RATE;
	
	@Column(name = "ASSESS_VAL")
	private Float ASSESS_VAL;
	
	@Column(name = "ASSVAL_INSFRT")
	private Float ASSVAL_INSFRT;
	
	public Float getASSESS_VAL() {
		return ASSESS_VAL;
	}

	public void setASSESS_VAL(Float aSSESS_VAL) {
		ASSESS_VAL = aSSESS_VAL;
	}

	public Float getASSVAL_INSFRT() {
		return ASSVAL_INSFRT;
	}

	public void setASSVAL_INSFRT(Float aSSVAL_INSFRT) {
		ASSVAL_INSFRT = aSSVAL_INSFRT;
	}

	@Column(name = "UNIQUE_ID")
	private String UNIQUE_ID;
	
	@Column(name = "MODIFIED")
	private String MODIFIED;
	
	@Column(name = "ITEM_UNIQUE")
	private Long ITEM_UNIQUE;
	
	@Column(name = "AMEND_FLAG")
	private String AMEND_FLAG;
	
	@Column(name = "ROLE")
	private String ROLE;
	
	@Column(name = "OFF_ID")
	private String OFF_ID;
	
	public String getMODIFIED() {
		return MODIFIED;
	}

	public void setMODIFIED(String mODIFIED) {
		MODIFIED = mODIFIED;
	}

	public Long getITEM_UNIQUE() {
		return ITEM_UNIQUE;
	}

	public void setITEM_UNIQUE(Long iTEM_UNIQUE) {
		ITEM_UNIQUE = iTEM_UNIQUE;
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

	/*
	 * public String getId() { return id; }
	 * 
	 * public void setId(String id) { this.id = id; }
	 */

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

	public Long getAMEND_SERIAL_NO() {
		return AMEND_SERIAL_NO;
	}

	public void setAMEND_SERIAL_NO(Long aMEND_SERIAL_NO) {
		AMEND_SERIAL_NO = aMEND_SERIAL_NO;
	}

	public Date getAMEND_DT() {
		return AMEND_DT;
	}

	public void setAMEND_DT(Date aMEND_DT) {
		AMEND_DT = aMEND_DT;
	}

	public String getMESG_TYPE_CD() {
		return MESG_TYPE_CD;
	}

	public void setMESG_TYPE_CD(String mESG_TYPE_CD) {
		MESG_TYPE_CD = mESG_TYPE_CD;
	}

	public Long getITEM_NO() {
		return ITEM_NO;
	}

	public void setITEM_NO(Long iTEM_NO) {
		ITEM_NO = iTEM_NO;
	}

	public Long getNOU() {
		return NOU;
	}

	public void setNOU(Long nOU) {
		NOU = nOU;
	}

	public Float getNETWT() {
		return NETWT;
	}

	public void setNETWT(Float nETWT) {
		NETWT = nETWT;
	}

	public Float getDECL_VAL() {
		return DECL_VAL;
	}

	public void setDECL_VAL(Float dECL_VAL) {
		DECL_VAL = dECL_VAL;
	}

	public String getCURRCD() {
		return CURRCD;
	}

	public void setCURRCD(String cURRCD) {
		CURRCD = cURRCD;
	}

	public String getITEM_DESC() {
		return ITEM_DESC;
	}

	public void setITEM_DESC(String iTEM_DESC) {
		ITEM_DESC = iTEM_DESC;
	}

	public String getCTH() {
		return CTH;
	}

	public void setCTH(String cTH) {
		CTH = cTH;
	}

	public String getORIGCNTRYCD() {
		return ORIGCNTRYCD;
	}

	public void setORIGCNTRYCD(String oRIGCNTRYCD) {
		ORIGCNTRYCD = oRIGCNTRYCD;
	}

	public String getBCD_NOTN() {
		return BCD_NOTN;
	}

	public void setBCD_NOTN(String bCD_NOTN) {
		BCD_NOTN = bCD_NOTN;
	}

	public String getBCD_NSNO() {
		return BCD_NSNO;
	}

	public void setBCD_NSNO(String bCD_NSNO) {
		BCD_NSNO = bCD_NSNO;
	}

	public Float getDUTY() {
		return DUTY;
	}

	public void setDUTY(Float dUTY) {
		DUTY = dUTY;
	}

	public Float getDUTY_FG() {
		return DUTY_FG;
	}

	public void setDUTY_FG(Float dUTY_FG) {
		DUTY_FG = dUTY_FG;
	}

	public Float getBCD_RTA() {
		return BCD_RTA;
	}

	public void setBCD_RTA(Float bCD_RTA) {
		BCD_RTA = bCD_RTA;
	}

	public Float getBCD_AMT() {
		return BCD_AMT;
	}

	public void setBCD_AMT(Float bCD_AMT) {
		BCD_AMT = bCD_AMT;
	}

	public Float getBCD_AMT_FG() {
		return BCD_AMT_FG;
	}

	public void setBCD_AMT_FG(Float bCD_AMT_FG) {
		BCD_AMT_FG = bCD_AMT_FG;
	}

	public String getIGST_NOTN() {
		return IGST_NOTN;
	}

	public void setIGST_NOTN(String iGST_NOTN) {
		IGST_NOTN = iGST_NOTN;
	}

	public String getIGST_NSNO() {
		return IGST_NSNO;
	}

	public void setIGST_NSNO(String iGST_NSNO) {
		IGST_NSNO = iGST_NSNO;
	}

	public Float getIGST_RTA() {
		return IGST_RTA;
	}

	public void setIGST_RTA(Float iGST_RTA) {
		IGST_RTA = iGST_RTA;
	}

	public Float getIGST_AMT() {
		return IGST_AMT;
	}

	public void setIGST_AMT(Float iGST_AMT) {
		IGST_AMT = iGST_AMT;
	}

	public Float getIGST_AMT_FG() {
		return IGST_AMT_FG;
	}

	public void setIGST_AMT_FG(Float iGST_AMT_FG) {
		IGST_AMT_FG = iGST_AMT_FG;
	}

	public String getSW_NOTN() {
		return SW_NOTN;
	}

	public void setSW_NOTN(String sW_NOTN) {
		SW_NOTN = sW_NOTN;
	}

	public String getSW_NSNO() {
		return SW_NSNO;
	}

	public void setSW_NSNO(String sW_NSNO) {
		SW_NSNO = sW_NSNO;
	}

	public Float getSW_AMT() {
		return SW_AMT;
	}

	public void setSW_AMT(Float sW_AMT) {
		SW_AMT = sW_AMT;
	}

	public Float getSW_RTA() {
		return SW_RTA;
	}

	public void setSW_RTA(Float sW_RTA) {
		SW_RTA = sW_RTA;
	}

	public Float getSW_AMT_FG() {
		return SW_AMT_FG;
	}

	public void setSW_AMT_FG(Float sW_AMT_FG) {
		SW_AMT_FG = sW_AMT_FG;
	}

	public Float getCURR_RATE() {
		return CURR_RATE;
	}

	public void setCURR_RATE(Float float1) {
		CURR_RATE = float1;
	}



	public String getUNIQUE_ID() {
		return UNIQUE_ID;
	}

	public void setUNIQUE_ID(String uNIQUE_ID) {
		UNIQUE_ID = uNIQUE_ID;
	}

}
