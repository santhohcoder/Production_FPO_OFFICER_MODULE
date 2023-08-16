package com.demo.fpo.NsmLsmModel;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "OPS$DIR.OTH_STATUS_FPOSITE")
public class OTH_STATUS_FPOSITE {

	@Id
	@GenericGenerator(name = "OTH_STS_SEQ", strategy = "com.seq.gen.OTH_STS_SEQseq")
	@GeneratedValue(generator = "OTH_STS_SEQ")
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

	@Column(name = "SITE_STATUS")
	private String sitests;

	@Column(name = "OFF_ID")
	private String offId;
	
	/*
	 * @Column(name = "OFF_NAME") private String offName;
	 */
	@Column(name = "CRT_DT")
	private Date crtdt;

	@Column(name = "REASON")
	private String reason;

	@Column(name = "SITE_NAME")
	private String sitename;

	public String getSitename() {
		return sitename;
	}

	public void setSitename(String sitename) {
		this.sitename = sitename;
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
	
	public String getSitests() {
		return sitests;
	}

	public void setSitests(String c) {
		this.sitests = c;
	}

	public String getOffId() {
		return offId;
	}

	public void setOffId(String offId) {
		this.offId = offId;
	}

	public Date getCrtdt() {
		return crtdt;
	}

	public void setCrtdt(Date crtdt) {
		this.crtdt = crtdt;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
