package com.demo.fpo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="MISREP_SEL")
public class MISREP_SEL {

	//created by santhosh

	@Id
	
	  @GenericGenerator(name = "MISREP_SEL_ID", strategy ="com.seq.gen.MISREP_SEL_seq")
	  
	  @GeneratedValue(generator = "MISREP_SEL_ID")
	 

	@Column(name = "ID")
	public Long id;
	
	@Column(name="REPORT_CATEGORY")
	private String reportCategory;
	
	public String getReportCategory() {
		return reportCategory;
	}

	public void setReportCategory(String reportCategory) {
		this.reportCategory = reportCategory;
	}

	public String getCus_site() {
		return cus_site;
	}

	public void setCus_site(String cus_site) {
		this.cus_site = cus_site;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	@Column(name="OFF_ID")
	private String oof_id;
	
	@Column(name="REPORTNAME")
	private String reportname;
	
	@Column(name="REPORTNUMBER")
	private String reportnumber;
	
	@Column(name="TYPE")
	private String type;
	
	
	@Column(name="DATEANDTIME")
	private  Date dateandtime;

	@Column(name="CUS_SITE")
	private String cus_site;
	
	public Date getDateandtime() {
		return dateandtime;
	}

	public void setDateandtime(Date dateandtime) {
		this.dateandtime = dateandtime;
	}
	@Column(name="ROLE")
	private String role;

	public String getOof_id() {
		return oof_id;
	}

	public void setOof_id(String oof_id) {
		this.oof_id = oof_id;
	}

	public String getReportname() {
		return reportname;
	}

	public void setReportname(String reportname) {
		this.reportname = reportname;
	}

	public String getReportnumber() {
		return reportnumber;
	}

	public void setReportnumber(String reportnumber) {
		this.reportnumber = reportnumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	
	
}
