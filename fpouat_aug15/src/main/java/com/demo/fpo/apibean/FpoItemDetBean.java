package com.demo.fpo.apibean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FPO_ITEM_DET")
public class FpoItemDetBean {
	@Id
	@Column(name = "ITEM_UNIQUE")
	private String itemUnique;

	@Column(name = "CIN_NO")
	private String cinNo;

	@Column(name = "CIN_DT")
	private Date cinDate;

	@Column(name = "ITEM_ID")
	private String itemId;

	@Column(name = "CUS_SITE")
	private String cussite;

	@Column(name = "POSTINGDT")
	private String postingDate;

	@Column(name = "MESG_TYPE_CD")
	private String messageTypeCode;

	@Column(name = "JOB_NO")
	private Long jobNo;

	@Column(name = "JOB_DT")
	private Date jobDate;

	@Column(name = "ITEM_NO")
	private Long itemNo;

	@Column(name = "NOU")
	private Long noOfUnits;

	@Column(name = "NETWT")
	private Float netWeight;

	@Column(name = "DECL_VAL")
	private Float declaredValue;

	@Column(name = "CURRCD")
	private String currencyCode;

	@Column(name = "ITEM_DESC")
	private String itemDescription;

	@Column(name = "CTH")
	private String cth;

	@Column(name = "ORIGCNTRYCD")
	private String originCountryCode;

	@Column(name = "ITEM_REVISEDDESC")
	private String itemRevisedDescription;

	@Column(name = "CTH_REVISED")
	private String cthRevised;

	@Column(name = "GEN_CTH")
	private String genCth;

	@Column(name = "DUTY")
	private Float duty;

	@Column(name = "DUTY_FG")
	private Float dutyFG;

	@Column(name = "BCD_NOTN")
	private String bcdNotn;

	@Column(name = "BCD_NSNO")
	private String bcdNsno;

	@Column(name = "BCD_RTA")
	private Float bcdRate;

	@Column(name = "BCD_AMT")
	private Float bcdAmount;

	@Column(name = "BCD_AMT_FG")
	private Float bcdAmountFG;

	@Column(name = "IGST_NOTN")
	private String igstNotn;

	@Column(name = "IGST_NSNO")
	private String igstNsno;

	@Column(name = "IGST_RTA")
	private Float igstRate;

	@Column(name = "IGST_AMT")
	private Float igstamount;

	@Column(name = "IGST_AMT_FG")
	private Float igstAmountFG;

	@Column(name = "SW_NOTN")
	private String swNotn;

	@Column(name = "SW_NSNO")
	private String swNsno;

	@Column(name = "SW_RTA")
	private Float swRate;

	@Column(name = "SW_AMT")
	private Float swAmount;

	@Column(name = "SW_AMT_FG")
	private Float swAmountFG;

	@Column(name = "UNIQUE_ID")
	private String uniqueId;

	@Column(name = "CURR_RATE")
	private Float currRate;

//	@Column(name = "FREIGHT_VAL")
//	private Long FreightValue;
//
//	@Column(name = "INSURED_VAL")
//	private Long InsuredValue;

	@Column(name = "MODIFIED")
	private String Modified;

/*	@Column(name = "AMEND_SERIAL_NO")
	private Long amendSerialNumber;

	@Column(name = "AMEND_DT")
	private Date amendDate;

	@Column(name = "AMEND_FLAG")
	private String amendFlag;

	@Column(name = "ROLE")
	private String role;

	@Column(name = "OFF_ID")
	private String officerId;*/

	@Column(name = "ASSESS_VAL")
	private String assessValue;

/*	@Column(name = "ASS_DT")
	private Date assessDate;

	@Column(name = "UPDASS_DT")
	private Date updateDate;*/

	@Column(name = "ASSVAL_INSFRT")
	private Float assessValueInsfrt;

/*	@Column(name = "UPD_CIF")
	private String updateCIF;*/

/*	@Column(name = "RMS_SENT_DT")
	private Date rmsSentDate;*/

	public String getItemUnique() {
		return itemUnique;
	}

	public void setItemUnique(String itemUnique) {
		this.itemUnique = itemUnique;
	}

	public String getCinNo() {
		return cinNo;
	}

	public void setCinNo(String cinNo) {
		this.cinNo = cinNo;
	}

	public Date getCinDate() {
		return cinDate;
	}

	public void setCinDate(Date cinDate) {
		this.cinDate = cinDate;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getCussite() {
		return cussite;
	}

	public void setCussite(String cussite) {
		this.cussite = cussite;
	}

	public String getPostingDate() {
		return postingDate;
	}

	public void setPostingDate(String postingDate) {
		this.postingDate = postingDate;
	}

	public String getMessageTypeCode() {
		return messageTypeCode;
	}

	public void setMessageTypeCode(String messageTypeCode) {
		this.messageTypeCode = messageTypeCode;
	}

	public Long getJobNo() {
		return jobNo;
	}

	public void setJobNo(Long jobNo) {
		this.jobNo = jobNo;
	}

	public Date getJobDate() {
		return jobDate;
	}

	public void setJobDate(Date jobDate) {
		this.jobDate = jobDate;
	}

	public Long getItemNo() {
		return itemNo;
	}

	public void setItemNo(Long itemNo) {
		this.itemNo = itemNo;
	}

	public Long getNoOfUnits() {
		return noOfUnits;
	}

	public void setNoOfUnits(Long noOfUnits) {
		this.noOfUnits = noOfUnits;
	}

	public Float getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(Float netWeight) {
		this.netWeight = netWeight;
	}

	public Float getDeclaredValue() {
		return declaredValue;
	}

	public void setDeclaredValue(Float declaredValue) {
		this.declaredValue = declaredValue;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public String getCth() {
		return cth;
	}

	public void setCth(String cth) {
		this.cth = cth;
	}

	public String getOriginCountryCode() {
		return originCountryCode;
	}

	public void setOriginCountryCode(String originCountryCode) {
		this.originCountryCode = originCountryCode;
	}

	public String getItemRevisedDescription() {
		return itemRevisedDescription;
	}

	public void setItemRevisedDescription(String itemRevisedDescription) {
		this.itemRevisedDescription = itemRevisedDescription;
	}

	public String getCthRevised() {
		return cthRevised;
	}

	public void setCthRevised(String cthRevised) {
		this.cthRevised = cthRevised;
	}

	public String getGenCth() {
		return genCth;
	}

	public void setGenCth(String genCth) {
		this.genCth = genCth;
	}

	public Float getDuty() {
		return duty;
	}

	public void setDuty(Float duty) {
		this.duty = duty;
	}

	public Float getDutyFG() {
		return dutyFG;
	}

	public void setDutyFG(Float dutyFG) {
		this.dutyFG = dutyFG;
	}

	public String getBcdNotn() {
		return bcdNotn;
	}

	public void setBcdNotn(String bcdNotn) {
		this.bcdNotn = bcdNotn;
	}

	public String getBcdNsno() {
		return bcdNsno;
	}

	public void setBcdNsno(String bcdNsno) {
		this.bcdNsno = bcdNsno;
	}

	public Float getBcdRate() {
		return bcdRate;
	}

	public void setBcdRate(Float bcdRate) {
		this.bcdRate = bcdRate;
	}

	public Float getBcdAmount() {
		return bcdAmount;
	}

	public void setBcdAmount(Float bcdAmount) {
		this.bcdAmount = bcdAmount;
	}

	public Float getBcdAmountFG() {
		return bcdAmountFG;
	}

	public void setBcdAmountFG(Float bcdAmountFG) {
		this.bcdAmountFG = bcdAmountFG;
	}

	public String getIgstNotn() {
		return igstNotn;
	}

	public void setIgstNotn(String igstNotn) {
		this.igstNotn = igstNotn;
	}

	public String getIgstNsno() {
		return igstNsno;
	}

	public void setIgstNsno(String igstNsno) {
		this.igstNsno = igstNsno;
	}

	public Float getIgstRate() {
		return igstRate;
	}

	public void setIgstRate(Float igstRate) {
		this.igstRate = igstRate;
	}

	public Float getIgstamount() {
		return igstamount;
	}

	public void setIgstamount(Float igstamount) {
		this.igstamount = igstamount;
	}

	public Float getIgstAmountFG() {
		return igstAmountFG;
	}

	public void setIgstAmountFG(Float igstAmountFG) {
		this.igstAmountFG = igstAmountFG;
	}

	public String getSwNotn() {
		return swNotn;
	}

	public void setSwNotn(String swNotn) {
		this.swNotn = swNotn;
	}

	public String getSwNsno() {
		return swNsno;
	}

	public void setSwNsno(String swNsno) {
		this.swNsno = swNsno;
	}

	public Float getSwRate() {
		return swRate;
	}

	public void setSwRate(Float swRate) {
		this.swRate = swRate;
	}

	public Float getSwAmount() {
		return swAmount;
	}

	public void setSwAmount(Float swAmount) {
		this.swAmount = swAmount;
	}

	public Float getSwAmountFG() {
		return swAmountFG;
	}

	public void setSwAmountFG(Float swAmountFG) {
		this.swAmountFG = swAmountFG;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public Float getCurrRate() {
		return currRate;
	}

	public void setCurrRate(Float currRate) {
		this.currRate = currRate;
	}

//	public Long getFreightValue() {
//		return FreightValue;
//	}
//
//	public void setFreightValue(Long freightValue) {
//		FreightValue = freightValue;
//	}
//
//	public Long getInsuredValue() {
//		return InsuredValue;
//	}
//
//	public void setInsuredValue(Long insuredValue) {
//		InsuredValue = insuredValue;
//	}

	public String getModified() {
		return Modified;
	}

	public void setModified(String modified) {
		Modified = modified;
	}

/*	public Long getAmendSerialNumber() {
		return amendSerialNumber;
	}

	public void setAmendSerialNumber(Long amendSerialNumber) {
		this.amendSerialNumber = amendSerialNumber;
	}

	public Date getAmendDate() {
		return amendDate;
	}

	public void setAmendDate(Date amendDate) {
		this.amendDate = amendDate;
	}

	public String getAmendFlag() {
		return amendFlag;
	}

	public void setAmendFlag(String amendFlag) {
		this.amendFlag = amendFlag;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getOfficerId() {
		return officerId;
	}

	public void setOfficerId(String officerId) {
		this.officerId = officerId;
	}*/

	public String getAssessValue() {
		return assessValue;
	}

	public void setAssessValue(String assessValue) {
		this.assessValue = assessValue;
	}

/*	public Date getAssessDate() {
		return assessDate;
	}

	public void setAssessDate(Date assessDate) {
		this.assessDate = assessDate;
	}*/

/*	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}*/

	public Float getAssessValueInsfrt() {
		return assessValueInsfrt;
	}

	public void setAssessValueInsfrt(Float assessValueInsfrt) {
		this.assessValueInsfrt = assessValueInsfrt;
	}

	/*public String getUpdateCIF() {
		return updateCIF;
	}

	public void setUpdateCIF(String updateCIF) {
		this.updateCIF = updateCIF;
	}*/

	/*public Date getRmsSentDate() {
		return rmsSentDate;
	}

	public void setRmsSentDate(Date rmsSentDate) {
		this.rmsSentDate = rmsSentDate;
	}*/

}
