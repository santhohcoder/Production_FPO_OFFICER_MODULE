package com.demo.fpo.NsmLsmModel;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "OPS$DIR.FPO_SITE_MGMT")
public class FPO_SITE_MGMT {
	
	@Id
	@GenericGenerator(name = "FPO_SITE_SEQ", strategy = "com.seq.gen.FPO_SITE_ADDseq")
	@GeneratedValue(generator = "FPO_SITE_SEQ")
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
	
	/*
	 * @Column(name = "OFF_NAME") private String offName;
	 */
	
	@Column(name = "ENTRY_DT")
	private Date createddate;

	
	@Column(name = "SITE_STATE_CD")
	private String siteStateCd;

	@Column(name = "REASON")
	private String reason;
	
	@Transient
	private String clustered;
	
	@Transient
	private String mapcode;
	
	public String getClustered() {
		return clustered;
	}

	public String getMapcode() {
		return mapcode;
	}

	public void setMapcode(String mapcode) {
		this.mapcode = mapcode;
	}

	public void setClustered(String clustered) {
		this.clustered = clustered;
	}
	
	public String getReason() {
		return reason;
	}
	
	@Transient
	private String clussite;
	
	

	public String getClussite() {
		return clussite;
	}

	public void setClussite(String clussite) {
		this.clussite = clussite;
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

	/*
	 * public String getOffName() { return offName; }
	 * 
	 * public void setOffName(String offName) { this.offName = offName; }
	 */
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
