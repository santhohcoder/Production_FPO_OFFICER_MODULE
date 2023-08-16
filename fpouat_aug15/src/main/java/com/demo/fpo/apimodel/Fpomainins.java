package com.demo.fpo.apimodel;

import java.math.BigDecimal;



import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;





@Entity
@Table(name = "C_CUSITM")
public class Fpomainins {
	
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	
//	@GeneratedValue(generator = "idSequence9", strategy=GenerationType.SEQUENCE)
//	@SequenceGenerator(name = "idSequence9", sequenceName = "CUSITM_JOB_NO", initialValue = 5, allocationSize = 1)
	@GenericGenerator(name = "C_CUSITM_ID", strategy = "com.seq.gen.C_CUSITMIdGenerator")
	@GeneratedValue(generator = "C_CUSITM_ID")
	@Column(name="JOB_NO")
	public Long JOB_NO;
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "JOB_NO")
//	@SequenceGenerator(sequenceName="CUSITM_JOB_NO", allocationSize=1,  name="JOB_NO")
//	@Column(name="JOB_NO",unique=true,nullable=false,precision=32,scale=0)
	//private Long id;
//	//@Column(name="JOB_NO")
//    private int JOB_NO;
//	
	public Long getJOB_NO() {
		return JOB_NO;
	}

	public void setJOB_NO(Long jOB_NO) {
		JOB_NO = jOB_NO;
	}

	@Column(name = "ITEM_ID")
	private String ITEM_ID;
	
	public void setINS_VAL_CURRCD(String iNS_VAL_CURRCD) {
		INS_VAL_CURRCD = iNS_VAL_CURRCD;
	}

	@Column(name = "MESG_TYPE_CD")
	private String MESG_TYPE_CD;

	@Column(name = "JOB_DT")
	//Date date = new Date();
	private java.sql.Date sqlDate;

	public java.sql.Date getSqlDate() {
        return sqlDate;
    }
 
    public void setSqlDate(java.sql.Date sqlDate) {
        this.sqlDate = sqlDate;
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

	@Column(name = "HANDLING_CLASS_CD")
	private String HANDLING_CLASS_CD;
	
	@Column(name = "INS_VAL")
	private BigDecimal INS_VAL;

	@Column(name = "INS_VAL_CURRCD")
	private String INS_VAL_CURRCD;

	@Column(name = "NATURE_TYPE_CD")
	private String NATURE_TYPE_CD;

	@Column(name = "POSTAGE_AMT")
	private BigDecimal POSTAGE_AMT;

	@Column(name = "POSTAGE_CURR_CD")
	private String POSTAGE_CURR_CD;

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

	@Column(name = "ORIGPOST_ORGCD")
	private String ORIGPOST_ORGCD;

	@Column(name = "UNIQUE_ID")
	private String UNIQUE_ID;

	@Column(name = "TYPE_CD")
	private String TYPE_CD;
	
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

	public String getPOST_ORG_CD() {
		return POST_ORG_CD;
	}

	public void setPOST_ORG_CD(String POST_ORG_CD) {
		this.POST_ORG_CD = POST_ORG_CD;
	}

	public String getDOCUMENTS() {
		return DOCUMENTS;
	}

	public void setDOCUMENTS(String DOCUMENTS) {
		this.DOCUMENTS = DOCUMENTS;
	}

	public BigDecimal getGROSS_WT() {
		return GROSS_WT;
	}

	public void setGROSS_WT(BigDecimal GROSS_WT) {
		this.GROSS_WT = GROSS_WT;
	}

	public String getHANDLING_CLASS_CD() {
		return HANDLING_CLASS_CD;
	}

	public void setHANDLING_CLASS_CD(String HANDLING_CLASS_CD) {
		this.HANDLING_CLASS_CD = HANDLING_CLASS_CD;
	}


	public BigDecimal getINS_VAL() {
		return INS_VAL;
	}

	public void setINS_VAL(BigDecimal INS_VAL) {
		this.INS_VAL = INS_VAL;
	}

	public String getINS_VAL_CURRCD() {
		return INS_VAL_CURRCD;
	}

	public void setIns_VAL_CURRCD(String INS_VAL_CURRCD) {
		this.INS_VAL_CURRCD = INS_VAL_CURRCD;
	}

	public String getNATURE_TYPE_CD() {
		return NATURE_TYPE_CD;
	}

	public void setNATURE_TYPE_CD(String NATURE_TYPE_CD) {
		this.NATURE_TYPE_CD = NATURE_TYPE_CD;
	}

	public BigDecimal getPOSTAGE_AMT() {
		return POSTAGE_AMT;
	}

	public void setPOSTAGE_AMT(BigDecimal POSTAGE_AMT) {
		this.POSTAGE_AMT = POSTAGE_AMT;
	}

	public String getPOSTAGE_CURR_CD() {
		return POSTAGE_CURR_CD;
	}

	public void setPOSTAGE_CURR_CD(String POSTAGE_CURR_CD) {
		this.POSTAGE_CURR_CD = POSTAGE_CURR_CD;
	}

	public String getRECP_ADD1() {
		return RECP_ADD1;
	}

	public void setRECP_ADD1(String RECP_ADD1) {
		this.RECP_ADD1 = RECP_ADD1;
	}

	public String getRECP_ADD2() {
		return RECP_ADD2;
	}

	public void setRECP_ADD2(String RECP_ADD2) {
		this.RECP_ADD2 = RECP_ADD2;
	}

	public String getRECP_CITY() {
		return RECP_CITY;
	}

	public void setRECP_CITY(String RECP_CITY) {
		this.RECP_CITY = RECP_CITY;
	}

	public String getRECP_CNTRY_CD() {
		return RECP_CNTRY_CD;
	}

	public void setRECP_CNTRY_CD(String RECP_CNTRY_CD) {
		this.RECP_CNTRY_CD = RECP_CNTRY_CD;
	}

	public String getRECP_EMAILID() {
		return RECP_EMAILID;
	}

	public void setRECP_EMAILID(String RECP_EMAILID) {
		this.RECP_EMAILID = RECP_EMAILID;
	}

	public String getRECP_FAX() {
		return RECP_FAX;
	}

	public void setRECP_FAX(String RECP_FAX) {
		this.RECP_FAX = RECP_FAX;
	}

	public String getRECP_FNAME() {
		return RECP_FNAME;
	}

	public void setRECP_FNAME(String RECP_FNAME) {
		this.RECP_FNAME = RECP_FNAME;
	}

	public String getRECP_LNAME() {
		return RECP_LNAME;
	}

	public void setRECP_LNAME(String RECP_LNAME) {
		this.RECP_LNAME = RECP_LNAME;
	}

	public String getRECP_NAME() {
		return RECP_NAME;
	}

	public void setRECP_NAME(String RECP_NAME) {
		this.RECP_NAME = RECP_NAME;
	}

	public String getRECP_STATE() {
		return RECP_STATE;
	}

	public void setRECP_STATE(String RECP_STATE) {
		this.RECP_STATE = RECP_STATE;
	}

	public String getRECP_PHONE() {
		return RECP_PHONE;
	}

	public void setRECP_PHONE(String RECP_PHONE) {
		this.RECP_PHONE = RECP_PHONE;
	}

	public String getRECP_ZIP() {
		return RECP_ZIP;
	}

	public void setRECP_ZIP(String RECP_ZIP) {
		this.RECP_ZIP = RECP_ZIP;
	}

	public String getSEND_ADD1() {
		return SEND_ADD1;
	}

	public void setSEND_ADD1(String SEND_ADD1) {
		this.SEND_ADD1 = SEND_ADD1;
	}

	public String getSEND_ADD2() {
		return SEND_ADD2;
	}

	public void setSEND_ADD2(String SEND_ADD2) {
		this.SEND_ADD2 = SEND_ADD2;
	}

	public String getSEND_CITY() {
		return SEND_CITY;
	}

	public void setSEND_CITY(String SEND_CITY) {
		this.SEND_CITY = SEND_CITY;
	}

	public String getSEND_CNTRY_CD() {
		return SEND_CNTRY_CD;
	}

	public void setSEND_CNTRY_CD(String SEND_CNTRY_CD) {
		this.SEND_CNTRY_CD = SEND_CNTRY_CD;
	}

	public String getSEND_EMAILID() {
		return SEND_EMAILID;
	}

	public void setSEND_EMAILID(String SEND_EMAILID) {
		this.SEND_EMAILID = SEND_EMAILID;
	}

	public String getSEND_FAX() {
		return SEND_FAX;
	}

	public void setSEND_FAX(String SEND_FAX) {
		this.SEND_FAX = SEND_FAX;
	}

	public String getSEND_FNAME() {
		return SEND_FNAME;
	}

	public void setSEND_FNAME(String SEND_FNAME) {
		this.SEND_FNAME = SEND_FNAME;
	}

	public String getSEND_LNAME() {
		return SEND_LNAME;
	}

	public void setSEND_LNAME(String SEND_LNAME) {
		this.SEND_LNAME = SEND_LNAME;
	}

	public String getSEND_NAME() {
		return SEND_NAME;
	}

	public void setSEND_NAME(String SEND_NAME) {
		this.SEND_NAME = SEND_NAME;
	}

	public String getSEND_STATE() {
		return SEND_STATE;
	}

	public void setSEND_STATE(String SEND_STATE) {
		this.SEND_STATE = SEND_STATE;
	}

	public String getSEND_PHONE() {
		return SEND_PHONE;
	}

	public void setSEND_PHONE(String SEND_PHONE) {
		this.SEND_PHONE = SEND_PHONE;
	}

	public String getSEND_ZIP() {
		return SEND_ZIP;
	}

	public void setSEND_ZIP(String SEND_ZIP) {
		this.SEND_ZIP = SEND_ZIP;
	}

	public String getTRANS_DATE() {
		return TRANS_DATE;
	}

	public void setTRANS_DATE(String TRANS_DATE) {
		this.TRANS_DATE = TRANS_DATE;
	}
	
	public String getPOSTINGDT() {
		return POSTINGDT;
	}

	public void setPOSTINGDT(String POSTINGDT) {
		this.POSTINGDT = POSTINGDT;
	}

	public String getTRANS_MODE() {
		return TRANS_MODE;
	}

	public void setTRANS_MODE(String TRANS_MODE) {
		this.TRANS_MODE = TRANS_MODE;
	}

	public String getDESTPOST_ORG_CD() {
		return DESTPOST_ORG_CD;
	}

	public void setDESTPOST_ORG_CD(String DESTPOST_ORG_CD) {
		this.DESTPOST_ORG_CD = DESTPOST_ORG_CD;
	}

	public String getLOCALID() {
		return LOCALID;
	}

	public void setLOCALID(String LOCALID) {
		this.LOCALID = LOCALID;
	}

	public String getLOCALID2() {
		return LOCALID2;
	}

	public void setLOCALID2(String LOCALID2) {
		this.LOCALID2 = LOCALID2;
	}

	public String getMAIL_CATEGORY_CD() {
		return MAIL_CATEGORY_CD;
	}

	public void setMAIL_CATEGORY_CD(String MAIL_CATEGORY_CD) {
		this.MAIL_CATEGORY_CD = MAIL_CATEGORY_CD;
	}

	public String getMAIL_CLASS_CD() {
		return MAIL_CLASS_CD;
	}

	public void setMAIL_CLASS_CD(String MAIL_CLASS_CD) {
		this.MAIL_CLASS_CD = MAIL_CLASS_CD;
	}

	public String getMAIL_STATE_CD() {
		return MAIL_STATE_CD;
	}

	public void setMAIL_STATE_CD(String MAIL_STATE_CD) {
		this.MAIL_STATE_CD = MAIL_STATE_CD;
	}

	public String getMAIL_STATE_REMARKS() {
		return MAIL_STATE_REMARKS;
	}

	public void setMAIL_STATE_REMARKS(String MAIL_STATE_REMARKS) {
		this.MAIL_STATE_REMARKS = MAIL_STATE_REMARKS;
	}

	public String getORIGPOST_ORGCD() {
		return ORIGPOST_ORGCD;
	}

	public void setORIGPOST_ORGCD(String ORIGPOST_ORGCD) {
		this.ORIGPOST_ORGCD = ORIGPOST_ORGCD;
	}

	public String getUNIQUE_ID() {
		return UNIQUE_ID;
	}

	public void setUNIQUE_ID(String UNIQUE_ID) {
		this.UNIQUE_ID = UNIQUE_ID;
	}

	public String getRECP_IDREF() {
		return RECP_IDREF;
	}
	
	public void setRECP_IDREF(String RECP_IDREF){
		this.RECP_IDREF = RECP_IDREF ;
	}
		
	public String getSEND_IDREF() {
			return SEND_IDREF;
	}
		
		public void setSEND_IDREF(String SEND_IDREF) {
			this.SEND_IDREF = SEND_IDREF;
	}		
    	
	
		// TODO Auto-generated method stub
		
}
