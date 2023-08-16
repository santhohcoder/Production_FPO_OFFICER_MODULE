package com.demo.fpo.NsmLsmModel;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "OPS$DIR.FPO_PUSH")
public class FPO_PUSH {
	@Id
	@GenericGenerator(name = "FPO_PUSH_SEQ", strategy = "com.seq.gen.FPO_PUSHseq")
	@GeneratedValue(generator = "FPO_PUSH_SEQ")
	@Column(name = "ID")
	public Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
    
	@Column(name = "ITEM_ID")
	private String itemid;
	
	@Column(name = "FROM_CUSSITE")
	private String fromcussite;
	
	@Column(name = "TO_CUSSITE")
	private String tocussite;
	
	@Column(name = "PUSH_DT")
	private Date pushdt;
	
	@Column(name = "OFF_ID")
	private String offid;
	
	@Column(name = "ROLE")
	private String role;
	
	@Column(name = "REASONS")
	private String reason;

	public String getItemid() {
		return itemid;
	}

	public void setItemid(String itemid) {
		this.itemid = itemid;
	}

	public String getFromcussite() {
		return fromcussite;
	}

	public void setFromcussite(String fromcussite) {
		this.fromcussite = fromcussite;
	}

	public String getTocussite() {
		return tocussite;
	}

	public void setTocussite(String tocussite) {
		this.tocussite = tocussite;
	}

	public Date getPushdt() {
		return pushdt;
	}

	public void setPushdt(Date pushdt) {
		this.pushdt = pushdt;
	}

	public String getOffid() {
		return offid;
	}

	public void setOffid(String offid) {
		this.offid = offid;
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
	
}
