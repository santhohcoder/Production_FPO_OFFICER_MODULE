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
@Table(name = "OPS$DIR.FPO_SITES")
public class FPO_SITE {
	
	@Id
	@GenericGenerator(name = "FPO_SITE", strategy = "com.seq.gen.FPO_SITE_seq")
	@GeneratedValue(generator = "FPO_SITE")
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

	
	
	@Column(name = "MAP_CODE")
	private String mapcode;
	
	@Column(name = "ACTIVATED_DT")
	private Date activedt;
	
	@Column(name = "CREATED_DT")
	private Date crtdt;
	
	@Column(name = "CREATED_BY")
	private String crtby;
	
	
	@Column(name = "ACTIVATED_BY")
	private String activeby;
	
	
	@Column(name = "CLUSTERED")
	private String clustered;
	
	@Column(name = "CLUSSITE")
	private String clussite;
	
	/*
	 * @Column(name = "OFF_NAME") private String offName;
	 */
	

	public Date getActivedt() {
		return activedt;
	}

	public void setActivedt(Date activedt) {
		this.activedt = activedt;
	}

	public Date getCrtdt() {
		return crtdt;
	}

	public void setCrtdt(Date crtdt) {
		this.crtdt = crtdt;
	}

	public String getCrtby() {
		return crtby;
	}

	public void setCrtby(String crtby) {
		this.crtby = crtby;
	}

	public String getActiveby() {
		return activeby;
	}

	public void setActiveby(String activeby) {
		this.activeby = activeby;
	}

	public String getClussite() {
		return clussite;
	}

	public void setClussite(String clussite) {
		this.clussite = clussite;
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

	
	public String getMapcode() {
		return mapcode;
	}

	public void setMapcode(String mapcode) {
		this.mapcode = mapcode;
	}

	

	public String getClustered() {
		return clustered;
	}

	public void setClustered(String clustered) {
		this.clustered = clustered;
	}

	
}
