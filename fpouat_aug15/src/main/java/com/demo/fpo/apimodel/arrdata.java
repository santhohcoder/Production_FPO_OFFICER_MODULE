package com.demo.fpo.apimodel;


import java.util.Date;

//import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name ="ARTICLE_ARR_INFO")
public class arrdata {
//	@Id
//	@GeneratedValue(generator = "idSequence1", strategy=GenerationType.SEQUENCE)
//	@SequenceGenerator(name = "idSequence1", sequenceName = "ARROOE_JOB_NO", allocationSize = 1)
	
	
	@Id
	@GenericGenerator(name = "ARTICLE_ARR_INFO_ID", strategy = "com.seq.gen.ARTICLE_ARR_INFO_IdGenerator")
	@GeneratedValue(generator = "ARTICLE_ARR_INFO_ID")
	@JsonIgnore
	@Column(name = "ID")
    private long id;
	@Column(name = "ARTICLE_ID")
	@JsonProperty("Articlenumber")
    private String Articlenumber;
@JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	//@JsonFormat(pattern="dd-mm-yyyy HH:mm:ss")
	@Column(name = "RECD_EVENT_DT")
	@JsonProperty("Received_Event_date")
	
    private Date Received_Event_date;
	@Column(name = "OOE")
	@JsonProperty("OOE")
    private String OOE;
	@Column(name = "RECP_ID")
	@JsonProperty("Receptacle_id")
    private String Receptacle_id;
	
	@Column(name = "MAIL_CLASS")
	@JsonProperty("Mail_Class")
    private String mailclass;
	
	public String getMailclass() {
		return mailclass;
	}
	public void setMailclass(String mailclass) {
		this.mailclass = mailclass;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getArticlenumber() {
		return Articlenumber;
	}
	public void setArticlenumber(String articlenumber) {
		Articlenumber = articlenumber;
	}
	public Date getReceived_Event_date() {
		return Received_Event_date;
	}
	public void setReceived_Event_date(Date received_Event_date) {
		Received_Event_date = received_Event_date;
	}
	public String getOOE() {
		return OOE;
	}
	public void setOOE(String oOE) {
		OOE = oOE;
	}
	public String getReceptacle_id() {
		return Receptacle_id;
	}
	public void setReceptacle_id(String receptacle_id) {
		Receptacle_id = receptacle_id;
	}
	
	
	
}
