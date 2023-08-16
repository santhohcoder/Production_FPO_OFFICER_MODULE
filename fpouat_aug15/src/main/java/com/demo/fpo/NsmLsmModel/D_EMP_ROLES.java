package com.demo.fpo.NsmLsmModel;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.multipart.MultipartFile;
@Entity
@Table(name = "OPS$DIR.D_EMP_ROLES")
public class D_EMP_ROLES {
	@Id
	@GenericGenerator(name = "D_EMP_SEQ", strategy = "com.seq.gen.D_EMP_ROLES_SEQ")
	@GeneratedValue(generator = "D_EMP_SEQ")
	@Column(name = "ID")
	public Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "USER_ID")
	private String userid;
	
	@Column(name = "ROLE_NAME")
	private String roleName;
	
	@Column(name = "ASSIGN_BY")
	private String assignBy;
	
	@Column(name = "REVOKED_BY")
	private String revokedBy;
	
	@Column(name = "REASON")
	private String reason;
	
	@Column(name = "DESIGNATION")
	private String design;
	
	public String getDesign() {
		return design;
	}

	public void setDesign(String design) {
		this.design = design;
	}
	
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Column(name = "CUS_SITE")
	private String cusSite;
	
	@Column(name = "START_DT")
	private Date startDt;
	
	@Column(name = "END_DT")
	private Date endDt;
	
	@Column(name = "assign_dt")
	private Date assgnDt;
	
	@Column(name = "MAIL_CLASS_CD")
	private String mailclasscd;
	
	@Column(name = "MOBILE_NO")
	private String mobileno;
	
	@Column(name = "EMAIL_ID")
	private String email;

	@Transient
	private String pwd;
	
	@Transient
	private String empName;
	
	@Transient
	private String storedata;

	public String getStoredata() {
		return storedata;
	}

	public void setStoredata(String storedata) {
		this.storedata = storedata;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getMailclasscd() {
		return mailclasscd;
	}

	public void setMailclasscd(String mailclasscd) {
		this.mailclasscd = mailclasscd;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getAssignBy() {
		return assignBy;
	}

	public void setAssignBy(String assignBy) {
		this.assignBy = assignBy;
	}

	public String getRevokedBy() {
		return revokedBy;
	}

	public void setRevokedBy(String revokedBy) {
		this.revokedBy = revokedBy;
	}

	public String getCusSite() {
		return cusSite;
	}

	public void setCusSite(String cusSite) {
		this.cusSite = cusSite;
	}

	public Date getStartDt() {
		return startDt;
	}

	public void setStartDt(Date startDt) {
		this.startDt = startDt;
	}

	public Date getEndDt() {
		return endDt;
	}

	public void setEndDt(Date endDt) {
		this.endDt = endDt;
	}

	public Date getAssgnDt() {
		return assgnDt;
	}

	public void setAssgnDt(Date assgnDt) {
		this.assgnDt = assgnDt;
	}
	
	@Transient
	private MultipartFile filename;
	
	public MultipartFile getFilename() {
		return filename;
	}

	public void setFilename(MultipartFile filename) {
		this.filename = filename;
	}

	
	@Transient
	private String docname;
		
	public String getDocname() {
		return docname;
	}

	public void setDocname(String docname) {
		this.docname = docname;
	}

	
}
