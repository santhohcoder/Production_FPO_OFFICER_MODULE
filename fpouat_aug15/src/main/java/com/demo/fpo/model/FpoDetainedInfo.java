package com.demo.fpo.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "fpo_detained_info")
public class FpoDetainedInfo {

	@Id
	@GenericGenerator(name = "fpo_detained_info_seq", strategy = "com.seq.gen.FpoDetainedInfoSeqIdGenerator")
	@GeneratedValue(generator = "fpo_detained_info_seq")
	@Column(name = "id")
	public Long id;

	@Column(name = "article_id")
	private String articleId;

	@Column(name = "remarks")
	private String remarks;

	@Column(name = "role")
	private String role;

	@Column(name = "off_id")
	private String officerId;

	@Column(name = "cus_site")
	private String cussite;

	@Column(name = "detain_decision")
	private String detainDecision;

	@Column(name = "decision_reason")
	private String decisionReason;

	@Column(name = "detained_no")
	private Long detainedNo;

	@Column(name = "detain_completed")
	private String detainCompleted;

	@Column(name = "cur_off_role")
	private String currentOfficerRole;

	@Column(name = "detain_decision_dt")
	private Date detainDecisionDate;

	@Column(name = "detain_case")
	private String detainCase;

	@Column(name = "detain_case_other")
	private String detainCaseOther;

	@Column(name = "detain_method")
	private String detainMethod;

	@Column(name = "detain_method_other")
	private String detainMethodOther;

	@Transient
	private List<FpoDetainedCaseInfo> fpoDetainedCaseInfo;

	@Transient
	private List<FpoDetainedDocInfo> fpoDetainedDocInfo;

	@Transient
	private List<FpoFine> fpoFines;

	@Transient
	private List<FpoPenal> fpoPenalties;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public String getCussite() {
		return cussite;
	}

	public void setCussite(String cussite) {
		this.cussite = cussite;
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

	public Long getDetainedNo() {
		return detainedNo;
	}

	public void setDetainedNo(Long detainedNo) {
		this.detainedNo = detainedNo;
	}

	public String getDetainCompleted() {
		return detainCompleted;
	}

	public void setDetainCompleted(String detainCompleted) {
		this.detainCompleted = detainCompleted;
	}

	public String getCurrentOfficerRole() {
		return currentOfficerRole;
	}

	public void setCurrentOfficerRole(String currentOfficerRole) {
		this.currentOfficerRole = currentOfficerRole;
	}

	public List<FpoDetainedDocInfo> getFpoDetainedDocInfo() {
		return fpoDetainedDocInfo;
	}

	public void setFpoDetainedDocInfo(List<FpoDetainedDocInfo> fpoDetainedDocInfo) {
		this.fpoDetainedDocInfo = fpoDetainedDocInfo;
	}

	public Date getDetainDecisionDate() {
		return detainDecisionDate;
	}

	public void setDetainDecisionDate(Date detainDecisionDate) {
		this.detainDecisionDate = detainDecisionDate;
	}

	public List<FpoFine> getFpoFines() {
		return fpoFines;
	}

	public void setFpoFines(List<FpoFine> fpoFines) {
		this.fpoFines = fpoFines;
	}

	public List<FpoPenal> getFpoPenalties() {
		return fpoPenalties;
	}

	public void setFpoPenalties(List<FpoPenal> fpoPenalties) {
		this.fpoPenalties = fpoPenalties;
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

	public List<FpoDetainedCaseInfo> getFpoDetainedCaseInfo() {
		return fpoDetainedCaseInfo;
	}

	public void setFpoDetainedCaseInfo(List<FpoDetainedCaseInfo> fpoDetainedCaseInfo) {
		this.fpoDetainedCaseInfo = fpoDetainedCaseInfo;
	}
}
