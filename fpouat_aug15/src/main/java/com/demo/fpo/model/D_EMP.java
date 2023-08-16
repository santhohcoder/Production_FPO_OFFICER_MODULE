package com.demo.fpo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "OPS$DIR.D_EMP")
public class D_EMP {
	
	
	@Id
	@Column(name = "USER_ID", columnDefinition="serial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	
	@Column(name = "EMP_NO")
	private String empno;
	
	@Column(name = "EMP_NAME")
	private String empName;
	
	@Column(name="SEX")
	private String sex;
	
	@Column(name="START_DATE")
	private Date startdt;
	
	@Column(name="END_DATE")
	private Date enddt;
	
	@Column(name="MGR_ID")
	private String mgrid;
	
	@Column(name="ADDRESS")
	private String adrs;
	
	@Column(name="MOBILE_NO")
	private String mobileno;
	
	@Column(name="DESIGNATION")
	private String designation;
	
	@Column(name="DOB")
	private Date dob;
	
	@Column(name = "FPO_CUS_SITE")
	private String fpo_cus_site;
	
	@Column(name="COMM_NAME")
	private String commname;
	
	public String getEmpno() {
		return empno;
	}

	public void setEmpno(String empno) {
		this.empno = empno;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getStartdt() {
		return startdt;
	}

	public void setStartdt(Date startdt) {
		this.startdt = startdt;
	}

	public Date getEnddt() {
		return enddt;
	}

	public void setEnddt(Date enddt) {
		this.enddt = enddt;
	}

	public String getMgrid() {
		return mgrid;
	}

	public void setMgrid(String mgrid) {
		this.mgrid = mgrid;
	}

	public String getAdrs() {
		return adrs;
	}

	public void setAdrs(String adrs) {
		this.adrs = adrs;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getFpo_cus_site() {
		return fpo_cus_site;
	}

	public void setFpo_cus_site(String fpo_cus_site) {
		this.fpo_cus_site = fpo_cus_site;
	}

	public String getCommname() {
		return commname;
	}

	public void setCommname(String commname) {
		this.commname = commname;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUseraccountcontrol() {
		return useraccountcontrol;
	}

	public void setUseraccountcontrol(String useraccountcontrol) {
		this.useraccountcontrol = useraccountcontrol;
	}

	@Column(name="EMAIL_ID")
	private String emailid;
	
	@Column(name="ROLE")
	private String role;
	
	@Column(name="USER_ACCOUNT_CONTROL")
	private String useraccountcontrol;
	
	
	
	
	
	
	
	@Transient
	private String roleName; 
	
	@Transient
	private String cusSite;
	

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getCusSite() {
		return cusSite;
	}

	public void setCusSite(String cusSite) {
		this.cusSite = cusSite;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}
	
	
	
	

}
