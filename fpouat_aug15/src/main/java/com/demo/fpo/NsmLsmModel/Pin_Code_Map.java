package com.demo.fpo.NsmLsmModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "OPS$DIR.PDI_PINCODE_MAPPING_INDIA")
public class Pin_Code_Map {

	@Id
	@GenericGenerator(name = "PINCODE_MAP_SEQ", strategy = "com.seq.gen.Pincode_map_insertrepo")
	@GeneratedValue(generator = "PINCODE_MAP_SEQ")
	private String fpo_code;
	
	private String state_name;
	
	private String inbound_start_pincode;
	
	private String inbound_end_pincode;
	
	private String fpo_destn;
	
	private String postal_site_code;
	
	private String customs_fpo_site_code;

	public Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFpo_code() {
		return fpo_code;
	}

	public void setFpo_code(String fpo_code) {
		this.fpo_code = fpo_code;
	}

	public String getState_name() {
		return state_name;
	}

	public void setState_name(String state_name) {
		this.state_name = state_name;
	}

	public String getInbound_start_pincode() {
		return inbound_start_pincode;
	}

	public void setInbound_start_pincode(String inbound_start_pincode) {
		this.inbound_start_pincode = inbound_start_pincode;
	}

	public String getInbound_end_pincode() {
		return inbound_end_pincode;
	}

	public void setInbound_end_pincode(String inbound_end_pincode) {
		this.inbound_end_pincode = inbound_end_pincode;
	}

	public String getFpo_destn() {
		return fpo_destn;
	}

	public void setFpo_destn(String fpo_destn) {
		this.fpo_destn = fpo_destn;
	}

	public String getPostal_site_code() {
		return postal_site_code;
	}

	public void setPostal_site_code(String postal_site_code) {
		this.postal_site_code = postal_site_code;
	}

	public String getCustoms_fpo_site_code() {
		return customs_fpo_site_code;
	}

	public void setCustoms_fpo_site_code(String customs_fpo_site_code) {
		this.customs_fpo_site_code = customs_fpo_site_code;
	}
	
}
