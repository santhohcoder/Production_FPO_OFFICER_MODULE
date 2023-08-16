package com.demo.fpo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "dcall_doc_info")
public class DCallDocInfo {
	@Id
	@GenericGenerator(name = "dcalldoc_seq", strategy = "com.seq.gen.DCallDocInfoSeqIdGenerator")
	@GeneratedValue(generator = "dcalldoc_seq")
	@Column(name = "id")
	public Long id;

	@Column(name = "dcall_no")
	private String dcallNumber;

	@Column(name = "kyc")
	private String isKyc;

	@Column(name = "doc_type")
	private String documentType;

	@Column(name = "doc_name")
	private String documentName;

	@Column(name = "doc_ref_no")
	private String documentReferenceNumber;

	@Column(name = "doc_desc")
	private String documentDescription;

	@Column(name = "article_stage")
	private String articleStage;

	@Column(name = "doc_phys_file")
	private String documentPhysicalFile;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDcallNumber() {
		return dcallNumber;
	}

	public void setDcallNumber(String dcallNumber) {
		this.dcallNumber = dcallNumber;
	}

	public String getIsKyc() {
		return isKyc;
	}

	public void setIsKyc(String isKyc) {
		this.isKyc = isKyc;
	}

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

	public String getDocumentReferenceNumber() {
		return documentReferenceNumber;
	}

	public void setDocumentReferenceNumber(String documentReferenceNumber) {
		this.documentReferenceNumber = documentReferenceNumber;
	}

	public String getDocumentDescription() {
		return documentDescription;
	}

	public void setDocumentDescription(String documentDescription) {
		this.documentDescription = documentDescription;
	}

	public String getDocumentPhysicalFile() {
		return documentPhysicalFile;
	}

	public void setDocumentPhysicalFile(String documentPhysicalFile) {
		this.documentPhysicalFile = documentPhysicalFile;
	}

	public String getArticleStage() {
		return articleStage;
	}

	public void setArticleStage(String articleStage) {
		this.articleStage = articleStage;
	}

}
