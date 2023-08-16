package com.demo.fpo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "fpo_scan_info")
public class FpoScanInfo {
	@Id
	@GenericGenerator(name = "fpo_scan_info_seq", strategy = "com.seq.gen.FpoScanInfoSeqIdGenerator")
	@GeneratedValue(generator = "fpo_scan_info_seq")
	@Column(name = "id")
	public Long id;

	@Column(name = "bag_no")
	private String bagNo;

	@Column(name = "bag_type")
	private String bagType;

	@Column(name = "scan_dt")
	private Date scanDate;

	@Column(name = "off_id")
	private String officerId;

	@Column(name = "role")
	private String role;

	@Column(name = "cus_site")
	private String cusSite;

	@Column(name = "scan_report")
	private String scanReport;

	@Column(name = "article_id")
	private String articleId;

	@Column(name = "scanned")
	private String scanned;
	
	@Column(name = "remarks")
	private String remarks;
	
	@Column(name = "OOE")
	private String OOE;

	public String getOOE() {
		return OOE;
	}

	public void setOOE(String oOE) {
		OOE = oOE;
	}

	@Transient
	private String site;

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBagNo() {
		return bagNo;
	}

	public void setBagNo(String bagNo) {
		this.bagNo = bagNo;
	}

	public String getBagType() {
		return bagType;
	}

	public void setBagType(String bagType) {
		this.bagType = bagType;
	}

	public Date getScanDate() {
		return scanDate;
	}

	public void setScanDate(Date scanDate) {
		this.scanDate = scanDate;
	}

	public String getOfficerId() {
		return officerId;
	}

	public void setOfficerId(String officerId) {
		this.officerId = officerId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getCusSite() {
		return cusSite;
	}

	public void setCusSite(String cusSite) {
		this.cusSite = cusSite;
	}

	public String getScanReport() {
		return scanReport;
	}

	public void setScanReport(String scanReport) {
		this.scanReport = scanReport;
	}

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public String getScanned() {
		return scanned;
	}

	public void setScanned(String scanned) {
		this.scanned = scanned;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

}
