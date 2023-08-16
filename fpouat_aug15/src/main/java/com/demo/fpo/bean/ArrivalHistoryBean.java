package com.demo.fpo.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ArrivalHistoryBean {
	@Id
	private String bagNo;
	private String bagNoFlag;
	private String receivedDate;
	private long totalCount;
	private long withEadCurrSite;
	private long withEadOtherSite;
	private long withoutEad;
	private String scannedDate;
	private String withEadSiteNotAllot;

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

	public String getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(String receivedDate) {
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

	public long getWithoutEad() {
		return withoutEad;
	}

	public void setWithoutEad(long withoutEad) {
		this.withoutEad = withoutEad;
	}

	public String getScannedDate() {
		return scannedDate;
	}

	public void setScannedDate(String scannedDate) {
		this.scannedDate = scannedDate;
	}

	public String getWithEadSiteNotAllot() {
		return withEadSiteNotAllot;
	}

	public void setWithEadSiteNotAllot(String withEadSiteNotAllot) {
		this.withEadSiteNotAllot = withEadSiteNotAllot;
	}

}
