package com.demo.fpo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "fpo_ooc_cancel_doc_info")
public class FpoOocCancelDocInfo {

	@Id
	@GenericGenerator(name = "fpo_ooc_cancel_doc_info_seq", strategy = "com.seq.gen.FpoOocCancelDocInfoSeqIdGenerator")
	@GeneratedValue(generator = "fpo_ooc_cancel_doc_info_seq")
	@Column(name = "id")
	public Long id;

	@Column(name = "article_id")
	private String articleId;

	@Column(name = "doc_type")
	private String documentType;

	@Column(name = "doc_type_other_val")
	private String documentTypeOtherValue;

	@Column(name = "doc_date")
	private Date documentDate;

	@Column(name = "doc_name")
	private String documentName;

	@Column(name = "doc_phys_file")
	private String documentPhysicalFile;

	@Column(name = "ooc_cancel_no")
	private Long oocCancelNo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getDocumentTypeOtherValue() {
		return documentTypeOtherValue;
	}

	public void setDocumentTypeOtherValue(String documentTypeOtherValue) {
		this.documentTypeOtherValue = documentTypeOtherValue;
	}

	public Date getDocumentDate() {
		return documentDate;
	}

	public void setDocumentDate(Date documentDate) {
		this.documentDate = documentDate;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getDocumentPhysicalFile() {
		return documentPhysicalFile;
	}

	public void setDocumentPhysicalFile(String documentPhysicalFile) {
		this.documentPhysicalFile = documentPhysicalFile;
	}

	public Long getOocCancelNo() {
		return oocCancelNo;
	}

	public void setOocCancelNo(Long oocCancelNo) {
		this.oocCancelNo = oocCancelNo;
	}

}
