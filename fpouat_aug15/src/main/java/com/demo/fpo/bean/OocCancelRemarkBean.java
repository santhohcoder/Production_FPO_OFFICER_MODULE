package com.demo.fpo.bean;

import java.util.List;

public class OocCancelRemarkBean {
	private List<DetainedDocumentBean> documentSections;
	private String oocCancelRemarks;
	private String oocCancelReason;
	private String cinNo;

	public List<DetainedDocumentBean> getDocumentSections() {
		return documentSections;
	}

	public void setDocumentSections(List<DetainedDocumentBean> documentSections) {
		this.documentSections = documentSections;
	}

	public String getOocCancelRemarks() {
		return oocCancelRemarks;
	}

	public void setOocCancelRemarks(String oocCancelRemarks) {
		this.oocCancelRemarks = oocCancelRemarks;
	}

	public String getOocCancelReason() {
		return oocCancelReason;
	}

	public void setOocCancelReason(String oocCancelReason) {
		this.oocCancelReason = oocCancelReason;
	}

	public String getCinNo() {
		return cinNo;
	}

	public void setCinNo(String cinNo) {
		this.cinNo = cinNo;
	}

}
