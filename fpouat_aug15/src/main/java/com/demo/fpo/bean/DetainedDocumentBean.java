package com.demo.fpo.bean;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

public class DetainedDocumentBean {
	private String documentType;
	private String documentName;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date documentDate;
	private MultipartFile documentFile;

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public Date getDocumentDate() {
		return documentDate;
	}

	public void setDocumentDate(Date documentDate) {
		this.documentDate = documentDate;
	}

	public MultipartFile getDocumentFile() {
		return documentFile;
	}

	public void setDocumentFile(MultipartFile documentFile) {
		this.documentFile = documentFile;
	}

}
