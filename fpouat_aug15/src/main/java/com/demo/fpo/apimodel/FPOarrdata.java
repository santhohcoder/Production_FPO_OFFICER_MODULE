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
@Table(name ="ARTICLEARR_FPO_INFO")
public class FPOarrdata {
	@Id
//	@GeneratedValue(generator = "idSequence2", strategy=GenerationType.SEQUENCE)
//	@SequenceGenerator(name = "idSequence2", sequenceName = "ARRFPO_JOB_NO", allocationSize = 1)
	@GenericGenerator(name = "ARTICLEARR_FPO_INFO_id", strategy = "com.seq.gen.ARTICLEARR_FPO_INFOIdGenerator")
	@GeneratedValue(generator = "ARTICLEARR_FPO_INFO_id")
  //  @GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
//	@JsonProperty(value = "ID")
	@Column(name = "ID")
    private long id;
	@Column(name = "ARTICLE_ID")
	@JsonProperty("Articlenumber")
    private String Articlenumber;
@JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	//@JsonFormat(pattern="dd-mm-yyyy HH24:mm:ss")
	@Column(name = "RECD_DT")
	@JsonProperty("Received_date")
	
    private Date Received_date;
	@Column(name = "RECD_FPO")
	@JsonProperty("ReceivedAt")
    private String RECD_FPO;
	@Column(name = "BAG_NO")
	@JsonProperty("BagNumber")
    private String BagNumber;
	@JsonIgnore
	@Column(name = "STATUS")
	private String status;
	
public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public Date getReceived_date() {
		return Received_date;
	}
	public void setReceived_date(Date received_date) {
		Received_date = received_date;
	}
	
	public String getRECD_FPO() {
		return RECD_FPO;
	}
	public void setRECD_FPO(String rECD_FPO) {
		RECD_FPO = rECD_FPO;
	}
	public String getBagNumber() {
		return BagNumber;
	}
	public void setBagNumber(String bagNumber) {
		BagNumber = bagNumber;
	}
	
	
	
	
	
}
