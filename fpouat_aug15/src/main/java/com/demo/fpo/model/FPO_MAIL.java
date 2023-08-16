package com.demo.fpo.model;


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "FPO_MAIL")
public class FPO_MAIL {
	
	@Id
	@GenericGenerator(name = "MAIL_SEQ", strategy = "com.seq.gen.MAIL_SEQIdGenerator")
	@GeneratedValue(generator = "MAIL_SEQ")
	@Column(name = "ID")
	public Long id;
	
	@Column(name = "HOST_NAME")
	private String Host_Name;
	
	@Column(name = "PORT")
	private Float Port;
	
	@Column(name = "USERNAME")
	private String User_Name;

	@Column(name = "PWD")
	private String Pwd;
	
	@Column(name = "SUPPORT_MAIL1")
	private String SUPPORT_MAIL1;
	
	@Column(name = "SUPPORT_MAIL2")
	private String SUPPORT_MAIL2;
	

	public String getSUPPORT_MAIL1() {
		return SUPPORT_MAIL1;
	}

	public void setSUPPORT_MAIL1(String sUPPORT_MAIL1) {
		SUPPORT_MAIL1 = sUPPORT_MAIL1;
	}

	public String getSUPPORT_MAIL2() {
		return SUPPORT_MAIL2;
	}

	public void setSUPPORT_MAIL2(String sUPPORT_MAIL2) {
		SUPPORT_MAIL2 = sUPPORT_MAIL2;
	}

	public String getHost_Name() {
		return Host_Name;
	}

	public void setHost_Name(String host_Name) {
		Host_Name = host_Name;
	}

	public Float getPort() {
		return Port;
	}

	public void setPort(Float port) {
		Port = port;
	}

	public String getUser_Name() {
		return User_Name;
	}

	public void setUser_Name(String user_Name) {
		User_Name = user_Name;
	}

	public String getPwd() {
		return Pwd;
	}

	public void setPwd(String pwd) {
		Pwd = pwd;
	}
	
	

}
