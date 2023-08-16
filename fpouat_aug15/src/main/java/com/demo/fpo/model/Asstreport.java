package com.demo.fpo.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "FPO_ITEM_DET")
public class Asstreport {
	@Id
	@Column(name = "CIN_NO", columnDefinition="serial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	
	@Column(name = "ITEM_ID")
	private String ITEM_ID;
	
	@Column(name = "CUS_SITE")
	private String CUS_SITE;
	
	@Column(name = "POSTINGDT")
	private String POSTINGDT;
	
	@Column(name = "MESG_TYPE_CD")
	private String MESG_TYPE_CD;
	
	@Column(name = "JOB_NO")
	private Long JOB_NO;
	
	@Column(name = "JOB_DT")
	private Date JOB_DT;
	
	@Column(name = "ITEM_NO")
	private Long ITEM_NO;
	
	@Column(name = "NOU")
	private Long NOU;
	
	@Column(name = "NETWT")
	private float NETWT;
	
	@Column(name = "DECL_VAL")
	private Long DECL_VAL;
	
	@Column(name = "CURRCD")
	private String CURRCD;
	
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
	
	@Column(name = "DUTY")
	private Long DUTY;
	
	@Column(name = "DUTY_FG")
	private Long DUTY_FG;
	
	@Column(name = "BCD_NOTN")
	private String BCD_NOTN;
	
	@Column(name = "BCD_NSNO")
	private String BCD_NSNO;
	
	@Column(name = "BCD_RTA")
	private Long BCD_RTA;
	
	@Column(name = "BCD_AMT")
	private Long BCD_AMT;
	
	@Column(name = "BCD_AMT_FG")
	private Long BCD_AMT_FG;


	@Column(name = "IGST_NOTN")
	private String IGST_NOTN;
	
	@Column(name = "IGST_NSNO")
	private String IGST_NSNO;
	
	@Column(name = "IGST_RTA")
	private Long IGST_RTA;
	
	@Column(name = "IGST_AMT")
	private Long IGST_AMT;
	
	@Column(name = "IGST_AMT_FG")
	private Long IGST_AMT_FG;
	
	@Column(name = "SW_NOTN")
	private String SW_NOTN;
	
	@Column(name = "SW_NSNO")
	private String SW_NSNO;
	
	@Column(name = "SW_RTA")
	private Long SW_RTA;
	
	@Column(name = "SW_AMT")
	private Long SW_AMT;
	
	@Column(name = "SW_AMT_FG")
	private Long SW_AMT_FG;
	
	@Column(name = "UNIQUE_ID")
	private String UNIQUE_ID;
	
	@Column(name = "GEN_CTH")
	private String GEN_CTH;
	
	@Column(name = "RATE")
	private Long RATE;
	
	@Column(name = "FREIGHT_VAL")
	private Long FREIGHT_VAL;
	
	@Column(name = "INSURED_VAL")
	private Long INSURED_VAL;

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getPOSTINGDT() {
		return POSTINGDT;
	}

	public void setPOSTINGDT(String pOSTINGDT) {
		POSTINGDT = pOSTINGDT;
	}

	public String getMESG_TYPE_CD() {
		return MESG_TYPE_CD;
	}

	public void setMESG_TYPE_CD(String mESG_TYPE_CD) {
		MESG_TYPE_CD = mESG_TYPE_CD;
	}

	public Long getJOB_NO() {
		return JOB_NO;
	}

	public void setJOB_NO(Long jOB_NO) {
		JOB_NO = jOB_NO;
	}

	public Date getJOB_DT() {
		return JOB_DT;
	}

	public void setJOB_DT(Date jOB_DT) {
		JOB_DT = jOB_DT;
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

	public float getNETWT() {
		return NETWT;
	}

	public void setNETWT(float nETWT) {
		NETWT = nETWT;
	}

	public Long getDECL_VAL() {
		return DECL_VAL;
	}

	public void setDECL_VAL(Long dECL_VAL) {
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

	public Long getDUTY() {
		return DUTY;
	}

	public void setDUTY(Long dUTY) {
		DUTY = dUTY;
	}

	public Long getDUTY_FG() {
		return DUTY_FG;
	}

	public void setDUTY_FG(Long dUTY_FG) {
		DUTY_FG = dUTY_FG;
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

	public Long getBCD_RTA() {
		return BCD_RTA;
	}

	public void setBCD_RTA(Long bCD_RTA) {
		BCD_RTA = bCD_RTA;
	}

	public Long getBCD_AMT() {
		return BCD_AMT;
	}

	public void setBCD_AMT(Long bCD_AMT) {
		BCD_AMT = bCD_AMT;
	}

	public Long getBCD_AMT_FG() {
		return BCD_AMT_FG;
	}

	public void setBCD_AMT_FG(Long bCD_AMT_FG) {
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

	public Long getIGST_RTA() {
		return IGST_RTA;
	}

	public void setIGST_RTA(Long iGST_RTA) {
		IGST_RTA = iGST_RTA;
	}

	public Long getIGST_AMT() {
		return IGST_AMT;
	}

	public void setIGST_AMT(Long iGST_AMT) {
		IGST_AMT = iGST_AMT;
	}

	public Long getIGST_AMT_FG() {
		return IGST_AMT_FG;
	}

	public void setIGST_AMT_FG(Long iGST_AMT_FG) {
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

	public Long getSW_RTA() {
		return SW_RTA;
	}

	public void setSW_RTA(Long sW_RTA) {
		SW_RTA = sW_RTA;
	}

	public Long getSW_AMT() {
		return SW_AMT;
	}

	public void setSW_AMT(Long sW_AMT) {
		SW_AMT = sW_AMT;
	}

	public Long getSW_AMT_FG() {
		return SW_AMT_FG;
	}

	public void setSW_AMT_FG(Long sW_AMT_FG) {
		SW_AMT_FG = sW_AMT_FG;
	}

	public String getUNIQUE_ID() {
		return UNIQUE_ID;
	}

	public void setUNIQUE_ID(String uNIQUE_ID) {
		UNIQUE_ID = uNIQUE_ID;
	}

	public String getGEN_CTH() {
		return GEN_CTH;
	}

	public void setGEN_CTH(String gEN_CTH) {
		GEN_CTH = gEN_CTH;
	}

	public Long getRATE() {
		return RATE;
	}

	public void setRATE(Long rATE) {
		RATE = rATE;
	}

	public Long getFREIGHT_VAL() {
		return FREIGHT_VAL;
	}

	public void setFREIGHT_VAL(Long fREIGHT_VAL) {
		FREIGHT_VAL = fREIGHT_VAL;
	}

	public Long getINSURED_VAL() {
		return INSURED_VAL;
	}

	public void setINSURED_VAL(Long iNSURED_VAL) {
		INSURED_VAL = iNSURED_VAL;
	}

	
	
	
}
