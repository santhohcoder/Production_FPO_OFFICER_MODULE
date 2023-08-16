package com.demo.fpo.apibean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FPO_DOC_DET")
public class FpoDocDetBean {
	@Column(name = "ITEM_ID")
	private String itemId;

	@Column(name = "MESG_TYP")
	private String messageType;

	@Column(name = "POSTINGDT")
	private String postingDate;

	@Column(name = "JOB_NO")
	private Long jobNo;

	@Column(name = "JOB_DT")
	private Date jobDate;

	@Column(name = "DOCSLNO")
	private Long docSlNo;

	@Column(name = "DOC_ID")
	private String docId;

	@Column(name = "DOC_NAME")
	private String docName;

	@Column(name = "DOC_TYPE")
	private String docType;

	@Id
	@Column(name = "DOCUNIQUE_ID")
	private String docUniqueId;

	@Column(name = "CIN_NO")
	private String cinNo;

	@Column(name = "CIN_DT")
	private Date cinDate;

	@Column(name = "CUS_SITE")
	private String cussite;

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getPostingDate() {
		return postingDate;
	}

	public void setPostingDate(String postingDate) {
		this.postingDate = postingDate;
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

	public Long getDocSlNo() {
		return docSlNo;
	}

	public void setDocSlNo(Long docSlNo) {
		this.docSlNo = docSlNo;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getDocUniqueId() {
		return docUniqueId;
	}

	public void setDocUniqueId(String docUniqueId) {
		this.docUniqueId = docUniqueId;
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

	public String getCussite() {
		return cussite;
	}

	public void setCussite(String cussite) {
		this.cussite = cussite;
	}

}
