package com.demo.fpo.apimodel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
//import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
//import javax.xml.datatype.XMLGregorianCalendar;

import org.hibernate.annotations.GenericGenerator;



@Entity
@Table(name = "C_CUSITM_CP_DET")
public class Fpoitemins {

	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO, generator = "idsequence8")
//	@SequenceGenerator(allocationSize=1, name="idsequence8", sequenceName="CUSITM_JOB_NO2")
	@GenericGenerator(name = "C_CUSITM_CP_DET_ID", strategy = "com.seq.gen.C_CUSITM_CP_DETIdGenerator")
	@GeneratedValue(generator = "C_CUSITM_CP_DET_ID")
	@Column(name = "ITEM_UNIQUE")
	private Long ITEM_UNIQUE;
	
	@Column(name = "ITEM_ID")
	private String ITEM_ID;

	@Column(name = "MESG_TYP")
	private String MESG_TYP;

	public String getPOSTINGDT() {
		return POSTINGDT;
	}
	
	public void setPOSTINGDT(String pOSTINGDT) {
		POSTINGDT = pOSTINGDT;
	}

	@Column(name = "POSTINGDT")
	private String POSTINGDT;
	
	@Column(name = "JOB_NO")
	private long JOB_NO;	
	
//	
	@Column(name = "JOB_DT")
	//private Date JOB_DT;
	//Date date = new Date();
	private java.sql.Date sqlDate;

	public java.sql.Date getSqlDate() {
        return sqlDate;
    }
 
    public void setSqlDate(java.sql.Date sqlDate) {
        this.sqlDate = sqlDate;
    }
	
	/*
	 * public Date getJOB_DT() { return JOB_DT; }
	 * 
	 * public void setJOB_DT(Date jOB_DT) { JOB_DT = jOB_DT; }
	 */

	@Column(name = "ITEM_NO")
	private long ITEM_NO;
	
	@Column(name = "CP_NOU")
	private long CP_NOU;

	@Column(name = "CP_NETWT")
	private BigDecimal CP_NETWT;

	@Column(name = "CP_AMT")
	private BigDecimal CP_AMT;

	@Column(name = "CP_CURRCD")
	private String CP_CURRCD;

	@Column(name = "CP_DESC")
	private String CP_DESC;

	@Column(name = "CP_HS")
	private String CP_HS;

	@Column(name = "CP_ORIGCNTRYCD")
	private String CP_ORIGCNTRYCD;
	
	@Column(name = "CP_REVISEDDESC")
	private String CP_REVISEDDESC;
	
	@Column(name = "CP_REVISEDHS")
	private String CP_REVISEDHS;
	
	@Column(name = "UNIQUE_ID")
	private String UNIQUE_ID;

//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}

	public String getITEM_ID() {
		return ITEM_ID;
	}

	public void setITEM_ID(String iTEM_ID) {
		ITEM_ID = iTEM_ID;
	}

	public String getMESG_TYP() {
		return MESG_TYP;
	}

	public void setMESG_TYP(String mESG_TYP) {
		MESG_TYP = mESG_TYP;
	}

	public long getJOB_NO() {
		return JOB_NO;
	}

	public void setJOB_NO(long jOB_NO) {
		JOB_NO = jOB_NO;
	}

	
	public long getITEM_NO() {
		return ITEM_NO;
	}

	public void setITEM_NO(long iTEM_NO) {
		ITEM_NO = iTEM_NO;
	}

	public long getCP_NOU() {
		return CP_NOU;
	}

	public void setCP_NOU(long cP_NOU) {
		CP_NOU = cP_NOU;
	}

	public BigDecimal getCP_NETWT() {
		return CP_NETWT;
	}

	public void setCP_NETWT(BigDecimal cP_NETWT) {
		CP_NETWT = cP_NETWT;
	}

	public BigDecimal getCP_AMT() {
		return CP_AMT;
	}

	public void setCP_AMT(BigDecimal cP_AMT) {
		CP_AMT = cP_AMT;
	}

	public String getCP_CURRCD() {
		return CP_CURRCD;
	}

	public void setCP_CURRCD(String cP_CURRCD) {
		CP_CURRCD = cP_CURRCD;
	}

	public String getCP_DESC() {
		return CP_DESC;
	}

	public void setCP_DESC(String cP_DESC) {
		CP_DESC = cP_DESC;
	}

	public String getCP_HS() {
		return CP_HS;
	}

	public void setCP_HS(String cP_HS) {
		CP_HS = cP_HS;
	}

	public String getCP_ORIGCNTRYCD() {
		return CP_ORIGCNTRYCD;
	}

	public void setCP_ORIGCNTRYCD(String cP_ORIGCNTRYCD) {
		CP_ORIGCNTRYCD = cP_ORIGCNTRYCD;
	}

	public String getCP_REVISEDDESC() {
		return CP_REVISEDDESC;
	}

	public void setCP_REVISEDDESC(String cP_REVISEDDESC) {
		CP_REVISEDDESC = cP_REVISEDDESC;
	}

	public String getCP_REVISEDHS() {
		return CP_REVISEDHS;
	}

	public void setCP_REVISEDHS(String cP_REVISEDHS) {
		CP_REVISEDHS = cP_REVISEDHS;
	}

	public String getUNIQUE_ID() {
		return UNIQUE_ID;
	}

	public void setUNIQUE_ID(String uNIQUE_ID) {
		UNIQUE_ID = uNIQUE_ID;
	}

	
		// TODO Auto-generated method stub
		
}
