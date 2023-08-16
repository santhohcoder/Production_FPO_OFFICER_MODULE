package com.demo.fpo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fpo_track_details")
public class FpoTrackDetails {
	@Id
	@Column(name = "item_id", columnDefinition = "serial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String itemId;

	@Column(name = "cus_site")
	private String cusSite;

	@Column(name = "site_name")
	private String siteName;

	@Column(name = "send_name")
	private String senderName;

	@Column(name = "send_cntry_cd")
	private String senderCountryCode;

	@Column(name = "cntry_nm")
	private String countryName;

	@Column(name = "recp_name")
	private String recipientName;

	@Column(name = "tot_decl_val")
	private String totalDeclaredValue;

	@Column(name = "currcd")
	private String currencyCode;

	@Column(name = "tot_ass_val")
	private String totalAssessValue;

	@Column(name = "tot_items")
	private String totalItems;

	@Column(name = "tot_duty_payable")
	private String totalDutyPayable;

	@Column(name = "query_status")
	private String queryStatus;

	@Column(name = "query_reply_link")
	private String queryReplyLink;

	@Column(name = "ooc")
	private String ooc;

	@Column(name = "delivery")
	private String delivery;

	@Column(name = "current_status")
	private String currentStatus;

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getCusSite() {
		return cusSite;
	}

	public void setCusSite(String cusSite) {
		this.cusSite = cusSite;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getSenderCountryCode() {
		return senderCountryCode;
	}

	public void setSenderCountryCode(String senderCountryCode) {
		this.senderCountryCode = senderCountryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getTotalDeclaredValue() {
		return totalDeclaredValue;
	}

	public void setTotalDeclaredValue(String totalDeclaredValue) {
		this.totalDeclaredValue = totalDeclaredValue;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getTotalAssessValue() {
		return totalAssessValue;
	}

	public void setTotalAssessValue(String totalAssessValue) {
		this.totalAssessValue = totalAssessValue;
	}

	public String getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(String totalItems) {
		this.totalItems = totalItems;
	}

	public String getTotalDutyPayable() {
		return totalDutyPayable;
	}

	public void setTotalDutyPayable(String totalDutyPayable) {
		this.totalDutyPayable = totalDutyPayable;
	}

	public String getQueryStatus() {
		return queryStatus;
	}

	public void setQueryStatus(String queryStatus) {
		this.queryStatus = queryStatus;
	}

	public String getQueryReplyLink() {
		return queryReplyLink;
	}

	public void setQueryReplyLink(String queryReplyLink) {
		this.queryReplyLink = queryReplyLink;
	}

	public String getOoc() {
		return ooc;
	}

	public void setOoc(String ooc) {
		this.ooc = ooc;
	}

	public String getDelivery() {
		return delivery;
	}

	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

}
