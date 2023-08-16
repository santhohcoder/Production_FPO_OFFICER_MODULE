package com.demo.fpo.apimodel;





import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
///import javax.persistence.GeneratedValue;
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
@Table(name = "FPO_DOC_DET")
public class Fpodocdet {

	@Id
//	@GeneratedValue(generator = "fpodocdet", strategy=GenerationType.SEQUENCE)
//	@SequenceGenerator(allocationSize=1, name="fpodocdet", sequenceName="FPODOCDET")
	@GenericGenerator(name = "FPO_DOC_DET_ID", strategy = "com.seq.gen.FPO_DOC_DETIdGenerator")
	@GeneratedValue(generator = "FPO_DOC_DET_ID")
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
	
//	
	@Column(name = "JOB_DT")
		private java.sql.Date jobdt;

	
	public java.sql.Date getJobdt() {
		return jobdt;
	}

	public void setJobdt(java.sql.Date jobdt) {
		this.jobdt = jobdt;
	}
	@Column(name = "DOCSLNO")
	private long docslno;
	
	@Column(name = "DOC_ID")
	private String docid;

	@Column(name = "DOC_NAME")
	private String docname;

	@Column(name = "DOC_TYPE")
	private String doctyp;

	
	@Column(name = "CIN_NO") 
	private String CIN_NO;  
	
	@Column(name = "CIN_DT") 
	private java.sql.Date cindt;
	
	public java.sql.Date getCindt() {
		return cindt;
	}

	public void setCindt(java.sql.Date cindt) {
		this.cindt = cindt;
	}

	public String getPOSTINGDT() {
		return POSTINGDT;
	}

	public void setPOSTINGDT(String pOSTINGDT) {
		POSTINGDT = pOSTINGDT;
	}

	public String getCIN_NO() {
		return CIN_NO;
	}

	public void setCIN_NO(String cIN_NO) {
		CIN_NO = cIN_NO;
	}

		public String getCUS_SITE() {
		return CUS_SITE;
	}

	public void setCUS_SITE(String cUS_SITE) {
		CUS_SITE = cUS_SITE;
	}

	@Column(name = "CUS_SITE")
	private String CUS_SITE;
	

	public String getITEM_ID() {
		return ITEM_ID;
	}

	public Long getDOCUNIQUE() {
		return DOCUNIQUE;
	}

	public void setDOCUNIQUE(Long dOCUNIQUE) {
		DOCUNIQUE = dOCUNIQUE;
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
