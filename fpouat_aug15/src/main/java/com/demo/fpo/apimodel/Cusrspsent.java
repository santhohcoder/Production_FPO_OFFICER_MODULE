package com.demo.fpo.apimodel;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "CUSRSP_SENT")
public class Cusrspsent {
	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	@GenericGenerator(name = "CUSRSP_ID", strategy = "com.seq.gen.CUSRSP_ID_SEQIdGenerator")
	@GeneratedValue(generator = "CUSRSP_ID")
	@Column(name = "ID")
	public Long Id;
	
	@Column(name = "CIN_NO")
	private String CINNO;

	@Column(name = "CATEGORY")
	public String CATEGORY;

	@Column(name = "SENT_DT")
	Date date = new Date();
	//public java.sql.Date SENT_DT;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getCINNO() {
		return CINNO;
	}

	public void setCINNO(String cINNO) {
		CINNO = cINNO;
	}

	public String getCATEGORY() {
		return CATEGORY;
	}

	public void setCATEGORY(String cATEGORY) {
		CATEGORY = cATEGORY;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

//	public java.sql.Date getSENT_DT() {
//		return SENT_DT;
//	}
//
//	public void setSENT_DT(java.sql.Date sENT_DT) {
//		SENT_DT = sENT_DT;
//	}


	
	
}
