package com.demo.fpo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "FPO_ALERT")
public class Fpo_Alert {
	
	@Id
	@GenericGenerator(name = "FPOALERT_SEQ", strategy = "com.seq.gen.FPOALERT_SEQIdGenerator")
	@GeneratedValue(generator = "FPOALERT_SEQ")
	@Column(name = "ID")
	public Long id;
	
	@Column(name = "OFF_ID")
	private String off_id;
	
	@Column(name = "ROLE")
	private String role;
	
	@Column(name = "GEN_DT")
	private Date gen_dt;
	
	@Column(name = "CUS_SITE")
	private String cus_site;
	
	@Column(name = "ALERT_TYPE")
	private String alert_type;
	
	@Column(name = "ALERT")
	private String alert;
	
	@Column(name = "REASON")
	private String reason;
	
	@Column(name = "ACTIVE_SINCE")
	private String active_since;
	
	@Column(name = "REMOVED_BY")
	private String removed_by;
	
	@Column(name = "REMOVE_REASON")
	private String remove_reason;
	
	@Column(name = "REMOVED_DT")
	private Date removed_dt;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "COUNTRY")
	private String country;
	
	@Column(name = "MAIL_CLASS")
	private String mail_class;
	
	@Column(name = "ITEM_CATEGORY")
	private String item_category;
	
	@Column(name = "ALERT_LEVEL")
	private String alert_level;
	
	
	

	public String getAlert_level() {
		return alert_level;
	}

	public void setAlert_level(String alert_level) {
		this.alert_level = alert_level;
	}

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
	
	

	public String getRemove_reason() {
		return remove_reason;
	}

	public void setRemove_reason(String remove_reason) {
		this.remove_reason = remove_reason;
	}

	public String getAlert_type() {
		return alert_type;
	}

	public void setAlert_type(String alert_type) {
		this.alert_type = alert_type;
	}

	public String getAlert() {
		return alert;
	}

	public void setAlert(String alert) {
		this.alert = alert;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getActive_since() {
		return active_since;
	}

	public void setActive_since(String active_since) {
		this.active_since = active_since;
	}

	public String getRemoved_by() {
		return removed_by;
	}

	public void setRemoved_by(String removed_by) {
		this.removed_by = removed_by;
	}

	public Date getRemoved_dt() {
		return removed_dt;
	}

	public void setRemoved_dt(Date removed_dt) {
		this.removed_dt = removed_dt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getMail_class() {
		return mail_class;
	}

	public void setMail_class(String mail_class) {
		this.mail_class = mail_class;
	}

	public String getItem_category() {
		return item_category;
	}

	public void setItem_category(String item_category) {
		this.item_category = item_category;
	}
	
	
	
	
	
	
	
	
	
	

}
