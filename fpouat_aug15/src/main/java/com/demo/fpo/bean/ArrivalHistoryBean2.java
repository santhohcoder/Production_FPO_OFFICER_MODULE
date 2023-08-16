package com.demo.fpo.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ArrivalHistoryBean2 {
	@Id
	private String bagNo;
	private String bagType;
	private String recd_dt;
	private String scan_dt;
	private String totalCount;
	private String withEadCurrSite;
	private String withEadOtherSite;
	private String withoutEad;
	private String withEadSiteNotAllot;
	
	
	public String getBagNo() {
		return bagNo;
	}
	public void setBagNo(String bagNo) {
		this.bagNo = bagNo;
	}
	public String getBagType() {
		return bagType;
	}
	public void setBagType(String bagType) {
		this.bagType = bagType;
	}
	public String getRecd_dt() {
		return recd_dt;
	}
	public void setRecd_dt(String recd_dt) {
		this.recd_dt = recd_dt;
	}
	public String getScan_dt() {
		return scan_dt;
	}
	public void setScan_dt(String scan_dt) {
		this.scan_dt = scan_dt;
	}
	public String getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	public String getWithEadCurrSite() {
		return withEadCurrSite;
	}
	public void setWithEadCurrSite(String withEadCurrSite) {
		this.withEadCurrSite = withEadCurrSite;
	}
	public String getWithEadOtherSite() {
		return withEadOtherSite;
	}
	public void setWithEadOtherSite(String withEadOtherSite) {
		this.withEadOtherSite = withEadOtherSite;
	}
	public String getWithoutEad() {
		return withoutEad;
	}
	public void setWithoutEad(String withoutEad) {
		this.withoutEad = withoutEad;
	}
	public String getWithEadSiteNotAllot() {
		return withEadSiteNotAllot;
	}
	public void setWithEadSiteNotAllot(String withEadSiteNotAllot) {
		this.withEadSiteNotAllot = withEadSiteNotAllot;
	}



}
