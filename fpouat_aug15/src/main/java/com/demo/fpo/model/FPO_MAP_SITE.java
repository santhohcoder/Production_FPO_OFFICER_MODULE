package com.demo.fpo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="FPO_MAP_SITE")
public class FPO_MAP_SITE {

	@Id
	@GenericGenerator(name = "FPO_ALLOTSEQ_ID", strategy = "com.seq.gen.Fpo_allotSite_seq")
	@GeneratedValue(generator = "FPO_ALLOTSEQ_ID")
	@Column(name = "ID")
	public long id;
	
	private String article_id;
	
	private String state;
	
	private String district;
	
	private Date allot_dt;
	
	private String officer_id;
	
	private String role;
	
	private String cus_site;
	
	private String reason;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getArticle_id() {
		return article_id;
	}

	public void setArticle_id(String article_id) {
		this.article_id = article_id;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public Date getAllot_dt() {
		return allot_dt;
	}

	public void setAllot_dt(Date allot_dt) {
		this.allot_dt = allot_dt;
	}

	public String getOfficer_id() {
		return officer_id;
	}

	public void setOfficer_id(String officer_id) {
		this.officer_id = officer_id;
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
