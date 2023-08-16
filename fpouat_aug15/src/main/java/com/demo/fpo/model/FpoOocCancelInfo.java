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
@Table(name = "fpo_ooc_cancel_info")
public class FpoOocCancelInfo {

	@Id
	@GenericGenerator(name = "fpo_ooc_cancel_info_seq", strategy = "com.seq.gen.FpoOocCancelInfoSeqIdGenerator")
	@GeneratedValue(generator = "fpo_ooc_cancel_info_seq")
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

	@Column(name = "reason")
	private String reason;

	@Column(name = "ooc_cancel_no")
	private Long oocCancelNo;

	@Column(name = "cur_off_role")
	private String currentOfficerRole;

	@Column(name = "ooc_cancel_dt")
	private Date oocCancelDt;

	@Column(name = "ooc_cancel_completed")
	private String oocCancelCompleted;

	@Column(name = "ooc_cancel_status")
	private String oocCancelStatus;

	@Column(name = "ac_remarks")
	private String acRemarks;

	@Transient
	private List<FpoOocCancelDocInfo> fpoOocCancelDocInfo;

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

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Long getOocCancelNo() {
		return oocCancelNo;
	}

	public void setOocCancelNo(Long oocCancelNo) {
		this.oocCancelNo = oocCancelNo;
	}

	public String getCurrentOfficerRole() {
		return currentOfficerRole;
	}

	public void setCurrentOfficerRole(String currentOfficerRole) {
		this.currentOfficerRole = currentOfficerRole;
	}

	public Date getOocCancelDt() {
		return oocCancelDt;
	}

	public void setOocCancelDt(Date oocCancelDt) {
		this.oocCancelDt = oocCancelDt;
	}

	public String getOocCancelCompleted() {
		return oocCancelCompleted;
	}

	public void setOocCancelCompleted(String oocCancelCompleted) {
		this.oocCancelCompleted = oocCancelCompleted;
	}

	public String getOocCancelStatus() {
		return oocCancelStatus;
	}

	public void setOocCancelStatus(String oocCancelStatus) {
		this.oocCancelStatus = oocCancelStatus;
	}

	public String getAcRemarks() {
		return acRemarks;
	}

	public void setAcRemarks(String acRemarks) {
		this.acRemarks = acRemarks;
	}

	public List<FpoOocCancelDocInfo> getFpoOocCancelDocInfo() {
		return fpoOocCancelDocInfo;
	}

	public void setFpoOocCancelDocInfo(List<FpoOocCancelDocInfo> fpoOocCancelDocInfo) {
		this.fpoOocCancelDocInfo = fpoOocCancelDocInfo;
	}

}
