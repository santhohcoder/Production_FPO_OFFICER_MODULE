package com.demo.fpo.NsmLsmModel;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "FPO_REALLOCATION")
public class REALLOCATION {

	@Id
	@GenericGenerator(name = "FPO_REALLO", strategy = "com.seq.gen.FPO_PUSHseq")
	@GeneratedValue(generator = "FPO_REALLO")
	@Column(name = "ID")
	public Long id;
	
	private String cin_no;
	
	/* private String item_id; */
	
	private String from_ssoid;
	
	private String to_ssoid;
	
	private Date reallocation_dt;
	
	private String role;
	
	private String off_id;
	
	private String cus_site;
	
	private String reason_cd;
	
	private String reason_others;
	
	private String select_article;
	
	private String article_id;
	
	private String Mail_class;
	
	private String roles_assigned;

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

	/*
	 * public String getItem_id() { return item_id; }
	 * 
	 * public void setItem_id(String item_id) { this.item_id = item_id; }
	 */

	public String getFrom_ssoid() {
		return from_ssoid;
	}

	public void setFrom_ssoid(String from_ssoid) {
		this.from_ssoid = from_ssoid;
	}

	public String getTo_ssoid() {
		return to_ssoid;
	}

	public void setTo_ssoid(String to_ssoid) {
		this.to_ssoid = to_ssoid;
	}

	public Date getReallocation_dt() {
		return reallocation_dt;
	}

	public void setReallocation_dt(Date reallocation_dt) {
		this.reallocation_dt = reallocation_dt;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getOff_id() {
		return off_id;
	}

	public void setOff_id(String off_id) {
		this.off_id = off_id;
	}

	public String getCus_site() {
		return cus_site;
	}

	public void setCus_site(String cus_site) {
		this.cus_site = cus_site;
	}

	public String getReason_cd() {
		return reason_cd;
	}

	public void setReason_cd(String reason_cd) {
		this.reason_cd = reason_cd;
	}

	public String getReason_others() {
		return reason_others;
	}

	public void setReason_others(String reason_others) {
		this.reason_others = reason_others;
	}

	public String getSelect_article() {
		return select_article;
	}

	public void setSelect_article(String select_article) {
		this.select_article = select_article;
	}

	public String getArticle_id() {
		return article_id;
	}

	public void setArticle_id(String article_id) {
		this.article_id = article_id;
	}

	public String getMail_class() {
		return Mail_class;
	}

	public void setMail_class(String mail_class) {
		Mail_class = mail_class;
	}

	public String getRoles_assigned() {
		return roles_assigned;
	}

	public void setRoles_assigned(String roles_assigned) {
		this.roles_assigned = roles_assigned;
	}	
	
}
