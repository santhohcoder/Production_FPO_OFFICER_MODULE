package com.demo.fpo.bean;

import java.util.List;

public class DetainedArticleRemarkBean {

	private List<DetainedCaseBean> detainedCaseSections;
	private List<DetainedDocumentBean> documentSections;
	private List<AmountSectionBean> fineSections;
	private List<AmountSectionBean> penaltySections;
	private String detainRemarks;
	private String detainDecision;
	private String decisionReason;
	private String cinNo;
	private String detainCase;
	private String detainCaseOther;
	private String detainMethod;
	private String detainMethodOther;

	public List<DetainedCaseBean> getDetainedCaseSections() {
		return detainedCaseSections;
	}

	public void setDetainedCaseSections(List<DetainedCaseBean> detainedCaseSections) {
		this.detainedCaseSections = detainedCaseSections;
	}

	public List<DetainedDocumentBean> getDocumentSections() {
		return documentSections;
	}

	public void setDocumentSections(List<DetainedDocumentBean> documentSections) {
		this.documentSections = documentSections;
	}

	public List<AmountSectionBean> getFineSections() {
		return fineSections;
	}

	public void setFineSections(List<AmountSectionBean> fineSections) {
		this.fineSections = fineSections;
	}

	public List<AmountSectionBean> getPenaltySections() {
		return penaltySections;
	}

	public void setPenaltySections(List<AmountSectionBean> penaltySections) {
		this.penaltySections = penaltySections;
	}

	public String getDetainRemarks() {
		return detainRemarks;
	}

	public void setDetainRemarks(String detainRemarks) {
		this.detainRemarks = detainRemarks;
	}

	public String getDetainDecision() {
		return detainDecision;
	}

	public void setDetainDecision(String detainDecision) {
		this.detainDecision = detainDecision;
	}

	public String getDecisionReason() {
		return decisionReason;
	}

	public void setDecisionReason(String decisionReason) {
		this.decisionReason = decisionReason;
	}

	public String getCinNo() {
		return cinNo;
	}

	public void setCinNo(String cinNo) {
		this.cinNo = cinNo;
	}

	public String getDetainCase() {
		return detainCase;
	}

	public void setDetainCase(String detainCase) {
		this.detainCase = detainCase;
	}

	public String getDetainCaseOther() {
		return detainCaseOther;
	}

	public void setDetainCaseOther(String detainCaseOther) {
		this.detainCaseOther = detainCaseOther;
	}

	public String getDetainMethod() {
		return detainMethod;
	}

	public void setDetainMethod(String detainMethod) {
		this.detainMethod = detainMethod;
	}

	public String getDetainMethodOther() {
		return detainMethodOther;
	}

	public void setDetainMethodOther(String detainMethodOther) {
		this.detainMethodOther = detainMethodOther;
	}

}
