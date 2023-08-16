package com.demo.fpo.bean;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FpoScndQryData {

	
	@Id
	private String QRY_ID;
	private String QRY_DEF_SLNO;
	private String QRY_DESC;
	private String INP_REQ;	
	
	
	public String getINP_REQ() {
		return INP_REQ;
	}
	public void setINP_REQ(String iNP_REQ) {
		INP_REQ = iNP_REQ;
	}
	public String getQRY_ID() {
		return QRY_ID;
	}
	public void setQRY_ID(String qRY_ID) {
		QRY_ID = qRY_ID;
	}
	public String getQRY_DEF_SLNO() {
		return QRY_DEF_SLNO;
	}
	public void setQRY_DEF_SLNO(String qRY_DEF_SLNO) {
		QRY_DEF_SLNO = qRY_DEF_SLNO;
	}
	public String getQRY_DESC() {
		return QRY_DESC;
	}
	public void setQRY_DESC(String qRY_DESC) {
		QRY_DESC = qRY_DESC;
	}
	
	
	
	
	
	
	
}
