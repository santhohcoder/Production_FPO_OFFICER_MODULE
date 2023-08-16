package com.demo.fpo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "fpo_addl_qry")
public class FpoAddlQry {
	
	@Id@GenericGenerator(name = "FPO_ADDL_QRY_ID", strategy = "com.seq.gen.ADDL_QUERY_SEQIdGenerator")
	@GeneratedValue(generator = "FPO_ADDL_QRY_ID")
	@Column(name = "QRY_ID")
	public Long QRY_ID;
	
	@Column(name = "CIN_NO")
	public String CIN_NO;
	
	@Column(name = "ITEM_ID")
	public String ITEM_ID;
	
	@Column(name = "QRY_DEF_SLNO")
	public String QRY_DEF_SLNO;
	
	@Column(name = "QRY_DESC")
	public String QRY_DESC;
	
	@Column(name = "QRY_OFF_ID")
	public String QRY_OFF_ID;
	
	@Column(name = "QRY_ROLE")
	public String QRY_ROLE;

	@Column(name = "QRY_DATE")
	private Date QRY_DATE;
	
	@Column(name = "QRY_TYPE")
	public String QRY_TYPE;
	
	@Column(name = "RPLY_OFF_ID")
	public String RPLY_OFF_ID;
	
	@Column(name = "RPLY_ROLE")
	public String RPLY_ROLE;
	
	@Column(name = "RPLY_DATE")
	public String RPLY_DATE;
	
	@Column(name = "QRY_EMAILID")
	public String QRY_EMAILID;
	
	@Column(name = "QRY_MOBILENO")
	public String QRY_MOBILENO;
	
	@Column(name = "QRY_DEPCMTS_APR")
	public String QRY_DEPCMTS_APR;
	
	@Column(name = "RPLY_DEPCMTS_APR")
	public String RPLY_DEPCMTS_APR;
	
	@Column(name = "QRY_DEPCMTS_ACL")
	public String QRY_DEPCMTS_ACL;
	
	@Column(name = "RPLY_DEPCMTS_ACL")
	public String RPLY_DEPCMTS_ACL;
	
	@Column(name = "DIN1")
	public String DIN1;
	
	@Column(name = "QRY_REPLY")
	public String QRY_REPLY;
	
	@Column(name = "ENT_NAME")
	private String ent_name;
	
    @Column(name = "UPLOAD_DT") 
	 private Date upload_dt;
	
	
	

	public String getEnt_name() {
		return ent_name;
	}

	public void setEnt_name(String ent_name) {
		this.ent_name = ent_name;
	}

	public Date getUpload_dt() {
		return upload_dt;
	}

	public void setUpload_dt(Date upload_dt) {
		this.upload_dt = upload_dt;
	}

	public Long getQRY_ID() {
		return QRY_ID;
	}

	public void setQRY_ID(Long qRY_ID) {
		QRY_ID = qRY_ID;
	}

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

	public String getQRY_DEF_SLNO() {
		return QRY_DEF_SLNO;
	}

	public void setQRY_DEF_SLNO(String qRY_DEF_SLNO) {
		QRY_DEF_SLNO = qRY_DEF_SLNO;
	}

	public String getQRY_DESC() {
		return QRY_DESC;
	}

	public void setQRY_DESC(String qRY_DESC) {
		QRY_DESC = qRY_DESC;
	}

	public String getQRY_OFF_ID() {
		return QRY_OFF_ID;
	}

	public void setQRY_OFF_ID(String qRY_OFF_ID) {
		QRY_OFF_ID = qRY_OFF_ID;
	}

	public String getQRY_ROLE() {
		return QRY_ROLE;
	}

	public void setQRY_ROLE(String qRY_ROLE) {
		QRY_ROLE = qRY_ROLE;
	}

	public Date getQRY_DATE() {
		return QRY_DATE;
	}

	public void setQRY_DATE(Date qRY_DATE) {
		QRY_DATE = qRY_DATE;
	}

	public String getQRY_TYPE() {
		return QRY_TYPE;
	}

	public void setQRY_TYPE(String qRY_TYPE) {
		QRY_TYPE = qRY_TYPE;
	}

	public String getRPLY_OFF_ID() {
		return RPLY_OFF_ID;
	}

	public void setRPLY_OFF_ID(String rPLY_OFF_ID) {
		RPLY_OFF_ID = rPLY_OFF_ID;
	}

	public String getRPLY_ROLE() {
		return RPLY_ROLE;
	}

	public void setRPLY_ROLE(String rPLY_ROLE) {
		RPLY_ROLE = rPLY_ROLE;
	}

	public String getRPLY_DATE() {
		return RPLY_DATE;
	}

	public void setRPLY_DATE(String rPLY_DATE) {
		RPLY_DATE = rPLY_DATE;
	}

	public String getQRY_EMAILID() {
		return QRY_EMAILID;
	}

	public void setQRY_EMAILID(String qRY_EMAILID) {
		QRY_EMAILID = qRY_EMAILID;
	}

	public String getQRY_MOBILENO() {
		return QRY_MOBILENO;
	}

	public void setQRY_MOBILENO(String qRY_MOBILENO) {
		QRY_MOBILENO = qRY_MOBILENO;
	}

	public String getQRY_DEPCMTS_APR() {
		return QRY_DEPCMTS_APR;
	}

	public void setQRY_DEPCMTS_APR(String qRY_DEPCMTS_APR) {
		QRY_DEPCMTS_APR = qRY_DEPCMTS_APR;
	}

	public String getRPLY_DEPCMTS_APR() {
		return RPLY_DEPCMTS_APR;
	}

	public void setRPLY_DEPCMTS_APR(String rPLY_DEPCMTS_APR) {
		RPLY_DEPCMTS_APR = rPLY_DEPCMTS_APR;
	}

	public String getQRY_DEPCMTS_ACL() {
		return QRY_DEPCMTS_ACL;
	}

	public void setQRY_DEPCMTS_ACL(String qRY_DEPCMTS_ACL) {
		QRY_DEPCMTS_ACL = qRY_DEPCMTS_ACL;
	}

	public String getRPLY_DEPCMTS_ACL() {
		return RPLY_DEPCMTS_ACL;
	}

	public void setRPLY_DEPCMTS_ACL(String rPLY_DEPCMTS_ACL) {
		RPLY_DEPCMTS_ACL = rPLY_DEPCMTS_ACL;
	}

	public String getDIN1() {
		return DIN1;
	}

	public void setDIN1(String dIN1) {
		DIN1 = dIN1;
	}

	public String getQRY_REPLY() {
		return QRY_REPLY;
	}

	public void setQRY_REPLY(String qRY_REPLY) {
		QRY_REPLY = qRY_REPLY;
	}
	
	
	
	
	

}
