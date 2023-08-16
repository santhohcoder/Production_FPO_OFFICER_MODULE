package com.demo.fpo.apimodel;


import java.math.BigDecimal;
//import java.util.Date;
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
public class OOEarrdata {
	@Id
//	@GeneratedValue(generator = "ARRNOOE", strategy=GenerationType.SEQUENCE)
//	@SequenceGenerator(name = "ARRNOOE", sequenceName = "ARROOE_JOB_NO", allocationSize = 1)

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
	//@JsonFormat(pattern="dd-mm-yyyy HH24:mm:ss")
	@Column(name = "BOOKING_DT")
	@JsonProperty("BookingDate")
	private Date BookingDate;
	
	@Column(name = "ITEM_VALUE")
	@JsonProperty("ItemValue")
	private BigDecimal ItemValue;
	
	@Column(name = "ITEM_WT")
	@JsonProperty("ItemWeight")
	private BigDecimal ItemWeight;
	
	@Column(name = "ORIGCNTRYCD")
	@JsonProperty("OriginCountry")
	private String OriginCountry;
	
	@Column(name = "DESTCNTRYCD")
	@JsonProperty("DestnCountry")
	private String DestnCountry;
	
@JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	//@JsonFormat(pattern="dd-mm-yyyy HH24:mm:ss")
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
	
	@Column(name = "A_Name")
	@JsonProperty("A_Name")
	private String A_Name;
	
	@Column(name = "A_Addr1")
	@JsonProperty("A_Addr1")
	private String A_Addr1;
	
	@Column(name = "A_Addr2")
	@JsonProperty("A_Addr2")
	private String A_Addr2;
		
	@Column(name = "A_PostCode")
	@JsonProperty("A_PostCode")
	private String A_PostCode;
	
	@Column(name = "S_NameAddr")
	@JsonProperty("S_NameAddr")
	private String S_NameAddr;
	
	@Column(name = "S_PostCode")
	@JsonProperty("S_PostCode")
	private String S_PostCode;
	
	@Column(name = "CIN_NO") 
	private String CIN_NO;  
	
	@Column(name = "CIN_DT") 
	private java.sql.Date cindt;
	
	@Column(name = "CUS_SITE")
	private String CUS_SITE;
	
		@Column(name = "PINCODE")
	private String PINCODE;
		
		@Column(name = "STATUS")
		private String status;
		
	public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
	public String getCIN_NO() {
			return CIN_NO;
		}
		public void setCIN_NO(String cIN_NO) {
			CIN_NO = cIN_NO;
		}
		public java.sql.Date getCindt() {
			return cindt;
		}
		public void setCindt(java.sql.Date cindt) {
			this.cindt = cindt;
		}
		public String getCUS_SITE() {
			return CUS_SITE;
		}
		public void setCUS_SITE(String cUS_SITE) {
			CUS_SITE = cUS_SITE;
		}
		public String getPINCODE() {
			return PINCODE;
		}
		public void setPINCODE(String pINCODE) {
			PINCODE = pINCODE;
		}
		public String getFPO_CODE() {
			return FPO_CODE;
		}
		public void setFPO_CODE(String fPO_CODE) {
			FPO_CODE = fPO_CODE;
		}
	@Column(name = "FPO_CODE")
	private String FPO_CODE;
	
	
	
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
	public Date getBookingDate() {
		return BookingDate;
	}
	public void setBookingDate(Date bookingDate) {
		BookingDate = bookingDate;
	}
	public BigDecimal getItemValue() {
		return ItemValue;
	}
	public void setItemValue(BigDecimal itemValue) {
		ItemValue = itemValue;
	}
	public BigDecimal getItemWeight() {
		return ItemWeight;
	}
	public void setItemWeight(BigDecimal itemWeight) {
		ItemWeight = itemWeight;
	}
	public String getOriginCountry() {
		return OriginCountry;
	}
	public void setOriginCountry(String originCountry) {
		OriginCountry = originCountry;
	}
	public String getDestnCountry() {
		return DestnCountry;
	}
	public void setDestnCountry(String destnCountry) {
		DestnCountry = destnCountry;
	}
	public String getA_Name() {
		return A_Name;
	}
	public void setA_Name(String a_Name) {
		A_Name = a_Name;
	}
	public String getA_Addr1() {
		return A_Addr1;
	}
	public void setA_Addr1(String a_Addr1) {
		A_Addr1 = a_Addr1;
	}
	public String getA_Addr2() {
		return A_Addr2;
	}
	public void setA_Addr2(String a_Addr2) {
		A_Addr2 = a_Addr2;
	}
	public String getA_PostCode() {
		return A_PostCode;
	}
	public void setA_PostCode(String a_PostCode) {
		A_PostCode = a_PostCode;
	}
	public String getS_NameAddr() {
		return S_NameAddr;
	}
	public void setS_NameAddr(String s_NameAddr) {
		S_NameAddr = s_NameAddr;
	}
	public String getS_PostCode() {
		return S_PostCode;
	}
	public void setS_PostCode(String s_PostCode) {
		S_PostCode = s_PostCode;
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
