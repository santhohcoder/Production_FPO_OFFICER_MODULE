package com.demo.fpo.NsmLsmModel;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "OPS$DIR.EDIT_FPO_SITE")
public class EDIT_FPO_SITE {
	
	@Id
	@GenericGenerator(name = "EDIT_SITE_SEQ", strategy = "com.seq.gen.FPO_SITE_EDITseq")
	@GeneratedValue(generator = "EDIT_SITE_SEQ")
	@Column(name = "SITE_ID")
	public Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "SITE_CODE")
	private String siteCode;

	@Column(name = "SITE_TYPE")
	private String siteType;
	
	@Column(name = "SITE_NAME")
	private String siteName;

	@Column(name = "SITE_STATE")
	private String siteState;

	@Column(name = "SITE_STATUS")
	private String sitests;
	
	@Column(name = "OFF_ID")
	private String offid;
	
	@Column(name = "ENTRY_DT")
	private Date createddate;
	
	@Column(name = "SITE_STATE_CD")
	private String siteStateCd;

	@Column(name = "REASON")
	private String reason;
	
	@Column(name = "CLUSTERED")
	private String clustered;
	
	@Column(name = "CLUSSITE")
	private String clussite;
	
	public String getClustered() {
		return clustered;
	}

	public void setClustered(String clustered) {
		this.clustered = clustered;
	}

	public String getClussite() {
		return clussite;
	}

	public void setClussite(String clussite) {
		this.clussite = clussite;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public String getSiteType() {
		return siteType;
	}

	public void setSiteType(String siteType) {
		this.siteType = siteType;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getSiteState() {
		return siteState;
	}

	public void setSiteState(String siteState) {
		this.siteState = siteState;
	}

	public String getSitests() {
		return sitests;
	}

	public void setSitests(String sitests) {
		this.sitests = sitests;
	}

	public String getOffid() {
		return offid;
	}

	public void setOffid(String offid) {
		this.offid = offid;
	}

	public Date getCreateddate() {
		return createddate;
	}

	public void setCreateddate(Date date) {
		this.createddate = date;
	}

	public String getSiteStateCd() {
		return siteStateCd;
	}

	public void setSiteStateCd(String siteStateCd) {
		this.siteStateCd = siteStateCd;
	}
	
}
