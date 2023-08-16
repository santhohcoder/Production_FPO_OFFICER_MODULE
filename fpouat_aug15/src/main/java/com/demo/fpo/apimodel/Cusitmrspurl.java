package com.demo.fpo.apimodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "CUSITMRSP_URL")
public class Cusitmrspurl {
	@Id
	@GenericGenerator(name = "CUSITMRSP_URL_ID", strategy = "com.seq.gen.CUSITMRSP_URL_IdGenerator")
	@GeneratedValue(generator = "CUSITMRSP_URL_ID")
	//@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID")
	public Long Id;
	
	@Column(name = "CUSITM_URL")
	private String CUSITM_URL;

	@Column(name = "CUSITM_METHODURL")
	public String CUSITM_METHODURL;

	@Column(name = "CUSRSP_URL")
	private String CUSRSP_URL;

	@Column(name = "CUSRSP_METHODURL")
	public String CUSRSP_METHODURL;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getCUSITM_URL() {
		return CUSITM_URL;
	}

	public void setCUSITM_URL(String cUSITM_URL) {
		CUSITM_URL = cUSITM_URL;
	}

	public String getCUSITM_METHODURL() {
		return CUSITM_METHODURL;
	}

	public void setCUSITM_METHODURL(String cUSITM_METHODURL) {
		CUSITM_METHODURL = cUSITM_METHODURL;
	}

	public String getCUSRSP_URL() {
		return CUSRSP_URL;
	}

	public void setCUSRSP_URL(String cUSRSP_URL) {
		CUSRSP_URL = cUSRSP_URL;
	}

	public String getCUSRSP_METHODURL() {
		return CUSRSP_METHODURL;
	}

	public void setCUSRSP_METHODURL(String cUSRSP_METHODURL) {
		CUSRSP_METHODURL = cUSRSP_METHODURL;
	}


}
