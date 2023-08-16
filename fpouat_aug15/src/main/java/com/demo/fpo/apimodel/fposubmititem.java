package com.demo.fpo.apimodel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
//import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
//import javax.xml.datatype.XMLGregorianCalendar;

import org.hibernate.annotations.GenericGenerator;



@Entity
@Table(name = "FPO_ITEM_DET")
public class fposubmititem {

	@Id
	
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fpoitemdetid")
//	@SequenceGenerator(allocationSize=1, name="fpoitemdetid", sequenceName="ITEMSUBMIT")

	@GenericGenerator(name = "ITEM_ID", strategy = "com.seq.gen.ITEM_SEQIdGenerator")
	@GeneratedValue(generator = "ITEM_ID")
	@Column(name = "ITEM_UNIQUE")
	private Long ITEM_UNIQUE;
	
	@Column(name = "JOB_NO") 
	private Long JOB_NO;     
	
	@Column(name = "ITEM_ID")
	private String ITEM_ID;
	
	@Column(name = "MODIFIED")
	private String MODIFIED;
	
	public String getMODIFIED() {
		return MODIFIED;
	}

	public void setMODIFIED(String mODIFIED) {
		MODIFIED = mODIFIED;
	}

	public String getMESG_TYPE_CD() {
		return MESG_TYPE_CD;
	}

	public void setMESG_TYPE_CD(String mESG_TYPE_CD) {
		MESG_TYPE_CD = mESG_TYPE_CD;
	}

	@Column(name = "MESG_TYPE_CD")
	private String MESG_TYPE_CD;

	public String getPOSTINGDT() {
		return POSTINGDT;
	}

	public void setPOSTINGDT(String pOSTINGDT) {
		POSTINGDT = pOSTINGDT;
	}

	@Column(name = "POSTINGDT")
	private String POSTINGDT;
	
	@Column(name = "CIN_NO") 
	private String CIN_NO;  
	
	@Column(name = "CIN_DT") 
	private java.sql.Date cindt;
	
	public Long getITEM_UNIQUE() {
		return ITEM_UNIQUE;
	}

	public java.sql.Date getCindt() {
		return cindt;
	}

	public void setCindt(java.sql.Date cindt) {
		this.cindt = cindt;
	}

	public void setITEM_UNIQUE(Long iTEM_UNIQUE) {
		ITEM_UNIQUE = iTEM_UNIQUE;
	}

	public String getCIN_NO() {
		return CIN_NO;
	}
	
	@Column(name = "BCD_NOTN")
	public String bcdnotn;
	
	@Column(name = "BCD_NSNO")
	public String bcdnsno;

	public String getBcdnotn() {
		return bcdnotn;
	}

	public void setBcdnotn(String bcdnotn) {
		this.bcdnotn = bcdnotn;
	}

	public String getBcdnsno() {
		return bcdnsno;
	}

	public void setBcdnsno(String bcdnsno) {
		this.bcdnsno = bcdnsno;
	}

	public void setCIN_NO(String cIN_NO) {
		CIN_NO = cIN_NO;
	}

	public long getNOU() {
		return NOU;
	}

	public void setNOU(long nOU) {
		NOU = nOU;
	}

	public BigDecimal getNETWT() {
		return NETWT;
	}

	public void setNETWT(BigDecimal nETWT) {
		NETWT = nETWT;
	}

	public BigDecimal getDECL_VAL() {
		return DECL_VAL;
	}

	public void setDECL_VAL(BigDecimal dECL_VAL) {
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

	public String getITEM_REVISEDDESC() {
		return ITEM_REVISEDDESC;
	}

	public void setITEM_REVISEDDESC(String iTEM_REVISEDDESC) {
		ITEM_REVISEDDESC = iTEM_REVISEDDESC;
	}

	public String getCTH_REVISED() {
		return CTH_REVISED;
	}

	public void setCTH_REVISED(String cTH_REVISED) {
		CTH_REVISED = cTH_REVISED;
	}

	public String getGEN_CTH() {
		return GEN_CTH;
	}

	public void setGEN_CTH(String gEN_CTH) {
		GEN_CTH = gEN_CTH;
	}

	public void setJOB_NO(Long jOB_NO) {
		JOB_NO = jOB_NO;
	}

	@Column(name = "CUS_SITE")
	private String CUS_SITE;
	
	public String getCUS_SITE() {
		return CUS_SITE;
	}
	
	public void setCUS_SITE(String cUS_SITE) {
		CUS_SITE = cUS_SITE;
	}

	@Column(name = "JOB_DT")
	private java.sql.Date sqlDate;

	public java.sql.Date getSqlDate() {
		return sqlDate;
	}

	public void setSqlDate(java.sql.Date sqlDate) {
		this.sqlDate = sqlDate;
	}
	
	@Column(name = "ITEM_NO")
	private long ITEM_NO;
	
	@Column(name = "NOU")
	private long NOU;

	@Column(name = "NETWT")
	private BigDecimal NETWT;

	@Column(name = "DECL_VAL")
	private BigDecimal DECL_VAL;
	
	@Column(name = "ASSESS_VAL")
	private BigDecimal ASSESS_VAL;
	
	@Column(name = "DUTY")
	private BigDecimal DUTY;
	
	public BigDecimal getDUTY() {
		return DUTY;
	}

	public void setDUTY(BigDecimal dUTY) {
		DUTY = dUTY;
	}

	public BigDecimal getBCD_AMT() {
		return BCD_AMT;
	}

	public void setBCD_AMT(BigDecimal bCD_AMT) {
		BCD_AMT = bCD_AMT;
	}

	public BigDecimal getSWS_AMT() {
		return SWS_AMT;
	}

	public void setSWS_AMT(BigDecimal sWS_AMT) {
		SWS_AMT = sWS_AMT;
	}

	public BigDecimal getIGST_AMT() {
		return IGST_AMT;
	}

	public void setIGST_AMT(BigDecimal iGST_AMT) {
		IGST_AMT = iGST_AMT;
	}

	@Column(name = "BCD_AMT")
	private BigDecimal BCD_AMT;
	
	@Column(name = "SW_AMT")
	private BigDecimal SWS_AMT;
	
	@Column(name = "IGST_AMT")
	private BigDecimal IGST_AMT;
	
	
	public BigDecimal getASSESS_VAL() {
		return ASSESS_VAL;
	}

	public void setASSESS_VAL(BigDecimal aSSESS_VAL) {
		ASSESS_VAL = aSSESS_VAL;
	}

	@Column(name = "CURRCD")
	private String CURRCD;
	
	@Column(name = "CURR_RATE")
	private String CURR_RATE;

	public String getCURR_RATE() {
		return CURR_RATE;
	}

	public void setCURR_RATE(String cURR_RATE) {
		CURR_RATE = cURR_RATE;
	}

	@Column(name = "ITEM_DESC")
	private String ITEM_DESC;

	@Column(name = "CTH")
	private String CTH;

	@Column(name = "ORIGCNTRYCD")
	private String ORIGCNTRYCD;
	
	@Column(name = "ITEM_REVISEDDESC")
	private String ITEM_REVISEDDESC;
	
	@Column(name = "CTH_REVISED")
	private String CTH_REVISED;
	
	public Float getBCD_AMT_FG() {
		return BCD_AMT_FG;
	}

	public void setBCD_AMT_FG(Float bCD_AMT_FG) {
		BCD_AMT_FG = bCD_AMT_FG;
	}

	public Float getIGST_AMT_FG() {
		return IGST_AMT_FG;
	}

	public void setIGST_AMT_FG(Float iGST_AMT_FG) {
		IGST_AMT_FG = iGST_AMT_FG;
	}

	public Float getSW_AMT_FG() {
		return SW_AMT_FG;
	}

	public void setSW_AMT_FG(Float sW_AMT_FG) {
		SW_AMT_FG = sW_AMT_FG;
	}

	@Column(name = "GEN_CTH")
	private String GEN_CTH;
	
	@Column(name = "BCD_RTA")
	private String BCD_RTA;
	
	@Column(name="BCD_AMT_FG")
	private Float BCD_AMT_FG;
	
	@Column(name="IGST_AMT_FG")
	private Float IGST_AMT_FG;
	
	@Column(name="SW_AMT_FG")
	private Float SW_AMT_FG;
	
	@Column(name="DUTY_FG")
	private Float DUTY_FG;
	
	public Float getDUTY_FG() {
		return DUTY_FG;
	}

	public void setDUTY_FG(Float dUTY_FG) {
		DUTY_FG = dUTY_FG;
	}

	public String getBCD_RTA() {
		return BCD_RTA;
	}

	public void setBCD_RTA(String bCD_RTA) {
		BCD_RTA = bCD_RTA;
	}

	public String getIGST_RTA() {
		return IGST_RTA;
	}

	public void setIGST_RTA(String iGST_RTA) {
		IGST_RTA = iGST_RTA;
	}

	public String getSWS_RTA() {
		return SWS_RTA;
	}

	public void setSWS_RTA(String sWS_RTA) {
		SWS_RTA = sWS_RTA;
	}

	@Column(name = "IGST_RTA")
	private String IGST_RTA;
	
	@Column(name = "SW_RTA")
	private String SWS_RTA;
	
	@Column(name = "UNIQUE_ID")
	private String UNIQUE_ID;

//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
	@Column(name = "ASSVAL_INSFRT")
	private BigDecimal ASSVAL_INSFRT;
	
	public BigDecimal getASSVAL_INSFRT() {
		return ASSVAL_INSFRT;
	}

	public void setASSVAL_INSFRT(BigDecimal aSSVAL_INSFRT) {
		ASSVAL_INSFRT = aSSVAL_INSFRT;
	}

	public String getUPD_CIF() {
		return UPD_CIF;
	}

	public void setUPD_CIF(String uPD_CIF) {
		UPD_CIF = uPD_CIF;
	}

	@Column(name = "UPD_CIF")
	private String UPD_CIF;

	public String getITEM_ID() {
		return ITEM_ID;
	}

	public void setITEM_ID(String iTEM_ID) {
		ITEM_ID = iTEM_ID;
	}

	

	public long getJOB_NO() {
		return JOB_NO;
	}

	public void setJOB_NO(long jOB_NO) {
		JOB_NO = jOB_NO;
	}

	
	public long getITEM_NO() {
		return ITEM_NO;
	}

	public void setITEM_NO(long iTEM_NO) {
		ITEM_NO = iTEM_NO;
	}

	public String getUNIQUE_ID() {
		return UNIQUE_ID;
	}

	public void setUNIQUE_ID(String uNIQUE_ID) {
		UNIQUE_ID = uNIQUE_ID;
	}

	
		// TODO Auto-generated method stub
		
}
