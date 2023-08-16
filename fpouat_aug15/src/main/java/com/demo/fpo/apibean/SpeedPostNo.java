package com.demo.fpo.apibean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class SpeedPostNo {

	@JsonProperty(value = "articlenumber")
	@Id
	    private String speedpost_no;

	public String getSpeedpost_no() {
		return speedpost_no;
	}

	public void setSpeedpost_no(String speedpost_no) {
		this.speedpost_no = speedpost_no;
	}

	
	
	
}
