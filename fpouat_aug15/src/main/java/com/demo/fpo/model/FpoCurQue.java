package com.demo.fpo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "FPO_CURR_QUE")
public class FpoCurQue {

	@Id
	@GenericGenerator(name = "CURR_QUE_SEQ", strategy = "com.seq.gen.CURR_QUE_SEQIdGenerator")
	@GeneratedValue(generator = "CURR_QUE_SEQ")
	@Column(name = "CURR_QUE_ID")
	public Long id;
	
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

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public Long getQueNo() {
		return queNo;
	}

	public void setQueNo(Long queNo) {
		this.queNo = queNo;
	}

	public String getCusSite() {
		return cusSite;
	}

	public void setCusSite(String cusSite) {
		this.cusSite = cusSite;
	}

	public String getCurrQue() {
		return currQue;
	}

	public void setCurrQue(String currQue) {
		this.currQue = currQue;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getOffId() {
		return offId;
	}

	public void setOffId(String offId) {
		this.offId = offId;
	}

	public Date getQueDt() {
		return queDt;
	}

	public void setQueDt(Date queDt) {
		this.queDt = queDt;
	}

	@Column(name = "CIN_NO")
	private String cinNo;

	@Column(name = "ITEM_ID")
	private String itemId;
	
	@Column(name = "QUE_NO")
	public Long queNo;

	@Column(name = "CUS_SITE")
	private String cusSite;
	
	@Column(name = "CURR_QUE")
	private String currQue;
	
	@Column(name = "ROLE")
	private String role;
	
	@Column(name = "OFF_ID")
	private String offId;
	
	@Column(name = "QUE_DT")
	private Date queDt;
}
