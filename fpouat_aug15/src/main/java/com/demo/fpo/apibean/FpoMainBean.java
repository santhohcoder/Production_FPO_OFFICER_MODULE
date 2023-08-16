package com.demo.fpo.apibean;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "FPO_MAIN")
public class FpoMainBean {
	@Id
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

//	@Column(name = "MAIL_PID")
//	private String mailPID;
//
//	@Column(name = "DECLARATION_PID")
//	private String declarationPID;

	@Column(name = "CUST_ORG_CD")
	private String cusOrgCode;

	@Column(name = "POST_ORG_CD")
	private String postOrgCode;

	@Column(name = "DOCUMENTS")
	private String documents;

	@Column(name = "GROSS_WT")
	private Float grossWeight;

	@Column(name = "MEASURED_GROSS_WT")
	private Float measuredGrossWeight;

	@Column(name = "HANDLING_CLASS_CD")
	private String handlingClassCode;

	@Column(name = "TOT_DECL_VAL")
	private Float totalDeclaredValue;

	@Column(name = "TOT_ASS_VAL")
	private Float totalAssessedValue;

	@Column(name = "TOT_DUTY")
	private Float totalDuty;

	@Column(name = "INS_VAL")
	private Float insValue;

	@Column(name = "INS_VAL_CURRCD")
	private String insValueCurrencyCode;

	@Column(name = "NATURE_TYPE_CD")
	private String natureTypeCode;

	@Column(name = "POSTAGE_AMT")
	private Float postageAmount;

	@Column(name = "POSTAGE_CURR_CD")
	private String postageCurrencyCode;

	@Column(name = "MODIFIED")
	private String modified;

	@Column(name = "RECP_ADD1")
	private String recipientAddress1;

	@Column(name = "RECP_ADD2")
	private String recipientAddress2;

	@Column(name = "RECP_CITY")
	private String recipientCity;

	@Column(name = "RECP_CNTRY_CD")
	private String recipientCountryCode;

	@Column(name = "RECP_EMAILID")
	private String recipientEmailId;

	@Column(name = "RECP_FAX")
	private String recipientFax;

	@Column(name = "RECP_FNAME")
	private String recipientFirstName;

	@Column(name = "RECP_IDREF")
	private String recipientIdReference;

	@Column(name = "RECP_LNAME")
	private String recipientLastName;

	@Column(name = "RECP_NAME")
	private String recipientName;

	@Column(name = "RECP_STATE")
	private String recipientState;

	@Column(name = "RECP_PHONE")
	private String recipientPhone;

	@Column(name = "RECP_ZIP")
	private String recipientZip;

	@Column(name = "SEND_ADD1")
	private String senderAddress1;

	@Column(name = "SEND_ADD2")
	private String senderAddress2;

	@Column(name = "SEND_CITY")
	private String senderCity;

	@Column(name = "SEND_CNTRY_CD")
	private String senderCountryCode;

	@Column(name = "SEND_EMAILID")
	private String senderEmailId;

	@Column(name = "SEND_FNAME")
	private String senderFirstName;

	@Column(name = "SEND_IDREF")
	private String senderIdReference;

	@Column(name = "SEND_LNAME")
	private String senderLastName;

	@Column(name = "SEND_NAME")
	private String senderName;

	@Column(name = "SEND_STATE")
	private String senderState;

	@Column(name = "SEND_PHONE")
	private String senderPhone;

	@Column(name = "SEND_ZIP")
	private String senderZip;

	@Column(name = "SEND_FAX")
	private String senderFax;

	@Column(name = "TRANS_DATE")
	private Date TransDate;

	@Column(name = "TRANS_MODE")
	private String TransMode;

	@Column(name = "DESTPOST_ORG_CD")
	private String DestinationPostalOrganisationCode;

//	@Column(name = "LOCALID")
//	private String localId;
//
//	@Column(name = "LOCALID2")
//	private String localId2;

	@Column(name = "MAIL_CATEGORY_CD")
	private String mailCategoryCode;

	@Column(name = "MAIL_CLASS_CD")
	private String mailClassCode;

	@Column(name = "MAIL_STATE_CD")
	private String mailStateCode;

	@Column(name = "MAIL_STATE_REMARKS")
	private String mailStateRemarks;

	@Column(name = "ORIGPOST_ORG_CD")
	private String originPostalOrganisationCode;

	@Column(name = "UNIQUE_ID")
	private String uniqueId;

	@Column(name = "TYPE_CD")
	private String typeCode;

	@Column(name = "FPO_CODE")
	private String fpoCode;

	@Column(name = "PINCODE")
	private String pincode;

	/*@Column(name = "AMEND_SERIAL_NO")
	private Long amendSerialNumber;

	@Column(name = "AMEND_DT")
	private Date amendDate;

	@Column(name = "AMEND_FLAG")
	private String amendFlag;

	@Column(name = "ROLE")
	private String role;

	@Column(name = "OFF_ID")
	private String officerId;

	@Column(name = "FPOMAIN_NO")
	private String fpoMainNumber;

	@Column(name = "INIT_QUE")
	private String initQueue;*/

	@Column(name = "TOT_AMT_PAYABLE")
	private Float totalAmountPayable;
	
	@Column(name = "TOT_DUTY_FG")
	private Float totalDutyFG;

/*	@Column(name = "TOT_FINE")
	private Float totalFine;

	@Column(name = "TOT_PENAL")
	private Float totalPenalty;

	@Column(name = "DEP_CMTS")
	private String depComments;

	@Column(name = "ASS_DT")
	private Date assessDate;

	@Column(name = "ACL_OFFID")
	private String aclOfficerId;

	@Column(name = "SET_ASIDE")
	private String setaside;

	@Column(name = "PRIORITY")
	private String priority;

	@Column(name = "ARR_INDIA")
	private String arrivalIndia;

	@Column(name = "ARR_FPO")
	private String arrivalFPO;

	@Column(name = "DELIVERY")
	private String delivery;*/

	public Float getTotalDutyFG() {
		return totalDutyFG;
	}

	public void setTotalDutyFG(Float totalDutyFG) {
		this.totalDutyFG = totalDutyFG;
	}

	@Column(name = "INS_CALC_VAL")
	private Float insCalcValue;

	@Column(name = "FRT_CALC_VAL")
	private Float freightCalcValue;

	@Column(name = "TOTASS_CALC_VAL")
	private Float totalAssCalcValue;

	@Column(name = "INS_CURR_RATE")
	private Float insCurrencyRate;

	@Column(name = "FRT_CURR_RATE")
	private Float frightCurrencyRate;

	/*@Column(name = "UPD_CIF")
	private String updateCIF;

	@Column(name = "RMS_SENT_DT")
	private Date rmsSentDate;*/

	@Transient
	private List<FpoItemDetBean> itemWiseDetails;

	@Transient
	private List<FpoDocDetBean> docDetails;	

	public List<FpoDocDetBean> getDocDetails() {
		return docDetails;
	}

	public void setDocDetails(List<FpoDocDetBean> docDetails) {
		this.docDetails = docDetails;
	}

	public List<FpoItemDetBean> getItemWiseDetails() {
		return itemWiseDetails;
	}

	public void setItemWiseDetails(List<FpoItemDetBean> itemWiseDetails) {
		this.itemWiseDetails = itemWiseDetails;
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

//	public String getMailPID() {
//		return mailPID;
//	}
//
//	public void setMailPID(String mailPID) {
//		this.mailPID = mailPID;
//	}
//
//	public String getDeclarationPID() {
//		return declarationPID;
//	}
//
//	public void setDeclarationPID(String declarationPID) {
//		this.declarationPID = declarationPID;
//	}

	public String getCusOrgCode() {
		return cusOrgCode;
	}

	public void setCusOrgCode(String cusOrgCode) {
		this.cusOrgCode = cusOrgCode;
	}

	public String getPostOrgCode() {
		return postOrgCode;
	}

	public void setPostOrgCode(String postOrgCode) {
		this.postOrgCode = postOrgCode;
	}

	public String getDocuments() {
		return documents;
	}

	public void setDocuments(String documents) {
		this.documents = documents;
	}

	public Float getGrossWeight() {
		return grossWeight;
	}

	public void setGrossWeight(Float grossWeight) {
		this.grossWeight = grossWeight;
	}

	public Float getMeasuredGrossWeight() {
		return measuredGrossWeight;
	}

	public void setMeasuredGrossWeight(Float measuredGrossWeight) {
		this.measuredGrossWeight = measuredGrossWeight;
	}

	public String getHandlingClassCode() {
		return handlingClassCode;
	}

	public void setHandlingClassCode(String handlingClassCode) {
		this.handlingClassCode = handlingClassCode;
	}

	public Float getTotalDeclaredValue() {
		return totalDeclaredValue;
	}

	public void setTotalDeclaredValue(Float totalDeclaredValue) {
		this.totalDeclaredValue = totalDeclaredValue;
	}

	public Float getTotalAssessedValue() {
		return totalAssessedValue;
	}

	public void setTotalAssessedValue(Float totalAssessedValue) {
		this.totalAssessedValue = totalAssessedValue;
	}

	public Float getTotalDuty() {
		return totalDuty;
	}

	public void setTotalDuty(Float totalDuty) {
		this.totalDuty = totalDuty;
	}

	public Float getInsValue() {
		return insValue;
	}

	public void setInsValue(Float insValue) {
		this.insValue = insValue;
	}

	public String getInsValueCurrencyCode() {
		return insValueCurrencyCode;
	}

	public void setInsValueCurrencyCode(String insValueCurrencyCode) {
		this.insValueCurrencyCode = insValueCurrencyCode;
	}

	public String getNatureTypeCode() {
		return natureTypeCode;
	}

	public void setNatureTypeCode(String natureTypeCode) {
		this.natureTypeCode = natureTypeCode;
	}

	public Float getPostageAmount() {
		return postageAmount;
	}

	public void setPostageAmount(Float postageAmount) {
		this.postageAmount = postageAmount;
	}

	public String getPostageCurrencyCode() {
		return postageCurrencyCode;
	}

	public void setPostageCurrencyCode(String postageCurrencyCode) {
		this.postageCurrencyCode = postageCurrencyCode;
	}

	public String getModified() {
		return modified;
	}

	public void setModified(String modified) {
		this.modified = modified;
	}

	public String getRecipientAddress1() {
		return recipientAddress1;
	}

	public void setRecipientAddress1(String recipientAddress1) {
		this.recipientAddress1 = recipientAddress1;
	}

	public String getRecipientAddress2() {
		return recipientAddress2;
	}

	public void setRecipientAddress2(String recipientAddress2) {
		this.recipientAddress2 = recipientAddress2;
	}

	public String getRecipientCity() {
		return recipientCity;
	}

	public void setRecipientCity(String recipientCity) {
		this.recipientCity = recipientCity;
	}

	public String getRecipientCountryCode() {
		return recipientCountryCode;
	}

	public void setRecipientCountryCode(String recipientCountryCode) {
		this.recipientCountryCode = recipientCountryCode;
	}

	public String getRecipientEmailId() {
		return recipientEmailId;
	}

	public void setRecipientEmailId(String recipientEmailId) {
		this.recipientEmailId = recipientEmailId;
	}

	public String getRecipientFax() {
		return recipientFax;
	}

	public void setRecipientFax(String recipientFax) {
		this.recipientFax = recipientFax;
	}

	public String getRecipientFirstName() {
		return recipientFirstName;
	}

	public void setRecipientFirstName(String recipientFirstName) {
		this.recipientFirstName = recipientFirstName;
	}

	public String getRecipientIdReference() {
		return recipientIdReference;
	}

	public void setRecipientIdReference(String recipientIdReference) {
		this.recipientIdReference = recipientIdReference;
	}

	public String getRecipientLastName() {
		return recipientLastName;
	}

	public void setRecipientLastName(String recipientLastName) {
		this.recipientLastName = recipientLastName;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getRecipientState() {
		return recipientState;
	}

	public void setRecipientState(String recipientState) {
		this.recipientState = recipientState;
	}

	public String getRecipientPhone() {
		return recipientPhone;
	}

	public void setRecipientPhone(String recipientPhone) {
		this.recipientPhone = recipientPhone;
	}

	public String getRecipientZip() {
		return recipientZip;
	}

	public void setRecipientZip(String recipientZip) {
		this.recipientZip = recipientZip;
	}

	public String getSenderAddress1() {
		return senderAddress1;
	}

	public void setSenderAddress1(String senderAddress1) {
		this.senderAddress1 = senderAddress1;
	}

	public String getSenderAddress2() {
		return senderAddress2;
	}

	public void setSenderAddress2(String senderAddress2) {
		this.senderAddress2 = senderAddress2;
	}

	public String getSenderCity() {
		return senderCity;
	}

	public void setSenderCity(String senderCity) {
		this.senderCity = senderCity;
	}

	public String getSenderCountryCode() {
		return senderCountryCode;
	}

	public void setSenderCountryCode(String senderCountryCode) {
		this.senderCountryCode = senderCountryCode;
	}

	public String getSenderEmailId() {
		return senderEmailId;
	}

	public void setSenderEmailId(String senderEmailId) {
		this.senderEmailId = senderEmailId;
	}

	public String getSenderFirstName() {
		return senderFirstName;
	}

	public void setSenderFirstName(String senderFirstName) {
		this.senderFirstName = senderFirstName;
	}

	public String getSenderIdReference() {
		return senderIdReference;
	}

	public void setSenderIdReference(String senderIdReference) {
		this.senderIdReference = senderIdReference;
	}

	public String getSenderLastName() {
		return senderLastName;
	}

	public void setSenderLastName(String senderLastName) {
		this.senderLastName = senderLastName;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getSenderState() {
		return senderState;
	}

	public void setSenderState(String senderState) {
		this.senderState = senderState;
	}

	public String getSenderPhone() {
		return senderPhone;
	}

	public void setSenderPhone(String senderPhone) {
		this.senderPhone = senderPhone;
	}

	public String getSenderZip() {
		return senderZip;
	}

	public void setSenderZip(String senderZip) {
		this.senderZip = senderZip;
	}

	public String getSenderFax() {
		return senderFax;
	}

	public void setSenderFax(String senderFax) {
		this.senderFax = senderFax;
	}

	public Date getTransDate() {
		return TransDate;
	}

	public void setTransDate(Date transDate) {
		TransDate = transDate;
	}

	public String getTransMode() {
		return TransMode;
	}

	public void setTransMode(String transMode) {
		TransMode = transMode;
	}

	public String getDestinationPostalOrganisationCode() {
		return DestinationPostalOrganisationCode;
	}

	public void setDestinationPostalOrganisationCode(String destinationPostalOrganisationCode) {
		DestinationPostalOrganisationCode = destinationPostalOrganisationCode;
	}

//	public String getLocalId() {
//		return localId;
//	}
//
//	public void setLocalId(String localId) {
//		this.localId = localId;
//	}
//
//	public String getLocalId2() {
//		return localId2;
//	}
//
//	public void setLocalId2(String localId2) {
//		this.localId2 = localId2;
//	}

	public String getMailCategoryCode() {
		return mailCategoryCode;
	}

	public void setMailCategoryCode(String mailCategoryCode) {
		this.mailCategoryCode = mailCategoryCode;
	}

	public String getMailClassCode() {
		return mailClassCode;
	}

	public void setMailClassCode(String mailClassCode) {
		this.mailClassCode = mailClassCode;
	}

	public String getMailStateCode() {
		return mailStateCode;
	}

	public void setMailStateCode(String mailStateCode) {
		this.mailStateCode = mailStateCode;
	}

	public String getMailStateRemarks() {
		return mailStateRemarks;
	}

	public void setMailStateRemarks(String mailStateRemarks) {
		this.mailStateRemarks = mailStateRemarks;
	}

	public String getOriginPostalOrganisationCode() {
		return originPostalOrganisationCode;
	}

	public void setOriginPostalOrganisationCode(String originPostalOrganisationCode) {
		this.originPostalOrganisationCode = originPostalOrganisationCode;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getFpoCode() {
		return fpoCode;
	}

	public void setFpoCode(String fpoCode) {
		this.fpoCode = fpoCode;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
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
	}

	public String getFpoMainNumber() {
		return fpoMainNumber;
	}

	public void setFpoMainNumber(String fpoMainNumber) {
		this.fpoMainNumber = fpoMainNumber;
	}

	public Float getTotalDutyFG() {
		return totalDutyFG;
	}

	public void setTotalDutyFG(Float totalDutyFG) {
		this.totalDutyFG = totalDutyFG;
	}

	public String getInitQueue() {
		return initQueue;
	}

	public void setInitQueue(String initQueue) {
		this.initQueue = initQueue;
	}
*/
	public Float getTotalAmountPayable() {
		return totalAmountPayable;
	}

	public void setTotalAmountPayable(Float totalAmountPayable) {
		this.totalAmountPayable = totalAmountPayable;
	}

/*	public Float getTotalFine() {
		return totalFine;
	}

	public void setTotalFine(Float totalFine) {
		this.totalFine = totalFine;
	}

	public Float getTotalPenalty() {
		return totalPenalty;
	}

	public void setTotalPenalty(Float totalPenalty) {
		this.totalPenalty = totalPenalty;
	}

	public String getDepComments() {
		return depComments;
	}

	public void setDepComments(String depComments) {
		this.depComments = depComments;
	}

	public Date getAssessDate() {
		return assessDate;
	}

	public void setAssessDate(Date assessDate) {
		this.assessDate = assessDate;
	}

	public String getAclOfficerId() {
		return aclOfficerId;
	}

	public void setAclOfficerId(String aclOfficerId) {
		this.aclOfficerId = aclOfficerId;
	}

	public String getSetaside() {
		return setaside;
	}

	public void setSetaside(String setaside) {
		this.setaside = setaside;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getArrivalIndia() {
		return arrivalIndia;
	}

	public void setArrivalIndia(String arrivalIndia) {
		this.arrivalIndia = arrivalIndia;
	}

	public String getArrivalFPO() {
		return arrivalFPO;
	}

	public void setArrivalFPO(String arrivalFPO) {
		this.arrivalFPO = arrivalFPO;
	}

	public String getDelivery() {
		return delivery;
	}

	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}*/

	public Float getInsCalcValue() {
		return insCalcValue;
	}

	public void setInsCalcValue(Float insCalcValue) {
		this.insCalcValue = insCalcValue;
	}

	public Float getFreightCalcValue() {
		return freightCalcValue;
	}

	public void setFreightCalcValue(Float freightCalcValue) {
		this.freightCalcValue = freightCalcValue;
	}

	public Float getTotalAssCalcValue() {
		return totalAssCalcValue;
	}

	public void setTotalAssCalcValue(Float totalAssCalcValue) {
		this.totalAssCalcValue = totalAssCalcValue;
	}

	public Float getInsCurrencyRate() {
		return insCurrencyRate;
	}

	public void setInsCurrencyRate(Float insCurrencyRate) {
		this.insCurrencyRate = insCurrencyRate;
	}

	public Float getFrightCurrencyRate() {
		return frightCurrencyRate;
	}

	public void setFrightCurrencyRate(Float frightCurrencyRate) {
		this.frightCurrencyRate = frightCurrencyRate;
	}

/*	public String getUpdateCIF() {
		return updateCIF;
	}

	public void setUpdateCIF(String updateCIF) {
		this.updateCIF = updateCIF;
	}

	public Date getRmsSentDate() {
		return rmsSentDate;
	}

	public void setRmsSentDate(Date rmsSentDate) {
		this.rmsSentDate = rmsSentDate;
	}*/

}
