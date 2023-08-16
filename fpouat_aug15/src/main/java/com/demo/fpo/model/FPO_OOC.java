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
@Table(name = "FPO_OOC_FINDINGS")
public class FPO_OOC {


	@Id
	@GenericGenerator(name = "OOC_SEQ", strategy = "com.seq.gen.OOC_SEQIdGenerator")
	@GeneratedValue(generator = "OOC_SEQ")
	@Column(name = "OOC_ID")
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

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getEnt_dt() {
		return ent_dt;
	}

	public void setEnt_dt(Date ent_dt) {
		this.ent_dt = ent_dt;
	}

	public String getDepcmts() {
		return depcmts;
	}

	public void setDepcmts(String depcmts) {
		this.depcmts = depcmts;
	}


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

	@Column(name = "STATUS")
	private String status;
	
	
	@Column(name = "ROLE")
	private String role;
	
	@Column(name = "ENTERED_DT")
	private Date ent_dt;
	
	@Column(name = "DEP_CMTS")
	private String depcmts;	
	
	@Column(name = "ITEM_ID")
	private String itemId;

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	

	
}
