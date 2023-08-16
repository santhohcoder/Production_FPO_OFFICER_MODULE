package com.demo.fpo.apimodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "OPS$DIR.FPO_SITE_ALLOT")
public class FpoSite_Allot {
	
	@Id
	@Column(name = "SITE_CODE") 
	private String SITE_CODE;
	
	@Column(name = "LETTER_AIR") 
	private int LETTER_AIR;
	
	@Column(name = "LETTER_SAL") 
	private int LETTER_SAL;
	
	@Column(name = "LETTER_SEA") 
	private int LETTER_SEA;
	
	@Column(name = "EMS_AIR") 
	private int EMS_AIR;
	
	@Column(name = "EMS_SAL") 
	private int EMS_SAL;
	
	@Column(name = "EMS_SEA") 
	private int EMS_SEA;
	
	@Column(name = "PARCEL_AIR") 
	private int PARCEL_AIR;
	
	@Column(name = "PARCEL_SAL") 
	private int PARCEL_SAL;
	
	@Column(name = "PARCEL_SEA") 
	private int PARCEL_SEA;

	public String getSITE_CODE() {
		return SITE_CODE;
	}

	public void setSITE_CODE(String sITE_CODE) {
		SITE_CODE = sITE_CODE;
	}

	public int getLETTER_AIR() {
		return LETTER_AIR;
	}

	public void setLETTER_AIR(int lETTER_AIR) {
		LETTER_AIR = lETTER_AIR;
	}

	public int getLETTER_SAL() {
		return LETTER_SAL;
	}

	public void setLETTER_SAL(int lETTER_SAL) {
		LETTER_SAL = lETTER_SAL;
	}

	public int getLETTER_SEA() {
		return LETTER_SEA;
	}

	public void setLETTER_SEA(int lETTER_SEA) {
		LETTER_SEA = lETTER_SEA;
	}

	public int getEMS_AIR() {
		return EMS_AIR;
	}

	public void setEMS_AIR(int eMS_AIR) {
		EMS_AIR = eMS_AIR;
	}

	public int getEMS_SAL() {
		return EMS_SAL;
	}

	public void setEMS_SAL(int eMS_SAL) {
		EMS_SAL = eMS_SAL;
	}

	public int getEMS_SEA() {
		return EMS_SEA;
	}

	public void setEMS_SEA(int eMS_SEA) {
		EMS_SEA = eMS_SEA;
	}

	public int getPARCEL_AIR() {
		return PARCEL_AIR;
	}

	public void setPARCEL_AIR(int pARCEL_AIR) {
		PARCEL_AIR = pARCEL_AIR;
	}

	public int getPARCEL_SAL() {
		return PARCEL_SAL;
	}

	public void setPARCEL_SAL(int pARCEL_SAL) {
		PARCEL_SAL = pARCEL_SAL;
	}

	public int getPARCEL_SEA() {
		return PARCEL_SEA;
	}

	public void setPARCEL_SEA(int pARCEL_SEA) {
		PARCEL_SEA = pARCEL_SEA;
	}
	
	
	

}
