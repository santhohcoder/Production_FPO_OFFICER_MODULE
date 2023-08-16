package com.demo.fpo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "fpo_login_track")
public class FpoLoginTrack {
	
	@Id
	@GenericGenerator(name = "LOGINTRACK_ID", strategy = "com.seq.gen.LoginTrackSeqIdGenerator")
	@GeneratedValue(generator = "LOGINTRACK_ID")
	@Column(name = "id")
	public Long id;

	@Column(name = "CUS_SITE")
	private String CUS_SITE;

	@Column(name = "OFF_ID")
	private String OFF_ID;

	@Column(name = "ROLE")
	private String ROLE;

	@Column(name = "GEN_DT")
	private Date gendt;

	@Column(name = "LOGIN_TIME")
	private Date logintime;

	@Column(name = "LOGOUT_TIME")
	private Date logouttime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCUS_SITE() {
		return CUS_SITE;
	}

	public void setCUS_SITE(String cUS_SITE) {
		CUS_SITE = cUS_SITE;
	}

	public String getOFF_ID() {
		return OFF_ID;
	}

	public void setOFF_ID(String oFF_ID) {
		OFF_ID = oFF_ID;
	}

	public String getROLE() {
		return ROLE;
	}

	public void setROLE(String rOLE) {
		ROLE = rOLE;
	}

	public Date getGendt() {
		return gendt;
	}

	public void setGendt(Date gendt) {
		this.gendt = gendt;
	}

	public Date getLogintime() {
		return logintime;
	}

	public void setLogintime(Date logintime) {
		this.logintime = logintime;
	}

	public Date getLogouttime() {
		return logouttime;
	}

	public void setLogouttime(Date logouttime) {
		this.logouttime = logouttime;
	}
	
	


}
