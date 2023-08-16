package com.demo.fpo.model;

import java.util.List;

public class FPOSecondDefQry {
	
	private String cinNo;
	private String itemId;
	private List<Reply> reply;
	
	private String query;
	private String dinNo;
	private String email;
	private String mobile;
	private String qrytype;
	
	
	
	
	
	
	
	
	public String getQrytype() {
		return qrytype;
	}
	public void setQrytype(String qrytype) {
		this.qrytype = qrytype;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getDinNo() {
		return dinNo;
	}
	public void setDinNo(String dinNo) {
		this.dinNo = dinNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCinNo() {
		return cinNo;
	}
	public void setCinNo(String cinNo) {
		this.cinNo = cinNo;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public List<Reply> getReply() {
		return reply;
	}
	public void setReply(List<Reply> reply) {
		this.reply = reply;
	}
	
	
	
	
}
