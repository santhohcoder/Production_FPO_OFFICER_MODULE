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
@Table(name = "fpo_qry_deci")
public class Fpoqrydeci {
	@Id
//	@GeneratedValue(generator = "idSequence", strategy=GenerationType.SEQUENCE)
//	@SequenceGenerator(schema = "ICES_FPO", name = "idSequence", sequenceName = "fpoqrydeciseq", initialValue = 5, allocationSize = 1)
//	@Column(name = "ID",columnDefinition="serial") 
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@GenericGenerator(name = "FPO_QRY_DECI_SEQ", strategy = "com.seq.gen.FPO_QRY_DECI_SEQIdGenerator")
	@GeneratedValue(generator = "FPO_QRY_DECI_SEQ")
	@Column(name = "ID")
	private Long id;
	
@Column(name = "CIN_NO") 
private String CIN_NO; 
	
	public String getCIN_NO() {
		return CIN_NO;
	}

	public void setCIN_NO(String cIN_NO) {
		CIN_NO = cIN_NO;
	}

	@Column(name = "ITEM_ID") 
	private String ITEM_ID;

	@Column(name = "CUS_SITE")
	private String CUS_SITE;
	
	@Column(name = "DECI_CD")
	private Long DECI_CD;
	
	@Column(name = "QRY_TYPE")
	private String QRY_TYPE;
		
	@Column(name = "QRY_NO")
	private Long QRY_NO;
	
	@Column(name = "QRY_DT") 
	Date date2 = new Date();
	
	@Column(name = "ROLE")
	private String ROLE;
	
	@Column(name = "OFF_ID")
	private String OFF_ID;
	
	@Column(name = "SENT")
	private Long SENT;

	

	public String getITEM_ID() {
		return ITEM_ID;
	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setITEM_ID(String iTEM_ID) {
		ITEM_ID = iTEM_ID;
	}

	public String getCUS_SITE() {
		return CUS_SITE;
	}

	public void setCUS_SITE(String cUS_SITE) {
		CUS_SITE = cUS_SITE;
	}

	public Long getDECI_CD() {
		return DECI_CD;
	}

	public void setDECI_CD(Long dECI_CD) {
		DECI_CD = dECI_CD;
	}

	public String getQRY_TYPE() {
		return QRY_TYPE;
	}

	public void setQRY_TYPE(String qRY_TYPE) {
		QRY_TYPE = qRY_TYPE;
	}

	public Long getQRY_NO() {
		return QRY_NO;
	}

	public void setQRY_NO(Long qRY_NO) {
		QRY_NO = qRY_NO;
	}

	public Date getDate2() {
		return date2;
	}

	public void setDate2(Date date2) {
		this.date2 = date2;
	}

	public String getROLE() {
		return ROLE;
	}

	public void setROLE(String rOLE) {
		ROLE = rOLE;
	}

	public String getOFF_ID() {
		return OFF_ID;
	}

	public void setOFF_ID(String oFF_ID) {
		OFF_ID = oFF_ID;
	}

	public Long getSENT() {
		return SENT;
	}

	public void setSENT(Long sENT) {
		SENT = sENT;
	}
	
	
	
}
