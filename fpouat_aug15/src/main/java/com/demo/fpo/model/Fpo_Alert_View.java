package com.demo.fpo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "FPO_ALERT_VIEW")
public class Fpo_Alert_View {

	
	@Id
	@GenericGenerator(name = "FPOALERTVIEW_SEQ", strategy = "com.seq.gen.FPOALERTVIEW_SEQIdGenerator")
	@GeneratedValue(generator = "FPOALERTVIEW_SEQ")
	@Column(name = "ID")
	public Long id;
	
	@Column(name = "CIN_NO")
	private String cin_no;
	
	@Column(name = "ITEM_ID")
	private String item_id;
	
	@Column(name = "OFF_ID")
	private String off_id;
	
	@Column(name = "ROLE")
	private String role;
	
	@Column(name = "GEN_DT")
	private Date gen_dt;
	
	@Column(name = "CUS_SITE")
	private String cus_site;
	
	@Column(name = "PROCESS_LVL")
	private String process_lvl;
	
	@Column(name = "ALERT_LEVEL")
	private String alert_level;
	
	
	
	

	public String getAlert_level() {
		return alert_level;
	}

	public void setAlert_level(String alert_level) {
		this.alert_level = alert_level;
	}

	public String getProcess_lvl() {
		return process_lvl;
	}

	public void setProcess_lvl(String process_lvl) {
		this.process_lvl = process_lvl;
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

	public Date getGen_dt() {
		return gen_dt;
	}

	public void setGen_dt(Date gen_dt) {
		this.gen_dt = gen_dt;
	}

	public String getCus_site() {
		return cus_site;
	}

	public void setCus_site(String cus_site) {
		this.cus_site = cus_site;
	}
	
	
	
}
