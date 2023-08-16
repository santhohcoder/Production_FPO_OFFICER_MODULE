package com.demo.fpo.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "CUSRSP_SENT")
public class CUSRSP_SENT {
	
	
	@Id
	@GenericGenerator(name = "CUSRSP_ID", strategy = "com.seq.gen.CUSRSP_ID_SEQIdGenerator")
	@GeneratedValue(generator = "CUSRSP_ID")
	@Column(name = "ID")
	public Long id;
	
	@Column(name = "CIN_NO")
	private String cin_NO;
	
	@Column(name = "CATEGORY")
	private String category;
	
	@Column(name = "SENT_DT")
	private Date sent_Dt;  
	
	@Column(name = "IN_DATE")
	private Date in_Date;
	
//	@Column(name = "SENT_ID")
//	private long sent_ID;
	

//	public long getSent_ID() {
//		return sent_ID;
//	}
//
//	public void setSent_ID(long sent_ID) {
//		this.sent_ID = sent_ID;
//	}

	public Date getIn_Date() {
		return in_Date;
	}

	public void setIn_Date(Date in_Date) {
		this.in_Date = in_Date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCin_NO() {
		return cin_NO;
	}

	public void setCin_NO(String cin_NO) {
		this.cin_NO = cin_NO;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getSent_Dt() {
		return sent_Dt;
	}

	public void setSent_Dt(Date sent_Dt) {
		this.sent_Dt = sent_Dt;
	}
	
		
}
