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
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "OPS$DIR.FPO_SITE_ALLOT")
public class FPO_SITE_ALLOT {
	
	@Id
	@GenericGenerator(name = "FPO_SITE_ALLOT", strategy = "com.seq.gen.FpoSiteAllotSeq")
	@GeneratedValue(generator = "FPO_SITE_ALLOT")
	@Column(name = "ID")
	public Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "OFF_ID")
	private String offid;
	
	@Column(name = "ROLE")
	private String role;
	
	@Column(name = "UPD_DATE")
	private Date updDate;
	
	@Column(name = "SITE_CODE")
	private String siteCode;
	
	@Column(name = "LETTER_AIR")
	private String letterAir;
	
	@Column(name = "LETTER_SAL")
	private String letterSal;
	
	@Column(name = "LETTER_SEA")
	private String letterSea;
	
	@Column(name = "EMS_AIR")
	private String emsAir;
	
	@Column(name = "EMS_SAL")
	private String emsSal;
	
	@Column(name = "EMS_SEA")
	private String emsSea;
	
	@Column(name = "PARCEL_AIR")
	private String parcelAir;
	
	@Column(name = "PARCEL_SAL")
	private String parcelSal;
	
	@Column(name = "PARCEL_SEA")
	private String parcelSea;
	
	@Column(name="DOC_NAME")
	private String Docname;
	
	@Column(name = "DOC_PATH")
	private String DOC_PATH;
	
	@Transient
	private MultipartFile filename;

	public String getOffid() {
		return offid;
	}

	public void setOffid(String offid) {
		this.offid = offid;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getUpdDate() {
		return updDate;
	}

	public void setUpdDate(Date date) {
		this.updDate = date;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public String getLetterAir() {
		return letterAir;
	}

	public void setLetterAir(String letterAir) {
		this.letterAir = letterAir;
	}

	public String getLetterSal() {
		return letterSal;
	}

	public void setLetterSal(String letterSal) {
		this.letterSal = letterSal;
	}

	public String getLetterSea() {
		return letterSea;
	}

	public void setLetterSea(String letterSea) {
		this.letterSea = letterSea;
	}

	public String getEmsAir() {
		return emsAir;
	}

	public void setEmsAir(String emsAir) {
		this.emsAir = emsAir;
	}

	public String getEmsSal() {
		return emsSal;
	}

	public void setEmsSal(String emsSal) {
		this.emsSal = emsSal;
	}

	public String getEmsSea() {
		return emsSea;
	}

	public void setEmsSea(String emsSea) {
		this.emsSea = emsSea;
	}

	public String getParcelAir() {
		return parcelAir;
	}

	public void setParcelAir(String parcelAir) {
		this.parcelAir = parcelAir;
	}

	public String getParcelSal() {
		return parcelSal;
	}

	public void setParcelSal(String parcelSal) {
		this.parcelSal = parcelSal;
	}

	public String getParcelSea() {
		return parcelSea;
	}

	public void setParcelSea(String parcelSea) {
		this.parcelSea = parcelSea;
	}	
	
	public String getDocname() {
		return Docname;
	}

	public void setDocname(String docname) {
		Docname = docname;
	}

	public MultipartFile getFilename() {
		return filename;
	}

	public void setFilename(MultipartFile filename) {
		this.filename = filename;
	}


	public String getDOC_PATH() {
		return DOC_PATH;
	}

	public void setDOC_PATH(String dOC_PATH) {
		DOC_PATH = dOC_PATH;
	}

	
}
