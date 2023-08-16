package com.demo.fpo.apimodel;

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

//import org.hibernate.annotations.GenericGenerator;



@Entity
@Table(name = "fpo_cusitm_err")
public class errinsert {

	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idsequence6")
//	@SequenceGenerator(allocationSize=1, name="idsequence6", sequenceName="err_cusitm")
	@GenericGenerator(name = "Fpo_cusitm_err_SEQ_id", strategy = "com.seq.gen.Fpo_cusitm_err_SEQIdGenerator")
	@GeneratedValue(generator = "Fpo_cusitm_err_SEQ_id")
	@Column(name = "err_id")
	private Long err_id;
	
//	@Id
//	@GenericGenerator(name = "idsequence7", strategy = "com.fpo.idgen.FpoIdGenerator")
//	@GeneratedValue(generator = "idsequence7")  
//	@Column(name = "ERR_ID")
//	private long ERR_ID;
	
	@Column(name = "ITEM_ID")
	private String ITEM_ID;

	@Column(name = "POSTINGDT")
	private String POSTINGDT;
	
	@Column(name = "JOB_NO")
	private long JOB_NO;	
		
	@Column(name = "CUS_SITE")
	private String CUS_SITE;
	
	public String getCUS_SITE() {
		return CUS_SITE;
	}
	
	public void setCUS_SITE(String cUS_SITE) {
		CUS_SITE = cUS_SITE;
	}
	
	@Column(name = "JOB_DT")
	//Date date = new Date();
	private java.sql.Date sqlDate;

	public java.sql.Date getSqlDate() {
        return sqlDate;
    }
 
    public void setSqlDate(java.sql.Date sqlDate) {
        this.sqlDate = sqlDate;
    }
	
	@Column(name = "UNIQUE_ID")
	private String UNIQUE_ID;
	
	@Column(name = "ERR")
	private String ERR;
	
	@Column(name = "ITEM_NO")
	private long ITEM_NO;
	

	public long getITEM_NO() {
		return ITEM_NO;
	}

	public void setITEM_NO(long iTEM_NO) {
		ITEM_NO = iTEM_NO;
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

	public long getJOB_NO() {
		return JOB_NO;
	}

	public void setJOB_NO(long jOB_NO) {
		JOB_NO = jOB_NO;
	}

	public String getUNIQUE_ID() {
		return UNIQUE_ID;
	}

	public void setUNIQUE_ID(String uNIQUE_ID) {
		UNIQUE_ID = uNIQUE_ID;
	}

	public String getERR() {
		return ERR;
	}

	public void setERR(String eRR) {
		ERR = eRR;
	}

}