package com.demo.fpo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "PUSH_DCALL")
public class Push_dcall {
	
	@Id
	@GenericGenerator(name = "push_dcall_id", strategy = "com.seq.gen.PushDcallIdGenerator")
	@GeneratedValue(generator = "push_dcall_id")
	@Column(name = "UNIQUE_ID")
	public Long id;
	
	@Column(name = "ITEM_ID")
	public String item_id;
	
	@Column(name = "CIN_NO")
	public String cin_no;
	
	@Column(name = "CIN_DT")
	public Date cin_dt;
	
	@Column(name = "MOBILE_NO_1")
	public String mobile_no_1;
	
	@Column(name = "MOBILE_NO_2")
	public String mobile_no_2;
	
	@Column(name = "EMAIL_ID_1")
	public String email_id_1;
	
	@Column(name = "EMAIL_ID_2")
	public String email_id_2;
	
	@Column(name = "GEN_DT")
	public Date gen_dt;
	
	@Column(name = "OFF_ID")
	public String off_id;
	
	@Column(name = "ROLE")
	public String role;
	
	@Column(name = "CUS_SITE")
	public String cus_site;
	
	@Column(name = "DCALL_NO")
	private String dcallno;
	
	
	

	public String getDcallno() {
		return dcallno;
	}

	public void setDcallno(String dcallno) {
		this.dcallno = dcallno;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItem_id() {
		return item_id;
	}

	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}

	public String getCin_no() {
		return cin_no;
	}

	public void setCin_no(String cin_no) {
		this.cin_no = cin_no;
	}

	public Date getCin_dt() {
		return cin_dt;
	}

	public void setCin_dt(Date cin_dt) {
		this.cin_dt = cin_dt;
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

	public String getEmail_id_1() {
		return email_id_1;
	}

	public void setEmail_id_1(String email_id_1) {
		this.email_id_1 = email_id_1;
	}

	public String getEmail_id_2() {
		return email_id_2;
	}

	public void setEmail_id_2(String email_id_2) {
		this.email_id_2 = email_id_2;
	}

	public Date getGen_dt() {
		return gen_dt;
	}

	public void setGen_dt(Date gen_dt) {
		this.gen_dt = gen_dt;
	}

	public String getOff_id() {
		return off_id;
	}

	public void setOff_id(String off_id) {
		this.off_id = off_id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getCus_site() {
		return cus_site;
	}

	public void setCus_site(String cus_site) {
		this.cus_site = cus_site;
	}
	
	
	
	
	
}
