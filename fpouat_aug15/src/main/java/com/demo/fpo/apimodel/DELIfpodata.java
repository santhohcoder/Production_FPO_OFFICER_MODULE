package com.demo.fpo.apimodel;
//import java.util.Date;

import java.util.Date;

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
@Table(name ="FPO_DELIVERY")
public class DELIfpodata {
	@Id
//	@GeneratedValue(generator = "idSequence7", strategy=GenerationType.SEQUENCE)
//	@SequenceGenerator(name = "idSequence7", sequenceName = "FPODELI_JOB_NO", allocationSize = 1)
	@GenericGenerator(name = "FPO_DELIVERY_ID", strategy = "com.seq.gen.FPO_DELIVERY_SEQIdGenerator")
	@GeneratedValue(generator = "FPO_DELIVERY_ID")
  //  @GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
//	@JsonProperty(value = "ID")
	@Column(name = "ID")
    private long id;
	
//	@JsonIgnore
//	@JsonProperty("Count")
//	private long count;
//		
//	public long getCount() {
//		return count;
//	}
//	public void setCount(long count) {
//		this.count = count;
//	}
	@Column(name = "ITEM_ID")
	@JsonProperty("Item_ID")
    private String itemid;
	
	@Column(name = "DELI_PO")
	@JsonProperty("Delivered_PO")
    private String delivered_po;
	
     // @JsonFormat(pattern="DD-mm-yyyy")
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	//@JsonFormat(pattern="DD-MM-yyyy HH:mm:ss")
	@JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
	@Column(name = "DELI_DT")
	@JsonProperty("Delivery_date")
    private Date delivery_date; 
	
	@Column(name = "DELI_STATUS")
	 @JsonProperty("Delivery_status")
    private String delivery_status;
	
	@Column(name = "DELI_MODE")
	@JsonProperty("Delivery_mode")
    private String delivery_mode;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getItemid() {
		return itemid;
	}
	public void setItemid(String itemid) {
		this.itemid = itemid;
	}
	public String getDelivered_po() {
		return delivered_po;
	}
	public void setDelivered_po(String delivered_po) {
		this.delivered_po = delivered_po;
	}
	public Date getDelivery_date() {
		return delivery_date;
	}
	public void setDelivery_date(Date delivery_date) {
		this.delivery_date = delivery_date;
	}
	public String getDelivery_status() {
		return delivery_status;
	}
	public void setDelivery_status(String delivery_status) {
		this.delivery_status = delivery_status;
	}
	public String getDelivery_mode() {
		return delivery_mode;
	}
	public void setDelivery_mode(String delivery_mode) {
		this.delivery_mode = delivery_mode;
	}
	
}
