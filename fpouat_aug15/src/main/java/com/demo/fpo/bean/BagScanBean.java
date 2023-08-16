package com.demo.fpo.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BagScanBean {
	@Id
	private String bagNo;
	private String bagNoFlag;
	private Date receivedDate;
	private long totalCount;
	private long withEadCurrSite;
	private long withEadOtherSite;
	private long withEadSiteNotAllot;
	private long withoutEad;
	private String siteValue;

	public String getBagNo() {
		return bagNo;
	}

	public void setBagNo(String bagNo) {
		this.bagNo = bagNo;
	}

	public String getBagNoFlag() {
		return bagNoFlag;
	}

	public void setBagNoFlag(String bagNoFlag) {
		this.bagNoFlag = bagNoFlag;
	}

	public Date getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public long getWithEadCurrSite() {
		return withEadCurrSite;
	}

	public void setWithEadCurrSite(long withEadCurrSite) {
		this.withEadCurrSite = withEadCurrSite;
	}

	public long getWithEadOtherSite() {
		return withEadOtherSite;
	}

	public void setWithEadOtherSite(long withEadOtherSite) {
		this.withEadOtherSite = withEadOtherSite;
	}

	public long getWithEadSiteNotAllot() {
		return withEadSiteNotAllot;
	}

	public void setWithEadSiteNotAllot(long withEadSiteNotAllot) {
		this.withEadSiteNotAllot = withEadSiteNotAllot;
	}

	public long getWithoutEad() {
		return withoutEad;
	}

	public void setWithoutEad(long withoutEad) {
		this.withoutEad = withoutEad;
	}

	public String getSiteValue() {
		return siteValue;
	}

	public void setSiteValue(String siteValue) {
		this.siteValue = siteValue;
	}

}
