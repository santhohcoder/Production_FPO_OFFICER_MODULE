package com.demo.fpo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "FPO_RECALL")
public class FPO_RE_CALL {
	
	@Id
	@GenericGenerator(name = "FPO_RE_CALL_SEQ", strategy = "com.seq.gen.Fpo_Re_Call_Seqgen")
	@GeneratedValue(generator = "FPO_RE_CALL_SEQ")
	@Column(name = "ID")
	public Long id;
	
	@Column(name = "CIN_NO")
	private String cinNO;
	
	@Column(name = "ITEM_ID")
	private String item_id;

	@Column(name = "RE_CALL_DT")
	private Date recall_dt;

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
	
	@Column(name = "CUS_SITE")
	private String cusSite;

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

	public Date getRecall_dt() {
		return recall_dt;
	}

	public void setRecall_dt(Date recall_dt) {
		this.recall_dt = recall_dt;
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

	public String getCusSite() {
		return cusSite;
	}

	public void setCusSite(String cusSite) {
		this.cusSite = cusSite;
	}

	public String getCinNO() {
		return cinNO;
	}

	public void setCinNO(String cinNO) {
		this.cinNO = cinNO;
	}

	
	
}
