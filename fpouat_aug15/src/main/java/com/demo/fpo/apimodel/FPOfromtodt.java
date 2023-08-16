package com.demo.fpo.apimodel;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
@Entity
@Table(name ="ARTICLEARR_FPO_REQ")
public class FPOfromtodt {
	@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@SequenceGenerator(name="fromtodtfpo", sequenceName="ARR_FPO")
	@GenericGenerator(name = "ARTICLEARR_FPO_REQ_ID", strategy = "com.seq.gen.ARTICLEARR_FPO_REQIdGenerator")
	@GeneratedValue(generator = "ARTICLEARR_FPO_REQ_ID")
	@JsonIgnore
	@JsonProperty(value = "ID")
	@Column(name = "ID")
	    private long id;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name = "FROMDT")
    private Date FromDate;
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
	@Column(name = "SUCCESS")
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

	public void setToDate(Date todate) {
		ToDate = todate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
	

    