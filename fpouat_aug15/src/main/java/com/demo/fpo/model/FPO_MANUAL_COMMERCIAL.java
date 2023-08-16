package com.demo.fpo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "FPO_MANUAL_COMMERCIAL")
public class FPO_MANUAL_COMMERCIAL {
	
	@Id
	@GenericGenerator(name = "FPO_MAN_COMM_SEQ", strategy = "com.seq.gen.FPO_MAN_COMM_SEQIdGenerator")
	@GeneratedValue(generator = "FPO_MAN_COMM_SEQ")
	@Column(name = "ID")
	public Long id;
	
	@Transient
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private String CHLDT; //changed to String for calender issue

	public String getCHLDT() { //changed to String for calender issue
		return CHLDT;
	}

	public void setCHLDT(String cHLDT) { //changed to String for calender issue
		CHLDT = cHLDT;
	}


	@Transient
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private String BEDT;  //changed to String for calender issue
		
	public String getBEDT() { //changed to String for calender issue
		return BEDT; 
	}

	public void setBEDT(String bEDT) { //changed to String for calender issue
		BEDT = bEDT;
	}

	@Transient
	private String currentStatus;
	
	
	
	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}
	
/*	@Transient	
private String EMPNAME;
	
	public String getEMPNAME() {
		return EMPNAME;
	}

	public void setEMP_NAME(String eMPNAME) {
		EMPNAME = eMPNAME;
	}*/



	public Date getCINDT() {
		return CINDT;
	}

	public void setCINDT(Date cINDT) {
		CINDT = cINDT;
	}



	@Transient
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date CINDT;

	
	@Column(name = "BE_NO")
	private String BE_NO;

	@Column(name = "BE_DT")
	private Date BE_DT;
	
	@Column(name = "CIN_DT")
	private Date CIN_DT;
	
	@Column(name = "CHALLAN_DT")
	private Date CHALLAN_DT;
	
	@Column(name = "TOT_DUTY_PAID")
	private float TOT_DUTY_PAID;
	
	@Column(name = "BE_DOC")
	private String BE_DOC;
	
	@Column(name = "INV_DOC")
	private String INV_DOC;
	


	@Column(name = "CHL_DOC")
	private String CHL_DOC;
	
	@Transient
	private MultipartFile filename1;
	
	public String getBE_DOC() {
		return BE_DOC;
	}

	public void setBE_DOC(String bE_DOC) {
		BE_DOC = bE_DOC;
	}

	public String getINV_DOC() {
		return INV_DOC;
	}

	public void setINV_DOC(String iNV_DOC) {
		INV_DOC = iNV_DOC;
	}



	public String getCHL_DOC() {
		return CHL_DOC;
	}

	public void setCHL_DOC(String cHL_DOC) {
		CHL_DOC = cHL_DOC;
	}

	public MultipartFile getFilename1() {
		return filename1;
	}

	public void setFilename1(MultipartFile filename1) {
		this.filename1 = filename1;
	}

	public MultipartFile getFilename2() {
		return filename2;
	}

	public void setFilename2(MultipartFile filename2) {
		this.filename2 = filename2;
	}

	public MultipartFile getFilename3() {
		return filename3;
	}

	public void setFilename3(MultipartFile filename3) {
		this.filename3 = filename3;
	}


	@Transient
	private MultipartFile filename2;
	
	@Transient
	private MultipartFile filename3;
	
	public String getBE_NO() {
		return BE_NO;
	}

	public Long getId() {
		return id;
	}

	public float getTOT_DUTY_PAID() {
		return TOT_DUTY_PAID;
	}

	public void setTOT_DUTY_PAID(float tOT_DUTY_PAID) {
		TOT_DUTY_PAID = tOT_DUTY_PAID;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setBE_NO(String bE_NO) {
		BE_NO = bE_NO;
	}

	public Date getBE_DT() {
		return BE_DT;
	}

	public void setBE_DT(Date bE_DT) {
		BE_DT = bE_DT;
	}

	public String getIEC() {
		return IEC;
	}

	public void setIEC(String iEC) {
		IEC = iEC;
	}

	public String getGSTIN_ID() {
		return GSTIN_ID;
	}

	public void setGSTIN_ID(String gSTIN_ID) {
		GSTIN_ID = gSTIN_ID;
	}

	public String getADCODE() {
		return ADCODE;
	}

	public void setADCODE(String aDCODE) {
		ADCODE = aDCODE;
	}

	public String getSCHEME_CD() {
		return SCHEME_CD;
	}

	public void setSCHEME_CD(String sCHEME_CD) {
		SCHEME_CD = sCHEME_CD;
	}

	public String getLICENSE_NO() {
		return LICENSE_NO;
	}

	public void setLICENSE_NO(String lICENSE_NO) {
		LICENSE_NO = lICENSE_NO;
	}

	public String getPERMISSION_NO() {
		return PERMISSION_NO;
	}

	public void setPERMISSION_NO(String pERMISSION_NO) {
		PERMISSION_NO = pERMISSION_NO;
	}

	public String getEDI_SITE() {
		return EDI_SITE;
	}

	public void setEDI_SITE(String eDI_SITE) {
		EDI_SITE = eDI_SITE;
	}

	public String getCHALLAN_NO() {
		return CHALLAN_NO;
	}

	public void setCHALLAN_NO(String cHALLAN_NO) {
		CHALLAN_NO = cHALLAN_NO;
	}

	public Date getCHALLAN_DT() {
		return CHALLAN_DT;
	}

	public void setCHALLAN_DT(Date cHALLAN_DT) {
		CHALLAN_DT = cHALLAN_DT;
	}

	@Column(name = "IEC")
	private String IEC;
	
	@Column(name = "GSTIN_ID")
	private String GSTIN_ID;
	
	@Column(name = "ADCODE")
	private String ADCODE;
	
	@Column(name = "SCHEME_CD")
	private String SCHEME_CD;
	
	@Column(name = "LICENSE_NO")
	private String LICENSE_NO;
	
	@Column(name = "PERMISSION_NO")
	private String PERMISSION_NO;

	@Column(name = "EDI_SITE")
	private String EDI_SITE;

	@Column(name = "CHALLAN_NO")
	private String CHALLAN_NO;
	

	@Column(name = "CIN_NO")
	private String CIN_NO;
	
	
	@Column(name = "ITEM_ID")
	private String ITEM_ID;

	public String getCIN_NO() {
		return CIN_NO;
	}

	public void setCIN_NO(String cIN_NO) {
		CIN_NO = cIN_NO;
	}

	

	public Date getCIN_DT() {
		return CIN_DT;
	}

	public void setCIN_DT(Date cIN_DT) {
		CIN_DT = cIN_DT;
	}

	public String getITEM_ID() {
		return ITEM_ID;
	}

	public void setITEM_ID(String iTEM_ID) {
		ITEM_ID = iTEM_ID;
	}
	
	
	@Column(name = "ENTERED_BY")
	private String ENTERED_BY;
	
	@Column(name = "ENTERED_DT")
	private Date ENTERED_DT;
	


	public String getENTERED_BY() {
		return ENTERED_BY;
	}

	public void setENTERED_BY(String eNTERED_BY) {
		ENTERED_BY = eNTERED_BY;
	}

	public Date getENTERED_DT() {
		return ENTERED_DT;
	}

	public void setENTERED_DT(Date eNTERED_DT) {
		ENTERED_DT = eNTERED_DT;
	}

	
	
}
