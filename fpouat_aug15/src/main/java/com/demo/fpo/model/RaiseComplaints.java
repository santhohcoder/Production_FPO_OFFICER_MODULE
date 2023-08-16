package com.demo.fpo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="raise_complnt")
public class RaiseComplaints {

	@Id
	
	  @GenericGenerator(name = "RAISE_COMP_ID", strategy ="com.seq.gen.RAISE_COMP_seq")
	  
	  @GeneratedValue(generator = "RAISE_COMP_ID")
	 
	
//	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	public Long id;
	
	public Long getId() {
		return id;
	}
	

	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="OFF_ID")
	private String OFF_ID;
	
	@Column(name="ROLE_NAME")
	private String ROLE_NAME;
	
	@Column(name="RAISE_DT")
	private Date RAISE_DT;
	
	@Column(name="MOD_NAME")
	private String MOD_NAME;

	@Column(name="EMAIL_ID")
	private String EMAIL_ID;
	
	@Column(name="MOBILE_NO")
	private String MOBILE_NO;
	
	@Column(name="COMPL_TXT")
	private String COMPL_TXT;
	
	@Column(name="FILE_NAME")
	private String FILE_NAME;
	
	
	
	@Transient
	private MultipartFile filename;

	public MultipartFile getFilename() {
		return filename;
	}

	public void setFilename(MultipartFile filename) {
		this.filename = filename;
	}
	
	public String getOFF_ID() {
		return OFF_ID;
	}
	public void setOFF_ID(String oFF_ID) {
		OFF_ID = oFF_ID;
	}
	public String getROLE_NAME() {
		return ROLE_NAME;
	}
	public void setROLE_NAME(String rOLE_NAME) {
		ROLE_NAME = rOLE_NAME;
	}
	public Date getRAISE_DT() {
		return RAISE_DT;
	}
	public void setRAISE_DT(Date rAISE_DT) {
		RAISE_DT = rAISE_DT;
	}
	public String getMOD_NAME() {
		return MOD_NAME;
	}
	public void setMOD_NAME(String mOD_NAME) {
		MOD_NAME = mOD_NAME;
	}
	public String getEMAIL_ID() {
		return EMAIL_ID;
	}
	public void setEMAIL_ID(String eMAIL_ID) {
		EMAIL_ID = eMAIL_ID;
	}
	public String getMOBILE_NO() {
		return MOBILE_NO;
	}
	public void setMOBILE_NO(String mOBILE_NO) {
		MOBILE_NO = mOBILE_NO;
	}
	public String getCOMPL_TXT() {
		return COMPL_TXT;
	}
	public void setCOMPL_TXT(String cOMPL_TXT) {
		COMPL_TXT = cOMPL_TXT;
	}
	public String getFILE_NAME() {
		return FILE_NAME;
	}
	public void setFILE_NAME(String fILE_NAME) {
		FILE_NAME = fILE_NAME;
	}
	
	
}
