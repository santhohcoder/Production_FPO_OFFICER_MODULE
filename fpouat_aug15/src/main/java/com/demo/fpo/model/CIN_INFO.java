package com.demo.fpo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "CIN_INFO")
public class CIN_INFO {

	@Id
	@GenericGenerator(name = "CININFO_SEQ", strategy = "com.seq.gen.CININFO_SEQIdGenerator")
	@GeneratedValue(generator = "CININFO_SEQ")
	@Column(name = "ID")
	public Long id;
	
	@Column(name = "CIN_NO")
	private String cinNO;

	@Column(name = "CIN_DT")
	private Date cinDt;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCinNO() {
		return cinNO;
	}

	public void setCinNO(String cinNO) {
		this.cinNO = cinNO;
	}

	public Date getCinDt() {
		return cinDt;
	}

	public void setCinDt(Date cinDt) {
		this.cinDt = cinDt;
	}

	public String getItemID() {
		return itemID;
	}

	public void setItemID(String itemID) {
		this.itemID = itemID;
	}

	public String getPostingDT() {
		return postingDT;
	}

	public void setPostingDT(String postingDT) {
		this.postingDT = postingDT;
	}

	@Column(name = "ITEM_ID")
	private String itemID;
	
	@Column(name = "POSTINGDT")
	private String postingDT;

}