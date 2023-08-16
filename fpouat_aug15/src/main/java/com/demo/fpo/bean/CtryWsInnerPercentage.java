package com.demo.fpo.bean;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CtryWsInnerPercentage {
	@Id
	private BigDecimal gift;
	private BigDecimal saleOfGoods;
	private BigDecimal returnedGoods;
	private BigDecimal commercialSample;
	private BigDecimal documents;
	private BigDecimal others;
	private BigDecimal noOfGift;
	private BigDecimal noOfSaleOfGoods;
	private BigDecimal noOfReturnedGoods;
	private BigDecimal noOfCommercialSample;
	private BigDecimal noOfDocuments;
	private BigDecimal noOfOthers;
	public BigDecimal getNoOfGift() {
		return noOfGift;
	}
	public void setNoOfGift(BigDecimal noOfGift) {
		this.noOfGift = noOfGift;
	}
	public BigDecimal getNoOfSaleOfGoods() {
		return noOfSaleOfGoods;
	}
	public void setNoOfSaleOfGoods(BigDecimal noOfSaleOfGoods) {
		this.noOfSaleOfGoods = noOfSaleOfGoods;
	}
	public BigDecimal getNoOfReturnedGoods() {
		return noOfReturnedGoods;
	}
	public void setNoOfReturnedGoods(BigDecimal noOfReturnedGoods) {
		this.noOfReturnedGoods = noOfReturnedGoods;
	}
	public BigDecimal getNoOfCommercialSample() {
		return noOfCommercialSample;
	}
	public void setNoOfCommercialSample(BigDecimal noOfCommercialSample) {
		this.noOfCommercialSample = noOfCommercialSample;
	}
	public BigDecimal getNoOfDocuments() {
		return noOfDocuments;
	}
	public void setNoOfDocuments(BigDecimal noOfDocuments) {
		this.noOfDocuments = noOfDocuments;
	}
	public BigDecimal getNoOfOthers() {
		return noOfOthers;
	}
	public void setNoOfOthers(BigDecimal noOfOthers) {
		this.noOfOthers = noOfOthers;
	}
	public BigDecimal getGift() {
		return gift;
	}
	public void setGift(BigDecimal gift) {
		this.gift = gift;
	}
	public BigDecimal getSaleOfGoods() {
		return saleOfGoods;
	}
	public void setSaleOfGoods(BigDecimal saleOfGoods) {
		this.saleOfGoods = saleOfGoods;
	}
	public BigDecimal getReturnedGoods() {
		return returnedGoods;
	}
	public void setReturnedGoods(BigDecimal returnedGoods) {
		this.returnedGoods = returnedGoods;
	}
	public BigDecimal getCommercialSample() {
		return commercialSample;
	}
	public void setCommercialSample(BigDecimal commercialSample) {
		this.commercialSample = commercialSample;
	}
	public BigDecimal getDocuments() {
		return documents;
	}
	public void setDocuments(BigDecimal documents) {
		this.documents = documents;
	}
	public BigDecimal getOthers() {
		return others;
	}
	public void setOthers(BigDecimal others) {
		this.others = others;
	}
	
	
}
