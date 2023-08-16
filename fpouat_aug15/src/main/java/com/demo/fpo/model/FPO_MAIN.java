package com.demo.fpo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "FPO_MAIN")
public class FPO_MAIN {

	@Id
	@Column(name = "CIN_NO", columnDefinition = "serial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	public Date getCIN_DT() {
		return CIN_DT;
	}

	public void setCIN_DT(Date cIN_DT) {
		CIN_DT = cIN_DT;
	}

	@Column(name = "ITEM_ID")
	private String ITEM_ID;
	
	
	@Column(name = "ARR_SCAN")
	private String ARR_SCAN;
	
	@Column(name = "ARR_FPO")
	private String ARR_FPO;
	
	@Column(name = "ARR_INDIA")
	private String ARR_INDIA;

	public String getARR_FPO() {
		return ARR_FPO;
	}

	public void setARR_FPO(String aRR_FPO) {
		ARR_FPO = aRR_FPO;
	}

	public String getARR_INDIA() {
		return ARR_INDIA;
	}

	public void setARR_INDIA(String aRR_INDIA) {
		ARR_INDIA = aRR_INDIA;
	}

	public String getARR_SCAN() {
		return ARR_SCAN;
	}

	public void setARR_SCAN(String aRR_SCAN) {
		ARR_SCAN = aRR_SCAN;
	}

	@Column(name = "CIN_DT")
	private Date CIN_DT;

	@Column(name = "CUS_SITE")
	private String CUS_SITE;

	@Column(name = "POSTINGDT")
	private String POSTINGDT;

	public String getPOSTINGTIME() {
		return POSTINGTIME;
	}

	public void setPOSTINGTIME(String pOSTINGTIME) {
		POSTINGTIME = pOSTINGTIME;
	}

	@Transient
	private String postDate;

	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}

	@Transient
	private String CinDate;

	public String getCinDate() {
		return CinDate;
	}

	public void setCinDate(String cinDate) {
		CinDate = cinDate;
	}

	@Transient
	private String POSTINGTIME;

	@Transient
	public String QRY_DT;

	public String getQRY_DT() {
		return QRY_DT;
	}

	public void setQRY_DT(String qRY_DT) {
		QRY_DT = qRY_DT;
	}

	@Column(name = "MESG_TYPE_CD")
	private String MESG_TYPE_CD;

	@Column(name = "JOB_NO")
	private Long JOB_NO;

	@Column(name = "JOB_DT")
	private Date JOB_DT;

	@Column(name = "MAIL_PID")
	private String MAIL_PID;

	@Column(name = "DECLARATION_PID")
	private String DECLARATION_PID;

	@Column(name = "CUST_ORG_CD")
	private String CUST_ORG_CD;

	@Column(name = "POST_ORG_CD")
	private String POST_ORG_CD;

	@Column(name = "DOCUMENTS")
	private String DOCUMENTS;

	@Column(name = "GROSS_WT")
	private Float GROSS_WT;

	@Column(name = "MEASURED_GROSS_WT")
	private Long MEASURED_GROSS_WT;

	@Column(name = "HANDLING_CLASS_CD")
	private String HANDLING_CLASS_CD;

	@Column(name = "TOT_DECL_VAL")
	private Long TOT_DECL_VAL;

	@Column(name = "TOT_ASS_VAL")
	private Float TOT_ASS_VAL;

	@Column(name = "TOT_DUTY")
	private Float TOT_DUTY;

	@Column(name = "TOT_DUTY_FG")
	private Float totalDutyFg;

	@Column(name = "INS_VAL")
	private Long INS_VAL;

	@Column(name = "INS_VAL_CURRCD")
	private String INS_VAL_CURRCD;

	@Column(name = "NATURE_TYPE_CD")
	private String NATURE_TYPE_CD;

	@Column(name = "POSTAGE_AMT")
	private Long POSTAGE_AMT;

	@Column(name = "POSTAGE_CURR_CD")
	private String POSTAGE_CURR_CD;


	@Column(name = "MODIFIED")
	private String MODIFIED;

	@Column(name = "RECP_ADD1")
	private String RECP_ADD1;

	@Column(name = "RECP_ADD2")
	private String RECP_ADD2;

	@Column(name = "RECP_CITY")
	private String RECP_CITY;

	@Column(name = "RECP_CNTRY_CD")
	private String RECP_CNTRY_CD;

	@Column(name = "RECP_EMAILID")
	private String RECP_EMAILID;

	@Column(name = "RECP_FAX")
	private String RECP_FAX;

	@Column(name = "RECP_FNAME")
	private String RECP_FNAME;

	@Column(name = "RECP_IDREF")
	private String RECP_IDREF;

	@Column(name = "RECP_LNAME")
	private String RECP_LNAME;

	@Column(name = "RECP_NAME")
	private String RECP_NAME;

	@Column(name = "RECP_STATE")
	private String RECP_STATE;

	@Column(name = "RECP_PHONE")
	private String RECP_PHONE;

	@Column(name = "RECP_ZIP")
	private String RECP_ZIP;

	@Column(name = "SEND_ADD1")
	private String SEND_ADD1;

	@Column(name = "SEND_ADD2")
	private String SEND_ADD2;

	@Column(name = "SEND_CITY")
	private String SEND_CITY;

	@Column(name = "SEND_CNTRY_CD")
	private String SEND_CNTRY_CD;

	@Column(name = "SEND_EMAILID")
	private String SEND_EMAILID;

	@Column(name = "SEND_FNAME")
	private String SEND_FNAME;

	@Column(name = "SEND_IDREF")
	private String SEND_IDREF;

	@Column(name = "SEND_LNAME")
	private String SEND_LNAME;

	@Column(name = "SEND_NAME")
	private String SEND_NAME;

	@Column(name = "SEND_STATE")
	private String SEND_STATE;

	@Column(name = "SEND_PHONE")
	private String SEND_PHONE;

	@Column(name = "SEND_ZIP")
	private String SEND_ZIP;

	@Column(name = "SEND_FAX")
	private String SEND_FAX;

	@Column(name = "TRANS_DATE")
	private Date TRANS_DATE;   
	
	@Column(name = "ASS_DT")
	private Date ASS_DT; 

	@Column(name = "TRANS_MODE")
	private String TRANS_MODE;

	@Column(name = "DESTPOST_ORG_CD")
	private String DESTPOST_ORG_CD;

	@Column(name = "LOCALID")
	private String LOCALID;

	@Column(name = "LOCALID2")
	private String LOCALID2;

	@Column(name = "MAIL_CATEGORY_CD")
	private String MAIL_CATEGORY_CD;

	@Column(name = "MAIL_CLASS_CD")
	private String MAIL_CLASS_CD;

	@Column(name = "MAIL_STATE_CD")
	private String MAIL_STATE_CD;

	@Column(name = "MAIL_STATE_REMARKS")
	private String MAIL_STATE_REMARKS;

	@Column(name = "ORIGPOST_ORG_CD")
	private String ORIGPOST_ORG_CD;

	@Column(name = "UNIQUE_ID")
	private String UNIQUE_ID;

	@Column(name = "TYPE_CD")
	private String TYPE_CD;

	@Column(name = "FPO_CODE")
	private String FPO_CODE;

	@Column(name = "PINCODE")
	private String PINCODE;

	@Column(name = "AMEND_SERIAL_NO")
	private Long AMEND_SERIAL_NO;

	@Column(name = "AMEND_DT")
	private Date AMEND_DT;

	@Column(name = "AMEND_FLAG")
	private String AMEND_FLAG;

	@Column(name = "ROLE")
	private String ROLE;

	@Column(name = "OFF_ID")
	private String OFF_ID;
	
	@Column(name = "ACL_OFFID")
	private String ACL_OFFID;
	
	@Column(name = "INIT_QUE")
	private String init_que;
	
	@Column(name = "COMMERCIAL")
	private String commercial;

	public String getCommercial() {
		return commercial;
	}

	public void setCommercial(String commercial) {
		this.commercial = commercial;
	}

	public String getInit_que() {
		return init_que;
	}

	public void setInit_que(String init_que) {
		this.init_que = init_que;
	}

	public String getACL_OFFID() {
		return ACL_OFFID;
	}

	public void setACL_OFFID(String aCL_OFFID) {
		ACL_OFFID = aCL_OFFID;
	}

	@Column(name = "FPOMAIN_NO")
	private String FPOMAIN_NO;

	@Column(name = "TOT_FINE")
	private Float TOT_FINE;

	@Column(name = "TOT_PENAL")
	private Float TOT_PENAL;

	@Column(name = "DEP_CMTS")
	private String DEP_CMTS;

	@Column(name = "TOT_AMT_PAYABLE")
	private Float TOT_AMT_PAYABLE;   
	
	@Column(name = "INS_CALC_VAL")
	private Float INS_CALC_VAL;
	
	@Column(name = "FRT_CALC_VAL")
	private Float FRT_CALC_VAL;
	
	@Column(name = "TOTASS_CALC_VAL")
	private Float TOTASS_CALC_VAL;
	
	@Column(name = "INS_CURR_RATE")
	private Float INS_CURR_RATE;
	
	@Column(name = "FRT_CURR_RATE")
	private Float FRT_CURR_RATE;

	@Column(name = "UPD_CIF")
	private String UPD_CIF;
	


	@Column(name = "clr_site")
	private String clrSite;	

	public String getClrSite() {
		return clrSite;
	}

	public void setClrSite(String clrSite) {
		this.clrSite = clrSite;
	}
	

	public String getUPD_CIF() {
		return UPD_CIF;
	}

	public void setUPD_CIF(String uPD_CIF) {
		UPD_CIF = uPD_CIF;
	}

	public Float getINS_CALC_VAL() {
		return INS_CALC_VAL;
	}

	public void setINS_CALC_VAL(Float iNS_CALC_VAL) {
		INS_CALC_VAL = iNS_CALC_VAL;
	}

	public Float getFRT_CALC_VAL() {
		return FRT_CALC_VAL;
	}

	public void setFRT_CALC_VAL(Float fRT_CALC_VAL) {
		FRT_CALC_VAL = fRT_CALC_VAL;
	}

	public Float getTOTASS_CALC_VAL() {
		return TOTASS_CALC_VAL;
	}

	public void setTOTASS_CALC_VAL(Float tOTASS_CALC_VAL) {
		TOTASS_CALC_VAL = tOTASS_CALC_VAL;
	}

	public Float getINS_CURR_RATE() {
		return INS_CURR_RATE;
	}

	public void setINS_CURR_RATE(Float iNS_CURR_RATE) {
		INS_CURR_RATE = iNS_CURR_RATE;
	}

	public Float getFRT_CURR_RATE() {
		return FRT_CURR_RATE;
	}

	public void setFRT_CURR_RATE(Float fRT_CURR_RATE) {
		FRT_CURR_RATE = fRT_CURR_RATE;
	}

	@Transient
	private String queue;
	
	@Transient
	private String stringval;
	

	public String getStringval() {
		return stringval;
	}

	public void setStringval(String stringval) {
		this.stringval = stringval;
	}

	public Date getASS_DT() {
		return ASS_DT;
	}

	public void setASS_DT(Date aSS_DT) {
		ASS_DT = aSS_DT;
	}

	public Float getTOT_AMT_PAYABLE() {
		return TOT_AMT_PAYABLE;
	}

	public void setTOT_AMT_PAYABLE(Float tOT_AMT_PAYABLE) {
		TOT_AMT_PAYABLE = tOT_AMT_PAYABLE;
	}

	public Float getTOT_FINE() {
		return TOT_FINE;
	}

	public void setTOT_FINE(Float tOT_FINE) {
		TOT_FINE = tOT_FINE;
	}

	public Float getTOT_PENAL() {
		return TOT_PENAL;
	}

	public void setTOT_PENAL(Float tOT_PENAL) {
		TOT_PENAL = tOT_PENAL;
	}

	public String getDEP_CMTS() {
		return DEP_CMTS;
	}

	public void setDEP_CMTS(String dEP_CMTS) {
		DEP_CMTS = dEP_CMTS;
	}

	public String getQueue() {
		return queue;
	}

	public void setQueue(String queue) {
		this.queue = queue;
	}

	public String getSEND_FAX() {
		return SEND_FAX;
	}

	public void setSEND_FAX(String sEND_FAX) {
		SEND_FAX = sEND_FAX;
	}

	public String getTYPE_CD() {
		return TYPE_CD;
	}

	public void setTYPE_CD(String tYPE_CD) {
		TYPE_CD = tYPE_CD;
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

	public String getFPOMAIN_NO() {
		return FPOMAIN_NO;
	}

	public void setFPOMAIN_NO(String fPOMAIN_NO) {
		FPOMAIN_NO = fPOMAIN_NO;
	}

	@Transient
	private String INTERPRETATION;

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

	public String getMAIL_PID() {
		return MAIL_PID;
	}

	public void setMAIL_PID(String mAIL_PID) {
		MAIL_PID = mAIL_PID;
	}

	public String getDECLARATION_PID() {
		return DECLARATION_PID;
	}

	public void setDECLARATION_PID(String dECLARATION_PID) {
		DECLARATION_PID = dECLARATION_PID;
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

	public String getDOCUMENTS() {
		return DOCUMENTS;
	}

	public void setDOCUMENTS(String dOCUMENTS) {
		DOCUMENTS = dOCUMENTS;
	}

	public Float getGROSS_WT() {
		return GROSS_WT;
	}

	public void setGROSS_WT(Float gROSS_WT) {
		GROSS_WT = gROSS_WT;
	}

	public Long getMEASURED_GROSS_WT() {
		return MEASURED_GROSS_WT;
	}

	public void setMEASURED_GROSS_WT(Long mEASURED_GROSS_WT) {
		MEASURED_GROSS_WT = mEASURED_GROSS_WT;
	}

	public String getHANDLING_CLASS_CD() {
		return HANDLING_CLASS_CD;
	}

	public void setHANDLING_CLASS_CD(String hANDLING_CLASS_CD) {
		HANDLING_CLASS_CD = hANDLING_CLASS_CD;
	}

	public Long getTOT_DECL_VAL() {
		return TOT_DECL_VAL;
	}

	public void setTOT_DECL_VAL(Long tOT_DECL_VAL) {
		TOT_DECL_VAL = tOT_DECL_VAL;
	}

	public Float getTOT_ASS_VAL() {
		return TOT_ASS_VAL;
	}

	public void setTOT_ASS_VAL(Float tOT_ASS_VAL) {
		TOT_ASS_VAL = tOT_ASS_VAL;
	}

	public Float getTOT_DUTY() {
		return TOT_DUTY;
	}

	public void setTOT_DUTY(Float tOT_DUTY) {
		TOT_DUTY = tOT_DUTY;
	}

	public Long getINS_VAL() {
		return INS_VAL;
	}

	public void setINS_VAL(Long iNS_VAL) {
		INS_VAL = iNS_VAL;
	}

	public String getINS_VAL_CURRCD() {
		return INS_VAL_CURRCD;
	}

	public void setINS_VAL_CURRCD(String iNS_VAL_CURRCD) {
		INS_VAL_CURRCD = iNS_VAL_CURRCD;
	}

	public String getNATURE_TYPE_CD() {
		return NATURE_TYPE_CD;
	}

	public String getFpoAllocatedValue() {
		return FPO_CODE;
	}

	public void setNATURE_TYPE_CD(String nATURE_TYPE_CD) {
		NATURE_TYPE_CD = nATURE_TYPE_CD;
	}

	public Long getPOSTAGE_AMT() {
		return POSTAGE_AMT;
	}

	public void setPOSTAGE_AMT(Long pOSTAGE_AMT) {
		POSTAGE_AMT = pOSTAGE_AMT;
	}

	public String getPOSTAGE_CURR_CD() {
		return POSTAGE_CURR_CD;
	}

	public void setPOSTAGE_CURR_CD(String pOSTAGE_CURR_CD) {
		POSTAGE_CURR_CD = pOSTAGE_CURR_CD;
	}

	public String getMODIFIED() {
		return MODIFIED;
	}

	public void setMODIFIED(String mODIFIED) {
		MODIFIED = mODIFIED;
	}

	public String getRECP_ADD1() {
		return RECP_ADD1;
	}

	public void setRECP_ADD1(String rECP_ADD1) {
		RECP_ADD1 = rECP_ADD1;
	}

	public String getRECP_ADD2() {
		return RECP_ADD2;
	}

	public void setRECP_ADD2(String rECP_ADD2) {
		RECP_ADD2 = rECP_ADD2;
	}

	public String getRECP_CITY() {
		return RECP_CITY;
	}

	public void setRECP_CITY(String rECP_CITY) {
		RECP_CITY = rECP_CITY;
	}

	public String getRECP_CNTRY_CD() {
		return RECP_CNTRY_CD;
	}

	public Float getTotalDutyFg() {
		return totalDutyFg;
	}

	public void setTotalDutyFg(Float totalDutyFg) {
		this.totalDutyFg = totalDutyFg;
	}

	public void setRECP_CNTRY_CD(String rECP_CNTRY_CD) {
		RECP_CNTRY_CD = rECP_CNTRY_CD;
	}

	public String getRECP_EMAILID() {
		return RECP_EMAILID;
	}

	public void setRECP_EMAILID(String rECP_EMAILID) {
		RECP_EMAILID = rECP_EMAILID;
	}

	public String getRECP_FAX() {
		return RECP_FAX;
	}

	public void setRECP_FAX(String rECP_FAX) {
		RECP_FAX = rECP_FAX;
	}

	public String getRECP_FNAME() {
		return RECP_FNAME;
	}

	public void setRECP_FNAME(String rECP_FNAME) {
		RECP_FNAME = rECP_FNAME;
	}

	public String getRECP_IDREF() {
		return RECP_IDREF;
	}

	public void setRECP_IDREF(String rECP_IDREF) {
		RECP_IDREF = rECP_IDREF;
	}

	public String getRECP_LNAME() {
		return RECP_LNAME;
	}

	public void setRECP_LNAME(String rECP_LNAME) {
		RECP_LNAME = rECP_LNAME;
	}

	public String getRECP_NAME() {
		return RECP_NAME;
	}

	public void setRECP_NAME(String rECP_NAME) {
		RECP_NAME = rECP_NAME;
	}

	public String getRECP_STATE() {
		return RECP_STATE;
	}

	public void setRECP_STATE(String rECP_STATE) {
		RECP_STATE = rECP_STATE;
	}

	public String getRECP_PHONE() {
		return RECP_PHONE;
	}

	public void setRECP_PHONE(String rECP_PHONE) {
		RECP_PHONE = rECP_PHONE;
	}

	public String getRECP_ZIP() {
		return RECP_ZIP;
	}

	public void setRECP_ZIP(String rECP_ZIP) {
		RECP_ZIP = rECP_ZIP;
	}

	public String getSEND_ADD1() {
		return SEND_ADD1;
	}

	public void setSEND_ADD1(String sEND_ADD1) {
		SEND_ADD1 = sEND_ADD1;
	}

	public String getSEND_ADD2() {
		return SEND_ADD2;
	}

	public void setSEND_ADD2(String sEND_ADD2) {
		SEND_ADD2 = sEND_ADD2;
	}

	public String getSEND_CITY() {
		return SEND_CITY;
	}

	public void setSEND_CITY(String sEND_CITY) {
		SEND_CITY = sEND_CITY;
	}

	public String getSEND_CNTRY_CD() {
		return SEND_CNTRY_CD;
	}

	public void setSEND_CNTRY_CD(String sEND_CNTRY_CD) {
		SEND_CNTRY_CD = sEND_CNTRY_CD;
	}

	public String getSEND_EMAILID() {
		return SEND_EMAILID;
	}

	public void setSEND_EMAILID(String sEND_EMAILID) {
		SEND_EMAILID = sEND_EMAILID;
	}

	public String getSEND_FNAME() {
		return SEND_FNAME;
	}

	public void setSEND_FNAME(String sEND_FNAME) {
		SEND_FNAME = sEND_FNAME;
	}

	public String getSEND_IDREF() {
		return SEND_IDREF;
	}

	public void setSEND_IDREF(String sEND_IDREF) {
		SEND_IDREF = sEND_IDREF;
	}

	public String getSEND_LNAME() {
		return SEND_LNAME;
	}

	public void setSEND_LNAME(String sEND_LNAME) {
		SEND_LNAME = sEND_LNAME;
	}

	public String getSEND_NAME() {
		return SEND_NAME;
	}

	public void setSEND_NAME(String sEND_NAME) {
		SEND_NAME = sEND_NAME;
	}

	public String getSEND_STATE() {
		return SEND_STATE;
	}

	public void setSEND_STATE(String sEND_STATE) {
		SEND_STATE = sEND_STATE;
	}

	public String getSEND_PHONE() {
		return SEND_PHONE;
	}

	public void setSEND_PHONE(String sEND_PHONE) {
		SEND_PHONE = sEND_PHONE;
	}

	public String getSEND_ZIP() {
		return SEND_ZIP;
	}

	public void setSEND_ZIP(String sEND_ZIP) {
		SEND_ZIP = sEND_ZIP;
	}

	public Date getTRANS_DATE() {
		return TRANS_DATE;
	}

	public void setTRANS_DATE(Date tRANS_DATE) {
		TRANS_DATE = tRANS_DATE;
	}

	public String getTRANS_MODE() {
		return TRANS_MODE;
	}

	public void setTRANS_MODE(String tRANS_MODE) {
		TRANS_MODE = tRANS_MODE;
	}

	public String getDESTPOST_ORG_CD() {
		return DESTPOST_ORG_CD;
	}

	public void setDESTPOST_ORG_CD(String dESTPOST_ORG_CD) {
		DESTPOST_ORG_CD = dESTPOST_ORG_CD;
	}

	public String getLOCALID() {
		return LOCALID;
	}

	public void setLOCALID(String lOCALID) {
		LOCALID = lOCALID;
	}

	public String getLOCALID2() {
		return LOCALID2;
	}

	public void setLOCALID2(String lOCALID2) {
		LOCALID2 = lOCALID2;
	}

	public String getMAIL_CATEGORY_CD() {
		return MAIL_CATEGORY_CD;
	}

	public void setMAIL_CATEGORY_CD(String mAIL_CATEGORY_CD) {
		MAIL_CATEGORY_CD = mAIL_CATEGORY_CD;
	}

	public String getMAIL_CLASS_CD() {
		return MAIL_CLASS_CD;
	}

	public void setMAIL_CLASS_CD(String mAIL_CLASS_CD) {
		MAIL_CLASS_CD = mAIL_CLASS_CD;
	}

	public String getMAIL_STATE_CD() {
		return MAIL_STATE_CD;
	}

	public void setMAIL_STATE_CD(String mAIL_STATE_CD) {
		MAIL_STATE_CD = mAIL_STATE_CD;
	}

	public String getMAIL_STATE_REMARKS() {
		return MAIL_STATE_REMARKS;
	}

	public void setMAIL_STATE_REMARKS(String mAIL_STATE_REMARKS) {
		MAIL_STATE_REMARKS = mAIL_STATE_REMARKS;
	}

	

	public String getORIGPOST_ORG_CD() {
		return ORIGPOST_ORG_CD;
	}

	public void setORIGPOST_ORG_CD(String oRIGPOST_ORG_CD) {
		ORIGPOST_ORG_CD = oRIGPOST_ORG_CD;
	}

	public String getUNIQUE_ID() {
		return UNIQUE_ID;
	}

	public void setUNIQUE_ID(String uNIQUE_ID) {
		UNIQUE_ID = uNIQUE_ID;
	}

	public String getFPO_CODE() {
		return FPO_CODE;
	}

	public void setFPO_CODE(String fPO_CODE) {
		FPO_CODE = fPO_CODE;
	}

	public String getPINCODE() {
		return PINCODE;
	}

	public void setPINCODE(String pINCODE) {
		PINCODE = pINCODE;
	}

	public String getINTERPRETATION() {
		return INTERPRETATION;
	}

	public void setINTERPRETATION(String iNTERPRETATION) {
		INTERPRETATION = iNTERPRETATION;
	}

}
