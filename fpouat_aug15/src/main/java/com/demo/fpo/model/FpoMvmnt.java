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
@Table(name = "FPO_MVMNT")
public class FpoMvmnt {

	@Id
	@GenericGenerator(name = "FPO_MVMNT_SEQ", strategy = "com.seq.gen.FpoMvmtIdGenerator")
	@GeneratedValue(generator = "FPO_MVMNT_SEQ")
	@Column(name = "ID")
	public Long id;

	@Column(name = "CIN_NO")
	private String cinNo;

	@Column(name = "CUS_SITE")
	private String cusSite;

	@Column(name = "ROLE")
	private String role;

	@Column(name = "OFFICER_ID")
	private String offId;

	@Column(name = "ENT_DT")
	private Date endDt;

	@Column(name = "EXT_DT")
	private Date extDt;

	@Column(name = "CIN_DT")
	private Date cinDt;

	@Column(name = "SLNO")
	public Long slNo;
	
	@Column(name = "STAGE")
	public String stage;
	

	@Column(name = "ITEM_ID")
	public String itemid;

	public String getItemid() {
		return itemid;
	}

	public void setItemid(String itemid) {
		this.itemid = itemid;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public Long getSlNo() {
		return slNo;
	}

	public void setSlNo(Long slNo) {
		this.slNo = slNo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCinNo() {
		return cinNo;
	}

	public void setCinNo(String cinNo) {
		this.cinNo = cinNo;
	}

	public String getCusSite() {
		return cusSite;
	}

	public void setCusSite(String cusSite) {
		this.cusSite = cusSite;
	}

	public String getOffId() {
		return offId;
	}

	public void setOffId(String offId) {
		this.offId = offId;
	}

	public Date getEndDt() {
		return endDt;
	}

	public void setEndDt(Date endDt) {
		this.endDt = endDt;
	}

	public Date getExtDt() {
		return extDt;
	}

	public void setExtDt(Date extDt) {
		this.extDt = extDt;
	}

	public Date getCinDt() {
		return cinDt;
	}

	public void setCinDt(Date cinDt) {
		this.cinDt = cinDt;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	@Transient
	private String flag;

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	

}
