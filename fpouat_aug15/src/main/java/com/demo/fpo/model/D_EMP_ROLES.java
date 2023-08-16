package com.demo.fpo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "OPS$DIR.D_EMP_ROLES")
public class D_EMP_ROLES {
	
	@Id
	@Column(name = "USER_ID", columnDefinition="serial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	
	@Column(name = "ROLE_NAME")
	private String roleName;
	
	@Column(name = "ASSIGN_BY")
	private String assignBy;
	

	@Column(name = "REVOKED_BY")
	private String revokedBy;
	
	@Column(name = "CUS_SITE")
	private String cusSite;
	
	@Column(name = "START_DT")
	private Date startDt;
	
	@Column(name = "END_DT")
	private Date endDt;
	
	@Column(name = "assign_dt")
	private Date assgnDt;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
	
	
}
