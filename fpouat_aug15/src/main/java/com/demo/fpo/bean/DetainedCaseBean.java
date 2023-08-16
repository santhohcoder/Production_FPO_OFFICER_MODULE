package com.demo.fpo.bean;

import org.springframework.web.multipart.MultipartFile;

public class DetainedCaseBean {
	private String suspectedItem;
	private String quantity;
	private String uqc;
	private String value;
	private MultipartFile detentionFile;

	public String getSuspectedItem() {
		return suspectedItem;
	}

	public void setSuspectedItem(String suspectedItem) {
		this.suspectedItem = suspectedItem;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
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

	public MultipartFile getDetentionFile() {
		return detentionFile;
	}

	public void setDetentionFile(MultipartFile detentionFile) {
		this.detentionFile = detentionFile;
	}

}
