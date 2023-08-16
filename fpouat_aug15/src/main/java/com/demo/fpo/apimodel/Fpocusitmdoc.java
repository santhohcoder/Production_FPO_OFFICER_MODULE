package com.demo.fpo.apimodel;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
//import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
//import javax.xml.datatype.XMLGregorianCalendar;

import org.hibernate.annotations.GenericGenerator;



@Entity
@Table(name = "C_CUSITM_DOC")
public class Fpocusitmdoc {

	@Id
//	@GeneratedValue(generator = "cusdocdet", strategy=GenerationType.SEQUENCE)
//	@SequenceGenerator(allocationSize=1, name="cusdocdet", sequenceName="CUSDOCDET")
	@GenericGenerator(name = "C_CUSITM_DOC_ID", strategy = "com.seq.gen.C_CUSITM_DOCIdGenerator")
	@GeneratedValue(generator = "C_CUSITM_DOC_ID")
	@Column(name = "DOCUNIQUE_ID")
	private Long DOCUNIQUE;
	
	@Column(name = "ITEM_ID")
	private String ITEM_ID;

	@Column(name = "MESG_TYP")
	private String MESG_TYP;

		@Column(name = "POSTINGDT")
	private String POSTINGDT;
	
	@Column(name = "JOB_NO")
	private long JOB_NO;	
	
	@Column(name = "JOB_DT")
		private java.sql.Date jobdt;

		
	@Column(name = "DOCSLNO")
	private long docslno;
	
	
	@Column(name = "DOC_ID")
	private String docid;

	@Column(name = "DOC_NAME")
	private String docname;

	@Column(name = "DOC_TYPE")
	private String doctyp;

	
	public Long getDOCUNIQUE() {
		return DOCUNIQUE;
	}

	public void setDOCUNIQUE(Long dOCUNIQUE) {
		DOCUNIQUE = dOCUNIQUE;
	}

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

	public java.sql.Date getJobdt() {
		return jobdt;
	}

	public void setJobdt(java.sql.Date jobdt) {
		this.jobdt = jobdt;
	}

	public long getDocslno() {
		return docslno;
	}

	public void setDocslno(long docslno) {
		this.docslno = docslno;
	}

	public String getDocid() {
		return docid;
	}

	public void setDocid(String docid) {
		this.docid = docid;
	}

	public String getDocname() {
		return docname;
	}

	public void setDocname(String docname) {
		this.docname = docname;
	}

	public String getDoctyp() {
		return doctyp;
	}

	public void setDoctyp(String doctyp) {
		this.doctyp = doctyp;
	}

	
}
