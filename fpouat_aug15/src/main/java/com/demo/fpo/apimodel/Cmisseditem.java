package com.demo.fpo.apimodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "C_MISSED_ITEMID")
public class Cmisseditem {
//	@Id
//	@GeneratedValue(generator = "idSequence", strategy=GenerationType.SEQUENCE)
//	@SequenceGenerator(name = "idSequence", sequenceName = "missedseq1", initialValue = 5, allocationSize = 1)
	@Id
	@GenericGenerator(name = "C_MISSED_ITEMID_ID", strategy = "com.seq.gen.C_MISSED_ITEMID_IdGenerator")
	@GeneratedValue(generator = "C_MISSED_ITEMID_ID")
	@Column(name = "ID")
	public Long id;
	
	@Column(name = "ITEM_ID")
	private String ITEM_ID;
	
	@Column(name = "POSTINGDT")
	private String POSTINGDT;
	
	@Column(name = "CREATEDTIME")
	//Date date = new Date();
	private java.sql.Date crDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getITEM_ID() {
		return ITEM_ID;
	}

	public void setITEM_ID(String iTEM_ID) {
		ITEM_ID = iTEM_ID;
	}

	public String getPOSTINGDT() {
		return POSTINGDT;
	}

	public void setPOSTINGDT(String pOSTINGDT) {
		POSTINGDT = pOSTINGDT;
	}

	public java.sql.Date getCrDate() {
		return crDate;
	}

	public void setCrDate(java.sql.Date crDate) {
		this.crDate = crDate;
	}
}
