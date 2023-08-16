package com.demo.fpo.NsmLsmModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "OPS$DIR.FPO_SITE_INFO")
public class FPO_SITE_INFO {
	
	@Id
	@GenericGenerator(name = "FPO_SITE_INFO", strategy = "com.seq.gen.fpo_site_info_seq")
	@GeneratedValue(generator = "FPO_SITE_INFO")
	@Column(name = "ID")
	public Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "OFF_ID")
	private String offid;
	
	@Column(name = "FPO_SITE")
	private String fposite;
	
	@Column(name = "ADDRESS")
	private String address;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "PHONE")
	private String phne;
	
	@Column(name = "VISIT_HOURS")
	private String visithrs;
	
	@Column(name = "ROLE")
	private String role;
	
	@Column(name = "END_DT")
	private String enddt;
	
	@Column(name = "CITY")
	private String city;
	
	@Column(name = "STATE")
	private String state;
	
	@Column(name = "FPO_SITE_NAME")
	private String fpositeName;

	@Column(name = "PIN_CODE")
	private String pincode;

	public String getFpositeName() {
		return fpositeName;
	}

	public void setFpositeName(String fpositeName) {
		this.fpositeName = fpositeName;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getOffid() {
		return offid;
	}

	public void setOffid(String offid) {
		this.offid = offid;
	}

	public String getFposite() {
		return fposite;
	}

	public void setFposite(String fposite) {
		this.fposite = fposite;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhne() {
		return phne;
	}

	public void setPhne(String phne) {
		this.phne = phne;
	}

	public String getVisithrs() {
		return visithrs;
	}

	public void setVisithrs(String visithrs) {
		this.visithrs = visithrs;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEnddt() {
		return enddt;
	}

	public void setEnddt(String enddt) {
		this.enddt = enddt;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
}
