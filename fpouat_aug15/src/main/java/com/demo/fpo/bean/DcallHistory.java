package com.demo.fpo.bean;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DcallHistory {
	
	@Id
	private Long id;
	public String getSpeedpost_dt() {
		return speedpost_dt;
	}
	public void setSpeedpost_dt(String speedpost_dt) {
		this.speedpost_dt = speedpost_dt;
	}
	private String cin_no;
	private String item_id;
	private String cus_site;
	private String dcall_no;
	private String genurl;
	private String recp_name;
	private String gen_dt;
	private String gen_filename;
	private String post_dt;
	private String origin_country;
	private String mail_class;
	private String mail_category;
	private String full_path;
	private String din1;
	private String mail_cc;
	private String mail_to;
	private String mobile_no_1;
	private String mobile_no_2;

	private Integer viewcou;
	private Integer printcou;
	private Integer smscou;
	private Integer emailcou;
	private String qryname;
	private String ooc;
	private String speedpost_no;
	private String speedpost_dt;
	private String speedpost_deli_status;
	private String recp_add1;
	private String rlystatus;
	private String rly_dt;
	private String addl_rlystatus;
	private String addl_rly_dt;
	
	
	
	
	
	
	public String getRlystatus() {
		return rlystatus;
	}
	public void setRlystatus(String rlystatus) {
		this.rlystatus = rlystatus;
	}
	public String getRly_dt() {
		return rly_dt;
	}
	public void setRly_dt(String rly_dt) {
		this.rly_dt = rly_dt;
	}
	public String getAddl_rlystatus() {
		return addl_rlystatus;
	}
	public void setAddl_rlystatus(String addl_rlystatus) {
		this.addl_rlystatus = addl_rlystatus;
	}
	public String getAddl_rly_dt() {
		return addl_rly_dt;
	}
	public void setAddl_rly_dt(String addl_rly_dt) {
		this.addl_rly_dt = addl_rly_dt;
	}
	public String getRecp_add1() {
		return recp_add1;
	}
	public void setRecp_add1(String recp_add1) {
		this.recp_add1 = recp_add1;
	}
	public String getSpeedpost_deli_status() {
		return speedpost_deli_status;
	}
	public void setSpeedpost_deli_status(String speedpost_deli_status) {
		this.speedpost_deli_status = speedpost_deli_status;
	}
	public String getSpeedpost_no() {
		return speedpost_no;
	}
	public void setSpeedpost_no(String speedpost_no) {
		this.speedpost_no = speedpost_no;
	}
	public String getOoc() {
		return ooc;
	}
	public void setOoc(String ooc) {
		this.ooc = ooc;
	}
	
	
	
	
	
	public String getQryname() {
		return qryname;
	}
	public void setQryname(String qryname) {
		this.qryname = qryname;
	}
	public Integer getViewcou() {
		return viewcou;
	}
	public void setViewcou(Integer viewcou) {
		this.viewcou = viewcou;
	}
	public Integer getPrintcou() {
		return printcou;
	}
	public void setPrintcou(Integer printcou) {
		this.printcou = printcou;
	}
	public Integer getSmscou() {
		return smscou;
	}
	public void setSmscou(Integer smscou) {
		this.smscou = smscou;
	}
	public Integer getEmailcou() {
		return emailcou;
	}
	public void setEmailcou(Integer emailcou) {
		this.emailcou = emailcou;
	}
	public String getMail_cc() {
		return mail_cc;
	}
	public void setMail_cc(String mail_cc) {
		this.mail_cc = mail_cc;
	}
	public String getMail_to() {
		return mail_to;
	}
	public void setMail_to(String mail_to) {
		this.mail_to = mail_to;
	}
	public String getMobile_no_1() {
		return mobile_no_1;
	}
	public void setMobile_no_1(String mobile_no_1) {
		this.mobile_no_1 = mobile_no_1;
	}
	public String getMobile_no_2() {
		return mobile_no_2;
	}
	public void setMobile_no_2(String mobile_no_2) {
		this.mobile_no_2 = mobile_no_2;
	}
	public String getDin1() {
		return din1;
	}
	public void setDin1(String din1) {
		this.din1 = din1;
	}
	public String getFull_path() {
		return full_path;
	}
	public void setFull_path(String full_path) {
		this.full_path = full_path;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCin_no() {
		return cin_no;
	}
	public void setCin_no(String cin_no) {
		this.cin_no = cin_no;
	}
	public String getItem_id() {
		return item_id;
	}
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	public String getCus_site() {
		return cus_site;
	}
	public void setCus_site(String cus_site) {
		this.cus_site = cus_site;
	}
	public String getDcall_no() {
		return dcall_no;
	}
	public void setDcall_no(String dcall_no) {
		this.dcall_no = dcall_no;
	}
	public String getGenurl() {
		return genurl;
	}
	public void setGenurl(String genurl) {
		this.genurl = genurl;
	}
	public String getRecp_name() {
		return recp_name;
	}
	public void setRecp_name(String recp_name) {
		this.recp_name = recp_name;
	}
	public String getGen_dt() {
		return gen_dt;
	}
	public void setGen_dt(String gen_dt) {
		this.gen_dt = gen_dt;
	}
	public String getGen_filename() {
		return gen_filename;
	}
	public void setGen_filename(String gen_filename) {
		this.gen_filename = gen_filename;
	}
	public String getPost_dt() {
		return post_dt;
	}
	public void setPost_dt(String post_dt) {
		this.post_dt = post_dt;
	}
	public String getOrigin_country() {
		return origin_country;
	}
	public void setOrigin_country(String origin_country) {
		this.origin_country = origin_country;
	}
	public String getMail_class() {
		return mail_class;
	}
	public void setMail_class(String mail_class) {
		this.mail_class = mail_class;
	}
	public String getMail_category() {
		return mail_category;
	}
	public void setMail_category(String mail_category) {
		this.mail_category = mail_category;
	}
	
	
	
	
	
	
	
	

}
