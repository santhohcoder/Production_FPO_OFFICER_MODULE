package com.demo.fpo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "FPO_ASS_PAO_CMTS")
public class FPO_ASS_PAO_CMTS {
	
	
	@Id
	@GenericGenerator(name = "ASS_PAO_CMTS_SEQ", strategy = "com.seq.gen.ASS_PAO_CMTS_SEQIdGenerator")
	@GeneratedValue(generator = "ASS_PAO_CMTS_SEQ")
	@Column(name = "ID")
	public Long id;
	
	@Column(name = "CIN_NO")
	private String cin_NO;
	
	@Column(name = "SEQ_NO")
	private int seq_NO;
	
	@Column(name = "ROLE")
	private String role;
	
	@Column(name = "OFF_ID")
	private String off_ID;
	
	@Column(name = "CMTS")
	private String cmts;

	@Column(name = "ENTRY_DT")
	private Date entry_DT;
	
	@Column(name = "ITEM_ID")
	private String item_ID;
	
	@Column(name = "STAGE")
	private String Stage;
	
	@Column(name = "CUS_SITE")
	private String cus_site;

	public String getCus_site() {
		return cus_site;
	}

	public void setCus_site(String cus_site) {
		this.cus_site = cus_site;
	}

	public String getStage() {
		return Stage;
	}

	public void setStage(String stage) {
		Stage = stage;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCin_NO() {
		return cin_NO;
	}

	public void setCin_NO(String cin_NO) {
		this.cin_NO = cin_NO;
	}

	public int getSeq_NO() {
		return seq_NO;
	}

	public void setSeq_NO(int seq_NO) {
		this.seq_NO = seq_NO;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getOff_ID() {
		return off_ID;
	}

	public void setOff_ID(String off_ID) {
		this.off_ID = off_ID;
	}

	public String getCmts() {
		return cmts;
	}

	public void setCmts(String cmts) {
		this.cmts = cmts;
	}

	public Date getEntry_DT() {
		return entry_DT;
	}

	public void setEntry_DT(Date entry_DT) {
		this.entry_DT = entry_DT;
	}

	public String getItem_ID() {
		return item_ID;
	}

	public void setItem_ID(String item_ID) {
		this.item_ID = item_ID;
	}
	

}
