package com.demo.fpo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
//@Table(name ="fpo_curr_que")
public class Recently_process {
	
	@Id
	private String ITEM_ID;
	private String STAGE_NAME;
	private String QUE_DT;
	
/*	private Date POSTINGDT;
 * 
	private String CIN_NO;
	private Date CIN_DT;
	//private String no_items;
	private String TOT_ASS_VAL;  */
	
	private String MAIL_CLASS_CD;
	private String CATEGORY;
	
	private String send_cntry_cd; 
	
	@Transient
	private String recp_id;
	
	@Transient
	private String bag_no;

	
	
	
	
	
	
	public String getRecp_id() {
		return recp_id;
	}
	public void setRecp_id(String recp_id) {
		this.recp_id = recp_id;
	}
	public String getBag_no() {
		return bag_no;
	}
	public void setBag_no(String bag_no) {
		this.bag_no = bag_no;
	}
	@Transient
	private String currentStatus;
	
	public String getITEM_ID() {
		return ITEM_ID;
	}
	public void setITEM_ID(String iTEM_ID) {
		ITEM_ID = iTEM_ID;
	}
	
	/*
	public Date getPOSTINGDT() {
		return POSTINGDT;
	}
	public void setPOSTINGDT(Date pOSTINGDT) {
		POSTINGDT = pOSTINGDT;
	}
	public String getCIN_NO() {
		return CIN_NO;
	}
	public void setCIN_NO(String cIN_NO) {
		CIN_NO = cIN_NO;
	}
	public Date getCIN_DT() {
		return CIN_DT;
	}
	public void setCIN_DT(Date cIN_DT) {
		CIN_DT = cIN_DT;
	}
	public String getNo_items() {
		return no_items;
	}
	public void setNo_items(String no_items) {
		this.no_items = no_items;
	}
	public String getTOT_ASS_VAL() {
		return TOT_ASS_VAL;
	}
	public void setTOT_ASS_VAL(String tOT_ASS_VAL) {
		TOT_ASS_VAL = tOT_ASS_VAL;   
	} */
	public String getMAIL_CLASS_CD() {
		return MAIL_CLASS_CD;
	}
	public void setMAIL_CLASS_CD(String mAIL_CLASS_CD) {
		MAIL_CLASS_CD = mAIL_CLASS_CD;
	}
	public String getCATEGORY() {
		return CATEGORY;
	}
	public void setCATEGORY(String cATEGORY) {
		CATEGORY = cATEGORY;
	}
	public String getSend_cntry_cd() {
		return send_cntry_cd;
	}
	public void setSend_cntry_cd(String send_cntry_cd) {
		this.send_cntry_cd = send_cntry_cd;  
	} 
	public String getSTAGE_NAME() {
		return STAGE_NAME;
	}
	public void setSTAGE_NAME(String sTAGE_NAME) {
		STAGE_NAME = sTAGE_NAME;
	}
	public String getCurrentStatus() {
		return currentStatus;
	}
	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}
	public String getQUE_DT() {
		return QUE_DT;
	}
	public void setQUE_DT(String qUE_DT) {
		QUE_DT = qUE_DT;
	}
	
	
	
	
	

}
