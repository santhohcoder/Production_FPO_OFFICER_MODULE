package com.demo.fpo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SEQ_TAB")
public class FpoSeqTab {
	
	@Id
	@Column(name = "ID")
	public Long ID;
	
	@Column(name = "AMEND_ID")
	public Long AMEND_ID;
	
	@Column(name = "AMEND_MAIN_ID")
	public Long AMEND_MAIN_ID;

	@Column(name = "CIN_ID")
	public Long CIN_ID;
	
	@Column(name = "CURR_QUE_ID")
	public Long CURR_QUE_ID;
	
	@Column(name = "CUSITM_JOB_ID")
	public Long CUSITM_JOB_ID;
	
	@Column(name = "DEF_QRY_ID")
	public Long DEF_QRY_ID;
	
	@Column(name = "FPO_FINE_AMEND_ID")
	public Long FPO_FINE_AMEND_ID;
	
	@Column(name = "FPO_FINE_ID")
	public Long FPO_FINE_ID;
	
	@Column(name = "FPO_ID")
	public Long FPO_ID;
	
	@Column(name = "FPO_PENAL_AMEND_ID")
	public Long FPO_PENAL_AMEND_ID;
	
	@Column(name = "FPO_PENAL_ID")
	public Long FPO_PENAL_ID;
	
	@Column(name = "FPO_QRY_DECI_ID")
	public Long FPO_QRY_DECI_ID;
	
	@Column(name = "FPO_QRY_DIN_ID")
	public Long FPO_QRY_DIN_ID;
	
	@Column(name = "ITEM_ID")
	public Long ITEM_ID;
	
	@Column(name = "ORDER_ID")
	public Long ORDER_ID;
	
	@Column(name = "OTHER_AMEND_ID")
	public Long OTHER_AMEND_ID;
	
	@Column(name = "OTHER_ID")
	public Long OTHER_ID;
	
	@Column(name = "EXAM_ID")
	public Long EXAM_ID;
	
	@Column(name = "OOC_ID")
	public Long OOC_ID;

	public Long getEXAM_ID() {
		return EXAM_ID;
	}

	public void setEXAM_ID(Long eXAM_ID) {
		EXAM_ID = eXAM_ID;
	}

	public Long getOOC_ID() {
		return OOC_ID;
	}

	public void setOOC_ID(Long oOC_ID) {
		OOC_ID = oOC_ID;
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public Long getAMEND_ID() {
		return AMEND_ID;
	}

	public void setAMEND_ID(Long aMEND_ID) {
		AMEND_ID = aMEND_ID;
	}

	public Long getAMEND_MAIN_ID() {
		return AMEND_MAIN_ID;
	}

	public void setAMEND_MAIN_ID(Long aMEND_MAIN_ID) {
		AMEND_MAIN_ID = aMEND_MAIN_ID;
	}

	public Long getCIN_ID() {
		return CIN_ID;
	}

	public void setCIN_ID(Long cIN_ID) {
		CIN_ID = cIN_ID;
	}

	public Long getCURR_QUE_ID() {
		return CURR_QUE_ID;
	}

	public void setCURR_QUE_ID(Long cURR_QUE_ID) {
		CURR_QUE_ID = cURR_QUE_ID;
	}

	public Long getCUSITM_JOB_ID() {
		return CUSITM_JOB_ID;
	}

	public void setCUSITM_JOB_ID(Long cUSITM_JOB_ID) {
		CUSITM_JOB_ID = cUSITM_JOB_ID;
	}

	public Long getDEF_QRY_ID() {
		return DEF_QRY_ID;
	}

	public void setDEF_QRY_ID(Long dEF_QRY_ID) {
		DEF_QRY_ID = dEF_QRY_ID;
	}

	public Long getFPO_FINE_AMEND_ID() {
		return FPO_FINE_AMEND_ID;
	}

	public void setFPO_FINE_AMEND_ID(Long fPO_FINE_AMEND_ID) {
		FPO_FINE_AMEND_ID = fPO_FINE_AMEND_ID;
	}

	public Long getFPO_FINE_ID() {
		return FPO_FINE_ID;
	}

	public void setFPO_FINE_ID(Long fPO_FINE_ID) {
		FPO_FINE_ID = fPO_FINE_ID;
	}

	public Long getFPO_ID() {
		return FPO_ID;
	}

	public void setFPO_ID(Long fPO_ID) {
		FPO_ID = fPO_ID;
	}

	public Long getFPO_PENAL_AMEND_ID() {
		return FPO_PENAL_AMEND_ID;
	}

	public void setFPO_PENAL_AMEND_ID(Long fPO_PENAL_AMEND_ID) {
		FPO_PENAL_AMEND_ID = fPO_PENAL_AMEND_ID;
	}

	public Long getFPO_PENAL_ID() {
		return FPO_PENAL_ID;
	}

	public void setFPO_PENAL_ID(Long fPO_PENAL_ID) {
		FPO_PENAL_ID = fPO_PENAL_ID;
	}

	public Long getFPO_QRY_DECI_ID() {
		return FPO_QRY_DECI_ID;
	}

	public void setFPO_QRY_DECI_ID(Long fPO_QRY_DECI_ID) {
		FPO_QRY_DECI_ID = fPO_QRY_DECI_ID;
	}

	public Long getFPO_QRY_DIN_ID() {
		return FPO_QRY_DIN_ID;
	}

	public void setFPO_QRY_DIN_ID(Long fPO_QRY_DIN_ID) {
		FPO_QRY_DIN_ID = fPO_QRY_DIN_ID;
	}

	public Long getITEM_ID() {
		return ITEM_ID;
	}

	public void setITEM_ID(Long iTEM_ID) {
		ITEM_ID = iTEM_ID;
	}

	public Long getORDER_ID() {
		return ORDER_ID;
	}

	public void setORDER_ID(Long oRDER_ID) {
		ORDER_ID = oRDER_ID;
	}

	public Long getOTHER_AMEND_ID() {
		return OTHER_AMEND_ID;
	}

	public void setOTHER_AMEND_ID(Long oTHER_AMEND_ID) {
		OTHER_AMEND_ID = oTHER_AMEND_ID;
	}

	public Long getOTHER_ID() {
		return OTHER_ID;
	}

	public void setOTHER_ID(Long oTHER_ID) {
		OTHER_ID = oTHER_ID;
	}
	
}
