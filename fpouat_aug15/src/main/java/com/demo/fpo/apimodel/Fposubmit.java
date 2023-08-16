package com.demo.fpo.apimodel;


import java.math.BigDecimal;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.Id;

import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;



@Entity
@Table(name = "FPO_MAIN")
public class Fposubmit {
	
	/*
	 * @Id
	 * 
	 * @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =
	 * "idsequence3")
	 * 
	 * @SequenceGenerator(allocationSize=1, name="idsequence3",
	 * sequenceName="CIN_NO")
	 */
	
	@Id
//	@GenericGenerator(name = "fpomainid", strategy = "com.seq.gen.idgen.FpoIdGenerator")
//	@GeneratedValue(generator = "fpomainid")  
	@GenericGenerator(name = "FPO_MAIN_ID", strategy = "com.seq.gen.FPO_MAINIdGenerator")
	@GeneratedValue(generator = "FPO_MAIN_ID")
	@Column(name = "FPOMAIN_NO")
	private Long FPOMAIN_NO;

	@Column(name = "JOB_NO") 
	private Long JOB_NO;     
	
	@Column(name = "CIN_NO") 
	private String CIN_NO;  
	
	@Column(name = "CIN_DT") 
	private java.sql.Date cindt;
	
	@Column(name = "CUS_SITE")
	private String CUS_SITE;
	
	@Column(name = "CLR_SITE")
	private String CLR_SITE;
	
	@Column(name = "ARR_FPO")
	private String ARR_FPO;
	
	public String getARR_FPO() {
		return ARR_FPO;
	}

	public void setARR_FPO(String aRR_FPO) {
		ARR_FPO = aRR_FPO;
	}

	public String getCLR_SITE() {
		return CLR_SITE;
	}

	public void setCLR_SITE(String cLR_SITE) {
		CLR_SITE = cLR_SITE;
	}
	
	public Long getFPOMAIN_NO() {
		return FPOMAIN_NO;
	}

	public void setFPOMAIN_NO(Long fPOMAIN_NO) {
		FPOMAIN_NO = fPOMAIN_NO;
	}

	public java.sql.Date getCindt() {
		return cindt;
	}

	public void setCindt(java.sql.Date cindt) {
		this.cindt = cindt;
	}

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

	public Long getJOB_NO() {
		return JOB_NO;	
    }

	public void setJOB_NO(Long jOB_NO) {
		JOB_NO = jOB_NO;
	}

	@Column(name = "ITEM_ID")
	private String ITEM_ID;


	@Column(name = "MESG_TYPE_CD")
	private String MESG_TYPE_CD;

	public String getCIN_NO() {
		return CIN_NO;
	}

	public void setCIN_NO(String cIN_NO) {
		CIN_NO = cIN_NO;
	}

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
	private BigDecimal GROSS_WT;
	
	@Column(name = "TOT_AMT_PAYABLE")
	private Float TOT_AMT_PAYABLE;
	
	public Float getTOT_AMT_PAYABLE() {
		return TOT_AMT_PAYABLE;
	}

	public void setTOT_AMT_PAYABLE(Float tOT_AMT_PAYABLE) {
		TOT_AMT_PAYABLE = tOT_AMT_PAYABLE;
	}

	@Column(name = "TOT_DECL_VAL")
	private BigDecimal TOT_DECL_VAL;
	
	@Column(name = "TOT_ASS_VAL")
	private BigDecimal TOT_ASS_VAL;

	@Column(name = "TOT_DUTY")
	private BigDecimal TOT_DUTY;
	
	@Column(name = "TOT_DUTY_FG")
	private float TOT_DUTY_FG;
	
	@Column(name = "HANDLING_CLASS_CD")
	private String HANDLING_CLASS_CD;
	
	@Column(name = "INS_VAL")
	private BigDecimal INS_VAL;
	
	@Column(name = "INS_CURR_RATE")
	private float INS_currrt;

	@Column(name = "INS_VAL_CURRCD")
	private String INS_VAL_CURRCD;

	@Column(name = "FRT_CURR_RATE")
	private float FRT_currrt;
	
	@Column(name="INS_CALC_VAL")
	private float INS_calc_val;
	
	@Column(name="FRT_CALC_VAL")
	private float FRT_calc_val;
	
	public float getTotass_calc_val() {
		return totass_calc_val;
	}

	public void setTotass_calc_val(float totass_calc_val) {
		this.totass_calc_val = totass_calc_val;
	}

	@Column(name="TOTASS_CALC_VAL")
	private float totass_calc_val;
	
	public float getFRT_currrt() {
		return FRT_currrt;
	}

	public void setFRT_currrt(float fRT_currrt) {
		FRT_currrt = fRT_currrt;
	}

	public float getINS_calc_val() {
		return INS_calc_val;
	}

	public void setINS_calc_val(float iNS_calc_val) {
		INS_calc_val = iNS_calc_val;
	}

	public float getFRT_calc_val() {
		return FRT_calc_val;
	}

	public void setFRT_calc_val(float fRT_calc_val) {
		FRT_calc_val = fRT_calc_val;
	}

	@Column(name = "NATURE_TYPE_CD")
	private String NATURE_TYPE_CD;

	public float getINS_currrt() {
		return INS_currrt;
	}

	public void setINS_currrt(float iNS_currrt) {
		INS_currrt = iNS_currrt;
	}


	@Column(name = "POSTAGE_AMT")
	private BigDecimal POSTAGE_AMT;

	@Column(name = "POSTAGE_CURR_CD")
	private String POSTAGE_CURR_CD;
	
	public String getUPD_CIF() {
		return UPD_CIF;
	}

	public void setUPD_CIF(String uPD_CIF) {
		UPD_CIF = uPD_CIF;
	}

	@Column(name = "UPD_CIF")
	private String UPD_CIF;

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

	@Column(name = "SEND_FAX")
	private String SEND_FAX;

	@Column(name = "SEND_FNAME")
	private String SEND_FNAME;

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

	@Column(name = "TRANS_DATE")
	private String TRANS_DATE;
	
	@Column(name = "POSTINGDT")
	private String POSTINGDT;

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
	
	@Column(name = "INIT_QUE")
	private String init_que;
	
	

	public String getInit_que() {
		return init_que;
	}

	public void setInit_que(String init_que) {
		this.init_que = init_que;
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

	public BigDecimal getGROSS_WT() {
		return GROSS_WT;
	}

	public void setGROSS_WT(BigDecimal gROSS_WT) {
		GROSS_WT = gROSS_WT;
	}

	public BigDecimal getTOT_DECL_VAL() {
		return TOT_DECL_VAL;
	}

	public void setTOT_DECL_VAL(BigDecimal tOT_DECL_VAL) {
		TOT_DECL_VAL = tOT_DECL_VAL;
	}

	public BigDecimal getTOT_ASS_VAL() {
		return TOT_ASS_VAL;
	}

	public void setTOT_ASS_VAL(BigDecimal tOT_ASS_VAL) {
		TOT_ASS_VAL = tOT_ASS_VAL;
	}

	public BigDecimal getTOT_DUTY() {
		return TOT_DUTY;
	}

	public void setTOT_DUTY(BigDecimal tOT_DUTY) {
		TOT_DUTY = tOT_DUTY;
	}

	

	public float getTOT_DUTY_FG() {
		return TOT_DUTY_FG;
	}

	public void setTOT_DUTY_FG(float tOT_DUTY_FG) {
		TOT_DUTY_FG = tOT_DUTY_FG;
	}

	public String getHANDLING_CLASS_CD() {
		return HANDLING_CLASS_CD;
	}

	public void setHANDLING_CLASS_CD(String hANDLING_CLASS_CD) {
		HANDLING_CLASS_CD = hANDLING_CLASS_CD;
	}

	public BigDecimal getINS_VAL() {
		return INS_VAL;
	}

	public void setINS_VAL(BigDecimal iNS_VAL) {
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

	public void setNATURE_TYPE_CD(String nATURE_TYPE_CD) {
		NATURE_TYPE_CD = nATURE_TYPE_CD;
	}

	public BigDecimal getPOSTAGE_AMT() {
		return POSTAGE_AMT;
	}

	public void setPOSTAGE_AMT(BigDecimal pOSTAGE_AMT) {
		POSTAGE_AMT = pOSTAGE_AMT;
	}

	public String getPOSTAGE_CURR_CD() {
		return POSTAGE_CURR_CD;
	}

	public void setPOSTAGE_CURR_CD(String pOSTAGE_CURR_CD) {
		POSTAGE_CURR_CD = pOSTAGE_CURR_CD;
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

	public String getSEND_FAX() {
		return SEND_FAX;
	}

	public void setSEND_FAX(String sEND_FAX) {
		SEND_FAX = sEND_FAX;
	}

	public String getSEND_FNAME() {
		return SEND_FNAME;
	}

	public void setSEND_FNAME(String sEND_FNAME) {
		SEND_FNAME = sEND_FNAME;
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

	public String getTRANS_DATE() {
		return TRANS_DATE;
	}

	public void setTRANS_DATE(String tRANS_DATE) {
		TRANS_DATE = tRANS_DATE;
	}

	public String getPOSTINGDT() {
		return POSTINGDT;
	}

	public void setPOSTINGDT(String pOSTINGDT) {
		POSTINGDT = pOSTINGDT;
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

	public String getRECP_IDREF() {
		return RECP_IDREF;
	}

	public void setRECP_IDREF(String rECP_IDREF) {
		RECP_IDREF = rECP_IDREF;
	}

	public String getSEND_IDREF() {
		return SEND_IDREF;
	}

	public void setSEND_IDREF(String sEND_IDREF) {
		SEND_IDREF = sEND_IDREF;
	}

	@Column(name = "UNIQUE_ID")
	private String UNIQUE_ID;

	@Column(name = "TYPE_CD")
	private String TYPE_CD;
	
	@Column(name = "FPO_CODE")
	private String FPO_CODE;
	
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

	@Column(name = "PINCODE")
	private String PINCODE;
	
	public String getTYPE_CD() {
		return TYPE_CD;
	}

	public void setTYPE_CD(String tYPE_CD) {
		TYPE_CD = tYPE_CD;
	}

	@Column(name = "RECP_IDREF")
	private String RECP_IDREF;
	
	@Column(name = "SEND_IDREF")
	private String SEND_IDREF;
	
	//private Set<Fpoitemins> Fpoitemrecords = 
	//		new HashSet<Fpoitemins>(0);
	
//    @OneToMany(fetch=FetchType.LAZY)
//    @JoinColumn(name="ITEM_ID",referencedColumnName="ITEM_ID")
//    private List<Fpoitemins> fpoitemins = new ArrayList<>();
//    
//    
//    public void additemins(String ItemId, String PostingDt, int CP_NOU) {
//    	  Fpoitemins fpoitemins = new Fpoitemins();
//    	  fpoitemins.setITEM_ID(ItemId);
//    	  fpoitemins.setPOSTINGDT(PostingDt);
//    	  fpoitemins.setCP_NOU(CP_NOU);
//    }
    

	public String getITEM_ID() {
		return ITEM_ID;
	}

	public void setITEM_ID(String ITEM_ID) {
		this.ITEM_ID = ITEM_ID;
	}

	
	public String getMESG_TYPE_CD() {
		return MESG_TYPE_CD;
	}

	public void setMESG_TYPE_CD(String MESG_TYPE_CD) {
		this.MESG_TYPE_CD = MESG_TYPE_CD;
	}


	public String getMAIL_PID() {
		return MAIL_PID;
	}

	public void setMAIL_PID(String MAIL_PID) {
		this.MAIL_PID = MAIL_PID;
	}

	public String getDECLARATION_PID() {
		return DECLARATION_PID;
	}

	public void setDECLARATION_PID(String DECLARATION_PID) {
		this.DECLARATION_PID = DECLARATION_PID;
	}

	public String getCUST_ORG_CD() {
		return CUST_ORG_CD;
	}

	public void setCUST_ORG_CD(String CUST_ORG_CD) {
		this.CUST_ORG_CD = CUST_ORG_CD;
	}

	
		// TODO Auto-generated method stub
		
	}

