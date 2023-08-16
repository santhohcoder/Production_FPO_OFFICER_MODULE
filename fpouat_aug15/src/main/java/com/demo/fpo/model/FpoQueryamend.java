package com.demo.fpo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "FPO_QUERYAMEND")
public class FpoQueryamend{

	@Id
	@GenericGenerator(name = "FPO_QUERYAMENDSEQ", strategy = "com.seq.gen.FPO_QUERYAMENDSEQIdGenerator")
	@GeneratedValue(generator = "FPO_QUERYAMENDSEQ")
	@Column(name = "ID")
	public Long id;

	@Column(name = "CIN_NO")
	private String cinNo;

	@Column(name = "ITEM_ID")
	private String ITEM_ID;

	@Column(name = "CUS_SITE")
	private String CUS_SITE;

	@Column(name = "ITEM_NO")
	private Long item_no;
	
	@Column(name = "QRY_EMAILID")
	private String Email_Id;
	
	@Column(name = "QRY_MOBILENO")
	private String Mobile_No;
	
	@Column(name = "GENERAL_QUERY")
	private String General_Query;

	public String getGeneral_Query() {
		return General_Query;
	}

	public void setGeneral_query(String general_query) {
		General_Query = general_query;
	}

	public String getEmail_Id() {
		return Email_Id;
	}

	public void setEmail_Id(String email_Id) {
		Email_Id = email_Id;
	}

	public String getMobile_No() {
		return Mobile_No;
	}

	public void setMobile_No(String mobile_No) {
		Mobile_No = mobile_No;
	}

	public Long getItem_no() {
		return item_no;
	}

	public void setItem_no(Long item_no) {
		this.item_no = item_no;
	}

//	@Column(name = "POSTINGDT")
//	private String POSTINGDT;

	@Column(name = "QRY_NO")
	private Long QRY_NO;

	@Column(name = "QRY")
	private String QRY;

	@Column(name = "QRY_TYP")
	private String QRY_TYP;

	@Transient
	private String itemDesc;

	@Column(name = "QRY_DATE")
	private Date QRY_DATE;

	@Column(name = "QRY_OFF_ID")
	private String QRY_OFF_ID;

	@Column(name = "QRY_ROLE")
	private String QRY_ROLE;

	@Column(name = "RPLY")
	private String rply;

	public String getRply() {
		return rply;
	}

	public void setRply(String rply) {
		this.rply = rply;
	}

	@Column(name = "RPLY_OFF_ID")
	private String RPLY_OFF_ID;

	@Column(name = "RPLY_ROLE")
	private String RPLY_ROLE;

	@Column(name = "RPLY_DATE")
	private Date RPLY_DATE;

	@Column(name = "DEF_QUERY")
	private String DEFUALT_QUERY;

	@Column(name = "MARK")
	private String mark;
	
	@Column(name = "RPLY_DEP_CMTS")
	private String dpcmts;

	@Transient
	private String generalQuery;

	@Transient
	private String UNIQUE_NO;

	@Transient
	private String defqry1;

	@Transient
	private String defqry2;

	@Transient
	private String defqry3;

	@Transient
	private String defqry4;
	
	@Transient
	private String date;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDpcmts() {
		return dpcmts;
	}

	public void setDpcmts(String dpcmts) {
		this.dpcmts = dpcmts;
	}

	public String getUNIQUE_NO() {
		return UNIQUE_NO;
	}

	public void setUNIQUE_NO(String uNIQUE_NO) {
		UNIQUE_NO = uNIQUE_NO;
	}

	public String getDefqry1() {
		return defqry1;
	}

	public void setDefqry1(String defqry1) {
		this.defqry1 = defqry1;
	}

	public String getDefqry2() {
		return defqry2;
	}

	public void setDefqry2(String defqry2) {
		this.defqry2 = defqry2;
	}

	public String getDefqry3() {
		return defqry3;
	}

	public void setDefqry3(String defqry3) {
		this.defqry3 = defqry3;
	}

	public String getDefqry4() {
		return defqry4;
	}

	public void setDefqry4(String defqry4) {
		this.defqry4 = defqry4;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getGeneralQuery() {
		return generalQuery;
	}

	public void setGeneralQuery(String generalQuery) {
		this.generalQuery = generalQuery;
	}

	public String getGENERAL_QUERY() {
		return generalQuery;
	}

	public void setGENERAL_QUERY(String generalQuery) {
		this.generalQuery = generalQuery;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public String getDEFUALT_QUERY() {
		return DEFUALT_QUERY;
	}

	public void setDEFUALT_QUERY(String dEFUALT_QUERY) {
		DEFUALT_QUERY = dEFUALT_QUERY;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCinNo() {
		return cinNo;
	}

	public void setCinNo(String cinNo) {
		this.cinNo = cinNo;
	}

	public String getITEM_ID() {
		return ITEM_ID;
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

	public Long getITEM_NO() {
		return item_no;
	}

	public void setITEM_NO(Long iTEM_NO) {
		item_no = iTEM_NO;
	}

//	public String getPOSTINGDT() {
//		return POSTINGDT;
//	}
//
//	public void setPOSTINGDT(String pOSTINGDT) {
//		POSTINGDT = pOSTINGDT;
//	}

	public Long getQRY_NO() {
		return QRY_NO;
	}

	public void setQRY_NO(Long qRY_NO) {
		QRY_NO = qRY_NO;
	}

	public String getQRY() {
		return QRY;
	}

	public void setQRY(String qRY) {
		QRY = qRY;
	}

	public String getQRY_TYP() {
		return QRY_TYP;
	}

	public void setQRY_TYP(String qRY_TYP) {
		QRY_TYP = qRY_TYP;
	}

	public Date getQRY_DATE() {
		return QRY_DATE;
	}

	public void setQRY_DATE(Date qRY_DATE) {
		QRY_DATE = qRY_DATE;
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

	/*
	 * public String getRPLY() { return rply; }
	 * 
	 * public void setRPLY(String rPLY) { rply = rPLY; }
	 */

	public String getRPLY_OFF_ID() {
		return RPLY_OFF_ID;
	}

	public String setRPLY_OFF_ID(String rPLY_OFF_ID) {
		return RPLY_OFF_ID = rPLY_OFF_ID;
	}

	public String getRPLY_ROLE() {
		return RPLY_ROLE;
	}

	public void setRPLY_ROLE(String rPLY_ROLE) {
		RPLY_ROLE = rPLY_ROLE;
	}

	public Date getRPLY_DATE() {
		return RPLY_DATE;
	}

	public Date setRPLY_DATE(Date rPLY_DATE) {
		return RPLY_DATE = rPLY_DATE;
	}
}