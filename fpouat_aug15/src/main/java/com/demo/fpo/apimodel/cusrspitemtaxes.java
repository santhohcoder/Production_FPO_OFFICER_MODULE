package com.demo.fpo.apimodel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
@Entity
@Table(name = "C_CUSRSP_ITEM_TAXES")
public class cusrspitemtaxes {
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	
//	@GeneratedValue(generator = "idSequence5", strategy=GenerationType.SEQUENCE)
//	@SequenceGenerator(name = "idSequence5", sequenceName = "CUSRSP_ITEM_TAX_ID", initialValue = 5, allocationSize = 1)
	@GenericGenerator(name = "C_CUSRSP_ITEM_TAXES_ID", strategy = "com.seq.gen.C_CUSRSP_ITEM_TAXES_IdGenerator")
	@GeneratedValue(generator = "C_CUSRSP_ITEM_TAXES_ID")
	@Column(name="CUSRSP_ID")
	public Long CUSRSP_ID;

	
	@Column(name = "CIN_NO")
	private String CIN_NO;
	
	@Column(name = "ITEM_ID")
	private String ITEM_ID;
	
	@Column(name = "ITEM_NO")
	private Long ITEM_NO;
	
	@Column(name = "CUS_SITE")
	private String CUS_SITE;
	
	public String getCUS_SITE() {
		return CUS_SITE;
	}

	public void setCUS_SITE(String cUS_SITE) {
		CUS_SITE = cUS_SITE;
	}

	public Long getITEM_NO() {
		return ITEM_NO;
	}

	public void setITEM_NO(Long iTEM_NO) {
		ITEM_NO = iTEM_NO;
	}
	
	@Column(name = "DUTY_AMT")
	private BigDecimal DUTY_AMT;
	
	@Column(name = "CURRCD")
	private String CURRCD;
	
	@Column(name = "TAX_RT")
	private BigDecimal TAX_RT;
	
	@Column(name = "TAX_TYPE")
	private String TAX_TYPE;
	
	public Long getCUSRSP_ID() {
		return CUSRSP_ID;
	}

	public void setCUSRSP_ID(Long cUSRSP_ID) {
		CUSRSP_ID = cUSRSP_ID;
	}

	public java.sql.Date getSqlDate() {
		return sqlDate;
	}

	public void setSqlDate(java.sql.Date sqlDate) {
		this.sqlDate = sqlDate;
	}

	@Column(name = "SENT_DT")
	private java.sql.Date sqlDate;

	public String getCIN_NO() {
		return CIN_NO;
	}

	public void setCIN_NO(String cIN_NO) {
		CIN_NO = cIN_NO;
	}

	public String getITEM_ID() {
		return ITEM_ID;
	}

	public void setITEM_ID(String iTEM_ID) {
		ITEM_ID = iTEM_ID;
	}

	public BigDecimal getDUTY_AMT() {
		return DUTY_AMT;
	}

	public void setDUTY_AMT(BigDecimal dUTY_AMT) {
		DUTY_AMT = dUTY_AMT;
	}

	public String getCURRCD() {
		return CURRCD;
	}

	public void setCURRCD(String cURRCD) {
		CURRCD = cURRCD;
	}

	public BigDecimal getTAX_RT() {
		return TAX_RT;
	}

	public void setTAX_RT(BigDecimal tAX_RT) {
		TAX_RT = tAX_RT;
	}

	public String getTAX_TYPE() {
		return TAX_TYPE;
	}

	public void setTAX_TYPE(String tAX_TYPE) {
		TAX_TYPE = tAX_TYPE;
	}
	
	
}