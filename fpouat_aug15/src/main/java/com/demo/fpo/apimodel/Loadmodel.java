package com.demo.fpo.apimodel;

import java.util.Date;

//import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "CUSITM_LOAD")
public class Loadmodel {

	
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO, generator = "idsequence7")
	//@SequenceGenerator(allocationSize=1, name="idsequence7", sequenceName="CUSLOAD")
	//@GeneratedValue(strategy = GenerationType.AUTO, generator = "idsequence7")
	//	@SequenceGenerator(name="cusloadid", sequenceName="CUSLOAD")
	@GenericGenerator(name = "CUSITM_LOAD_SEQ", strategy = "com.seq.gen.CUSITM_LOAD_SEQIdGenerator")
	@GeneratedValue(generator = "CUSITM_LOAD_SEQ")
	@Column(name = "ID")
	public Long Id;

	
	@Column(name = "TOKEN")
	private String TOKEN;

	@Column(name = "POST_ORG_CD")
	private String POST_ORG_CD;
	
	@Column(name = "CUST_ORG_CD")
	private String CUST_ORG_CD;
	
	@Column(name = "ORIGPOST_ORGCD")
	private String ORIGPOST_ORGCD;
	
	@Column(name = "ITEM_ID")
	private String ITEM_ID;
	
	@Column(name = "FLOW")
	private String FLOW;
	
	@Column(name = "DESTPOST_ORG_CD")
	private String DESTPOST_ORG_CD;
	
	@Column(name = "CNTRYCD")
	private String CNTRYCD;
	
	@Column(name = "FROMDT")
	private Date FROMDT;
	
	@Column(name = "TODT")
	private Date TODT;
	
	@Column(name = "FROMZIP")
	private String FROMZIP;
	
	@Column(name = "TOZIP")
	private String TOZIP;
	
	@Column(name = "MAIL_CLASS_CD")
	private String MAIL_CLASS_CD;
	
	@Column(name = "WITHOUT_RESP_ONLY")
	private String WITHOUT_RESP_ONLY;
	
	@Column(name = "WITH_RESP_ONLY")
	private String WITH_RESP_ONLY;
	
	@Column(name = "REC_LIMIT")
	private Long REC_LIMIT;
	
	@Column(name = "SUCCESS")
	private String SUCCESS;
	
	@Column(name = "LAST_EXECUTED")
	
	private Date LAST_EXECUTED;
	

@Column(name = "CDSRECORDS")
	
	private Long cdsrecords;
	
@Column(name = "TIMELAPSE")
public long timelapse;
	

	public long getTimelapse() {
	return timelapse;
}

public void setTimelapse(long timelapse) {
	this.timelapse = timelapse;
}

	public Long getCdsrecords() {
	return cdsrecords;
}

public void setCdsrecords(Long cdsrecords) {
	this.cdsrecords = cdsrecords;
}

	public String getTOKEN() {
		return TOKEN;
	}

	public Date getLAST_EXECUTED() {
		return LAST_EXECUTED;
	}

	public void setLAST_EXECUTED(Date lAST_EXECUTED) {
		LAST_EXECUTED = lAST_EXECUTED;
	}

	public void setTOKEN(String tOKEN) {
		TOKEN = tOKEN;
	}

	public String getPOST_ORG_CD() {
		return POST_ORG_CD;
	}

	public void setPOST_ORG_CD(String pOST_ORG_CD) {
		POST_ORG_CD = pOST_ORG_CD;
	}

	public String getCUST_ORG_CD() {
		return CUST_ORG_CD;
	}

	public void setCUST_ORG_CD(String cUST_ORG_CD) {
		CUST_ORG_CD = cUST_ORG_CD;
	}

	public String getORIGPOST_ORGCD() {
		return ORIGPOST_ORGCD;
	}

	public void setORIGPOST_ORGCD(String oRIGPOST_ORGCD) {
		ORIGPOST_ORGCD = oRIGPOST_ORGCD;
	}

	public String getITEM_ID() {
		return ITEM_ID;
	}

	public void setITEM_ID(String iTEM_ID) {
		ITEM_ID = iTEM_ID;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getFLOW() {
		return FLOW;
	}

	public void setFLOW(String fLOW) {
		FLOW = fLOW;
	}

	public String getDESTPOST_ORG_CD() {
		return DESTPOST_ORG_CD;
	}

	public void setDESTPOST_ORG_CD(String dESTPOST_ORG_CD) {
		DESTPOST_ORG_CD = dESTPOST_ORG_CD;
	}

	public String getCNTRYCD() {
		return CNTRYCD;
	}

	public void setCNTRYCD(String cNTRYCD) {
		CNTRYCD = cNTRYCD;
	}

	public Date getFROMDT() {
		return FROMDT;
	}

	public void setFROMDT(Date fROMDT) {
		FROMDT = fROMDT;
	}

	public Date getTODT() {
		return TODT;
	}

	public void setTODT(Date tODT) {
		TODT = tODT;
	}

	public String getFROMZIP() {
		return FROMZIP;
	}

	public void setFROMZIP(String fROMZIP) {
		FROMZIP = fROMZIP;
	}

	public String getTOZIP() {
		return TOZIP;
	}

	public void setTOZIP(String tOZIP) {
		TOZIP = tOZIP;
	}

	public String getMAIL_CLASS_CD() {
		return MAIL_CLASS_CD;
	}

	public void setMAIL_CLASS_CD(String mAIL_CLASS_CD) {
		MAIL_CLASS_CD = mAIL_CLASS_CD;
	}

	public String getWITHOUT_RESP_ONLY() {
		return WITHOUT_RESP_ONLY;
	}

	public void setWITHOUT_RESP_ONLY(String wITHOUT_RESP_ONLY) {
		WITHOUT_RESP_ONLY = wITHOUT_RESP_ONLY;
	}

	public String getWITH_RESP_ONLY() {
		return WITH_RESP_ONLY;
	}

	public void setWITH_RESP_ONLY(String wITH_RESP_ONLY) {
		WITH_RESP_ONLY = wITH_RESP_ONLY;
	}

	public Long getREC_LIMIT() {
		return REC_LIMIT;
	}

	public void setREC_LIMIT(Long rEC_LIMIT) {
		REC_LIMIT = rEC_LIMIT;
	}

	public String getSUCCESS() {
		return SUCCESS;
	}

	public void setSUCCESS(String sUCCESS) {
		SUCCESS = sUCCESS;
	}


}
