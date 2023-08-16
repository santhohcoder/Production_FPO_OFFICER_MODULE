package com.demo.fpo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "fpo_detained_case_info")
public class FpoDetainedCaseInfo {

	@Id
	@GenericGenerator(name = "fpo_detained_case_info_seq", strategy = "com.seq.gen.FpoDetainedCaseInfoSeqIdGenerator")
	@GeneratedValue(generator = "fpo_detained_case_info_seq")
	@Column(name = "id")
	public Long id;

	@Column(name = "article_id")
	private String articleId;

	@Column(name = "item_detail")
	private String itemDetail;

	@Column(name = "quantity")
	private Long quantity;

	@Column(name = "uqc")
	private String uqc;

	@Column(name = "value")
	private String value;

	@Column(name = "photo_name")
	private String photoName;

	@Column(name = "photo_phys_file")
	private String photoPhysicalFile;

	@Column(name = "detained_no")
	private Long detainedNo;

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

	public String getItemDetail() {
		return itemDetail;
	}

	public void setItemDetail(String itemDetail) {
		this.itemDetail = itemDetail;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public String getUqc() {
		return uqc;
	}

	public void setUqc(String uqc) {
		this.uqc = uqc;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public String getPhotoPhysicalFile() {
		return photoPhysicalFile;
	}

	public void setPhotoPhysicalFile(String photoPhysicalFile) {
		this.photoPhysicalFile = photoPhysicalFile;
	}

	public Long getDetainedNo() {
		return detainedNo;
	}

	public void setDetainedNo(Long detainedNo) {
		this.detainedNo = detainedNo;
	}

}
