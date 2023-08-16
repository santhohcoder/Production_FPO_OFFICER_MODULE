package com.demo.fpo.bean;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.transaction.Transactional;

@Entity
public class TotalIcCount {

	@Id
	private Long gift;
	private Long saleOfGoods;
	private Long documents;
	private Long returnedGoods;
	private Long others;
	private Long commercialSample;
	public Long getGift() {
		return gift;
	}
	public void setGift(Long gift) {
		this.gift = gift;
	}
	public Long getSaleOfGoods() {
		return saleOfGoods;
	}
	public void setSaleOfGoods(Long saleOfGoods) {
		this.saleOfGoods = saleOfGoods;
	}
	public Long getDocuments() {
		return documents;
	}
	public void setDocuments(Long documents) {
		this.documents = documents;
	}
	public Long getReturnedGoods() {
		return returnedGoods;
	}
	public void setReturnedGoods(Long returnedGoods) {
		this.returnedGoods = returnedGoods;
	}
	public Long getOthers() {
		return others;
	}
	public void setOthers(Long others) {
		this.others = others;
	}
	public Long getCommercialSample() {
		return commercialSample;
	}
	public void setCommercialSample(Long commercialSample) {
		this.commercialSample = commercialSample;
	}
	

}
