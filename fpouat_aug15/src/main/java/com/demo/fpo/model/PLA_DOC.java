package com.demo.fpo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class PLA_DOC {
	        
	/*DOC_PATH
	
	*/
	
	
	@Id
	//@GeneratedValue
	private String  DOC_NAME;
	private Date UPLOADED_DT;
	private String PLA_OFFID;
	private String UPLOADED_BY;
	private String DOC_PATH;
	
	@Transient
	private String fileAttachment;
	

	public String getDOC_NAME() {
		return DOC_NAME;
	}

	public void setDOC_NAME(String dOC_NAME) {
		DOC_NAME = dOC_NAME;
	}

	public Date getUPLOADED_DT() {
		return UPLOADED_DT;
	}

	public void setUPLOADED_DT(Date uPLOADED_DT) {
		UPLOADED_DT = uPLOADED_DT;
	}

	public String getPLA_OFFID() {
		return PLA_OFFID;
	}

	public void setPLA_OFFID(String pLA_OFFID) {
		PLA_OFFID = pLA_OFFID;
	}

	public String getUPLOADED_BY() {
		return UPLOADED_BY;
	}

	public void setUPLOADED_BY(String uPLOADED_BY) {
		UPLOADED_BY = uPLOADED_BY;
	}

	public String getDOC_PATH() {
		return DOC_PATH;
	}

	public void setDOC_PATH(String dOC_PATH) {
		DOC_PATH = dOC_PATH;
	}
	
	
	

	public String getFileAttachment() {
		return fileAttachment;
	}

	public void setFileAttachment(String fileAttachment) {
		this.fileAttachment = fileAttachment;
	}
	
/*	public void setDocumentPhysicalFile(String documentPhysicalFile) {
		this.documentPhysicalFile = documentPhysicalFile;
	} */
	
	
	

}
