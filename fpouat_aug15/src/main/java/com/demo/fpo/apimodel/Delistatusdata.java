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
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "FPO_SPEEDDELISTATUS")
public class Delistatusdata implements Comparable<Delistatusdata>  {

	@Id
	@GenericGenerator(name = "FPO_SPEEDDELISTATUS_SEQ", strategy = "com.seq.gen.FPO_SPEEDDELISTATUS_SEQIdGenerator")
	@GeneratedValue(generator = "FPO_SPEEDDELISTATUS_SEQ")
	@JsonIgnore
	@Column(name = "ID")
	private long id;
	
	@Column(name = "SPEEDPOST_NO")
	private String Articlenumber;
	
	@Column(name = "RECD_DT")
	private Date RECD_DT;
	
	@Column(name = "DELI_STATUS")
	private String DELI_STATUS;
	
	@Column(name = "OFFICE")
	private String OFFICE;
	
	@Column(name = "remarks")
	private String remarks;
	
	@Column(name = "GENDT")
	private Date GENDT;
	
	
	
	
	
	
	
	

	  public String getRemarks() {
		return remarks;
	}









	public void setRemarks(String remarks) {
		this.remarks = remarks;
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









	public Date getRECD_DT() {
		return RECD_DT;
	}









	public void setRECD_DT(Date rECD_DT) {
		RECD_DT = rECD_DT;
	}









	public String getDELI_STATUS() {
		return DELI_STATUS;
	}









	public void setDELI_STATUS(String dELI_STATUS) {
		DELI_STATUS = dELI_STATUS;
	}









	public String getOFFICE() {
		return OFFICE;
	}









	public void setOFFICE(String oFFICE) {
		OFFICE = oFFICE;
	}









	public Date getGENDT() {
		return GENDT;
	}









	public void setGENDT(Date gENDT) {
		GENDT = gENDT;
	}









	@Override
	  public int compareTo(Delistatusdata u) {
	    if (getRECD_DT() == null || u.getRECD_DT() == null) {
	      return 0;
	    }
	    return getRECD_DT().compareTo(u.getRECD_DT());
	  }
}