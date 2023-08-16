package com.demo.fpo.apimodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "CUSRSP_URL")
public class Cusrspurl {
	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	@GenericGenerator(name = "CUSRSP_URL_ID", strategy = "com.seq.gen.CUSRSP_URL_ID_SEQIdGenerator")
	@GeneratedValue(generator = "CUSRSP_URL_ID")
	@Column(name = "ID")
	public Long Id;
	
	@Column(name = "URL")
	private String URL;

	@Column(name = "METHODURL")
	public String METHODURL;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		this.Id = id;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getMETHODURL() {
		return METHODURL;
	}

	public void setMETHODURL(String mETHODURL) {
		METHODURL = mETHODURL;
	} 

}
