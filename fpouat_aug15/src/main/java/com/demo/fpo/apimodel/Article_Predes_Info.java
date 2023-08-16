package com.demo.fpo.apimodel;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "ARTICLE_PREDES_INFO")
public class Article_Predes_Info {
	
	@Id
	@JsonIgnore	
	@GenericGenerator(name = "ARTICLE_PREDES_INFO_ID", strategy = "com.seq.gen.ARTICLE_PREDES_INFOIdGenerator")
	@GeneratedValue(generator = "ARTICLE_PREDES_INFO_ID")
	private Long id;
	
	@Column(name = "ARTICLE_ID")
	@JsonProperty("Articlenumber")
	private String article_id;
	
	@Column(name = "RECP_ID")
	@JsonProperty("Receptacle_id")
	private String recp_id;
	
	@JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
	@Column(name = "PREDEC_DT")
	@JsonProperty("PreDesDate")
	private Date predes_dt;
	
	@JsonIgnore
	@Column(name = "GEN_DT")
	private Date gen_dt;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getArticle_id() {
		return article_id;
	}
	public void setArticle_id(String article_id) {
		this.article_id = article_id;
	}
	public String getRecp_id() {
		return recp_id;
	}
	public void setRecp_id(String recp_id) {
		this.recp_id = recp_id;
	}
	public Date getPredes_dt() {
		return predes_dt;
	}
	public void setPredes_dt(Date predes_dt) {
		this.predes_dt = predes_dt;
	}
	public Date getGen_dt() {
		return gen_dt;
	}
	public void setGen_dt(Date gen_dt) {
		this.gen_dt = gen_dt;
	}
	
	
	

}
