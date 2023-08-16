package com.demo.fpo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "FPO_PRIORITY")
public class FPO_PRIORITY {

	@Id
	@GenericGenerator(name = "ORDER_SEQ", strategy = "com.seq.gen.priority_seqgen")
	@GeneratedValue(generator = "ORDER_SEQ")
	@Column(name = "ID")
	public Long id;

	@Column(name = "CIN_NO")
	private String cin_no;

	@Column(name = "ITEM_ID")
	private String item_id;

	@Column(name = "PRIORITY_DT")
	private Date priority_dt;

	@Column(name = "OFF_ID")
	private String off_id;

	@Column(name = "ROLE")
	private String role;

	@Column(name = "REASON")
	private String reason;

	@Column(name = "ARTCLE_STAGE")
	private String artclestg;

	@Column(name = "FINISH_DT")
	private Date finishdt;

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

	public Date getPriority_dt() {
		return priority_dt;
	}

	public void setPriority_dt(Date priority_dt) {
		this.priority_dt = priority_dt;
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

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getArtclestg() {
		return artclestg;
	}

	public void setArtclestg(String artclestg) {
		this.artclestg = artclestg;
	}

	public Date getFinishdt() {
		return finishdt;
	}

	public void setFinishdt(Date finishdt) {
		this.finishdt = finishdt;
	}
	
}
