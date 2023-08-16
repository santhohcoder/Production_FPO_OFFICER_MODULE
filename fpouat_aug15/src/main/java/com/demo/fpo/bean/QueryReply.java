package com.demo.fpo.bean;

import java.util.List;

import com.demo.fpo.model.FpoQuery;

public class QueryReply {
	private List<DocumentSection> kycSections;
	private List<DocumentSection> supportSections;
	private List<FpoQuery> itemQueryResponse;
	private FpoQuery defaultQueryResponse;
	private String generalQueryResponse;
	private String dcallLetterNumber;
	private String articleId;
	private String currentUrl;
	
	
	
	private String replydt;
	private String ent_name;
	private String mailid;
	private String mobileno;
	
	

	public String getReplydt() {
		return replydt;
	}

	public void setReplydt(String replydt) {
		this.replydt = replydt;
	}

	public String getEnt_name() {
		return ent_name;
	}

	public void setEnt_name(String ent_name) {
		this.ent_name = ent_name;
	}

	public String getMailid() {
		return mailid;
	}

	public void setMailid(String mailid) {
		this.mailid = mailid;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	
	
	
	
	
	public List<DocumentSection> getKycSections() {
		return kycSections;
	}

	public void setKycSections(List<DocumentSection> kycSections) {
		this.kycSections = kycSections;
	}

	public List<DocumentSection> getSupportSections() {
		return supportSections;
	}

	public void setSupportSections(List<DocumentSection> supportSections) {
		this.supportSections = supportSections;
	}

	public List<FpoQuery> getItemQueryResponse() {
		return itemQueryResponse;
	}

	public void setItemQueryResponse(List<FpoQuery> itemQueryResponse) {
		this.itemQueryResponse = itemQueryResponse;
	}

	public FpoQuery getDefaultQueryResponse() {
		return defaultQueryResponse;
	}

	public void setDefaultQueryResponse(FpoQuery defaultQueryResponse) {
		this.defaultQueryResponse = defaultQueryResponse;
	}

	public String getGeneralQueryResponse() {
		return generalQueryResponse;
	}

	public void setGeneralQueryResponse(String generalQueryResponse) {
		this.generalQueryResponse = generalQueryResponse;
	}

	public String getDcallLetterNumber() {
		return dcallLetterNumber;
	}

	public void setDcallLetterNumber(String dcallLetterNumber) {
		this.dcallLetterNumber = dcallLetterNumber;
	}

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public String getCurrentUrl() {
		return currentUrl;
	}

	public void setCurrentUrl(String currentUrl) {
		this.currentUrl = currentUrl;
	}

}
