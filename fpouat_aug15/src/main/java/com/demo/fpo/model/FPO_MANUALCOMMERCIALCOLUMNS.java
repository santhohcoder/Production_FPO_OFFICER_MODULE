package com.demo.fpo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class FPO_MANUALCOMMERCIALCOLUMNS {

	@Id
	private String id;
	
	private String beNO;
	
	private String beDT;
	
	private String cinDt;
	
	private String challanDT;
	
	private String IEC;
	
	private String gstINid;
	
	private String ADCODE;
	
	private String schemeCd;
	
	private String licenseNo;
	
	private String permissionNo;
	
	private String ediSite;
	
	private String challanNo;
	
	private String cinNo;
	
	private String ITEM_ID;
	
	private String enteredBy;
	
	private String EnteredDt;
	
	private String empName;
	
	@Transient
	private String currentStatus;
	
	private String bedoc;
	
	private String invdoc;
	
	private String challandoc;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBeNO() {
		return beNO;
	}

	public void setBeNO(String beNO) {
		this.beNO = beNO;
	}

	public String getBeDT() {
		return beDT;
	}

	public void setBeDT(String beDT) {
		this.beDT = beDT;
	}

	public String getCinDt() {
		return cinDt;
	}

	public void setCinDt(String cinDt) {
		this.cinDt = cinDt;
	}

	public String getChallanDT() {
		return challanDT;
	}

	public void setChallanDT(String challanDT) {
		this.challanDT = challanDT;
	}

	public String getIEC() {
		return IEC;
	}

	public void setIEC(String iEC) {
		IEC = iEC;
	}

	public String getGstINid() {
		return gstINid;
	}

	public void setGstINid(String gstINid) {
		this.gstINid = gstINid;
	}

	public String getADCODE() {
		return ADCODE;
	}

	public void setADCODE(String aDCODE) {
		ADCODE = aDCODE;
	}

	public String getSchemeCd() {
		return schemeCd;
	}

	public void setSchemeCd(String schemeCd) {
		this.schemeCd = schemeCd;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public String getPermissionNo() {
		return permissionNo;
	}

	public void setPermissionNo(String permissionNo) {
		this.permissionNo = permissionNo;
	}

	public String getEdiSite() {
		return ediSite;
	}

	public void setEdiSite(String ediSite) {
		this.ediSite = ediSite;
	}

	public String getChallanNo() {
		return challanNo;
	}

	public void setChallanNo(String challanNo) {
		this.challanNo = challanNo;
	}

	public String getCinNo() {
		return cinNo;
	}

	public void setCinNo(String cinNo) {
		this.cinNo = cinNo;
	}

	public String getITEM_ID() {
		return ITEM_ID;
	}

	public void setITEM_ID(String iTEM_ID) {
		ITEM_ID = iTEM_ID;
	}

	public String getEnteredBy() {
		return enteredBy;
	}

	public void setEnteredBy(String enteredBy) {
		this.enteredBy = enteredBy;
	}

	public String getEnteredDt() {
		return EnteredDt;
	}

	public void setEnteredDt(String enteredDt) {
		EnteredDt = enteredDt;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public String getBedoc() {
		return bedoc;
	}

	public void setBedoc(String bedoc) {
		this.bedoc = bedoc;
	}

	public String getInvdoc() {
		return invdoc;
	}

	public void setInvdoc(String invdoc) {
		this.invdoc = invdoc;
	}

	public String getChallandoc() {
		return challandoc;
	}

	public void setChallandoc(String challandoc) {
		this.challandoc = challandoc;
	}
	
	
}
