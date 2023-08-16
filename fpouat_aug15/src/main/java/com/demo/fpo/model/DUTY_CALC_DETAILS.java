package com.demo.fpo.model;


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "DUTY_CALC_DETAILS")
public class DUTY_CALC_DETAILS {
	
	@Id
	@GenericGenerator(name = "DUTY_CALC_SEQ", strategy = "com.seq.gen.DUTY_CALC_SEQIdGenerator")
	@GeneratedValue(generator = "DUTY_CALC_SEQ")
	@Column(name = "ID")
	public Long id;
	
	@Column(name = "NATURE_TRANS_CD")
	private String category;
	
	@Column(name = "NOTN")
	private String Notn;
	
	@Column(name = "SLNO")
	private String Slno;

	@Column(name = "BCDRTA")
	private Float Bcd_Rta;
	
	@Column(name = "BCDRTA_GIFT")
	private Float BcdRta_gift;
	
	@Column(name = "BCDRTA_EFF")
	private Float BcdRta_eff;
	
	@Column(name = "IGSTRTA")
	private Float Igst_Rta;
	
	public Float getBcdRta_eff() {
		return BcdRta_eff;
	}

	public void setBcdRta_eff(Float bcdRta_eff) {
		BcdRta_eff = bcdRta_eff;
	}

	public Float getBcdRta_gift() {
		return BcdRta_gift;
	}

	public void setBcdRta_gift(Float bcdRta_gift) {
		BcdRta_gift = bcdRta_gift;
	}

	@Column(name = "SWRTA")
	public Float Sw_Rta;

	@Column(name = "ASSVAL_AMT")
	private Float Assval_Amt;
	
	@Column(name = "CAT_ASSVAL_AMT")
	private Float CatAssval_Amt;

	public Float getCatAssval_Amt() {
		return CatAssval_Amt;
	}

	public void setCatAssval_Amt(Float catAssval_Amt) {
		CatAssval_Amt = catAssval_Amt;
	}

	public Float getAssval_Amt() {
		return Assval_Amt;
	}

	public void setAssval_Amt(Float assval_Amt) {
		Assval_Amt = assval_Amt;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getNotn() {
		return Notn;
	}

	public void setNotn(String notn) {
		Notn = notn;
	}

	public String getSlno() {
		return Slno;
	}

	public void setSlno(String slno) {
		Slno = slno;
	}

	public Float getBcd_Rta() {
		return Bcd_Rta;
	}

	public void setBcd_Rta(Float bcd_Rta) {
		Bcd_Rta = bcd_Rta;
	}

	public Float getIgst_Rta() {
		return Igst_Rta;
	}

	public void setIgst_Rta(Float igst_Rta) {
		Igst_Rta = igst_Rta;
	}

	public Float getSw_Rta() {
		return Sw_Rta;
	}

	public void setSw_Rta(Float sw_Rta) {
		Sw_Rta = sw_Rta;
	}


}
