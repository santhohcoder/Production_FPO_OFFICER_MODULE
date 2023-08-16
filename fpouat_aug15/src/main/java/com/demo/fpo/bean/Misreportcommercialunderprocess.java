package com.demo.fpo.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Misreportcommercialunderprocess {
	
@Id 
private String id;

private String item_id;
private String cussite;
private String iec;
private String gstinid;
private String adcode;
private String schemecd;
private String licenseno;
private String challanno;
private String challandt;
private String beno;
private String bedt;
private String status;
public String getBeno() {
	return beno;
}
public void setBeno(String beno) {
	this.beno = beno;
}
public String getBedt() {
	return bedt;
}
public void setBedt(String bedt) {
	this.bedt = bedt;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
@Transient
private String postingdt;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getPostingdt() {
	return postingdt;
}
public void setPostingdt(String postingdt) {
	this.postingdt = postingdt;
}
public String getItem_id() {
	return item_id;
}
public void setItem_id(String item_id) {
	this.item_id = item_id;
}

public String getCussite() {
	return cussite;
}
public void setCussite(String cussite) {
	this.cussite = cussite;
}
public String getIec() {
	return iec;
}
public void setIec(String iec) {
	this.iec = iec;
}

public String getAdcode() {
	return adcode;
}
public void setAdcode(String adcode) {
	this.adcode = adcode;
}
public String getGstinid() {
	return gstinid;
}
public void setGstinid(String gstinid) {
	this.gstinid = gstinid;
}
public String getSchemecd() {
	return schemecd;
}
public void setSchemecd(String schemecd) {
	this.schemecd = schemecd;
}
public String getLicenseno() {
	return licenseno;
}
public void setLicenseno(String licenseno) {
	this.licenseno = licenseno;
}
public String getChallanno() {
	return challanno;
}
public void setChallanno(String challanno) {
	this.challanno = challanno;
}
public String getChallandt() {
	return challandt;
}
public void setChallandt(String challandt) {
	this.challandt = challandt;
}



}
