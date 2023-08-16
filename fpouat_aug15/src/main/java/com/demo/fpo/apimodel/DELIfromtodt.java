package com.demo.fpo.apimodel;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
@Entity
@Table(name ="FPO_DELI_REQ")
public class DELIfromtodt {
	//@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	@JsonProperty(value = "ID")
	//@SequenceGenerator(name="fromdtdtdeli", sequenceName="FPODELIREQ")
	@GenericGenerator(name = "FPO_DELI_REQ_ID", strategy = "com.seq.gen.FPO_DELI_REQ_SEQIdGenerator")
	@GeneratedValue(generator = "FPO_DELI_REQ_ID")
	@Id
	@Column(name = "ID")
	    private long id;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name = "FROMDT")
    private Date FromDate;
	//@JsonFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name = "TODT")
    private Date ToDate;
	@JsonIgnore
	@Column(name = "CREATEDTIME")
	private Date Crdt;
	@JsonIgnore
	@Column(name = "UPDATEDTIME")
	private Date Updt;
	@JsonIgnore
	@Column(name = "RECORDCOUNT")
    private long reccnt;
	@JsonIgnore
	@Column(name = "Success")
    private String Success;
	@JsonIgnore
	@Column(name = "TIMELAPSE")
	private long timelapse;
	public long getTimelapse() {
		return timelapse;
	}
	public void setTimelapse(long timelapse) {
		this.timelapse = timelapse;
	}
   
	public Date getCrdt() {
		return Crdt;
	}

	public void setCrdt(Date crdt) {
		Crdt = crdt;
	}

	public Date getUpdt() {
		return Updt;
	}

	public void setUpdt(Date updt) {
		Updt = updt;
	}

	public long getReccnt() {
		return reccnt;
	}

	public void setReccnt(long reccnt) {
		this.reccnt = reccnt;
	}

	public String getSuccess() {
		return Success;
	}

	public void setSuccess(String success) {
		Success = success;
	}

	public Date getFromDate() {
		return FromDate;
	}

	public void setFromDate(Date fromDate) {
		this.FromDate = fromDate;
	}

	public Date getToDate() {
		return ToDate;
	}

	public void setToDate(Date toDate) {
		this.ToDate = toDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}

