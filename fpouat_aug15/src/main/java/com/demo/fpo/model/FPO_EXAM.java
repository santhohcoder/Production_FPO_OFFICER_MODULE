package com.demo.fpo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "FPO_EXAM_FINDINGS")
public class FPO_EXAM {


	@Id
	@GenericGenerator(name = "EXAM_SEQ", strategy = "com.seq.gen.EXAM_SEQIdGenerator")
	@GeneratedValue(generator = "EXAM_SEQ")
	@Column(name = "ID")
	public Long id;
	

	@Column(name = "CIN_NO")
	private String cinNo;

	public String getCinNo() {
		return cinNo;
	}

	public void setCinNo(String cinNo) {
		this.cinNo = cinNo;
	}

	
	@Column(name = "CUS_SITE")
	private String cus_site;

	public String getCus_site() {
		return cus_site;
	}

	public void setCus_site(String cus_site) {
		this.cus_site = cus_site;
	}

	public Long getItem_no() {
		return item_no;
	}

	public void setItem_no(Long item_no) {
		this.item_no = item_no;
	}

	@Column(name = "ITEM_ID")
	private String ITEM_ID;

	public String getITEM_ID() {
		return ITEM_ID;
	}

	public void setITEM_ID(String iTEM_ID) {
		ITEM_ID = iTEM_ID;
	}

	public String getRmks() {
		return rmks;
	}

	public void setRmks(String rmks) {
		this.rmks = rmks;
	}


	@Column(name = "ITEM_NO")
	private Long item_no;
	
	public String getItem_fou() {
		return item_fou;
	}


	@Column(name = "ITEM_FOU")
	private String item_fou;
	
	@Column(name = "OFF_ID")
	private String off_id;
	
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


	@Column(name = "ROLE")
	private String role;
	
	@Column(name = "INS_DT")
	private Date ins_dt;
	
	public Date getIns_dt() {
		return ins_dt;
	}

	public void setIns_dt(Date ins_dt) {
		this.ins_dt = ins_dt;
	}

	public void setItem_fou(String item_fou) {
		this.item_fou = item_fou;
	}


	@Column(name = "REMARKS")
	private String rmks;	
	
	@Column(name = "DETAIN")
	private String detain;	
	
	public String getDetain() {
		return detain;
	}

	public void setDetain(String detain) {
		this.detain = detain;
	}


	@Transient
	private String genrmks;

	public String getGenrmks() {
		return genrmks;
	}

	public void setGenrmks(String genrmks) {
		this.genrmks = genrmks;
	}
}
