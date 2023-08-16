package com.demo.fpo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "fpo_second_defqry")
public class FpoSecondDefaultQuery {

	
	@Id
	@Column(name = "SLNO")
	public Long SLNO;

	@Column(name = "DESCRIPTION")
	private String DESCRIPTION;
	
	@Column(name = "INP_REQ")
	private Long INP_REQ;
	
	@Column(name = "SECQRY_SEQ")
	private Long SECQRY_SEQ;

	public Long getSLNO() {
		return SLNO;
	}

	public void setSLNO(Long sLNO) {
		SLNO = sLNO;
	}

	public String getDESCRIPTION() {
		return DESCRIPTION;
	}

	public void setDESCRIPTION(String dESCRIPTION) {
		DESCRIPTION = dESCRIPTION;
	}

	public Long getINP_REQ() {
		return INP_REQ;
	}

	public void setINP_REQ(Long iNP_REQ) {
		INP_REQ = iNP_REQ;
	}

	public Long getSECQRY_SEQ() {
		return SECQRY_SEQ;
	}

	public void setSECQRY_SEQ(Long sECQRY_SEQ) {
		SECQRY_SEQ = sECQRY_SEQ;
	}
	
	
	

	
	
}
