package com.demo.fpo.NsmLsmModel;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "OPS$DIR.PINCODE_MAPPING_FPOSITE")
public class Pincode_chnage {

	@Id
	@GenericGenerator(name = "PINCODE_SEQ", strategy = "com.seq.gen.Pincode_Seq")
	@GeneratedValue(generator = "PINCODE_SEQ")
	private Long id;
	
	private String mapped_site_code;
	
	private String mapped_state_name;
	
	private String start_pincode;
	
	private String end_pincode;
	
	private String off_id;
	
	private Date map_dt;
	
	private String pincode_typ;
	
	private String reason;
	
	private String new_map_cussite;
	
	private String new_state;

	public String getNew_state() {
		return new_state;
	}

	public void setNew_state(String new_state) {
		this.new_state = new_state;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getMapped_site_code() {
		return mapped_site_code;
	}

	public void setMapped_site_code(String mapped_site_code) {
		this.mapped_site_code = mapped_site_code;
	}

	public String getMapped_state_name() {
		return mapped_state_name;
	}

	public void setMapped_state_name(String mapped_state_name) {
		this.mapped_state_name = mapped_state_name;
	}

	public String getStart_pincode() {
		return start_pincode;
	}

	public void setStart_pincode(String start_pincode) {
		this.start_pincode = start_pincode;
	}

	public String getEnd_pincode() {
		return end_pincode;
	}

	public void setEnd_pincode(String end_pincode) {
		this.end_pincode = end_pincode;
	}

	public String getOff_id() {
		return off_id;
	}

	public void setOff_id(String off_id) {
		this.off_id = off_id;
	}

	public Date getMap_dt() {
		return map_dt;
	}

	public void setMap_dt(Date map_dt) {
		this.map_dt = map_dt;
	}

	public String getPincode_typ() {
		return pincode_typ;
	}

	public void setPincode_typ(String pincode_typ) {
		this.pincode_typ = pincode_typ;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getNew_map_cussite() {
		return new_map_cussite;
	}

	public void setNew_map_cussite(String new_map_cussite) {
		this.new_map_cussite = new_map_cussite;
	}
	
	
}
