package com.demo.fpo.bean;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MisReportQueryReplyStatus {

	@Id
	private  String item_id;
	private String cussite;
	private String stage;
	private String qry_type;
	private String Dcall_no;
	private String onetime_link;
	private String queryraiseddate;
	private String query_raised_by;
	private String reply_date;
	private String replied_by;
	private String time_taken_to_reply;
	private String emailid;
	private String mobileno;
	private String ip_address;
	public String getCussite() {
		return cussite;
	}
	public void setCussite(String cussite) {
		this.cussite = cussite;
	}

	public String getItem_id() {
		return item_id;
	}
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	public String getQry_type() {
		return qry_type;
	}
	public String getQueryraiseddate() {
		return queryraiseddate;
	}
	public void setQueryraiseddate(String queryraiseddate) {
		this.queryraiseddate = queryraiseddate;
	}
	public void setQry_type(String qry_type) {
		this.qry_type = qry_type;
	}
	public String getDcall_no() {
		return Dcall_no;
	}
	public void setDcall_no(String dcall_no) {
		Dcall_no = dcall_no;
	}
	public String getOnetime_link() {
		return onetime_link;
	}
	public void setOnetime_link(String onetime_link) {
		this.onetime_link = onetime_link;
	}
	public String getQuery_raised_by() {
		return query_raised_by;
	}
	public void setQuery_raised_by(String query_raised_by) {
		this.query_raised_by = query_raised_by;
	}
	public String getReply_date() {
		return reply_date;
	}
	public void setReply_date(String reply_date) {
		this.reply_date = reply_date;
	}
	public String getReplied_by() {
		return replied_by;
	}
	public void setReplied_by(String replied_by) {
		this.replied_by = replied_by;
	}
	public String getTime_taken_to_reply() {
		return time_taken_to_reply;
	}
	public void setTime_taken_to_reply(String time_taken_to_reply) {
		this.time_taken_to_reply = time_taken_to_reply;
	}
	
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public String getIp_address() {
		return ip_address;
	}
	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}
	
}
